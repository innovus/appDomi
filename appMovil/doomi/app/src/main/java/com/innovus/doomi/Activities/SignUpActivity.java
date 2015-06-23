package com.innovus.doomi.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.appspot.domi_app.doomiUsuarios.model.Email;
import com.appspot.domi_app.doomiUsuarios.model.PhoneNumber;
import com.appspot.domi_app.doomiUsuarios.model.UsuarioForm;
import com.innovus.doomi.Consumir.RegistroTask;
import com.innovus.doomi.Consumir.SessionManager;
import com.innovus.doomi.R;

import org.w3c.dom.Text;

public class SignUpActivity extends ActionBarActivity {
    SessionManager session;
    EditText etEmail;
    EditText etPassword1;
    EditText etPassword2;
    EditText etNombres;
    EditText etApellidos;
    EditText etNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        session = new SessionManager(this.getApplicationContext());
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword1 = (EditText) findViewById(R.id.etPassword);
        etPassword2 = (EditText) findViewById(R.id.etPassword2);
        etNombres = (EditText) findViewById(R.id.etNombres);
        etApellidos = (EditText) findViewById(R.id.etApellidos);
        etNumber = (EditText) findViewById(R.id.etPhoneNumber);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
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

    public void onClickRegistrar(View v) {
        String p1 = etPassword1.getText().toString();
        String p2 = etPassword2.getText().toString();

        if(!p1.equals(p2)){
            Toast.makeText(this, "La contraseña debe ser igual",
                    Toast.LENGTH_SHORT).show();

        }else {

            UsuarioForm usuarioForm = new UsuarioForm();
            usuarioForm.setApellidosUsuario(etApellidos.getText().toString());
            usuarioForm.setNombresUsuario(etNombres.getText().toString());

           PhoneNumber pn = new PhoneNumber() ;

           pn.setNumber(etNumber.getText().toString());
            usuarioForm.setCelularUsuario(pn);


            usuarioForm.setPasswordUsuario(etPassword1.getText().toString());

            Email e = new Email();
            e.setEmail(etEmail.getText().toString());
            usuarioForm.setCorreoUsuario(e);

            usuarioForm.setApellidosUsuario(etApellidos.getText().toString());

            new RegistroTask(this).execute(usuarioForm);

        }



    }

}
