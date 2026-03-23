package com.example.parkinglot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Initializing Multilevel Parking Lot ===");
        
        List<Slot> slots = new ArrayList<>();
        // Floor 1 (Nearest to Gate 1)
        slots.add(new Slot(101, 1, SlotType.SMALL));
        slots.add(new Slot(102, 1, SlotType.SMALL));
        slots.add(new Slot(103, 1, SlotType.MEDIUM));
        
        // Floor 2 (Nearest to Gate 2)
        slots.add(new Slot(201, 2, SlotType.MEDIUM));
        slots.add(new Slot(202, 2, SlotType.LARGE));
        
        // Floor 3 (Nearest to Gate 3)
        slots.add(new Slot(301, 3, SlotType.LARGE));
        slots.add(new Slot(302, 3, SlotType.LARGE));

        ParkingLot lot = new ParkingLot("Central Plaza Parking", slots);

        lot.status();

        System.out.println("\n=== Testing Parking Scenarios ===");
        LocalDateTime now = LocalDateTime.now();

        // 1. Park a BIKE entering from Gate 1
        Vehicle bike = new Vehicle("BIKE-123", VehicleType.BIKE);
        Ticket t1 = lot.park(bike, now.minusHours(2), SlotType.SMALL, 1);

        // 2. Park a CAR entering from Gate 1
        Vehicle car1 = new Vehicle("CAR-ABC", VehicleType.CAR);
        Ticket t2 = lot.park(car1, now.minusHours(4), SlotType.MEDIUM, 1);

        // 3. Park another CAR entering from Gate 2
        Vehicle car2 = new Vehicle("CAR-XYZ", VehicleType.CAR);
        Ticket t3 = lot.park(car2, now.minusHours(1), SlotType.MEDIUM, 2);

        // 4. Park another CAR (No MEDIUM left, should assign LARGE on floor 2 or 3)
        Vehicle car3 = new Vehicle("CAR-MMM", VehicleType.CAR);
        Ticket t4 = lot.park(car3, now.minusHours(5), SlotType.MEDIUM, 1);

        lot.status();

        System.out.println("\n=== Testing Exit Scenarios ===");
        if (t1 != null) lot.exit(t1, now);
        
        // Exiting the car that took a LARGE slot
        if (t4 != null) lot.exit(t4, now);

        lot.status();
    }
}
