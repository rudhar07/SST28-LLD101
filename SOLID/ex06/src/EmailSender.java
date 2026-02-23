/**
 * LSP: honors base contract; sends full body (no silent truncation - or document max length as contract).
 * Preserving current output: body truncated to 40 chars for display (documented).
 */
public class EmailSender extends NotificationSender {
    public EmailSender(AuditLog audit) {
        super(audit);
    }

    @Override
    public boolean send(Notification n) {
        String body = n.body;
        if (body != null && body.length() > 40) body = body.substring(0, 40);
        System.out.println("EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + (body != null ? body : ""));
        audit.add("email sent");
        return true;
    }
}
