import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Clasa Config gestionează citirea configurației din fișierul JSON cu Jackson.
 * Implementează gestionarea robustă a erorilor și Singleton pattern.
 *
 * Jackson - bibliotecă profesională pentru JSON:
 * - Deserializare automată JSON → Object
 * - Suportă record-uri (Java 16+)
 * - Gestionare automată a null values
 * - Performanță ridicată
 */
public class Config {
    private static Config instance;
    private ConfigData configData;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private Config() {
        loadConfig();
    }

    /**
     * Singleton pattern - returnează o instanță unică de Config
     */
    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    /**
     * Încarcă configurația din fișierul config.json cu Jackson
     * Gestionează FileNotFoundException și IOException
     */
    private void loadConfig() {
        try {
            // Citire fișier
            File configFile = new File("config.json");

            if (!configFile.exists()) {
                throw new FileNotFoundException("config.json nu a fost găsit în directorul curent");
            }

            // Deserializare JSON → ConfigData record cu Jackson
            configData = objectMapper.readValue(configFile, ConfigData.class);

            System.out.println("✓ Configurație încărcată cu succes din config.json");
            System.out.println("  Restaurant: " + configData.restaurant().name());
            System.out.println("  VAT: " + (configData.tax().vat_rate() * 100) + "%");

        } catch (FileNotFoundException e) {
            System.err.println("❌ EROARE: Fișierul config.json nu a fost găsit!");
            System.err.println("   Locație așteptată: config.json (în directorul curent)");
            System.err.println("   Detaliu: " + e.getMessage());
            loadDefaultConfig();

        } catch (JsonProcessingException e) {
            System.err.println("❌ EROARE: Format JSON invalid în config.json!");
            System.err.println("   Detaliu: " + e.getMessage());
            loadDefaultConfig();

        } catch (IOException e) {
            System.err.println("❌ EROARE: Problemă la citirea fișierului config.json!");
            System.err.println("   Detaliu: " + e.getMessage());
            loadDefaultConfig();
        }
    }

    /**
     * Încarcă configurația implicită în caz de eroare
     */
    private void loadDefaultConfig() {
        System.out.println("⚠️  Se folosește configurația implicită...");
        configData = new ConfigData(
                new ConfigData.RestaurantInfo(
                        "La Andrei",
                        "Strada Principale, Nr. 42, Bucuresti",
                        "0212-345-6789",
                        "contact@la-andrei.ro",
                        "RON"
                ),
                new ConfigData.TaxInfo(
                        0.09,
                        "TVA 9%"
                ),
                new ConfigData.SettingsInfo(
                        10,
                        0.0,
                        5.0
                )
        );
    }

    // Getter-i pentru accesul la date
    public String getRestaurantName() {
        return configData.restaurant().name();
    }

    public String getRestaurantAddress() {
        return configData.restaurant().address();
    }

    public String getRestaurantPhone() {
        return configData.restaurant().phone();
    }

    public String getRestaurantEmail() {
        return configData.restaurant().email();
    }

    public String getCurrency() {
        return configData.restaurant().currency();
    }

    public double getVatRate() {
        return configData.tax().vat_rate();
    }

    public String getVatDescription() {
        return configData.tax().vat_description();
    }

    public int getMaxToppings() {
        return configData.settings().max_toppings();
    }

    public double getMinOrderValue() {
        return configData.settings().min_order_value();
    }

    public double getDeliveryFee() {
        return configData.settings().delivery_fee();
    }

    public ConfigData getConfigData() {
        return configData;
    }
}

