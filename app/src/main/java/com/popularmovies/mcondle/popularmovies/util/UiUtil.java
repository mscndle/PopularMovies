package com.popularmovies.mcondle.popularmovies.util;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by mandeep.condle on 4/21/16.
 */
public class UiUtil {

    public static void showMovieDetailsErrorDialog(Context context) {
        String message = "Sorry, we were unable to load the movieDetail details at this time. Please try again later!";
        ProgressDialog.show(context, "Error", message);
    }

    public static void showMovieGridErrorDialog(Context context) {
        String message = "Sorry, we were unable to load the movies list at this time. Please try again later!";
        ProgressDialog.show(context, "Error", message);
    }

}
