package restaurant.model;

public abstract sealed class MenuItem implements Discountable permits FoodItem, DrinkItem {
    private final String name;
    private final Category category;
    private final double price;

    protected MenuItem(String name, Category category, double price) {
        if (name == null) throw new IllegalArgumentException("Name cannot be null");
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() { return name; }
    public Category getCategory() { return category; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return name + " (" + category + ") - €" + String.format("%.2f", price);
    }
}
