package com.innovus.domi.form;

import java.util.ArrayList;
import java.util.List;

public class ProductoForm {
	
	private String nombreProducto;
	private String descripcionProducto;
	private String urlImageProducto;
	private List<String> detalles = new ArrayList<>(0);
	private float precioProducto;
	
	public ProductoForm(){}
	
	public ProductoForm(String nombreProducto,String descripcionProducto,float precioProducto, String urlImageProducto, List<String> detalles){
		
		this.nombreProducto =nombreProducto;
		this.descripcionProducto =descripcionProducto;
		this.precioProducto =precioProducto;
		this.urlImageProducto = urlImageProducto;
		this.detalles = detalles;
		
	}
	
	public String getNombreProducto(){
		return nombreProducto;
	}
	
	public String getDescripcionProducto(){
		return descripcionProducto;
	}
	
	public float getPrecioProducto(){
		return precioProducto;
	}

	public String getUrlImageProducto() {
		return urlImageProducto;
	}

	public void setUrlImageProducto(String urlImageProducto) {
		this.urlImageProducto = urlImageProducto;
	}
	
	public List<String> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<String> detalles) {
		this.detalles = detalles;
	}
		
}
