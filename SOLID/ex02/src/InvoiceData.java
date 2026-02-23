import java.util.List;

public class InvoiceData {
    public final List<String> lineLines;
    public final double subtotal;

    public InvoiceData(List<String> lineLines, double subtotal) {
        this.lineLines = lineLines;
        this.subtotal = subtotal;
    }
}
