package com.innovus.doomi.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.appspot.domi_app.domi.model.Empresa;
import android.widget.ImageView;
import android.widget.Toast;

import com.innovus.doomi.Activities.Productos;
import com.innovus.doomi.Principal;
import com.innovus.doomi.R;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Janeth Arcos on 17/02/2015.
 */
public class EmpresasAdapter extends RecyclerView.Adapter<EmpresasAdapter.ViewHolder> {
    public static Activity activity;
  /*  public List<Empresa> empresas;//dataset
    public int itemLayout;//es la vista que va a cargar la row_empresas.xml
*/
  private List<Empresa> empresas;
    private int itemLayout;

    public EmpresasAdapter(List<Empresa> data,int itemLayout,Activity activity){
        super();
        this.itemLayout = itemLayout;
        this.empresas = data;
        this.activity = activity;

    }

    @Override
    public EmpresasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(EmpresasAdapter.ViewHolder holder, int position) {

        //lcargo el view con los valores
        Empresa empresa = empresas.get(position);
        /*
        holder.nombreEmpresa.setText(empresa.getNombre());
        holder.descripcionEmpresa.setText(empresa.getDescripcion());
        holder.minimosEmpresa.setText(empresa.getTiempoMinimo()+ " Min - $" + empresa.getValorMinimoPedido() + " minimo");
        holder.itemView.setTag(empresa);
        */
        holder.nombreEmpresa.setText(empresa.getNombre());
        holder.descripcionEmpresa.setText(empresa.getDescripcion());
        holder.minimosEmpresa.setText(empresa.getTiempoMinimo()+ " Min - $" + empresa.getValorMinimoPedido() + " minimo");
        holder.itemView.setTag(empresa);
        holder.propiedades = empresa;//paso la empresa aqui para pque en el onclick me reconosca q es esta empresa donde doy click
    }

    @Override
    public int getItemCount() {
        return empresas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nombreEmpresa;
        public TextView descripcionEmpresa;
        public TextView minimosEmpresa;
        public Empresa propiedades;

        public ViewHolder(View itemView){
            super(itemView);

            nombreEmpresa = (TextView) itemView.findViewById(R.id.nombre);
            descripcionEmpresa = (TextView) itemView.findViewById(R.id.descripcion);
            minimosEmpresa = (TextView) itemView.findViewById(R.id.minimos);


            //aqui capturo el click de cada cardview
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // Intent i = new Inten
                    Intent i = new Intent (activity, Productos.class);


                    i.putExtra("llave", propiedades.getWebsafeKey());
                    i.putExtra("nombre", propiedades.getNombre());
                    activity.startActivity(i);


                   // Toast.makeText(view.getContext(),"llave de consulta: "+ propiedades.getWebsafeKey(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
