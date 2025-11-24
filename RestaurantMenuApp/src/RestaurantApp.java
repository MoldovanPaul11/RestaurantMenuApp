import java.util.List;

public class RestaurantApp {

    public static void main(String[] args) {
        // ============ ITERAȚIA 4: Configurare și Export ============
        System.out.println("═══ ITERAȚIA 4: Configurare Externă și Export ═══\n");
        demonstrateConfiguration();

        // Inițializare meniu
        Meniu menu = createRestaurantMenu();

        // ============ ITERAȚIA 1: Afișarea meniului ============
        System.out.println("\n\n═══ ITERAȚIA 1: Afișarea Meniului ═══\n");
        menu.displayMenu();

        // ============ ITERAȚIA 2: Sistemul de comenzi ============
        System.out.println("\n\n═══ ITERAȚIA 2: Sistem de Comenzi ═══\n");

        // Scenariul 1: Comandă simplă fără discount
        demonstrateSimpleOrder(menu);

        // Scenariul 2: Comandă cu Happy Hour (reducere băuturi alcoolice)
        demonstrateHappyHourOrder(menu);

        // Scenariul 3: Comandă cu reducere generală (Valentine's Day)
        demonstrateValentinesOrder(menu);

        // Demonstrarea robustețății sistemului
        demonstrateSystemSafety();

        // ============ ITERAȚIA 3: Meniu Complex și Pizza Customizabilă ============
        System.out.println("\n\n═══ ITERAȚIA 3: Meniu Organizat pe Categorii ═══\n");
        demonstrateMenuByCategory(menu);

        System.out.println("\n\n═══ ITERAȚIA 3: Interogări Complexe (Fluent API) ═══\n");
        demonstrateComplexQueries(menu);

        System.out.println("\n\n═══ ITERAȚIA 3: Căutare Sigură în Meniu ═══\n");
        demonstrateSafeSearch(menu);

        System.out.println("\n\n═══ ITERAȚIA 3: Pizza Customizabilă (Builder Pattern) ═══\n");
        demonstratePizzaCustomization(menu);

        // ============ ITERAȚIA 4: Export JSON ============
        System.out.println("\n\n═══ ITERAȚIA 4: Export Meniu în JSON ═══\n");
        demonstrateJSONExport(menu);
    }

