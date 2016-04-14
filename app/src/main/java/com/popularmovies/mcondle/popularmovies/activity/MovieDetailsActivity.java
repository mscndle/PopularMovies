package com.popularmovies.mcondle.popularmovies.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.model.Movie;
import com.popularmovies.mcondle.popularmovies.network.MoviesDbClient;
import com.squareup.picasso.Picasso;

/**
 * Created by mscndle on 1/2/16.
 */
public class MovieDetailsActivity extends AppCompatActivity {

    private static final String MOVIE_ID_KEY = "movieIdKey";

    private Toolbar toolbar;
    private ViewPager viewPager;

    private Movie movie;

    public static void startWith(Activity origin) {
        startWith(origin, null);
    }

    public static void startWith(Activity origin, Long movieId) {
        Intent intent = new Intent(origin, MovieDetailsActivity.class);
        if (movieId != null) {
            intent.putExtra(MOVIE_ID_KEY, movieId);
        }

        origin.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);



        long movieId = getIntent().getExtras().getLong(MOVIE_ID_KEY);
        getExtraMovieDetails(movieId);
        populateViews();
    }

    private void getExtraMovieDetails(long movieId) {
        FetchMovieDetailsTask task = new FetchMovieDetailsTask();

        try {
            movie = task.execute(movieId).get();
        } catch (Exception ie) {
            //
        }
    }

    private void populateViews() {
        // grab views
//        TextView movieTitle = (TextView) findViewById(R.id.movie_original_title);
//        ImageView movieDetailImg = (ImageView) findViewById(R.id.movie_detail_img);
//        TextView movieReleaseDate = (TextView) findViewById(R.id.movie_release_date);
//        TextView movieRunningTime = (TextView) findViewById(R.id.movie_running_time);
//        TextView movieRating = (TextView) findViewById(R.id.movie_rating);
//        TextView movieSynposis = (TextView) findViewById(R.id.movie_synopsis);
//
//        // fill title and image
//        movieTitle.setText(movie.getOriginalTitle());
//        Picasso.with(this)
//                .load(MoviesDbClient.API_BASE_POSTER + movie.getPosterPath())
//                .into(movieDetailImg);
//
//        // parse year from date
//        String[] dateArr = movie.getReleaseDate().split("-");
//        movieReleaseDate.setText(dateArr[0]);
//
//        String formattedRuntime = String.valueOf(movie.getRuntime()) + "minutes";
//        movieRunningTime.setText(formattedRuntime);
//
//        movieRating.setText(String.valueOf(movie.getVoteAverage() + "/10"));
//        movieSynposis.setText(movie.getOverview());
    }

    private void grabMovie(Movie movie) {
        this.movie = movie;
    }

    public class FetchMovieDetailsTask extends AsyncTask<Long, Void, Movie> {

        @Override
        protected Movie doInBackground(Long... params) {
            MoviesDbClient moviesDbClient = MoviesDbClient.getMoviesDbClient();
            return moviesDbClient.getMovieDetails(params[0]);
        }

        @Override
        protected void onPostExecute(Movie movie) {
            //
        }

    }




}
