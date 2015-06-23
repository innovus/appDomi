package com.innovus.doomi.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


import com.innovus.doomi.Consumir.LoginTask;
import com.innovus.doomi.R;

public class LoginActivity extends ActionBarActivity {
    EditText etEmail;
    EditText etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);

        toolbar.setTitle("Login");
        setSupportActionBar(toolbar);


        etEmail = (EditText) this.findViewById(R.id.etEmail);
        etPassword = (EditText) this.findViewById(R.id.etPaswword);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
    public void onClickLogin(View v) {



        new LoginTask(this,etEmail.getText().toString(),etPassword.getText().toString()).execute();
        finish();

    }
    public void onClickRegistrarse(View v){
        Intent i = new Intent(this, SignUpActivity.class);
        this.startActivity(i);
    }


}