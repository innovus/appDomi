package com.innovus.domi.form;

public class ProductoForm {
	
	private String NombreProducto;
	private String DescripcionProducto;
	private int PrecioProducto;
	
	public ProductoForm(){}
	
	public ProductoForm(String nombreProducto,String descripcionProducto,int precioProducto){
		
		this.NombreProducto =nombreProducto;
		this.DescripcionProducto =descripcionProducto;
		this.PrecioProducto =precioProducto;	
		
	}
	
	public String getNombreProducto(){
		return NombreProducto;
	}
	
	public String getDescripcionProducto(){
		return DescripcionProducto;
	}
	
	public int getPrecioProducto(){
		return PrecioProducto;
	}
	
	
	

}
