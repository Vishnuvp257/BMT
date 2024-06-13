package com.example.demo.service;

import com.example.demo.dto.ShowtimeRequest;
import com.example.demo.entity.Movies;
import com.example.demo.entity.Showtimes;
import com.example.demo.entity.Theaters;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.ShowtimeRepository;
import com.example.demo.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShowtimeService {

    private final MovieRepository movieRepository;
    private final ShowtimeRepository showtimeRepository;
    private final TheaterRepository theaterRepository;

    @Autowired
    public ShowtimeService(MovieRepository movieRepository, ShowtimeRepository showtimeRepository, TheaterRepository theaterRepository){
        this.movieRepository = movieRepository;
        this.showtimeRepository = showtimeRepository;
        this.theaterRepository = theaterRepository;
    }

    public List<Showtimes> getShowtimeByMovieId(int movieId){
        Optional<Movies> result = movieRepository.findById(movieId);

        if(result.isPresent()) {
            return showtimeRepository.findByMovieId(result.get());
        }else {
            throw new RuntimeException("Movie not found with id: " + movieId);
        }
    }

    public List<Showtimes> save(int movieId, int theaterId, ShowtimeRequest req){
        Optional<Movies> movie = movieRepository.findById(movieId);
        Optional<Theaters> theater = theaterRepository.findById(theaterId);

        if(movie.isPresent() && theater.isPresent()) {
            List<Showtimes> showtimeArray = req.getShowtimes().stream().map(detail->
                        new Showtimes(movie.get(),theater.get(), detail.getShowDate(), detail.getShowTime())
                    ).collect(Collectors.toList());

            return showtimeRepository.saveAll(showtimeArray);
        }else if(movie.isEmpty()){
            throw new RuntimeException("Movie not found with id: " + movieId);
        }else{
            throw new RuntimeException("Theater not found with id: " + theaterId);
        }
    }
}
