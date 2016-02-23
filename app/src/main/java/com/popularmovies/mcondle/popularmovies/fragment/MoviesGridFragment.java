package com.popularmovies.mcondle.popularmovies.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.adapter.MoviesAdapter;
import com.popularmovies.mcondle.popularmovies.model.Movie;
import com.popularmovies.mcondle.popularmovies.model.SortOrder;
import com.popularmovies.mcondle.popularmovies.network.MoviesDbClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mscndle on 11/28/15.
 */
public class MoviesGridFragment extends Fragment {

    private MoviesClickListener moviesClickListener;

    public interface MoviesClickListener {
        void onMovieClicked(Movie movie);
    }

    private MoviesAdapter moviesAdapter;
    private ArrayList<Movie> moviesList;

    public MoviesGridFragment() {
    }

    public Context getContext() {
        return getActivity();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof MoviesClickListener) {
            moviesClickListener = (MoviesClickListener) activity;
        } else {
            throw new ClassCastException(activity.toString() +
                    " must implement MoviesGridFragment.MoviesClickListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);

        if (savedInstanceState == null || !savedInstanceState.containsKey("moviesList")) {
            moviesList = new ArrayList<>();
        } else {
            moviesList = savedInstanceState.getParcelableArrayList("moviesList");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("moviesList", moviesList);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_movies_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_sort_highest_rated) {
            updateMoviesList(SortOrder.HIGHEST_RATED);

        } else if (id == R.id.action_sort_most_popular) {
            updateMoviesList(SortOrder.MOST_POPULAR);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        updateMoviesList(SortOrder.MOST_POPULAR);

        View v = inflater.inflate(R.layout.fragment_movies_grid, container, false);
        moviesAdapter = new MoviesAdapter(getContext(), moviesList);

        GridView gridView = (GridView) v.findViewById(R.id.movies_grid_view);
        gridView.setAdapter(moviesAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getContext(), "movie clicked", Toast.LENGTH_SHORT).show();
                Movie movie = moviesAdapter.getItem(position);
                moviesClickListener.onMovieClicked(movie);
            }
        });

        return v;
    }

    /**
     * called to get the movies list based on the sortOrder
     * @param sortOrder default, popular, latest
     */
    private void updateMoviesList(SortOrder sortOrder) {
        FetchMoviesTask fetchMoviesTask = new FetchMoviesTask();
        fetchMoviesTask.execute(sortOrder);
    }

    public class FetchMoviesTask extends AsyncTask<SortOrder, Void, List<Movie>> {

        private final String TAG = FetchMoviesTask.class.getSimpleName();

        protected List<Movie> doInBackground(SortOrder... params) {
            MoviesDbClient client = new MoviesDbClient();

            switch (params[0]) {
                default:
                case MOST_POPULAR:
                    return client.getMoviesList(SortOrder.MOST_POPULAR);
                case HIGHEST_RATED:
                    return client.getMoviesList(SortOrder.HIGHEST_RATED);
            }
        }

        @Override
        public void onPostExecute(List<Movie> result) {
            if (result != null) {
                moviesAdapter.clear();

                for (Movie m : result) {
                    moviesAdapter.add(m);
                }
            }
        }

    }

}
