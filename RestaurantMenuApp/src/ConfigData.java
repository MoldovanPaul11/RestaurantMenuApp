public record ConfigData(
        RestaurantInfo restaurant,
        TaxInfo tax,
        SettingsInfo settings
) {

    public record RestaurantInfo(
            String name,
            String address,
            String phone,
            String email,
            String currency
    ) {}


    public record TaxInfo(
            double vat_rate,
            String vat_description
    ) {}


    public record SettingsInfo(
            int max_toppings,
            double min_order_value,
            double delivery_fee
    ) {}
}

