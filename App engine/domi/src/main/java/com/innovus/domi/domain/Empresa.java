package com.innovus.domi.domain;

import static com.innovus.domi.service.OfyService.ofy;

import java.util.ArrayList;
import java.util.List;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.google.appengine.repackaged.com.google.common.collect.ImmutableList;
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
	 private String nombreEmpresa;
	
	 private String descripcion;
	 
	 
	 private List<String> gruposKeysPertenece = new ArrayList<>(0);
	 
	    
	 /*
	 @Index
	 private String  grupos;
	 */
	 //el usuario esta activo
	 private Boolean is_active;
	 
	 
	 //falta el logo
	 private Empresa (){}
	    
	 public Empresa(final long idEmpresa,final String websafeKeyCliente, final EmpresaForm empresaForm) {
		 
	  	 Preconditions.checkNotNull(empresaForm.getNombre(), "El nombre es requerido");
	   	 this.idEmpresa = idEmpresa;	
	   	 actualizaConEmpresaForm( empresaForm);
	   	 
	   	 //creamos llaves padres
	   	 this.clienteKey=Key.create(websafeKeyCliente);	
	  
	   	 //creamos padres cn las llaves padres
		 Cliente cliente = ofy().load().key(clienteKey).now();	 
		 //obtenemos ids padres
		 
		 this.idCliente = cliente.getIdCliente();	 
		 this.is_active = true;
	 }
	 
	 // aqui empiesas los get normales para obtener cualquier dato
	    
	 public  long getidEmpresa (){
	   	return idEmpresa;
	    	
	 }
	 public String getNombreEmpresa(){
	   	 return nombreEmpresa;
	 }
	 
	
	     
	 public String getDescripcion(){
	   	 return descripcion;
	 }
	     
	/* public List<String> getKeysCategorias() {
	     return ImmutableList.copyOf(keysCategorias);
	 }*/
	 
	 
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
	            return Cliente.getNombresCliente();
	        }
	    }
	 
	 
	 // este obtendra la cadena de la llave de la empresa para que pueda pasar atraves de form
	     
	     //enlazar la empresa con la empresa form
	     
	 public void actualizaConEmpresaForm(EmpresaForm empresaForm) {
	     this.nombreEmpresa = empresaForm.getNombre();
	     this.descripcion = empresaForm.getDescripcion();
	     
	         // The initial number of seatsAvailable is the same as maxAttendees.
	         // However, if there are already some seats allocated, we should subtract that numbers.
	         
	 }
	 
	 public List<String> getGruposKeysPertenece(){
		return ImmutableList.copyOf(gruposKeysPertenece);
			
	}
	 
	 //agrega grupos a la empresa
	public void addgruposKeysPertenece(String grupoKey){
		gruposKeysPertenece.add(grupoKey);
//			
	}
		
	public void deleteGrupoOfEmpresa (String grupoKey){
		if(gruposKeysPertenece.contains(grupoKey)){
			gruposKeysPertenece.remove(grupoKey);
		}
		else{
			throw new IllegalArgumentException("El Grupo No Existe: "+ grupoKey);
		}
	}
	 
}