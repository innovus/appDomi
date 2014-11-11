package com.innovus.doomi;

import com.appspot.domi_app.domi.model.Empresa;
import com.appspot.domi_app.domi.Domi;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

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

        EndpointsAsyncTask(Context context) {
            this.context = context;
        }

        @Override
        protected List<Empresa> doInBackground(Void... params) {
            if(myApiService == null) { // Only do this once
                Domi.Builder builder = new Domi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
// options for running against local devappserver
// - 10.0.2.2 is localhost's IP address in Android emulator
// - turn off compression when running against local devappserver
                        .setRootUrl("https://domi-app.appspot.com/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
// end options for devappserver

                myApiService = builder.build();
            }

            try {
                return myApiService.consultaEmpresa().execute().getItems();
            } catch (IOException e) {
                return Collections.EMPTY_LIST;
            }
        }

        @Override
        protected void onPostExecute(List<Empresa> result) {
            for (Empresa q : result) {
                Toast.makeText(context, q.getNombre() + " : " + q.getDescripcion(), Toast.LENGTH_LONG).show();
            }
        }
    public static Domi buildServiceHandler(
            Context context, String email) {
        GoogleAccountCredential credential = GoogleAccountCredential.usingAudience(
                context, AppConstants.AUDIENCE);
        credential.setSelectedAccountName(email);

        Domi.Builder builder
                = new Domi.Builder(
                AppConstants.HTTP_TRANSPORT,
                AppConstants.JSON_FACTORY, credential);
        builder.setApplicationName("conference-central-server");
        return builder.build();
    }
}
