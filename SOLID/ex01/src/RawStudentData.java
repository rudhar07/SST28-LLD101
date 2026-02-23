/**
 * DTO for parsed raw input (name=...;email=...; etc.).
 */
public class RawStudentData {
    public final String name;
    public final String email;
    public final String phone;
    public final String program;

    public RawStudentData(String name, String email, String phone, String program) {
        this.name = name != null ? name.trim() : "";
        this.email = email != null ? email.trim() : "";
        this.phone = phone != null ? phone.trim() : "";
        this.program = program != null ? program.trim() : "";
    }
}
