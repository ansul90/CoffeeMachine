package test;

import org.junit.Test;
import services.CoffeeMachineService;

public class CoffeeDriverTest {

    CoffeeMachineService coffeeMachineService = new CoffeeMachineService();

    @Test
    public void checkItemQuantity() {
        System.out.println("Checking items quantity test case");
        coffeeMachineService.readDataFromFile("../CoffeeMachine/src/resources/sample.json");
    }

    @Test
    public void checkItemAvailability() {
        System.out.println("Checking items availability test case");
        coffeeMachineService.readDataFromFile("../CoffeeMachine/src/resources/sample_1.json");
    }
}
