package com.innovus.domi.form;

public class EmpresaForm {
	   
	    private String nombre;

	    private String descripcion;
	    
	    private String urlPortada;
	    private String urlLogo;

	    private EmpresaForm(){
	    }
	    
	    private EmpresaForm(String nombre, String descripcion, String urlLogo,String urlPortada){
	    	 this.nombre = nombre; 
	         this.descripcion = descripcion;
	         this.urlLogo = urlLogo;
	         this.urlPortada = urlPortada;	        
	    }
	    
	     public String getNombre(){
	    	 return nombre;
	     }
	       
	     public String getDescripcion(){
	    	 return descripcion;
	     }

		public String getUrlPortada() {
			return urlPortada;
		}

		public void setUrlPortada(String urlPortada) {
			this.urlPortada = urlPortada;
		}

		public String getUrlLogo() {
			return urlLogo;
		}

		public void setUrlLogo(String urlLogo) {
			this.urlLogo = urlLogo;
		}
	      
}
