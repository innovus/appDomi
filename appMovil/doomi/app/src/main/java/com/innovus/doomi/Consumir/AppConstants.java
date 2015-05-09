package com.innovus.doomi.Consumir;

import android.app.Activity;
import android.app.Dialog;

import com.appspot.domi_app.doomiTodos.DoomiTodos;
import com.appspot.domi_app.doomiUsuarios.DoomiUsuarios;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.innovus.doomi.R;

/**
 * Created by Janeth Arcos on 17/02/2015.
 */
public class AppConstants {

    public static final String menuCajon[] = {"Home","Direcciones","Favoritos","Recientes","Salir"};
    public static final int ICONS[] = {R.drawable.ic_action_home,R.drawable.ic_action_direcciones,R.drawable.ic_action_favoritos,R.drawable.ic_action_recientes,R.drawable.ic_action_salir};


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
    public static DoomiUsuarios buildServiceHandlerUsuarios() {

        DoomiUsuarios.Builder builder
                = new DoomiUsuarios.Builder(
                AppConstants.HTTP_TRANSPORT,
                AppConstants.JSON_FACTORY, null);

        //builder.setApplicationName("domi-app");
        return builder.build();
    }
    public static DoomiTodos buildServiceHandlerTodos() {

        DoomiTodos.Builder builder
                = new DoomiTodos.Builder(
                AppConstants.HTTP_TRANSPORT,
                AppConstants.JSON_FACTORY, null);

        //builder.setApplicationName("domi-app");
        return builder.build();
    }
}
