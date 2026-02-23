import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Calculates order: line lines + subtotal (no tax/discount here).
 */
public class OrderCalculator {
    public InvoiceData calculate(Map<String, MenuItem> menu, List<OrderLine> order) {
        double subtotal = 0.0;
        List<String> lines = new ArrayList<>();
        for (OrderLine line : order) {
            MenuItem item = menu.get(line.itemId);
            if (item != null) {
                double lineTotal = item.price * line.qty;
                subtotal += lineTotal;
                lines.add(item.name + " x " + line.qty + " = " + String.format("%.2f", lineTotal));
            }
        }
        return new InvoiceData(lines, subtotal);
    }
}