    private static Meniu createRestaurantMenu() {
        Meniu menu = new Meniu();

        // Adăugare preparate culinare - Fel Principal
        menu.addProduct(new Mancare("Pizza Margherita", 45.0, 450, true), "Fel Principal");
        menu.addProduct(new Mancare("Paste Carbonara", 52.5, 400, false), "Fel Principal");
        menu.addProduct(new Mancare("Risotto Vegetarian", 48.0, 350, true), "Fel Principal");
        menu.addProduct(new Mancare("Broccoli cu Sos Cheddar", 42.0, 380, true), "Fel Principal");
        menu.addProduct(new Mancare("File de Pește", 60.0, 320, false), "Fel Principal");
        menu.addProduct(new Mancare("Meniu Vegan", 50.0, 400, true), "Fel Principal");
        menu.addProduct(new Mancare("Entrecôte", 75.0, 350, false), "Fel Principal");

        // Adăugare salate - Aperitiv
        menu.addProduct(new Mancare("Salată Caesar", 28.0, 300, false), "Aperitiv");
        menu.addProduct(new Mancare("Salată Grecească", 32.0, 350, true), "Aperitiv");
        menu.addProduct(new Mancare("Bruschetta", 25.0, 250, true), "Aperitiv");
        menu.addProduct(new Mancare("Calamari Prăjiți", 40.0, 280, false), "Aperitiv");

        // Adăugare deserturi - Desert
        menu.addProduct(new Mancare("Tiramisu Premium", 125.0, 250, true), "Desert");
        menu.addProduct(new Mancare("Mousse Ciocolată", 35.0, 200, true), "Desert");
        menu.addProduct(new Mancare("Cheesecake", 45.0, 180, true), "Desert");
        menu.addProduct(new Mancare("Profiterol", 38.0, 220, true), "Desert");
        menu.addProduct(new Mancare("Pannacotta", 42.0, 210, true), "Desert");
        menu.addProduct(new Mancare("Crème Brûlée", 48.0, 190, true), "Desert");
        menu.addProduct(new Mancare("Tort Zmeura", 55.0, 240, true), "Desert");

        // Adăugare băuturi nealcoolice - Băuturi Răcoritoare
        menu.addProduct(new Bautura("Limonada", 15.0, 400, false), "Băuturi Răcoritoare");
        menu.addProduct(new Bautura("Apa Plata", 8.0, 500, false), "Băuturi Răcoritoare");
        menu.addProduct(new Bautura("Coca Cola", 10.0, 330, false), "Băuturi Răcoritoare");
        menu.addProduct(new Bautura("Suc Portocală", 12.0, 250, false), "Băuturi Răcoritoare");
        menu.addProduct(new Bautura("Suc Măr", 12.0, 250, false), "Băuturi Răcoritoare");
        menu.addProduct(new Bautura("Apă Minerală", 9.0, 500, false), "Băuturi Răcoritoare");
        menu.addProduct(new Bautura("Limonadă Zmeură", 16.0, 400, false), "Băuturi Răcoritoare");

        // Adăugare băuturi alcoolice - Băuturi Alcoolice
        menu.addProduct(new Bautura("Bere Ursus", 12.0, 500, true), "Băuturi Alcoolice");
        menu.addProduct(new Bautura("Vin Rosu (pahar)", 18.0, 200, true), "Băuturi Alcoolice");
        menu.addProduct(new Bautura("Whisky", 35.0, 50, true), "Băuturi Alcoolice");
        menu.addProduct(new Bautura("Vin Alb (pahar)", 16.0, 200, true), "Băuturi Alcoolice");
        menu.addProduct(new Bautura("Bere Heineken", 13.0, 500, true), "Băuturi Alcoolice");
        menu.addProduct(new Bautura("Vodka", 38.0, 50, true), "Băuturi Alcoolice");
        menu.addProduct(new Bautura("Rom Bacardi", 35.0, 50, true), "Băuturi Alcoolice");

        return menu;
    }

    private static void demonstrateSimpleOrder(Meniu menu) {
        System.out.println("📋 SCENARIUL 1: Comandă Normală (fără oferte)\n");

        Order order = new Order();
        order.addProduct(menu.findProductByName("Pizza Margherita"), 2);
        order.addProduct(menu.findProductByName("Limonada"), 1);
        order.addProduct(menu.findProductByName("Apa Plata"), 1);

        order.printReceipt();
    }

    private static void demonstrateHappyHourOrder(Meniu menu) {
        System.out.println("🍺 SCENARIUL 2: Comandă în Happy Hour (17:00-19:00)\n");

        Order order = new Order();
        order.addProduct(menu.findProductByName("Paste Carbonara"), 1);
        order.addProduct(menu.findProductByName("Risotto Vegetarian"), 1);
        order.addProduct(menu.findProductByName("Bere Ursus"), 3);
        order.addProduct(menu.findProductByName("Vin Rosu (pahar)"), 2);

        // Aplicăm oferta Happy Hour
        order.setDiscountStrategy(new HappyHour());

        order.printReceipt();
    }


    private static void demonstrateValentinesOrder(Meniu menu) {
        System.out.println("💝 SCENARIUL 3: Ofertă Specială Valentine's Day\n");

        Order order = new Order();
        order.addProduct(menu.findProductByName("Pizza Margherita"), 1);
        order.addProduct(menu.findProductByName("Salată Caesar"), 2);
        order.addProduct(menu.findProductByName("Vin Rosu (pahar)"), 2);
        order.addProduct(menu.findProductByName("Coca Cola"), 1);

        // Aplicăm oferta de Valentine's Day
        order.setDiscountStrategy(new PercentageDiscount(0.10, "Valentine's Day"));

        order.printReceipt();
    }

