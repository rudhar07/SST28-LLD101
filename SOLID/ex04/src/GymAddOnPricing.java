public class GymAddOnPricing implements AddOnPricing {
    @Override
    public boolean appliesTo(AddOn addOn) {
        return addOn == AddOn.GYM;
    }

    @Override
    public double getMonthlyAmount() {
        return 800.0;
    }
}
