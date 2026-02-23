/**
 * OCP: one export format = one class.
 */
public abstract class Exporter {
    public abstract ExportResult export(ExportRequest request);
}
