package com.popularmovies.mcondle.popularmovies.fragment;

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

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.adapter.MoviesListAdapter;
import com.popularmovies.mcondle.popularmovies.model.MovieLite;
import com.popularmovies.mcondle.popularmovies.model.SortOrder;
import com.popularmovies.mcondle.popularmovies.network.MoviesDbClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mscndle on 11/28/15.
 */
public class MostPopularFragment extends Fragment {

    private SortOrder sortOrder;

    private MoviesListAdapter moviesListAdapter;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);

        if (savedInstanceState == null || !savedInstanceState.containsKey("moviesList")) {
            moviesList = new ArrayList<>();
        } else {
            moviesList = savedInstanceState.getParcelableArrayList("moviesList");
        }

        // set default sorting order
        sortOrder = SortOrder.MOST_POPULAR;
        // added here so that the movies list (based on sorting order) is preserved when coming back from the details page
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
            sortOrder = SortOrder.HIGHEST_RATED;

        } else if (id == R.id.action_sort_most_popular) {
            sortOrder = SortOrder.MOST_POPULAR;
        }

        updateMoviesList(sortOrder);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        updateMoviesList(sortOrder);

        View v = inflater.inflate(R.layout.fragment_movies_grid, container, false);
        moviesListAdapter = new MoviesListAdapter(getContext(), moviesList);

        GridView gridView = (GridView) v.findViewById(R.id.movies_grid_view);
        gridView.setAdapter(moviesListAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getContext(), "movie clicked", Toast.LENGTH_SHORT).show();
                MovieLite movie = moviesListAdapter.getItem(position);
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
        FetchMoviesTask fetchMoviesTask = new FetchMoviesTask();
        fetchMoviesTask.execute(sortOrder);
    }

    public class FetchMoviesTask extends AsyncTask<SortOrder, Void, List<MovieLite>> {

        private final String TAG = FetchMoviesTask.class.getSimpleName();

        protected List<MovieLite> doInBackground(SortOrder... params) {
            MoviesDbClient client = MoviesDbClient.getMoviesDbClient();

            switch (params[0]) {
                default:
                case MOST_POPULAR:
                    return client.getMoviesList(SortOrder.MOST_POPULAR);
                case HIGHEST_RATED:
                    return client.getMoviesList(SortOrder.HIGHEST_RATED);
            }
        }

        @Override
        public void onPostExecute(List<MovieLite> result) {
            if (result != null) {
                moviesListAdapter.clear();

                for (MovieLite m : result) {
                    moviesListAdapter.add(m);
                }
            }
        }

    }

}
