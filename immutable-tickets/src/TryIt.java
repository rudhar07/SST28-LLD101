import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * Starter demo that shows why mutability is risky.
 *
 * After refactor:
 * - direct mutation should not compile (no setters)
 * - external modifications to tags should not affect the ticket
 * - service "updates" should return a NEW ticket instance
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket t1 = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t1);

        // Demonstrate “updates” via new instances from service
        IncidentTicket t2 = service.assign(t1, "agent@example.com");
        IncidentTicket t3 = service.escalateToCritical(t2);
        System.out.println("\nAfter service updates (new instance): " + t3);

        // Demonstrate external mutation via leaked list reference
        List<String> tags = t3.getTags();
        tags.add("HACKED_FROM_OUTSIDE");
        System.out.println("\nAfter external tag mutation (ticket unchanged): " + t3);

        // Updates happen through new instances; external list changes do not affect the ticket.
    }
}
