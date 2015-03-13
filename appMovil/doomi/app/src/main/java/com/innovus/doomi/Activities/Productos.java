package com.innovus.doomi.Activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.innovus.doomi.Consumir.ProductosTask;
import com.innovus.doomi.R;
import com.innovus.doomi.modelos.Parent;

import java.util.ArrayList;

public class Productos extends ActionBarActivity {
//    private ExpandableListView mExpandableList;
//    public TextView nombreEmpresa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
       Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);
        String nomResta = getIntent().getStringExtra("nombre");
        toolbar.setTitle(nomResta);

        setSupportActionBar(toolbar);
        new ProductosTask(this).execute( getIntent().getStringExtra("llave"));

    }
    public void Prueba(View view){
        new ProductosTask(this).execute( getIntent().getStringExtra("llave"));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_productos, menu);
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

}
