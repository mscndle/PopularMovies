package com.popularmovies.mcondle.popularmovies.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.adapter.MoviesViewPagerAdapter;
import com.popularmovies.mcondle.popularmovies.layout.SlidingTabLayout;
import com.popularmovies.mcondle.popularmovies.model.SortOrder;

public class MoviesHomeActivity extends AppCompatActivity {

    private static final int TAB_COUNT = 3;

    private Toolbar toolBar;
    private ViewPager viewPager;
    private SlidingTabLayout tabLayout;

    private MoviesViewPagerAdapter moviesViewPagerAdapter;
    private CharSequence tiles[] = {"POPULAR", "HIGHLY RATED", "FAVORITES"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_home);

        toolBar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolBar);

        viewPager = (ViewPager) findViewById(R.id.movies_view_pager);
        moviesViewPagerAdapter = new MoviesViewPagerAdapter(getSupportFragmentManager(), tiles, TAB_COUNT);
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
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
