public class JsonExporter extends Exporter {
    @Override
    public ExportResult export(ExportRequest request) {
        String json = "{\"title\":\"" + escape(request.title) + "\",\"body\":\"" + escape(request.body) + "\"}";
        return ExportResult.ok("application/json", json.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n");
    }
}
