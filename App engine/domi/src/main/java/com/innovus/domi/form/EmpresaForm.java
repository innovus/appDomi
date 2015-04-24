package com.innovus.domi.form;

public class EmpresaForm {
	   
	    private String nombre;

	    private String descripcion;

	    private EmpresaForm(){
	    }

	    private EmpresaForm(String nombre, String descripcion){
	    	 this.nombre = nombre; 
	         this.descripcion = descripcion;
	        
	    }
	    
	     public String getNombre(){
	    	 return nombre;
	     }
	       
	     public String getDescripcion(){
	    	 return descripcion;
	     }
	      
}
