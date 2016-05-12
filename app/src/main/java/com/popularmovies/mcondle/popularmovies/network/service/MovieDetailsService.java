package com.popularmovies.mcondle.popularmovies.network.service;

import com.popularmovies.mcondle.popularmovies.network.model.Movie;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by mandeep.condle on 5/7/16.
 */
public interface MovieDetailsService {

    String API_BASE_MOVIE_DETAILS = "api.themoviedb.org/3/movie/{movieId}";

    @GET(API_BASE_MOVIE_DETAILS)
    void getMovieDetails(
            @Query("movieId") long movieId,
            Callback<Movie> movie
    );

}
