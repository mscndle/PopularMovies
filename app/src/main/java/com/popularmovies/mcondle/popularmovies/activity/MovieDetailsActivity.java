package com.popularmovies.mcondle.popularmovies.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.adapter.DetailsViewPagerAdapter;
import com.popularmovies.mcondle.popularmovies.fragment.MovieInfoFragment;
import com.popularmovies.mcondle.popularmovies.fragment.MovieReviewsFragment;
import com.popularmovies.mcondle.popularmovies.fragment.MovieTrailersFragment;
import com.popularmovies.mcondle.popularmovies.layout.SlidingTabLayout;
import com.popularmovies.mcondle.popularmovies.network.Urls;
import com.popularmovies.mcondle.popularmovies.network.model.Movie;
import com.popularmovies.mcondle.popularmovies.network.MoviesClient;
import com.popularmovies.mcondle.popularmovies.network.service.MovieDetailsService;
import com.popularmovies.mcondle.popularmovies.util.UiUtil;
import com.squareup.picasso.Picasso;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by mandeep.condle on 1/2/16.
 */
public class MovieDetailsActivity extends AppCompatActivity {

    private static final String TAG = MovieDetailsActivity.class.getSimpleName();
    private static final String MOVIE_ID_KEY = "movieIdKey";
    private static final String MOVIE_FAV = "movieFavorite";
    private static final int TAB_COUNT = 3;

    private CollapsingToolbarLayout collapsingToolbar;
    private Toolbar toolbar;
    private ImageView movieBackdrop;

    private MovieInfoFragment movieInfoFragment;
    private MovieReviewsFragment movieReviewsFragment;
    private MovieTrailersFragment movieTrailersFragment;

    private ViewPager viewPager;
    private SlidingTabLayout slidingTabLayout;

    private DetailsViewPagerAdapter viewPagerAdapter;
    private CharSequence tiles[] = {"INFO", "REVIEWS", "TRAILERS"};

    protected Movie movie;
    private long movieId;
    private boolean isFavorite;

    MovieDetailsService movieDetailsService;

    public static void startWith(Activity origin) {
        startWith(origin, null);
    }

    public static void startWith(Activity origin, Long movieId) {
        startWith(origin, movieId, false);
    }

    public static void startWith(Activity origin, Long movieId, boolean isFavorite) {
        Intent intent = new Intent(origin, MovieDetailsActivity.class);
        intent.putExtra(MOVIE_FAV, isFavorite);

        if (movieId != null) {
            intent.putExtra(MOVIE_ID_KEY, movieId);
        }

        origin.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        toolbar = (Toolbar) findViewById(R.id.details_tool_bar);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.details_view_pager);
        viewPagerAdapter = new DetailsViewPagerAdapter(getSupportFragmentManager(), tiles, TAB_COUNT);
        viewPager.setAdapter(viewPagerAdapter);

        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.details_sliding_tab_layout);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.indicator);
            }
        });
        slidingTabLayout.setViewPager(viewPager);

        movieId = getIntent().getExtras().getLong(MOVIE_ID_KEY);
        isFavorite = getIntent().getExtras().getBoolean(MOVIE_FAV);

        setupViews();
    }

    @Override
    public void onResume() {
        super.onResume();

        getExtraMovieDetails();
        handleListeners();
    }

    private void getExtraMovieDetails() {

        movieDetailsService = MoviesClient.getInstance().getMovieDetailsService();
        movieDetailsService.getMovieDetails(movieId, new Callback<Movie>() {
            @Override
            public void success(Movie movie, Response response) {
                MovieDetailsActivity.this.movie = movie;
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "MovieDetailsFragment#getExtraMovieDetails() "  + error.toString());
            }
        });
    }

    private void setupViews() {
        movieBackdrop = (ImageView) findViewById(R.id.poster_backdrop);
//
//        movieInfoFragment.setupViews();
//        movieReviewsFragment.setupViews();
//        movieTrailersFragment.setupViews();
    }

    private void populateData() {
        collapsingToolbar.setTitle(movie.getTitle());
        Picasso.with(this)
                .load(Urls.API_BASE_POSTER + movie.getBackdropPath())
                .fit().into(movieBackdrop);

//        movieInfoFragment.populateData();
//        movieReviewsFragment.populateData();
//        movieTrailersFragment.populateData();
    }

    private void handleListeners() {

    }

//    public class FetchMovieDetailsTask extends AsyncTask<Long, Void, Movie> {
//
//        @Override
//        protected Movie doInBackground(Long... params) {
//            MovieDetailsService movieDetailsService = MoviesClient.getInstance().getMovieDetailsService();
////            return moviesClient.getMovieDetails(params[0]);
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Movie movie) {
//            MovieDetailsActivity.this.movie = movie;
//            populateData();
//
//            Toast.makeText(MovieDetailsActivity.this,
//                    MovieDetailsActivity.this.movie.getTitle(),
//                    Toast.LENGTH_SHORT).show();
//        }
//
//    }

}
