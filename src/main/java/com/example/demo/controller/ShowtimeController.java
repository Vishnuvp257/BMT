package com.example.demo.controller;

import com.example.demo.dto.ShowtimeRequest;
import com.example.demo.entity.Movies;
import com.example.demo.entity.Showtimes;
import com.example.demo.entity.Theaters;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.ShowtimeRepository;
import com.example.demo.repository.TheaterRepository;
import com.example.demo.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class ShowtimeController {

    private final ShowtimeService showtimeService;

    @Autowired
    public ShowtimeController(ShowtimeService showtimeService, MovieRepository movieRepository, TheaterRepository theaterRepository){
        this.showtimeService = showtimeService;
    }

    @GetMapping("/{movieId}/showtimes")
    public List<Showtimes> showtimes(@PathVariable("movieId") int movieId) {
        return showtimeService.getShowtimeByMovieId(movieId);
    }

    @PostMapping("/{movieId}/showtimes")
    public List<Showtimes> addShowtimes(@PathVariable("movieId") int movieId, @RequestBody ShowtimeRequest req) {
        int theaterId = req.getTheaterId();
        return showtimeService.save(movieId,theaterId,req);
    }
}
