/**
 * Orchestrates: plagiarism check -> grade -> report (DIP: depends on abstractions).
 */
public class EvaluationPipeline {
    private final PlagiarismChecker plagiarismChecker;
    private final CodeGrader codeGrader;
    private final ReportWriter reportWriter;
    private final Rubric rubric;

    public EvaluationPipeline(PlagiarismChecker plagiarismChecker, CodeGrader codeGrader,
                              ReportWriter reportWriter, Rubric rubric) {
        this.plagiarismChecker = plagiarismChecker;
        this.codeGrader = codeGrader;
        this.reportWriter = reportWriter;
        this.rubric = rubric;
    }

    public void evaluate(Submission submission) {
        int plag = plagiarismChecker.check(submission);
        int code = codeGrader.grade(submission, rubric);
        String report = reportWriter.write(submission, plag, code);
        System.out.println(report);
    }
}
