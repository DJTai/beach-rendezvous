package com.example.beachrendezvous.fragments;

import android.content.Context;
import android.net.Uri;
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
import com.example.beachrendezvous.database.SportsEntity;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public class eventDetails extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    SportsEntity sportsEntity;
    View view = null;

    Unbinder mUnbinder;


    public eventDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment eventDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static eventDetails newInstance(String param1, String param2) {
        eventDetails fragment = new eventDetails();
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

            sportsEntity = (SportsEntity) getArguments().getSerializable("entityObject");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
                  Log.i("Sports Search Details", "Running oncreateView");
            view = inflater.inflate(R.layout.fragment_event_details, container, false);
            TextView type = view.findViewById(R.id.eventDetails_eventType);
            TextView date = view.findViewById(R.id.eventDetails_dateText);
            date.setText(sportsEntity.getDate());
            TextView place = view.findViewById(R.id.eventDetails_placeText);
            place.setText(sportsEntity.getLocation());
            TextView time = view.findViewById(R.id.eventDetails_timeText);
            time.setText(sportsEntity.getTime());

            TextView people = view.findViewById(R.id.eventDetails_numText);
            people.setText(sportsEntity.getNum_max());

            TextView additionalInfo = view.findViewById(R.id.eventDetails_commentText);
            additionalInfo.setText(sportsEntity.getComments());

            TextView duration = view.findViewById(R.id.eventDetails_DurationText);
            duration.setText(sportsEntity.getDuration());
            TextView limit = view.findViewById(R.id.eventDetails_limitText);
            limit.setText(sportsEntity.getLimit());


        // Bind view using ButterKnife
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }



}
