/**
 * Legacy disciplinary flags (int codes).
 */
public final class LegacyFlags {
    public static final int NONE = 0;
    public static final int WARNING = 1;
    public static final int SUSPENDED = 2;

    private LegacyFlags() {}

    public static String nameOf(int flag) {
        switch (flag) {
            case NONE: return "None";
            case WARNING: return "Warning";
            case SUSPENDED: return "Suspended";
            default: return "Unknown";
        }
    }
}
