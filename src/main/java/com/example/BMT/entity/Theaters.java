package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "theaters")
public class Theaters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theater_id")
    private int theaterId;

    @Column(name = "theatername")
    private String theaterName;

    @Column(name = "location")
    private String location;

    @Column(name = "total_seats")
    private int totalSeats;

    public Theaters(String theaterName, String location, int totalSeats) {
        this.theaterName = theaterName;
        this.location = location;
        this.totalSeats = totalSeats;
    }

    public Theaters() {

    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }
}
