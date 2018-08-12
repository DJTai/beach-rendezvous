package com.example.beachrendezvous.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beachrendezvous.MainActivity;
import com.example.beachrendezvous.R;
import com.example.beachrendezvous.database.SportsEntity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
public class SportsSearchDetails extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";  // param1 gives the type of sport
    private static final String ARG_PARAM2 = "param2";
    String event_id;
    String name;
    Unbinder mUnbinder;
    View view = null;
    SportsEntity sportsEntity;
    private static final String EVENT_ID="event_id";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @OnClick(R.id.sportsSearch_btn)
    void joinClicked() {
        Toast.makeText(getContext(), "join clicked", Toast.LENGTH_SHORT).show();
        Log.d("name",name);
        Log.d("event id",event_id);
        Log.d("mparam1",mParam1);
        DatabaseReference mDatabaseReference= FirebaseDatabase.getInstance().getReference().child("Users").child(name).child("Event_Id");
        mDatabaseReference.child(event_id).setValue(mParam1);



    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Use this to grab arguments passed from a previous fragment

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
            event_id=getArguments().getString(EVENT_ID);
            name=getArguments().getString(MainActivity.ARG_GIVEN_NAME);


            sportsEntity = (SportsEntity) getArguments().getSerializable("entityObject");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {


        if (mParam1 != null) {
            Log.i("Sports Search Details", "Running oncreateView");
            view = inflater.inflate(R.layout.fragment_sports_search_details, container, false);
            TextView type = view.findViewById(R.id.sportsSearch_eventType);
            TextView date = view.findViewById(R.id.searchEvent_dateText);
            date.setText(sportsEntity.getDate());
            TextView place = view.findViewById(R.id.searchEvent_placeText);
            place.setText(sportsEntity.getLocation());
            TextView time = view.findViewById(R.id.searchEvent_timeText);
            time.setText(sportsEntity.getTime());

            Log.i("Sports Search Details", "Running oncreateView");
            TextView people = view.findViewById(R.id.searchEvent_numText);
            people.setText(sportsEntity.getNum_max());
            Log.i("comments ", sportsEntity.getComments());
            TextView additionalInfo = view.findViewById(R.id.searchEvent_commentText);
            additionalInfo.setText(sportsEntity.getComments());
            Log.i("Duration",sportsEntity.getDuration());
            TextView duration = view.findViewById(R.id.searchEvent_DurationText);
            duration.setText(sportsEntity.getDuration());



        }
        // Bind view using ButterKnife
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

}
