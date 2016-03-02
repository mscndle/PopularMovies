package com.popularmovies.mcondle.popularmovies.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.popularmovies.mcondle.popularmovies.fragment.FavoritesFragment;
import com.popularmovies.mcondle.popularmovies.fragment.HighlyRatedFragment;
import com.popularmovies.mcondle.popularmovies.fragment.MostPopularFragment;

/**
 * Created by mscndle on 2/28/16.
 */
public class MoviesViewPagerAdapter extends FragmentPagerAdapter {

    private int tabCount;
    private CharSequence titles[];

    public MoviesViewPagerAdapter(FragmentManager fragmentManager, CharSequence titles[], int tabCount) {
        super(fragmentManager);
        this.titles = titles;
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return MostPopularFragment.newInstance();
            case 1:
                return HighlyRatedFragment.newInstance();
            case 2:
                return FavoritesFragment.newInstance();
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
