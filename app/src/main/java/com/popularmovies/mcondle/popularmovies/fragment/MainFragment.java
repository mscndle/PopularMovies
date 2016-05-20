package com.popularmovies.mcondle.popularmovies.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.activity.MainActivity;
import com.popularmovies.mcondle.popularmovies.adapter.MoviesViewPagerAdapter;
import com.popularmovies.mcondle.popularmovies.layout.SlidingTabLayout;

public class MainFragment extends Fragment {

    private static final int COLUMNS_PHONE = 2;
    private static final int COLUMNS_TABLET = 3;

    private static final int TAB_COUNT = 3;

    private Toolbar toolBar;
    private ViewPager viewPager;
    private SlidingTabLayout tabLayout;

    private MoviesViewPagerAdapter moviesViewPagerAdapter;
    private CharSequence tiles[] = {"POPULAR", "TOP RATED", "FAVORITES"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        toolBar = (Toolbar) rootView.findViewById(R.id.tool_bar);
        ((MainActivity) getActivity()).setSupportActionBar(toolBar);

        viewPager = (ViewPager) rootView.findViewById(R.id.movies_view_pager);
        moviesViewPagerAdapter = new MoviesViewPagerAdapter(getActivity().getSupportFragmentManager(), tiles, TAB_COUNT);
        viewPager.setAdapter(moviesViewPagerAdapter);

        tabLayout = (SlidingTabLayout) rootView.findViewById(R.id.sliding_tab_layout);
        tabLayout.setDistributeEvenly(true);
        tabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.indicator);
            }
        });
        tabLayout.setViewPager(viewPager);

        return rootView;
    }

}
