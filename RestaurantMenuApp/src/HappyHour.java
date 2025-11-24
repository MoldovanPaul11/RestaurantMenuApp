

public class HappyHour implements DiscountStrat {
    private double discountPercentage;

    public HappyHour() {
        this.discountPercentage = 0.20; // 20% reducere
    }

    public HappyHour(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double applyDiscount(Order order) {
        double totalDiscount = 0.0;

        for (OrderItem item : order.getItems()) {
            Produs product = item.getProduct();

            // Verificăm dacă produsul este o băutură alcoolică
            if (product instanceof Bautura) {
                Bautura drink = (Bautura) product;
                if (drink.isAlcoholic()) {
                    totalDiscount += item.getSubtotal() * discountPercentage;
                }
            }
        }

        return totalDiscount;
    }

    @Override
    public String getDescription() {
        return String.format("Happy Hour: %.0f%% reducere la băuturi alcoolice",
                discountPercentage * 100);
    }
}