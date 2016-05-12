package com.popularmovies.mcondle.popularmovies.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mscndle on 5/12/16.
 */
public class MoviesListResponse implements Parcelable {

    private long page;
    private ArrayList<MovieLite> moviesList;
    private long total_results;
    private long total_pages;

    public MoviesListResponse() {

    }

    public MoviesListResponse(long page,
                              ArrayList<MovieLite> moviesList,
                              long total_results,
                              long total_pages) {
        this.page = page;
        this.moviesList = moviesList;
        this.total_results = total_results;
        this.total_pages = total_pages;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public List<MovieLite> getMoviesList() {
        return moviesList;
    }

    public void setMoviesList(ArrayList<MovieLite> moviesList) {
        this.moviesList = moviesList;
    }

    public long getTotal_results() {
        return total_results;
    }

    public void setTotal_results(long total_results) {
        this.total_results = total_results;
    }

    public long getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(long total_pages) {
        this.total_pages = total_pages;
    }


    /********************************
     * implement parcelable methods *
     ********************************/

    protected MoviesListResponse(Parcel in) {
        page = in.readLong();
        if (in.readByte() == 0x01) {
            moviesList = new ArrayList<MovieLite>();
            in.readList(moviesList, MovieLite.class.getClassLoader());
        } else {
            moviesList = null;
        }
        total_results = in.readLong();
        total_pages = in.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(page);
        if (moviesList == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(moviesList);
        }
        dest.writeLong(total_results);
        dest.writeLong(total_pages);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MoviesListResponse> CREATOR = new Parcelable.Creator<MoviesListResponse>() {
        @Override
        public MoviesListResponse createFromParcel(Parcel in) {
            return new MoviesListResponse(in);
        }

        @Override
        public MoviesListResponse[] newArray(int size) {
            return new MoviesListResponse[size];
        }
    };


}
