package adapters;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.innovus.spofity.R;

import java.util.ArrayList;

import models.Empresa;

/**
 * Created by Janeth Arcos on 16/02/2015.
 */

public class AdapterEmpresas extends RecyclerView.Adapter<AdapterEmpresas.ViewHolder> {
    private ArrayList<Empresa> empresas;//dataset
    private int itemLayout;//es la vista que va a cargar la row_empresas.xml


    public AdapterEmpresas(ArrayList<Empresa> empresas,int itemLayout){
        this.itemLayout = itemLayout;
        this.empresas = empresas;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout,parent,false);//infla el itemklayouy el row_empresas
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //lcargo el view con los valores
        Empresa empresa = empresas.get(position);
        holder.nombreEmpresa.setText(empresa.getNombre());
        holder.descripcionEmpresa.setText(empresa.getDescripcion());
        holder.minimosEmpresa.setText("Tiempo Minimo: "+empresa.getTiempoMinimo()+ "Minutos");
    }

    @Override
    public int getItemCount() {
        return empresas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nombreEmpresa;
        public TextView descripcionEmpresa;
        public TextView minimosEmpresa;

        public ViewHolder(View itemView){
            super(itemView);

           nombreEmpresa = (TextView) itemView.findViewById(R.id.nombre);
            descripcionEmpresa = (TextView) itemView.findViewById(R.id.descripcion);
            minimosEmpresa = (TextView) itemView.findViewById(R.id.minimos);

        }
    }
}
