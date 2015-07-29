package com.innovus.doomi.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.innovus.doomi.Consumir.ProductosTask;
import com.innovus.doomi.R;
import com.innovus.doomi.db.DbProductos;


public class Productos extends ActionBarActivity {
    private String nomResta;
    private String llaveSucursal;
    private float domicilio;



    Button  btnPedidos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);
        nomResta = getIntent().getStringExtra("nombre");
        llaveSucursal = getIntent().getStringExtra("llave");
        domicilio = getIntent().getFloatExtra("domicilio",0);
        toolbar.setTitle(nomResta);

        setSupportActionBar(toolbar);


        this.ActualizarBoton();
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

    @Override
    public void onResume(){
        super.onResume();
        this.ActualizarBoton();
        //Nuestro código a ejecutar en este momento
    }

    public void ActualizarBoton(){
        btnPedidos = (Button) this.findViewById(R.id.btnPedido);
        btnPedidos.setText("Total pedido = $" +this.getTotal());


    }
    public void OnclickCarrito(View v) {

        Intent i = new Intent (v.getContext(), Carrito.class);

        //pasar variables a la otra actividad

         i.putExtra("nombre", nomResta);
        i.putExtra("llaveSucursal",llaveSucursal);
        i.putExtra("domicilio", domicilio);
        i.putExtra("total",this.getTotal());
        v.getContext().startActivity(i);

    }
    //metodo que dice q hacer cuando le de el boton volver atras
    // pregunta si esta seguro volver atras o no
    //si esta seguro y le da si vuelve y borra lo de la base de datos x el metodo onresume
    //de la actividad principal
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Se Eliminara tu pedido")
                    .setMessage("Estás seguro?")
                    .setNegativeButton(android.R.string.cancel, null)// sin listener
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {// un listener que al pulsar, cierre la aplicacion
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        // Salir
                           Productos.this.finish();
                        }
                    })
                    .show();

// Si el listener devuelve true, significa que el evento esta procesado, y nadie debe hacer nada mas
            return true;
        }
// para las demas cosas, se reenvia el evento al listener habitual
        return super.onKeyDown(keyCode, event);
    }
    public float getTotal(){
        float total = 0;
        DbProductos admin = new DbProductos(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase(); //Create and/or open a database that will be used for reading and writing.
        //String dni = et1.getText().toString();
        Cursor fila = bd.rawQuery(  //devuelve 0 o 1 fila //es una consulta
                "select cantidad,precioProducto from productos ", null);

        //recorre la base de datos
        if (fila.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {

                total = total + (fila.getInt(0) * fila.getInt(1));
            } while(fila.moveToNext());


        }
        bd.close();
        if(total == 0){
            return total;

        }else{
            return total + domicilio;

        }

    }
}
