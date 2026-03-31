package com.example.elevator;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Initializing Elevator System ===");

        // Create 3 elevators with capacity 8 and weight limit 800 kg
        Elevator e1 = new Elevator(1, 8, 800.0);
        Elevator e2 = new Elevator(2, 8, 800.0);
        Elevator e3 = new Elevator(3, 8, 800.0);

        // Building with 10 floors (0 to 9) and 3 elevators
        ElevatorController controller = new ElevatorController(
                "Skyline Tower",
                10,
                Arrays.asList(e1, e2, e3)
        );

        controller.status();

        // --- Scenario 1: External requests (people pressing buttons on floors) ---
        System.out.println("\n=== Scenario 1: External Floor Requests ===");

        Request r1 = controller.getFloor(3).pressUp();    // Person at floor 3 wants to go up
        controller.handleExternalRequest(r1);

        Request r2 = controller.getFloor(7).pressDown();  // Person at floor 7 wants to go down
        controller.handleExternalRequest(r2);

        Request r3 = controller.getFloor(5).pressUp();    // Person at floor 5 wants to go up
        controller.handleExternalRequest(r3);

        // --- Scenario 2: Internal requests (person inside elevator presses destination) ---
        System.out.println("\n=== Scenario 2: Internal Destination Requests ===");

        // Person picked up at floor 3 wants to go to floor 8
        controller.handleInternalRequest(e1, 8);

        // Person picked up at floor 7 wants to go to floor 1
        controller.handleInternalRequest(e2, 1);

        // Person picked up at floor 5 wants to go to floor 9
        controller.handleInternalRequest(e3, 9);

        // --- Process all stops ---
        controller.runAllElevators();

        controller.status();

        // --- Scenario 3: More requests after first round ---
        System.out.println("\n=== Scenario 3: New Requests After First Round ===");

        Request r4 = controller.getFloor(2).pressUp();
        controller.handleExternalRequest(r4);

        Request r5 = controller.getFloor(6).pressDown();
        controller.handleExternalRequest(r5);

        // Internal: person at elevator 1 (currently at floor 8) wants floor 0
        controller.handleInternalRequest(e1, 0);

        controller.runAllElevators();

        controller.status();

        // --- Scenario 4: Edge case - ground floor DOWN button ---
        System.out.println("\n=== Scenario 4: Edge Cases ===");
        controller.getFloor(0).pressDown();   // Should show no DOWN button
        controller.getFloor(9).pressUp();     // Should show no UP button

        // --- Scenario 5: Full elevator ---
        System.out.println("\n=== Scenario 5: Full Elevator ===");
        e1.setCurrentLoad(8); // Max capacity
        Request r6 = controller.getFloor(4).pressUp();
        controller.handleExternalRequest(r6);
        // Reset load
        e1.setCurrentLoad(0);

        controller.status();

        // --- Scenario 6: Weight sensor overload ---
        System.out.println("\n=== Scenario 6: Weight Sensor Overload ===");
        e1.setCurrentLoad(0);
        e1.getWeightSensor().setCurrentWeight(500.0);
        System.out.println("Elevator 1 weight: " + e1.getWeightSensor());

        // Add stops and process - should work fine at 500kg
        e1.addStop(3);
        e1.processAllStops();
        System.out.println("Elevator 1 door: " + e1.getDoorStatus());

        // Now overload it
        e1.getWeightSensor().setCurrentWeight(850.0);
        System.out.println("\nElevator 1 weight increased to: " + e1.getWeightSensor());
        e1.addStop(5);
        e1.addStop(7);
        e1.processAllStops(); // Should stop at floor 5, door stays open
        System.out.println("Elevator 1 door: " + e1.getDoorStatus()); // Should be OPEN

        // Reduce weight and retry
        e1.getWeightSensor().setCurrentWeight(600.0);
        System.out.println("\nWeight reduced to: " + e1.getWeightSensor());
        e1.processAllStops(); // Now should continue to floor 7
        System.out.println("Elevator 1 door: " + e1.getDoorStatus()); // Should be CLOSED

        controller.status();
    }
}
