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
	 private long idEmpresa;

	 public Categoria(){}

	 public Categoria(final long idCategoria,final String nombreCategoria ,final String websafeKeyEmpresa){
		// Preconditions.checkNotNull(categoriaForm.getNombreCategoria(), "El nombre es requerido");
		
		 this.idCategoria=idCategoria;
		 this.nombreCategoria = nombreCategoria;
		
		 this.empresaKey=Key.create(websafeKeyEmpresa);//para qe empresa sea el padre de categoria
		 this.idEmpresa = ofy().load().key(empresaKey).now().getidEmpresa();	
		 
	 }
	 
	 public long getidCategoria(){
		 return idCategoria;
	 }
	 
	 public String getNombreCategoria(){
		 return nombreCategoria;
	 }
	 
	 
	 @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	 public Key<Empresa> getEmpresaKey(){
		 return empresaKey;
		 
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
	            return organizer.getNombreEmpresa();
	        }
	    }
	 
}
