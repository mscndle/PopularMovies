package com.popularmovies.mcondle.popularmovies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.model.Movie;
import com.popularmovies.mcondle.popularmovies.network.MoviesDbClient;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Adapter to get movie data
 *
 * Created by mscndle on 12/29/15.
 */
public class MoviesAdapter extends ArrayAdapter<Movie> {

    private static final String TAG = MoviesAdapter.class.getSimpleName();

    private static class ViewHolder {
        ImageView imageView;
    }

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

        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.movie_item, null);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imageView = (ImageView) convertView.findViewById(R.id.movie_img);
        Picasso.with(getContext()).load(finalPosterPath).into(viewHolder.imageView);

        return convertView;
    }
}
