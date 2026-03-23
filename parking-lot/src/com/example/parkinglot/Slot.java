package com.example.parkinglot;

public class Slot {
    private int id;
    private SlotType type;
    private int floor;
    private boolean isOccupied;

    public Slot(int id, int floor, SlotType type) {
        this.id = id;
        this.floor = floor;
        this.type = type;
        this.isOccupied = false;
    }

    public int getId() { return id; }
    public SlotType getType() { return type; }
    public int getFloor() { return floor; }
    public boolean isOccupied() { return isOccupied; }

    public void setOccupied(boolean occupied) { this.isOccupied = occupied; }

    // Distance logic based on floor level
    public int distanceTo(int gateId) {
        return Math.abs(this.floor - gateId);
    }
}
