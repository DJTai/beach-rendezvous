package com.example.beachrendezvous.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beachrendezvous.R;
import com.example.beachrendezvous.database.FoodEntity;
import com.example.beachrendezvous.database.SportsEntity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link SportsSearchDetails.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link SportsSearchDetails#newInstance} factory method to
// * create an instance of this fragment.
// */
public class FoodSearchDetails extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";  // param1 gives the type of sport
    private static final String ARG_PARAM2 = "param2";

    Unbinder mUnbinder;
    View view = null;
    FoodEntity foodEntity;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @OnClick(R.id.foodSearch_btn)
    void joinClicked() {
        Toast.makeText(getContext(), "join clicked", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Use this to grab arguments passed from a previous fragment

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
            foodEntity = (FoodEntity) getArguments().getSerializable("entityObject");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {


        if (mParam1 != null) {
            Log.i("Food Search Details", "Running oncreateView");
            view = inflater.inflate(R.layout.fragment_food_search_details, container, false);
            TextView type = view.findViewById(R.id.foodSearch_eventType);
            TextView date = view.findViewById(R.id.searchEvent_dateText);
            date.setText(foodEntity.getDate());
            TextView place = view.findViewById(R.id.searchEvent_placeText);
            place.setText(foodEntity.getLocation());
            TextView time = view.findViewById(R.id.searchEvent_timeText);
            time.setText(foodEntity.getTime());

            Log.i("Food Search Details", "Running oncreateView");
            TextView people = view.findViewById(R.id.searchEvent_numText);
            people.setText(foodEntity.getNum_max());
            Log.i("comments ", foodEntity.getComments());
            TextView additionalInfo = view.findViewById(R.id.searchEvent_commentText);
            additionalInfo.setText(foodEntity.getComments());
            Log.i("Duration",foodEntity.getDuration());
            TextView duration = view.findViewById(R.id.searchEvent_DurationText);
            duration.setText(foodEntity.getDuration());



        }
        // Bind view using ButterKnife
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

}
