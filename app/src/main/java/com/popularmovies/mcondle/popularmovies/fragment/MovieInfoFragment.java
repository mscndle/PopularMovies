package com.popularmovies.mcondle.popularmovies.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.activity.MovieDetailActivity;
import com.popularmovies.mcondle.popularmovies.db.FavoritesDbHelper;
import com.popularmovies.mcondle.popularmovies.network.MoviesClient;
import com.popularmovies.mcondle.popularmovies.network.model.Movie;
import com.squareup.picasso.Picasso;

import java.nio.charset.Charset;

/**
 * Created by mandeep.condle on 5/3/16.
 */
public class MovieInfoFragment extends Fragment {

    private static final String MOVIE_KEY = "movie";
    private static final String MOVIE_FAV_KEY = "movieFav";

    // activity is used as the context in this fragment
    private Movie movie;
    private boolean isFavorite;

    private ImageView movieImg;
    private TextView movieInfoTitle;
    private TextView releaseDate;
    private TextView rating;
    private TextView synopsis;
    private Button favButton;

    public static MovieInfoFragment newInstance(Movie movie, boolean isFavorite) {
        MovieInfoFragment fragment = new MovieInfoFragment();
        Bundle args = new Bundle();

        args.putBoolean(MOVIE_FAV_KEY, isFavorite);
        if (movie != null) {
            args.putParcelable(MOVIE_KEY, movie);
        }

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        movie = getArguments().getParcelable(MOVIE_KEY);
        isFavorite = getArguments().getBoolean(MOVIE_FAV_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_info, container, false);

        movieImg = (ImageView) rootView.findViewById(R.id.movie_detail_img);
        movieInfoTitle = (TextView) rootView.findViewById(R.id.movie_info_title);
        releaseDate = (TextView) rootView.findViewById(R.id.movie_release_date);
        rating = (TextView) rootView.findViewById(R.id.movie_rating);
        synopsis = (TextView) rootView.findViewById(R.id.movie_synopsis);
        favButton = (Button) rootView.findViewById(R.id.fav_button);

        handleFavButtonUI(isFavorite);

        setClickListeners();

        return rootView;
    }

    private void setClickListeners() {
        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo - DB stuff

                CharSequence text = isFavorite ? "Removed from Favorites" : "Added to Favorites";
                Snackbar.make(v, text, Snackbar.LENGTH_SHORT).show();
                isFavorite = !isFavorite;

                handleFavButtonUI(isFavorite);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        populateData();
    }

    protected void populateData() {
        Picasso.with(getActivity())
                .load(MoviesClient.BASE_POSTER_URL + movie.getPosterPath())
                .into(movieImg);

        movieInfoTitle.setText(movie.getTitle());

        // parse year from date
        String[] dateArr = movie.getReleaseDate().split("-");
        releaseDate.setText(dateArr[0]);

        String formattedRating = movie.getVoteAverage() + "/10";
        rating.setText(formattedRating);

        synopsis.setText(movie.getOverview());
    }

    private void handleFavButtonUI(boolean isFavorite) {
        if (isFavorite) {
            favButton.setBackgroundColor(getResources().getColor(R.color.primaryDarkColor));
            favButton.setText(getResources().getString(R.string.remove_from_fav));
        } else {
            favButton.setBackgroundColor(getResources().getColor(R.color.material_blue_grey_800));
            favButton.setText(getResources().getString(R.string.add_to_fav));
        }
    }

}
