import java.util.List;

/**
 * Renders repository records as a simple text table.
 */
public final class TextTable {
    private TextTable() {}

    public static String render3(StudentRepository repo) {
        List<StudentRecord> all = repo.all();
        if (all.isEmpty()) {
            return "(no records)";
        }
        StringBuilder sb = new StringBuilder();
        String header = String.format("%-6s %-12s %-20s %-12s %-6s%n", "ID", "Name", "Email", "Phone", "Program");
        sb.append(header);
        for (StudentRecord r : all) {
            sb.append(String.format("%-6s %-12s %-20s %-12s %-6s%n",
                    r.id, r.name, r.email, r.phone, r.program));
        }
        return sb.toString();
    }
}
