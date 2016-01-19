package com.popularmovies.mcondle.popularmovies.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.popularmovies.mcondle.popularmovies.R;
import com.popularmovies.mcondle.popularmovies.fragment.MoviesGridFragment;

public class MoviesGridActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_grid);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.gridActivityFrameLayout, new MoviesGridFragment())
                    .commit();
        }
    }
}
