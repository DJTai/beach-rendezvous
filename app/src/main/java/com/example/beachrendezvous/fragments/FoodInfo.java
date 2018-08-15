package com.example.beachrendezvous.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.beachrendezvous.MainActivity;
import com.example.beachrendezvous.R;
import com.example.beachrendezvous.sportsListAdapter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FoodInfo extends Fragment {

    private static final String DEBUG = "debug";

    private static final String ARG_PARAM1 = "param1";    // Refers to search OR create
    private static final String ARG_PARAM2 = "param2";    // Refers to food

    //region References

    /** Value retrieved from ARG_PARAM1 key */
    private String mParam1;

    /** Value retrieved from ARG_PARAM2 key */
    private String mParam2;

    /** ButterKnife Unbinder */
    private Unbinder mUnbinder;

    /** ListView for displaying the food events */
    private ListView mListView;

    /** Food event creator's name */
    String name;

    //endregion

    //region Constructors
    public FoodInfo() {
        // Required empty public constructor
    }
    //endregion

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            name = getArguments().getString(MainActivity.ARG_GIVEN_NAME);

            Log.d(DEBUG,
                  "onCreate: mParam1 = " + mParam1 + ", mParam2 = " + mParam2 + ", user is " +
                          name);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = null;

        if (mParam1 != null) {
            view = inflater.inflate(R.layout.fragment_food_info, container, false);
            TextView header = view.findViewById(R.id.header_foodInfo); // fragment_food_info.xml

            if (mParam2.trim().equals("food")) {
                sportsListAdapter foodAdapter;

                if (mParam1.trim().equals("search")) {
                    header.setText(R.string.food_info_search);
                } else {
                    header.setText(R.string.food_info_create);
                }

                foodAdapter = new sportsListAdapter(getContext().getApplicationContext(),
                                                    getRestaurants(),
                                                    getFoodImages());
                mListView = view.findViewById(R.id.listView_food);
                mListView.setAdapter(foodAdapter);
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Log.d(DEBUG, "onItemClick: List clicked");

                        Fragment fragment;
                        Bundle args;
                        FragmentManager fragmentManager;

                        if (mParam1.trim().equals("search")) {
                            Log.d(DEBUG, "onItemClick: searching");

                            fragment = new FoodEvents();
                            args = new Bundle();
                            args.putString(ARG_PARAM1, getRestaurants()[i]);
                            args.putString(MainActivity.ARG_GIVEN_NAME, name);
                            fragment.setArguments(args);

                            fragmentManager = getActivity().getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager
                                    .beginTransaction();

                            fragmentTransaction
                                    .replace(R.id.frame_fragment, fragment)
                                    .addToBackStack(mParam1)
                                    .commit();

                        } else if (mParam1.equals("create")) {
                            fragment = new FoodCreateDetails();
                            args = new Bundle();

                            args.putString(ARG_PARAM1, getRestaurants()[i]);
                            args.putString(MainActivity.ARG_GIVEN_NAME, name);
                            fragment.setArguments(args);

                            fragmentManager = getActivity().getSupportFragmentManager();

                            FragmentTransaction fragmentTransaction = fragmentManager
                                    .beginTransaction();

                            fragmentTransaction
                                    .replace(R.id.frame_fragment, fragment)
                                    .addToBackStack(mParam1)
                                    .commit();
                        }
                    }
                });
            }
        }

        mUnbinder = ButterKnife.bind(this, view);

        return view;
    }

    /**
     * Retrieves a list of all of the restaurants on campus
     *
     * @return - String array of restaurants on campus
     */
    private final String[] getRestaurants() {
        String[] restaurants = {
                "Parkside Dining",
                "Hillside Dining",
                "Beachside Dining",
                "Chartroom",
                "Nugget Grill & Pub",
                "Outpost Grill",
                "University Dining Plaza",
                "Starbucks @ UDP",
                "Starbucks @ Library",
                "Beach Walk",
                "OPA! Greek",
                "Hibachi San",
                "Panda Express",
                "Squeeze Me",
                ""
        };

        return restaurants;
    }

    /**
     * TODO: Use different logos
     *
     * @return - int array of logos
     */
    private final int[] getFoodImages() {
        int[] foodImages = new int[getRestaurants().length];
        for (int i = 0; i < foodImages.length; i++) {
            foodImages[i] = R.drawable.beach_rendevous_icon;
        }

        return foodImages;
    }
}
