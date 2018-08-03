package com.example.beachrendezvous;

import android.support.v7.app.AppCompatActivity;

import com.example.beachrendezvous.auth.MSALAuthenticationCallback;
import com.microsoft.identity.client.AuthenticationResult;
import com.microsoft.identity.client.MsalException;

public class LoginActivity extends AppCompatActivity implements MSALAuthenticationCallback{
    @Override
    public void onSuccess(AuthenticationResult authenticationResult) {

    }

    @Override
    public void onError(MsalException exception) {

    }

    @Override
    public void onError(Exception exception) {

    }

    @Override
    public void onCancel() {

    }
}
