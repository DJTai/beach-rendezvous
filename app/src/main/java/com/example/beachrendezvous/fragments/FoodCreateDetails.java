package com.example.beachrendezvous.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

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
    private final Calendar c = Calendar.getInstance();
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
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog =
                new DatePickerDialog(this.getContext(),
                                     new DatePickerDialog.OnDateSetListener() {
                                         @Override
                                         public void onDateSet(DatePicker view, int year,
                                                               int monthOfYear, int dayOfMonth) {
                                             mDate.setText(format("%d/%d/%d", monthOfYear + 1,
                                                                  dayOfMonth, year));

                                             mDate.setError(null);
                                         }
                                     }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @OnClick(R.id.foodCreate_timeText)
    void selectTime() {
        // Get current Time
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog =
                new TimePickerDialog(this.getContext(),
                                     new TimePickerDialog.OnTimeSetListener() {
                                         @Override
                                         public void onTimeSet(TimePicker view, int hourOfDay,
                                                               int minute) {
                                             mTime.setText(format("%d:%02d", hourOfDay, minute));
                                             mTime.setError(null);
                                         }
                                     }, mHour, mMinute, true);
        timePickerDialog.show();
    }

    @OnClick(R.id.btn_foodCreate)
    void createFoodEventClicked() {
        DatabaseReference mDatabaseReference;
        FoodEntity foodEvent;
        String id;
        String dateIs = "";
        boolean validDate = false;
        boolean validTime = false;

        // Grab Strings from views
        String foodDate = mDate.getText().toString();
        String foodPlace = mPlace.getText().toString();
        String foodTime = mTime.getText().toString();
        String foodNum = mNumOfPeople.getText().toString();     // !! - Could be null
        String foodDuration = mDuration.getText().toString();   // !! - Could be null
        String foodComments = mComments.getText().toString();

        // Validate date field
        if (TextUtils.isEmpty(foodDate)) {
            mDate.setError("Mandatory field");
            Toast.makeText(this.getContext(), "Date required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate time field
        if (TextUtils.isEmpty(foodTime)) {
            mTime.setError("Mandatory field");
            Toast.makeText(this.getContext(), "Time required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(foodNum)) {
            mNumOfPeople.setError("Mandatory field");
            Toast.makeText(this.getContext(), "Number of people needs a number", Toast.LENGTH_SHORT)
                 .show();
            return;
        } else {
            mNumOfPeople.setError(null);
        }


        // Validate Date and Time booleans
        dateIs = validateDate(foodDate);
        Log.d(TAG, "createClicked: dateIs: " + dateIs);
        switch (dateIs) {
            case "past":
                validDate = false;
                validTime = false;
                break;
            case "present":
                // Date is today so we have to verify the time is correct
                validDate = true;
                validTime = validateTime(foodTime);
                break;
            default:
                // Date is good. Continue with the rest of the code
                validDate = true;
                validTime = true;
                break;
        }

        if (!validDate) {
            Toast.makeText(this.getContext(), "Invalid date", Toast.LENGTH_SHORT).show();
            mDate.setError("Date is in the past");
        } else if (!validTime) {
            Toast.makeText(this.getContext(), "Invalid time", Toast.LENGTH_SHORT).show();
            mTime.setError("Can't go back in time, sorry");
        } else {
            /* All fields filled - add to Firebase DB */

            // Data validation
            if (foodDuration.equals("")) {
                foodDuration = "n/a";
            }
            if (foodComments.equals("")) {
                foodComments = "n/a";
            }

            foodEvent = new FoodEntity(name, foodTime, foodDuration, foodDate,
                                       mParam1, foodNum, foodComments, "Food",
                                       foodNum);

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

    public FoodCreateDetails() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // Unbind view to free some memory
        mUnbinder.unbind();
    }

    /**
     * Validate the date that the user has selected
     *
     * @param date - Date to be validated
     * @return - String describing the date (past, present, or future)
     */
    private String validateDate(String date) {

        String[] splitDate = date.split("/");
        int[] dateSplit = new int[splitDate.length];
        for (int i = 0; i < dateSplit.length; i++) {
            dateSplit[i] = Integer.parseInt(splitDate[i]);
        }

        String userDateIs = "";

        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int year = c.get(Calendar.YEAR);

        // Validate date
        if (dateSplit[2] > year) {
            userDateIs = "future";

        } else if (dateSplit[2] == year) {
            // Same year - check the month first
            if (dateSplit[0] > month) {
                userDateIs = "future";

            } else if (dateSplit[0] == month) {
                // Same year & same month - check the day
                if (dateSplit[1] > day) {
                    userDateIs = "future";

                } else if (dateSplit[1] == day) {
                    // Same year, month, and day
                    userDateIs = "present";

                } else {
                    // Same year, same month, but past day
                    userDateIs = "past";
                }
            } else {
                // Past month selected
                userDateIs = "past";
            }
        } else {
            // Year is in the past
            userDateIs = "past";
        }

        return userDateIs;
    }

    /**
     * Verifies that the time the user is trying to set for the event is after right now
     *
     * @param time - Time trying to be set
     * @return - true if the time is past the present
     */
    private boolean validateTime(String time) {

        String[] splitTime = time.split(":");
        int[] timeSplit = new int[splitTime.length];
        for (int i = 0; i < timeSplit.length; i++) {
            timeSplit[i] = Integer.parseInt(splitTime[i]);
        }

        boolean vTime = false;

        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        vTime = timeSplit[0] > hour || timeSplit[0] == hour && timeSplit[1] > minute;

        return vTime;
    }
}
