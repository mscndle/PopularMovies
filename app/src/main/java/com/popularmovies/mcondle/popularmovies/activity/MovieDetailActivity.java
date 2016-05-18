package com.popularmovies.mcondle.popularmovies.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.fragment.MovieDetailFragment;
import com.popularmovies.mcondle.popularmovies.network.model.Movie;

/**
 * Created by mscndle on 5/15/16.
 */
public class MovieDetailActivity extends AppCompatActivity {

    private static final String MOVIE_KEY = "movie";
    private static final String MOVIE_FAV_KEY = "movieFav";

    private Movie movie;
    private boolean isFavorite;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        movie = getIntent().getParcelableExtra(MOVIE_KEY);
        isFavorite = getIntent().getBooleanExtra(MOVIE_FAV_KEY, false);

        if (savedInstanceState == null) {
            MovieDetailFragment fragment = new MovieDetailFragment();
            Bundle args = new Bundle();

            args.putParcelable(MOVIE_KEY, movie);
            args.putBoolean(MOVIE_FAV_KEY, isFavorite);
            fragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_container, fragment)
                    .commit();
        }
    }

}
