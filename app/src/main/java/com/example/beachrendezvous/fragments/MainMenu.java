package com.example.beachrendezvous.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainMenu extends Fragment {

    private static final String DEBUG = "debug";

    //region Bundle parameters
    private static final String ARG_PARAM1 = "param1";  // param1 gives the type of sport
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    //endregion

    ListView mListView;
    String name;
    private static final String EVENT_ID = "event_id";
    ArrayList<String> type1 = new ArrayList<>();
    ArrayList<String> date1 = new ArrayList<>();
    ArrayList<String> place1 = new ArrayList<>();
    ArrayList<String> admin = new ArrayList<>();
    ArrayList<String> event_id = new ArrayList<>();
    ArrayList<SportsEntity> entityObjects = new ArrayList<>();
    FragmentManager mFragManager;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    ChildEventListener mChildEventListener;
    private sportsEventDetailsAdapter myAdapter;

    public MainMenu() {
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
                    Log.i("onchildadd", dataSnapshot.getKey());
                    final DatabaseReference mDatabaseReference1 =
                            FirebaseDatabase.getInstance()
                                            .getReference()
                                            .child(dataSnapshot.getValue().toString())
                                            .child(dataSnapshot.getKey());

                    mDatabaseReference1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            SportsEntity sportsevent = dataSnapshot.getValue(
                                    SportsEntity.class);
//                            Log.i("in menu created", sportsevent.getCreated_by());
                            event_id.add(dataSnapshot.getKey());

                            type1.add(sportsevent.getType());
                            date1.add(sportsevent.getDate());
                            place1.add(sportsevent.getLocation());
                            if (sportsevent.getCreated_by().equals(name)) {
                                admin.add("My Event");
                            } else {
                                admin.add(sportsevent.getCreated_by());
                            }

                            myAdapter = new sportsEventDetailsAdapter(
                                    getContext().getApplicationContext(),
                                    type1,
                                    date1,
                                    place1,
                                    admin);
                            //sportsEventDetailsAdapter m=new sportsEventDetailsAdapter(getContext()
                            // .getApplicationContext(), )
                            entityObjects.add(sportsevent);
                            mListView.setAdapter(myAdapter);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //
                        }
                    });
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
            name = getArguments().getString(MainActivity.ARG_GIVEN_NAME);
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mDatabaseReference = mFirebaseDatabase.getReference().child("Users").child(name)
                                                  .child("Event_Id");
//            attachDatabaseReadListener();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        TextView header;

        view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        Log.d("sport_events", "Setting header");
        header = (TextView) view.findViewById(R.id.mainMenuHeader);
        header.setText("WELCOME");

        mListView = (ListView) view.findViewById(R.id.mainMenu_listview);
        attachDatabaseReadListener();
        mListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //Toast.makeText(getContext(), mParam1, Toast.LENGTH_SHORT).show();
                        FragmentManager fragmentManager;
                        FragmentTransaction fragmentTransaction;
                        Fragment f = new eventDetails();

                        Bundle args = new Bundle();
                        //args.putString(ARG_PARAM1, mParam1);
                        args.putString(MainActivity.ARG_GIVEN_NAME, name);
                        Log.i("name in sportevent", name);
                        args.putString(EVENT_ID, event_id.get(i));
                        f.setArguments(args);
                        args.putSerializable("entityObject", entityObjects.get(i));
                        fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction
                                .replace(R.id.frame_fragment, f)
                                .addToBackStack("search")
                                .commit();
                    }
                });


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
