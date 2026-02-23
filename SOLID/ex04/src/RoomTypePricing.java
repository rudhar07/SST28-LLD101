/**
 * OCP: one room type's base monthly price; new room types add new classes.
 */
public interface RoomTypePricing {
    boolean matches(int roomType);
    double getMonthlyAmount();
}
