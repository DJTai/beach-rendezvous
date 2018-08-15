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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.beachrendezvous.MainActivity;
import com.example.beachrendezvous.R;
import com.example.beachrendezvous.database.FoodEntity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static java.lang.String.format;

public class FoodCreateDetails extends Fragment {

    //region Logging Tags
    private static final String TAG = "FoodCreateDetails";
    private static final String DEBUG = "debug";
    private static final String EXCEPTION = "Exception";
    private static final String CREATE_SEARCH = "createOrSearch";
    //endregion

    //region Bundle parameters
    private static final String ARG_PARAM1 = "param1";    // Refers to the restaurant
    private static final String ARG_PARAM2 = "param2";    // Refers to ???
    private String mParam1;
    private String mParam2;
    //endregion

    //region References
    private Unbinder mUnbinder;
    private String name;
    View view = null;
    private DatabaseReference databaseFood;
    //endregion

    //region ButterKnife Binds

    //region ButterKnife BindViews
    @BindView(R.id.foodCreate_eventType)
    TextView tvHeader;

    @BindView(R.id.btn_foodCreate)
    Button mBtnCreate;

    @BindView(R.id.foodCreate_dateText)
    TextView mDate;

    @BindView(R.id.foodCreate_placeText)
    TextView mPlace;

    @BindView(R.id.foodCreate_timeText)
    EditText mTime;

    @BindView(R.id.foodCreate_durationText)
    EditText mDuration;

    @BindView(R.id.foodCreate_numText)
    EditText mNumOfPeople;

    @BindView(R.id.foodCreate_commentText)
    TextView mComments;
    //endregion

    @OnClick(R.id.foodCreate_dateText)
    void selectDate() {
        // Get current date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        final EditText date = view.findViewById(R.id.foodCreate_dateText);

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

    @OnClick(R.id.foodCreate_timeText)
    void selectTime() {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);
        final EditText time = view.findViewById(R.id.foodCreate_timeText);

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

    @OnClick(R.id.btn_foodCreate)
    void createClicked() {
        DatabaseReference mDatabaseReference;
        FoodEntity foodEvent;
        String id;

        // Grab Strings from views
        String foodDate = mDate.getText().toString();
        String foodPlace = mPlace.getText().toString();
        String foodTime = mTime.getText().toString();
        String foodNum = mNumOfPeople.getText().toString();     // !! - Could be null
        String foodDuration = mDuration.getText().toString();   // !! - Could be null
        String foodComments = mComments.getText().toString();

        if (foodDate.equals("") || foodTime.equals("") || foodNum.equals("")) {

            // TODO: Alert user that certain fields are empty
            foodEvent = new FoodEntity(foodDate, foodPlace, foodTime, foodNum, foodDuration,
                                       foodComments, name, mParam1);
            Log.d(TAG, "createClicked: " + foodEvent.toString());
        } else {
            // All fields filled - add to Firebase DB

            // Data validation
            if (foodDuration.equals("")) {
                foodDuration = "n/a";
            }
            if (foodComments.equals("")) {
                foodComments = "n/a";
            }

            foodEvent = new FoodEntity(foodDate, foodPlace, foodTime, foodNum, foodDuration,
                                       foodComments, name, mParam1);

            // Add data to Firebase
            id = databaseFood.push().getKey();
            databaseFood.child(id).setValue(foodEvent);
            mDatabaseReference = FirebaseDatabase.getInstance().getReference()
                                                 .child("Users").child(name).child("Event_Id");
            mDatabaseReference.child(id).setValue(mParam1);

            Fragment popUp = new popup();
            Bundle args = new Bundle();
            args.putString(MainActivity.ARG_GIVEN_NAME, name);
            args.putString(CREATE_SEARCH, "create");
            popUp.setArguments(args);

            FragmentManager fragmentManager = getActivity()
                    .getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager
                    .beginTransaction();
            fragmentTransaction
                    .replace(R.id.frame_fragment, popUp)
                    .addToBackStack("create")
                    .commit();
        }

    }
    //endregion

    //region Constructors
    public FoodCreateDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FoodCreateDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static FoodCreateDetails newInstance(String param1, String param2) {
        FoodCreateDetails fragment = new FoodCreateDetails();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    //endregion

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
            name = getArguments().getString(MainActivity.ARG_GIVEN_NAME);
        }

        // Get the DB reference for the specified restaurant
        databaseFood = FirebaseDatabase.getInstance().getReference(mParam1);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (mParam1 != null) {
            view = inflater.inflate(R.layout.fragment_food_create_details, container, false);
        }

        // Bind view
        mUnbinder = ButterKnife.bind(this, view);

        tvHeader.setText(R.string.food_create_header);
        mPlace.setText(mParam1);

        return view;
    }
}
