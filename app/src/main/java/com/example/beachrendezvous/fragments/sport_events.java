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
import com.example.beachrendezvous.database.SportsEntity;
import com.example.beachrendezvous.sportsEventDetailsAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class sport_events extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";  // param1 gives the type of sport
    private static final String EVENT_ID="event_id";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ListView mListView;
    String name;
    ArrayList<String> type1 = new ArrayList<>();
    ArrayList<String> date1 = new ArrayList<>();
    ArrayList<String> place1 = new ArrayList<>();
    ArrayList<String> admin = new ArrayList<>();
    ArrayList<SportsEntity> entityObjects = new ArrayList<>();
    ArrayList<String> event_id = new ArrayList<>();
    FragmentManager mFragManager;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    ChildEventListener mChildEventListener;
    private sportsEventDetailsAdapter myAdapter;

    public sport_events() {
        // Required empty public constructor
    }

    private void attachDatabaseReadListener() {
        // Only attach DB if there is no ChildEventListener
        Log.d("in mchildeventlistner", " test");
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    // Called whenever a new msg is inserted in the msg's list
                    // Triggered for every child msg in the list when the listener is first attached
                    // - DataSnapshot will contain the message that just got added to the DB
                    SportsEntity sportsevent = dataSnapshot.getValue(
                            SportsEntity.class);    // Deserialize msg from DB -> POJO
                    event_id.add(dataSnapshot.getKey());
                    type1.add(sportsevent.getType());
                    date1.add(sportsevent.getDate());
                    place1.add(sportsevent.getLocation());
                    admin.add(sportsevent.getCreated_by());

                    myAdapter = new sportsEventDetailsAdapter(
                            getContext().getApplicationContext(), type1, date1, place1,
                            admin);
                    //sportsEventDetailsAdapter m=new sportsEventDetailsAdapter(getContext()
                    // .getApplicationContext(), )
                    entityObjects.add(sportsevent);
                    mListView.setAdapter(myAdapter);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    // Called when the contents of an existing msg  is changed
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    // Called when a msg is deleted
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                    // Called when a msg changes position in the list
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Called when an error occurred while trying to make changes
                    // Usually called when permission is denied to read data
                }
            };
            mDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            // Get the type of sport
            mParam1 = getArguments().getString(ARG_PARAM1);
            Log.i("in sportevnts argu size",Integer.toString(getArguments().size()));
            name = getArguments().getString(MainActivity.ARG_GIVEN_NAME);
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mDatabaseReference = mFirebaseDatabase.getReference().child(mParam1);
            attachDatabaseReadListener();

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        TextView header;

        view = inflater.inflate(R.layout.fragment_sportevents, container, false);

        Log.d("sport_events", "Setting header");
        header = (TextView) view.findViewById(R.id.gameheader);
        header.setText("Lets find the best moment and best place to play " + mParam1 + "!");

        mListView = (ListView) view.findViewById(R.id.listview1);
        attachDatabaseReadListener();

        mListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(getContext(), mParam1, Toast.LENGTH_SHORT).show();
                        FragmentManager fragmentManager;
                        FragmentTransaction fragmentTransaction;
                        Fragment f = new SportsSearchDetails();

                        Bundle args = new Bundle();
                        args.putString(ARG_PARAM1, mParam1);
                        args.putString(MainActivity.ARG_GIVEN_NAME, name);
                        Log.i("name in sportevent",name);
                        args.putString(EVENT_ID,event_id.get(i));
                        f.setArguments(args);
                        args.putSerializable("entityObject", entityObjects.get(i));


                        fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction
                                .replace(R.id.frame_fragment, f)
                                .addToBackStack("search")
                                .commit();
                    }
                }
                                        );


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (myAdapter != null) {
            // Refresh the list view with the data from Firebase
            mListView.setAdapter(myAdapter);
            myAdapter.notifyDataSetChanged();
        }
    }
}
