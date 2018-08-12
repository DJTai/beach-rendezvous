package com.example.beachrendezvous.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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

import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MainMenu extends Fragment {

    /* Logging Tags */
    public static final String TAG = "mainMenu_fragment";
    private static final String DEBUG = "debug";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Unbinder mUnbinder;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    ChildEventListener eventchildEventListener;
    String name;
    ListView mListView;
    ArrayList<String> type1 = new ArrayList<>();
    ArrayList<String> date1 = new ArrayList<>();
    ArrayList<String> place1 = new ArrayList<>();


    public MainMenu() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
                     name = getArguments().getString(MainActivity.ARG_GIVEN_NAME);

        }
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(name).child("Event_Id");
        Log.d("Menu datbasereference",mDatabaseReference.getKey());
        attachDatabaseReadListener();

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(DEBUG, "onCreateView: Main Menu OCV");

        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        mListView = (ListView) view.findViewById(R.id.mainMenu_listview);


        mUnbinder = ButterKnife.bind(this, view);
        attachDatabaseReadListener();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // Unbind the view to free some memory
        mUnbinder.unbind();
    }
    private void attachDatabaseReadListener() {
        // Only attach DB if there is no ChildEventListener
        Log.d("in mchildeventlistner", " test");
        if (eventchildEventListener == null) {
            eventchildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    // Called whenever a new msg is inserted in the msg's list
                    // Triggered for every child msg in the list when the listener is first attached
                    // - DataSnapshot will contain the message that just got added to the DB

                   for(DataSnapshot ds : dataSnapshot.getChildren()) {
                        //String friend = ds.getKey();
                       // DatabaseReference mDatabaseReference1=FirebaseDatabase.getInstance().getReference().child("Users").child(name).child("Event_Id");
                      //  mDatabaseReference.child(id).setValue(true);
                       Log.d("In main menu ", ds.getKey());
                    }
/*
                    SportsEntity sportsevent = dataSnapshot.getValue(
                            SportsEntity.class);    // Deserialize msg from DB -> POJO

                    type1.add(sportsevent.getType());
                    date1.add(sportsevent.getDate());
                    place1.add(sportsevent.getLocation());
                    sportsEventDetailsAdapter myAdapter = new sportsEventDetailsAdapter(
                            getContext().getApplicationContext(), type1, date1, place1,
                            sportsevent.getCreated_by());
                    //sportsEventDetailsAdapter m=new sportsEventDetailsAdapter(getContext()
                    // .getApplicationContext(), )
                   // entityObjects.add(sportsevent);
                    mListView.setAdapter(myAdapter);
                    */
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
            mDatabaseReference.addChildEventListener(eventchildEventListener);
        }
    }


}
