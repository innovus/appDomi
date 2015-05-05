package com.innovus.domi.domain;

import static com.innovus.domi.service.OfyService.ofy;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import com.innovus.domi.form.ReputacionSucursalForm;
import com.innovus.domi.form.ReputacionUsuarioForm;

@Entity
public class ReputacionSucursal {
	
	@Id
	private long idRepSuc;	
	@Index
	private float puntos;
	private String comentario;	
	
	@Parent
	@ApiResourceProperty(ignored=AnnotationBoolean.TRUE)
	private Key<Sucursal> keySucursal;
	
	@ApiResourceProperty(ignored=AnnotationBoolean.TRUE)
	private long idSucursal;
	
	private ReputacionSucursal(){}
	public ReputacionSucursal(long idRepSucursal, ReputacionSucursalForm reputacionSucursalForm){
		this.idRepSuc = idRepSucursal;
		this.ActualizarFormulario(reputacionSucursalForm);
	}
	public long getIdRepSuc() {
		return idRepSuc;
	}
	public void setIdRepUsu(long idRepSuc) {
		this.idRepSuc = idRepSuc;
	}
	public float getPuntos() {
		return puntos;
	}
	public void setPuntos(float puntos) {
		this.puntos = puntos;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Key<Sucursal> getKeySucursal() {
		return keySucursal;
	}
	public void setKeySucursal(Key<Sucursal> keySucursal) {
		this.keySucursal = keySucursal;
	}
	
	public void ActualizarFormulario(ReputacionSucursalForm reputacionSucursalForm){
		
		this.keySucursal=Key.create(reputacionSucursalForm.getWebsafeKeySucursal());	
		  
	   	 //creamos padres cn las llaves padres
		Sucursal sucursal = ofy().load().key(this.keySucursal).now();	 
		 //obtenemos ids padres
		 
		 this.idSucursal = sucursal.getIdSucursal();
		this.comentario = reputacionSucursalForm.getComentario();
		this.puntos = reputacionSucursalForm.getPuntos();
		
	}
	public String getNombrSucursal (){
		Sucursal sucursal = ofy().load().key(getKeySucursal()).now();	 
		return sucursal.getNombreSucursal();	
	}
	


}
