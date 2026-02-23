public class CsvExporter extends Exporter {
    @Override
    public ExportResult export(ExportRequest request) {
        String line = "\"" + request.title.replace("\"", "\"\"") + "\",\"" + request.body.replace("\"", "\"\"") + "\"";
        return ExportResult.ok("text/csv", line.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }
}
