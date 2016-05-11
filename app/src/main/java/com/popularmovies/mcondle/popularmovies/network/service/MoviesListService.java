package com.popularmovies.mcondle.popularmovies.network.service;

import com.popularmovies.mcondle.popularmovies.network.Urls;
import com.popularmovies.mcondle.popularmovies.network.model.MovieLite;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by mandeep.condle on 5/7/16.
 */
public interface MoviesListService {

    @GET(Urls.API_POPULAR_MOVIES)
    void getPopularMovies(
            @Query("api_key") String apiKey,
            Callback<List<MovieLite>> popularMovies
    );

    @GET(Urls.API_TOP_RATED_MOVIES)
    void getTopRatedMovies(
            @Query("api_key") String apiKey,
            Callback<List<MovieLite>> topRatedMovies
    );

}
