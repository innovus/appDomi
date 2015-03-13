package com.innovus.domi.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.innovus.domi.form.PedidoForm;

@Entity 
public class Pedido {
	
	@Id
	private long keyPedido;
	private int cantidadProducto;
	@Index
	private String keyProducto;
	private String descripcionPedido;
	
	public Pedido(final long keyPedido,final PedidoForm pedidoform){
		this.keyPedido=keyPedido;
		ActualizarPedidoForm(pedidoform);
		
	}
	
	public long getKeyPedido(){
		return keyPedido;
	}
	public int getCantidadProducto(){
		return cantidadProducto;
	}
	public String getKeyProducto(){
		return keyProducto;
	}
	public String getDescripcionPedido(){
		return descripcionPedido;
	}
	void ActualizarPedidoForm(PedidoForm pedidoForm){
		this.cantidadProducto=pedidoForm.getCantidadProducto();
		this.keyProducto=pedidoForm.getKeyProducto();
		this.descripcionPedido=pedidoForm.getDescripcionPedido();
	}
	
	
	
	
	

}
