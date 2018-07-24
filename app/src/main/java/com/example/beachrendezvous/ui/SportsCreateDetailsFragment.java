package com.example.beachrendezvous.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.beachrendezvous.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class SportsCreateDetailsFragment extends Fragment {

    private String mParam;
    private static final String TAG = "sports_create_details";
    // KEY for getArguments()
    private static final String ARG_PARAM = "param";

    Unbinder mUnbinder;

    // TODO: MAKE BINDING CALLS TO EVERY THING ON THE XML LAYOUT
    @BindView(R.id.createEvent_commentLabel)
    TextView commentTextView;

    public SportsCreateDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam = getArguments().getString(ARG_PARAM);
            Log.i(TAG, "onCreate: mParam = " + mParam);
        }
    }

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater,
                              @Nullable ViewGroup container,
                              @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_event, container, false);

        mUnbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView () {
        super.onDestroyView();

        // Unbind view to free some memory
        mUnbinder.unbind();
    }

    /**
     * function called when "CREATE" button is clicked
     * - grab all the data from the associated ID's
     * - call a fake function for now     *
     *
     */
}
