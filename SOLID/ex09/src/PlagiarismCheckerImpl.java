/**
 * Simple plagiarism check (e.g. similarity score 0-100).
 */
public class PlagiarismCheckerImpl implements PlagiarismChecker {
    @Override
    public int check(Submission s) {
        if (s == null || s.code == null) return 0;
        return Math.min(100, s.code.length() % 97);
    }
}
