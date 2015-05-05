package com.innovus.domi.form;

public class ProductoForm {
	
	private String nombreProducto;
	private String descripcionProducto;
	private float precioProducto;
	
	public ProductoForm(){}
	
	public ProductoForm(String nombreProducto,String descripcionProducto,float precioProducto){
		
		this.nombreProducto =nombreProducto;
		this.descripcionProducto =descripcionProducto;
		this.precioProducto =precioProducto;	
		
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
	
}
