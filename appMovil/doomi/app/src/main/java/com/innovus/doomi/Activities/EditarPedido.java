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
import android.widget.TextView;
import android.widget.Toast;

import com.innovus.doomi.R;
import com.innovus.doomi.db.DbProductos;

public class EditarPedido extends ActionBarActivity {
    TextView producto ;
    TextView descripcion;
    TextView precio;
    EditText etCantidad;
    EditText etObservacion;

    String websafeKey;
    String nombreProducto ;
    String descripcionProducto ;
    String precioProducto;
    String cantidad ;
    String observacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_pedido);
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);

        toolbar.setTitle("Modificar");
        setSupportActionBar(toolbar);

        //casteo los valores

        precio = (TextView) this.findViewById(R.id.txtPrecioPEditar);
        producto = (TextView) this.findViewById(R.id.nomProductoPEditar);
        descripcion = (TextView) this.findViewById(R.id.descProductoPEditar);
        etCantidad = (EditText) this.findViewById(R.id.etCantidadEditar);
        etObservacion = (EditText) this.findViewById(R.id.etObservacionEditar);

        websafeKey = getIntent().getStringExtra("llave");
        nombreProducto = getIntent().getStringExtra("producto");
        descripcionProducto = getIntent().getStringExtra("descripcion");
        precioProducto = getIntent().getStringExtra("precio");
        observacion = getIntent().getStringExtra("observacion");
        cantidad = getIntent().getStringExtra("cantidad");

        //pongo los valores q traigo en los text
        producto.setText(nombreProducto);
        descripcion.setText(descripcionProducto);
        precio.setText("$" + precioProducto);
        etCantidad.setText(cantidad);
        etCantidad.setSelection(etCantidad.getText().length());// para que aparesca el cursor al final
        etObservacion.setText(observacion);
        etObservacion.setSelection(etObservacion.getText().length());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_editar_pedido, menu);
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
    public void onClickActualizar(View v) {
        DbProductos admin = new DbProductos(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();  //es una clase para guardar datos
        registro.put("websafeKey", websafeKey);
        registro.put("observacion", observacion);
        cantidad = etCantidad.getText().toString();
        observacion = etObservacion.getText().toString();
        registro.put("cantidad", cantidad);
        bd.update("productos", registro, "websafeKey = ?",new String[]{websafeKey});
        bd.close();
        Toast.makeText(this, "Se Agrego el producto al carrito",
                Toast.LENGTH_SHORT).show();

        this.finish();
    }
}