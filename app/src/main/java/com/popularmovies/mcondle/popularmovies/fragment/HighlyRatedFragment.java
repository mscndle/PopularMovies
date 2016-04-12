package com.popularmovies.mcondle.popularmovies.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.popularmovies.mcondle.popularmovies.model.MovieLite;
import com.popularmovies.mcondle.popularmovies.model.SortOrder;
import com.popularmovies.mcondle.popularmovies.network.FetchMoviesTask;

import java.util.List;

/**
 * Created by mscndle on 2/28/16.
 */
public class HighlyRatedFragment extends BaseFragment implements MoviesAsyncDelegate {

    public static HighlyRatedFragment newInstance() {
        return new HighlyRatedFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        updateMoviesList(SortOrder.HIGHEST_RATED);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateMoviesList(SortOrder.HIGHEST_RATED);
    }

    /**
     * called to get the movies list based on the sortOrder
     * @param sortOrder default, popular, latest
     */

    @Override
    protected void updateMoviesList(SortOrder sortOrder) {
        FetchMoviesTask fetchMoviesTask = new FetchMoviesTask(this);
        fetchMoviesTask.execute(sortOrder);
    }

    public void asyncComplete(List<MovieLite> movies) {
        moviesGridAdapter.clear();

        for (MovieLite m : movies) {
            moviesGridAdapter.insert(m);
        }
    }
}
