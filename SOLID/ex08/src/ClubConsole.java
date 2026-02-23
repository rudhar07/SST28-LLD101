import java.util.Scanner;

/**
 * Simple console that wires treasurer, minutes, and event lead tools; run() drives the menu.
 */
public class ClubConsole {
    private final BudgetLedger ledger;
    private final MinutesBook minutes;
    private final EventPlanner events;

    public ClubConsole(BudgetLedger ledger, MinutesBook minutes, EventPlanner events) {
        this.ledger = ledger;
        this.minutes = minutes;
        this.events = events;
    }

    public void run() {
        TreasurerTool treasurer = new TreasurerTool(ledger);
        MinutesOps minutesOps = minutes;
        EventLeadTool eventLead = new EventLeadTool(events);
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Commands: income <amt> <note> | expense <amt> <note> | minutes <text> | event <name> <budget> | quit");
            while (true) {
                String line = sc.nextLine();
                if (line == null || line.trim().equalsIgnoreCase("quit")) break;
                String trimmed = line.trim();
                int firstSpace = trimmed.indexOf(' ');
                String cmd = firstSpace > 0 ? trimmed.substring(0, firstSpace) : trimmed;
                String rest = firstSpace > 0 ? trimmed.substring(firstSpace + 1).trim() : "";
                String[] restParts = rest.split("\\s+", 2);
                switch (cmd.toLowerCase()) {
                    case "income":
                        try {
                            double amt = restParts.length > 0 ? Double.parseDouble(restParts[0]) : 0;
                            treasurer.addIncome(amt, restParts.length > 1 ? restParts[1] : "");
                        } catch (NumberFormatException ignored) {}
                        break;
                    case "expense":
                        try {
                            double amt = restParts.length > 0 ? Double.parseDouble(restParts[0]) : 0;
                            treasurer.addExpense(amt, restParts.length > 1 ? restParts[1] : "");
                        } catch (NumberFormatException ignored) {}
                        break;
                    case "minutes":
                        minutesOps.addMinutes(rest);
                        break;
                    case "event":
                        try {
                            String name = restParts.length > 0 ? restParts[0] : "";
                            double budget = restParts.length > 1 ? Double.parseDouble(restParts[1]) : 0;
                            eventLead.createEvent(name, budget);
                        } catch (NumberFormatException ignored) {}
                        break;
                    default:
                        break;
                }
            }
        }
        System.out.println("Bye. Ledger balance: " + ledger.balanceInt() + " | Events: " + events.count());
    }
}
