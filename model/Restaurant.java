package restaurant.model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class Restaurant {
    private final String name;
    private final List<MenuItem> menu = new ArrayList<>();

    public Restaurant(String name) {
        this.name = name;
    }

    // 👇 ADD THESE TWO METHODS
    public String getName() {
        return name;
    }

    public List<MenuItem> getMenuList() {
        return new ArrayList<>(menu);
    }
    // 👆 ADD THESE TWO METHODS

    public void addMenuItem(MenuItem item) {
        if (item == null) throw new IllegalArgumentException("MenuItem cannot be null");
        menu.add(item);
    }

    public void addMenuItem(String name, Category cat, double price) {
        menu.add(new FoodItem(name, cat, price));
    }

    public void addMenuItems(MenuItem... items) {
        for (MenuItem m : items) addMenuItem(m);
    }

    public Stream<MenuItem> getMenuStream() {
        return menu.stream();
    }

    public void printMenu() {
        System.out.println("Menu for " + name + ":");
        menu.forEach(System.out::println);
    }

    public Order createOrder(Customer c, MenuItem... items) {
        Order o = new Order(c);
        o.addItems(items);
        return o;
    }

    public MenuItem findMenuByName(String name) {
        return menu.stream()
                .filter(i -> i.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Item not found: " + name));
    }
}
