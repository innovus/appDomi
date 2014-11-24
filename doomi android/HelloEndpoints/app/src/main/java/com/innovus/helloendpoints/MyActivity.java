package com.innovus.helloendpoints;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.appspot.universal_team_757.helloworld.Helloworld;
import com.appspot.universal_team_757.helloworld.Helloworld.Greetings.GetGreeting;
import com.appspot.universal_team_757.helloworld.Helloworld.Greetings.Multiply;
import com.appspot.universal_team_757.helloworld.model.HelloGreeting;
import com.google.common.base.Strings;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


public class MyActivity extends ActionBarActivity {
    /* Note la LOG_TAG definido en la parte superior. Esta etiqueta nos permite filtrar los mensajes de error por el Activty
     nombre de etiqueta mientras se ejecuta en modo de depuración en Android Estudio

     */

    private static final String LOG_TAG = "MainActivity";
    private GreetingsDataAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        // Prevent the keyboard from being visible upon startup.
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        ListView listView = (ListView) findViewById(R.id.greetings_list_view);
        mListAdapter = new GreetingsDataAdapter((Application) getApplication());
        listView.setAdapter(mListAdapter);

    }

/*
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
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }
    */

    /**
     * Simple use of an ArrayAdapter but we're using a static class to ensure no references to the
     * Activity exists.
     */
    static class GreetingsDataAdapter extends ArrayAdapter {
        GreetingsDataAdapter(Application application) {
            super((Application)application.getApplicationContext(), android.R.layout.simple_list_item_1,
                    application.greetings);
        }

        void replaceData(HelloGreeting[] greetings) {
            clear();
            for (HelloGreeting greeting : greetings) {
                add(greeting);
            }
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getView(position, convertView, parent);

            HelloGreeting greeting = (HelloGreeting)this.getItem(position);

            StringBuilder sb = new StringBuilder();

            Set<String> fields = greeting.keySet();
            boolean firstLoop = true;
            for (String fieldName : fields) {
                // Append next line chars to 2.. loop runs.
                if (firstLoop) {
                    firstLoop = false;
                } else {
                    sb.append("\n");
                }

                sb.append(fieldName)
                        .append(": ")
                        .append(greeting.get(fieldName));
            }

            view.setText(sb.toString());
            return view;
        }
    }


    /////////////////////////////////////////////////////////////////////////
    //obtener 1 saludo
    /**
     * This method is invoked when the "Get Greeting" button is clicked. See activity_main.xml for
     * the dynamic reference to this method.
     */
    public void onClickGetGreeting(View view) {
        View rootView = view.getRootView();
        TextView greetingIdInputTV = (TextView)rootView.findViewById(R.id.greeting_id_edit_text);
        if (greetingIdInputTV.getText()==null ||
                Strings.isNullOrEmpty(greetingIdInputTV.getText().toString())) {
            Toast.makeText(this, "Input a Greeting ID", Toast.LENGTH_SHORT).show();
            return;
        };

        String greetingIdString = greetingIdInputTV.getText().toString();
        int greetingId = Integer.parseInt(greetingIdString);

        // Use of an anonymous class is done for sample code simplicity. {@code AsyncTasks} should be
        // static-inner or top-level classes to prevent memory leak issues.
        // @see http://goo.gl/fN1fuE @26:00 for a great explanation.
        AsyncTask<Integer, Void, HelloGreeting> getAndDisplayGreeting =
                new AsyncTask<Integer, Void, HelloGreeting> () {
                    @Override
                    protected HelloGreeting doInBackground(Integer... integers) {
                        // Retrieve service handle.
                        Helloworld apiServiceHandle = AppConstants.getApiServiceHandle();

                        try {
                            GetGreeting getGreetingCommand = apiServiceHandle.greetings().getGreeting(integers[0]);
                            HelloGreeting greeting = getGreetingCommand.execute();
                            return greeting;
                        } catch (IOException e) {
                            Log.e(LOG_TAG, "Exception during API call", e);
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(HelloGreeting greeting) {
                        if (greeting!=null) {
                            displayGreetings(greeting);
                        } else {
                            Log.e(LOG_TAG, "No greetings were returned by the API.");
                        }
                    }
                };

        getAndDisplayGreeting.execute(greetingId);
    }

    private void displayGreetings(HelloGreeting... greetings) {
        String msg;
        if (greetings==null || greetings.length < 1) {
            msg = "Greeting was not present";
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        } else {
            Log.d(LOG_TAG, "Displaying " + greetings.length + " greetings.");

            List<HelloGreeting> greetingsList = Arrays.asList(greetings);
            mListAdapter.replaceData(greetings);
        }
    }


    /////////////////////////////enviar saludo
    public void onClickSendGreetings(View view) {
        View rootView = view.getRootView();

        TextView greetingCountInputTV = (TextView)rootView.findViewById(R.id.greeting_count_edit_text);
        if (greetingCountInputTV.getText()==null ||
                Strings.isNullOrEmpty(greetingCountInputTV.getText().toString())) {
            Toast.makeText(this, "Input a Greeting Count", Toast.LENGTH_SHORT).show();
            return;
        };

        String greetingCountString = greetingCountInputTV.getText().toString();
        final int greetingCount = Integer.parseInt(greetingCountString);

        TextView greetingTextInputTV = (TextView)rootView.findViewById(R.id.greeting_text_edit_text);
        if (greetingTextInputTV.getText()==null ||
                Strings.isNullOrEmpty(greetingTextInputTV.getText().toString())) {
            Toast.makeText(this, "Input a Greeting Message", Toast.LENGTH_SHORT).show();
            return;
        };

        final String greetingMessageString = greetingTextInputTV.getText().toString();


        AsyncTask<Void, Void, HelloGreeting> sendGreetings = new AsyncTask<Void, Void, HelloGreeting> () {
            @Override
            protected HelloGreeting doInBackground(Void... unused) {
                // Retrieve service handle.
                Helloworld apiServiceHandle = AppConstants.getApiServiceHandle();

                try {
                    HelloGreeting greeting = new HelloGreeting();
                    greeting.setMessage(greetingMessageString);

                    Multiply multiplyGreetingCommand = apiServiceHandle.greetings().multiply(greetingCount,
                            greeting);
                    greeting = multiplyGreetingCommand.execute();
                    return greeting;
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Exception during API call", e);
                }
                return null;
            }

            @Override
            protected void onPostExecute(HelloGreeting greeting) {
                if (greeting!=null) {
                    displayGreetings(greeting);


                } else {
                    Log.e(LOG_TAG, "No greetings were returned by the API.");
                }
            }
        };

        sendGreetings.execute((Void)null);
    }
}
