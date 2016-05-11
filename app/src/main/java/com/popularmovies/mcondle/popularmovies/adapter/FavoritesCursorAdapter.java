package com.popularmovies.mcondle.popularmovies.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.db.FavoritesContract;
import com.popularmovies.mcondle.popularmovies.network.MoviesClient;
import com.squareup.picasso.Picasso;

/**
 * Cursor adapter used to populate the favorites movies grid based on the underlying SQLiteDatabase
 *
 * Created by mandeep.condle on 4/20/16.
 */
public class FavoritesCursorAdapter extends CursorRecyclerViewAdapter<FavoritesCursorAdapter.ViewHolder> {

    private Context context;

    public FavoritesCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;
//        ViewHolderClicked viewHolderClicked;

        public ViewHolder(final View itemView) {
            super(itemView);

            this.imageView = (ImageView) itemView.findViewById(R.id.movie_img);
            this.textView = (TextView) itemView.findViewById(R.id.movie_original_title);
//            this.viewHolderClicked = viewHolderClicked;

            itemView.setOnClickListener(this);
            itemView.setPadding(2, 2, 2, 2);    // padding is added here and in the RecyclerView for symmetry
        }

        @Override
        public void onClick(View v) {
//            viewHolderClicked.onViewHolderClicked(imageView.getContext(), getAdapterPosition());
            Toast.makeText(imageView.getContext(), "Favorite movie clicked :)", Toast.LENGTH_SHORT).show();
        }
    }

//    @Override
//    public void onViewHolderClicked(Context context, int position) {
//        long movieId =
//    }

    @Override
    public FavoritesCursorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {

        // grab MovieLite params from cursor
        int movieId = cursor.getInt(cursor.getColumnIndex(FavoritesContract.FavoritesEntry.COLUMN_MOVIE_ID));
        String movieTitle = cursor.getString(cursor.getColumnIndex(FavoritesContract.FavoritesEntry.COLUMN_MOVIE_TITLE));
        String moviePosterPath = cursor.getString(cursor.getColumnIndex(FavoritesContract.FavoritesEntry.COLUMN_MOVIE_POSTER_PATH));

        ImageView imageView = viewHolder.imageView;
        TextView textView = viewHolder.textView;

        Picasso.with(context)
                .load(MoviesClient.API_BASE_POSTER + moviePosterPath)
                .into(imageView);

        textView.setText(movieTitle);
    }

}
