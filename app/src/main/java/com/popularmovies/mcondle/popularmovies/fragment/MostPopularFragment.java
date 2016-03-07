package com.popularmovies.mcondle.popularmovies.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.adapter.MoviesGridAdapter;
import com.popularmovies.mcondle.popularmovies.model.MovieLite;
import com.popularmovies.mcondle.popularmovies.model.SortOrder;
import com.popularmovies.mcondle.popularmovies.network.FetchMoviesTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mscndle on 11/28/15.
 */
public class MostPopularFragment extends Fragment implements MoviesAsyncDelegate {

    private static final String KEY_MOVIES_LIST = "moviesListPopular";

    private MoviesGridAdapter moviesGridAdapter;
    private ArrayList<MovieLite> moviesList;

    public static MostPopularFragment newInstance() {
        MostPopularFragment fragment = new MostPopularFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    public Context getContext() {
        return getActivity();
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        if (activity instanceof MoviesClickListener) {
//            moviesClickListener = (MoviesClickListener) activity;
//        } else {
//            throw new ClassCastException(activity.toString() +
//                    " must implement MostPopularFragment.MoviesClickListener");
//        }
//    }

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
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(KEY_MOVIES_LIST, moviesList);
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        updateMoviesList(SortOrder.MOST_POPULAR);

        View v = inflater.inflate(R.layout.fragment_movies_grid, container, false);
        moviesGridAdapter = new MoviesGridAdapter(getContext(), moviesList);

        GridView gridView = (GridView) v.findViewById(R.id.movies_grid_recycler_view);
        gridView.setAdapter(moviesGridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                MovieLite movie = moviesGridAdapter.getItem(position);
//                moviesClickListener.onMovieClicked(movie.getId());
            }
        });

        return v;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        updateMoviesList(sortOrder);
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        moviesList.clear();
//    }
//

    /**
     * called to get the movies list based on the sortOrder
     * @param sortOrder default, popular, latest
     */
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
