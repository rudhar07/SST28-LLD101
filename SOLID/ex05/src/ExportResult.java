/**
 * Result of an export: content type, bytes, success/error.
 */
public class ExportResult {
    public final String contentType;
    public final byte[] bytes;
    public final boolean success;
    public final String errorMessage;

    private ExportResult(String contentType, byte[] bytes, boolean success, String errorMessage) {
        this.contentType = contentType;
        this.bytes = bytes != null ? bytes : new byte[0];
        this.success = success;
        this.errorMessage = errorMessage != null ? errorMessage : "";
    }

    public static ExportResult ok(String contentType, byte[] bytes) {
        return new ExportResult(contentType, bytes, true, null);
    }

    public static ExportResult error(String errorMessage) {
        return new ExportResult(null, null, false, errorMessage);
    }
}
