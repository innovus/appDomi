package com.innovus.spofity;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


<<<<<<< HEAD:doomi android/Spofity/app/src/main/java/com/innovus/spofity/MainActivity.java
public class MainActivity extends Activity {
    public EditText editText;
    public TextView textView;
||||||| merged common ancestors
public class MainActivity extends ActionBarActivity {
    public EditText editText;
    public TextView textView;
=======
public class MyActivity extends ActionBarActivity {
>>>>>>> origin/master:doomi android/Spofity/app/src/main/java/com/innovus/spofity/MyActivity.java

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD:doomi android/Spofity/app/src/main/java/com/innovus/spofity/MainActivity.java
        setContentView(R.layout.activity_main);

        /*
        int size = getResources().getDimensionPixelSize(R.dimen.fab_size);
        Outline outline = new Outline();
        outline.setOval(0,0,size,size);
        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOutlineProvider(outline);
        */

||||||| merged common ancestors
        setContentView(R.layout.activity_main);
=======
        setContentView(R.layout.activity_my);
>>>>>>> origin/master:doomi android/Spofity/app/src/main/java/com/innovus/spofity/MyActivity.java
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
<<<<<<< HEAD:doomi android/Spofity/app/src/main/java/com/innovus/spofity/MainActivity.java
    public void onclick(View v){
       Intent i = new Intent (this,Principal.class);
       startActivity(i);
    }
||||||| merged common ancestors
    public void onclick(View v){
        editText= (EditText)findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);

        textView.setText("Hola "+editText.getText().toString()) ;

    }
=======
>>>>>>> origin/master:doomi android/Spofity/app/src/main/java/com/innovus/spofity/MyActivity.java
}
