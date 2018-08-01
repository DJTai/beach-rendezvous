package com.example.beachrendezvous.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beachrendezvous.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public class Profile extends Fragment {

    /* Logging Tags */
    public static final String TAG = "profile_fragment";
    private static final String DEBUG = "debug_profileFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

//    private OnFragmentInteractionListener mListener;

    Unbinder mUnbinder;

    public Profile () {
        // Required empty public constructor
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView (@NonNull LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        mUnbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView () {
        super.onDestroyView();

        // Unbind the view to free some memory
        mUnbinder.unbind();
    }

    // TODO: Rename method, update argument and hook method into UI event
    // TODO: Might need to link profile options (e.g., editing profile to change info in DB)
//    public void onButtonPressed (Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach (Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                                               + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach () {
        super.onDetach();
//        mListener = null;
    }
}
