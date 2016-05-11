package com.popularmovies.mcondle.popularmovies.util;

import android.content.Context;

/**
 * Created by mandeep.condle on 4/9/16.
 */
public interface ViewHolderClicked {

    /**
     * interface to handle clicks on the movie grid elements
     * @param context   the Context
     * @param position  the position of the data element in the adapter
     */
    void onViewHolderClicked(Context context, int position);

}
