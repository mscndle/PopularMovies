package com.popularmovies.mcondle.popularmovies.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.popularmovies.mcondle.popularmovies.fragment.MovieInfoFragment;
import com.popularmovies.mcondle.popularmovies.fragment.MovieReviewsFragment;
import com.popularmovies.mcondle.popularmovies.fragment.MovieTrailersFragment;
import com.popularmovies.mcondle.popularmovies.network.model.Movie;

/**
 * Created by mandeep.condle on 5/4/16.
 */
public class DetailsViewPagerAdapter extends FragmentPagerAdapter {

    private int tabCount;
    private CharSequence titles[];
    private Movie movie;
    private boolean isFavorite;

    public DetailsViewPagerAdapter(FragmentManager fm, CharSequence titles[], int tabCount,
                                   Movie movie, boolean isFavorite) {
        super(fm);
        this.titles = titles;
        this.tabCount = tabCount;
        this.movie = movie;
        this.isFavorite = isFavorite;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return MovieInfoFragment.newInstance(movie, isFavorite);
            case 1:
                return MovieReviewsFragment.newInstance(movie.getId());
            case 2:
                return MovieTrailersFragment.newInstance(movie.getId());
        }

        throw new IllegalArgumentException("postion has to be between 0 and 2");
    }

    @Override
    public int getCount() {
        return tabCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}
