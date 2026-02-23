import java.util.List;

/**
 * Orchestrates onboarding: parse -> validate -> persist -> confirm.
 * Depends on abstractions (parser, validator, repository, printer); no direct formatting or DB.
 */
public class OnboardingService {
    private final InputParser parser;
    private final StudentValidator validator;
    private final StudentRepository repository;
    private final ConfirmationPrinter printer;

    public OnboardingService(InputParser parser, StudentValidator validator,
                             StudentRepository repository, ConfirmationPrinter printer) {
        this.parser = parser;
        this.validator = validator;
        this.repository = repository;
        this.printer = printer;
    }

    public void registerFromRawInput(String raw) {
        printer.printInput(raw);
        RawStudentData data = parser.parse(raw);
        List<String> errors = validator.validate(data);
        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }
        String id = IdUtil.nextStudentId(repository.count());
        StudentRecord rec = new StudentRecord(id, data.name, data.email, data.phone, data.program);
        repository.save(rec);
        printer.printConfirmation(rec, repository.count());
    }
}
