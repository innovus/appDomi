package com.innovus.doomi.Consumir;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import com.appspot.domi_app.doomiUsuarios.DoomiUsuarios;
import com.appspot.domi_app.doomiUsuarios.model.CarritoForm;
import com.appspot.domi_app.doomiUsuarios.model.Usuario;
import com.appspot.domi_app.doomiUsuarios.model.UsuarioForm;

import java.io.IOException;

/**
 * Created by personal on 23/06/15.
 */
public class RegistroTask extends AsyncTask<UsuarioForm, Void, Usuario> {
    // private static com.appspot.domi_app.domi.Domi myApiService = null;
    private com.appspot.domi_app.doomiUsuarios.DoomiUsuarios myApiService = null;
    private String email;
    private String password;
    SessionManager session;


    private Activity activity;

    public static void build(Context context) {
        //myApiService = buildServiceHandler(context);
    }

    public RegistroTask(Activity activity) {
        super();
        this.activity = activity;
        session = new SessionManager(this.activity.getApplicationContext());
    }

    @Override
    protected Usuario doInBackground(UsuarioForm... params) {
        if (myApiService == null) { // Only do this once
            myApiService = AppConstants.buildServiceHandlerUsuarios();
        }
        // Domi.ConsultaEmpresa queryEmpresas = myApiService.consultaEmpresa();

        try {


            DoomiUsuarios.CrearUsuario crearUsuario = myApiService.crearUsuario(params[0]);

            Usuario usuario = crearUsuario.execute();
            return usuario;


        } catch (IOException e) {
            Log.e("Erroroooooooor", e.getMessage());
            //exceptionToBeThrown = e;
            //cancel(true);
            //  Log.e("Error",e);
            // return new Login();
            return null;
        }

    }

    @Override
    protected void onPostExecute(Usuario result) {
        if (result == null) {
            Toast.makeText(activity, "error conexion intenta mas tarde", Toast.LENGTH_SHORT);

        } else {
            session.createLoginSession(result.getWebsafeKey(), result.getCorreoUser());

            Toast.makeText(this.activity, "Logeado", Toast.LENGTH_SHORT).show();
            this.activity.finish();


        }

    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        // Perform error post processing here...
    }
}