package com.example.elevator;

import java.util.*;

public class Elevator {
    private int id;
    private int currentFloor;
    private Direction direction;
    private DoorStatus doorStatus;
    private int capacity;
    private int currentLoad;
    private TreeSet<Integer> upStops;
    private TreeSet<Integer> downStops;

    public Elevator(int id, int capacity) {
        this.id = id;
        this.currentFloor = 0;
        this.direction = Direction.IDLE;
        this.doorStatus = DoorStatus.CLOSED;
        this.capacity = capacity;
        this.currentLoad = 0;
        this.upStops = new TreeSet<>();
        this.downStops = new TreeSet<>(Collections.reverseOrder());
    }

    public void addStop(int floor) {
        if (floor > currentFloor) {
            upStops.add(floor);
        } else if (floor < currentFloor) {
            downStops.add(floor);
        }
    }

    public void moveOneStep() {
        if (direction == Direction.UP) {
            if (!upStops.isEmpty()) {
                currentFloor++;
                if (upStops.contains(currentFloor)) {
                    upStops.remove(currentFloor);
                    openDoor();
                    System.out.println("   Elevator " + id + " stopped at Floor " + currentFloor + " (going UP)");
                    closeDoor();
                }
            }
            if (upStops.isEmpty()) {
                if (!downStops.isEmpty()) {
                    direction = Direction.DOWN;
                } else {
                    direction = Direction.IDLE;
                }
            }
        } else if (direction == Direction.DOWN) {
            if (!downStops.isEmpty()) {
                currentFloor--;
                if (downStops.contains(currentFloor)) {
                    downStops.remove(currentFloor);
                    openDoor();
                    System.out.println("   Elevator " + id + " stopped at Floor " + currentFloor + " (going DOWN)");
                    closeDoor();
                }
            }
            if (downStops.isEmpty()) {
                if (!upStops.isEmpty()) {
                    direction = Direction.UP;
                } else {
                    direction = Direction.IDLE;
                }
            }
        }
    }

    public void processAllStops() {
        if (upStops.isEmpty() && downStops.isEmpty()) {
            System.out.println("   Elevator " + id + " has no pending stops.");
            return;
        }

        // Decide initial direction
        if (!upStops.isEmpty() && (downStops.isEmpty() || direction == Direction.UP || direction == Direction.IDLE)) {
            direction = Direction.UP;
        } else {
            direction = Direction.DOWN;
        }

        int safetyCounter = 0;
        while (!upStops.isEmpty() || !downStops.isEmpty()) {
            moveOneStep();
            safetyCounter++;
            if (safetyCounter > 200) break; // safety net
        }
        direction = Direction.IDLE;
    }

    private void openDoor() {
        this.doorStatus = DoorStatus.OPEN;
    }

    private void closeDoor() {
        this.doorStatus = DoorStatus.CLOSED;
    }

    public boolean isFull() { return currentLoad >= capacity; }
    public int getId() { return id; }
    public int getCurrentFloor() { return currentFloor; }
    public Direction getDirection() { return direction; }
    public DoorStatus getDoorStatus() { return doorStatus; }
    public int getCapacity() { return capacity; }
    public int getCurrentLoad() { return currentLoad; }

    public void setCurrentLoad(int load) { this.currentLoad = load; }
    public void setDirection(Direction direction) { this.direction = direction; }

    public boolean hasStops() {
        return !upStops.isEmpty() || !downStops.isEmpty();
    }

    public int totalPendingStops() {
        return upStops.size() + downStops.size();
    }

    public String getStatus() {
        return "Elevator " + id + " | Floor: " + currentFloor + " | Dir: " + direction
                + " | Door: " + doorStatus + " | Load: " + currentLoad + "/" + capacity
                + " | Pending: " + totalPendingStops();
    }
}
