package com.popularmovies.mcondle.popularmovies.network.service;

import com.popularmovies.mcondle.popularmovies.network.model.TrailersResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by mscndle on 5/15/16.
 */
public interface MovieTrailersService {

    String URL_MOVIE_TRAILERS = "/movie/{movieId}/videos";

    @GET(URL_MOVIE_TRAILERS)
    void getMovieTrailers(
            @Path("movieId") long movieId,
            @Query("api_key") String apiKey,
            Callback<TrailersResponse> trailersResponse
    );

}
