
public interface DiscountStrat {

    double applyDiscount(Order order);
    String getDescription();
}