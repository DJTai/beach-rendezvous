package com.example.beachrendezvous.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.beachrendezvous.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.R.layout.simple_spinner_item;


public class SportsCreateDetails extends Fragment {

    private String mParam;
    private static final String TAG = "sports_create_details";
    // KEY for getArguments()
    private static final String ARG_PARAM1 = "param1";

    Unbinder mUnbinder;
    Spinner spinner;

    // TODO: MAKE BINDING CALLS TO EVERY THING ON THE XML LAYOUT
    @BindView(R.id.createEvent_commentLabel)
    TextView commentTextView;

    public SportsCreateDetails () {
        // Required empty public constructor
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam = getArguments().getString(ARG_PARAM1);
            Log.i(TAG, "onCreate: mParam = " + mParam);
        }


    }

    public void addItemsOnSpinner(View v1) {

        spinner = (Spinner) v1.findViewById(R.id.createEvent_placeText);
        List<String> list = new ArrayList<String>();
        list.add("USU");
        list.add("Student Recreation Center");
        list.add("Near Brotman Hall");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater,
                              @Nullable ViewGroup container,
                              @Nullable Bundle savedInstanceState) {

        View view = null;

        if (mParam != null) {
                Log.i(TAG, "sports create details");
                view = inflater.inflate(R.layout.fragment_sports_create_event, container, false);
            addItemsOnSpinner(view);


        }
        // Bind view using ButterKnife
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }


}
