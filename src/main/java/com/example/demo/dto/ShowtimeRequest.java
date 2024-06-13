package com.example.demo.dto;

import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class ShowtimeRequest {

    private int theaterId;
    private List<ShowtimeDetails> showtimes;

    public ShowtimeRequest(int theaterId, List<ShowtimeDetails> showtimes) {
        this.theaterId = theaterId;
        this.showtimes = showtimes;
    }

    public static class ShowtimeDetails {
        private LocalDate showDate;
        private LocalTime showTime;

        public ShowtimeDetails(LocalDate showDate, LocalTime showTime) {
            this.showDate = showDate;
            this.showTime = showTime;
        }

        public LocalDate getShowDate() {
            return showDate;
        }

        public void setShowDate(LocalDate showDate) {
            this.showDate = showDate;
        }

        public LocalTime getShowTime() {
            return showTime;
        }

        public void setShowTime(LocalTime showTime) {
            this.showTime = showTime;
        }
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public List<ShowtimeDetails> getShowtimes() {
        return showtimes;
    }

    public void setShowtimes(List<ShowtimeDetails> showtimes) {
        this.showtimes = showtimes;
    }
}

