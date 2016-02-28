package com.popularmovies.mcondle.popularmovies.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.fragment.MovieDetailsFragment;
import com.popularmovies.mcondle.popularmovies.fragment.MoviesGridFragment;
import com.popularmovies.mcondle.popularmovies.model.Movie;
import com.popularmovies.mcondle.popularmovies.model.MovieLite;

public class MoviesGridActivity extends ActionBarActivity implements MoviesGridFragment.MoviesClickListener {

    public static final String MOVIE_DETAIL_KEY = "MOVIE_DETAIL_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_grid);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.gridActivityFrameLayout, new MoviesGridFragment())
                    .commit();
        }
    }

    public void onMovieClicked(long movieId) {
        Bundle bundle = new Bundle();
        bundle.putLong(MOVIE_DETAIL_KEY, movieId);

        MovieDetailsFragment movieDetailsFragment = new MovieDetailsFragment();
        movieDetailsFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.gridActivityFrameLayout, movieDetailsFragment)
                .addToBackStack(null)
                .commit();
    }
}
