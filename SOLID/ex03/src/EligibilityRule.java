import java.util.Optional;

/**
 * OCP: one rule returns Optional error message if ineligible.
 */
public interface EligibilityRule {
    Optional<String> check(StudentProfile profile);
}
