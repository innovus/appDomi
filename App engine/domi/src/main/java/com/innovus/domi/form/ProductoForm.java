package com.innovus.domi.form;

public class ProductoForm {
	
	private String nombreProducto;
	private String descripcionProducto;
	private String urlImageProducto;
	private float precioProducto;
	
	public ProductoForm(){}
	
	public ProductoForm(String nombreProducto,String descripcionProducto,float precioProducto, String urlImageProducto){
		
		this.nombreProducto =nombreProducto;
		this.descripcionProducto =descripcionProducto;
		this.precioProducto =precioProducto;
		this.urlImageProducto = urlImageProducto;
		
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
	
	
	
}
