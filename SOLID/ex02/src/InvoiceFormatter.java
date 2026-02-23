import java.util.List;

/**
 * Formats invoice as text (single responsibility).
 */
public class InvoiceFormatter {
    public String format(String invoiceId, List<String> lineLines, double subtotal,
                         double taxPercent, double taxAmt, double discountAmt, double total) {
        StringBuilder sb = new StringBuilder();
        sb.append("Invoice ").append(invoiceId).append("\n");
        for (String line : lineLines) sb.append("  ").append(line).append("\n");
        sb.append("Subtotal: ").append(String.format("%.2f", subtotal)).append("\n");
        sb.append("Tax (").append(String.format("%.1f", taxPercent)).append("%): ").append(String.format("%.2f", taxAmt)).append("\n");
        sb.append("Discount: ").append(String.format("%.2f", discountAmt)).append("\n");
        sb.append("TOTAL: ").append(String.format("%.2f", total)).append("\n");
        return sb.toString();
    }
}
