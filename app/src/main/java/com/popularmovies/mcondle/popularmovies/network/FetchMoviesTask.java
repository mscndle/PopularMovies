package com.popularmovies.mcondle.popularmovies.network;

import android.os.AsyncTask;

import com.popularmovies.mcondle.popularmovies.fragment.MoviesAsyncDelegate;
import com.popularmovies.mcondle.popularmovies.model.MovieLite;
import com.popularmovies.mcondle.popularmovies.model.SortOrder;

import java.util.List;

public class FetchMoviesTask extends AsyncTask<SortOrder, Void, List<MovieLite>> {

    private final String TAG = FetchMoviesTask.class.getSimpleName();

    private MoviesAsyncDelegate delegate;

    public FetchMoviesTask(MoviesAsyncDelegate delegate) {
        this.delegate = delegate;
    }

    protected List<MovieLite> doInBackground(SortOrder... params) {
        MoviesDbClient client = MoviesDbClient.getMoviesDbClient();

        switch (params[0]) {
            default:
            case MOST_POPULAR:
                return client.getMoviesList(SortOrder.MOST_POPULAR);
            case HIGHEST_RATED:
                return client.getMoviesList(SortOrder.HIGHEST_RATED);
        }
    }

    @Override
    public void onPostExecute(List<MovieLite> result) {
        if (result != null) {
            delegate.asyncComplete(result);
        }
    }

}