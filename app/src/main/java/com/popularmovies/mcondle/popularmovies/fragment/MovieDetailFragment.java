package com.popularmovies.mcondle.popularmovies.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.activity.MovieDetailActivity;
import com.popularmovies.mcondle.popularmovies.adapter.DetailsViewPagerAdapter;
import com.popularmovies.mcondle.popularmovies.layout.SlidingTabLayout;
import com.popularmovies.mcondle.popularmovies.network.MoviesClient;
import com.popularmovies.mcondle.popularmovies.network.model.Movie;
import com.squareup.picasso.Picasso;

/**
 * Created by mandeep.condle on 1/2/16.
 */
public class MovieDetailFragment extends Fragment {

    private static final String TAG = MovieDetailFragment.class.getSimpleName();

    private static final String MOVIE_KEY = "movie";
    private static final String MOVIE_FAV_KEY = "movieFav";
    private static final int TAB_COUNT = 3;

    private Toolbar toolBar;
    private ImageView movieBackdrop;

    private ViewPager viewPager;
    private SlidingTabLayout slidingTabLayout;

    private DetailsViewPagerAdapter viewPagerAdapter;
    private CharSequence tiles[] = {"INFO", "REVIEWS", "TRAILERS"};

    private Movie movie;
    private boolean isFavorite;

    public static MovieDetailFragment newInstance(Movie movie) {
        return newInstance(movie, false);
    }

    public static MovieDetailFragment newInstance(Movie movie, boolean isFavorite) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle args = new Bundle();

        args.putBoolean(MOVIE_FAV_KEY, isFavorite);
        if (movie != null) {
            args.putParcelable(MOVIE_KEY, movie );
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

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        movieBackdrop = (ImageView) rootView.findViewById(R.id.poster_backdrop);

        toolBar = (Toolbar) rootView.findViewById(R.id.tool_bar);
        ((MovieDetailActivity) getActivity()).setSupportActionBar(toolBar);

        /**
         * we need to pass the movie object and isFavorite flag to the {@link MovieInfoFragment}
         * similarly, we need to pass the movieId to {@link MovieReviewsFragment} and {@link MovieTrailersFragment}
         * so the fragments can handle making the network requests to fetch their own data
         * to do this, we pass the movie object and isFavorite to the {@link DetailsViewPagerAdapter}
         */
        viewPager = (ViewPager) rootView.findViewById(R.id.details_view_pager);
        viewPagerAdapter = new DetailsViewPagerAdapter(getActivity().getSupportFragmentManager(),
                tiles, TAB_COUNT, movie, isFavorite);
        viewPager.setAdapter(viewPagerAdapter);

        slidingTabLayout = (SlidingTabLayout) rootView.findViewById(R.id.details_sliding_tab_layout);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.indicator);
            }
        });
        slidingTabLayout.setViewPager(viewPager);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        populateData();
    }

    private void populateData() {
        toolBar.setTitle(movie.getTitle());

        Picasso.with(getActivity())
                .load(MoviesClient.BASE_POSTER_URL + movie.getBackdropPath())
                .fit().into(movieBackdrop);
    }

}
