package com.example.beachrendezvous.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.beachrendezvous.MainActivity;
import com.example.beachrendezvous.R;
import com.example.beachrendezvous.database.FoodEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FoodSearchDetails extends Fragment {

    //region Bundle paramters
    private static final String ARG_PARAM1 = "param1";  // Refers to restaurant
    private static final String ARG_PARAM2 = "param2";  // Refers to ???
    private static final String EVENT_ID = "event_id";
    private static final String ENTITY_OBJECT = "entityObject";
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
    @BindView(R.id.foodSearch_eventType)
    TextView eventType;

    @BindView(R.id.foodSearch_dateText)
    TextView dateText;

    @BindView(R.id.foodSearch_placeText)
    TextView placeText;

    @BindView(R.id.foodSearch_timeText)
    TextView timeText;

    @BindView(R.id.foodSearch_numText)
    TextView numText;

    @BindView(R.id.foodSearch_commentText)
    TextView commentText;

    @BindView(R.id.foodSearch_durationText)
    TextView durationText;

    @BindView(R.id.foodSearch_limitText)
    TextView limitText;
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

        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // Unbind view to free some memory
        mUnbinder.unbind();
    }
}
