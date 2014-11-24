package com.innovus.helloendpoints;

import com.appspot.universal_team_757.helloworld.model.HelloGreeting;
import com.google.api.client.util.Lists;

import java.util.ArrayList;

/**
 * Created by Janeth Arcos on 23/11/2014.
 */

/**
          * Dummy Application que puede contener datos estáticos para uso exclusivo en aplicaciones de ejemplo.
          *
          * TODO (desarrollador): Implementar una técnica de almacenamiento de datos adecuada para su aplicación.
          */
public class Application extends android.app.Application {

    ArrayList<HelloGreeting> greetings = Lists.newArrayList();

}
