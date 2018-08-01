package com.example.beachrendezvous.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beachrendezvous.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public class Info extends Fragment {

    /* Logging Tags */
    public static final String TAG = "info_fragment";
    private static final String DEBUG = "debug_infoFragment";

    private Unbinder mUnbinder;

    public Info () {
        // Required empty public constructor
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info, container, false);

        mUnbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView () {
        super.onDestroyView();

        // Unbind the view to free some memory
        mUnbinder.unbind();
    }

    @Override
    public void onDetach () {
        super.onDetach();
    }
}
