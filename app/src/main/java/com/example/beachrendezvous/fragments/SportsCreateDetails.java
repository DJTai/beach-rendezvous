package com.example.beachrendezvous.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beachrendezvous.MainActivity;
import com.example.beachrendezvous.R;
import com.example.beachrendezvous.database.SportsEntity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.R.layout.simple_spinner_item;


public class SportsCreateDetails extends Fragment {

    private String mParam;
    private static final String TAG = "sports_create_details";
    // KEY for getArguments()
    private static final String ARG_PARAM1 = "param1";

    Unbinder mUnbinder;
    Spinner spinner;
    String name;
    View view = null;

    DatabaseReference databaseSports;


    // TODO: MAKE BINDING CALLS TO EVERY THING ON THE XML LAYOUT
    @BindView(R.id.createEvent_commentLabel)
    TextView commentTextView;

    public SportsCreateDetails () {
        // Required empty public constructor
    }

    @OnClick(R.id.sportsCreate_btn)
    void joinClicked () {

        try {
           // Toast.makeText(getContext(), "create button clicked", Toast.LENGTH_SHORT).show();
            TextView date =(TextView) view.findViewById(R.id.createEvent_dateText);
            Spinner place = (Spinner)view.findViewById(R.id.createEvent_placeText);
            EditText time = (EditText)view.findViewById(R.id.createEvent_timeText);
            EditText people = (EditText)view.findViewById(R.id.createEvent_numText);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String dob_var;
            TextView comments = (TextView)view.findViewById(R.id.createEvent_commentText);
             dob_var = date.getText().toString();
            String time1 = time.getText().toString();
            SportsEntity eventdetais=new SportsEntity(name, time1,dob_var,place.getSelectedItem().toString(),Integer.parseInt(people.getText().toString()),comments.getText().toString(), mParam);
           // Toast.makeText(getContext(), eventdetais.getDate().toString(), Toast.LENGTH_SHORT).show();
            Toast.makeText(getContext(), eventdetais.toString(), Toast.LENGTH_SHORT).show();

            String id = databaseSports.push().getKey();
            databaseSports.child(id).setValue(eventdetais);
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.i("Exception", e.toString());
        }

    }


    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam = getArguments().getString(ARG_PARAM1);
            name=getArguments().getString(MainActivity.ARG_GIVEN_NAME);
            Log.i(TAG, "onCreate: mParam = " + mParam);
        }

        databaseSports = FirebaseDatabase.getInstance().getReference("sports");

    }

    public void addItemsOnSpinner(View v1) {

        spinner = (Spinner) v1.findViewById(R.id.createEvent_placeText);
        List<String> list = new ArrayList<String>();
        list.add("USU");
        list.add("Student Recreation Center");
        list.add("Near Brotman Hall");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater,
                              @Nullable ViewGroup container,
                              @Nullable Bundle savedInstanceState) {



        if (mParam != null) {
                Log.i(TAG, "sports create details");
                view = inflater.inflate(R.layout.fragment_sports_create_event, container, false);
            addItemsOnSpinner(view);


        }
        // Bind view using ButterKnife
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }


}
