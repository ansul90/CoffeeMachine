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

    // Reads JSON from URL and pass the DAO to the process function
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

    /* Processes the input JSON and use a combination of decorator & singleton design pattern
    Initialize the coffee machine with initial available units
    Validates whether item is available and if available is the quantity enough
    */
    private void process(InputDao machineDao) {
        initializeMachine(machineDao.getMachine().getTotalItemsQuantity());
        Map<String, Map<String, Integer>> beveragesList = machineDao.getMachine().getBeverages();
        boolean flag = true;
        // Get beverages from input and parse the contents
        for (Map.Entry<String, Map<String, Integer>> hM : beveragesList.entrySet()) {
            Map<String, Integer> contentsMap = hM.getValue();
            List<String> remaining = new ArrayList<>();
            List<String> unavailableList = new ArrayList<>();
            // Parse contents for a beverage
            for (Map.Entry<String, Integer> content : contentsMap.entrySet()) {
                BeverageDecorator beverageDecorator = contentFactory.getContent(content.getKey());
                // if item is available
                if (!availableItems.contains(content.getKey())) {
                    unavailableList.add(content.getKey());
                    flag = false;
                    break;
                    // if item quantity is enough
                } else if (!isPossible(beverageDecorator, content.getValue())) {
                    flag = false;
                    remaining.add(content.getKey());
                }
            }
            // updating the quantity of the content
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

    // Verifies whether the quantity is available
    private boolean isPossible(BeverageDecorator beverageDecorator, int requiredQuantity) {
        return beverageDecorator.getTotalQuantity() >= requiredQuantity;
    }

    // Initialise machine with total quantity available
    private void initializeMachine(Map<String, Integer> totalContents) {
        for (Map.Entry<String, Integer> content : totalContents.entrySet()) {
            BeverageDecorator beverageDecorator = contentFactory.getContent(content.getKey());
            beverageDecorator.setTotalQuantity(content.getValue());
            availableItems.add(content.getKey());
        }
    }
}
