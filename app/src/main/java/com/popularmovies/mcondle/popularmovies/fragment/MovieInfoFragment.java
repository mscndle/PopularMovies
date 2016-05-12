package com.popularmovies.mcondle.popularmovies.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.activity.MovieDetailsActivity;
import com.popularmovies.mcondle.popularmovies.network.model.Movie;
import com.popularmovies.mcondle.popularmovies.network.MoviesClient;
import com.squareup.picasso.Picasso;

/**
 * Created by mandeep.condle on 5/3/16.
 */
public class MovieInfoFragment extends Fragment {

    // activity is used as the context in this fragment
    private MovieDetailsActivity activity;
    private Movie movie;

    private ImageView movieImg;
    private TextView releaseDate;
    private TextView runningTime;
    private TextView rating;
    private TextView synopsis;

    public static MovieInfoFragment newInstance() {
        return new MovieInfoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MovieDetailsActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_info, container, false);

        movieImg = (ImageView) rootView.findViewById(R.id.movie_detail_img);
        releaseDate = (TextView) rootView.findViewById(R.id.movie_release_date);
        runningTime = (TextView) rootView.findViewById(R.id.movie_running_time);
        rating = (TextView) rootView.findViewById(R.id.movie_rating);
        synopsis = (TextView) rootView.findViewById(R.id.movie_synopsis);

        synopsis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                synopsis.setMaxLines(20);
            }
        });

        return rootView;
    }

    protected void populateData() {
        // movie ref is grabbed here since this method is called after the movie obj has been fetched
//        movie = activity.movie;

//         fixme
//        Picasso.with(activity)
//                .load(MoviesClient.API_BASE_POSTER + movie.getPosterPath())
//                .into(movieImg);

        // parse year from date
        String[] dateArr = movie.getReleaseDate().split("-");
        releaseDate.setText(dateArr[0]);

        String formattedRuntime = movie.getRuntime() + "minutes";
        runningTime.setText(formattedRuntime);

        String formattedRating = movie.getVoteAverage() + "/10";
        rating.setText(formattedRating);

        synopsis.setText(movie.getOverview());
    }

}
