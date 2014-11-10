package com.innovus.domi;

import com.google.api.server.spi.Constant;

/**
 * Contains the client IDs and scopes for allowed clients consuming the helloworld API.
 */
public class Constants {
  public static final String WEB_CLIENT_ID = "204916157214-1hho3fgafmt30l9kt7rljls1qttbeb3n.apps.googleusercontent.com";
  public static final String ANDROID_CLIENT_ID = "204916157214-rl2danb6k5nbv0h4heonvmqia8q1ff25.apps.googleusercontent.com";
  public static final String IOS_CLIENT_ID = "replace this with your iOS client ID";
  public static final String ANDROID_AUDIENCE = WEB_CLIENT_ID;
  public static final String API_EXPLORER_CLIENT_ID = Constant.API_EXPLORER_CLIENT_ID;

  public static final String EMAIL_SCOPE = "https://www.googleapis.com/auth/userinfo.email";
}
