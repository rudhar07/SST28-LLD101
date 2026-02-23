import java.util.List;

/**
 * Single responsibility: print onboarding feedback (input echo, errors, confirmation).
 */
public class ConfirmationPrinter {
    public void printInput(String raw) {
        System.out.println("Input: " + raw);
    }

    public void printErrors(List<String> errors) {
        System.out.println("Validation errors:");
        for (String e : errors) {
            System.out.println("  - " + e);
        }
    }

    public void printConfirmation(StudentRecord rec, int totalCount) {
        System.out.println("Registered: " + rec.id + " | Total: " + totalCount);
    }
}
