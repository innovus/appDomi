package com.innovus.doomi.adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
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
    private float domicilio;
    private float total;
    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_DOMICILIO = 1;
    private static final int TYPE_TOTAL = 2;

    public CarritoAdapter(List<ProductoDB> data,int itemLayout,Activity activity,float domicilio, float total){
        super();
        this.itemLayout = itemLayout;
        this.productos= data;
        this.activity = activity;
        this.domicilio = domicilio;
        this.total = total;


    }

    @Override
    public CarritoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch ( viewType ) {

            //si esta agotado pongale el color si no devuelvame normal

            case TYPE_DOMICILIO:
                View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pedidos_footer, parent, false);
                ViewHolder mainViewHolder = new ViewHolder(itemLayoutView);

                FooterViewHolder viewHolderFooter = new FooterViewHolder(itemLayoutView) ;
                //(FooterViewHolder)mainViewHolder;
                //viewHolderFooter.precio.setText("$ "+domicilio);
                viewHolderFooter.descripcion.setText("Costo Domicilio");
                return viewHolderFooter;
            case TYPE_NORMAL:
                View iitemLayoutView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
                // ViewHolder vviewHolder = new ViewHolder(iitemLayoutView);

                //NormalViewHolder normalviewHolder = (NormalViewHolder)vviewHolder;
                NormalViewHolder normalviewHolder = new NormalViewHolder(iitemLayoutView);


                return normalviewHolder;

            case TYPE_TOTAL:
                View iiitemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pedidos_footer, parent, false);
                ViewHolder mmainViewHolder = new ViewHolder(iiitemLayoutView);
                //FooterViewHolder vviewHolderFooter = (FooterViewHolder)mmainViewHolder;
                FooterViewHolder vviewHolderFooter =new FooterViewHolder(iiitemLayoutView);

                vviewHolderFooter.precio.setText("$ "+total);
                vviewHolderFooter.descripcion.setText("Total");
                vviewHolderFooter.precio.setTypeface(null, Typeface.BOLD);
                vviewHolderFooter.descripcion.setTypeface(null, Typeface.BOLD);
                return vviewHolderFooter;



            default:
                View iiiitemLayoutView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
                ViewHolder ivviewHolder = new ViewHolder(iiiitemLayoutView);
                return ivviewHolder;
        }




    }

    @Override
    public void onBindViewHolder(CarritoAdapter.ViewHolder holder, int position) {

        switch ( holder.getItemViewType () ) {
            case TYPE_TOTAL:
                FooterViewHolder footerViewHolder = ( FooterViewHolder ) holder;

                footerViewHolder.precio.setText("$ "+total);
                break;


            case TYPE_NORMAL:
                if(productos.size()<=0){
                    break;

                }else{
                    ProductoDB productoDB = productos.get(position);
                    NormalViewHolder normalViewHolder = ( NormalViewHolder) holder;
                    normalViewHolder.cantidadPedido.setText(productoDB.getCantidad()+"");
                    normalViewHolder.productoPedido.setText(productoDB.getNombre());
                    normalViewHolder.totalPedidoProducto.setText("$" + (productoDB.getPrecio() * productoDB.getCantidad()) + "");
                    normalViewHolder.itemView.setTag(productoDB);
                    normalViewHolder.propiedades = productoDB;
                    break;
                }



            case TYPE_DOMICILIO:
                FooterViewHolder footerViewHolderr = ( FooterViewHolder ) holder;
                footerViewHolderr.precio.setText("$ "+domicilio);

                break;


        }
    }

    @Override
    public int getItemViewType ( int position ) {

        int viewType;
        //en la posicion del array si me devuelve un amount 0 pongale el viewType = TYPE_AGOTADO

        if(position == productos.size()){
            viewType = TYPE_DOMICILIO;
        }else if (position == productos.size()+1){
            viewType = TYPE_TOTAL;
        }else
            viewType = TYPE_NORMAL;
        return viewType;
    }
    @Override
    public int getItemCount() {
        return productos.size()+2;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder ( View itemView ) {
            super ( itemView );
        }

    }

    public class NormalViewHolder extends ViewHolder {
        public TextView productoPedido;
        public TextView cantidadPedido;
        public TextView totalPedidoProducto;
        public ProductoDB propiedades;

      /*  @InjectView ( R.id.groupTitle )
        TextView mTitle;
        @InjectView ( R.id.groupContent )
        TextView mContent;*/

        public NormalViewHolder ( View itemView ) {

            super ( itemView );
            cantidadPedido = (TextView) itemView.findViewById(R.id.txtCantidad);
            totalPedidoProducto = (TextView) itemView.findViewById(R.id.txtTotalPedido);
            // cardView = (CardView) itemView.findViewById(R.id.card_view_carrito);
            productoPedido = (TextView) itemView.findViewById(R.id.txtProductoPedido);

            //  ButterKnife.inject ( this, itemView );

        }

    }

    public class FooterViewHolder extends ViewHolder {
        // @InjectView ( R.id.groupImage )
        //ImageView mImage;
        public TextView precio;
        public TextView descripcion;


        public FooterViewHolder ( View itemView ) {
            super ( itemView );
            precio = (TextView) itemView.findViewById(R.id.txtCostoPedido);
            // cardView = (CardView) itemView.findViewById(R.id.card_view_carrito);
            descripcion = (TextView) itemView.findViewById(R.id.txtDescPedido);

            // ButterKnife.inject ( this, itemView );
        }
    }
    /*

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

    }*/

}
