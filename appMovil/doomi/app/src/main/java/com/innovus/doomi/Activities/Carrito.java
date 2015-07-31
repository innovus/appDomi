package com.innovus.doomi.Activities;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.innovus.doomi.Consumir.CarritoDBLocal;
import com.innovus.doomi.Consumir.CarritoTask;
import com.innovus.doomi.Consumir.SessionManager;
import com.innovus.doomi.Others.DividerItemDecoration;
import com.innovus.doomi.R;
import com.innovus.doomi.adapters.CarritoAdapter;
import com.innovus.doomi.db.DbProductos;
import com.innovus.doomi.fragments.CarritoProductosFragment;
import com.innovus.doomi.modelos.ProductoDB;
import java.util.ArrayList;

public class Carrito extends ActionBarActivity  {
    private String nomResta;
    private String llaveSucursal;
    private float domicilio;
    private float total;
    SessionManager session;
    TextView txtDireccion;

    private CarritoProductosFragment fragments = new CarritoProductosFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);
        nomResta = getIntent().getStringExtra("nombre");
        llaveSucursal = getIntent().getStringExtra("llaveSucursal");
        domicilio = getIntent().getFloatExtra("domicilio", 0);
        total = getIntent().getFloatExtra("total",0);
        toolbar.setTitle(nomResta);
        setSupportActionBar(toolbar);
        session = new SessionManager(this.getApplicationContext());
        txtDireccion = (TextView) this.findViewById(R.id.etdireccioncarrito);
        this.ActualizarFragemento();
        /*
        // CarritoProductosFragment fragments =(CarritoProductosFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentPedidos);
        CarritoProductosFragment fragments = new CarritoProductosFragment();
        FragmentManager manager = getFragmentManager();//llevar fragmnetos a nivel de cofigo
        FragmentTransaction fragmentTransaccion = manager.beginTransaction();//agregar , remover , pasar cosas al fragmento
        fragmentTransaccion.add(R.id.main_principal_carrito,fragments).hide(fragments);//por cada fragmento que tenga lo añado a la vista. hide()pa que no los muestre todos a lavez entnces mientras ocultalo
        fragmentTransaccion.show(fragments).commit();
        */
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


    public void onClickConfirmarp(View v) {
        /*Toast.makeText(this, "No has pedido nada" ,
                Toast.LENGTH_SHORT).show();
*/
        if(session.isLoggedIn()==false){
            Intent i = new Intent (v.getContext(), LoginActivity.class);
            v.getContext().startActivity(i);
        }else{
            //

            //
           String direccion = txtDireccion.getText().toString();

            new CarritoTask(this).execute(llaveSucursal,session.getKeyUser(),direccion);
            //Toast.makeText(this, String.valueOf(this.getTotal()), Toast.LENGTH_SHORT).show();
           /* Intent i = new Intent (v.getContext(), SignUpActivity.class);
            v.getContext().startActivity(i);

*/
        }
    }

    public ArrayList<ProductoDB> llenarCarrito(){
        ArrayList<ProductoDB> ListadoProductosDB = new ArrayList<ProductoDB>();
        DbProductos admin = new DbProductos (this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase(); //Create and/or open a database that will be used for reading and writing.
        //String dni = et1.getText().toString();
        Cursor fila = bd.rawQuery(  //devuelve 0 o 1 fila //es una consulta
                "select websafeKey, nombreProducto , descripcionProducto , precioProducto, cantidad , observacion from productos ", null);

        //recorre la base de datos
        if (fila.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                ProductoDB auxProductoDB = new ProductoDB(fila.getString(0),fila.getString(1),fila.getString(2),fila.getInt(3),fila.getInt(4),fila.getString(5));

                ListadoProductosDB.add(auxProductoDB);

            } while(fila.moveToNext());

        } else
            Toast.makeText(this, "No has pedido nada",
                    Toast.LENGTH_SHORT).show();
        bd.close();
        return ListadoProductosDB;
    }

    @Override
    public void onResume(){
        super.onResume();

        this.ActualizarFragemento();
        //Nuestro código a ejecutar en este momento
    }
    public void ActualizarFragemento(){
        ArrayList <ProductoDB> pusheenArrayList = new ArrayList<ProductoDB>();

        RecyclerView recyclerView = (RecyclerView) this.findViewById(R.id.my_recycler_pedidos);
        recyclerView.setHasFixedSize(true);//que todo lo optimize
        recyclerView.setAdapter(new CarritoAdapter(this.llenarCarrito(), R.layout.row_pedido_carrito, this, domicilio, total));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//linear x q es lienas o si no tambn grillas
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


        new CarritoDBLocal(this,domicilio,total).execute();

    }



}
