package com.example.beachrendezvous.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beachrendezvous.R;

public class FoodEvents extends Fragment {

    //region Bundle parameters
    private static final String ARG_PARAM1 = "param1";    // Gives Restaurant
    private static final String ARG_PARAM2 = "param2";    //
    private String mParam1;
    private String mParam2;
    //endregion

    //region References
    
    //endregion

    //region Constructors
    public FoodEvents() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FoodEvents.
     */
    // TODO: Rename and change types and number of parameters
    public static FoodEvents newInstance(String param1, String param2) {
        FoodEvents fragment = new FoodEvents();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    //endregion

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_events, container, false);
    }
}
