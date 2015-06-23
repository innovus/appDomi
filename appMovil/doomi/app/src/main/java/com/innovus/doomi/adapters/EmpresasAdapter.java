package com.innovus.doomi.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.appspot.domi_app.doomiUsuarios.model.Sucursal;
import com.innovus.doomi.Activities.Productos;
import com.innovus.doomi.R;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Janeth Arcos on 17/02/2015.
 */
public class EmpresasAdapter extends RecyclerView.Adapter<EmpresasAdapter.ViewHolder> {
    public static Activity activity;
  /*  public List<Empresa> sucursales;//dataset
    public int itemLayout;//es la vista que va a cargar la row_empresas.xml
*/
    //prueba de busqueda
  //private List<Empresa> visibleObjects;

    //todos los objetos
  private List<Sucursal> allObjects;

    ///los objetos visibles en la busqueda
  private List<Sucursal> sucursales;

    private int itemLayout;



    public EmpresasAdapter(List<Sucursal> data,int itemLayout,Activity activity){
        super();
        this.itemLayout = itemLayout;
        //this.sucursales = data;
        //inicaializo todos los objetos
        this.allObjects = data;
        this.activity = activity;
        flushFilter();

    }

    //iniicializa para que sean visibles todos los objetos
    public void flushFilter(){
        sucursales =new ArrayList<>();
        sucursales.addAll(allObjects);
        notifyDataSetChanged();
    }



    public void setFilter(String queryText) {
        sucursales = new ArrayList<>();
        // constraint = constraint.toString().toLowerCase();
        for (Sucursal item: allObjects) {
            if (item.getNombreEmpresa().toLowerCase().contains(queryText))
                sucursales.add(item);
        }
        notifyDataSetChanged();
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
        Sucursal sucursal = sucursales.get(position);

        holder.nombreEmpresa.setText(sucursal.getNombreEmpresa() );
        holder.descripcionEmpresa.setText(sucursal.getDescripcionEmpresa());
        holder.minimosEmpresa.setText(sucursal.getTiempoMinimo()+ " Min - $" + com.innovus.doomi.Consumir.AppConstants.fmt(sucursal.getPedidoMinimo() )+ " minimo");
        holder.itemView.setTag(sucursal);
        holder.propiedades = sucursal;//paso la empresa aqui para pque en el onclick me reconosca q es esta empresa donde doy click
    }

    @Override
    public int getItemCount() {
        return sucursales.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nombreEmpresa;
        public TextView descripcionEmpresa;
        public TextView minimosEmpresa;
        public Sucursal propiedades;

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
                    i.putExtra("llave", propiedades.getWebsafekeySucursal());
                    i.putExtra("nombre", propiedades.getNombreEmpresa());
                    activity.startActivity(i);


                   // Toast.makeText(view.getContext(),"llave de consulta: "+ propiedades.getWebsafeKey(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


}
