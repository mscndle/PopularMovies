package com.popularmovies.mcondle.popularmovies.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.activity.MovieDetailsActivity;

/**
 * Created by mandeep.condle on 5/3/16.
 */
public class MovieReviewsFragment extends Fragment {

    private MovieDetailsActivity activity;
    private ViewPager viewPager;

    public static MovieReviewsFragment newInstance() {
        return new MovieReviewsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reviews, container, false);

        // assign views

        return rootView;
    }

    protected void setupViews() {

    }

    protected void populateData() {

    }

}
