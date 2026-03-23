package com.example.parkinglot;

import java.time.LocalDateTime;

public class Ticket {
    private String ticketId;
    private Vehicle vehicle;
    private Slot allocatedSlot;
    private LocalDateTime entryTime;

    public Ticket(String ticketId, Vehicle vehicle, Slot allocatedSlot, LocalDateTime entryTime) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.allocatedSlot = allocatedSlot;
        this.entryTime = entryTime;
    }

    public String getTicketId() { return ticketId; }
    public Vehicle getVehicle() { return vehicle; }
    public Slot getAllocatedSlot() { return allocatedSlot; }
    public LocalDateTime getEntryTime() { return entryTime; }
}
