package com.popularmovies.mcondle.popularmovies.network;

import java.net.URL;

/**
 * Handles making network requests to themoviedb.org and retrieving movie objects
 *
 * Created by mscndle on 1/7/16.
 */
public class MoviesDbClient {

    private static final String API_BASE_MOVIE = "";
    private static final String API_BASE_POSTER = "http://image.tmdb.org/t/p/";

    // FIXME - ideally shouldn't be stored here; find a better place
    private static final String API_KEY = "39759d3e11a3b8d6194c19814150629c";

    private URL mUrl;

    public MoviesDbClient(String url) {
        mUrl = new URL(url);
    }


}
