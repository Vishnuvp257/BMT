package com.example.demo.service;

import com.example.demo.entity.Movies;
import com.example.demo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movies> findAll(){
        return movieRepository.findAll();
    }

    public Movies findById(int id){
        Optional<Movies> result = movieRepository.findById(id);

        if(result.isPresent()){
            return result.get();
        }else{
            throw new RuntimeException("Movie not found with id: " + id);
        }
    }

    public Movies save(Movies movie){
        return movieRepository.save(movie);
    }

    public Movies saveOrUpdate(int id, Movies movie){
        Movies existingUser = findById(id);

        existingUser.setTitle(movie.getTitle() != null ? movie.getTitle() : existingUser.getTitle());
        existingUser.setGenre(movie.getGenre() != null ? movie.getGenre() : existingUser.getGenre());
        existingUser.setDuration(movie.getDuration() != null ? movie.getDuration() : existingUser.getDuration());
        existingUser.setRatings(movie.getRatings() != null ? movie.getRatings() : existingUser.getRatings());
        existingUser.setDescrip(movie.getDescrip() != null ? movie.getDescrip() : existingUser.getDescrip());

        return movieRepository.save(existingUser);
    }

    public Movies deleteById(int id){
        Movies movie = findById(id);
        movieRepository.deleteById(id);

        return movie;
    }

}
