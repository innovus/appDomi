package com.innovus.domi.domain;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import com.innovus.domi.form.CarritoForm;

@Entity
public class Carrito {
	@Id
	private long idCarrito;
	 @Parent
	 @ApiResourceProperty(ignored=AnnotationBoolean.TRUE)
	 private Key<Empresa> empresaKey;
	 private float total;
	 private String observacion;
	 @Index 
     private String usuario;
     private String ubicacion;
     private String formaDePago;
     private String costoDomicilio;
     private String fecha,hora;
     
     public long getIdCarrito(){
    	 return idCarrito;
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
     public void ActualizarCarrito(CarritoForm carritoForm){
    	 this.total=carritoForm.getTotal();
    	 this.observacion=carritoForm.getObservacion();
    	 this.usuario=carritoForm.getUsuario();
    	 this.ubicacion=carritoForm.getUbicacion();
    	 this.formaDePago=carritoForm.getFormaDePago();
    	 this.costoDomicilio=carritoForm.getCostoDomicilio();
    	 this.fecha=carritoForm.getFecha();
    	 this.hora=carritoForm.getHora();
     }
}
