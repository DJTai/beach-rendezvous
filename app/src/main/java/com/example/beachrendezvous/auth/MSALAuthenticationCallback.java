package com.example.beachrendezvous.auth;

import com.microsoft.identity.client.AuthenticationResult;
import com.microsoft.identity.client.MsalException;

/**
 * Created by johnaustin on 7/5/17.
 * Implemented by DJTai on 8/3/18
 */

public interface MSALAuthenticationCallback {
    void onSuccess(AuthenticationResult authenticationResult);
    void onError(MsalException exception);
    void onError(Exception exception);
    void onCancel();
}
