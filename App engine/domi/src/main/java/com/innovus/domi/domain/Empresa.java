package com.innovus.domi.domain;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.innovus.domi.form.EmpresaForm;



@Entity
public class Empresa {
	
	 @Id
	 private long idEmpresa;

	    /**
	     * Nombre de la empresa
	     */
	 @Index
	 private String nombre;

	    /**
	     * The description de la empresa
	     */
	
	 private String descripcion;
	    
	 /*   /**
	     *claves de las conferencias que la empresa registra.
	     *
	* private List<String> keysCategorias = new ArrayList<>(0);
	    
	*/
	    /*falta el horario
	     * 
	     * */
	    //el tiempo minimo sera en minutos 
	    
	 private int tiempoMinimo ;
	    
	    /**
	     * la ubicacion, primero ira en ciudad
	     */
	 @Index
	 private String ciudad;
	 
	 private String  grupos;
	 
	 private int valorMinimoPedido;
	    
	 private Empresa (){}
	    
	 public Empresa(final long idEmpresa, final EmpresaForm empresaForm) {
	  	 Preconditions.checkNotNull(empresaForm.getNombre(), "El nombre es requerido");
	   	 Preconditions.checkNotNull(empresaForm.getCiudad(), "La ciudad es requerida");
	   	 Preconditions.checkNotNull(empresaForm.getTiempoMinimo(), "El tiempo minimo es requerido");
	   	// Preconditions.checkNotNull(empresaForm.getGrupo(), "el grupo es requerido");
	   	Preconditions.checkNotNull(empresaForm.getValorMinimoPedido(), "El valor minimo es requerido");
	   	 this.idEmpresa = idEmpresa;
	   	 actualizaConEmpresaForm( empresaForm);
	 }
	 
	 // aqui empiesas los get normales para obtener cualquier dato
	    
	 public  long getidEmpresa (){
	   	return idEmpresa;
	    	
	 }
	 public String getNombre(){
	   	 return nombre;
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
	 
	public String getGrupo(){
	   	 return grupos;
	 }
	 
	 public long getValorMinimoPedido(){
		 return valorMinimoPedido;
	 }
	 
	 // este obtendra la cadena de la llave de la empresa para que pueda pasar atraves de formularios
	/* public String getWebsafeKey(){
		 return Key.create(Empresa.class,idEmpresa);
				 
	 }
	 */
	 
	/* public void addKeyCategoria(String keyCategoria) {
	     keysCategorias.add(keyCategoria);
	 }
	 */
	     
	     //enlazar la empresa con la empresa form
	     
	 public void actualizaConEmpresaForm(EmpresaForm empresaForm) {
	     this.nombre = empresaForm.getNombre();
	     this.descripcion = empresaForm.getDescripcion();
	     this.ciudad = empresaForm.getCiudad();
	     this.tiempoMinimo = empresaForm.getTiempoMinimo();
	     this.grupos = empresaForm.getGrupos();
	     this.valorMinimoPedido = empresaForm.getValorMinimoPedido();
	         
	         // The initial number of seatsAvailable is the same as maxAttendees.
	         // However, if there are already some seats allocated, we should subtract that numbers.
	         
	 }
	 
	 

}