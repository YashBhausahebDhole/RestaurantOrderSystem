package restaurant.model;

public final class FoodItem extends MenuItem {
    private int calories;

    public FoodItem(String name, Category category, double price) {
        super(name, category, price);
    }

    @Override
    public double applyDiscount(double price) {
        // 10% discount for all food
        return price * 0.9;
    }

    @Override
    public String discountReason() {
        return "10% discount applied on food items.";
    }

    public int getCalories() { return calories; }
    public void setCalories(int calories) { this.calories = calories; }
}
