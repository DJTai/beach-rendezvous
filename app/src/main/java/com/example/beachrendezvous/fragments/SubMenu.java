package com.example.beachrendezvous.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beachrendezvous.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link SubMenu.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link SubMenu#newInstance} factory method to
// * create an instance of this fragment.
// */
public class SubMenu extends Fragment {

    /* Logging Tags */
    private static final String TAG = "sub_menu_fragment";
    private static final String ARG_PARAM = "param";
    private static final String ARG_PARAM1 = "param1";

        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM2 = "param2";
//
    /* Parameter to determine the context of the SubMenu fragment, e.g., Creating or Searching */
    private String mParam;

//    private OnFragmentInteractionListener mListener;

    Unbinder mUnbinder;

    //region Handle button clicks
    @OnClick(R.id.subMenu_btn_food)
    void foodClicked () {
        if (mParam != null) {
            String msg = "";
            if (mParam.equals("search")) {

                // TODO: Handle searching for food
                msg = "search food opened";
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();


            } else {

                // TODO: Handle creating an event for food
                msg = "create food opened";
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @OnClick(R.id.subMenu_btn_movies)
    void moviesClicked () {
        if (mParam != null) {
            String msg = "";
            if (mParam.equals("search")) {

                // TODO: Handle searching for a movie
                msg = "search movies opened";
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

            } else {

                // TODO: Handle creating an event for a movie
                msg = "create movies opened";
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        }
    }
    @OnClick(R.id.subMenu_btn_sports)
    void sportsClicked () {
        if (mParam != null) {
            String msg = "";
            if (mParam.equals("search")) {

                // TODO: Open SportsSearchDetails fragment
                msg = "search sports opened";
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                Fragment f=new sportinfo();
                Bundle args = new Bundle();
                args.putString(ARG_PARAM1,"search");
                args.putString(ARG_PARAM,"sports");
                f.setArguments(args);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction
                        .replace(R.id.frame_fragment, f)
                        .addToBackStack("search")
                        .commit();



            } else {

                // TODO: Open SportsCreateDetails fragment
                msg = "create sports opened";
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                Fragment f=new sportinfo();
                Bundle args = new Bundle();
                args.putString(ARG_PARAM1,"create");
                args.putString(ARG_PARAM,"sports");
                f.setArguments(args);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction
                        .replace(R.id.frame_fragment, f)
                        .addToBackStack("create")
                        .commit();
            }
        }
    }
    //endregion


    public SubMenu () {
        // Required empty public constructor
    }

    //    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment SubMenu.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static SubMenu newInstance (String param1, String param2) {
//        SubMenu fragment = new SubMenu();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getString(ARG_PARAM);
            Log.i(TAG, "onCreate: mParam = " + mParam);
        }
    }

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = null;

        if (mParam != null) {
            if (mParam.equals("create")) {
                Log.i(TAG, "onCreateView: type equals create");
                view = inflater.inflate(R.layout.fragment_sub_menu, container, false);
                TextView header = view.findViewById(R.id.subMenu_header);
                header.setText(R.string.submenu_header_create);
            } else {
                Log.i(TAG, "onCreateView: type not equal to create");
                view = inflater.inflate(R.layout.fragment_sub_menu, container, false);
                TextView header = view.findViewById(R.id.subMenu_header);
                header.setText("Let's Find an Event!");
            }
        }
        // Bind view using ButterKnife
        mUnbinder = ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onDestroyView () {
        super.onDestroyView();

        // Unbind view to free some memory
        mUnbinder.unbind();
    }

//    // TODO: Rename method, update argument and hook method into UI event
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
//

//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction (Uri uri);
//    }
}
