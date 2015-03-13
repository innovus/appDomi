package com.innovus.doomi.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.innovus.doomi.Activities.EditarPedido;
import com.innovus.doomi.R;
import com.innovus.doomi.modelos.ProductoDB;

import java.util.List;

/**
 * Created by personal on 10/03/15.
 */
public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.ViewHolder> {
    public static Activity activity;

    private List<ProductoDB> productos;
    private int itemLayout;

    public CarritoAdapter(List<ProductoDB> data,int itemLayout,Activity activity){
        super();
        this.itemLayout = itemLayout;
        this.productos= data;
        this.activity = activity;

    }

    @Override
    public CarritoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(CarritoAdapter.ViewHolder holder, int position) {

        //lcargo el view con los valores
        ProductoDB producto = productos.get(position);

        holder.productoPedido.setText(producto.getNombre());
        holder.cantidadPedido.setText("" +producto.getCantidad());
        holder.totalPedidoProducto.setText("$" + producto.getTotalPedido());
        holder.itemView.setTag(producto);
        holder.propiedades = producto;//paso la empresa aqui para pque en el onclick me reconosca q es esta empresa donde doy click
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView productoPedido;
        public TextView cantidadPedido;
        public TextView totalPedidoProducto;
        public ProductoDB propiedades;

        public ViewHolder(View itemView){
            super(itemView);
            productoPedido = (TextView) itemView.findViewById(R.id.txtProductoPedido);
            cantidadPedido = (TextView) itemView.findViewById(R.id.txtCantidad);
            totalPedidoProducto = (TextView) itemView.findViewById(R.id.txtTotalPedido);


            //aqui capturo el click de cada cardview
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Intent i = new Inten
                    Intent i = new Intent (activity, EditarPedido.class);
                    i.putExtra("llave", propiedades.getWebSafeKey());
                    i.putExtra("producto", propiedades.getNombre());
                    i.putExtra("descripcion", propiedades.getDescripcion());
                    i.putExtra("observacion", propiedades.getObservacion());
                    i.putExtra("cantidad", ""+propiedades.getCantidad());
                    i.putExtra("precio",""+propiedades.getPrecio());


                    activity.startActivity(i);


                    // Toast.makeText(view.getContext(),"llave de consulta: "+ propiedades.getWebsafeKey(),Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

}
