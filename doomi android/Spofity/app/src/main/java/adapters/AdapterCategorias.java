package adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.innovus.spofity.R;

import java.util.ArrayList;

import models.Categoria;


/**
 * Created by Janeth Arcos on 16/02/2015.
 */
public class AdapterCategorias extends RecyclerView.Adapter<AdapterCategorias.ViewHolder>{

    private ArrayList<Categoria> categorias;//dataset
    private int itemLayout;//es la vista que va a cargar la row_empresas.xml


    public AdapterCategorias(ArrayList<Categoria> categorias,int itemLayout){
        this.itemLayout = itemLayout;
        this.categorias = categorias;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout,parent,false);//infla el itemklayouy el row_empresas
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //lcargo el view con los valores
        Categoria categoria = categorias.get(position);

        holder.nombreCategoria.setText(categoria.getNombre());
        holder.estrellas.setNumStars(Integer.parseInt("" + categoria.getId()));
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nombreCategoria;
        public RatingBar estrellas;
        public ViewHolder(View itemView){
            super(itemView);
            nombreCategoria = (TextView) itemView.findViewById(R.id.nombreCategoria);
            estrellas= (RatingBar) itemView.findViewById(R.id.estrellas);

        }
    }
}
