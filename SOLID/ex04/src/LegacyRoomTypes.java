/**
 * Legacy room type constants (int codes); nameOf for display.
 */
public final class LegacyRoomTypes {
    public static final int SINGLE = 1;
    public static final int DOUBLE = 2;
    public static final int DELUXE = 3;
    public static final int TRIPLE = 4;

    private LegacyRoomTypes() {}

    public static String nameOf(int roomType) {
        switch (roomType) {
            case SINGLE: return "Single";
            case DOUBLE: return "Double";
            case DELUXE: return "Deluxe";
            case TRIPLE: return "Triple";
            default: return "Unknown";
        }
    }
}
