import java.util.ArrayList;
import java.util.List;

public final class Pizza extends Produs {
    private String dough; // type of dough (mandatory)
    private String sauce; // type of sauce (mandatory)
    private List<String> toppings; // optional toppings

    private Pizza(PizzaBuilder builder) {
        super(builder.name, builder.price);
        this.dough = builder.dough;
        this.sauce = builder.sauce;
        this.toppings = new ArrayList<>(builder.toppings);
    }

    public String getDough() {
        return dough;
    }

    public String getSauce() {
        return sauce;
    }

    public List<String> getToppings() {
        return new ArrayList<>(toppings);
    }

    @Override
    public String getSpecificDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Blat: ").append(dough).append(" | Sos: ").append(sauce);
        if (!toppings.isEmpty()) {
            details.append(" | Toppinguri: ").append(String.join(", ", toppings));
        }
        return details.toString();
    }

    // Builder Pattern
    public static class PizzaBuilder {
        private String name;
        private double price;
        private String dough; // mandatory
        private String sauce; // mandatory
        private List<String> toppings = new ArrayList<>();

        public PizzaBuilder(String name, double price, String dough, String sauce) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Pizza name cannot be empty");
            }
            if (price <= 0) {
                throw new IllegalArgumentException("Pizza price must be positive");
            }
            if (dough == null || dough.trim().isEmpty()) {
                throw new IllegalArgumentException("Dough type cannot be empty");
            }
            if (sauce == null || sauce.trim().isEmpty()) {
                throw new IllegalArgumentException("Sauce type cannot be empty");
            }
            this.name = name;
            this.price = price;
            this.dough = dough;
            this.sauce = sauce;
        }

        public PizzaBuilder addTopping(String topping) {
            if (topping != null && !topping.trim().isEmpty()) {
                this.toppings.add(topping);
            }
            return this;
        }

        public PizzaBuilder addToppings(String... toppings) {
            for (String topping : toppings) {
                addTopping(topping);
            }
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }
}

