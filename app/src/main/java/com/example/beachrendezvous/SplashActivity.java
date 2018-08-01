package com.example.beachrendezvous;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends Activity {

    private static final String TAG = "splash_activity";
    private static final String DEBUG = "debug_splashActivity";

    @OnClick(R.id.email_sign_in_button)
    void signInHandler () {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }
}
