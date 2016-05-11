package com.popularmovies.mcondle.popularmovies.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.popularmovies.mcondle.popularmovies.R;

/**
 * Created by mandeep.condle on 5/2/16.
 */
public class ReviewFragment extends Fragment {

    View rootView;
    TextView reviewAuthor;
    TextView reviewContent;

    // todo - should reviews data be passed into this fragment from the adapter?
    public static ReviewFragment newInstance() {
        return new ReviewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_review, container, false);
        reviewAuthor = (TextView) rootView.findViewById(R.id.review_author);
        reviewContent = (TextView) rootView.findViewById(R.id.review_content);

        return rootView;
    }

    @Override
    public void onResume() {
        // todo - grab content
    }

}
