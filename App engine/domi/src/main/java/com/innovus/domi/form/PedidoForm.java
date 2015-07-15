package com.innovus.domi.form;

import static com.innovus.domi.service.OfyService.ofy;

import com.innovus.domi.domain.Categoria;

public class PedidoForm {
	private String websafeKeyProducto;
	private int cantidadProducto;
	private String observacionPedido;
	private int indexDetalle;
	
	public PedidoForm(){}
	private PedidoForm(String websafeKeyProducto ,int cantidadProducto,String observacionPedido,int indexDetalle){
		this.cantidadProducto=cantidadProducto;
		this.observacionPedido=observacionPedido;
		this.websafeKeyProducto=websafeKeyProducto;
		this.indexDetalle = indexDetalle;
		
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
	public int getIndexDetalle(){
		return this.indexDetalle;
	}
	
	 
}
