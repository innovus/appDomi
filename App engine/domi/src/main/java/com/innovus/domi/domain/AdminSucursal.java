package com.innovus.domi.domain;

import com.google.appengine.api.datastore.PhoneNumber;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class AdminSucursal {
	
	@Id
	private long idUsuario;
	@Index
	private String nombresUsuario;	
	@Index
	private String keyGCM;
	
	
	public AdminSucursal(){}		
	
	public AdminSucursal(long idUsuario, String nombresUsuario, String keyGCM) {
		super();
		this.idUsuario = idUsuario;
		this.nombresUsuario = nombresUsuario;
		this.keyGCM = keyGCM;
	}
	
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombresUsuario() {
		return nombresUsuario;
	}
	public void setNombresUsuario(String nombresUsuario) {
		this.nombresUsuario = nombresUsuario;
	}
	public String getKeyGCM() {
		return keyGCM;
	}
	public void setKeyGCM(String keyGCM) {
		this.keyGCM = keyGCM;
	}
	
	
	
	
	

}
