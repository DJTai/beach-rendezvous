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
import android.widget.Toast;

import com.example.beachrendezvous.auth.AuthenticationManager;
import com.example.beachrendezvous.fragments.Info;
import com.example.beachrendezvous.fragments.MainMenu;
import com.example.beachrendezvous.fragments.Profile;
import com.example.beachrendezvous.fragments.Settings;
import com.example.beachrendezvous.fragments.SubMenu;
import com.example.beachrendezvous.viewModel.MainViewModel;
import com.microsoft.identity.client.MsalClientException;
import com.microsoft.identity.client.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

// Butter Knife imports
// see https://jakewharton.github.io/butterknife/ for more details


public class MainActivity
        extends AppCompatActivity {

    /* Logging Tags */
    private static final String TAG = "main_activity";
    public static final String DEBUG = "debug";

    /* Parameter used to pass arguments within Bundle objects */
    private static final String ARG_PARAM = "param";
    public static final String ARG_GIVEN_NAME = "givenName";
    public static final String ARG_DISPLAY_ID = "displayableId";
    String name=" ";

    // References
    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private static String bottomSelection;

    private MainViewModel mViewModel;


    //region Options Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

       if (id == R.id.action_settings) {
            Fragment fragment = new Settings();
            return initFragment(fragment, "settings");

        } else if (id == R.id.action_info) {
            Fragment fragment = new Info();
            return initFragment(fragment, "info");

        } else if (id == R.id.action_signOut) {
            signUserOut();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //endregion

    /**
     * Initialize, inflate, and add to the back stack, the supplied fragment
     *
     * @param fragment - Fragment to be initialized
     * @return true
     */
    public boolean initFragment(Fragment fragment, String value) {
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, value);
        args.putString(ARG_GIVEN_NAME, name);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .replace(R.id.frame_fragment, fragment)
                .addToBackStack(value)
                .commit();

        return true;
    }

    /**
     * Determines which fragment to open upon nav selection
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            int choice = item.getItemId();

            if (choice == R.id.nav_home) {
                fragment = new MainMenu();
                bottomSelection = "home";
                return initFragment(fragment, "home");

            } else if (choice == R.id.nav_search) {
                fragment = new SubMenu();
                bottomSelection = "search";
                return initFragment(fragment, "search");

            } else if (choice == R.id.nav_create) {
                fragment = new SubMenu();
                bottomSelection = "create";
                return initFragment(fragment, "create");
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Don't delete this!!
        ButterKnife.bind(this);
        name=getIntent().getStringExtra(ARG_GIVEN_NAME);

        if (savedInstanceState == null) {
            Fragment fragment = new MainMenu();
            initFragment(fragment, "home");
        }

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // TODO: Link backpress with FragmentManager to update BottomNav selection
    }

    /**
     * Signs the user out of the application and removes them from the list of
     * authenticated users.
     */
    private void signUserOut() {
        List<User> users = null;
        AuthenticationManager mgr = AuthenticationManager.getInstance();
        String TAG = MainActivity.class.getSimpleName();

        try {
            users = mgr.getPublicClient().getUsers();

            if (users == null) {

            } else if (users.size() == 1) {
                mgr.getPublicClient().remove(users.get(0));
                finish();
            } else {
                for (int i = 0; i < users.size(); i++) {
                    mgr.getPublicClient().remove(users.get(i));
                }
            }

            Toast.makeText(getBaseContext(), "Signed Out!", Toast.LENGTH_SHORT)
                 .show();

        } catch (MsalClientException e) {
            Log.d(TAG, "MSAL Exception Generated while getting users: " + e.toString());

        } catch (IndexOutOfBoundsException e) {
            Log.d(TAG, "User at this position does not exist: " + e.toString());
        }
    }
}
