package com.popularmovies.mcondle.popularmovies.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.adapter.MoviesAdapter;
import com.popularmovies.mcondle.popularmovies.model.Movie;

/**
 * Created by mscndle on 11/28/15.
 */
public class MoviesGridFragment extends Fragment {
    MoviesAdapter mMoviesAdapter;

    OnButtonClickedListener mCallback;

    public interface OnButtonClickedListener {
        public void onButtonClicked();
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//
//        try {
//            mCallback = (OnButtonClickedListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnButtonClickedListener");
//        }
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_movies_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_sort_latest) {

        } else if (id == R.id.action_sort_popular) {

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_movies_grid, container, false);



        mMoviesAdapter = new MoviesAdapter(getActivity());

        GridView gridView = (GridView) v.findViewById(R.id.gridView);
        gridView.setAdapter(new MoviesAdapter(getActivity()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        return v;
    }

    //TODO - changing sort order should call this method
    private void updateMoviesList() {

    }

    public class FetchMoviesTask extends AsyncTask<String, Void, Movie[]> {


        @Override
        protected Movie[] doInBackground(String... params) {
            //TODO - make network call as soon as this fragment is loaded

            return null;
        }

        @Override
        public void onPostExecute(Movie[] result) {
            if (result != null) {

            }

        }

    }

}
