package com.popularmovies.mcondle.popularmovies.network;

import android.support.annotation.NonNull;

import com.popularmovies.mcondle.popularmovies.fragment.MovieTrailersFragment;
import com.popularmovies.mcondle.popularmovies.network.service.MovieReviewsService;
import com.popularmovies.mcondle.popularmovies.network.service.MovieTrailersService;
import com.popularmovies.mcondle.popularmovies.network.service.MoviesListService;

import retrofit.RestAdapter;

/**
 * Handles network requests to themoviedb.org and gets movieDetail objects and poster images
 *
 * Created by mandeep.condle on 1/7/16.
 */
public class MoviesClient {

    private static final String TAG = MoviesClient.class.getSimpleName();

    // PLEASE INSERT API_KEY HERE
    public static final String API_KEY = "39759d3e11a3b8d6194c19814150629c";

    private static final String BASE_URL = "http://api.themoviedb.org/3";
    public static final String BASE_POSTER_URL = "http://image.tmdb.org/t/p/w185"; //using size w185 for now

    MoviesListService moviesListService;
    MovieReviewsService movieReviewsService;
    MovieTrailersService movieTrailersService;

    private static RestAdapter restAdapter;
    private static MoviesClient moviesClient;

    private MoviesClient() {
        // singleton
    }

    @NonNull
    public static MoviesClient getInstance() {
        if (moviesClient == null) {
            moviesClient = new MoviesClient();
        }
        if (restAdapter == null) {
            initRestAdapter();
        }

        return moviesClient;
    }

    private static void initRestAdapter() {
        restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                .build();
    }

    @NonNull
    public MoviesListService getMoviesListService() {
        if (moviesListService == null) {
            moviesListService = restAdapter.create(MoviesListService.class);
        }
        return moviesListService;
    }

    @NonNull
    public MovieReviewsService getMovieReviewsService() {
        if (movieReviewsService == null) {
            movieReviewsService = restAdapter.create(MovieReviewsService.class);
        }
        return movieReviewsService;
    }

    @NonNull
    public MovieTrailersService getMovieTrailersService() {
        if (movieTrailersService == null) {
            movieTrailersService = restAdapter.create(MovieTrailersService.class);
        }
        return movieTrailersService;
    }

}
