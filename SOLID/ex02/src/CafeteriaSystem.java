import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Orchestrates cafeteria: menu, checkout using TaxPolicy, DiscountPolicy, OrderCalculator, formatter, store (OCP/DIP).
 */
public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final OrderCalculator orderCalculator = new OrderCalculator();
    private final TaxPolicy taxPolicy;
    private final DiscountPolicy discountPolicy;
    private final InvoiceFormatter formatter;
    private final InvoiceStore store;
    private int invoiceSeq = 0;

    public CafeteriaSystem(TaxPolicy taxPolicy, DiscountPolicy discountPolicy,
                           InvoiceFormatter formatter, InvoiceStore store) {
        this.taxPolicy = taxPolicy;
        this.discountPolicy = discountPolicy;
        this.formatter = formatter;
        this.store = store;
    }

    public void addToMenu(MenuItem item) {
        menu.put(item.id, item);
    }

    public void checkout(String customerId, List<OrderLine> order) {
        InvoiceData data = orderCalculator.calculate(menu, order);
        double taxPct = 0.0;
        for (OrderLine line : order) {
            MenuItem item = menu.get(line.itemId);
            if (item != null) taxPct = Math.max(taxPct, taxPolicy.taxPercent(line.itemId));
        }
        double taxAmt = data.subtotal * (taxPct / 100.0);
        int totalItems = order.stream().mapToInt(o -> o.qty).sum();
        double discountAmt = discountPolicy.discountAmount("", data.subtotal, totalItems);
        double total = data.subtotal + taxAmt - discountAmt;
        String invoiceId = "INV-" + (++invoiceSeq);
        String content = formatter.format(invoiceId, data.lineLines, data.subtotal, taxPct, taxAmt, discountAmt, total);
        store.save(invoiceId, content);
        System.out.println(content);
    }
}
