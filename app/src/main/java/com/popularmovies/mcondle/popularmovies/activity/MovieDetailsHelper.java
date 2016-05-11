package com.popularmovies.mcondle.popularmovies.activity;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;

/**
 * Helper handles the FAB and favorite display
 *
 * Created by mandeep.condle on 4/27/16.
 */
public class MovieDetailsHelper {

    private FloatingActionButton fab;
    private Context context;
    private boolean isFavorite;

    public MovieDetailsHelper(Context context, FloatingActionButton fab, boolean isFavorite) {
        this.context = context;
        this.isFavorite = isFavorite;
        this.fab = fab;
    }

    protected void toggleFavorite() {
        if (isFavorite) {
            // remove from favorites


        } else {
            // add to favorites


        }
    }

}
