package com.innovus.doomi.Activities;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.innovus.doomi.R;
import com.innovus.doomi.db.DbDirecciones;
import com.innovus.doomi.db.DbProductos;


public class AddDirecciones extends ActionBarActivity {

    EditText etNombreDireccion;
    EditText etDireccion;
    EditText etBarrio;
    EditText etReferencia;
    String nombreDireccion;
    String direccion;
    String barrio;
    String referencia;
    String id;
    boolean bandera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_direcciones);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);

        toolbar.setTitle("Direcciones");

        setSupportActionBar(toolbar);


        etNombreDireccion = (EditText) this.findViewById(R.id.etnombredireccion);
        etDireccion = (EditText) this.findViewById(R.id.etdireccion);
        etBarrio = (EditText) this.findViewById(R.id.etBarrio);
        etReferencia = (EditText) this.findViewById(R.id.etreferencia);
        //bandera es true cuando se da click en el cardview para editar
        bandera = getIntent().getBooleanExtra("bandera",false);

        if(bandera == true) {
            nombreDireccion = getIntent().getStringExtra("nombreDireccion");
            etNombreDireccion.setText(nombreDireccion);
            referencia = getIntent().getStringExtra("referencia");
            etReferencia.setText(referencia);
            barrio= getIntent().getStringExtra("barrio");
            etBarrio.setText(barrio);
            direccion = getIntent().getStringExtra("direccion");
            etDireccion.setText(direccion);
            id = getIntent().getStringExtra("id");


        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_direcciones, menu);
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
    public void onClickGuardarDir(View v) {
        DbDirecciones admin = new DbDirecciones(this,"administracionn", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        barrio= etBarrio.getText().toString();
        nombreDireccion= etNombreDireccion.getText().toString();
        referencia= etReferencia.getText().toString();
        direccion= etDireccion.getText().toString();


        ContentValues registro = new ContentValues();  //es una clase para guardar datos
        registro.put("nombreDireccion", nombreDireccion);
        registro.put("direccion", direccion);
        registro.put("barrio", barrio);
        registro.put("referencia", referencia);
        if(bandera == true) {
            bd.update("direcciones", registro, "id = ?",new String[]{id});

        }else{
            bd.insert("direcciones", null, registro);

        }


        bd.close();

       /* Toast.makeText(this, "Se Agrego la direccion exitosamente",
                Toast.LENGTH_SHORT).show();*/

        this.finish();
    }
}
