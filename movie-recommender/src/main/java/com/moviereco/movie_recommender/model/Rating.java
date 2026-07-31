package com.moviereco.movie_recommender.model;

import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "Ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private double rating;
    private LocalDate dateRated;

    public Rating() {
        // blank constructor
    }

    public Rating(Long id, Movie movie, double rating, LocalDate dateRated) {
        this.id = id;
        this.movie = movie;
        this.rating = rating;
        this.dateRated = dateRated;
    }

    public Long getId() {
        return id;
    }
    public Movie getMovie() {
        return movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    public double getRating() {
        return rating;
    }
    public LocalDate getDateRated() {
        return dateRated;
    }
    public void setDateRated(LocalDate dateRated) {
        this.dateRated = dateRated;
    }

}
