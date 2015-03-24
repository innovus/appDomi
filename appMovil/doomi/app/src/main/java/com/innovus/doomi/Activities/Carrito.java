package com.innovus.doomi.Activities;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.innovus.doomi.R;
import com.innovus.doomi.fragments.CarritoProductosFragment;

public class Carrito extends ActionBarActivity implements  SearchView.OnQueryTextListener {
    private String nomResta;

    private CarritoProductosFragment fragments = new CarritoProductosFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);
        nomResta = getIntent().getStringExtra("nombre");
        toolbar.setTitle(nomResta);
        setSupportActionBar(toolbar);

       // CarritoProductosFragment fragments =(CarritoProductosFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentPedidos);
        CarritoProductosFragment fragments = new CarritoProductosFragment();
        FragmentManager manager = getFragmentManager();//llevar fragmnetos a nivel de cofigo
        FragmentTransaction fragmentTransaccion = manager.beginTransaction();//agregar , remover , pasar cosas al fragmento
        fragmentTransaccion.add(R.id.main_principal_carrito,fragments).hide(fragments);//por cada fragmento que tenga lo añado a la vista. hide()pa que no los muestre todos a lavez entnces mientras ocultalo
        fragmentTransaccion.show(fragments).commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_carrito, menu);
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

    @Override
    public boolean onQueryTextSubmit(String s) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        return false;

    }
    public void onClickConfirmar(View v) {

       // Intent i = new Intent (v.getContext(), pruebaSlider.class);

        //pasar variables a la otra actividad

        //v.getContext().startActivity(i);

    }
}
