package com.example.beachrendezvous;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends Activity {

    public static final String TAG = "splash_activity";
    public static final String DEBUG = "debug";

    @OnClick(R.id.email_sign_in_button)
    void signInHandler() {

        // HAVEN'T CONNECTED THIS YET :(
//        Intent intent = new Intent(this, LoginActivity.class);
//        Log.d(DEBUG, "signInHandler: CLICKED");
//        startActivity(intent);

        Intent intent = new Intent(this, findSports.class);
        Log.d(DEBUG, "signInHandler: clicked");
        startActivity(intent);
        finish();
        return;
    }

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

    }
}
