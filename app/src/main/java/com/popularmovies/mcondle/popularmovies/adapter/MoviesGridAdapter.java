package com.popularmovies.mcondle.popularmovies.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.model.MovieLite;
import com.popularmovies.mcondle.popularmovies.network.MoviesDbClient;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Adapter to get movie data
 *
 * Created by mscndle on 12/29/15.
 */
public class MoviesGridAdapter extends RecyclerView.Adapter<MoviesGridAdapter.ViewHolder> {

    private static final String TAG = MoviesGridAdapter.class.getSimpleName();

    private List<MovieLite> movieLiteList;
    private Context context;

//    public static interface ViewHolderClicks {
//        public void onClick(View caller);
//    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;
//        ViewHolderClicks viewHolderClicks;

        public ViewHolder(final View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.movie_img);
            this.textView = (TextView) itemView.findViewById(R.id.movie_original_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(imageView.getContext(), "To be implemented", Toast.LENGTH_SHORT).show();
        }
    }

    public MoviesGridAdapter(Context context, List<MovieLite> movieLiteList) {
        this.context = context;
        this.movieLiteList = movieLiteList;
    }

    /**
     * Inflates the layout of a single item from xml and returns the ViewHolder
     * @param parent the parent
     * @param viewType the viewType
     * @return  MoviesGridAdapter.ViewHolder
     */
    @Override
    public MoviesGridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // grab inflater to inflate ViewHolder based on view id
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // inflate the custom view
        View movieLiteView = inflater.inflate(R.layout.movie_item, parent, false);

        // return a new ViewHolder instance
        return new ViewHolder(movieLiteView);
    }

    /**
     * Populates data into the item through the viewHolder
     * @param viewHolder    the ViewHolder
     * @param position  the position
     */
    @Override
    public void onBindViewHolder(MoviesGridAdapter.ViewHolder viewHolder, int position) {
        // get the MovieLite model based on the position
        MovieLite movieLite = movieLiteList.get(position);

        // set each item's views based on model and viewholder
        ImageView imageView = viewHolder.imageView;
        TextView textView = viewHolder.textView;

        // set movie poster as grid icon and textview as movie title
        Picasso.with(context)
                .load(MoviesDbClient.API_BASE_POSTER + movieLite.getPosterPath())
                .into(imageView);

        textView.setText(movieLite.getTitle());
    }

    @Override
    public int getItemCount() {
        if (movieLiteList == null) {
            return -1;
        }
        return movieLiteList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    /**
     * Inserts a new item into the adapter on a predefined position
     * @param position  the position
     * @param movieLite the object
     */
    public void insert(int position, MovieLite movieLite) {
        movieLiteList.add(position, movieLite);
        notifyItemInserted(position);
    }

    /**
     * Inserts a new items into the adapter
     * @param movieLite the object
     */
    public void insert(MovieLite movieLite) {
        movieLiteList.add(movieLite);
        notifyDataSetChanged();
    }

    public void remove(MovieLite movieLite) {
        int position = movieLiteList.indexOf(movieLite);
        movieLiteList.remove(position);
        notifyItemRemoved(position);
    }

    public void clear() {
        movieLiteList.clear();
    }
}
