package com.popularmovies.mcondle.popularmovies.network.service;

import com.popularmovies.mcondle.popularmovies.network.model.ReviewsResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by mscndle on 5/15/16.
 */
public interface MovieReviewsService {

    String URL_MOVIE_REVIEWS = "/movie/{movieId}/reviews";

    @GET(URL_MOVIE_REVIEWS)
    void getMovieReviews(
            @Path("movieId") long movieId,
            @Query("api_key") String apiKey,
            Callback<ReviewsResponse> reviewsResponse
    );

}
