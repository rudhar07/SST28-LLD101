/**
 * LSP: honors base contract; subject is optional per base (SMS uses body only).
 */
public class SmsSender extends NotificationSender {
    public SmsSender(AuditLog audit) {
        super(audit);
    }

    @Override
    public boolean send(Notification n) {
        System.out.println("SMS -> to=" + n.phone + " body=" + n.body);
        audit.add("sms sent");
        return true;
    }
}
