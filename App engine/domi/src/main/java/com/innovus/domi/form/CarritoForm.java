package com.innovus.domi.form;

public class CarritoForm {

	 private float total;
	 private String observacion;
	 private String usuario;
     private String ubicacion;
     private String formaDePago;
     private String costoDomicilio;
     private String fecha,hora;
     
     private CarritoForm(){}
     
     private CarritoForm(float total,String observacion,String usuario,String ubicacion,String formaDePago,String costoDomicilio,String fecha,String hora){
    	 this.total=total;
    	 this.observacion=observacion;
    	 this.usuario=usuario;
    	 this.ubicacion=ubicacion;
    	 this.formaDePago=formaDePago;
    	 this.costoDomicilio=costoDomicilio;
    	 this.fecha=fecha;
     }
     
     public float getTotal(){
    	 return total;
     }
     public String getObservacion(){
    	 return observacion;
     }
     public String getUsuario(){
    	 return usuario;
     }
     public String getUbicacion(){
    	 return ubicacion;
     }
     public String getFormaDePago(){
    	 return formaDePago;
     }
     public String getCostoDomicilio(){
    	 return costoDomicilio;
     }
     public String getFecha(){
    	 return fecha;
     }
     public String getHora(){
    	 return hora;
     }
}
