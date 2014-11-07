package com.innovus.domi.form;

public class CategoriaForm {
	
	 private String NombreCategoria;
	 
	 private CategoriaForm(){}
	 
	public CategoriaForm(String NombreCategoria){
		 this.NombreCategoria = NombreCategoria;
	 }
	 
	 public String getNombreCategoria(){
		 return NombreCategoria;
	 }

}
