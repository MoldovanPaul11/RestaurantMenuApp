
public class OrderItem {
    private Produs product;
    private int quantity;

    public OrderItem(Produs product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Produs getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return String.format("%d x %s - Subtotal: %.2f RON",
                quantity, product.getName(), getSubtotal());
    }
}