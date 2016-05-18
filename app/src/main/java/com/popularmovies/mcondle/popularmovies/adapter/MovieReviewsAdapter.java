package com.popularmovies.mcondle.popularmovies.adapter;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.network.model.Review;

import java.util.List;

/**
 * Created by mscndle on 5/15/16.
 */
public class MovieReviewsAdapter extends RecyclerView.Adapter<MovieReviewsAdapter.ViewHolder> {

    private static final String TAG = MovieReviewsAdapter.class.getSimpleName();

    private Context context;
    private List<Review> reviewsList;

    public MovieReviewsAdapter(Context context, List<Review> reviewsList) {
        this.context = context;
        this.reviewsList = reviewsList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        Context context;
        TextView authorText;
        TextView reviewText;

        public ViewHolder(final View itemView, Context context) {
            super(itemView);
            this.context = context;

            authorText = (TextView) itemView.findViewById(R.id.author_txt);
            reviewText = (TextView) itemView.findViewById(R.id.review_txt);
        }
    }

    @Override
    public MovieReviewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.review_item, parent, false);

        return new ViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(MovieReviewsAdapter.ViewHolder viewHolder, int position) {
        Review review = reviewsList.get(position);

        TextView authorText = viewHolder.authorText;
        final TextView reviewText = viewHolder.reviewText;

        authorText.setText(review.getAuthor());
        reviewText.setText(review.getContent());

        reviewText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    int maxLines = reviewText.getMaxLines();

                    if (maxLines == 3) {
                        reviewText.setMaxLines(Integer.MAX_VALUE);
                    } else if (maxLines == Integer.MAX_VALUE) {
                        reviewText.setMaxLines(3);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return reviewsList.size();
    }

    public void insert(Review review) {
        reviewsList.add(review);
        notifyDataSetChanged();
    }

    public void insert(List<Review> review) {
        for (Review r : review) {
            reviewsList.add(r);
        }
        notifyDataSetChanged();
    }

    public void remove(Review review) {
        int position = reviewsList.indexOf(review);
        reviewsList.remove(position);
        notifyItemRemoved(position);
    }

    public void clear() {
        reviewsList.clear();
    }

}
