package com.example.demo.service;

import com.example.demo.entity.Seats;
import com.example.demo.entity.Theaters;
import com.example.demo.repository.SeatRepository;
import com.example.demo.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    private TheaterRepository theaterRepository;
    private SeatRepository seatRepository;

    @Autowired
    public SeatService(SeatRepository seatRepository, TheaterRepository theaterRepository) {
        this.seatRepository = seatRepository;
        this.theaterRepository = theaterRepository;
    }

    public List<Seats> findAll(){
        return seatRepository.findAll();
    }

    public List<Seats> getSeatsbyTheaterId(int theaterId){
        Optional<Theaters> result = theaterRepository.findById(theaterId);

        if(result.isPresent()) {
            return seatRepository.findByTheaterId(result.get());
        }else{
            throw new RuntimeException("Theater not found with id: " + theaterId);
        }
    }


}
