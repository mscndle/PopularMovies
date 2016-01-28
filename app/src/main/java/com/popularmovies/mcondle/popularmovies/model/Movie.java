package com.popularmovies.mcondle.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model retrieved from themoviedb.org
 * This will be used by the adapter
 *
 * Created by mscndle on 1/2/16.
 */
public class Movie implements Parcelable {

    private long id;
    private String title;
    private int[] genre_ids;
    private boolean adult;
    private String backdrop_path;
    private String overview;
    private String original_title;
    private String original_language;

    @Override
    public int describeContents() {
        return 0;   //TODO - complete this
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

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

    private Movie(Parcel in) {

    }

}

