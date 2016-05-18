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
public class Movie implements Parcelable {

    private static final String TAG = Movie.class.getSimpleName();

    private long id;
    private String title;
    private String poster_path;
    private String backdrop_path;
    private double vote_average;
    private double popularity;
    private String overview;
    private String release_date;

    public Movie() {
        // empty constructor
    }

    public Movie(long id,
                 String title,
                 String poster_path,
                 String backdrop_path,
                 double vote_average,
                 double popularity,
                 String overview,
                 String release_date) {

        this.id = id;
        this.title = title;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.vote_average = vote_average;
        this.popularity = popularity;
        this.overview = overview;
        this.release_date = release_date;
    }

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

    public String getBackdropPath() {
        return backdrop_path;
    }

    public void setBackdropPath(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public double getVoteAverage() {
        return vote_average;
    }

    public void setVoteAverage(double vote_average) {
        this.vote_average = vote_average;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public void setReleaseDate(String release_date) {
        this.release_date = release_date;
    }

    public static Movie fromCursor(Cursor cursor) {
        // todo
        return null;
    }

//    /**
//     * Converts the expected jsonObject into a MovieDetail object
//     *
//     * @param jsonObject returned from the api call
//     * @return converted Movie object
//     */
//    public static Movie fromJson(JSONObject jsonObject) {
//        Movie movie = new Movie();
//
//        try {
//            movie.id = jsonObject.getLong("id");
//            movie.title = jsonObject.getString("title");
//            movie.poster_path = jsonObject.getString("poster_path");
//
//        } catch (JSONException jse) {
//            Log.d(TAG, "JSONException in Movie.fromJson(...) " + jse.toString());
//            return null;
//        }
//
//        return movie;
//    }

//    /**
//     * Converts the expected jsonArray into a list of MovieDetail objects
//     *
//     * @param jsonArray returned from the api call
//     * @return List of Movie objects
//     */
//    public static List<Movie> fromJson(JSONArray jsonArray) {
//        List<Movie> movies = new ArrayList<>(jsonArray.length());
//
//        for (int i = 0; i < jsonArray.length(); i++) {
//            try {
//                Movie m = Movie.fromJson(jsonArray.getJSONObject(i));
//                movies.add(m);
//            } catch (JSONException jse) {
//                Log.d(TAG, "JSONException in Movie.fromJson(...) " + jse.toString());
//                // don't exist if there's an issue with a particular json movieDetail object. continue...
//            }
//        }
//
//        return movies;
//    }


    /********************************
     * implement parcelable methods *
     ********************************/

    protected Movie(Parcel in) {
        id = in.readLong();
        title = in.readString();
        poster_path = in.readString();
        backdrop_path = in.readString();
        vote_average = in.readDouble();
        popularity = in.readDouble();
        overview = in.readString();
        release_date = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(poster_path);
        dest.writeString(backdrop_path);
        dest.writeDouble(vote_average);
        dest.writeDouble(popularity);
        dest.writeString(overview);
        dest.writeString(release_date);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

}