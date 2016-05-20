package com.popularmovies.mcondle.popularmovies.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.fragment.MainFragment;
import com.popularmovies.mcondle.popularmovies.fragment.MovieDetailFragment;
import com.popularmovies.mcondle.popularmovies.network.model.Movie;

/**
 * Created by mscndle on 5/15/16.
 */
public class MainActivity extends AppCompatActivity implements GridMovieClicked {

    private static final String MOVIE_KEY = "movie";
    private static final String MOVIE_FAV_KEY = "movieFav";

    private boolean twoPane = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.main_fragment_container, new MainFragment())
//                    .commit();
//        }

        if (findViewById(R.id.movie_details_container) != null) {
            /** user is on a tablet */
            twoPane = true;

            if (savedInstanceState == null) {
                MovieDetailFragment fragment = new MovieDetailFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_details_container, fragment)
                        .commit();
            }
        }
    }

    @Override
    public void onGridMovieClicked(Movie movie, boolean isFavorite) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra(MOVIE_KEY, movie);
        intent.putExtra(MOVIE_FAV_KEY, isFavorite);

        startActivity(intent);
    }

}
