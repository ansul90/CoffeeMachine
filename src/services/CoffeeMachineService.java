package services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.dao.InputDao;
import models.decorator.BeverageDecorator;
import models.factory.ContentFactory;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;

public class CoffeeMachineService {
    ContentFactory contentFactory = new ContentFactory();
    Set<String> availableItems = new HashSet<>();

    public void readDataFromFile(String location) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputDao machineDao = mapper.readValue(Paths.get(location).toFile(), InputDao.class);
            process(machineDao);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readDataFromURL(String url) {
        ObjectMapper objectMapper = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            InputDao machineDao = objectMapper.readValue(new URL(url), InputDao.class);
            process(machineDao);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void process(InputDao machineDao) {
        initializeMachine(machineDao.getMachine().getTotalItemsQuantity());
        Map<String, Map<String, Integer>> beveragesList = machineDao.getMachine().getBeverages();
        boolean flag = true;
        for (Map.Entry<String, Map<String, Integer>> hM : beveragesList.entrySet()) {
            Map<String, Integer> contentsMap = hM.getValue();
            List<String> remaining = new ArrayList<>();
            List<String> unavailableList = new ArrayList<>();
            for (Map.Entry<String, Integer> content : contentsMap.entrySet()) {
                BeverageDecorator beverageDecorator = contentFactory.getContent(content.getKey());
                if (!availableItems.contains(content.getKey())) {
                    unavailableList.add(content.getKey());
                    flag = false;
                    break;
                } else if (!isPossible(beverageDecorator, content.getValue())) {
                    flag = false;
                    remaining.add(content.getKey());
                }
            }
            if (flag) {
                for (Map.Entry<String, Integer> content : contentsMap.entrySet()) {
                    BeverageDecorator beverageDecorator = contentFactory.getContent(content.getKey());
                    beverageDecorator.updateQuantity(content.getValue());
                }
                System.out.println(hM.getKey() + " is prepared");
            } else {
                if (!unavailableList.isEmpty()) {
                    System.out.println(hM.getKey() + " cannot be prepared because " + String.join(",", unavailableList) + " is not available");
                } else {
                    System.out.println(hM.getKey() + " cannot be prepared because item " + String.join(",", remaining) + " is not sufficient");
                }
                flag = true;
            }
        }
    }

    private boolean isPossible(BeverageDecorator beverageDecorator, int requiredQuantity) {
        return beverageDecorator.getTotalQuantity() >= requiredQuantity;
    }

    private void initializeMachine(Map<String, Integer> totalContents) {
        for (Map.Entry<String, Integer> content : totalContents.entrySet()) {
            BeverageDecorator beverageDecorator = contentFactory.getContent(content.getKey());
            beverageDecorator.setTotalQuantity(content.getValue());
            availableItems.add(content.getKey());
        }
    }
}
