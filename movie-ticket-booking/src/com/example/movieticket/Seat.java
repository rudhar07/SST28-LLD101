package com.example.movieticket;

public class Seat {
    private int seatNumber;
    private String row;
    private SeatType type;
    private boolean isBooked;

    public Seat(int seatNumber, String row, SeatType type) {
        this.seatNumber = seatNumber;
        this.row = row;
        this.type = type;
        this.isBooked = false;
    }

    public int getSeatNumber() { return seatNumber; }
    public String getRow() { return row; }
    public SeatType getType() { return type; }
    public boolean isBooked() { return isBooked; }

    public void setBooked(boolean booked) { this.isBooked = booked; }

    public String getSeatLabel() {
        return row + seatNumber;
    }
}
