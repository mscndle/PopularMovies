package com.popularmovies.mcondle.popularmovies.activity;

import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.adapter.MoviesViewPagerAdapter;
import com.popularmovies.mcondle.popularmovies.fragment.MovieDetailsFragment;

public class MoviesHomeActivity extends AppCompatActivity {

    public static final String MOVIE_DETAIL_KEY = "MOVIE_DETAIL_KEY";

    ViewPager viewPager;
    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_home);

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.gridActivityFrameLayout, new MostPopularFragment())
//                    .commit();
//        }

        toolBar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolBar);

        viewPager = (ViewPager) findViewById(R.id.movies_view_pager);
        MoviesViewPagerAdapter moviesViewPagerAdapter = new MoviesViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(moviesViewPagerAdapter);

        PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.movies_pager_tabstrip);
//        pagerTabStrip.set

        setupListeners();
    }

    private void setupListeners() {

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                Toast.makeText(MoviesHomeActivity.this, "onPageScrolled(...) called from position: "
//                                + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(MoviesHomeActivity.this, "onPageSelected(...) called from position: "
                        + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                Toast.makeText(MoviesHomeActivity.this, "onPageScrollStateChanged(...) called from state: "
//                        + state, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //todo - delete if not using the listener approach to start details activity from movies fragments
    public void onMovieClicked(long movieId) {
        Bundle bundle = new Bundle();
        bundle.putLong(MOVIE_DETAIL_KEY, movieId);

        MovieDetailsFragment movieDetailsFragment = new MovieDetailsFragment();
        movieDetailsFragment.setArguments(bundle);

//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.gridActivityFrameLayout, movieDetailsFragment)
//                .addToBackStack(null)
//                .commit();
    }


}
