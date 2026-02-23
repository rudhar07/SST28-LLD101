import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Cafeteria ===");
        CafeteriaSystem sys = new CafeteriaSystem(
                new TaxRules(),
                new DiscountRules(),
                new InvoiceFormatter(),
                new FileStore()
        );
        sys.addToMenu(new MenuItem("F1", "Fruit salad", 80.0));
        sys.addToMenu(new MenuItem("C1", "Coffee", 120.0));
        sys.addToMenu(new MenuItem("W1", "Water", 20.0));
        List<OrderLine> order = Arrays.asList(
                new OrderLine("F1", 1),
                new OrderLine("C1", 2),
                new OrderLine("W1", 1)
        );
        sys.checkout("C001", order);
    }
}
