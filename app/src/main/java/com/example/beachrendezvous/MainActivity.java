package com.example.beachrendezvous;

import android.content.Intent;
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


public class MainActivity
        extends AppCompatActivity {

    /* Logging Tags */
    private static final String TAG = "main_activity";
    public static final String DEBUG = "debug";

    /* Parameter used to pass arguments within Bundle objects */
    private static final String ARG_PARAM = "param";
    public static final String ARG_GIVEN_NAME = "givenName";
    public static final String ARG_DISPLAY_ID = "displayableId";
    String name = " ";

    //region References

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    FragmentManager mFragManager;

    private MainViewModel mViewModel;

    /* Bottom Nav Bar Icons */
    private final String home = "home";
    private final String search = "search";
    private final String create = "create";

    //endregion

    //region Options Menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_profile:
                return initFragment(new Profile(), "profile");
            case R.id.action_settings:
                return initFragment(new Settings(), "settings");
            case R.id.action_info:
                return initFragment(new Info(), "info");
            case R.id.action_signOut:
                signUserOut();
                Intent intent = new Intent(this, LoginActivity.class);
                intent.putExtra("logged_out", true);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //endregion

    /**
     * Determines which fragment to open upon nav selection
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            int choice = item.getItemId();
            int current = navigation.getSelectedItemId();    // Prevent re-creating fragments

            if (choice == R.id.nav_home && current != R.id.nav_home) {
                return initFragment(new MainMenu(), home);

            } else if (choice == R.id.nav_search && current != R.id.nav_search) {
                return initFragment(new SubMenu(), search);

            } else if (choice == R.id.nav_create && current != R.id.nav_create) {
                return initFragment(new SubMenu(), create);
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
        name = getIntent().getStringExtra(ARG_GIVEN_NAME);

        mFragManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            // Don't add initial fragment to back-stack
            FragmentTransaction fragmentTransaction = mFragManager.beginTransaction();
            fragmentTransaction
                    .replace(R.id.frame_fragment, new MainMenu())
                    .commit();
        }

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Listen for changes to the fragment back-stack
        mFragManager.addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    @Override
                    public void onBackStackChanged() {
                        MenuItem shown;
                        if (mFragManager.getBackStackEntryCount() > 0) {
                            String name = mFragManager
                                    .getBackStackEntryAt(mFragManager.getBackStackEntryCount() - 1)
                                    .getName();

                            if (name.equals(home)) {
                                shown = navigation.getMenu().getItem(0);
                                shown.setChecked(true);
                            } else if (name.equals(search)) {
                                shown = navigation.getMenu().getItem(1);
                                shown.setChecked(true);
                            } else if (name.equals(create)) {
                                shown = navigation.getMenu().getItem(2);
                                shown.setChecked(true);
                            }
                        } else {
                            shown = navigation.getMenu().getItem(0);
                            shown.setChecked(true);
                        }
                    }
                });
    }

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
     * Signs the user out of the application and removes them from the list of
     * authenticated users.
     */
    private void signUserOut() {
        List<User> users = null;
        AuthenticationManager authManager = AuthenticationManager.getInstance();
        String TAG = MainActivity.class.getSimpleName();

        try {
            users = authManager.getPublicClient().getUsers();

            if (users == null) {
                // TODO: Code?
            } else if (users.size() == 1) {
                authManager.getPublicClient().remove(users.get(0));
                finish();
            } else {
                for (int i = 0; i < users.size(); i++) {
                    authManager.getPublicClient().remove(users.get(i));
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
