package com.example.beachrendezvous;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.beachrendezvous.ui.InfoFragment;
import com.example.beachrendezvous.ui.MainMenuFragment;
import com.example.beachrendezvous.ui.ProfileFragment;
import com.example.beachrendezvous.ui.SettingsFragment;
import com.example.beachrendezvous.ui.SubMenuFragment;
import com.example.beachrendezvous.viewModel.MainViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

// Butter Knife imports
// see https://jakewharton.github.io/butterknife/ for more details


public class MainActivity
        extends AppCompatActivity
        implements MainMenuFragment.OnFragmentInteractionListener,
        SubMenuFragment.OnFragmentInteractionListener {

    public static final String TAG = "main_activity";

    // References
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    private MainViewModel mViewModel;


    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_profile) {
            Fragment fragment = new ProfileFragment();
            return initFragment(fragment);

        } else if (id == R.id.action_settings) {
            Fragment fragment = new SettingsFragment();
            return initFragment(fragment);

        } else if (id == R.id.action_info) {
            Fragment fragment = new InfoFragment();
            return initFragment(fragment);

        } else if (id == R.id.action_signOut) {
            Intent intent = new Intent(this, SplashActivity.class);
            Log.i(TAG, "onOptionsItemSelected: Signing Out");
            startActivity(intent);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Initialize, inflate, and add to the back stack, the supplied fragment
     *
     * @param fragment - Fragment to be initialized
     * @return true
     */
    private boolean initFragment (Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .replace(R.id.frame_fragment, fragment)
                .addToBackStack(fragment.getClass().toString())
                .commit();
        return true;
    }

    /**
     * Determines which fragment to open upon nav selection
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected (@NonNull MenuItem item) {
            Fragment fragment;
            int choice = item.getItemId();

            if (choice == R.id.nav_home) {
                fragment = new MainMenuFragment();
                return initFragment(fragment);

            } else if (choice == R.id.nav_search) {
                fragment = new SubMenuFragment();
                return initFragment(fragment);

            } else if (choice == R.id.nav_create) {
                fragment = new SubMenuFragment();
                return initFragment(fragment);
            }
            return false;
        }
    };

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment;

        Log.i(TAG, "main activity created");

        // !! - Needed to complete binding of views - !!
        // Needs to be before any Butter Knife references as well
        ButterKnife.bind(this);

        Log.i(TAG, "butterknife binded");

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Log.i(TAG, "bottom nav set");

        fragment = new MainMenuFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .replace(R.id.frame_fragment, fragment)
                .commit();

        Log.i(TAG, "fragment transaction successful");

    }

    @Override
    public void onFragmentInteraction (Uri uri) {

    }
}
