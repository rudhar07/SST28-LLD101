public class DoubleRoomPricing implements RoomTypePricing {
    @Override
    public boolean matches(int roomType) {
        return roomType == LegacyRoomTypes.DOUBLE;
    }

    @Override
    public double getMonthlyAmount() {
        return 15000.0;
    }
}
