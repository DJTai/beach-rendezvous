package com.example.beachrendezvous.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.beachrendezvous.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link MainMenu.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link MainMenu#newInstance} factory method to
// * create an instance of this fragment.
// */
public class MainMenu extends Fragment {

    /* Logging Tags */
    public static final String TAG = "mainMenu_fragment";
    private static final String DEBUG = "debug_mainMenu";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Unbinder mUnbinder;

    //region ButterKnife Binds
    @BindView(R.id.mainMenu_findEvent)
    Button btnFindEvent;

    @BindView(R.id.mainMenu_createEvent)
    Button btnCreateEvent;

    @OnClick(R.id.mainMenu_findEvent)
    void findEventClicked () {
        Toast.makeText(getContext(), "CLICK BELOW", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.mainMenu_createEvent)
    void createEvent () {
        Toast.makeText(getContext(), "CLICK BELOW", Toast.LENGTH_SHORT).show();
    }
    //endregion

    public MainMenu () {
        // Required empty public constructor
    }

    //    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment MainMenu.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static MainMenu newInstance (String param1, String param2) {
//        MainMenu fragment = new MainMenu();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }

    }

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        // Bind View using ButterKnife
        mUnbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView () {
        super.onDestroyView();

        // Unbind the view to free some memory
        mUnbinder.unbind();
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed (Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach (Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                                               + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach () {
//        super.onDetach();
//        mListener = null;
//    }
}