package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "showtimes")
public class Showtimes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "showtime_id")
    private int showtimeId;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movies movieId;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theaters theaterId;

    @Column(name = "show_date")
    private LocalDate showDate;

    @Column(name = "show_time")
    private LocalTime showTime;

    public Showtimes(Movies movieId, Theaters theaterId, LocalDate showDate, LocalTime showTime) {
        this.movieId = movieId;
        this.theaterId = theaterId;
        this.showDate = showDate;
        this.showTime = showTime;
    }

    public Showtimes() {

    }

    public Movies getMovieId() {
        return movieId;
    }

    public void setMovieId(Movies movieId) {
        this.movieId = movieId;
    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public LocalDate getShowDate() {
        return showDate;
    }

    public void setShowDate(LocalDate showDate) {
        this.showDate = showDate;
    }

    public Theaters getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(Theaters theaterId) {
        this.theaterId = theaterId;
    }

    public LocalTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalTime showTime) {
        this.showTime = showTime;
    }
}
