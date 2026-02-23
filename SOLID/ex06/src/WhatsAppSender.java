/**
 * Sends notification via WhatsApp (body/phone); LSP-compatible with NotificationSender.
 */
public class WhatsAppSender extends NotificationSender {
    public WhatsAppSender(AuditLog audit) {
        super(audit);
    }

    @Override
    public boolean send(Notification n) {
        System.out.println("WHATSAPP -> to=" + n.phone + " body=" + n.body);
        audit.add("whatsapp sent");
        return true;
    }
}
