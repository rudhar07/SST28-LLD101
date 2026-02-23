/**
 * Base for notification senders (audit + send contract).
 */
public abstract class NotificationSender {
    protected final AuditLog audit;

    public NotificationSender(AuditLog audit) {
        this.audit = audit;
    }

    public abstract boolean send(Notification n);
}
