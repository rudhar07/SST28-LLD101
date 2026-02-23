import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Export Demo ===");
        ExportRequest req = new ExportRequest("Report", "Hello world");
        CsvExporter csv = new CsvExporter();
        System.out.println(formatResult(csv.export(req)));
        JsonExporter json = new JsonExporter();
        System.out.println(formatResult(json.export(req)));
        PdfExporter pdf = new PdfExporter();
        System.out.println(formatResult(pdf.export(new ExportRequest("Big", SampleData.longBody()))));
    }

    private static String formatResult(ExportResult r) {
        if (r.success) return r.contentType + " (" + r.bytes.length + " bytes)";
        return "Error: " + r.errorMessage;
    }
}
