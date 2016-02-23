package com.popularmovies.mcondle.popularmovies.fragment;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.activity.MoviesGridActivity;
import com.popularmovies.mcondle.popularmovies.model.Movie;
import com.popularmovies.mcondle.popularmovies.network.MoviesDbClient;
import com.squareup.picasso.Picasso;

/**
 * Created by mscndle on 1/2/16.
 */
public class MovieDetailsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(false);

        ActionBar actionBar = getActivity().getActionBar(); // doing this to avoid NPE warnings
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movie_details, container, false);

        Movie movie = getArguments().getParcelable(MoviesGridActivity.MOVIE_DETAIL_KEY);
        populateView(v, movie);

        return v;
    }

    private void populateView(View v, Movie movie) {
        // grab views
        TextView movieTitle = (TextView) v.findViewById(R.id.movie_title);
        ImageView movieDetailImg = (ImageView) v.findViewById(R.id.movie_detail_img);
        TextView movieReleaseDate = (TextView) v.findViewById(R.id.movie_release_date);
        TextView movieRunningTime = (TextView) v.findViewById(R.id.movie_running_time);
        TextView movieRating = (TextView) v.findViewById(R.id.movie_rating);

        // fill views
        movieTitle.setText(movie.getTitle());
        Picasso.with(getActivity()).load(MoviesDbClient.API_BASE_POSTER + movie.getPosterPath()).into(movieDetailImg);
        movieReleaseDate.setText(movie.getReleaseDate());
        movieRunningTime.setText("MUST_FILL_THIS");
        movieRating.setText(String.valueOf(movie.getPopularity()));
    }





}
