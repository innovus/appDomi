package com.innovus.domi.form;

import static com.innovus.domi.service.OfyService.ofy;

import com.innovus.domi.domain.Categoria;

public class PedidoForm {
	private String websafeKeyProducto;
	private int cantidadProducto;
	private String observacionPedido;
	
	public PedidoForm(){}
	private PedidoForm(String websafeKeyProducto ,int cantidadProducto,String observacionPedido){
		this.cantidadProducto=cantidadProducto;
		this.observacionPedido=observacionPedido;
		this.websafeKeyProducto=websafeKeyProducto;
	}
	public int getCantidadProducto(){
		return cantidadProducto;
	}
	
	public String getObservacionPedido(){
		return observacionPedido;
	}
	public String getWebsafeKeyProducto(){
		return websafeKeyProducto;
	}
	
	 
}
