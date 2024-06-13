package com.example.demo.dto;

public class BookingRequest {

    private int userId;
    private int showtimeId;
    private int noOfSeats;
    private String selectedSeatIds;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public String getSelectedSeatIds() {
        return selectedSeatIds;
    }

    public void setSelectedSeatIds(String selectedSeatIds) {
        this.selectedSeatIds = selectedSeatIds;
    }
}
