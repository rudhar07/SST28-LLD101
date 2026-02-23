import java.util.HashMap;
import java.util.Map;

/**
 * In-memory file store for invoices (implements InvoiceStore).
 */
public class FileStore implements InvoiceStore {
    private final Map<String, String> files = new HashMap<>();

    @Override
    public void save(String id, String content) {
        files.put(id, content);
    }

    @Override
    public int countLines(String id) {
        String c = files.get(id);
        return c == null ? 0 : c.split("\n").length;
    }
}
