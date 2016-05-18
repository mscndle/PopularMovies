package com.popularmovies.mcondle.popularmovies.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mscndle on 5/15/16.
 */
public class TrailersResponse implements Parcelable {

    private long id;
    private List<Trailer> results;

    public TrailersResponse() {

    }

    public TrailersResponse(long id, List<Trailer> results) {
        this.id = id;
        this.results = results;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Trailer> getResults() {
        return results;
    }

    public void setResults(List<Trailer> results) {
        this.results = results;
    }

    /********************************
     * implement parcelable methods *
     ********************************/

    protected TrailersResponse(Parcel in) {
        id = in.readLong();
        if (in.readByte() == 0x01) {
            results = new ArrayList<Trailer>();
            in.readList(results, Trailer.class.getClassLoader());
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
        dest.writeLong(id);
        if (results == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(results);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TrailersResponse> CREATOR = new Parcelable.Creator<TrailersResponse>() {
        @Override
        public TrailersResponse createFromParcel(Parcel in) {
            return new TrailersResponse(in);
        }

        @Override
        public TrailersResponse[] newArray(int size) {
            return new TrailersResponse[size];
        }
    };

}
