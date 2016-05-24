package com.popularmovies.mcondle.popularmovies.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.fragment.MovieDetailFragment;
import com.popularmovies.mcondle.popularmovies.network.model.Movie;

/**
 * Created by mscndle on 5/15/16.
 */
public class MovieDetailActivity extends BaseActivity {

    private static final String MOVIE_KEY = "movie";
    private static final String MOVIE_FAV_KEY = "movieFav";

    private Movie movie;
    private boolean isFavorite;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        movie = getIntent().getParcelableExtra(MOVIE_KEY);
        isFavorite = getIntent().getBooleanExtra(MOVIE_FAV_KEY, false);

        if (movie == null) {
            setContentView(R.layout.dummy_layout);

        } else {
            setContentView(R.layout.activity_movie_detail);

            if (savedInstanceState == null) {
                MovieDetailFragment fragment = new MovieDetailFragment();
                Bundle args = new Bundle();

                args.putParcelable(MOVIE_KEY, movie);
                args.putBoolean(MOVIE_FAV_KEY, isFavorite);
                fragment.setArguments(args);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_details_container, fragment)
                        .commit();
            }
        }

        /** force phones to portrait mode and tablets to landscape */
        if (getResources().getBoolean(R.bool.portrait_only)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

}
