/**
 * Utility for generating next student ID (e.g. STU001, STU002, ...).
 */
public final class IdUtil {
    private IdUtil() {}

    public static String nextStudentId(int currentCount) {
        return String.format("STU%03d", currentCount + 1);
    }
}
