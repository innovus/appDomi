package com.innovus.doomi.Activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.innovus.doomi.R;

public class ProductoPedidos extends ActionBarActivity {
    TextView producto ;
    TextView descripcion;
    TextView precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_pedidos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);
        String nomResta = getIntent().getStringExtra("nombre");
        toolbar.setTitle(nomResta);

        setSupportActionBar(toolbar);

        precio = (TextView) this.findViewById(R.id.txtPrecioP);
        producto = (TextView) this.findViewById(R.id.nomProductoP);
        descripcion = (TextView) this.findViewById(R.id.descProductoP);
        producto.setText(getIntent().getStringExtra("nombre"));
        descripcion.setText(getIntent().getStringExtra("descripcion"));
        precio.setText("$" + getIntent().getStringExtra("precio"));
        /*
        NumberPicker Np = (NumberPicker) findViewById (R.id.numberPicker);

        Np.setMaxValue(100);
        Np.setMinValue(1);
        Np.setFocusable(true);
        Np.setFocusableInTouchMode(true);
        */







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
}
