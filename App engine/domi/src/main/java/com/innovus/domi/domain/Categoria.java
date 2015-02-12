package com.innovus.domi.domain;


import static com.innovus.domi.service.OfyService.ofy;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.Key;




@Entity //para decir qe es una tabla
public class Categoria {

	 @Id
	 private long idCategoria;	 
	 
	 @Index
	 private String nombreCategoria;
	 

	 @Parent
	 @ApiResourceProperty(ignored=AnnotationBoolean.TRUE)
	 private Key<Empresa> empresaKey;
	 
	 @ApiResourceProperty(ignored=AnnotationBoolean.TRUE)
	 private Key<Cliente> clienteKey;
	 
	 @ApiResourceProperty(ignored=AnnotationBoolean.TRUE)
	 private long idEmpresa;
	 
	 @ApiResourceProperty(ignored=AnnotationBoolean.TRUE)
	 private long idCliente;
	 
	 public Categoria(){}

	 public Categoria(final long idCategoria,final long idCliente,final String nombreCategoria ,final long idEmpresa){
		// Preconditions.checkNotNull(categoriaForm.getNombreCategoria(), "El nombre es requerido");
		 this.idCliente=idCliente;
		 this.idCategoria=idCategoria;
		 this.nombreCategoria = nombreCategoria;
		 this.clienteKey=Key.create(Cliente.class,idCliente);
		 this.empresaKey=Key.create(clienteKey,Empresa.class,idEmpresa);//para qe empresa sea el padre de categoria
		 this.idEmpresa = idEmpresa;
		 
		 
	 }
	 
	 public long getidCategoria(){
		 return idCategoria;
	 }
	 
	 public String getNombreCategoria(){
		 return nombreCategoria;
	 }
	 
	 @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	 public long getidCliente(){
		 return idCliente;
	 }
	 
	 
	 @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	 public Key<Empresa> getEmpresaKey(){
		 return empresaKey;
		 
	 }
	 @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	 public Key<Cliente> getClienteKey(){
		 return clienteKey;
		 
	 }
	 
	 public String getWefSafeKey(){
		 return Key.create(empresaKey, Categoria.class,idCategoria).getString();
		
	 }
	 
	 @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	    public Long getIdEmpresa() {
	        return idEmpresa;
	    }
	 
	 /**
	     * Obtiene el nombre de la empresa y lo muestra al obtener en le metodo rest
	     *
	     * 
	     */
	    public String getEmpresaPropietaria() {
	        // Profile organizer = ofy().load().key(Key.create(Profile.class, organizerUserId)).now();
	        Empresa organizer = ofy().load().key(getEmpresaKey()).now();
	        if (organizer == null) {
	            return "";
	        } else {
	            return organizer.getNombre();
	        }
	    }
	 
}
