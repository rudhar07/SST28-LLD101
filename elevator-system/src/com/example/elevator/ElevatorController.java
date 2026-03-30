package com.example.elevator;

import java.util.*;

public class ElevatorController {
    private String buildingName;
    private List<Elevator> elevators;
    private List<Floor> floors;

    public ElevatorController(String buildingName, int totalFloors, List<Elevator> elevators) {
        this.buildingName = buildingName;
        this.elevators = elevators;
        this.floors = new ArrayList<>();
        for (int i = 0; i < totalFloors; i++) {
            floors.add(new Floor(i, totalFloors));
        }
    }

    public void handleExternalRequest(Request request) {
        Elevator best = findBestElevator(request);
        if (best == null) {
            System.out.println("   All elevators are busy or full. Request queued.");
            return;
        }
        System.out.println("   -> Assigned to Elevator " + best.getId());
        best.addStop(request.getFloor());
    }

    public void handleInternalRequest(Elevator elevator, int destinationFloor) {
        System.out.println("-> Inside Elevator " + elevator.getId() + ": button pressed for Floor " + destinationFloor);
        elevator.addStop(destinationFloor);
    }

    private Elevator findBestElevator(Request request) {
        Elevator best = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            if (elevator.isFull()) continue;

            int distance = Math.abs(elevator.getCurrentFloor() - request.getFloor());

            // Prefer idle elevators or elevators already heading in the right direction
            if (elevator.getDirection() == Direction.IDLE) {
                if (distance < minDistance) {
                    minDistance = distance;
                    best = elevator;
                }
            } else if (elevator.getDirection() == request.getDirection()) {
                boolean onTheWay = false;
                if (request.getDirection() == Direction.UP && elevator.getCurrentFloor() <= request.getFloor()) {
                    onTheWay = true;
                } else if (request.getDirection() == Direction.DOWN && elevator.getCurrentFloor() >= request.getFloor()) {
                    onTheWay = true;
                }

                if (onTheWay && distance < minDistance) {
                    minDistance = distance;
                    best = elevator;
                }
            }
        }

        // Fallback: pick nearest elevator regardless of direction
        if (best == null) {
            for (Elevator elevator : elevators) {
                if (elevator.isFull()) continue;
                int distance = Math.abs(elevator.getCurrentFloor() - request.getFloor());
                if (distance < minDistance) {
                    minDistance = distance;
                    best = elevator;
                }
            }
        }

        return best;
    }

    public void runAllElevators() {
        System.out.println("\n--- Processing all elevator stops ---");
        for (Elevator elevator : elevators) {
            if (elevator.hasStops()) {
                System.out.println("\n[Elevator " + elevator.getId() + " starting from Floor " + elevator.getCurrentFloor() + "]");
                elevator.processAllStops();
            }
        }
    }

    public void status() {
        System.out.println("\n--- " + buildingName + " : Elevator Status ---");
        for (Elevator elevator : elevators) {
            System.out.println("   " + elevator.getStatus());
        }
        System.out.println("   Floors: " + floors.size() + " (0 to " + (floors.size() - 1) + ")");
    }

    public Floor getFloor(int floorNumber) {
        if (floorNumber >= 0 && floorNumber < floors.size()) {
            return floors.get(floorNumber);
        }
        return null;
    }

    public List<Elevator> getElevators() { return elevators; }
}
