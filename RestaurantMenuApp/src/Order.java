import java.util.ArrayList;
import java.util.List;


public class Order {

    public static final double VAT_RATE = 0.09; // 9%

    private List<OrderItem> items;
    private DiscountStrat discountStrategy;

    public Order() {
        this.items = new ArrayList<>();
        this.discountStrategy = null;
    }

    public void addProduct(Produs product, int quantity) {
        items.add(new OrderItem(product, quantity));
    }

    public void setDiscountStrategy(DiscountStrat strategy) {
        this.discountStrategy = strategy;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getSubtotal() {
        double subtotal = 0.0;
        for (OrderItem item : items) {
            subtotal += item.getSubtotal();
        }
        return subtotal;
    }

    public double getDiscount() {
        if (discountStrategy != null) {
            return discountStrategy.applyDiscount(this);
        }
        return 0.0;
    }

    public double getVAT() {
        double amountAfterDiscount = getSubtotal() - getDiscount();
        return amountAfterDiscount * VAT_RATE;
    }

    public double getTotal() {
        return getSubtotal() - getDiscount() + getVAT();
    }

    public void printReceipt() {
        System.out.println("\n========== CHITANȚĂ ==========");
        System.out.println("Restaurant \"La Andrei\"\n");

        System.out.println("Produse comandate:");
        for (OrderItem item : items) {
            System.out.println("  " + item);
        }

        System.out.println("\n------------------------------");
        System.out.printf("Subtotal:        %8.2f RON\n", getSubtotal());

        if (discountStrategy != null && getDiscount() > 0) {
            System.out.println("\nOfertă specială: " + discountStrategy.getDescription());
            System.out.printf("Reducere:       -%8.2f RON\n", getDiscount());
        }

        System.out.printf("TVA (%.0f%%):       %8.2f RON\n", VAT_RATE * 100, getVAT());
        System.out.println("------------------------------");
        System.out.printf("TOTAL DE PLATĂ:  %8.2f RON\n", getTotal());
        System.out.println("==============================\n");
    }
}