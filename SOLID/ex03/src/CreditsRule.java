import java.util.Optional;

public class CreditsRule implements EligibilityRule {
    private static final int MIN_CREDITS = 120;

    @Override
    public Optional<String> check(StudentProfile profile) {
        if (profile.earnedCredits < MIN_CREDITS) {
            return Optional.of("Credits " + profile.earnedCredits + " below " + MIN_CREDITS);
        }
        return Optional.empty();
    }
}
