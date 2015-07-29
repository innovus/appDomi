package com.innovus.doomi.Google;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.innovus.doomi.R;


public class ActivityMaps extends ActionBarActivity  implements OnMapReadyCallback{

    GoogleMap m_map;
    boolean mapReady = false;
    MarkerOptions local;
    static  final CameraPosition  janeth = CameraPosition.builder()
            .target(new LatLng(1.2202171,-77.2803347))
            .zoom(10).bearing(0).tilt(45).build();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_maps);
        local = new MarkerOptions().position(new LatLng(1.2202171,-77.2803347))
                .title("Janeth Arcos");

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
        btnHibrido.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (mapReady)
                    m_map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mapReady=true;
        m_map = googleMap;
        m_map.addMarker(local);
        flyTo(janeth);
       /* LatLng local = new LatLng(1.2202171,-77.2803347);
        CameraPosition target = CameraPosition.builder().target(local).zoom(14).build();
        m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));*/


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


}
