package com.example.demo.controller;

import com.example.demo.entity.Seats;
import com.example.demo.service.SeatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/theaters")
public class SeatController {

    private SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/{theaterId}/seats")
    public List<Seats> getALlSeats(@PathVariable int theaterId){
        return seatService.getSeatsbyTheaterId(theaterId);
    }
}
