package com.innovus.doomi.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import com.appspot.domi_app.domi.model.Producto;
import com.innovus.doomi.Activities.ProductoPedidos;
import com.innovus.doomi.modelos.Parent;
import java.util.ArrayList;
import android.widget.TextView;
import android.widget.Toast;

import com.innovus.doomi.R;

import java.util.ArrayList;
/**
 * Created by Janeth Arcos on 21/02/2015.
 */
public class ExpandibleCategoriasAdapter extends BaseExpandableListAdapter  {
    private LayoutInflater inflater;
    private ArrayList<Parent> mParent;
    private ArrayList<ArrayList<Producto>> mHijos; // Alumnos por grupo.
    private int itemLayouPadre;
    private int itemLayoutHijo;

    public ExpandibleCategoriasAdapter(Activity context, ArrayList<Parent> parent, int itemLayoutPadre, int itemLayoutHijo){
        mParent = parent;
        inflater = LayoutInflater.from(context);
        this.itemLayoutHijo = itemLayoutHijo;
        this.itemLayouPadre = itemLayoutPadre;
    }
    @Override
    public int getGroupCount() {
        return mParent.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return mParent.get(i).getArrayChildren().size();
    }

    @Override
    public String getGroup(int i) {
        return mParent.get(i).getTitle();
    }

    @Override
    public Producto getChild(int i, int i2) {
        return mParent.get(i).getArrayChildren().get(i2);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i2) {
        return i2;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean b, View view, ViewGroup viewGroup) {
        ViewHolder holder = new ViewHolder();
     //   holder.groupPosition = groupPosition;

        if (view == null) {
            view = inflater.inflate(itemLayouPadre, viewGroup,false);

        }

        TextView textView = (TextView) view.findViewById(R.id.list_item_text_view);
        textView.setText(getGroup(groupPosition).toString());


        view.setTag(holder);

        //return the entire view
        return view;

    }

    @Override
    //in this method you must set the text to see the children on the list
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View view, ViewGroup viewGroup) {

        ViewHolder holder = new ViewHolder();
        holder.childPosition = childPosition;
        holder.groupPosition = groupPosition;

        if (view == null) {
            view = inflater.inflate(itemLayoutHijo, viewGroup,false);
        }

        TextView producto = (TextView) view.findViewById(R.id.list_item_text_child);
        producto.setText(mParent.get(groupPosition).getArrayChildren().get(childPosition).getNombreProducto());
        TextView descripcion = (TextView) view.findViewById(R.id.descProducto);
        descripcion.setText(mParent.get(groupPosition).getArrayChildren().get(childPosition).getDescripcionProducto());
        TextView precio = (TextView) view.findViewById(R.id.txtPrecio);
        precio.setText(" $" + mParent.get(groupPosition).getArrayChildren().get(childPosition).getPrecioProducto());
        //textView.setText(mParent.get(groupPosition).getArrayChildren().get(childPosition));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String llaveF =  mParent.get(groupPosition).getArrayChildren().get(childPosition).getWebsafeKey();
                Intent i = new Intent (view.getContext(), ProductoPedidos.class);

                //pasar variables a la otra actividad
                i.putExtra("nombre",  mParent.get(groupPosition).getArrayChildren().get(childPosition).getNombreProducto() );
                i.putExtra("descripcion",  mParent.get(groupPosition).getArrayChildren().get(childPosition).getDescripcionProducto() );
                i.putExtra("precio", mParent.get(groupPosition).getArrayChildren().get(childPosition).getPrecioProducto().toString() );
                i.putExtra("websafeKey",  mParent.get(groupPosition).getArrayChildren().get(childPosition).getWebsafeKey() );


                // i.putExtra("nombre", propiedades.getNombre());
                view.getContext().startActivity(i);

                // Intent i = new Inten
               // Toast.makeText(view.getContext(), mParent.get(groupPosition).getArrayChildren().get(childPosition).getWebsafeKey() , Toast.LENGTH_SHORT).show();
            }
        });

        view.setTag(holder);

        //return the entire view
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i2) {
        return true;
    }



    //Intentionally put on comment, if you need on click deactivate it
    public static class ViewHolder {
        int childPosition;
         int groupPosition;
    }

}
