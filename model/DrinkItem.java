package restaurant.model;

public final class DrinkItem extends MenuItem {
    private boolean isHot;

    public DrinkItem(String name, Category category, double price) {
        super(name, category, price);
    }

    @Override
    public double applyDiscount(double price) {
        // 5% discount for drinks above €5
        if (price > 5.0)
            return price * 0.95;
        else
            return price; // no discount for cheaper drinks
    }

    @Override
    public String discountReason() {
        return "5% discount applied on premium drinks over €5.";
    }

    public boolean isHot() { return isHot; }
    public void setHot(boolean hot) { isHot = hot; }
}
