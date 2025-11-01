package restaurant;

import restaurant.model.*;

import java.util.*;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Restaurant app = new Restaurant("Mumbai Diner");

        // Sample menu
        app.addMenuItems(
                new FoodItem("Vadapav", Category.MAIN, 8.99),
                new FoodItem("Misal", Category.SIDE, 2.99),
                new DrinkItem("Lassi", Category.DRINK, 1.50),
                new FoodItem("Gulab Jamun", Category.MAIN, 10.50),
                new FoodItem("Salad", Category.SIDE, 3.50),
                new DrinkItem("Chai", Category.DRINK, 2.00)
        );

        System.out.println("Welcome to " + app.getName() + "!");
        System.out.println("----------------------------------");
        System.out.print("Enter your name: ");
        String userName = sc.nextLine().trim();
        Customer customer = new Customer(userName, "A00335962"); // demo ID

        System.out.println("\nHere’s our Menu:");
        List<MenuItem> menuList = app.getMenuList();
        for (int i = 0; i < menuList.size(); i++) {
            System.out.println((i + 1) + ". " + menuList.get(i));
        }

        System.out.print("\nEnter item numbers you’d like to order (comma separated, e.g. 1,3,4): ");
        String[] inputs = sc.nextLine().split(",");
        List<MenuItem> selectedItems = new ArrayList<>();

        try {
            for (String s : inputs) {
                int index = Integer.parseInt(s.trim()) - 1;
                if (index < 0 || index >= menuList.size())
                    throw new IllegalArgumentException("Invalid menu choice: " + (index + 1));
                selectedItems.add(menuList.get(index));
            }

            // Create order
            Order order = app.createOrder(customer, selectedItems.toArray(new MenuItem[0]));

            System.out.println("\nGenerating your receipt...");
            System.out.println("----------------------------------");
            System.out.println(order.generateReceipt());
            System.out.println("----------------------------------");

            // Lambda example: filter cheap items
            Predicate<MenuItem> cheap = i -> i.getPrice() < 5.0;
            System.out.println("\n Items under €5 in your order:");
            order.filterItems(cheap).forEach(System.out::println);

            System.out.println("\nThank you for ordering with " + app.getName() + "!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}
