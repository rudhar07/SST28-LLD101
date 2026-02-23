/**
 * Simple tax rules: default 5%, some items 0%.
 */
public class TaxRules implements TaxPolicy {
    @Override
    public double taxPercent(String itemId) {
        if (itemId != null && (itemId.startsWith("F") || itemId.equals("water"))) return 0.0;
        return 5.0;
    }
}
