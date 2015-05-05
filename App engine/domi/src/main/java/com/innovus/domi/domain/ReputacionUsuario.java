package com.innovus.domi.domain;

import static com.innovus.domi.service.OfyService.ofy;

import java.util.List;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.google.appengine.api.datastore.PhoneNumber;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import com.innovus.domi.form.ReputacionUsuarioForm;

@Entity
public class ReputacionUsuario {
	
	@Id
	private long idRepUsu;	
	@Index
	private float puntos;
	private String comentario;	
	
	@Parent
	@ApiResourceProperty(ignored=AnnotationBoolean.TRUE)
	private Key<Usuario> keyUsuario;
	
	@ApiResourceProperty(ignored=AnnotationBoolean.TRUE)
	private long idUsuario;
	
	private ReputacionUsuario(){}
	public ReputacionUsuario(long idRepUsu, ReputacionUsuarioForm reputacionUsuarioForm){
		this.idRepUsu = idRepUsu;
		this.ActualizarFormulario(reputacionUsuarioForm);
	}
	public long getIdRepUsu() {
		return idRepUsu;
	}
	public void setIdRepUsu(long idRepUsu) {
		this.idRepUsu = idRepUsu;
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
	public Key<Usuario> getKeyUsuario() {
		return keyUsuario;
	}
	public void setKeyUsuario(Key<Usuario> keyUsuario) {
		this.keyUsuario = keyUsuario;
	}
	
	public void ActualizarFormulario(ReputacionUsuarioForm reputacionUsuarioForm){
		
		this.keyUsuario=Key.create(reputacionUsuarioForm.getWebsafeKeyUsuario());	
		  
	   	 //creamos padres cn las llaves padres
		 Usuario usuario = ofy().load().key(this.keyUsuario).now();	 
		 //obtenemos ids padres
		 
		 this.idUsuario = usuario.getIdUsuario();
		this.comentario = reputacionUsuarioForm.getComentario();
		this.puntos = reputacionUsuarioForm.getPuntos();
		
	}
	public String getNombreUsuario (){
		Usuario usuario = ofy().load().key(getKeyUsuario()).now();	 
		return usuario.getNombresUsuario() + " " + usuario.getApellidosUsuario();	
	}
	
	public String getCorreoUser(){
		Usuario usuario = ofy().load().key(getKeyUsuario()).now();
		return usuario.getCorreoUser();
	}
	


}
