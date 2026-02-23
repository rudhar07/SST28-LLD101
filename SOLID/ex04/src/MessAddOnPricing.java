public class MessAddOnPricing implements AddOnPricing {
    @Override
    public boolean appliesTo(AddOn addOn) {
        return addOn == AddOn.MESS;
    }

    @Override
    public double getMonthlyAmount() {
        return 1000.0;
    }
}
