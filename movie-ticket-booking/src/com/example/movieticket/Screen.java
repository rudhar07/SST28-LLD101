package com.example.movieticket;

import java.util.List;

public class Screen {
    private int screenId;
    private String name;
    private List<Seat> seats;

    public Screen(int screenId, String name, List<Seat> seats) {
        this.screenId = screenId;
        this.name = name;
        this.seats = seats;
    }

    public int getScreenId() { return screenId; }
    public String getName() { return name; }
    public List<Seat> getSeats() { return seats; }

    public int getTotalSeats() { return seats.size(); }

    public int getAvailableCount() {
        int count = 0;
        for (Seat seat : seats) {
            if (!seat.isBooked()) count++;
        }
        return count;
    }
}
