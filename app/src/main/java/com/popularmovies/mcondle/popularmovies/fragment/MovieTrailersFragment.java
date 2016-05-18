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
import com.popularmovies.mcondle.popularmovies.adapter.MovieTrailersAdapter;
import com.popularmovies.mcondle.popularmovies.network.MoviesClient;
import com.popularmovies.mcondle.popularmovies.network.model.Trailer;
import com.popularmovies.mcondle.popularmovies.network.model.TrailersResponse;
import com.popularmovies.mcondle.popularmovies.network.service.MovieTrailersService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by mandeep.condle on 5/3/16.
 */
public class MovieTrailersFragment extends Fragment {

    private static final String TAG = MovieTrailersFragment.class.getSimpleName();

    private static final String MOVIE_ID_KEY = "movieId";
    private static final String TRAILERS_LIST_KEY = "movieTrailers";

    private long movieId;
    private MovieTrailersService movieTrailersService;

    private ArrayList<Trailer> trailersList;
    private RecyclerView recyclerView;
    private MovieTrailersAdapter movieTrailersAdapter;

    public static MovieTrailersFragment newInstance(long movieId) {
        MovieTrailersFragment fragment = new MovieTrailersFragment();
        Bundle args = new Bundle();

        args.putLong(MOVIE_ID_KEY, movieId);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieId = getArguments().getLong(MOVIE_ID_KEY);

        if (savedInstanceState == null || !savedInstanceState.containsKey(TRAILERS_LIST_KEY)) {
            trailersList = new ArrayList<>();
        } else {
            trailersList = savedInstanceState.getParcelableArrayList(TRAILERS_LIST_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_trailers, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.trailers_recycler_view);
        movieTrailersAdapter = new MovieTrailersAdapter(getActivity(), trailersList);
        recyclerView.setAdapter(movieTrailersAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateTrailersList();
    }

    private void updateTrailersList() {
        movieTrailersService = MoviesClient.getInstance().getMovieTrailersService();
        movieTrailersService.getMovieTrailers(movieId, MoviesClient.API_KEY, new Callback<TrailersResponse>() {
            @Override
            public void success(TrailersResponse trailersResponse, Response response) {
                syncTrailers(trailersResponse.getResults());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, error.toString());
            }
        });
    }

    private void syncTrailers(List<Trailer> trailersList) {
        movieTrailersAdapter.clear();
        movieTrailersAdapter.insert(trailersList);
    }

}
