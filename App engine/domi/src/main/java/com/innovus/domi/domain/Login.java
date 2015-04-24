package com.innovus.domi.domain;

//etsa clase devuelve si esta logeado o no
public class Login {
	Boolean estado;
	public Login(){
		this.estado=false;
		
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	public void loegearlo(){
		estado = true;
	}
	
	

}
