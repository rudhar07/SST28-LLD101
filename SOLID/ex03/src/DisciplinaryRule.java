import java.util.Optional;

public class DisciplinaryRule implements EligibilityRule {
    @Override
    public Optional<String> check(StudentProfile profile) {
        if (profile.disciplinaryFlag == 2) { // SUSPENDED
            return Optional.of("Disciplinary status: " + LegacyFlags.nameOf(profile.disciplinaryFlag));
        }
        return Optional.empty();
    }
}
