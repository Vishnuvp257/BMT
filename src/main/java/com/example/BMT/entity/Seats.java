package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="seats")
public class Seats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private int seatId;

    @ManyToOne
    @JoinColumn(name="theater_id")
    private Theaters theaterId;

    @Column(name="seat_number")
    private String seatNumber;

    @Column(name="is_booked")
    private boolean isBooked;

    public Seats(Theaters theaterId, String seatNumber, boolean isBooked) {
        this.theaterId = theaterId;
        this.seatNumber = seatNumber;
        this.isBooked = isBooked;
    }

    public Seats() {

    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public Theaters getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(Theaters theaterId) {
        this.theaterId = theaterId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}
