package com.innovus.domi.form;







public class EmpresaForm {
	   
	    private String nombre;

	    /**
	     * The description de la empresa
	     */
	   
	    private String descripcion;
	    
	    
	    /*falta el horario
	     * 
	     * */
	    //el tiempo minimo sera en minutos 
	    
	    private int tiempoMinimo ;
	    
	    /**
	     * la ubicacion, primero ira en ciudad
	     */
	    private String ciudad;
	    
	    private String grupos;
	    
	    private EmpresaForm(){
	    }

	    private EmpresaForm(String nombre, String descripcion, int tiempoMinimo, String ciudad, String grupos){
	    	 this.nombre = nombre;
	         this.descripcion = descripcion;
	         this.tiempoMinimo = tiempoMinimo;
	         this.ciudad = ciudad;
	         this.grupos = grupos;
	         
	    }
	    
	     public String getNombre(){
	    	 return nombre;
	     }
	     
	     public String getDescripcion(){
	    	 return descripcion;
	     }
	      
	     public  int getTiempoMinimo (){
	    	 return tiempoMinimo;
	    	 }
	     
	     public String getCiudad(){
	    	 return ciudad;
	     }
	     
	     public String getGrupo() {
	         return grupos;
	     }
	   

	    
}
