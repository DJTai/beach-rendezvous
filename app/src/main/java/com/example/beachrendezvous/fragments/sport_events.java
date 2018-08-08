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
import android.widget.Toast;

import com.example.beachrendezvous.MainActivity;
import com.example.beachrendezvous.R;
import com.example.beachrendezvous.sportsEventDetailsAdapter;


public class sport_events extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String name;

    public sport_events() {
        // Required empty public constructor
    }

     @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            name=getArguments().getString(MainActivity.ARG_GIVEN_NAME);

                  }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  view = inflater.inflate(R.layout.fragment_sportevents, container, false);
        TextView header = view.findViewById(R.id.gameheader);

        Log.d("sport_events", "Setting header");

        header.setText("");
        header = (TextView) view.findViewById(R.id.gameheader);
        header.setText("Find the best moment & place to play " + mParam1 + "!");

        ListView mListView = (ListView) view.findViewById(R.id.listview1);
        String[] type1={"Indoor", "Outdoor", "Indoor", "Outdoor"};
        String [] date1={"07/9", "08/10", "09/20", "07/5"};
        String[] place1 ={"Gym", "ASI", "Pyramid", "Place4"};
        sportsEventDetailsAdapter myAdapter = new sportsEventDetailsAdapter(getContext().getApplicationContext(), type1, date1, place1);
        mListView.setAdapter(myAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), mParam1, Toast.LENGTH_SHORT).show();
                Fragment f = new SportsSearchDetails();
                Bundle args = new Bundle();
                args.putString(ARG_PARAM1, mParam1);
                args.putString(MainActivity.ARG_GIVEN_NAME, name);
                f.setArguments(args);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction
                        .replace(R.id.frame_fragment, f)
                        .addToBackStack("search")
                        .commit();
            }
        }
        ) ;


        return view;
    }

    }
