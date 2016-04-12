package com.popularmovies.mcondle.popularmovies.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.adapter.MoviesGridAdapter;
import com.popularmovies.mcondle.popularmovies.model.MovieLite;
import com.popularmovies.mcondle.popularmovies.model.SortOrder;
import com.popularmovies.mcondle.popularmovies.network.FetchMoviesTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mscndle on 2/28/16.
 */
public class FavoritesFragment extends BaseFragment {

    public static FavoritesFragment newInstance() {
        FavoritesFragment fragment = new FavoritesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = super.onCreateView(inflater, container, savedInstanceState);
//        updateMoviesList(SortOrder.FAVORITES);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateMoviesList(SortOrder.HIGHEST_RATED);
    }

    protected void updateMoviesList(SortOrder sortOrder) {
//        todo - fetch movies from the DB
//        FetchMoviesTask fetchMoviesTask = new FetchMoviesTask(this);
//        fetchMoviesTask.execute(sortOrder);
    }
}
