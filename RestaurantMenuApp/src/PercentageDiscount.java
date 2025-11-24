

public class PercentageDiscount implements DiscountStrat {
    private double discountPercentage;
    private String occasion;

    public PercentageDiscount(double discountPercentage, String occasion) {
        this.discountPercentage = discountPercentage;
        this.occasion = occasion;
    }

    @Override
    public double applyDiscount(Order order) {
        return order.getSubtotal() * discountPercentage;
    }

    @Override
    public String getDescription() {
        return String.format("%s: %.0f%% reducere la întreaga comandă",
                occasion, discountPercentage * 100);
    }
}