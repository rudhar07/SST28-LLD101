public class DeluxeRoomPricing implements RoomTypePricing {
    @Override
    public boolean matches(int roomType) {
        return roomType == LegacyRoomTypes.DELUXE;
    }

    @Override
    public double getMonthlyAmount() {
        return 16000.0;
    }
}
