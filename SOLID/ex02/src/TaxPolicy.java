/**
 * Strategy for tax: percent by item/category (OCP-friendly).
 */
public interface TaxPolicy {
    double taxPercent(String itemId);
}
