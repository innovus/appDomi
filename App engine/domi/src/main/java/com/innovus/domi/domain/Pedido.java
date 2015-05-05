package com.innovus.domi.domain;

import static com.innovus.domi.service.OfyService.ofy;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.innovus.domi.form.PedidoForm;


@Entity 
public class Pedido {
	
	@Id
	private long idPedido;
	private int cantidad;
	@Index
	private String websafeKeyProducto;
	private String observacion;
	@ApiResourceProperty(ignored=AnnotationBoolean.TRUE)
	private Key<Producto> productoKey;
	
	private Pedido(){}
	
	public Pedido(final long idPedido,final PedidoForm pedidoform){
		this.idPedido=idPedido;
		ActualizarPedidoForm(pedidoform);
		
		this.productoKey = Key.create(websafeKeyProducto);
		Producto producto = ofy().load().key(productoKey).now();
			
	}
	
	void ActualizarPedidoForm(PedidoForm pedidoForm){
		this.cantidad=pedidoForm.getCantidadProducto();
		this.observacion=pedidoForm.getObservacionPedido();
		this.websafeKeyProducto = pedidoForm.getWebsafeKeyProducto();
	}
	
	public String getNombreProducto() {
        // Profile organizer = ofy().load().key(Key.create(Profile.class, organizerUserId)).now();
       Producto producto = ofy().load().key(getProductoKey()).now();
        if (producto == null) {
            return "";
        } else {
            return producto.getNombreProducto();
        }
    }

	public long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	@ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	public Key<Producto> getProductoKey() {
		return productoKey;
	}

	public void setProductoKey(Key<Producto> productoKey) {
		this.productoKey = productoKey;
	}
	
	public String getWebsafeKey(){
		 return Key.create(Pedido.class,idPedido).getString();
	 }
	
}
