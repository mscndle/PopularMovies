package com.popularmovies.mcondle.popularmovies.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

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
public class HighlyRatedFragment extends Fragment implements MoviesAsyncDelegate {

    private static final String KEY_MOVIES_LIST = "moviesListHighlyRated";

    private ArrayList<MovieLite> moviesList;
    private MoviesGridAdapter moviesGridAdapter;

    public static HighlyRatedFragment newInstance() {
        HighlyRatedFragment fragment = new HighlyRatedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    private Context getContext() {
        return getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null || !(savedInstanceState.containsKey(KEY_MOVIES_LIST))) {
            moviesList = new ArrayList<>();
        } else {
            moviesList = savedInstanceState.getParcelableArrayList(KEY_MOVIES_LIST);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(KEY_MOVIES_LIST, moviesList);
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        updateMoviesList(SortOrder.HIGHEST_RATED);

        View v = inflater.inflate(R.layout.fragment_movies_grid, container, false);
        moviesGridAdapter = new MoviesGridAdapter(getContext(), moviesList);

        GridView gridView = (GridView) v.findViewById(R.id.movies_grid_recycler_view);
        gridView.setAdapter(moviesGridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "movie clicked", Toast.LENGTH_SHORT);
            }
        });

        return v;
    }

    private void updateMoviesList(SortOrder sortOrder) {
        FetchMoviesTask fetchMoviesTask = new FetchMoviesTask(this);
        fetchMoviesTask.execute(sortOrder);
    }

    public void asyncComplete(List<MovieLite> movies) {
        moviesGridAdapter.clear();

        for (MovieLite m : movies) {
            moviesGridAdapter.add(m);
        }
    }

}
