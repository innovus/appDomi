package com.innovus.domi.form;

public class ReputacionSucursalForm {
	private String comentario;
	private float puntos;
	private String websafeKeySucursal;
	
	private ReputacionSucursalForm(){}
	
	public ReputacionSucursalForm(String comentario, float puntos,
			String websafeKeySucursal) {
	
		this.comentario = comentario;
		this.puntos = puntos;
		this.websafeKeySucursal = websafeKeySucursal;
	}

	public String getComentario() {
		return comentario;
	}

	public float getPuntos() {
		return puntos;
	}

	public String getWebsafeKeySucursal() {
		return websafeKeySucursal;
	}
		
}
