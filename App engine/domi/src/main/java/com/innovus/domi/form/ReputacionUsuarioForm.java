package com.innovus.domi.form;

public class ReputacionUsuarioForm {
	private String comentario;
	private float puntos;
	private String websafeKeyUsuario;
	
	private  ReputacionUsuarioForm() {
		// TODO Auto-generated constructor stub
	}

	public ReputacionUsuarioForm(String comentario, float puntos,
			String websafeKeyUser) {

		this.comentario = comentario;
		this.puntos = puntos;
		this.websafeKeyUsuario = websafeKeyUser;
	}

	public String getComentario() {
		return comentario;
	}

	public float getPuntos() {
		return puntos;
	}

	public String getWebsafeKeyUsuario() {
		return websafeKeyUsuario;
	}
	
	
}
