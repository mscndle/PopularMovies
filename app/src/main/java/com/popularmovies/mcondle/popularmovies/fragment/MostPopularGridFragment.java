package com.popularmovies.mcondle.popularmovies.fragment;

import android.util.Log;

import com.popularmovies.mcondle.popularmovies.network.MoviesClient;
import com.popularmovies.mcondle.popularmovies.network.model.Movie;
import com.popularmovies.mcondle.popularmovies.network.model.MoviesListResponse;
import com.popularmovies.mcondle.popularmovies.network.service.MoviesListService;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by mandeep.condle on 11/28/15.
 */
public class MostPopularGridFragment extends BaseGridFragment {

    private static final String TAG = MostPopularGridFragment.class.getSimpleName();

    private MoviesListService moviesListService;

    public static MostPopularGridFragment newInstance() {
        return new MostPopularGridFragment();
    }

    /**
     * gets the list of popular movies
     */
    @Override
    protected void updateMoviesList() {
        moviesListService = MoviesClient.getInstance().getMoviesListService();
        moviesListService.getPopularMovies(MoviesClient.API_KEY, new Callback<MoviesListResponse>() {
            @Override
            public void success(MoviesListResponse moviesListResponse, Response response) {
                asyncComplete(moviesListResponse.getMoviesList());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, error.toString());
            }
        });

    }

    public void asyncComplete(List<Movie> movies) {
        moviesGridAdapter.clear();

        for (Movie m : movies) {
            moviesGridAdapter.insert(m);
        }
    }

}
