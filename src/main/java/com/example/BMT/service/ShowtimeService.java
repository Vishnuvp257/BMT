package com.example.demo.service;

import com.example.demo.entity.Movies;
import com.example.demo.entity.Showtimes;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowtimeService {

    private final MovieRepository movieRepository;
    private final ShowtimeRepository showtimeRepository;

    @Autowired
    public ShowtimeService(MovieRepository movieRepository, ShowtimeRepository showtimeRepository){
        this.movieRepository = movieRepository;
        this.showtimeRepository = showtimeRepository;
    }

    public List<Showtimes> getShowtimeByMovieId(int movieId){
        Optional<Movies> result = movieRepository.findById(movieId);

        if(result.isPresent()) {
            return showtimeRepository.findByMovieId(result.get());
        }else {
            throw new RuntimeException("Movie not found with id: " + movieId);
        }
    }
}
