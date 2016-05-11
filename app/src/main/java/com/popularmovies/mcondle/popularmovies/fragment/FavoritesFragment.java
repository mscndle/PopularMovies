package com.popularmovies.mcondle.popularmovies.fragment;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.adapter.FavoritesCursorAdapter;
import com.popularmovies.mcondle.popularmovies.db.FavoritesContract;
import com.popularmovies.mcondle.popularmovies.network.model.MovieLite;

import java.util.ArrayList;

/**
 * Created by mandeep.condle on 2/28/16.
 */
public class FavoritesFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int FAVORITES_LOADER = 0;
    private static final String KEY_MOVIES_LIST = "moviesListPopular";

    private static final int GRID_COLUMNS_PHONE = 2;
    private static final int GRID_COLUMNS_TABLET = 3;

    protected FavoritesCursorAdapter favoritesAdapter;
    protected ArrayList<MovieLite> moviesList;
    protected Cursor cursor;

    protected View rootView;
    protected RecyclerView gridRecyclerView;

    public Context getContext() {
        return getActivity();
    }

    public static FavoritesFragment newInstance() {
        FavoritesFragment fragment = new FavoritesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null || !savedInstanceState.containsKey(KEY_MOVIES_LIST)) {
            moviesList = new ArrayList<>();
        } else {
            moviesList = savedInstanceState.getParcelableArrayList(KEY_MOVIES_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_movies_grid, container, false);

        gridRecyclerView = (RecyclerView) rootView.findViewById(R.id.movies_grid_recycler_view);

        cursor = initCursor();
        favoritesAdapter = new FavoritesCursorAdapter(getContext(), cursor);

        gridRecyclerView.setAdapter(favoritesAdapter);
        gridRecyclerView.setPadding(2, 2, 2, 2);    // padding is added here and in the ViewHolder for symmetry

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), GRID_COLUMNS_PHONE);
        gridRecyclerView.setLayoutManager(gridLayoutManager);

        loadFavorites();

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(FAVORITES_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadFavorites();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String sortOrder = FavoritesContract.FavoritesEntry._ID + " ASC";
        Uri allFavoritesUri = FavoritesContract.FavoritesEntry.buildFavoritesUri();

        return new CursorLoader(getActivity(), allFavoritesUri, null, null, null, sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        favoritesAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        favoritesAdapter.swapCursor(null);
    }

    private Cursor initCursor() {
        String sortOrder = FavoritesContract.FavoritesEntry._ID + " ASC";
        Uri allFavoritesUri = FavoritesContract.FavoritesEntry.buildFavoritesUri();

        return getActivity().getContentResolver().query(allFavoritesUri, null, null, null, sortOrder);
    }

    private void loadFavorites() {
        // todo
    }

}
