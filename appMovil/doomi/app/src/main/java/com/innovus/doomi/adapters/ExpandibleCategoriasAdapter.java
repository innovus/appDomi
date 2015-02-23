package com.innovus.doomi.adapters;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;


import com.innovus.doomi.modelos.Parent;

import java.util.ArrayList;
import android.database.DataSetObserver;

import android.widget.Button;
import android.widget.TextView;
import com.innovus.doomi.R;

import java.util.ArrayList;
/**
 * Created by Janeth Arcos on 21/02/2015.
 */
public class ExpandibleCategoriasAdapter extends BaseExpandableListAdapter {
    private LayoutInflater inflater;
    private ArrayList<Parent> mParent;

    public ExpandibleCategoriasAdapter(Context context, ArrayList<Parent> parent){
        mParent = parent;
        inflater = LayoutInflater.from(context);
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
    public Object getGroup(int i) {
        return mParent.get(i).getTitle();
    }

    @Override
    public Object getChild(int i, int i2) {
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
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean b, View view, ViewGroup viewGroup) {
        ViewHolder holder = new ViewHolder();
     //   holder.groupPosition = groupPosition;

        if (view == null) {
            view = inflater.inflate(R.layout.exp_lista_categoria, viewGroup,false);
        }

        TextView textView = (TextView) view.findViewById(R.id.list_item_text_view);
        textView.setText(getGroup(groupPosition).toString());

        view.setTag(holder);

        //return the entire view
        return view;

    }

    @Override
    //in this method you must set the text to see the children on the list
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup viewGroup) {

        ViewHolder holder = new ViewHolder();
        holder.childPosition = childPosition;
        holder.groupPosition = groupPosition;

        if (view == null) {
            view = inflater.inflate(R.layout.exp_lista_producto, viewGroup,false);
        }

        TextView textView = (TextView) view.findViewById(R.id.list_item_text_child);
        textView.setText(mParent.get(groupPosition).getArrayChildren().get(childPosition).getNombreProducto());
        //textView.setText(mParent.get(groupPosition).getArrayChildren().get(childPosition));

        view.setTag(holder);

        //return the entire view
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i2) {
        return true;
    }


    protected class ViewHolder {
        protected int childPosition;
        protected int groupPosition;
        protected Button button;
    }


}
