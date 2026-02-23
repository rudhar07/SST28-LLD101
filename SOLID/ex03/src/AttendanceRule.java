import java.util.Optional;

public class AttendanceRule implements EligibilityRule {
    private static final int MIN_ATTENDANCE = 75;

    @Override
    public Optional<String> check(StudentProfile profile) {
        if (profile.attendancePct < MIN_ATTENDANCE) {
            return Optional.of("Attendance " + profile.attendancePct + "% below " + MIN_ATTENDANCE + "%");
        }
        return Optional.empty();
    }
}
