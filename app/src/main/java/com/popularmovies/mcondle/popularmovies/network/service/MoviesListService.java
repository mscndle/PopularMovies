package com.popularmovies.mcondle.popularmovies.network.service;

import com.popularmovies.mcondle.popularmovies.network.Urls;
import com.popularmovies.mcondle.popularmovies.network.model.MovieLite;
import com.popularmovies.mcondle.popularmovies.network.model.MoviesListResponse;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by mandeep.condle on 5/7/16.
 */
public interface MoviesListService {

    public static final String API_POPULAR_MOVIES = "/3/movie/popular";
    public static final String API_TOP_RATED_MOVIES = "/3/movie/top_rated";

    @GET(API_POPULAR_MOVIES)
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
