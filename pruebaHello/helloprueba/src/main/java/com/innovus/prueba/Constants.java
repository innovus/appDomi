package com.innovus.prueba;

import com.google.api.server.spi.Constant;

/**
 * Contains the client IDs and scopes for allowed clients consuming the helloworld API.
 */
public class Constants {
  public static final String WEB_CLIENT_ID = "207871756954-ea8oo1enldn0f50krf8ql1v6rhcig21t.apps.googleusercontent.com";
  public static final String ANDROID_CLIENT_ID = "207871756954-ruutdu53t9g2hrgid7ghbss25n8iqcc4.apps.googleusercontent.com";
  public static final String IOS_CLIENT_ID = "replace this with your iOS client ID";
  public static final String ANDROID_AUDIENCE = WEB_CLIENT_ID;

  public static final String API_EXPLORER_CLIENT_ID = Constant.API_EXPLORER_CLIENT_ID;
  public static final String EMAIL_SCOPE = "https://www.googleapis.com/auth/userinfo.email";
}
