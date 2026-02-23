public class PdfExporter extends Exporter {
    private static final int MAX_BODY_LENGTH = 500;

    @Override
    public ExportResult export(ExportRequest request) {
        if (request.body != null && request.body.length() > MAX_BODY_LENGTH) {
            return ExportResult.error("Body too long for PDF (max " + MAX_BODY_LENGTH + ")");
        }
        String content = "[PDF] " + request.title + "\n" + (request.body != null ? request.body : "");
        return ExportResult.ok("application/pdf", content.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }
}
