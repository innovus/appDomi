package com.innovus.doomi.Consumir;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;;
import android.util.Log;
import android.widget.Toast;

import com.appspot.domi_app.doomiTodos.DoomiTodos;
import com.appspot.domi_app.doomiTodos.model.Login;


import java.io.IOException;


/**
 * Created by personal on 13/05/15.
 */
public class LoginTask extends AsyncTask<Void, Void, Login> {
    // private static com.appspot.domi_app.domi.Domi myApiService = null;
    private com.appspot.domi_app.doomiTodos.DoomiTodos myApiService = null;
    private String email;
    private String password;
    SessionManager session;


    private Activity activity;

    public static void build(Context context) {
        //myApiService = buildServiceHandler(context);
    }

    public LoginTask(Activity activity, String email, String password) {
        super();
        this.activity = activity;
        this.email = email;
        this.password = password;
        session = new SessionManager(this.activity.getApplicationContext());
    }

    @Override
    protected Login doInBackground(Void... params) {
        if (myApiService == null) { // Only do this once
            myApiService = AppConstants.buildServiceHandlerTodos();
        }
        // Domi.ConsultaEmpresa queryEmpresas = myApiService.consultaEmpresa();

        try {
            DoomiTodos.LoginEstado loginEstado = myApiService.loginEstado(email,password);

            Login login= loginEstado.execute();
            return login;


        } catch (IOException e) {
            Log.e("Erroroooooooor", e.getMessage());
            //  Log.e("Error",e);
            return new Login();
        }
    }

    @Override
    protected void onPostExecute(Login result) {
        if(result.getEstado().equals(false))
            Toast.makeText(this.activity, "Usuario o Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
        else{
            session.createLoginSession(result.getUser().getWebsafeKey(), result.getUser().getEmail().getEmail());

            Toast.makeText(this.activity, "Logeado", Toast.LENGTH_SHORT).show();

            // Staring MainActivity
           // Intent i = new Intent(this.activity, Login.class);
           // this.activity.startActivity(i);

            /*Toast.makeText(this.activity,result.getUser().getEmail().getEmail(), Toast.LENGTH_SHORT).show();
            SharedPreferences prefs =
                    this.activity.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("email", "modificado@email.com");
            editor.putString("nombre", "Prueba");
            editor.commit();*/
            /*
            Toast.makeT
            Intent i = new Intent (this.activity, com.innovus.doomi.Activities.Login.class);

            this.activity.startActivity(i);
            */

        }




    }

}
