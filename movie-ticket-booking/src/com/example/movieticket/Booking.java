package com.example.movieticket;

import java.util.List;

public class Booking {
    private String bookingId;
    private Show show;
    private List<Seat> bookedSeats;
    private Customer customer;
    private double totalAmount;
    private BookingStatus status;

    public Booking(String bookingId, Show show, List<Seat> bookedSeats, Customer customer, double totalAmount) {
        this.bookingId = bookingId;
        this.show = show;
        this.bookedSeats = bookedSeats;
        this.customer = customer;
        this.totalAmount = totalAmount;
        this.status = BookingStatus.CONFIRMED;
    }

    public String getBookingId() { return bookingId; }
    public Show getShow() { return show; }
    public List<Seat> getBookedSeats() { return bookedSeats; }
    public Customer getCustomer() { return customer; }
    public double getTotalAmount() { return totalAmount; }
    public BookingStatus getStatus() { return status; }

    public void setStatus(BookingStatus status) { this.status = status; }
}
