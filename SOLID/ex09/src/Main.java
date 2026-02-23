public class Main {
    public static void main(String[] args) {
        System.out.println("=== Evaluation ===");
        PlagiarismChecker plag = new PlagiarismCheckerImpl();
        CodeGrader grader = new CodeGraderImpl();
        ReportWriter writer = new ReportWriterImpl();
        Rubric rubric = new Rubric();
        EvaluationPipeline pipeline = new EvaluationPipeline(plag, grader, writer, rubric);
        Submission sub = new Submission("R01", "public class Foo {}", "Foo.java");
        pipeline.evaluate(sub);
    }
}
