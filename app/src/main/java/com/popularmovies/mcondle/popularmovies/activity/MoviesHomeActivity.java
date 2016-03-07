package com.popularmovies.mcondle.popularmovies.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.adapter.MoviesViewPagerAdapter;
import com.popularmovies.mcondle.popularmovies.fragment.MovieDetailsFragment;
import com.popularmovies.mcondle.popularmovies.layout.SlidingTabLayout;
import com.popularmovies.mcondle.popularmovies.model.SortOrder;

public class MoviesHomeActivity extends AppCompatActivity {

    public static final String MOVIE_DETAIL_KEY = "MOVIE_DETAIL_KEY";

    ViewPager viewPager;
    Toolbar toolBar;
    MoviesViewPagerAdapter moviesViewPagerAdapter;
    SlidingTabLayout tabLayout;
    CharSequence tiles[] = {"POPULAR", "HIGHLY RATED", "FAVORITES"};
    int tabCount = 3;
    SortOrder sortOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_home);

        toolBar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolBar);

        viewPager = (ViewPager) findViewById(R.id.movies_view_pager);
        MoviesViewPagerAdapter moviesViewPagerAdapter =
                new MoviesViewPagerAdapter(getSupportFragmentManager(), tiles, tabCount);
        viewPager.setAdapter(moviesViewPagerAdapter);

        tabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tab_layout);
        tabLayout.setDistributeEvenly(true);
        tabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.indicator);
            }
        });

        tabLayout.setViewPager(viewPager);

        setupListeners();
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_movies_fragment, menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.action_sort_highest_rated) {
//            sortOrder = SortOrder.HIGHEST_RATED;
//
//        } else if (id == R.id.action_sort_most_popular) {
//            sortOrder = SortOrder.MOST_POPULAR;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    private void setupListeners() {
//
//        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
////                Toast.makeText(MoviesHomeActivity.this, "onPageScrolled(...) called from position: "
////                                + position, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                Toast.makeText(MoviesHomeActivity.this, "onPageSelected(...) called from position: "
//                        + position, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
////                Toast.makeText(MoviesHomeActivity.this, "onPageScrollStateChanged(...) called from state: "
////                        + state, Toast.LENGTH_SHORT).show();
//            }
//        });
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
