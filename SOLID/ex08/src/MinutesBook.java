import java.util.ArrayList;
import java.util.List;

/**
 * Holds meeting minutes; implements MinutesOps.
 */
public class MinutesBook implements MinutesOps {
    private final List<String> entries = new ArrayList<>();

    @Override
    public void addMinutes(String text) {
        entries.add(text);
        System.out.println("Minutes: " + text);
    }

    public int count() {
        return entries.size();
    }
}
