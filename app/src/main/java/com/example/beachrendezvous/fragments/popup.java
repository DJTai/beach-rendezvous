package com.example.beachrendezvous.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.beachrendezvous.MainActivity;
import com.example.beachrendezvous.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class popup extends Fragment {

    private static final String CREATE_SEARCH = "createOrSearch";
    private static final String ARG_PARAM2 = "param2";
    private String name;
    private String mParam2;
    Unbinder mUnbinder;
    View view;
    private String create_search;

    public popup() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(MainActivity.ARG_GIVEN_NAME);
            create_search = getArguments().getString(CREATE_SEARCH);
            // mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_popup, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        TextView confirmText = view.findViewById(R.id.confirmtext);
        if (create_search.equals("search")) {
            confirmText.setText(R.string.success_joined_event);
        } else {
            confirmText.setText(R.string.success_created_event);
        }

        return view;
    }

    @OnClick(R.id.Okbutton)
    void OkbuttonClicked() {
        Log.i("button clicked", "popup");
        FragmentManager fragmentManager;
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.popBackStack(fragmentManager.getBackStackEntryAt(0).getId(),
                                     fragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}

