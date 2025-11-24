
public abstract class Produs {
    private String name;
    private double price;

    public Produs(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public abstract String getSpecificDetails();

    @Override
    public String toString() {
        return String.format("%s - %.1f RON - %s", name, price, getSpecificDetails());
    }
}