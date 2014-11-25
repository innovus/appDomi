package com.innovus.domi.domain;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import com.innovus.domi.form.ProductoForm;


@Entity
public class Producto {
	
	 @Id
	 private long idProducto;
	 
	 @Parent
	 @ApiResourceProperty(ignored=AnnotationBoolean.TRUE)
	 private Key<Categoria> categoriaKey;
	 
	 @ApiResourceProperty(ignored=AnnotationBoolean.TRUE)
	 private long idCategoria;	
	 
	 @Index
	 private String NombreProducto;
	 
	 
	 private String DescripcionProducto;
	 
	 private int PrecioProducto;
	 
	 public Producto(){}
	 
	 public Producto(final long idProducto,final long idCategoria,final ProductoForm objetoProducto){
		 
		 this.idProducto=idProducto;
		 this.categoriaKey=Key.create(Categoria.class,idCategoria);;
		 this.NombreProducto= objetoProducto.getNombreProducto();
		 this.DescripcionProducto= objetoProducto.getDescripcionProducto();
		 this.PrecioProducto= objetoProducto.getPrecioProducto();	
		 
		 
	 }
	 
	 public long getidProducto(){
		 
		 return idProducto;
	 }
	 
     public String getNombreProducto(){
		 
		 return NombreProducto;
	 }
     
     public String getDescripcionProducto(){
		 
		 return DescripcionProducto;
	 }
     
     public int getPrecioProducto(){
    	 
    	 return PrecioProducto;
     }
     
     @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	 public Key<Categoria> getKeyCategoria(){
		 return categoriaKey;
		 
	 }
	 
	 public long getLlaveSegura(){
		 return Key.create(Categoria.class,idCategoria).getId();
	 }

}