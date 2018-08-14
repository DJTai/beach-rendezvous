package com.example.beachrendezvous.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.beachrendezvous.R;
import com.google.firebase.database.DatabaseReference;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FoodCreateDetails extends Fragment {

    //region Bundle parameters
    private static final String ARG_PARAM1 = "param1";    // Refers to ???
    private static final String ARG_PARAM2 = "param2";    // Refers to ???
    private static final String EXCEPTION = "Exception";
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
    @BindView(R.id.btn_foodCreate)
    Button mBtnCreate;

    @BindView(R.id.foodCreate_dateText)
    TextView date;

    @OnClick(R.id.btn_foodCreate)
    void createClicked() {
        try {

        } catch (Exception e) {
            e.printStackTrace();;
            Log.i(EXCEPTION, "createClicked: exception = " + e.toString());
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
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_create_details, container, false);
    }
}
