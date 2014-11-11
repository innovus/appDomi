package com.innovus.doomi;

/**
 * Created by Jorge Viveros on 11/11/2014.
 */

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;

/**
 * Application constants.
 */
public class AppConstants {
    /**
     * Your WEB CLIENT ID from the API Access screen of the Developer Console for your project. This
     * is NOT the Android client id from that screen.
     *
     * @see <a href="https://developers.google.com/console">https://developers.google.com/console</a>
     */
    public static final String WEB_CLIENT_ID = "204916157214-1hho3fgafmt30l9kt7rljls1qttbeb3n.apps.googleusercontent.com";
    /**
     * The audience is defined by the web client id, not the Android client id.
     */
    public static final String AUDIENCE = "server:client_id:" + WEB_CLIENT_ID;

    /**
     * Class instance of the JSON factory.
     */
    public static final JsonFactory JSON_FACTORY = new AndroidJsonFactory();

    /**
     * Class instance of the HTTP transport.
     */
    public static final HttpTransport HTTP_TRANSPORT = AndroidHttp.newCompatibleTransport();

}