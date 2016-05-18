package com.popularmovies.mcondle.popularmovies.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.popularmovies.mcondle.popularmovies.fragment.FavoritesFragment;
import com.popularmovies.mcondle.popularmovies.fragment.MostPopularGridFragment;
import com.popularmovies.mcondle.popularmovies.fragment.TopRatedGridFragment;

/**
 * FragmentPagerAdapter that provides the grid fragments on the landing page
 *
 * Created by mandeep.condle on 2/28/16.
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
                return MostPopularGridFragment.newInstance();
            case 1:
                return TopRatedGridFragment.newInstance();
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
