package com.example.beachrendezvous.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beachrendezvous.MainActivity;
import com.example.beachrendezvous.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class SubMenu extends Fragment {

    /* Logging Tags */
    private static final String TAG = "sub_menu_fragment";
    private static final String DEBUG = "debug";
    private static final String ARG_PARAM = "param";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    /* Parameter to determine the context of the SubMenu fragment, e.g., Creating or Searching */
    private String mParam;
    String name;

    Unbinder mUnbinder;

    //region Handle button clicks
    @OnClick(R.id.subMenu_btn_food)
    void foodClicked() {
        if (mParam != null) {
            String msg = "";
            if (mParam.equals("search")) {
                // TODO: Handle searching for food
                msg = "search food opened";
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();


            } else {
                // TODO: Handle creating an event for food
                msg = "create food opened";
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

                Fragment fragment = new FoodInfo();
                Bundle args = new Bundle();
                args.putString(ARG_PARAM1, "create");
                args.putString(ARG_PARAM2, "food");
                args.putString(MainActivity.ARG_GIVEN_NAME, name);
                fragment.setArguments(args);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction
                        .replace(R.id.frame_fragment, fragment)
                        .addToBackStack(ARG_PARAM1)
                        .commit();
            }
        }
    }

    @OnClick(R.id.subMenu_btn_movies)
    void moviesClicked() {
        if (mParam != null) {
            String msg = "";
            if (mParam.equals("search")) {
                // TODO: Handle searching for a movie
                msg = "search movies opened";
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

            } else {
                // TODO: Handle creating an event for a movie
                msg = "create movies opened";
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @OnClick(R.id.subMenu_btn_sports)
    void sportsClicked() {
        if (mParam != null) {
            String msg = "";
            if (mParam.equals("search")) {
                msg = "search sports opened";
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                Fragment f = new sportinfo();
                Bundle args = new Bundle();
                args.putString(ARG_PARAM1, "search");
                args.putString(ARG_PARAM, "sports");
                f.setArguments(args);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction
                        .replace(R.id.frame_fragment, f)
                        .addToBackStack(ARG_PARAM1)
                        .commit();

            } else {
                msg = "create sports opened";
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                Fragment f = new sportinfo();
                Bundle args = new Bundle();
                args.putString(ARG_PARAM1, "create");
                args.putString(ARG_PARAM, "sports");
                args.putString(MainActivity.ARG_GIVEN_NAME, name);
                f.setArguments(args);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction
                        .replace(R.id.frame_fragment, f)
                        .addToBackStack(ARG_PARAM1)
                        .commit();
            }
        }
    }
    //endregion


    public SubMenu() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getString(ARG_PARAM);
            name = getArguments().getString(MainActivity.ARG_GIVEN_NAME);
            Log.i(TAG, "onCreate: mParam = " + mParam);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(DEBUG, "onCreateView: Sub Menu OCV");

        View view = null;

        if (mParam != null) {
            if (mParam.equals("create")) {
                Log.i(TAG, "onCreateView: param equals create");
                view = inflater.inflate(R.layout.fragment_sub_menu, container, false);
                TextView header = view.findViewById(R.id.subMenu_header);
                header.setText(R.string.submenu_header_create);

            } else {
                Log.i(TAG, "onCreateView: param equals search");
                view = inflater.inflate(R.layout.fragment_sub_menu, container, false);
                TextView header = view.findViewById(R.id.subMenu_header);
                header.setText(R.string.submenu_header_search);
            }
        }
        mUnbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // Unbind view to free some memory
        mUnbinder.unbind();
    }
}
