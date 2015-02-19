package com.innovus.doomi.adapters;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.innovus.doomi.Pusheen;
import com.innovus.doomi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Janeth Arcos on 18/02/2015.
 */
public class PusheenAdapter extends RecyclerView.Adapter<PusheenAdapter.ViewHolder> {

    private List<Pusheen> pusheenArrayList;
    private int itemLayout;


    public PusheenAdapter(List<Pusheen> data, int itemLayout){

        this.pusheenArrayList = data;
        this.itemLayout = itemLayout;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

      //  public ImageView image;
        public TextView name;
        private TextView pasTime;

        public ViewHolder(View itemView) {

            super(itemView);
          //  image = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.nombre);
            pasTime = (TextView) itemView.findViewById(R.id.descripcion);

        }
    }

    @Override
    public PusheenAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PusheenAdapter.ViewHolder viewHolder, int position) {

        Pusheen pusheen = pusheenArrayList.get(position);

        viewHolder.name.setText(pusheen.getName());
        viewHolder.pasTime.setText(pusheen.getPasTime());

        /*if (pusheen.getId()!=null) {
            switch (pusheen.getId()) {
                case 1:
                    viewHolder.image.setImageResource(R.mipmap.ic_launcher);
                    break;

                case 2:
                    viewHolder.image.setImageResource(R.mipmap.ic_launcher);
                    break;

                case 3:
                    viewHolder.image.setImageResource(R.mipmap.ic_launcher);
                    break;

                case 4:
                    viewHolder.image.setImageResource(R.mipmap.ic_launcher);
                    break;

                case 5:
                    viewHolder.image.setImageResource(R.mipmap.ic_launcher);
                    break;
            }

        }else{
            viewHolder.image.setImageResource(R.mipmap.ic_launcher);
        }
        */
        viewHolder.itemView.setTag(pusheen);
    }

    @Override
    public int getItemCount() {
        return this.pusheenArrayList.size();
    }
}
