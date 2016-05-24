package com.popularmovies.mcondle.popularmovies.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.fragment.MainFragment;
import com.popularmovies.mcondle.popularmovies.fragment.MovieDetailFragment;
import com.popularmovies.mcondle.popularmovies.network.MoviesClient;
import com.popularmovies.mcondle.popularmovies.network.model.Movie;
import com.popularmovies.mcondle.popularmovies.network.model.MoviesListResponse;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by mscndle on 5/15/16.
 */
public class MainActivity extends BaseActivity implements GridMovieClicked {

    private static final String TAG = MainActivity.class.getSimpleName();

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
                MovieDetailFragment detailFrag = new MovieDetailFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_details_container, detailFrag)
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

    @Override
    public void onGridMovieClicked(Movie movie, boolean isFavorite) {

        if (twoPane) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_details_container, MovieDetailFragment.newInstance(movie, isFavorite))
                    .commit();

        } else {
            Intent intent = new Intent(this, MovieDetailActivity.class);
            intent.putExtra(MOVIE_KEY, movie);
            intent.putExtra(MOVIE_FAV_KEY, isFavorite);

            startActivity(intent);
        }
    }

}
