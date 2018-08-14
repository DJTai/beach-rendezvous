package com.example.beachrendezvous.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.beachrendezvous.MainActivity;
import com.example.beachrendezvous.R;
import com.example.beachrendezvous.database.SportsEntity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.R.layout.simple_spinner_item;
import static java.lang.String.format;


public class SportsCreateDetails extends Fragment {

    private String mParam;
    private static final String TAG = "sports_create_details";
    // KEY for getArguments()
    private static final String ARG_PARAM1 = "param1";

    //region References
    Unbinder mUnbinder;
    Spinner spinner;
    String name;
    View view = null;
    private static final String CREATE_SEARCH = "createOrSearch";

    DatabaseReference databaseSports;
    //endregion

    //region ButterKnife Binds
    @BindView(R.id.createEvent_commentLabel)
    TextView commentTextView;

    @OnClick(R.id.sportsCreate_btn)
    void joinClicked() {
        try {
            TextView date = (TextView) view.findViewById(R.id.createEvent_dateText);
            Spinner place = (Spinner) view.findViewById(R.id.createEvent_placeText);
            EditText time = (EditText) view.findViewById(R.id.createEvent_timeText);
            EditText duration = (EditText) view.findViewById(R.id.createEvent_DurationText);
            EditText people = (EditText) view.findViewById(R.id.createEvent_numText);
            String dob_var;
            TextView comments = (TextView) view.findViewById(R.id.createEvent_commentText);
            dob_var = date.getText().toString();
            String time1 = time.getText().toString();
            SportsEntity eventdetais = new SportsEntity(name, time1, duration.getText().toString(),
                                                        dob_var, place.getSelectedItem().toString(),
                                                        people.getText().toString(),
                                                        comments.getText().toString(), mParam,people.getText().toString());

            String id = databaseSports.push().getKey();
            databaseSports.child(id).setValue(eventdetais);
            DatabaseReference mDatabaseReference=FirebaseDatabase.getInstance().getReference().child("Users").child(name).child("Event_Id");
            mDatabaseReference.child(id).setValue(mParam);
            Fragment f = new popup();
            Bundle args = new Bundle();
            args.putString(MainActivity.ARG_GIVEN_NAME, name);
            args.putString(CREATE_SEARCH,"create");
            f.setArguments(args);
            FragmentManager fragmentManager = getActivity()
                    .getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager
                    .beginTransaction();
            fragmentTransaction
                    .replace(R.id.frame_fragment, f)
                    .addToBackStack("create")
                    .commit();


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.i("Exception", e.toString());
        }
    }

    @OnClick(R.id.createEvent_dateText)
    void selectDate() {
        // Get current date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        final EditText date = view.findViewById(R.id.createEvent_dateText);

        DatePickerDialog datePickerDialog =
                new DatePickerDialog(this.getContext(),
                                     new DatePickerDialog.OnDateSetListener() {
                                         @Override
                                         public void onDateSet(DatePicker view, int year,
                                                               int monthOfYear, int dayOfMonth) {
                                             date.setText(format("%d/%d/%d", monthOfYear + 1,
                                                                 dayOfMonth, year));
                                         }
                                     }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @OnClick(R.id.createEvent_timeText)
    void selectTime() {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);
        final EditText time = view.findViewById(R.id.createEvent_timeText);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog =
                new TimePickerDialog(this.getContext(),
                                     new TimePickerDialog.OnTimeSetListener() {
                                         @Override
                                         public void onTimeSet(TimePicker view, int hourOfDay,
                                                               int minute) {
                                             time.setText(format("%d:%d", hourOfDay, minute));
                                         }
                                     }, mHour, mMinute, true);
        timePickerDialog.show();
    }

    //endregion

    public SportsCreateDetails() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam = getArguments().getString(ARG_PARAM1);
            name = getArguments().getString(MainActivity.ARG_GIVEN_NAME);
            Log.i(TAG, "onCreate: mParam = " + mParam);

        }

        databaseSports = FirebaseDatabase.getInstance().getReference(mParam);
    }

    public void addItemsOnSpinner(View v1) {

        spinner = (Spinner) v1.findViewById(R.id.createEvent_placeText);
        List<String> list = new ArrayList<String>();
        list.add("-");
        list.add("USU");
        list.add("Student Recreation Center");
        list.add("Near Brotman Hall");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
                getActivity().getApplicationContext(),
                simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
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
