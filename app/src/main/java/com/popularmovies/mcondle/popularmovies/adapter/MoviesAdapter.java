package com.popularmovies.mcondle.popularmovies.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.model.Movie;

import java.util.List;

/**
 * Adapter to get movie data
 *
 * Created by mscndle on 12/29/15.
 */
public class MoviesAdapter extends BaseAdapter {

    private Context mContext;
    private List<Movie> mMoviesList;

    public MoviesAdapter(Context c, List<Movie> moviesList) {
        mContext = c;
        mMoviesList = moviesList;
    }

    public int getCount() {
        return mMoviesList.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            // if it's not recycled, initialize some attributes

        } else {
                mGridView = (GridView) convertView;

        }

        return imageView;
    }
}
