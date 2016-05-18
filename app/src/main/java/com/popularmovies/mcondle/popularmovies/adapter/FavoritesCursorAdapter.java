package com.popularmovies.mcondle.popularmovies.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.db.FavoritesContract;
import com.popularmovies.mcondle.popularmovies.activity.GridMovieClicked;
import com.popularmovies.mcondle.popularmovies.network.MoviesClient;
import com.popularmovies.mcondle.popularmovies.network.model.Movie;
import com.squareup.picasso.Picasso;

/**
 * Cursor adapter used to populate the favorites movies grid based on the underlying SQLiteDatabase
 *
 * Created by mandeep.condle on 4/20/16.
 */
public class FavoritesCursorAdapter extends CursorRecyclerViewAdapter<FavoritesCursorAdapter.ViewHolder> {

    private Context context;
    private GridMovieClicked gridMovieClicked;

    public FavoritesCursorAdapter(Context context, GridMovieClicked gridMovieClicked, Cursor cursor) {
        super(context, cursor);
        this.context = context;
        this.gridMovieClicked = gridMovieClicked;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        GridMovieClicked gridMovieClicked;
        FavoritesCursorAdapter adapter;

        public ViewHolder(final View itemView, GridMovieClicked gridMovieClicked, FavoritesCursorAdapter adapter) {
            super(itemView);

            this.imageView = (ImageView) itemView.findViewById(R.id.movie_img);
            this.gridMovieClicked = gridMovieClicked;

            itemView.setOnClickListener(this);
            itemView.setPadding(2, 2, 2, 2);    // padding is added here and in the RecyclerView for symmetry
        }

        @Override
        public void onClick(View v) {
            Snackbar.make(v, "Not yet implemented", Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public FavoritesCursorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_item, parent, false);

        return new ViewHolder(view, gridMovieClicked, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {

        // grab Movie params from cursor
        int movieId = cursor.getInt(cursor.getColumnIndex(FavoritesContract.FavoritesEntry.COLUMN_MOVIE_ID));
        String moviePosterPath = cursor.getString(cursor.getColumnIndex(FavoritesContract.FavoritesEntry.COLUMN_MOVIE_POSTER_PATH));

        ImageView imageView = viewHolder.imageView;

        Picasso.with(context)
                .load(MoviesClient.BASE_POSTER_URL + moviePosterPath)
                .into(imageView);

    }


}
