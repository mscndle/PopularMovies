package com.popularmovies.mcondle.popularmovies.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by mscndle on 2/28/16.
 */
public class HighlyRatedFragment extends Fragment {

    public static HighlyRatedFragment newInstance() {
        HighlyRatedFragment fragment = new HighlyRatedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }
}
