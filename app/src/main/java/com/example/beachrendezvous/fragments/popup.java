package com.example.beachrendezvous.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.beachrendezvous.MainActivity;
import com.example.beachrendezvous.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class popup extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String CREATE_SEARCH = "createOrSearch";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String name;
    private String mParam2;
    Unbinder mUnbinder;
    View view;
    private  String create_search;
    public popup() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(MainActivity.ARG_GIVEN_NAME);
            create_search=getArguments().getString(CREATE_SEARCH);

           // mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_popup, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        TextView confirmText = view.findViewById(R.id.confirmtext);
        if(create_search.equals("search"))
        {

            confirmText.setText("You Successfully Joined Event");
        }
        else {
            confirmText.setText("You Successfully Created Event");
        }


        return view;
    }
    @OnClick(R.id.Okbutton)
    void OkbuttonClicked() {
        Log.i("button clicked","popup");
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        Fragment f = new MainMenu();

        Bundle args = new Bundle();
        args.putString(MainActivity.ARG_GIVEN_NAME, name);
        f.setArguments(args);
               fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .replace(R.id.frame_fragment, f)
                .addToBackStack("search")
                .commit();

    }
}

