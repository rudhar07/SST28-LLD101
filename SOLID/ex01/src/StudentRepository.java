import java.util.List;

/**
 * Abstraction for persisting student records (DIP-friendly).
 */
public interface StudentRepository {
    void save(StudentRecord r);
    int count();
    List<StudentRecord> all();
}
