package com.popularmovies.mcondle.popularmovies.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.popularmovies.mcondle.popularmovies.R;

/**
 * Created by mscndle on 11/28/15.
 */
public class MoviesGridFragment extends Fragment {
    OnButtonClickedListener mCallback;

    public interface OnButtonClickedListener {
        public void onButtonClicked();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnButtonClickedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnButtonClickedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_movies_grid, container, false);

        TextView textView = (TextView) v.findViewById(R.id.main_text_view);
        textView.setText("Hello!!! this is the main textView");

        Button button = (Button) v.findViewById(R.id.grid_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onButtonClicked();
            }
        });

        return v;
    }


}
