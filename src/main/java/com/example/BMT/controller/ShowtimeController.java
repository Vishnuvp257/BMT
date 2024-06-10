package com.example.demo.controller;

import com.example.demo.entity.Showtimes;
import com.example.demo.service.MovieService;
import com.example.demo.service.ShowtimeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class ShowtimeController {

    private final ShowtimeService showtimeService;

    public ShowtimeController(ShowtimeService showtimeService){
        this.showtimeService = showtimeService;
    }

    @GetMapping("/{movieId}/showtimes")
    public List<Showtimes> showtimes(@PathVariable("movieId") int movieId) {
        return showtimeService.getShowtimeByMovieId(movieId);
    }
}
