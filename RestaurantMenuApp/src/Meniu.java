import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Optional;
import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Meniu {
    private List<Produs> products;
    private Map<String, List<Produs>> productsByCategory;

    public Meniu() {
        this.products = new ArrayList<>();
        this.productsByCategory = new HashMap<>();
    }

    public void addProduct(Produs product) {
        addProduct(product, "Neclasificat");
    }

    public void addProduct(Produs product, String category) {
        products.add(product);
        productsByCategory.computeIfAbsent(category, k -> new ArrayList<>()).add(product);
    }

    public List<Produs> getProducts() {
        return products;
    }

    public List<Produs> getProductsByCategory(String category) {
        return productsByCategory.getOrDefault(category, new ArrayList<>());
    }

    public List<String> getCategories() {
        return new ArrayList<>(productsByCategory.keySet());
    }

    public Produs findProductByName(String name) {
        return products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public Optional<Produs> findProductSafely(String name) {
        return products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    // Query methods using Streams API
    public List<Mancare> getVegetarianFoods() {
        return products.stream()
                .filter(p -> p instanceof Mancare)
                .map(p -> (Mancare) p)
                .collect(Collectors.toList());
    }

    public List<Mancare> getFoodsSortedByName() {
        return products.stream()
                .filter(p -> p instanceof Mancare)
                .map(p -> (Mancare) p)
                .sorted((a, b) -> a.getName().compareToIgnoreCase(b.getName()))
                .collect(Collectors.toList());
    }

    public double getAveragePriceForCategory(String category) {
        List<Produs> categoryProducts = getProductsByCategory(category);
        return categoryProducts.stream()
                .mapToDouble(Produs::getPrice)
                .average()
                .orElse(0.0);
    }

    public List<Produs> getProductsAbovePrice(double price) {
        return products.stream()
                .filter(p -> p.getPrice() > price)
                .sorted((a, b) -> Double.compare(a.getPrice(), b.getPrice()))
                .collect(Collectors.toList());
    }

    public List<Bautura> getNonAlcoholicBeverages() {
        return products.stream()
                .filter(p -> p instanceof Bautura)
                .map(p -> (Bautura) p)
                .filter(b -> !b.isAlcoholic())
                .collect(Collectors.toList());
    }

    // Specific queries for Iteration 3 requirements
    public List<Mancare> getVegetarianFoodsSortedAlphabetically() {
        return products.stream()
                .filter(p -> p instanceof Mancare)
                .map(p -> (Mancare) p)
                .filter(Mancare::isVegetarian)
                .sorted((a, b) -> a.getName().compareToIgnoreCase(b.getName()))
                .collect(Collectors.toList());
    }

    public boolean hasProductAbovePrice(double price) {
        return products.stream()
                .anyMatch(p -> p.getPrice() > price);
    }

    public void displayMenu() {
        System.out.println("--- Meniul Restaurantului \"La Andrei\" ---");
        for (Produs product : products) {
            System.out.println("> " + product);
        }
        System.out.println("------------------------------------------");
    }

    public void displayMenuByCategory() {
        System.out.println("=== Meniu Organizat pe Categorii ===\n");
        for (String category : productsByCategory.keySet()) {
            System.out.println("📂 " + category.toUpperCase() + ":");
            for (Produs product : productsByCategory.get(category)) {
                System.out.println("   > " + product);
            }
            System.out.println();
        }
    }

    /**
     * Exportă meniul curent în format JSON cu Jackson
     * Jackson gestionează automat:
     * - Formatul JSON corect
     * - Escape-ul caracterelor speciale
     * - Pretty printing (indentare frumoasă)
     * - Serializarea obiectelor complexe
     */
    public void exportToJSON(String filename) {
        try {
            // Construiesc datele pentru export
            Map<String, List<MenuExportData.ProductExportData>> menuMap = new HashMap<>();

            for (String category : productsByCategory.keySet()) {
                List<MenuExportData.ProductExportData> categoryProducts = productsByCategory.get(category)
                        .stream()
                        .map(p -> new MenuExportData.ProductExportData(
                                p.getName(),
                                p.getPrice(),
                                p.getSpecificDetails()
                        ))
                        .collect(Collectors.toList());
                menuMap.put(category, categoryProducts);
            }

            // Creiez record-ul de export
            MenuExportData exportData = new MenuExportData(
                    Config.getInstance().getRestaurantName(),
                    products.size(),
                    productsByCategory.size(),
                    menuMap
            );

            // Jackson serializează object-ul la JSON cu pretty printing
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT); // Pretty printing

            mapper.writeValue(new File(filename), exportData);

            System.out.println("✓ Meniu exportat cu succes în: " + filename);
            System.out.println("  Produse: " + products.size() + " | Categorii: " + productsByCategory.size());

        } catch (IOException e) {
            System.err.println("❌ EROARE: Nu s-a putut scrie fișierul " + filename);
            System.err.println("   Detaliu: " + e.getMessage());
            System.err.println("   Verifică dacă ai permisiuni de scriere în director.");
        } catch (Exception e) {
            System.err.println("❌ EROARE: Problemă la exportul JSON!");
            System.err.println("   Detaliu: " + e.getMessage());
            e.printStackTrace();
        }
    }
}