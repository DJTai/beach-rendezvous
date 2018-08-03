package com.example.beachrendezvous;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.StrictMode;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.beachrendezvous.auth.Constants;
import com.microsoft.identity.client.ILoggerCallback;
import com.microsoft.identity.client.Logger;

import java.util.HashMap;
import java.util.Map;

// Import information from Microsoft 365 account
public class Application extends MultiDexApplication {

    private static String TAG = Application.class.getSimpleName();
    public static class User
    {
        private static String displayName;
        private static Bitmap profilePicture;
        private static String accessToken;
        private static String emailAddress;
        private static String firstName;
        private static String lastName;


        public static String getDisplayName() {
            return displayName;
        }

        public static void setDisplayName(String displayName) {
            User.displayName = displayName;
        }

        public static Bitmap getProfilePicture() {
            return profilePicture;
        }

        public static void setProfilePicture(Bitmap profilePicture) {
            User.profilePicture = profilePicture;
        }

        public static String getAccessToken() {
            return accessToken;
        }

        public static void setAccessToken(String accessToken) {
            User.accessToken = accessToken;
        }

        public static void setEmailAddress(String emailAddress) {
            User.emailAddress = emailAddress;
        }

        public static void setFirstName(String firstName) {
            User.firstName = firstName;
        }

        public static String getLastName() {
            return lastName;
        }

        public static String getFirstName(){
            return firstName;
        }

        public static String getEmailAddress() {
            return emailAddress;
        }

        public static Bitmap downloadProfilePicture()
        {
            RequestQueue queue = Volley.newRequestQueue(Application.getContext());
            ImageRequest request = new ImageRequest(Constants.MSGRAPH_URL + "me/photo/$value",
                                                    new Response.Listener<Bitmap>() {
                                                        @Override
                                                        public void onResponse(Bitmap response) {
                                                            Application.User.setProfilePicture(response);
                                                        }
                                                    }, 360, 360, ImageView.ScaleType.CENTER_CROP, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, "Profile picture download error");
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Authorization", "Bearer " + Application.User.getAccessToken());
                    return headers;
                }

            };
            queue.add(request);
            return profilePicture;
        }
    }

    public static Application instance;

    public static Application getInstance() {
        return instance;
    }

    public static Context getContext(){
        return instance;
    }

    private Activity mApplicationActivity;

    private StringBuffer mLogs;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    @Override
    public void onCreate(){
        instance = this;
        super.onCreate();

        mLogs = new StringBuffer();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // Logging can be turned on four different levels: error, warning, info, and verbose. By default the sdk is turning on
        // verbose level logging. Any apps can use Logger.getInstance().setLogLevel(Loglevel) to enable different level of logging.
        Logger.getInstance().setExternalLogger(new ILoggerCallback() {
            @Override
            public void log(String tag, Logger.LogLevel logLevel, String message, boolean containsPII) {
                // contains PII indicates that if the log message contains PII information. If Pii logging is
                // disabled, the sdk never returns back logs with Pii.
                mLogs.append(message).append('\n');
            }
        });
    }
    String getLogs() {
        return mLogs.toString();
    }

    void clearLogs() {
        mLogs = new StringBuffer();
    }

    public Activity getApplicationActivity() {
        return mApplicationActivity;
    }

    public void setApplicationActivity(Activity connectActivity) {
        mApplicationActivity = connectActivity;
    }
}
