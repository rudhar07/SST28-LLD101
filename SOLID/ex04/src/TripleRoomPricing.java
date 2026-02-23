public class TripleRoomPricing implements RoomTypePricing {
    @Override
    public boolean matches(int roomType) {
        return roomType == LegacyRoomTypes.TRIPLE;
    }

    @Override
    public double getMonthlyAmount() {
        return 12000.0;
    }
}
