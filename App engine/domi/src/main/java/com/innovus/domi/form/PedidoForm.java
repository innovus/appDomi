package com.innovus.domi.form;

public class PedidoForm {
	private int cantidadProducto;
	private String keyProducto;
	private String descripcionPedido;
	
	private PedidoForm(){}
	private PedidoForm(int cantidadProducto,String keyProducto,String descripcionPedido){
		this.cantidadProducto=cantidadProducto;
		this.keyProducto=keyProducto;
		this.descripcionPedido=descripcionPedido;
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
	

}
