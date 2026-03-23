package com.example.parkinglot;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class ParkingLot {
    private String name;
    private List<Slot> slots;

    public ParkingLot(String name, List<Slot> slots) {
        this.name = name;
        this.slots = slots;
    }

    public Ticket park(Vehicle vehicle, LocalDateTime entryTime, SlotType requestedSlotType, int entryGateID) {
        List<SlotType> allowedSlotTypes = getAllowedSlotTypes(requestedSlotType);

        Slot selectedSlot = null;
        int minDistance = Integer.MAX_VALUE;

        for (SlotType type : allowedSlotTypes) {
            for (Slot slot : slots) {
                if (!slot.isOccupied() && slot.getType() == type) {
                    int distance = slot.distanceTo(entryGateID);
                    if (distance < minDistance) {
                        minDistance = distance;
                        selectedSlot = slot;
                    }
                }
            }
            if (selectedSlot != null) {
                break; 
            }
        }

        if (selectedSlot == null) {
            System.out.println("No compatible slots available for " + vehicle.getLicensePlate());
            return null;
        }

        selectedSlot.setOccupied(true);
        String ticketId = "TKT-" + System.currentTimeMillis();
        Ticket ticket = new Ticket(ticketId, vehicle, selectedSlot, entryTime);
        System.out.println("-> Vehicle " + vehicle.getLicensePlate() + " parked at slot " + selectedSlot.getId() + " (" + selectedSlot.getType() + ")");
        return ticket;
    }

    private List<SlotType> getAllowedSlotTypes(SlotType requestedType) {
        switch (requestedType) {
            case SMALL: return Arrays.asList(SlotType.SMALL, SlotType.MEDIUM, SlotType.LARGE);
            case MEDIUM: return Arrays.asList(SlotType.MEDIUM, SlotType.LARGE);
            case LARGE: return Collections.singletonList(SlotType.LARGE);
            default: return new ArrayList<>();
        }
    }

    public void status() {
        Map<SlotType, Integer> availability = new HashMap<>();
        for (SlotType type : SlotType.values()) {
            availability.put(type, 0);
        }

        for (Slot slot : slots) {
            if (!slot.isOccupied()) {
                availability.put(slot.getType(), availability.get(slot.getType()) + 1);
            }
        }

        System.out.println("\n--- Current Parking Availability ---");
        for (Map.Entry<SlotType, Integer> entry : availability.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " available");
        }
    }

    public double exit(Ticket parkingTicket, LocalDateTime exitTime) {
        Slot allocatedSlot = parkingTicket.getAllocatedSlot();
        allocatedSlot.setOccupied(false);

        long hours = Duration.between(parkingTicket.getEntryTime(), exitTime).toHours();
        if (hours <= 0) hours = 1; 
        
        double rate = allocatedSlot.getType().getHourlyRate();
        double amount = hours * rate;

        System.out.println("\n<- Vehicle " + parkingTicket.getVehicle().getLicensePlate() + " exited.");
        System.out.println("   Bill Amount: $" + amount + " (Duration: " + hours + " hrs @ $" + rate + "/hr for " + allocatedSlot.getType() + " slot)");

        return amount;
    }
}
