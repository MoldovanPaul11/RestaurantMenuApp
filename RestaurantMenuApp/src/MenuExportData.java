import java.util.List;
import java.util.Map;


public record MenuExportData(
        String restaurant,
        long total_products,
        int categories,
        Map<String, List<ProductExportData>> menu
) {

    public record ProductExportData(
            String name,
            double price,
            String details
    ) {}
}

