package com.example.movieticket;

import java.util.*;

public class Theatre {
    private String name;
    private List<Screen> screens;
    private List<Show> shows;
    private List<Booking> bookings;

    public Theatre(String name, List<Screen> screens) {
        this.name = name;
        this.screens = screens;
        this.shows = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }

    public Show addShow(String showId, Movie movie, Screen screen, java.time.LocalDateTime showTime) {
        Show show = new Show(showId, movie, screen, showTime);
        shows.add(show);
        System.out.println("-> Show added: \"" + movie.getTitle() + "\" on Screen " + screen.getName() + " at " + showTime);
        return show;
    }

    public Booking bookTickets(Customer customer, Show show, List<Seat> requestedSeats) {
        // Verify all requested seats are available
        for (Seat seat : requestedSeats) {
            if (seat.isBooked()) {
                System.out.println("Booking failed: Seat " + seat.getSeatLabel() + " is already booked.");
                return null;
            }
        }

        // Calculate total amount
        double totalAmount = 0;
        for (Seat seat : requestedSeats) {
            totalAmount += seat.getType().getPrice();
        }

        // Mark seats as booked
        for (Seat seat : requestedSeats) {
            seat.setBooked(true);
        }

        String bookingId = "BKG-" + System.currentTimeMillis();
        Booking booking = new Booking(bookingId, show, requestedSeats, customer, totalAmount);
        bookings.add(booking);

        StringBuilder seatLabels = new StringBuilder();
        for (int i = 0; i < requestedSeats.size(); i++) {
            if (i > 0) seatLabels.append(", ");
            seatLabels.append(requestedSeats.get(i).getSeatLabel());
        }

        System.out.println("\n✅ Booking Confirmed!");
        System.out.println("   Booking ID : " + bookingId);
        System.out.println("   Movie      : " + show.getMovie().getTitle());
        System.out.println("   Show Time  : " + show.getShowTime());
        System.out.println("   Customer   : " + customer.getName());
        System.out.println("   Seats      : " + seatLabels);
        System.out.println("   Total      : ₹" + totalAmount);

        return booking;
    }

    public boolean cancelBooking(Booking booking) {
        if (booking.getStatus() == BookingStatus.CANCELLED) {
            System.out.println("Booking " + booking.getBookingId() + " is already cancelled.");
            return false;
        }

        // Release the seats
        for (Seat seat : booking.getBookedSeats()) {
            seat.setBooked(false);
        }

        booking.setStatus(BookingStatus.CANCELLED);
        System.out.println("\n❌ Booking " + booking.getBookingId() + " cancelled. Seats released. Refund: ₹" + booking.getTotalAmount());
        return true;
    }

    public void showAvailability() {
        System.out.println("\n--- " + name + " : Current Availability ---");
        for (Show show : shows) {
            System.out.println("\nMovie: \"" + show.getMovie().getTitle() + "\" | Screen: " + show.getScreen().getName()
                    + " | Time: " + show.getShowTime());

            Map<SeatType, int[]> seatStats = new LinkedHashMap<>();
            for (SeatType type : SeatType.values()) {
                seatStats.put(type, new int[]{0, 0}); // [available, total]
            }

            for (Seat seat : show.getScreen().getSeats()) {
                int[] stats = seatStats.get(seat.getType());
                stats[1]++; // total
                if (!seat.isBooked()) stats[0]++; // available
            }

            for (Map.Entry<SeatType, int[]> entry : seatStats.entrySet()) {
                int[] stats = entry.getValue();
                if (stats[1] > 0) {
                    System.out.println("   " + entry.getKey() + " (₹" + entry.getKey().getPrice() + "): "
                            + stats[0] + "/" + stats[1] + " available");
                }
            }
        }
    }

    public List<Show> getShows() { return shows; }
    public List<Booking> getBookings() { return bookings; }
}
