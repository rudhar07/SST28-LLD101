package com.example.elevator;

public class Floor {
    private int floorNumber;
    private boolean hasUpButton;
    private boolean hasDownButton;

    public Floor(int floorNumber, int totalFloors) {
        this.floorNumber = floorNumber;
        this.hasUpButton = (floorNumber < totalFloors - 1);
        this.hasDownButton = (floorNumber > 0);
    }

    public int getFloorNumber() { return floorNumber; }
    public boolean hasUpButton() { return hasUpButton; }
    public boolean hasDownButton() { return hasDownButton; }

    public Request pressUp() {
        if (!hasUpButton) {
            System.out.println("   No UP button on Floor " + floorNumber);
            return null;
        }
        System.out.println("-> UP button pressed on Floor " + floorNumber);
        return new Request(floorNumber, Direction.UP);
    }

    public Request pressDown() {
        if (!hasDownButton) {
            System.out.println("   No DOWN button on Floor " + floorNumber);
            return null;
        }
        System.out.println("-> DOWN button pressed on Floor " + floorNumber);
        return new Request(floorNumber, Direction.DOWN);
    }
}
