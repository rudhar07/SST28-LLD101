/**
 * Persists eligibility result (DIP).
 */
public interface EligibilityStore {
    void save(String rollNo, String result);
}
