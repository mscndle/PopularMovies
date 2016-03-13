package com.popularmovies.mcondle.popularmovies.fragment;

import android.app.ActionBar;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.activity.MoviesHomeActivity;
import com.popularmovies.mcondle.popularmovies.model.Movie;
import com.popularmovies.mcondle.popularmovies.network.MoviesDbClient;
import com.squareup.picasso.Picasso;

/**
 * Created by mscndle on 1/2/16.
 */
public class MovieDetailsFragment extends Fragment {

    private Movie movie;

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

        long movieId = getArguments().getLong(MoviesHomeActivity.MOVIE_DETAIL_KEY);
        getExtraMovieDetails(movieId);
        populateView(v);

        return v;
    }

    private void getExtraMovieDetails(long movieId) {
        FetchMovieDetailsTask task = new FetchMovieDetailsTask();

        try {
            movie = task.execute(movieId).get();
        } catch (Exception ie) {
            //
        }

    }

    private void populateView(View v) {
        // grab views
        TextView movieTitle = (TextView) v.findViewById(R.id.movie_original_title);
        ImageView movieDetailImg = (ImageView) v.findViewById(R.id.movie_detail_img);
        TextView movieReleaseDate = (TextView) v.findViewById(R.id.movie_release_date);
        TextView movieRunningTime = (TextView) v.findViewById(R.id.movie_running_time);
        TextView movieRating = (TextView) v.findViewById(R.id.movie_rating);
        TextView movieSynposis = (TextView) v.findViewById(R.id.movie_synopsis);

        // fill title and image
        movieTitle.setText(movie.getOriginalTitle());
        Picasso.with(getActivity())
                .load(MoviesDbClient.API_BASE_POSTER + movie.getPosterPath())
                .into(movieDetailImg);

        // parse year from date
        String[] dateArr = movie.getReleaseDate().split("-");
        movieReleaseDate.setText(dateArr[0]);

        String formattedRuntime = String.valueOf(movie.getRuntime()) + "minutes";
        movieRunningTime.setText(formattedRuntime);

        movieRating.setText(String.valueOf(movie.getVoteAverage() + "/10"));
        movieSynposis.setText(movie.getOverview());
    }

    private void grabMovie(Movie movie) {
        this.movie = movie;
    }

    public class FetchMovieDetailsTask extends AsyncTask<Long, Void, Movie> {

        @Override
        protected Movie doInBackground(Long... params) {
            MoviesDbClient moviesDbClient = MoviesDbClient.getMoviesDbClient();
            return moviesDbClient.getMovieDetails(params[0]);
        }

        @Override
        protected void onPostExecute(Movie movie) {
            //
        }

    }




}
