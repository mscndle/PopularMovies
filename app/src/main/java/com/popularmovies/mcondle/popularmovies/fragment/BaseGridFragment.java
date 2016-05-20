package com.popularmovies.mcondle.popularmovies.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.activity.MainActivity;
import com.popularmovies.mcondle.popularmovies.adapter.MoviesGridAdapter;
import com.popularmovies.mcondle.popularmovies.network.model.Movie;

import java.util.ArrayList;

/**
 * Created by mandeep.condle on 4/9/16.
 */
public abstract class BaseGridFragment extends Fragment {

    protected static final String KEY_MOVIES_LIST = "moviesListPopular";
    protected static final  GRID_COLUMNS_PHONE = 2;
    protected static final int GRID_COLUMNS_TABLET = 3;

    protected MoviesGridAdapter moviesGridAdapter;
    protected ArrayList<Movie> moviesList;

    protected View rootView;
    protected RecyclerView gridRecyclerView;

    public Context getContext() {
        return getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null || !savedInstanceState.containsKey(KEY_MOVIES_LIST)) {
            moviesList = new ArrayList<>();
        } else {
            moviesList = savedInstanceState.getParcelableArrayList(KEY_MOVIES_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_movies_grid, container, false);

        gridRecyclerView = (RecyclerView) rootView.findViewById(R.id.movies_grid_recycler_view);
        moviesGridAdapter = new MoviesGridAdapter(getContext(), ((MainActivity) getActivity()), moviesList);
        gridRecyclerView.setAdapter(moviesGridAdapter);
        gridRecyclerView.setPadding(2, 2, 2, 2);    // padding is added here and in the ViewHolder for symmetry

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), GRID_COLUMNS_PHONE);
        gridRecyclerView.setLayoutManager(gridLayoutManager);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateMoviesList();
    }

    protected abstract void updateMoviesList();
}