    private static void demonstrateSystemSafety() {
        System.out.println("🔒 DEMONSTRAȚIE: Robustețea Sistemului\n");
        System.out.println("Sistemul este protejat prin design:");
        System.out.println("✓ Clasa Product este abstractă");
        System.out.println("✓ Doar Food și Drink pot extinde Product");
        System.out.println("✓ Food și Drink sunt declarate 'final' (nu pot fi extinse)");
        System.out.println("✓ Este IMPOSIBIL să creăm produse de alt tip (ex: Decorațiuni)\n");

        System.out.println("Exemplu de cod care NU va compila:");
        System.out.println("  // Product decoration = new Product(\"Lumanare\", 5.0);");
        System.out.println("  // ❌ EROARE: Product este abstract!\n");

        System.out.println("TVA-ul este definit ca CONSTANTĂ:");
        System.out.printf("  Order.VAT_RATE = %.2f (%.0f%%)\n", Order.VAT_RATE, Order.VAT_RATE * 100);
        System.out.println("  ✓ Definit într-un singur loc");
        System.out.println("  ✓ Ușor de modificat dacă se schimbă legea");
        System.out.println("  ✓ Consistent în toată aplicația\n");
    }

    // ============ ITERAȚIA 3 DEMONSTRATIONS ============

    private static void demonstrateMenuByCategory(Meniu menu) {
        menu.displayMenuByCategory();
    }

    private static void demonstrateComplexQueries(Meniu menu) {
        System.out.println("🔍 INTEROGĂRI COMPLEXE FOLOSIND STREAMS API:\n");

        // Query 1: Care sunt toate preparatele vegetariene, sortate în ordine alfabetică?
        System.out.println("❓ INTEROGARE 1: Care sunt toate preparatele vegetariene, sortate alfabetic?");
        System.out.println("─────────────────────────────────────────────────────────────────");
        List<Mancare> vegetarianFoods = menu.getVegetarianFoodsSortedAlphabetically();
        if (vegetarianFoods.isEmpty()) {
            System.out.println("   ℹ️  Nu au fost găsite preparate vegetariene.");
        } else {
            vegetarianFoods.forEach(food ->
                System.out.println("   ✓ " + food.getName() + " - " + food.getPrice() + " RON (Gramaj: " + food.getWeight() + "g)")
            );
        }

        // Query 2: Care este prețul mediu al deserturilor?
        System.out.println("\n❓ INTEROGARE 2: Care este prețul mediu al deserturilor?");
        System.out.println("─────────────────────────────────────────────────────────────────");
        double averageDesertPrice = menu.getAveragePriceForCategory("Desert");
        System.out.printf("   ✓ Prețul mediu al deserturilor: %.2f RON\n", averageDesertPrice);
        System.out.println("\n   Detaliu - Deserturi disponibile:");
        menu.getProductsByCategory("Desert").forEach(desert ->
            System.out.printf("      • %s: %.2f RON%n", desert.getName(), desert.getPrice())
        );

        // Query 3: Avem vreun preparat care costă mai mult de 100 RON?
        System.out.println("\n❓ INTEROGARE 3: Avem vreun preparat care costă mai mult de 100 RON?");
        System.out.println("─────────────────────────────────────────────────────────────────");
        boolean hasExpensive = menu.hasProductAbovePrice(100.0);
        if (hasExpensive) {
            System.out.println("   ✓ DA! Avem preparate mai scumpe de 100 RON:");
            menu.getProductsAbovePrice(100.0).forEach(product ->
                System.out.printf("      • %s: %.2f RON%n", product.getName(), product.getPrice())
            );
        } else {
            System.out.println("   ✗ NU! Nu avem preparate mai scumpe de 100 RON.");
        }
    }

    private static void demonstrateSafeSearch(Meniu menu) {
        System.out.println("🔐 CĂUTARE SIGURĂ ÎN MENIU (Cu Optional):\n");

        // Căutare produsă care există
        String searchName1 = "Pizza Margherita";
        System.out.println("Căutând: \"" + searchName1 + "\"");
        menu.findProductSafely(searchName1)
            .ifPresentOrElse(
                product -> System.out.println("✓ GĂSIT: " + product),
                () -> System.out.println("✗ NEGĂSIT - Produsul nu existe în meniu")
            );

        // Căutare produsă care nu există
        System.out.println();
        String searchName2 = "Supa de Pește";
        System.out.println("Căutând: \"" + searchName2 + "\"");
        menu.findProductSafely(searchName2)
            .ifPresentOrElse(
                product -> System.out.println("✓ GĂSIT: " + product),
                () -> System.out.println("✗ NEGĂSIT - Produsul nu există în meniu")
            );

        // Alte exemple de căutare
        System.out.println();
        String searchName3 = "bere ursus"; // Case-insensitive
        System.out.println("Căutând: \"" + searchName3 + "\"");
        menu.findProductSafely(searchName3)
            .ifPresentOrElse(
                product -> System.out.println("✓ GĂSIT: " + product),
                () -> System.out.println("✗ NEGĂSIT - Produsul nu există în meniu")
            );
    }

