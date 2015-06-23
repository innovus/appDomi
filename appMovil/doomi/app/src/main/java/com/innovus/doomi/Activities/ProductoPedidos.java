package com.innovus.doomi.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.innovus.doomi.R;
import com.innovus.doomi.db.DbProductos;

public class ProductoPedidos extends ActionBarActivity {
    TextView producto ;
    TextView descripcion;
    TextView precio;
    EditText etCantidad;
    EditText etObservacion;

    String websafeKeyProducto;
    String nombreProducto ;
    String descripcionProducto ;
    String precioProducto;
    String cantidad ;
    String observacion;
    String nomResta;
    String llaveSucursal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_pedidos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);
        nomResta = getIntent().getStringExtra("nombre");
        toolbar.setTitle(nomResta);

        setSupportActionBar(toolbar);

        //traigo los valores de el otro intent

        websafeKeyProducto = getIntent().getStringExtra("websafeKeyProducto");
        nombreProducto = getIntent().getStringExtra("nombre");
        descripcionProducto = getIntent().getStringExtra("descripcion");
        precioProducto = getIntent().getStringExtra("precio");
        llaveSucursal = getIntent().getStringExtra("llaveSucursal");


        //casteo los valores

        precio = (TextView) this.findViewById(R.id.txtPrecioP);
        producto = (TextView) this.findViewById(R.id.nomProductoP);
        descripcion = (TextView) this.findViewById(R.id.descProductoP);
        etCantidad = (EditText) this.findViewById(R.id.etCantidad);
        etObservacion = (EditText) this.findViewById(R.id.etObservacion);



        //pongo los valores q traigo en los text
        producto.setText(nombreProducto);
        descripcion.setText(descripcionProducto);
        precio.setText("$" + precioProducto);
        etCantidad.setText("1");
        etCantidad.setSelection(etCantidad.getText().length());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_producto_pedidos, menu);
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

    public void alta(View v) {
        DbProductos  admin = new DbProductos(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        observacion = etObservacion.getText().toString();
        cantidad = etCantidad.getText().toString();


        ContentValues registro = new ContentValues();  //es una clase para guardar datos
        registro.put("websafeKey", websafeKeyProducto);
        registro.put("nombreProducto", nombreProducto);
        registro.put("descripcionProducto", descripcionProducto);
        registro.put("precioProducto", precioProducto);
        registro.put("observacion", observacion);

        registro.put("cantidad", cantidad);
        bd.insert("productos", null, registro);
        bd.close();

        Toast.makeText(this, "Se Agrego el producto al carrito",
                Toast.LENGTH_SHORT).show();

        this.finish();
    }
}
