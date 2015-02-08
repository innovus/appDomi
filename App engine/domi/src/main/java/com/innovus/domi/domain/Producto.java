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
		 //Preconditions.checkNotNull(objetoProducto.getNombreProducto(), "El nombre es requerido");
	   	 //Preconditions.checkNotNull(objetoProducto.getDescripcionProducto(), "La descripcion es requerida");
	   	 //Preconditions.checkNotNull(objetoProducto.getPrecioProducto(), "El precio es requerido");
		 
		 this.idProducto=idProducto;
		 this.categoriaKey=Key.create(Categoria.class,idCategoria);;
		 actualizaConProductoForm(objetoProducto);
		 
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
     
     //actualiza el producto con el formulario delproducto
     public void actualizaConProductoForm(ProductoForm productoForm) {
	     this.NombreProducto = productoForm.getNombreProducto();
	     this.DescripcionProducto = productoForm.getDescripcionProducto();
	  
	     this.PrecioProducto = productoForm.getPrecioProducto();
	       
	 }
     
     @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	 public Key<Categoria> getKeyCategoria(){
		 return categoriaKey;
		 
	 }
     @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	    public Long getIdCategoria() {
	        return idCategoria;
	    }
	 
	 
	 public long getLlaveSegura(){
		 return Key.create(Categoria.class,idCategoria).getId();
	 }
	 
	 public String getCategoriaPropietaria() {
	        // Profile organizer = ofy().load().key(Key.create(Profile.class, organizerUserId)).now();
	       Categoria organizer = ofy().load().key(getKeyCategoria()).now();
	        if (organizer == null) {
	            return "";
	        } else {
	            return organizer.getNombreCategoria();
	        }
	    }
	 

}