package com.innovus.doomi.Consumir;

import android.app.Activity;
import android.app.Dialog;

import com.appspot.domi_app.domi.Domi;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;

/**
 * Created by Janeth Arcos on 17/02/2015.
 */
public class AppConstants {

    public static final String WEB_CLIENT_ID = "204916157214-1hho3fgafmt30l9kt7rljls1qttbeb3n.apps.googleusercontent.com";

    public static final String AUDIENCE = "server:client_id:" + WEB_CLIENT_ID;

    public static final JsonFactory JSON_FACTORY = new AndroidJsonFactory();

    public static final HttpTransport HTTP_TRANSPORT = AndroidHttp.newCompatibleTransport();

    public static void showGooglePlayServicesAvailabilityErrorDialog(final Activity activity,
                                                                     final int connectionStatusCode) {
        final int REQUEST_GOOGLE_PLAY_SERVICES = 0;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Dialog dialog = GooglePlayServicesUtil.getErrorDialog(
                        connectionStatusCode, activity, REQUEST_GOOGLE_PLAY_SERVICES);
                dialog.show();
            }
        });
    }
    public static Domi buildServiceHandler() {

        Domi.Builder builder
                = new Domi.Builder(
                AppConstants.HTTP_TRANSPORT,
                AppConstants.JSON_FACTORY, null);

        //builder.setApplicationName("domi-app");
        return builder.build();
    }
}
