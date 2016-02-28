package com.popularmovies.mcondle.popularmovies.network;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.popularmovies.mcondle.popularmovies.model.Movie;
import com.popularmovies.mcondle.popularmovies.model.MovieLite;
import com.popularmovies.mcondle.popularmovies.model.SortOrder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles network requests to themoviedb.org and gets movie objects and poster images
 *
 * Created by mscndle on 1/7/16.
 */
public class MoviesDbClient {

    private static final String TAG = MoviesDbClient.class.getSimpleName();

    private static final String API_KEY = "39759d3e11a3b8d6194c19814150629c";

    public static final String API_BASE_DISCOVER_MOVIE = "api.themoviedb.org/3/discover/movie";
    public static final String API_BASE_MOVIE_DETAILS = "api.themoviedb.org/3/movie";
    public static final String API_BASE_POSTER = "http://image.tmdb.org/t/p/w185"; //using size w185 for now

    private static MoviesDbClient moviesDbClient;

    public static MoviesDbClient getMoviesDbClient() {
        if (moviesDbClient == null) {
            moviesDbClient = new MoviesDbClient();
        }

        return moviesDbClient;
    }

    private MoviesDbClient() {
        // singleton
    }

    private URL getDiscoverMovieUrl(SortOrder sortOrder) {
        Uri.Builder builder = new Uri.Builder();
        URL url = null;
        Uri uri;

        String sortOrderStr = "popularity.desc";
        if (sortOrder == SortOrder.HIGHEST_RATED) {
            sortOrderStr = "vote_average.desc";
        }

        try {
            uri = builder.scheme("http")
                    .encodedAuthority(API_BASE_DISCOVER_MOVIE)
                    .appendQueryParameter("sort_by", sortOrderStr)
                    .appendQueryParameter("api_key", API_KEY)
                    .build();

            url = new URL(uri.toString());

        } catch (MalformedURLException mue) {
            Log.d(TAG, mue.toString());
        }

        return url;
    }

    private URL getMovieDetailsUrl(long movieId) {
        Uri.Builder builder = new Uri.Builder();
        URL url = null;
        Uri uri;

        try {
            uri = builder.scheme("http")
                    .encodedAuthority(API_BASE_MOVIE_DETAILS)
                    .appendPath(String.valueOf(movieId))
                    .appendQueryParameter("api_key", API_KEY)
                    .build();

            url = new URL(uri.toString());

        } catch (IOException ioe) {
            Log.d(TAG, ioe.toString());
        }

        return url;
    }

    /**
     * @return  Most popular list of movies returned by the discover movie endpoint
     * this is also the default list loaded when
     */
    public List<MovieLite> getMoviesList(SortOrder sortOrder) {
        final String JSON_RESULTS = "results";
        JSONArray moviesJsonArray = new JSONArray();
        List<MovieLite> defaultMoviesList;

        try {
            JSONObject moviesJsonObject = new JSONObject(getMoviesListJson(sortOrder));
            moviesJsonArray = moviesJsonObject.getJSONArray(JSON_RESULTS);

        } catch (JSONException jse) {
            Log.d(TAG, "-----> getDefaultMoviesList()" + jse.toString());

        } finally {
            if (moviesJsonArray != null) {
                defaultMoviesList = MovieLite.fromJson(moviesJsonArray);
            } else {
                defaultMoviesList = new ArrayList<>();
            }
        }

        return defaultMoviesList;
    }

    /**
     * Private helper to get
     * @return  Json string of the moves list
     */
    @Nullable
    private String getMoviesListJson(SortOrder sortOrder) {
        String moviesJson = null;
        HttpURLConnection urlConnection = null;
        BufferedReader br = null;

        try {
            URL url = getDiscoverMovieUrl(sortOrder);
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

    public Movie getMovieDetails(long movieId) {
        Movie movie = new Movie();

        try {
            JSONObject jsonObject = new JSONObject(getMovieDetailsJson(movieId));
            movie = Movie.fromJson(jsonObject);

        } catch (JSONException jse) {
            Log.d(TAG, jse.toString());
        }

        return movie;
    }

    private String getMovieDetailsJson(long movieId) {
        HttpURLConnection urlConnection = null;
        BufferedReader br = null;
        String movie = null;

        try {
            URL url = getMovieDetailsUrl(movieId);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStreamReader reader = new InputStreamReader(urlConnection.getInputStream());

            br = new BufferedReader(reader);
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            movie = sb.toString();
            br.close();

        } catch (IOException ioe) {
            Log.d(TAG, ioe.toString());
        }

        if (urlConnection != null) {
            urlConnection.disconnect();
        }

        return movie;
    }







}
