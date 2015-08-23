package com.innovus.doomi.Google;


import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.innovus.doomi.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class ActivityMaps extends ActionBarActivity  implements OnMapReadyCallback ,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener{

    GoogleMap m_map;
    boolean mapReady = false;
    MarkerOptions local;


    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private final String LOG_TAG ="LaurenceTestApp";
    protected Location mLastLocation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_maps);



        Button btnMap = (Button) findViewById(R.id.btnMapa);
        Button btnSatelite = (Button) findViewById(R.id.btnSatelite);
        Button btnHibrido = (Button) findViewById(R.id.btnHibrido);

        btnMap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (mapReady)
                    m_map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });
        btnSatelite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (mapReady)
                    m_map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        });
        btnHibrido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapReady)
                    m_map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();


    }
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mapReady=true;
        m_map = googleMap;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_maps, menu);
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
    private void flyTo(CameraPosition target){
        m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Connect the client.
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        // Disconnecting the client invalidates it.
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle bundle) {


        /*Intent intent = new Intent( android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
*/
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (mLastLocation != null) {
            local = new MarkerOptions().position(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude()))
                    .title("Janeth Arcos");



        }
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(10); // Update location every second

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

        if (mLastLocation != null) {
            flyTo(getCamera(mLastLocation.getLatitude(),mLastLocation.getLongitude()));
            String title = this.setLocation(mLastLocation);

            local = new MarkerOptions().position(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude()))
                    .title(title);
                    //.title("Janeth ");


        }else{
            flyTo(getCamera(1.2202171,-77.2803347));
            local = new MarkerOptions().position(new LatLng(1.2202171,-77.2803347))
                    .title("Janeth ");

        }
        m_map.clear();



        m_map.addMarker(local);
    }



    @Override
    public void onConnectionSuspended(int i) {
        Log.i(LOG_TAG, "GoogleApiClient connection has been suspend");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(LOG_TAG, "GoogleApiClient connection has failed");
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i(LOG_TAG, location.toString());
        //txtOutput.setText(location.toString());

     /*   local = new MarkerOptions().position(new LatLng(location.getLatitude(),location.getLongitude()))
                .title("Janeth Arcos");

*/


       // txtOutput.setText(Double.toString(location.getLatitude()));
    }

    /*
    public class MiLocationListener implements LocationListener{


        ActivityMaps activity;
        public ActivityMaps getActivity() {
            return activity;
        }

        public void onLocationChanged(Location loc) {
            loc.getLatitude();
            loc.getLongitude();
            String coordenadas = "Mis coordenadas son:"  + "Latitud = " + loc.getLatitude() + "Longitud = " + loc.getLongitude();
            Toast.makeText( getApplicationContext(),coordenadas, Toast.LENGTH_LONG).show();

            activity.setLocation(loc);
        }


        public void onStatusChanged(String s, int i, Bundle bundle) {

        }


        public void onProviderEnabled(String s) {
            Toast.makeText( getApplicationContext(),"Gps Activo",Toast.LENGTH_SHORT ).show();

        }


        public void onProviderDisabled(String s) {
            Toast.makeText( getApplicationContext(),"Gps Desactivado",Toast.LENGTH_SHORT ).show();

        }

    }


    }
*/
    public CameraPosition getCamera(double latitud,double longuitud){
        return  CameraPosition.builder()
                .target(new LatLng(latitud,longuitud))
                .zoom(15).bearing(0).tilt(45).build();

    }
    public String setLocation(Location loc) {
        String s = "";
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {

            try {

                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address address = list.get(0);
                    s= address.getAddressLine(1) + "";
                    // messageTextView2.setText("Mi direcci—n es: \n" + address.getAddressLine(0));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return s;
    }



}
