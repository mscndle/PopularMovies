package com.popularmovies.mcondle.popularmovies.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.popularmovies.mcondle.popularmovies.fragment.MovieInfoFragment;
import com.popularmovies.mcondle.popularmovies.fragment.MovieReviewsFragment;
import com.popularmovies.mcondle.popularmovies.fragment.MovieTrailersFragment;
import com.popularmovies.mcondle.popularmovies.network.model.Movie;
import com.popularmovies.mcondle.popularmovies.network.service.MovieTrailersService;

/**
 * Created by mandeep.condle on 5/4/16.
 */
public class DetailsViewPagerAdapter extends FragmentStatePagerAdapter {

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
//                if (movie != null) {
                    return MovieInfoFragment.newInstance(movie, isFavorite);
//                } else {
//                    return new MovieInfoFragment();
//                }

            case 1:
//                if (movie != null) {
                    return MovieReviewsFragment.newInstance(movie.getId());
//                } else {
//                    return new MovieReviewsFragment();
//                }

            case 2:
//                if (movie != null) {
                    return MovieTrailersFragment.newInstance(movie.getId());
//                } else {
//                    return new MovieTrailersFragment();
//                }
        }

        throw new IllegalArgumentException("postion has to be between 0 and 2");
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
    }

    public void setItem(int position) {

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
