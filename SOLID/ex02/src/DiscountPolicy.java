/**
 * Strategy for discount (OCP-friendly).
 */
public interface DiscountPolicy {
    double discountAmount(String itemId, double subtotal, int totalItems);
}
