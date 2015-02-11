package com.innovus.domi.form;

public class ProductoForm {
	
	private String nombreProducto;
	private String descripcionProducto;
	private int precioProducto;
	
	public ProductoForm(){}
	
	public ProductoForm(String nombreProducto,String descripcionProducto,int precioProducto){
		
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
	
	public int getPrecioProducto(){
		return precioProducto;
	}
	
}
