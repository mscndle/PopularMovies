package com.popularmovies.mcondle.popularmovies.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mscndle on 5/12/16.
 */
public class MoviesListResponse implements Parcelable {

    private long page;
    private ArrayList<Movie> results;
    private long total_results;
    private long total_pages;

    public MoviesListResponse() {

    }

    public MoviesListResponse(long page,
                              ArrayList<Movie> results,
                              long total_results,
                              long total_pages) {
        this.page = page;
        this.results = results;
        this.total_results = total_results;
        this.total_pages = total_pages;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public List<Movie> getMoviesList() {
        return results;
    }

    public void setMoviesList(ArrayList<Movie> results) {
        this.results = results;
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
            results = new ArrayList<Movie>();
            in.readList(results, Movie.class.getClassLoader());
        } else {
            results = null;
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
        if (results == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(results);
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