    private static void demonstratePizzaCustomization(Meniu menu) {
        System.out.println("🍕 PIZZA CUSTOMIZABILĂ FOLOSIND BUILDER PATTERN:\n");

        // Exemplul 1: Pizza simplă (doar ingredientele obligatorii)
        System.out.println("Exemplul 1: Pizza Simplă");
        Pizza simplePizza = new Pizza.PizzaBuilder("Pizza Simplă", 30.0, "Blat Subțire", "Sos Roșu")
                .build();
        System.out.println("✓ Comandă: 1x " + simplePizza.getName());
        System.out.println("  Detalii: " + simplePizza.getSpecificDetails());
        System.out.println("  Preț: " + simplePizza.getPrice() + " RON\n");

        // Exemplul 2: Pizza cu toppinguri multiple
        System.out.println("Exemplul 2: Pizza Deluxe");
        Pizza deluxePizza = new Pizza.PizzaBuilder("Pizza Deluxe", 55.0, "Blat Gros", "Sos Alb")
                .addTopping("Mozzarella")
                .addTopping("Sunca")
                .addTopping("Ciuperci")
                .addTopping("Masline Negre")
                .addTopping("Brânză Parmezană")
                .build();
        System.out.println("✓ Comandă: 1x " + deluxePizza.getName());
        System.out.println("  Detalii: " + deluxePizza.getSpecificDetails());
        System.out.println("  Preț: " + deluxePizza.getPrice() + " RON\n");

        // Exemplul 3: Pizza cu toppinguri din array
        System.out.println("Exemplul 3: Pizza Vegetariană");
        Pizza vegetarianPizza = new Pizza.PizzaBuilder("Pizza Vegetariană", 40.0, "Blat Integral", "Sos Pesto")
                .addToppings("Roșii Cherry", "Busuioc", "Rucola", "Piersică")
                .build();
        System.out.println("✓ Comandă: 1x " + vegetarianPizza.getName());
        System.out.println("  Detalii: " + vegetarianPizza.getSpecificDetails());
        System.out.println("  Preț: " + vegetarianPizza.getPrice() + " RON\n");

        // Exemplul 4: Comandă cu pizza customizată
        System.out.println("Exemplul 4: Comandă Completă cu Pizza Customizată");
        Order customOrder = new Order();
        customOrder.addProduct(deluxePizza, 1);
        customOrder.addProduct(menu.findProductByName("Limonada"), 2);
        customOrder.setDiscountStrategy(new PercentageDiscount(0.05, "Promo Pizza 5%"));
        customOrder.printReceipt();
    }

    // ============ ITERAȚIA 4 DEMONSTRATIONS ============

    private static void demonstrateConfiguration() {
        System.out.println("📋 CONFIGURARE EXTERNĂ DIN JSON:\n");
        Config config = Config.getInstance();
        System.out.println("   Restaurant: " + config.getRestaurantName());
        System.out.println("   Adresă: " + config.getRestaurantAddress());
        System.out.println("   Telefon: " + config.getRestaurantPhone());
        System.out.printf("   TVA: %.0f%%\n", config.getVatRate() * 100);
    }

    private static void demonstrateJSONExport(Meniu menu) {
        System.out.println("📤 EXPORT MENIU ÎN FORMAT JSON:\n");
        System.out.println("   Exportând meniu la: meniu_export.json");
        menu.exportToJSON("meniu_export.json");
        System.out.println("\n   Fișierul JSON conține:");
        System.out.println("   • Informații restaurant");
        System.out.println("   • Toate categoriile");
        System.out.println("   • Toate produsele cu detalii");
        System.out.println("   • Preturi și descrieri");
    }
}
