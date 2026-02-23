public class SingleRoomPricing implements RoomTypePricing {
    @Override
    public boolean matches(int roomType) {
        return roomType == LegacyRoomTypes.SINGLE;
    }

    @Override
    public double getMonthlyAmount() {
        return 14000.0;
    }
}
