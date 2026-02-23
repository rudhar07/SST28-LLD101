/**
 * DTO for a notification (email/subject/body or phone/body).
 */
public class Notification {
    public final String email;
    public final String phone;
    public final String subject;
    public final String body;

    public Notification(String email, String phone, String subject, String body) {
        this.email = email != null ? email : "";
        this.phone = phone != null ? phone : "";
        this.subject = subject != null ? subject : "";
        this.body = body != null ? body : "";
    }
}
