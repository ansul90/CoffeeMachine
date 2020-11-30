import services.CoffeeMachineService;

public class CoffeeMachineDriver {
    public static void main(String[] args) {
        CoffeeMachineService coffeeMachineService = new CoffeeMachineService();
        String url = "https://api.npoint.io/c86888c679448e8831c7";
        coffeeMachineService.readDataFromURL(url);
    }
}
