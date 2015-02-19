package com.innovus.doomi.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.innovus.doomi.R;

import java.util.EventListener;

/**
 * Created by Janeth Arcos on 18/02/2015.
 */
public class CirculoFragment extends Fragment {
    //intereactuar cn el otro fragmento
    ToolbarListener activityCallback;
   // private static FloatingActionButton button;
    public CirculoFragment() {
        // Required empty public constructor
    }
    //comunitacion cn el otro fragmento al hacer onButtonClick
    public interface ToolbarListener {
        public void onButtonClick();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            activityCallback = (ToolbarListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " Debe implementar ToolbarListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.circulo, container, false);


  //      final Button button;
        final FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.pink_icon);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonClicked(v);
            }
        });
        return view;



    }

    public void buttonClicked (View view) {
       activityCallback.onButtonClick( );


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
}
