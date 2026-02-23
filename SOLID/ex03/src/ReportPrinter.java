import java.util.List;

/**
 * Single responsibility: print eligibility report.
 */
public class ReportPrinter {
    public void print(StudentProfile profile, EligibilityEngineResult result) {
        System.out.println("Roll: " + profile.rollNo + " | " + profile.name);
        System.out.println("Status: " + result.status);
        for (String r : result.reasons) System.out.println("  - " + r);
    }
}
