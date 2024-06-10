package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "bookings")
public class Bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private int bookingId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Users userId;

    @ManyToOne
    @JoinColumn(name="showtime_id")
    private Showtimes showtimeId;

    @Column(name = "booking_date")
    private LocalDateTime bookingDate;

    @Column(name = "num_tickets")
    private int numTickets;

    @ManyToMany
    @JoinTable(
            name = "booking_seats",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "seat_id")
    )
    private List<Seats> seats;

    public Bookings(Users userId, Showtimes showtimeId, LocalDateTime bookingDate, int numTickets) {
        this.userId = userId;
        this.showtimeId = showtimeId;
        this.bookingDate = bookingDate;
        this.numTickets = numTickets;
    }

    public Bookings() {

    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public Showtimes getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(Showtimes showtimeId) {
        this.showtimeId = showtimeId;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getNumTickets() {
        return numTickets;
    }

    public void setNumTickets(int numTickets) {
        this.numTickets = numTickets;
    }

    @Override
    public String toString() {
        return "Bookings{" +
                "bookingId=" + bookingId +
                ", userId=" + userId +
                ", showtimeId=" + showtimeId +
                ", bookingDate=" + bookingDate +
                ", numTickets=" + numTickets +
                '}';
    }
}
