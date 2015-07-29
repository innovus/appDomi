package com.innovus.doomi.Consumir;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.appspot.domi_app.doomiTodos.DoomiTodos;
import com.appspot.domi_app.doomiTodos.model.Login;
import com.appspot.domi_app.doomiUsuarios.DoomiUsuarios;
import com.appspot.domi_app.doomiUsuarios.model.Carrito;
import com.appspot.domi_app.doomiUsuarios.model.CarritoConPedidosForm;
import com.appspot.domi_app.doomiUsuarios.model.CarritoForm;
import com.appspot.domi_app.doomiUsuarios.model.ListaPedidosForms;
import com.appspot.domi_app.doomiUsuarios.model.PedidoForm;
import com.innovus.doomi.db.DbProductos;
import com.innovus.doomi.modelos.ProductoDB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by personal on 27/05/15.
 */
public class CarritoTask extends AsyncTask<String, Void, Carrito> {
;
    private com.appspot.domi_app.doomiUsuarios.DoomiUsuarios myApiService = null;

    SessionManager session;
    String key;


    private Activity activity;

    public static void build(Context context) {
        //myApiService = buildServiceHandler(context);
    }

    public CarritoTask(Activity activity) {
        super();
        this.activity = activity;

    }

    @Override
    protected Carrito doInBackground(String... strings) {
        if (myApiService == null) { // Only do this once
            myApiService = AppConstants.buildServiceHandlerUsuarios();
        }
        // Domi.ConsultaEmpresa queryEmpresas = myApiService.consultaEmpresa();

        //llenamos el carritoForm
        try {

            CarritoForm carritoForm = new CarritoForm();
            carritoForm.setFormaDePago("Efectivo");

            /*carritoForm.setLatitud(Float.parseFloat("24.3434"));
            carritoForm.setLongitud(Float.parseFloat("24.3434"));*/
            carritoForm.setLongitud(new Float(23.3344));
            carritoForm.setLatitud(new Float(23.3344));
            carritoForm.setDireccion(strings[2]);
            carritoForm.setObservacion("prueba de android");

            //carritoForm.set.setTotal(this.getTotal());
            //obtenemos la llave de la sesion que tenemos iniciada en sessionmanager
         //   String key = session.getKeyUser();
            key = strings[1];
            carritoForm.setWebsafeKeyUsuario(strings[1]);


            //llenamos listado pedido formis
           // ListaPedidosForms listaPedidosForms =
            ListaPedidosForms listaPedidosForms = new ListaPedidosForms().setListaPedidos(this.getPedidoForms());
           // listaPedidosForms.setListaPedidos(this.getPedidoForms());


            //creamos ya todo unimos listadode pediddos con carrito
            CarritoConPedidosForm carritoConPedidosForm = new CarritoConPedidosForm();
            carritoConPedidosForm.setCarritoForm(carritoForm);
            carritoConPedidosForm.setListaPedidosForms(listaPedidosForms);


            DoomiUsuarios.AgregarCarritoConPedidos addPedido = myApiService.agregarCarritoConPedidos(strings[0], carritoConPedidosForm);


            Carrito carrito = addPedido.execute();
            return carrito;


        } catch (IOException e) {
            Log.e("Erroroooooooor", e.getMessage());
            //  Log.e("Error",e);
            return new Carrito();
        }
    }

    @Override
    protected void onPostExecute(Carrito result) {
        if (result.getWebsafeKeyUsuario().equals(key)) {
            Toast.makeText(this.activity, "Pedido Registrado", Toast.LENGTH_SHORT).show();


        } else {

            Toast.makeText(this.activity, "Error!!!!", Toast.LENGTH_SHORT).show();


        }

    }

    /*

    private ArrayList<ProductoDB> getProductos() {
        ArrayList<ProductoDB> ListadoProductosDB = new ArrayList<ProductoDB>();


        DbProductos admin = new DbProductos (activity,"administracion", null, 1);
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
                //total = total + (fila.getInt(0) * fila.getInt(1));
            } while(fila.moveToNext());
        }
        bd.close();
        return ListadoProductosDB;


    }*/

    private List<PedidoForm> getPedidoForms() {
        ArrayList<PedidoForm> ListadoPedidoForm = new ArrayList<PedidoForm>();


        DbProductos admin = new DbProductos(activity, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase(); //Create and/or open a database that will be used for reading and writing.
        //String dni = et1.getText().toString();
        Cursor fila = bd.rawQuery(  //devuelve 0 o 1 fila //es una consulta
                "select websafeKey, cantidad , observacion from productos ", null);

        //recorre la base de datos
        if (fila.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                PedidoForm auxPedidoForm = new PedidoForm();
                auxPedidoForm.setCantidadProducto(fila.getInt(1));
                auxPedidoForm.setObservacionPedido(fila.getString(2));
                auxPedidoForm.setWebsafeKeyProducto(fila.getString(0));

                ListadoPedidoForm.add(auxPedidoForm);
                //total = total + (fila.getInt(0) * fila.getInt(1));
            } while (fila.moveToNext());


        }
        bd.close();
        return ListadoPedidoForm;


    }

}
