package com.innovus.doomi;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.innovus.doomi.Activities.LoginActivity;
import com.innovus.doomi.Consumir.HttpRequestTask;
import com.innovus.doomi.Consumir.SessionManager;
import com.innovus.doomi.adapters.MenuSliderAdapter;
import com.innovus.doomi.db.DbProductos;
import com.innovus.doomi.fragments.CirculoFragment;
import com.innovus.doomi.fragments.EmpresaFragment;
import com.innovus.doomi.Consumir.AppConstants;

public class Principal extends ActionBarActivity implements CirculoFragment.ToolbarListener, SearchView.OnQueryTextListener, EmpresaFragment.OnBusquedaListener /* extends Activity implements ActionBar.TabListener */
{
    //private EmpresaFragment fragments= new EmpresaFragment();
    private EmpresaFragment fragments = new EmpresaFragment();
    private CirculoFragment circulo = new CirculoFragment() ;
    SessionManager sessionManager;

    private static final String LOG_TAG = "MainActivity";
    private static final int ACTIVITY_RESULT_FROM_ACCOUNT_SELECTION = 2222;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        sessionManager = new SessionManager(getApplicationContext());

        //agregmos el toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);
        toolbar.setTitle("Doomi");
        setSupportActionBar(toolbar);


        //aqui creamos el recyclerview para el cajon de menu


        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View

        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size

        MenuSliderAdapter mAdapter = new MenuSliderAdapter(AppConstants.menuCajon,AppConstants.ICONS,this);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture
        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));                 // Setting the layout Manager
        DrawerLayout Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);        // Drawer object Assigned to the view
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,Drawer,toolbar,R.string.drawer_open ,R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }



        }; // Drawer Toggle Object Made
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();               // Finally we set the drawer toggle sync State



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
        //eliminar base de datos
        DbProductos admin = new DbProductos(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase(); //Create and/or open a database that will be used for reading and writing.

        bd.execSQL("DELETE FROM productos ");
        admin.close();
        new HttpRequestTask(this).execute();

        //prueba search


    }


    private SearchView mSearchView;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(sessionManager.isLoggedIn()){
            getMenuInflater().inflate(R.menu.menu_principal, menu);

        }
        else{
            getMenuInflater().inflate(R.menu.menu_principal_login,menu);

        }

        MenuItem searchItem = menu.findItem(R.id.buscar_principal);
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setOnQueryTextListener(this);

        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        menu.clear();
        if(sessionManager.isLoggedIn()){
            getMenuInflater().inflate(R.menu.menu_principal, menu);


        }
        else{
            getMenuInflater().inflate(R.menu.menu_principal_login,menu);


        }

        MenuItem searchItem = menu.findItem(R.id.buscar_principal);
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setOnQueryTextListener(this);

        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //si esta logeado aparece(cerrar sesion) si no esta logeado aparece  aparece iniciarsion
        if(sessionManager.isLoggedIn()){
            switch (id){
                case R.id.action_settings:
                    return true;

                case R.id.action_logout:
                    sessionManager.logoutUser();
                    Toast.makeText(this, "Cerraste sesion", Toast.LENGTH_SHORT).show();
                    return true;

                default:
                    return super.onOptionsItemSelected(item);

            }

        }else {
            switch (id) {
                case R.id.action_settings:
                    return true;

                case R.id.action_login:

                    sessionManager.checkLogin();
                    return true;

                default:
                    return super.onOptionsItemSelected(item);

            }
        }


    }

    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */
    @Override
    public void onPause(){

        super.onPause();
        DbProductos admin = new DbProductos(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase(); //Create and/or open a database that will be used for reading and writing.

        bd.execSQL("DELETE FROM productos ");
        admin.close();
        //Nuestro código a ejecutar en este momento
    }

    //hace click en el fragmento y haga esto
    @Override
    public void onButtonClick() {
        if (fragments != null) {
            new HttpRequestTask(this).execute();

        }

    }

    //metodos para escuchar la bsuqueda
   @Override
    public boolean onQueryTextSubmit(String s) {


        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        return false;
     //   return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        fragments.ponerBusqueda(s);

        return false;

        //return true;
    }

    @Override
    public void onBusqueda(String cadena) {
        this.onQueryTextChange(cadena);
    }

}
