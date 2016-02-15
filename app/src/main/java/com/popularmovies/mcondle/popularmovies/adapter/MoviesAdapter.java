package com.popularmovies.mcondle.popularmovies.adapter;

import android.app.ActionBar;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.model.Movie;
import com.popularmovies.mcondle.popularmovies.network.MoviesDbClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter to get movie data
 *
 * Created by mscndle on 12/29/15.
 */
public class MoviesAdapter extends ArrayAdapter<Movie> {

    private static final String TAG = MoviesAdapter.class.getSimpleName();

    // int constants to be used for initializing the LayoutParams
//    private static final int GRID_HEIGHT = 185;
//    private static final int GRID_WIDTH = 185;

    public MoviesAdapter(Context context, List<Movie> moviesList) {
        super(context, 0, moviesList);
    }

    /**
     * create a new ImageView for each item referenced by the adapter
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);
        String posterPath = movie.getPosterPath();
        String finalPosterPath = MoviesDbClient.API_BASE_POSTER + posterPath;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.movie_item, null);
        }

        ImageView movieView = (ImageView) convertView.findViewById(R.id.movie_img);
        Picasso.with(getContext()).load(finalPosterPath).into(movieView);

        return convertView;
    }
}
