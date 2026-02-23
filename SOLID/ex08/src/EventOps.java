/**
 * Contract for event operations (create, count).
 */
public interface EventOps {
    void createEvent(String name, double budget);
    int getEventsCount();
}
