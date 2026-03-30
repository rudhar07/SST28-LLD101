package com.example.movieticket;

public enum SeatType {
    REGULAR(150.0),
    PREMIUM(250.0),
    VIP(500.0);

    private final double price;

    SeatType(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
