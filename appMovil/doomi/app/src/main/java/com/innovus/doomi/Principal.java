package com.innovus.doomi;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.innovus.doomi.Consumir.HttpRequestTask;
import com.innovus.doomi.fragments.CirculoFragment;
import com.innovus.doomi.fragments.EmpresaFragment;


public class Principal extends ActionBarActivity implements CirculoFragment.ToolbarListener /* extends Activity implements ActionBar.TabListener */
{


    //private EmpresaFragment fragments= new EmpresaFragment();
    private EmpresaFragment fragments = new EmpresaFragment();
    private CirculoFragment circulo = new CirculoFragment() ;

    /*private Fragment[] fragments = new Fragment[]{
        new CategoriaFragment(), new EmpresaFragment()
        };*/
    //private Fragment fragmento = new EmpresaFragment();
    private static final String LOG_TAG = "MainActivity";

    /**
     * Activity result indicating a return from the Google account selection intent.
     */
    private static final int ACTIVITY_RESULT_FROM_ACCOUNT_SELECTION = 2222;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);
        toolbar.setTitle("Doomi");
        setSupportActionBar(toolbar);
      //  setTabs();//agrego tabs


        /*cada elemento que tenemos en el vector fragments lo vamos a añadir a la vista y luego lo ocultamos

         */
        FragmentManager manager = getFragmentManager();//llevar fragmnetos a nivel de cofigo
        FragmentTransaction fragmentTransaccion = manager.beginTransaction();//agregar , remover , pasar cosas al fragmento

        fragmentTransaccion.add(R.id.main_principal,fragments).hide(fragments);//por cada fragmento que tenga lo añado a la vista. hide()pa que no los muestre todos a lavez entnces mientras ocultalo

        fragmentTransaccion.show(fragments);

        fragmentTransaccion.add(R.id.main_principal,circulo).hide(circulo);
        fragmentTransaccion.show(circulo).commit();//muestre el elemento cero  y muestre los cambios q se han hecho
       /* for(Fragment fragment: fragments){//por cada elemento de fragment obten ese fragment
            fragmentTransaccion.add(R.id.main_principal,fragment).hide(fragment);//por cada fragmento que tenga lo añado a la vista. hide()pa que no los muestre todos a lavez entnces mientras ocultalo
        }
        fragmentTransaccion.show(fragments[0]).commit();//muestre el elemento cero  y muestre los cambios q se han hecho
    */
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //hace click en el fragmento y haga esto
    @Override
    public void onButtonClick() {
        if (fragments != null) {
            new HttpRequestTask(this).execute();

        }

    }

}
