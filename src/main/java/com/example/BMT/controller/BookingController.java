package com.example.demo.controller;

import com.example.demo.dto.BookingRequest;
import com.example.demo.entity.Bookings;
import com.example.demo.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/bookings")
    public List<Bookings> bookings(){
        return bookingService.findAll();
    }

    @GetMapping("/bookings/{id}")
    public Bookings booking(@PathVariable int id){
        return bookingService.findBookingById(id);
    }

    @PostMapping("/bookings")
    public ResponseEntity<Map<String,Object>> createBooking(@RequestBody BookingRequest bq){
        int userId = bq.getUserId();
        int showtimeId = bq.getShowtimeId();
        int totalTickets = bq.getNoOfSeats();
        String ids = bq.getSelectedSeatIds();

        try{
            bookingService.bookTicket(userId,showtimeId,totalTickets,ids);

            Map<String,Object> response = new HashMap<>();
            response.put("status",HttpStatus.CREATED.value());
            response.put("message","Booking Ticket Created");
            response.put("userId",userId);
            response.put("showtimeId",showtimeId);
            response.put("totalTickets",totalTickets);
            response.put("selectedSeatIds",ids);

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
