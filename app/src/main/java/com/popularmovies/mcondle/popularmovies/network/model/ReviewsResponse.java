package com.popularmovies.mcondle.popularmovies.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mscndle on 5/15/16.
 */
public class ReviewsResponse implements Parcelable {

    private int page;
    private int total_pages;
    private int total_results;
    private List<Review> results;

    public ReviewsResponse() {
        // empty constructor
    }

    public ReviewsResponse(int page,
                           int total_pages,
                           int total_results,
                           List<Review> results) {
        this.page = page;
        this.total_pages = total_pages;
        this.total_results = total_results;
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return total_pages;
    }

    public void setTotalPages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotalResults() {
        return total_results;
    }

    public void setTotalResults(int total_results) {
        this.total_results = total_results;
    }

    public List<Review> getResults() {
        return results;
    }

    public void setResults(List<Review> results) {
        this.results = results;
    }

    /********************************
     * implement parcelable methods *
     ********************************/

    protected ReviewsResponse(Parcel in) {
        page = in.readInt();
        total_pages = in.readInt();
        total_results = in.readInt();
        if (in.readByte() == 0x01) {
            results = new ArrayList<Review>();
            in.readList(results, Review.class.getClassLoader());
        } else {
            results = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(page);
        dest.writeInt(total_pages);
        dest.writeInt(total_results);
        if (results == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(results);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ReviewsResponse> CREATOR = new Parcelable.Creator<ReviewsResponse>() {
        @Override
        public ReviewsResponse createFromParcel(Parcel in) {
            return new ReviewsResponse(in);
        }

        @Override
        public ReviewsResponse[] newArray(int size) {
            return new ReviewsResponse[size];
        }
    };

}
