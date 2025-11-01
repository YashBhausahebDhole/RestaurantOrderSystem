package restaurant.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class Receipt {
    private final String customerName;
    private final List<MenuItem> items;
    private final double total;
    private final LocalDateTime timestamp;

    public Receipt(String customerName, List<MenuItem> items, double total, LocalDateTime timestamp) {
        this.customerName = customerName;
        this.items = Collections.unmodifiableList(items);
        this.total = total;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        sb.append("********** RECEIPT **********\n");
        sb.append("Customer: ").append(customerName).append("\n");
        sb.append("------------------------------\n");

        for (MenuItem item : items) {
            double original = item.getPrice();
            double finalPrice = original;

            if (Discountable.isEligible(original)) {
                finalPrice = item.applyDiscount(original);
                sb.append(String.format(" - %-15s €%.2f → €%.2f\n",
                        item.getName(), original, finalPrice));
                sb.append("   > ").append(item.discountReason()).append("\n");
            } else {
                sb.append(String.format(" - %-15s €%.2f\n", item.getName(), original));
            }
        }

        sb.append("------------------------------\n");
        sb.append(String.format("Total after discounts: €%.2f\n", total));
        sb.append("Date: ").append(timestamp.format(fmt)).append("\n");
        sb.append("******************************\n");

        return sb.toString();
    }
}
