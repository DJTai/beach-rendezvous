package com.example.beachrendezvous.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beachrendezvous.R;
import com.example.beachrendezvous.database.SportsEntity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MainMenu extends Fragment {

    /* Logging Tags */
    public static final String TAG = "mainMenu_fragment";
    private static final String DEBUG = "debug_mainMenu";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Unbinder mUnbinder;
    DatabaseReference databaseSports;

    public MainMenu() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //reference to database
        databaseSports = FirebaseDatabase.getInstance().getReference("sports");



    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        // TODO: Show events

        databaseSports
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            SportsEntity user = snapshot.getValue(SportsEntity.class);
                            Log.i(TAG, "Listening created" + user.getType());
                            // Log.d("item id ",snapshot.child("time").getValue().toString());
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

        //end show events


        // Bind View using ButterKnife
        mUnbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // Unbind the view to free some memory
        mUnbinder.unbind();
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed (Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
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
//
//    @Override
//    public void onDetach () {
//        super.onDetach();
//        mListener = null;
//    }
}
