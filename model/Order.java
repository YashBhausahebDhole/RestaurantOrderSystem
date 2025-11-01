package restaurant.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Order {
    private final Customer customer;
    private final List<MenuItem> items = new ArrayList<>();

    public Order(Customer customer) {
        this.customer = Objects.requireNonNull(customer);
    }

    public void addItems(MenuItem... menuItems) {
        for (MenuItem m : menuItems) {
            if (m != null) items.add(m);
        }
    }

    public void addItem(MenuItem menuItem) {
        if (menuItem == null) throw new IllegalArgumentException("menuItem cannot be null");
        items.add(menuItem);
    }

    public List<MenuItem> getItemsCopy() {
        return Collections.unmodifiableList(new ArrayList<>(items));
    }

    // Updated: Apply discount dynamically per item
    public double calculateTotal() {
        double total = 0;
        for (MenuItem item : items) {
            double price = item.getPrice();
            if (Discountable.isEligible(price)) {
                price = item.applyDiscount(price);
            }
            total += price;
        }
        return total;
    }

    public Receipt generateReceipt() {
        double total = calculateTotal();
        return new Receipt(customer.name(), getItemsCopy(), total, LocalDateTime.now());
    }

    public List<MenuItem> filterItems(Predicate<MenuItem> predicate) {
        return items.stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Order for " + customer.name() + " with " + items.size() + " items.";
    }
}
