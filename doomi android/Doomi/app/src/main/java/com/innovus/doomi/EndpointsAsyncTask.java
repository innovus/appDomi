package com.innovus.doomi;

import com.appspot.domi_app.domi.model.Empresa;
import com.appspot.domi_app.domi.Domi;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import com.appspot.domi_app.domi.model.EmpresaCollection;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Janeth Arcos on 11/11/2014.
 */
public class EndpointsAsyncTask  extends AsyncTask<Void, Void, List<Empresa>>{
    private static com.appspot.domi_app.domi.Domi myApiService = null;
    private Context context;

    public static void build(Context context) {
        myApiService = buildServiceHandler(context);
    }

        EndpointsAsyncTask(Context context) {
            this.context = context;
        }

        @Override
        protected List<Empresa> doInBackground(Void... params) {
            myApiService = buildServiceHandler(context);
            if(myApiService == null) { // Only do this once

            }
           // Domi.ConsultaEmpresa queryEmpresas = myApiService.consultaEmpresa();

            try {
                Domi.ConsultaEmpresa queryEmpresas = myApiService.consultaEmpresa();

                EmpresaCollection empresaCollection = queryEmpresas.execute();
                if(empresaCollection != null && empresaCollection.getItems() != null){
                    List<Empresa> empresas = empresaCollection.getItems();
                    return empresas;


                }
                return Collections.EMPTY_LIST;
               // return myApiService.consultaEmpresa().execute().getItems();

            } catch (IOException e) {
                return Collections.EMPTY_LIST;
            }
        }

        @Override
        protected void onPostExecute(List<Empresa> result) {
            String mostrar = "";
            for (Empresa q : result) {
                mostrar = " +" + q.getNombre()+ " - " + " "+ q.getDescripcion() +" ";
                Toast.makeText(context, mostrar, Toast.LENGTH_LONG).show();
            }
        }
    public static Domi buildServiceHandler(
            Context context) {
        GoogleAccountCredential credential = GoogleAccountCredential.usingAudience(
                context, AppConstants.AUDIENCE);
       // credential.setSelectedAccountName(email);

        Domi.Builder builder
                = new Domi.Builder(
                AppConstants.HTTP_TRANSPORT,
                AppConstants.JSON_FACTORY, credential);
        builder.setApplicationName("doomi");
        return builder.build();
    }
}
