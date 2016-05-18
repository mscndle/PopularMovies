package com.popularmovies.mcondle.popularmovies.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.network.model.Trailer;

import java.util.List;

/**
 * Created by mscndle on 5/15/16.
 */
public class MovieTrailersAdapter extends RecyclerView.Adapter<MovieTrailersAdapter.ViewHolder> {

    public static final String TAG = MovieTrailersAdapter.class.getSimpleName();

    private Context context;
    private List<Trailer> trailersList;

    public MovieTrailersAdapter(Context context, List<Trailer> trailersList) {
        this.context = context;
        this.trailersList = trailersList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        Context context;
        TextView trailerText;
        TextView trailerSize;

        public ViewHolder(final View itemView, Context context) {
            super(itemView);
            this.context = context;

            trailerText = (TextView) itemView.findViewById(R.id.trailer_txt);
            trailerSize = (TextView) itemView.findViewById(R.id.trailer_size);
        }
    }

    @Override
    public MovieTrailersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.trailer_item, parent, false);

        return new ViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Trailer trailer = trailersList.get(position);

        TextView trailerText = holder.trailerText;
        TextView trailerSize = holder.trailerSize;

        trailerText.setText(trailer.getName());
        String formattedSizeStr = String.valueOf(trailer.getSize() + "p");
        trailerSize.setText(String.valueOf(formattedSizeStr));

//        final String youtubeUrl = "http://www.youtube.com/watch?v=" + trailer.getKey();

//        trailerText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl)));
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void insert(Trailer trailer) {
        trailersList.add(trailer);
        notifyDataSetChanged();
    }

    public void insert(List<Trailer> trailers) {
        for (Trailer t : trailers) {
            trailersList.add(t);
        }
        notifyDataSetChanged();
    }

    public void remove(Trailer trailer) {
        int position = trailersList.indexOf(trailer);
        trailersList.remove(position);
        notifyItemRemoved(position);
    }

    public void clear() {
        trailersList.clear();
    }

}
