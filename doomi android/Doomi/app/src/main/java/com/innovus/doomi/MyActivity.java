package com.innovus.doomi;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.appspot.domi_app.domi.Domi;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.appspot.domi_app.domi.Domi;
import com.appspot.domi_app.domi.model.Empresa;
import com.appspot.domi_app.domi.model.EmpresaCollection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MyActivity extends ActionBarActivity {

    private static final String LOG_TAG = "MainActivity";
    public static final ArrayList<String> empresasA = new ArrayList<String>();

    /**
     * Activity result indicating a return from the Google account selection intent.
     */
    private static final int ACTIVITY_RESULT_FROM_ACCOUNT_SELECTION = 2222;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new HttpRequestTask().execute();
        setContentView(R.layout.activity_my);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        new HttpRequestTask().execute();
    }

    public void getEmpresas(View v) {
       // new EndpointsAsyncTask(this).execute();
        new HttpRequestTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private ArrayAdapter<String> mEmpresasAdapter;
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            //List<String> empresas = new ArrayList<String>();
            mEmpresasAdapter = new ArrayAdapter<String>(getActivity(),R.layout.list_item_empresa,R.id.list_item_empresa_textview,empresasA);


            View rootView = inflater.inflate(R.layout.fragment_my, container, false);
            ListView listView = (ListView) rootView.findViewById(R.id.listViewEmpresas);
            listView.setAdapter(mEmpresasAdapter);
            return rootView;
        }
    }

    public static Domi buildServiceHandler() {
        // GoogleAccountCredential credential = GoogleAccountCredential.usingAudience(
        //       context, AppConstants.AUDIENCE);
        //credential.setSelectedAccountName("204916157214-1hho3fgafmt30l9kt7rljls1qttbeb3n@developer.gserviceaccount.com");

        Domi.Builder builder
                = new Domi.Builder(
                AppConstants.HTTP_TRANSPORT,
                AppConstants.JSON_FACTORY, null);
        
        //builder.setApplicationName("domi-app");
        return builder.build();
    }


    private class HttpRequestTask extends AsyncTask<Void, Void, List<Empresa>> {


        private com.appspot.domi_app.domi.Domi myApiService = null;

        @Override
        protected List<Empresa> doInBackground(Void... params) {
            if (myApiService == null) { // Only do this once
                myApiService = buildServiceHandler();
            }
            // Domi.ConsultaEmpresa queryEmpresas = myApiService.consultaEmpresa();

            try {
                Domi.ConsultaEmpresas queryEmpresas = myApiService.consultaEmpresas();

                EmpresaCollection empresaCollection = queryEmpresas.execute();
                if (empresaCollection != null && empresaCollection.getItems() != null) {
                    List<Empresa> empresas = empresaCollection.getItems();
                    return empresas;
                }
                //return Collections.EMPTY_LIST;
                return myApiService.consultaEmpresas().execute().getItems();

            } catch (IOException e) {
                return Collections.EMPTY_LIST;
            }


        }

        @Override
        protected void onPostExecute(List<Empresa> result) {
           // TextView greetingIdText = (TextView) findViewById(R.id.prueba);

           // String mostrar = "";
            empresasA.clear();

            // mostrar = result.get(0).getNombre();
            for (Empresa q : result) {
                empresasA.add(q.getNombre());
               // mostrar += " +" + q.getNombre() + " - " + " " + q.getDescripcion() + " ";

                //Toast.makeText(context, mostrar, Toast.LENGTH_LONG).show();
            }
            //Toast.makeText(getApplicationContext(), mostrar, Toast.LENGTH_LONG).show();
           // greetingIdText.setText(mostrar);

        }

    }
}
