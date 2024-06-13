package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="movies")
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private int movieId;

    @Column(name = "title")
    private String title;

    public Movies(String title, String genre, String duration, String ratings, String descrip) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.ratings = ratings;
        this.descrip = descrip;
    }

    public Movies() {

    }

    @Column(name= "genre")
    private String genre;

    @Column(name = "duration")
    private String duration;

    @Column(name = "ratings")
    private String ratings;

    @Column(name = "descrip")
    private String descrip;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMovie_id() {
        return movieId;
    }

    public void setMovie_id(int movieId) {
        this.movieId = movieId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }
}
