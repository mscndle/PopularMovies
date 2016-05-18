package com.popularmovies.mcondle.popularmovies.activity;

import com.popularmovies.mcondle.popularmovies.network.model.Movie;

/**
 * Created by mandeep.condle on 4/9/16.
 */
public interface GridMovieClicked {

    /**
     * interface to handle clicks on grid ViewHolder elements
     * @param movie     the movie
     * @param isFavorite     the isFavorite
     */
    void onGridMovieClicked(Movie movie, boolean isFavorite);

}