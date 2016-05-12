package com.popularmovies.mcondle.popularmovies.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.popularmovies.mcondle.popularmovies.network.MoviesClient;
import com.popularmovies.mcondle.popularmovies.network.model.MovieLite;
import com.popularmovies.mcondle.popularmovies.network.model.MoviesListResponse;
import com.popularmovies.mcondle.popularmovies.network.model.SortOrder;
import com.popularmovies.mcondle.popularmovies.network.FetchMoviesTask;
import com.popularmovies.mcondle.popularmovies.network.service.MoviesListService;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by mandeep.condle on 11/28/15.
 */
//public class MostPopularFragment extends BaseFragment implements MoviesAsyncDelegate {
public class MostPopularFragment extends BaseFragment {

    private static final String TAG = MostPopularFragment.class.getSimpleName();

    private MoviesListService moviesListService;

    public static MostPopularFragment newInstance() {
        return new MostPopularFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateMoviesList();
    }

    /**
     * gets the list of popular movies
     */
    @Override
    protected void updateMoviesList() {
        moviesListService = MoviesClient.getInstance().getMoviesListService();
//        FetchMoviesTask fetchMoviesTask = new FetchMoviesTask(this);
//        fetchMoviesTask.execute(sortOrder);
        moviesListService.getPopularMovies(MoviesClient.API_KEY, new Callback<MoviesListResponse>() {
            @Override
            public void success(MoviesListResponse moviesListResponse, Response response) {
                asyncComplete(moviesListResponse.getMoviesList());
//                moviesGridAdapter.insert(movieLites);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, error.toString());
            }
        });

    }

    public void asyncComplete(List<MovieLite> movies) {
        moviesGridAdapter.clear();

        for (MovieLite m : movies) {
            moviesGridAdapter.insert(m);
        }
    }
}
