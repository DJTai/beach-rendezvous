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
import com.example.beachrendezvous.database.MoviesEntity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MoviesSearchDetails extends Fragment {

    private static final String ARG_PARAM1 = "param1";  // param1 gives the type of sport
    private static final String ARG_PARAM2 = "param2";
    private static final String CREATE_SEARCH = "createOrSearch";
    String event_id;
    String name;
    Unbinder mUnbinder;
    View view = null;
    MoviesEntity moviesEntity;
    int limit;
    private static final String EVENT_ID = "event_id";
    String ALREADY_JOINED = "alreadyJoined";

    private String mParam1;
    private String mParam2;

    @OnClick(R.id.moviesSearch_btn)
    void joinClicked() {

        final DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference()
                                                                     .child("Users")
                                                                     .child(name)
                                                                     .child("Event_Id");//.child
        // (event_id);
        // mDatabaseReference.setValue(mParam1);
        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.hasChild(event_id)) {
                    // run some code
                } else {
                    mDatabaseReference.child(event_id).setValue(mParam1);
                    int limit = Integer.parseInt(moviesEntity.getLimit()) - 1;
                    DatabaseReference mDatabaseReference1 = FirebaseDatabase.getInstance()
                                                                            .getReference()
                                                                            .child(mParam1)
                                                                            .child(event_id);
                    mDatabaseReference1.child("limit").setValue(Integer.toString(limit));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Fragment f = new popup();
        Bundle args = new Bundle();
        args.putString(MainActivity.ARG_GIVEN_NAME, name);
        args.putString(CREATE_SEARCH, "search");

        args.putString(
                ALREADY_JOINED,
                limit == Integer.parseInt(moviesEntity.getLimit()) ? "joined" : "new");

        f.setArguments(args);
        FragmentManager fragmentManager = getActivity()
                .getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction
                .replace(R.id.frame_fragment, f)
                .addToBackStack("search")
                .commit();


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Use this to grab arguments passed from a previous fragment

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
            event_id = getArguments().getString(EVENT_ID);
            name = getArguments().getString(MainActivity.ARG_GIVEN_NAME);


            moviesEntity = (MoviesEntity) getArguments().getSerializable("entityObject");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {


        if (mParam1 != null) {
            Log.i("Movies Search Details", "Running oncreateView");
            view = inflater.inflate(R.layout.fragment_movies_search_details, container, false);

            // Get TextViews
            TextView type = view.findViewById(R.id.moviesSearch_eventType);
            TextView date = view.findViewById(R.id.searchMoviesEvent_dateText);
            TextView place = view.findViewById(R.id.searchMoviesEvent_placeText);
            TextView time = view.findViewById(R.id.searchMoviesEvent_timeText);
            TextView people = view.findViewById(R.id.searchMoviesEvent_numText);
            TextView additionalInfo = view.findViewById(R.id.searchMoviesEvent_commentText);
            TextView duration = view.findViewById(R.id.searchMoviesEvent_DurationText);

            date.setText(moviesEntity.getDate());
            place.setText(moviesEntity.getLocation());
            time.setText(moviesEntity.getTime());
            people.setText(moviesEntity.getNum_max());
            additionalInfo.setText(moviesEntity.getComments());
            duration.setText(moviesEntity.getDuration());


        }
        // Bind view using ButterKnife
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

}
