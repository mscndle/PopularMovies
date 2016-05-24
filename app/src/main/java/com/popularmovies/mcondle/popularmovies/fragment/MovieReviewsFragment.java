package com.popularmovies.mcondle.popularmovies.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.adapter.MovieReviewsAdapter;
import com.popularmovies.mcondle.popularmovies.network.MoviesClient;
import com.popularmovies.mcondle.popularmovies.network.model.Review;
import com.popularmovies.mcondle.popularmovies.network.model.ReviewsResponse;
import com.popularmovies.mcondle.popularmovies.network.service.MovieReviewsService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by mandeep.condle on 5/3/16.
 */
public class MovieReviewsFragment extends Fragment {

    private static final String TAG = MovieReviewsFragment.class.getSimpleName();

    private static final String MOVIE_ID_KEY = "movieId";
    private static final String REVIEWS_LIST_KEY = "movieReviews";

    private Long movieId;
    private MovieReviewsService movieReviewsService;

    private ArrayList<Review> reviewsList;
    private RecyclerView recyclerView;
    private MovieReviewsAdapter movieReviewsAdapter;

    public static MovieReviewsFragment newInstance(long movieId) {
        MovieReviewsFragment fragment = new MovieReviewsFragment();
        Bundle args = new Bundle();

        args.putLong(MOVIE_ID_KEY, movieId);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            movieId = getArguments().getLong(MOVIE_ID_KEY);
        }

        if (savedInstanceState == null || !savedInstanceState.containsKey(REVIEWS_LIST_KEY)) {
            reviewsList = new ArrayList<>();
        } else {
            reviewsList = savedInstanceState.getParcelableArrayList(REVIEWS_LIST_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reviews, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.reviews_recycler_view);
        movieReviewsAdapter = new MovieReviewsAdapter(getActivity(), reviewsList);
        recyclerView.setAdapter(movieReviewsAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        movieReviewsAdapter.notifyDataSetChanged();

        updateReviewsList();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void updateReviewsList() {

        if (movieId != null) {
            movieReviewsService = MoviesClient.getInstance().getMovieReviewsService();
            movieReviewsService.getMovieReviews(movieId, MoviesClient.API_KEY, new Callback<ReviewsResponse>() {
                @Override
                public void success(ReviewsResponse reviewsResponse, Response response) {
                    syncReviews(reviewsResponse.getResults());
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.d(TAG, error.toString());
                }
            });
        }
    }

    private void syncReviews(List<Review> reviewsList) {
        movieReviewsAdapter.clear();
        movieReviewsAdapter.insert(reviewsList);
    }

}
