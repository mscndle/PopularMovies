package com.popularmovies.mcondle.popularmovies.fragment;

import android.content.ContentValues;
import android.net.Uri;
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
import android.widget.Toast;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.activity.MovieDetailActivity;
import com.popularmovies.mcondle.popularmovies.db.FavoritesContract;
import com.popularmovies.mcondle.popularmovies.db.FavoritesDbHelper;
import com.popularmovies.mcondle.popularmovies.db.FavoritesProvider;
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

        if (getArguments() != null) {
            movie = getArguments().getParcelable(MOVIE_KEY);
            isFavorite = getArguments().getBoolean(MOVIE_FAV_KEY);
        }
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
                toggleFavorites(isFavorite);
                handleFavButtonUI(isFavorite);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        populateData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    protected void populateData() {
        if (movie != null) {
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
    }

    private void toggleFavorites(boolean isFavorite) {
        CharSequence text = "";

        long id = movie.getId();
        String title = movie.getTitle();
        String posterPath = movie.getPosterPath();
        String backdropPath = movie.getBackdropPath();
        double voteAvg = movie.getVoteAverage();
        double popularity = movie.getPopularity();
        String overview = movie.getOverview();
        String releaseDate = movie.getReleaseDate();

        FavoritesProvider provider = new FavoritesProvider();
        Uri uri = FavoritesContract.FavoritesEntry.buildFavoritesUri();

        ContentValues favContentValues = new ContentValues();
        favContentValues.put(FavoritesContract.FavoritesEntry.COLUMN_MOVIE_ID, id);
        favContentValues.put(FavoritesContract.FavoritesEntry.COLUMN_MOVIE_TITLE, title);
        favContentValues.put(FavoritesContract.FavoritesEntry.COLUMN_MOVIE_POSTER_PATH, posterPath);
        favContentValues.put(FavoritesContract.FavoritesEntry.COLUMN_MOVIE_BACKDROP_PATH, backdropPath);
        favContentValues.put(FavoritesContract.FavoritesEntry.COLUMN_MOVIE_VOTE_AVG, voteAvg);
        favContentValues.put(FavoritesContract.FavoritesEntry.COLUMN_MOVIE_POPULARITY, popularity);
        favContentValues.put(FavoritesContract.FavoritesEntry.COLUMN_MOVIE_OVERVIEW, overview);
        favContentValues.put(FavoritesContract.FavoritesEntry.COLUMN_MOVIE_RELEASE_DATE, releaseDate);

        if (isFavorite) {
            int deletedRows = getActivity().getContentResolver()
                    .delete(FavoritesContract.FavoritesEntry.CONTENT_URI,
                            FavoritesContract.FavoritesEntry.COLUMN_MOVIE_ID + " = ?",
                            new String[]{String.valueOf(id)});

            if (deletedRows > 0) {
                text = "Removed from Favorites";
            } else {
                text = "Error removing from Favorites";
            }

        } else {
            Uri insertedRowsUri = getActivity().getContentResolver()
                    .insert(FavoritesContract.FavoritesEntry.CONTENT_URI, favContentValues);

//            favButton.setBackgroundColor(getResources().getColor(R.color.material_blue_grey_800));
//            favButton.setText(getResources().getString(R.string.add_to_fav));
            text = "Added to Favorites";
        }

        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();

        // change the favorite flag
        this.isFavorite = !isFavorite;
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
