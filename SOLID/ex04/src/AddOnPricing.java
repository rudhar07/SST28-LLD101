/**
 * OCP: one add-on's monthly price; new add-ons add new classes.
 */
public interface AddOnPricing {
    boolean appliesTo(AddOn addOn);
    double getMonthlyAmount();
}
