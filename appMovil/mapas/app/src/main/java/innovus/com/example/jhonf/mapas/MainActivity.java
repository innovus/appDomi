package innovus.com.example.jhonf.mapas;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

import java.util.MissingFormatArgumentException;

import innovus.com.example.jhonf.mapas.servicios.mi_servicio;


public class MainActivity extends ActionBarActivity {

    GoogleMap googleMap;
    MapView mapView;//es la vista del mapa, es decir lo que vemos en el celular
    //ciclo de vida para el mapview
    // onresume es el ciclo de vida de las actividades
    @Override
    protected void onResume(){
    super.onResume();
        mapView.onResume();
    }
    //onDestroy
    @Override
    protected void onDestroy(){
        super.onDestroy();
        mapView.onDestroy();
    }
    //
    @Override
    protected void onPause(){
        super.onPause();
        mapView.onPause();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //se crea el mapview, lo encontramos con find y lo inicializamos
        mapView=(MapView)findViewById(R.id.mi_mapa);
        mapView.onCreate(savedInstanceState);// aqui se inicializa el mapa
        googleMap = mapView.getMap();
        googleMap.setMapType(googleMap.MAP_TYPE_NORMAL);
        googleMap.setMyLocationEnabled(true);
        if(savedInstanceState==null){
            getFragmentManager().beginTransaction().add(R.id.container,new PlaceholderFragment()).commit();
        }
        mi_servicio servicio=new mi_servicio(getApplicationContext());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment{

        public PlaceholderFragment(){

        }
        @Override
        public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle saveInstanceState){

            View rootView=inflater.inflate(R.layout.fragment_main, container,false);
            //creamos un servicio nuevo al cual le pasamos el contexto de la app como parametro
            mi_servicio servicio=new mi_servicio(getActivity().getApplicationContext());
            //
            servicio.setView(rootView.findViewById(R.id.textoUbicacion));
            return rootView;
        }

    }
}
