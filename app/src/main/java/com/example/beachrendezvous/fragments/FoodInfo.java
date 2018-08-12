package com.example.beachrendezvous.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.beachrendezvous.MainActivity;
import com.example.beachrendezvous.R;
import com.example.beachrendezvous.sportsListAdapter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FoodInfo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";    // Refers to search OR create
    private static final String ARG_PARAM2 = "param2";    // Refers to food

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Unbinder mUnbinder;
    private ListView mListView;
    String name;

    public FoodInfo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FoodInfo.
     */
    // TODO: Rename and change types and number of parameters
    public static FoodInfo newInstance(String param1, String param2) {
        FoodInfo fragment = new FoodInfo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            name = getArguments().getString(MainActivity.ARG_GIVEN_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food_info, container, false);
        TextView header = view.findViewById(R.id.header_foodInfo);

        if (mParam1 != null) {
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
                        Fragment fragment;
                        Bundle args;
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                        if (mParam1.trim().equals("search")) {
                            fragment = new FoodEvents();
                            args = new Bundle();
                            args.putString(ARG_PARAM1, getRestaurants()[i]);
                            args.putString(MainActivity.ARG_GIVEN_NAME, name);
                            fragment.setArguments(args);

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

                            FragmentTransaction fragmentTransaction = fragmentManager
                                    .beginTransaction();

                            fragmentTransaction
                                    .replace(R.id.frame_fragment, fragment)
                                    .addToBackStack(mParam1)
                                    .commit();
                        }
                    }
                });

            } else {
                header.setText(R.string.action_info);
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
