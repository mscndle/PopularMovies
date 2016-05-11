package com.popularmovies.mcondle.popularmovies.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.popularmovies.mcondle.popularmovies.network.model.MovieLite;
import com.popularmovies.mcondle.popularmovies.network.model.SortOrder;
import com.popularmovies.mcondle.popularmovies.network.FetchMoviesTask;

import java.util.List;

/**
 * Created by mandeep.condle on 11/28/15.
 */
public class MostPopularFragment extends BaseFragment implements MoviesAsyncDelegate {

    private static final String TAG = MostPopularFragment.class.getSimpleName();

    public static MostPopularFragment newInstance() {
        return new MostPopularFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        updateMoviesList(SortOrder.MOST_POPULAR);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateMoviesList(SortOrder.MOST_POPULAR);
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
