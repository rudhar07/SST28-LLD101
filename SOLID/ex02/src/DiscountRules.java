/**
 * Simple discount: 10% off when 3+ items.
 */
public class DiscountRules implements DiscountPolicy {
    @Override
    public double discountAmount(String itemId, double subtotal, int totalItems) {
        if (totalItems >= 3) return subtotal * 0.10;
        return 0.0;
    }
}
