package com.moviereco.movie_recommender.service;

import org.springframework.web.client.RestClient;

import com.moviereco.movie_recommender.dto.OmdbMovieResponse;
import com.moviereco.movie_recommender.model.Movie;

public class OmdbService {
    private final RestClient restClient;
    private final String apiKey;

    public OmdbService(RestClient restClient, String apiKey) {
        this.restClient = restClient;
        this.apiKey = apiKey;
    }

    public Movie fetchMovieByTitle(String title) {
        OmdbMovieResponse response = restClient.get().uri(uriBuilder -> uriBuilder
                .queryParam("apikey", apiKey)
                .queryParam("t", title)
                .build())
            .retrieve()
            .body(OmdbMovieResponse.class);

            if (response == null || response.getTitle() == null) {
                throw new RuntimeException("Movie not found on OMDB: " + title);
            }
        return convertToMovie(response);
    }

    private Movie convertToMovie(OmdbMovieResponse dto) {

        Movie movie = new Movie();
        movie.setTitle(dto.getTitle());
        movie.setGenre(dto.getGenre());
        movie.setDirector(dto.getDirector());
        movie.setActors(dto.getActors());
        movie.setImdbId(dto.getImdbId());
        movie.setPosterUrl(dto.getPosterUrl());

        movie.setYear(parseIntSafely(dto.getYear()));
        movie.setImdbRating(parseDoubleSafely(dto.getImdbId()));
        return movie;
    }


    private int parseIntSafely(String year) {
        try {
            return Integer.parseInt(year);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private double parseDoubleSafely(String imdbId) {
        try {
            return Double.parseDouble(imdbId);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
