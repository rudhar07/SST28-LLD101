import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Runs all eligibility rules (OCP), persists via store, no direct print.
 */
public class EligibilityEngine {
    private final List<EligibilityRule> rules = new ArrayList<>();
    private final EligibilityStore store;

    public EligibilityEngine(EligibilityStore store) {
        this.store = store;
    }

    public void addRule(EligibilityRule rule) {
        rules.add(rule);
    }

    public EligibilityEngineResult evaluate(StudentProfile profile) {
        List<String> reasons = new ArrayList<>();
        for (EligibilityRule rule : rules) {
            Optional<String> err = rule.check(profile);
            err.ifPresent(reasons::add);
        }
        String status = reasons.isEmpty() ? "ELIGIBLE" : "NOT_ELIGIBLE";
        EligibilityEngineResult result = new EligibilityEngineResult(status, reasons);
        store.save(profile.rollNo, status);
        return result;
    }

    public void runAndPrint(StudentProfile profile) {
        EligibilityEngineResult result = evaluate(profile);
        new ReportPrinter().print(profile, result);
    }
}
