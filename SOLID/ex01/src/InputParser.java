import java.util.HashMap;
import java.util.Map;

/**
 * Single responsibility: parse raw input string into RawStudentData.
 */
public class InputParser {
    public RawStudentData parse(String raw) {
        Map<String, String> map = new HashMap<>();
        if (raw != null && !raw.isBlank()) {
            for (String pair : raw.split(";")) {
                String[] kv = pair.split("=", 2);
                if (kv.length == 2) {
                    map.put(kv[0].trim().toLowerCase(), kv[1].trim());
                }
            }
        }
        return new RawStudentData(
                map.getOrDefault("name", ""),
                map.getOrDefault("email", ""),
                map.getOrDefault("phone", ""),
                map.getOrDefault("program", "")
        );
    }
}
