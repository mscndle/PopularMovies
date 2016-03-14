package com.popularmovies.mcondle.popularmovies.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(final View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.movie_img);
            this.textView = (TextView) itemView.findViewById(R.id.movie_original_title);

        }
    }

    public MoviesGridAdapter(Context context, List<MovieLite> movieLiteList) {
        this.context = context;
        this.movieLiteList = movieLiteList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // grab inflater to inflate ViewHolder based on view id
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // inflate the custom view
        View movieLiteView = inflater.inflate(R.layout.movie_item, parent, false);

        // return a new ViewHolder instance
        return new ViewHolder(movieLiteView);
    }

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


//    /**
//     * create a new ImageView for each item referenced by the adapter
//     */
//    public View getView(int position, View convertView, ViewGroup parent) {
//        MovieLite movie = getItem(position);
//        String posterPath = movie.getPosterPath();
//        String finalPosterPath = MoviesDbClient.API_BASE_POSTER + posterPath;
//
//        ViewHolder viewHolder;
//
//        if (convertView == null) {
//            viewHolder = new ViewHolder();
//
//            convertView = LayoutInflater.from(getContext()).inflate(
//                    R.layout.movie_item, null);
//            convertView.setTag(viewHolder);
//
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//
//        viewHolder.imageView = (ImageView) convertView.findViewById(R.id.movie_img);
//        Picasso.with(getContext()).load(finalPosterPath).into(viewHolder.imageView);
//
//        return convertView;
//    }
}
