public class LaundryAddOnPricing implements AddOnPricing {
    @Override
    public boolean appliesTo(AddOn addOn) {
        return addOn == AddOn.LAUNDRY;
    }

    @Override
    public double getMonthlyAmount() {
        return 500.0;
    }
}
