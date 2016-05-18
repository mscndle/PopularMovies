package com.popularmovies.mcondle.popularmovies.network.service;

import com.popularmovies.mcondle.popularmovies.network.model.MoviesListResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by mandeep.condle on 5/7/16.
 */
public interface MoviesListService {

    String URL_POPULAR_MOVIES = "/movie/popular";
    String API_TOP_RATED_MOVIES = "/movie/top_rated";

    @GET(URL_POPULAR_MOVIES)
    void getPopularMovies(
            @Query("api_key") String apiKey,
            Callback<MoviesListResponse> popularMovies
    );

    @GET(API_TOP_RATED_MOVIES)
    void getTopRatedMovies(
            @Query("api_key") String apiKey,
            Callback<MoviesListResponse> topRatedMovies
    );

}
