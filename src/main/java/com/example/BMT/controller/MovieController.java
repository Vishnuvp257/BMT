package com.example.demo.controller;

import com.example.demo.entity.Movies;
import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello World 24";
    }

    @GetMapping("/movies")
    public List<Movies> movies() {
        return movieService.findAll();
    }

    @GetMapping("/movies/{id}")
    public Movies movies(@PathVariable int id) {
        return movieService.findById(id);
    }

    @PostMapping("/movies")
    public Movies addMovie(@RequestBody Movies movie) {
        return movieService.save(movie);
    }

    @PutMapping("/movies/{id}")
    public Movies updateMovie(@PathVariable int id, @RequestBody Movies movie) {
        return movieService.saveOrUpdate(id,movie);
    }

    @DeleteMapping("/movies/{id}")
    public Movies deleteMovie(@PathVariable int id) {
        return movieService.deleteById(id);
    }
}
