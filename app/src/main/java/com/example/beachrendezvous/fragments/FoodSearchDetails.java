package com.example.beachrendezvous.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.beachrendezvous.MainActivity;
import com.example.beachrendezvous.R;
import com.example.beachrendezvous.database.FoodEntity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FoodSearchDetails extends Fragment {

    //region Bundle paramters
    private static final String ARG_PARAM1 = "param1";  // Refers to restaurant
    private static final String ARG_PARAM2 = "param2";  // Refers to ???
    private static final String EVENT_ID = "event_id";
    private static final String ENTITY_OBJECT = "entityObject";
    private static final String CREATE_SEARCH = "createOrSearch";
    private String mParam1;
    private String mParam2;
    //endregion

    //region References
    private Unbinder mUnbinder;
    private String event_id;
    private String name;
    private FoodEntity foodEntity;
    //endregion

    //region ButterKnife binds

    //region ButterKnife BindViews
    @BindView(R.id.foodSearch_eventType)
    TextView mEventType;

    @BindView(R.id.foodSearch_dateText)
    TextView mDateText;

    @BindView(R.id.foodSearch_placeText)
    TextView mPlaceText;

    @BindView(R.id.foodSearch_timeText)
    TextView mTimeText;

    @BindView(R.id.foodSearch_numText)
    TextView mNumText;

    @BindView(R.id.foodSearch_commentText)
    TextView mCommentText;

    @BindView(R.id.foodSearch_durationText)
    TextView mDurationText;

    @BindView(R.id.foodSearch_limitText)
    TextView mLimitText;
    //endregion

    @OnClick(R.id.btn_foodSearch)
    void joinFoodEventClicked() {
        final DatabaseReference mDatabaseReference
                = FirebaseDatabase.getInstance()
                                  .getReference()
                                  .child("Users")
                                  .child(name)
                                  .child("Event_Id");

        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Fragment popUp = new popup();
        Bundle args = new Bundle();
        args.putString(MainActivity.ARG_GIVEN_NAME, name);
        args.putString(CREATE_SEARCH, "search");
        popUp.setArguments(args);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.frame_fragment, popUp)
                           .addToBackStack("search")
                           .commit();
    }

    //endregion

    public FoodSearchDetails() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
            event_id = getArguments().getString(EVENT_ID);
            name = getArguments().getString(MainActivity.ARG_GIVEN_NAME);

            foodEntity = (FoodEntity) getArguments().getSerializable(ENTITY_OBJECT);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;

        view = inflater.inflate(R.layout.fragment_food_search_details, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        if (mParam1 != null) {
            // Set TextViews from event data
            mDateText.setText(foodEntity.getDate());
            mPlaceText.setText(foodEntity.getLocation());
            mTimeText.setText(foodEntity.getTime());
            mNumText.setText(foodEntity.getNum_max());
            mCommentText.setText(foodEntity.getComments());
            mDurationText.setText(foodEntity.getDuration());
            mLimitText.setText(foodEntity.getLimit());
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
