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
import android.widget.Toast;

import com.example.beachrendezvous.MainActivity;
import com.example.beachrendezvous.R;
import com.example.beachrendezvous.database.FoodEntity;
import com.example.beachrendezvous.sportsEventDetailsAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FoodEvents extends Fragment {

    //region Bundle parameters
    private static final String ARG_PARAM1 = "param1";    // Gives Restaurant
    private static final String ARG_PARAM2 = "param2";    //
    private static final String EVENT_ID = "event_id";
    private String mParam1;
    private String mParam2;
    //endregion

    //region References
    private String name;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;
    private Unbinder mUnbinder;
    private FragmentManager mFragManager;
    private sportsEventDetailsAdapter myAdapter;

    ArrayList<String> eventTypes = new ArrayList<>();
    ArrayList<String> eventDates = new ArrayList<>();
    ArrayList<String> eventPlaces = new ArrayList<>();
    ArrayList<String> eventAdmins = new ArrayList<>();
    ArrayList<FoodEntity> entityObjects = new ArrayList<>();
    ArrayList<String> eventIDs = new ArrayList<>();
    //endregion

    //region ButterKnife binds

    @BindView(R.id.header_foodEvents)
    TextView mHeader;

    @BindView(R.id.lv_foodEvents)
    ListView mListView;

    //endregion

    //region Constructors
    public FoodEvents() {
        // Required empty public constructor
    }
    //endregion

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
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

        // Inflate view and bind views
        view = inflater.inflate(R.layout.fragment_food_events, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        mHeader.setText(R.string.header_food_events);

        attachDatabaseReadListener();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), mParam1, Toast.LENGTH_SHORT).show();

                FragmentManager fragmentManager;
                FragmentTransaction fragmentTransaction;
                Fragment fragment = new FoodSearchDetails();
                Bundle args = new Bundle();
                args.putString(ARG_PARAM1, mParam1);
                args.putString(MainActivity.ARG_GIVEN_NAME, name);
                args.putString(EVENT_ID, eventIDs.get(i));
                fragment.setArguments(args);

                args.putSerializable("entityObject", entityObjects.get(i));

                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction
                        .replace(R.id.frame_fragment, fragment)
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
            // Refresh the list view with Firebase data
            mListView.setAdapter(myAdapter);
            myAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Pull data from Firebase
     */
    private void attachDatabaseReadListener() {
        // Only attach DB if there is no ChildEventListener
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    /*
                    Called whenever new data is inserted. It is also triggered for every child in
                     the list, when the listener is first attached.
                     DataSnapshot will contain the message that just got added to the DB.
                     */

                    // Deserialize event from DB -> POJO
                    FoodEntity foodEvent = dataSnapshot.getValue(FoodEntity.class);

                    if (!name.equals(foodEvent.getCreated_by())) {
                        eventIDs.add(dataSnapshot.getKey());
                        eventTypes.add(foodEvent.getType());
                        eventDates.add(foodEvent.getDate());
                        eventPlaces.add(foodEvent.getLocation());
                        eventAdmins.add(foodEvent.getCreated_by());

                        myAdapter = new sportsEventDetailsAdapter(
                                getContext().getApplicationContext(), eventTypes, eventDates,
                                eventPlaces, eventAdmins);

                        entityObjects.add(foodEvent);
                        mListView.setAdapter(myAdapter);
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };

            mDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }
}
