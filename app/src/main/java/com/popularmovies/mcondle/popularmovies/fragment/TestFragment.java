package com.popularmovies.mcondle.popularmovies.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.popularmovies.mcondle.popularmovies.R;

/**
 * Created by mscndle on 12/25/15.
 */
public class TestFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_test, container, false);

        TextView textView1 = (TextView) v.findViewById(R.id.textView1);
        textView1.setText("First textView");

        TextView textView2 = (TextView) v.findViewById(R.id.textView2);
        textView2.setText("Second textView");
        textView2.setAllCaps(true);

        return v;
    }
}
