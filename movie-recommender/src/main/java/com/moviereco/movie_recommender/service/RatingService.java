package com.moviereco.movie_recommender.service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.moviereco.movie_recommender.model.Movie;
import com.moviereco.movie_recommender.model.Rating;
import com.moviereco.movie_recommender.repository.MovieRepository;
import com.moviereco.movie_recommender.repository.RatingRepository;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    private final MovieRepository movieRepository;
    
    public RatingService(RatingRepository ratingRepository, MovieRepository movieRepository) {
        this.ratingRepository = ratingRepository;
        this.movieRepository = movieRepository;
    }

    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    public Rating rateMovie(Long movieId, double score) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new NoSuchElementException("Movie not found with id: " + movieId));

        Rating rating = new Rating(movieId, movie, score, LocalDate.now());
        return ratingRepository.save(rating);
    }

    public void deleteRating(Long ratingId) {
        if (!ratingRepository.existsById(ratingId)) {
            throw new NoSuchElementException("Rating not found with id: " + ratingId);
        }
        ratingRepository.deleteById(ratingId);
    }
}
