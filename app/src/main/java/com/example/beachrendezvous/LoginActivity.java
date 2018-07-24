package com.example.beachrendezvous;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.okta.appauth.android.OktaAppAuth;

import net.openid.appauth.AuthorizationException;

public class LoginActivity extends Activity {

    public static final String TAG = "login_activity";
    public static final String DEBUG = "debug";

    private OktaAppAuth mOktaAuth;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(DEBUG, "onCreate: login created");

        mOktaAuth = OktaAppAuth.getInstance(this);

        Log.d(DEBUG, "onCreate: mOktaAuth created");

        if (mOktaAuth.isUserLoggedIn()) {
            Log.i(TAG, "User is already authenticated, proceeding to app");
            startActivity(new Intent(this, findSports.class));
            finish();
            return;
        }

        Log.d(DEBUG, "onCreate: checked if user is logged in");

        mOktaAuth.init(
                LoginActivity.this,
                new OktaAppAuth.OktaAuthListener() {
                    @Override
                    public void onSuccess () {
                        // Handle a successful initialization (e.g., display login button)
                        // Unsure about below
//                        Intent intent = new Intent(LoginActivity.this,
//                                                   findSports.class);
//                        startActivity(intent);
//                        finish();
                        // Unsure about above
                        Log.d(DEBUG, "onSuccess: OktaAppAuth success");

                        Intent completionIntent =
                                new Intent(LoginActivity.this, findSports.class);
                        Intent cancelIntent =
                                new Intent(LoginActivity.this, LoginActivity.class);
                        cancelIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        mOktaAuth.login(
                                LoginActivity.this,
                                PendingIntent.getActivity(LoginActivity.this, 0, completionIntent, 0),
                                PendingIntent.getActivity(LoginActivity.this, 0, cancelIntent, 0)
                        );
                    }

                    @Override
                    public void onTokenFailure (@NonNull AuthorizationException e) {
                        // Handle a failed initialization
                        Log.d(DEBUG, "onTokenFailure: token failed");

                        // Have user login
                    }
                }
        );

        Log.d(DEBUG, "onCreate: init passed");

    }
}