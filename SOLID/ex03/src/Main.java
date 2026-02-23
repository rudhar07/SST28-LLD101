public class Main {
    public static void main(String[] args) {
        System.out.println("=== Eligibility ===");
        FakeEligibilityStore store = new FakeEligibilityStore();
        EligibilityEngine engine = new EligibilityEngine(store);
        engine.addRule(new AttendanceRule());
        engine.addRule(new CgrRule());
        engine.addRule(new CreditsRule());
        engine.addRule(new DisciplinaryRule());
        StudentProfile p = new StudentProfile("R001", "Riya", 7.5, 80, 130, 0);
        engine.runAndPrint(p);
    }
}
