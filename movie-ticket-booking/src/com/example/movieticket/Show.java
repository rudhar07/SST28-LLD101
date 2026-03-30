package com.example.movieticket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Show {
    private String showId;
    private Movie movie;
    private Screen screen;
    private LocalDateTime showTime;

    public Show(String showId, Movie movie, Screen screen, LocalDateTime showTime) {
        this.showId = showId;
        this.movie = movie;
        this.screen = screen;
        this.showTime = showTime;
    }

    public String getShowId() { return showId; }
    public Movie getMovie() { return movie; }
    public Screen getScreen() { return screen; }
    public LocalDateTime getShowTime() { return showTime; }

    public List<Seat> getAvailableSeats() {
        List<Seat> available = new ArrayList<>();
        for (Seat seat : screen.getSeats()) {
            if (!seat.isBooked()) {
                available.add(seat);
            }
        }
        return available;
    }

    public List<Seat> getAvailableSeatsByType(SeatType type) {
        List<Seat> available = new ArrayList<>();
        for (Seat seat : screen.getSeats()) {
            if (!seat.isBooked() && seat.getType() == type) {
                available.add(seat);
            }
        }
        return available;
    }
}
