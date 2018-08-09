package com.example.beachrendezvous.fragments;

import android.content.Intent;
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
import android.widget.Toast;

import com.example.beachrendezvous.MainActivity;
import com.example.beachrendezvous.R;
import com.example.beachrendezvous.sportsListAdapter;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class sportinfo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam;
    // TODO: Rename and change types of parameters
    private String mParam1;
    Unbinder mUnbinder;
    ListView mListView;
    String name;

    public sportinfo() {
        // Required empty public constructor
    }
    //ListView mlistView=(ListView) getActivity().getvi


    public ListView getmListView() {
        return mListView;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getString("param");
            mParam1 = getArguments().getString("param1");
            name = getArguments().getString(MainActivity.ARG_GIVEN_NAME);
            Log.i("search", "onCreate: mParam = " + mParam);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;

        if (mParam != null) {
            if (mParam.trim().equals("sports")) {
                Log.i("serach", "onCreateView: type equals create");
                view = inflater.inflate(R.layout.fragment_sportinfo, container, false);
                TextView header = view.findViewById(R.id.sportsheader);
                sportsListAdapter myAdapter;

                if (mParam1.trim().equals("search")) {
                    header.setText("Join a game! What Sport?");
                } else {
                    header.setText("Create a Game! What Sport?");
                }

                final String[] gameNames = {"SOCCER", "TENNNIS", "BASEBALL", "FOOTBALL",
                        "VOLLEYBALL", "SOFTBALL"};

                int[] gameImages = {
                        R.drawable.soccer,
                        R.drawable.tennis,
                        R.drawable.baseball,
                        R.drawable.football,
                        R.drawable.volleyball,
                        R.drawable.softball
                };
                Log.d("adapter", "onCreateView: before sports adapter");
                myAdapter = new sportsListAdapter(getContext().getApplicationContext(), gameNames,
                                                  gameImages);

                mListView = (ListView) view.findViewById(R.id.listview);
                mListView.setAdapter(myAdapter);
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        if (mParam1.trim().equals("search")) {
                            String msg = "games[i]";
                            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                            Fragment f = new sport_events();
                            Bundle args = new Bundle();
                            args.putString(ARG_PARAM1, gameNames[i]);
                            args.putString(MainActivity.ARG_GIVEN_NAME, name);
                            f.setArguments(args);

                            FragmentManager fragmentManager = getActivity()
                                    .getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager
                                    .beginTransaction();
                            fragmentTransaction
                                    .replace(R.id.frame_fragment, f)
                                    .addToBackStack("search")
                                    .commit();

                        } else if (mParam1.equals("create")) {

                            Fragment f = new SportsCreateDetails();
                            Bundle args = new Bundle();
                            args.putString(ARG_PARAM1, gameNames[i]);
                            args.putString(MainActivity.ARG_GIVEN_NAME, name);
                            f.setArguments(args);

                            FragmentManager fragmentManager = getActivity()
                                    .getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager
                                    .beginTransaction();
                            fragmentTransaction
                                    .replace(R.id.frame_fragment, f)
                                    .addToBackStack("create")
                                    .commit();
                        }


                    }
                });
            } else {
                Log.i("search", "onCreateView: type not equal to create, mparam=" + mParam);
                view = inflater.inflate(R.layout.fragment_sportinfo, container, false);
                TextView header = view.findViewById(R.id.sportsheader);
                header.setText(R.string.action_info);
            }
        }
        Log.i("search", "on search view:  mparam=" + mParam);
        // Bind view using ButterKnife
        mUnbinder = ButterKnife.bind(this, view);

        return view;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    /*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    */
}
