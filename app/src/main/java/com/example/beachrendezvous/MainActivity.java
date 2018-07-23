package com.example.beachrendezvous;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.beachrendezvous.ui.InfoFragment;
import com.example.beachrendezvous.ui.ProfileFragment;
import com.example.beachrendezvous.ui.SettingsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

// Butter Knife imports
// see https://jakewharton.github.io/butterknife/ for more details

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "main_activity";
    public static final String DEBUG = "debug";

    // Reference to the bottom nav bar
    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    /**
     * Determines which fragment to open upon nav selection
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected (@NonNull MenuItem item) {
            Fragment fragment;
            int choice = item.getItemId();

            if (choice == R.id.nav_info) {
                fragment = new InfoFragment();

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction
                        .replace(R.id.frame_fragment, fragment)
                        .commit();
                return true;

            } else if (choice == R.id.nav_profile) {
                fragment = new ProfileFragment();

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction
                        .replace(R.id.frame_fragment, fragment)
                        .commit();

                return true;

            } else if (choice == R.id.nav_settings) {
                fragment = new SettingsFragment();

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction
                        .replace(R.id.frame_fragment, fragment)
                        .commit();

                return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(DEBUG, "main activity created");

        // !! - Needed to complete binding of views - !!
        // Needs to be before any Butter Knife references as well
        ButterKnife.bind(this);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
