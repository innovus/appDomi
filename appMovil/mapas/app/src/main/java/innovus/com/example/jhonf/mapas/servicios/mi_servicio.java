package innovus.com.example.jhonf.mapas.servicios;

import java.security.Provider;
import android.app.Service;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.os.IBinder;
/**
 * Created by jhonf on 03/03/2015.
 */
public class mi_servicio extends Service implements  LocationListener {
    //creamos un contexto: es un objeto android
    private final Context ctx;

    double latitud;
    double longitud;
    Location location;//puede guardar coordenadas
    boolean gpsActivo=false;//bandera
    TextView texto;
    LocationManager locationManager;//me permite manejar el tipo de conexion

    public mi_servicio(){

        super();
        this.ctx=this.getApplicationContext();
    }

    public mi_servicio( Context c){

        super();
        this.ctx=c;
        getLocation();
    }
    public void setView (View v){
        texto=(TextView)v;
        texto.setText("Coordenadas:"+latitud+","+longitud);
       }
    // metodo para obtener la ubicacion
    public void getLocation(){
        try{
            locationManager=(LocationManager)this.ctx.getSystemService(LOCATION_SERVICE);
            gpsActivo=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                } catch (Exception e){}
            if(gpsActivo){

                locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER,100*60,10,this);

                location=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);//obtiene la ultima ubicacion conocida

                latitud=location.getLatitude();
                longitud=location.getLongitude();
            }

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }



    @Override
    public IBinder onBind(Intent intent){

        return null;
    }
}
