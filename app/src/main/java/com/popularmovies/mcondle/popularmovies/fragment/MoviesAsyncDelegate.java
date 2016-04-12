package com.popularmovies.mcondle.popularmovies.fragment;

import com.popularmovies.mcondle.popularmovies.model.MovieLite;

import java.util.List;

/**
 * All fragments should implement this so that the {@link com.popularmovies.mcondle.popularmovies.network.FetchMoviesTask }
 * can update the underlying adapter in that fragment
 *
 * Created by mandeep.condle on 3/5/16.
 */
public interface MoviesAsyncDelegate {

    void asyncComplete(List<MovieLite> movies);

}
