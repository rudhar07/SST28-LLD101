package com.example.movieticket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Initializing Movie Ticket Booking System ===");

        // --- Setup Screen 1 (Audi 1) with seats ---
        List<Seat> screen1Seats = new ArrayList<>();
        // Row A - Regular
        screen1Seats.add(new Seat(1, "A", SeatType.REGULAR));
        screen1Seats.add(new Seat(2, "A", SeatType.REGULAR));
        screen1Seats.add(new Seat(3, "A", SeatType.REGULAR));
        screen1Seats.add(new Seat(4, "A", SeatType.REGULAR));
        // Row B - Regular
        screen1Seats.add(new Seat(1, "B", SeatType.REGULAR));
        screen1Seats.add(new Seat(2, "B", SeatType.REGULAR));
        screen1Seats.add(new Seat(3, "B", SeatType.REGULAR));
        screen1Seats.add(new Seat(4, "B", SeatType.REGULAR));
        // Row C - Premium
        screen1Seats.add(new Seat(1, "C", SeatType.PREMIUM));
        screen1Seats.add(new Seat(2, "C", SeatType.PREMIUM));
        screen1Seats.add(new Seat(3, "C", SeatType.PREMIUM));
        // Row D - VIP
        screen1Seats.add(new Seat(1, "D", SeatType.VIP));
        screen1Seats.add(new Seat(2, "D", SeatType.VIP));

        Screen screen1 = new Screen(1, "Audi 1", screen1Seats);

        // --- Setup Screen 2 (Audi 2) with seats ---
        List<Seat> screen2Seats = new ArrayList<>();
        // Row A - Regular
        screen2Seats.add(new Seat(1, "A", SeatType.REGULAR));
        screen2Seats.add(new Seat(2, "A", SeatType.REGULAR));
        screen2Seats.add(new Seat(3, "A", SeatType.REGULAR));
        // Row B - Premium
        screen2Seats.add(new Seat(1, "B", SeatType.PREMIUM));
        screen2Seats.add(new Seat(2, "B", SeatType.PREMIUM));

        Screen screen2 = new Screen(2, "Audi 2", screen2Seats);

        // --- Create Theatre ---
        Theatre theatre = new Theatre("CinePlex Multiplex", Arrays.asList(screen1, screen2));

        // --- Create Movies ---
        Movie movie1 = new Movie("MOV-1", "Inception", "Sci-Fi", 148);
        Movie movie2 = new Movie("MOV-2", "The Dark Knight", "Action", 152);

        // --- Add Shows ---
        System.out.println("\n=== Adding Shows ===");
        LocalDateTime today = LocalDateTime.now().withHour(0).withMinute(0);
        Show show1 = theatre.addShow("SHW-1", movie1, screen1, today.withHour(10).withMinute(30));
        Show show2 = theatre.addShow("SHW-2", movie2, screen2, today.withHour(14).withMinute(0));

        // --- Show initial availability ---
        theatre.showAvailability();

        // --- Booking Scenario 1: Customer books 2 Regular seats ---
        System.out.println("\n=== Booking Scenario 1: Regular seats ===");
        Customer customer1 = new Customer("Rahul Sharma", "rahul@email.com");
        List<Seat> seatsToBook1 = Arrays.asList(screen1Seats.get(0), screen1Seats.get(1)); // A1, A2
        Booking b1 = theatre.bookTickets(customer1, show1, seatsToBook1);

        // --- Booking Scenario 2: Customer books VIP seats ---
        System.out.println("\n=== Booking Scenario 2: VIP seats ===");
        Customer customer2 = new Customer("Priya Patel", "priya@email.com");
        List<Seat> seatsToBook2 = Arrays.asList(screen1Seats.get(11), screen1Seats.get(12)); // D1, D2
        Booking b2 = theatre.bookTickets(customer2, show1, seatsToBook2);

        // --- Booking Scenario 3: Customer books Premium seats in Audi 2 ---
        System.out.println("\n=== Booking Scenario 3: Premium seats ===");
        Customer customer3 = new Customer("Amit Kumar", "amit@email.com");
        List<Seat> seatsToBook3 = Arrays.asList(screen2Seats.get(3), screen2Seats.get(4)); // B1, B2
        Booking b3 = theatre.bookTickets(customer3, show2, seatsToBook3);

        // --- Show availability after bookings ---
        theatre.showAvailability();

        // --- Booking Scenario 4: Try to book an already booked seat ---
        System.out.println("\n=== Booking Scenario 4: Duplicate booking attempt ===");
        Customer customer4 = new Customer("Sneha Gupta", "sneha@email.com");
        List<Seat> seatsToBook4 = Arrays.asList(screen1Seats.get(0)); // A1 (already booked)
        Booking b4 = theatre.bookTickets(customer4, show1, seatsToBook4);

        // --- Cancel Booking 1 ---
        System.out.println("\n=== Cancellation Scenario ===");
        if (b1 != null) theatre.cancelBooking(b1);

        // --- Show final availability ---
        theatre.showAvailability();
    }
}
