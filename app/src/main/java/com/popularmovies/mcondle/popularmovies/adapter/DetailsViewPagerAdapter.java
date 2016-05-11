package com.popularmovies.mcondle.popularmovies.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.popularmovies.mcondle.popularmovies.fragment.MovieInfoFragment;
import com.popularmovies.mcondle.popularmovies.fragment.MovieReviewsFragment;
import com.popularmovies.mcondle.popularmovies.fragment.MovieTrailersFragment;

/**
 * Created by mandeep.condle on 5/4/16.
 */
public class DetailsViewPagerAdapter extends FragmentPagerAdapter {

    private int tabCount;
    private CharSequence titles[];

    public DetailsViewPagerAdapter(FragmentManager fm, CharSequence titles[], int tabCount) {
        super(fm);
        this.titles = titles;
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return MovieInfoFragment.newInstance();
            case 1:
                return MovieReviewsFragment.newInstance();
            case 2:
                return MovieTrailersFragment.newInstance();
            default:
                return null;
        }
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
