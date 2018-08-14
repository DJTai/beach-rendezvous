package com.example.beachrendezvous.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

public class MoviesInfo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //region References

    /** Value retrieved from ARG_PARAM1 key */
    private String mParam1;

    /** Value retrieved from ARG_PARAM2 key */
    private String mParam2;

    /** ButterKnife Unbinder */
    private Unbinder mUnbinder;

    /** ListView for displaying the movies events */
    private ListView mListView;

    /** Movies event creator's name */
    String name;

    //endregion

    public MoviesInfo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoviesInfo.
     */
    // TODO: Rename and change types and number of parameters
    public static MoviesInfo newInstance(String param1, String param2) {
        MoviesInfo fragment = new MoviesInfo();
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
        View view = inflater.inflate(R.layout.fragment_movies_info, container, false);
        TextView header = view.findViewById(R.id.header_moviesInfo); //fragment_movies.info.xml

        if (mParam1 != null) {
            if (mParam2.trim().equals("movies")) {
                sportsListAdapter moviesAdapter;

                if (mParam1.trim().equals("search")) {
                    header.setText(R.string.movies_info_search);
                } else {
                    header.setText(R.string.movies_info_create);
                }

                moviesAdapter = new sportsListAdapter(getContext().getApplicationContext(),
                        getMovies(),
                        getMoviesImages());
                mListView = view.findViewById(R.id.listView_movies);
                mListView.setAdapter(moviesAdapter);
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Fragment fragment;
                        Bundle args;
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                        if (mParam1.trim().equals("search")) {
                            fragment = new MoviesEvents();
                            args = new Bundle();
                            args.putString(ARG_PARAM1, getMovies()[i]);
                            args.putString(MainActivity.ARG_GIVEN_NAME, name);
                            fragment.setArguments(args);

                            FragmentTransaction fragmentTransaction = fragmentManager
                                    .beginTransaction();

                            fragmentTransaction
                                    .replace(R.id.frame_fragment, fragment)
                                    .addToBackStack(mParam1)
                                    .commit();

                        } else if (mParam1.equals("create")) {
                            fragment = new MoviesCreateDetails();
                            args = new Bundle();
                            args.putString(ARG_PARAM1, getMovies()[i]);
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
     * Retrieves a list of all of the movies on campus
     *
     * @return - String array of movies on campus
     */
    private final String[] getMovies() {
        String[] movies = {
                "Titanic",
                "Creed",
                "Thor Ragnarok",
                "Justice League",
                "Coco"
        };

        return movies;
    }


    /**
     * TODO: Use different logos
     *
     * @return - int array of logos
     */
    private final int[] getMoviesImages() {
        int[] moviesImages = new int[getMovies().length];
        for (int i = 0; i < moviesImages.length; i++) {
            moviesImages[i] = R.drawable.beach_rendevous_icon;
        }

        return moviesImages;
    }

}
