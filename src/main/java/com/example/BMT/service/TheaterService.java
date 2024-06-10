package com.example.demo.service;

import com.example.demo.entity.Theaters;
import com.example.demo.repository.TheaterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService {

    private final TheaterRepository theaterRepository;

    public TheaterService(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    public List<Theaters> findAll(){
        return theaterRepository.findAll();
    }
}
