package com.popularmovies.mcondle.popularmovies.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.activity.GridMovieClicked;
import com.popularmovies.mcondle.popularmovies.network.MoviesClient;
import com.popularmovies.mcondle.popularmovies.network.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Adapter to get movieDetail data
 *
 * Created by mandeep.condle on 12/29/15.
 */
public class MoviesGridAdapter extends RecyclerView.Adapter<MoviesGridAdapter.ViewHolder> {

    private static final String TAG = MoviesGridAdapter.class.getSimpleName();

    private Context context;
    private GridMovieClicked gridMovieClicked;
    private List<Movie> movieList;

    public MoviesGridAdapter(Context context, GridMovieClicked gridMovieClicked, List<Movie> movieList) {
        this.context = context;
        this.gridMovieClicked = gridMovieClicked;
        this.movieList = movieList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        GridMovieClicked gridMovieClicked;
        MoviesGridAdapter adapter;

        public ViewHolder(final View itemView, GridMovieClicked gridMovieClicked, MoviesGridAdapter adapter) {
            super(itemView);

            this.imageView = (ImageView) itemView.findViewById(R.id.movie_img);
            this.gridMovieClicked = gridMovieClicked;
            this.adapter = adapter;

            itemView.setOnClickListener(this);
            itemView.setPadding(2, 2, 2, 2);    // padding is added here and in the RecyclerView for symmetry
        }

        @Override
        public void onClick(View v) {
            Movie movie = adapter.getItem(getAdapterPosition());
            gridMovieClicked.onGridMovieClicked(movie, false);
        }
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

        // inflate custom layout
        View view = inflater.inflate(R.layout.movie_item, parent, false);

        // return a new ViewHolder instance
        return new ViewHolder(view, gridMovieClicked, this);
    }

    /**
     * Populates data into the item through the viewHolder
     * @param viewHolder    the ViewHolder
     * @param position  the position
     */
    @Override
    public void onBindViewHolder(MoviesGridAdapter.ViewHolder viewHolder, int position) {
        // get the Movie model based on the position
        Movie movie = movieList.get(position);

        // set each item's views based on model and viewholder
        ImageView imageView = viewHolder.imageView;

        // set movieDetail poster as grid icon and textview as movieDetail title
        Picasso.with(context)
                .load(MoviesClient.BASE_POSTER_URL + movie.getPosterPath())
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        if (movieList == null) {
            return -1;
        }
        return movieList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    /**
     * Inserts a new item into the adapter on a predefined position
     * @param position  the position
     * @param movie the object
     */
    public void insert(int position, Movie movie) {
        movieList.add(position, movie);
        notifyItemInserted(position);
    }

    public Movie getItem(int position) {
        return movieList.get(position);
    }

    /**
     * Inserts a new items into the adapter
     * @param movie the object
     */
    public void insert(Movie movie) {
        movieList.add(movie);
        notifyDataSetChanged();
    }

    public void insert(List<Movie> movies) {
        for (Movie m : movies) {
            movieList.add(m);
        }
        notifyDataSetChanged();
    }

    public void remove(Movie movie) {
        int position = movieList.indexOf(movie);
        movieList.remove(position);
        notifyItemRemoved(position);
    }

    public void clear() {
        movieList.clear();
    }

}
