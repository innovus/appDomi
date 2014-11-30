package com.innovus.domi.domain;

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
	 private String NombreCategoria;
	 
	 @Parent
	 @ApiResourceProperty(ignored=AnnotationBoolean.TRUE)
	 private Key<Empresa> empresaKey;
	 
	 @ApiResourceProperty(ignored=AnnotationBoolean.TRUE)
	 private long idEmpresa;	
	 
	 public Categoria(){}

	 public Categoria(final long idCategoria,final String nombre ,final long idEmpresa){
		// Preconditions.checkNotNull(categoriaForm.getNombreCategoria(), "El nombre es requerido");
		 this.idCategoria=idCategoria;
		 this.NombreCategoria = nombre;
		 this.empresaKey=Key.create(Empresa.class,idEmpresa);//para qe empresa sea el padre de categoria
		 this.idEmpresa = idEmpresa;
		 
		 
	 }
	 
	 public long getidCategoria(){
		 return idCategoria;
	 }
	 
	 public String getNombreCategoria(){
		 return NombreCategoria;
	 }
	 
	 public long getidEmpresa(){
		 return idEmpresa;
	 }
	 
	 @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	 public Key<Empresa> getKeyEmpresa(){
		 return empresaKey;
		 
	 }
	 
	 public long getLlaveSegura(){
		 return Key.create(Empresa.class,idEmpresa).getId();
	 }
	 
	 @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	    public Long getIdEmpresa() {
	        return idEmpresa;
	    }
	 
}
