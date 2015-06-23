package com.innovus.doomi.Activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.innovus.doomi.Consumir.CarritoDBLocal;
import com.innovus.doomi.Consumir.DireccionesTask;
import com.innovus.doomi.R;
import com.innovus.doomi.adapters.CarritoAdapter;
import com.innovus.doomi.adapters.DireccionesAdapter;
import com.innovus.doomi.db.DbDirecciones;
import com.innovus.doomi.db.DbProductos;
import com.innovus.doomi.modelos.Direcciones;
import com.innovus.doomi.modelos.ProductoDB;

import java.util.ArrayList;

public class ListaDirecciones extends ActionBarActivity {

    boolean is_select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direcciones);
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);

        toolbar.setTitle("Direcciones");
        setSupportActionBar(toolbar);
        is_select = getIntent().getBooleanExtra("is_select",false);
        this.ActualizarDirecciones();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_direcciones, menu);
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

    public ArrayList<Direcciones> llenarDirecciones(){
        ArrayList<Direcciones> ListadoDirecciones = new ArrayList<Direcciones>();


        DbDirecciones admin = new DbDirecciones (this,"administracionn", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase(); //Create and/or open a database that will be used for reading and writing.
        //String dni = et1.getText().toString();
        Cursor fila = bd.rawQuery(  //devuelve 0 o 1 fila //es una consulta
                "select id, nombreDireccion , direccion , barrio, referencia  from direcciones ", null);

        //recorre la base de datos
        if (fila.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Direcciones auxDirecciones = new Direcciones(fila.getString(0),fila.getString(1),fila.getString(2),fila.getString(3),fila.getString(4));

                ListadoDirecciones.add(auxDirecciones);
                //total = total + (fila.getInt(0) * fila.getInt(1));
            } while(fila.moveToNext());


        } else
            Toast.makeText(this, "Que",
                    Toast.LENGTH_SHORT).show();
        bd.close();

        return ListadoDirecciones;

    }
    public void ActualizarDirecciones(){
        ArrayList <Direcciones> pusheenArrayList = new ArrayList<Direcciones>();

        RecyclerView recyclerView = (RecyclerView) this.findViewById(R.id.recycler_view_direcciones);
        recyclerView.setHasFixedSize(true);//que todo lo optimize
        recyclerView.setAdapter(new DireccionesAdapter(this.llenarDirecciones(),R.layout.row_direcciones,this,this.is_select));
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));//linear x q es lienas o si no tambn grillas
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        new DireccionesTask(this,is_select).execute();

    }
    @Override
    public void onResume(){
        super.onResume();
        this.ActualizarDirecciones();
        //Nuestro código a ejecutar en este momento
    }
}
