import java.util.HashMap;
import java.util.Map;

public class FakeEligibilityStore implements EligibilityStore {
    private final Map<String, String> saved = new HashMap<>();

    @Override
    public void save(String rollNo, String result) {
        saved.put(rollNo, result);
    }
}
