package restaurant.model;


public interface Discountable {
    double applyDiscount(double price); // abstract method

    default double applyFixedDiscount(double price, double amount) {
        return Math.max(0, price - amount);
    }

    static boolean isEligible(double price) {
        return price > 5.0; // only apply discount if price > 5
    }

    // private helper (Java 9+ feature)
    private static double clamp(double v) {
        return v < 0 ? 0 : v;
    }

    default String discountReason() {
        return "General discount applied for eligible item.";
    }
}
