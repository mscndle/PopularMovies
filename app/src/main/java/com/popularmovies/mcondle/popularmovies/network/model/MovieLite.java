package com.popularmovies.mcondle.popularmovies.network.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandeep.condle on 2/27/16.
 */
public class MovieLite implements Parcelable {

    private static final String TAG = MovieLite.class.getSimpleName();

    private long id;
    private String title;
    private String poster_path;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return poster_path;
    }

    public void setPosterPath(String poster_path) {
        this.poster_path = poster_path;
    }

    public MovieLite() {
        //
    }

    public MovieLite(long id, String title, String poster_path) {
        this.id = id;
        this.title = title;
        this.poster_path = poster_path;
    }

    /**
     * Converts the expected jsonObject into a Movie object
     *
     * @param jsonObject returned from the api call
     * @return converted MovieLite object
     */
    public static MovieLite fromJson(JSONObject jsonObject) {
        MovieLite movieLite = new MovieLite();

        try {
            movieLite.id = jsonObject.getLong("id");
            movieLite.title = jsonObject.getString("title");
            movieLite.poster_path = jsonObject.getString("poster_path");

        } catch (JSONException jse) {
            Log.d(TAG, "JSONException in MovieLite.fromJson(...) " + jse.toString());
            return null;
        }

        return movieLite;
    }

    public static MovieLite fromCursor(Cursor cursor) {
        // todo
        return null;
    }

    /**
     * Converts the expected jsonArray into a list of Movie objects
     *
     * @param jsonArray returned from the api call
     * @return List of MovieLite objects
     */
    public static List<MovieLite> fromJson(JSONArray jsonArray) {
        List<MovieLite> movies = new ArrayList<>(jsonArray.length());

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                MovieLite m = MovieLite.fromJson(jsonArray.getJSONObject(i));
                movies.add(m);
            } catch (JSONException jse) {
                Log.d(TAG, "JSONException in MovieLite.fromJson(...) " + jse.toString());
                // don't exist if there's an issue with a particular json movie object. continue...
            }
        }

        return movies;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("movie_id: " + getId() + "\n");
        sb.append("movie_name: " + getTitle() + "\n");
        sb.append("movie_poster_path: " + getPosterPath() + "\n");

        return sb.toString();
    }

    /********************************
     * implement parcelable methods *
     ********************************/

    @Override
    public int describeContents() {
        return 0;   //todo
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Parcelable.Creator<MovieLite> CREATOR = new Parcelable.Creator<MovieLite>() {
        @Override
        public MovieLite createFromParcel(Parcel in) {
            return new MovieLite(in);
        }

        @Override
        public MovieLite[] newArray(int size) {
            return new MovieLite[size];
        }
    };

    // todo
    private MovieLite(Parcel in) {

    }


}