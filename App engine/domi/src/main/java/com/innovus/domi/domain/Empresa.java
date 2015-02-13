package com.innovus.domi.domain;




import static com.innovus.domi.service.OfyService.ofy;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.google.common.base.Preconditions;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import com.innovus.domi.form.EmpresaForm;



@Entity
public class Empresa {
	
	 @Id
	 private long idEmpresa;
	 
	 @Parent
	 @ApiResourceProperty(ignored=AnnotationBoolean.TRUE)
	 private Key<Cliente> clienteKey;
	 
	 @ApiResourceProperty(ignored=AnnotationBoolean.TRUE)
	 private long idCliente;

	 @Index
	 private String nombre;

	 private String passEmpresa;
	    /**
	     * The description de la empresa
	     */
	
	 private String descripcion;
	    
	 
	 private int tiempoMinimo ;
	    
	    /**
	     * la ubicacion, primero ira en ciudad
	     */
	 @Index
	 private String ciudad;
	 
	 @Index
	 private String  grupos;
	 
	 private int valorMinimoPedido;
	    
	 private Empresa (){}
	    
	 public Empresa(final long idCliente, final long idEmpresa, final EmpresaForm empresaForm) {
	  	 Preconditions.checkNotNull(empresaForm.getNombre(), "El nombre es requerido");
	   	 Preconditions.checkNotNull(empresaForm.getCiudad(), "La ciudad es requerida");
	   	 Preconditions.checkNotNull(empresaForm.getTiempoMinimo(), "El tiempo minimo es requerido");
	   	// Preconditions.checkNotNull(empresaForm.getGrupo(), "el grupo es requerido");
	   	Preconditions.checkNotNull(empresaForm.getValorMinimoPedido(), "El valor minimo es requerido");
	   	 this.idEmpresa = idEmpresa;
	   	 this.idCliente = idCliente;
	   	 actualizaConEmpresaForm( empresaForm);
	   	this.clienteKey=Key.create(Cliente.class,idCliente);
	 }
	 
	 // aqui empiesas los get normales para obtener cualquier dato
	    
	 public  long getidEmpresa (){
	   	return idEmpresa;
	    	
	 }
	 public String getNombre(){
	   	 return nombre;
	 }
	 
	 public String getpassEmpresa(){
		 return passEmpresa;
	 }
	     
	 public String getDescripcion(){
	   	 return descripcion;
	 }
	     
	/* public List<String> getKeysCategorias() {
	     return ImmutableList.copyOf(keysCategorias);
	 }*/
	 
	     
	 public  long getTiempoMinimo (){
	  	 return tiempoMinimo;
	 }
	     
	 public String getCiudad(){
	   	 return ciudad;
	 }
	 
	public String getGrupos(){
	   	 return grupos;
	 }
	 
	 public long getValorMinimoPedido(){
		 return valorMinimoPedido;
	 }
	 
	 @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	 public long getIdCliente(){
		 return idCliente;
	 
	 }
	 
	 
	 
	 @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	 public Key<Cliente> getclienteKey(){
		 return clienteKey;
		 
	 }
	 
	 public String getWebsafeKey(){
		 return Key.create(clienteKey, Empresa.class,idEmpresa).getString();
	 }
	 
	 public String getClienteNombreCliente() {
	        Cliente Cliente = ofy().load().key(Key.create(Cliente.class, idCliente)).now();
	        if (Cliente == null) {
	            return String.valueOf( idCliente);
	        } else {
	            return Cliente.getNomCliente();
	        }
	    }
	 
	 // este obtendra la cadena de la llave de la empresa para que pueda pasar atraves de form
	     
	     //enlazar la empresa con la empresa form
	     
	 public void actualizaConEmpresaForm(EmpresaForm empresaForm) {
	     this.nombre = empresaForm.getNombre();
	     this.passEmpresa = empresaForm.getpassEmpresa();
	     this.descripcion = empresaForm.getDescripcion();
	     this.ciudad = empresaForm.getCiudad();
	     this.tiempoMinimo = empresaForm.getTiempoMinimo();
	     this.grupos = empresaForm.getGrupos();
	     this.valorMinimoPedido = empresaForm.getValorMinimoPedido();
	     
	         
	         // The initial number of seatsAvailable is the same as maxAttendees.
	         // However, if there are already some seats allocated, we should subtract that numbers.
	         
	 }
	 
	 

}