package com.innovus.domi.form;

public class UsuarioForm {
	private String nombreUsuario;
	private String celularUsuario;	
	private String correoUsuario;
	private String passwordUsuario;
	
	private UsuarioForm(){}
	
	private UsuarioForm(String nombreUsuario,String celularUsuario,String correoUsuario,String passwordUsuario){
		this.nombreUsuario=nombreUsuario;
		this.celularUsuario=celularUsuario;
		this.correoUsuario=correoUsuario;
		this.passwordUsuario=passwordUsuario;
		
	}
	
	public String getNombreUsuario(){
		return nombreUsuario;
	}
	public String getCelularUsuario(){
		return celularUsuario;
	}
	public String getCorreoUsuario(){
		return correoUsuario;
	}
	public String getPasswordUsuario(){
		return passwordUsuario;
	}

}
