package com.popularmovies.mcondle.popularmovies.network;

import android.net.Uri;
import android.util.Log;

import com.popularmovies.mcondle.popularmovies.model.Movie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Handles network requests to themoviedb.org and gets movie objects and poster images
 *
 * Created by mscndle on 1/7/16.
 */
public class MoviesDbClient {

    private static final String TAG = MoviesDbClient.class.getSimpleName();

    private static final String API_KEY = "39759d3e11a3b8d6194c19814150629c";

    private static final String API_BASE_MOVIE = "http://api.themoviedb.org/3/discover/movie";
    private static final String API_BASE_POSTER = "http://image.tmdb.org/t/p/w185"; //using size w185 for now
//    private static final String API_MOVIE_DISCOVER = API_BASE_MOVIE + "?sort_by=popularity.desc&api_key=" + API_KEY;

    public MoviesDbClient() {
        // TODO - check sample code if it's a good idea to call the endpoint in the constructor?
    }

    private URL getDiscoverMovieUrl() {
        Uri.Builder builder = new Uri.Builder();
        URL url = null;
        Uri uri;

        try {
            uri = builder.scheme("http")
                    .authority(API_BASE_MOVIE)
                    .appendQueryParameter("sort_by", "popularity.desc")
                    .appendQueryParameter("api_key", API_KEY)
                    .build();

            url = new URL(uri.toString());

        } catch (MalformedURLException mue) {
            Log.d(TAG, mue.toString());
        }

        return url;
    }

    /**
     * @return  Default list of movies returned by the discover movie endpoint
     * sorted by popularity
     */
    List<Movie> getDefaultMoviesList() {
        return null;
    }

    /**
     *
     * @return
     */
    String getDefaultMoviesListJson() {
        String moviesJson = null;
        HttpURLConnection urlConnection = null;
        BufferedReader br = null;

        try {
            URL url = getDiscoverMovieUrl();
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = urlConnection.getInputStream();
            br = new BufferedReader(new InputStreamReader(inputStream));

            if (inputStream == null) {
                return null;
            }

            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }

            if (sb.length() == 0) {
                return null;
            } else {
                moviesJson = sb.toString();
            }

        } catch (IOException ioe) {
            Log.d(TAG, ioe.toString());

        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if (br != null) {
                try {
                    br.close();
                } catch (IOException ioe) {
                    Log.d(TAG, "error closing stream " + ioe.toString());
                }
            }
        }

        return moviesJson;
    }







}
