
public final class Mancare extends Produs {
    private int weight;
    private boolean isVegetarian;

    public Mancare(String name, double price, int weight) {
        this(name, price, weight, false);
    }

    public Mancare(String name, double price, int weight, boolean isVegetarian) {
        super(name, price);
        this.weight = weight;
        this.isVegetarian = isVegetarian;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    @Override
    public String getSpecificDetails() {
        return String.format("Gramaj: %dg", weight);
    }
}