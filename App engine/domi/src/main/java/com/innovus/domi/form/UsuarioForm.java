package com.innovus.domi.form;

import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.PhoneNumber;

public class UsuarioForm {
	private String nombresUsuario;
	private String apellidosUsuario;
	private PhoneNumber celularUsuario;	
	private Email correoUsuario;
	private String passwordUsuario;
	
	private UsuarioForm(){}
	
	private UsuarioForm(String nombreUsuario,String apellidosUsuario,PhoneNumber celularUsuario,Email correoUsuario,String passwordUsuario){
		this.nombresUsuario=nombreUsuario;
		this.celularUsuario=celularUsuario;
		this.correoUsuario=correoUsuario;
		this.passwordUsuario=passwordUsuario;
		this.apellidosUsuario=apellidosUsuario;
		
	}
	
	public String getNombresUsuario(){
		return nombresUsuario;
	}
	public String getApellidosUsuario(){
		return apellidosUsuario;
	}
	public PhoneNumber getCelularUsuario(){
		return celularUsuario;
	}
	public Email getCorreoUsuario(){
		return correoUsuario;
	}
	public String getPasswordUsuario(){
		return passwordUsuario;
	}
	
	//trae el email pero en cadena
	public String getCorreoUsuarioString(){
		return correoUsuario.getEmail();
	}

}
