package com.popularmovies.mcondle.popularmovies.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by mscndle on 2/28/16.
 */
public class FavoritesFragment extends Fragment {

    public static FavoritesFragment newInstance() {
        FavoritesFragment fragment = new FavoritesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }
}
