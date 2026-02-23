/**
 * Writes evaluation report string.
 */
public class ReportWriterImpl implements ReportWriter {
    @Override
    public String write(Submission s, int plag, int code) {
        return "Roll: " + s.roll + " | Plag: " + plag + " | Code: " + code;
    }
}
