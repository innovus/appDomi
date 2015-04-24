package com.innovus.domi.form;

import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.PhoneNumber;

public class ClienteForm {
	
	private String cedulaCliente;
	private String nombresCliente;
	private String apellidosCliente;
	private Email correoCliente;
	private String passwordCliente;
	private PhoneNumber telefonoCliente;
	private String pais;
	private String ciudad;

	
	private ClienteForm(){}
	
	private ClienteForm(String cedulaCliente,String nomCliente,String apellidoPaterno,String apellidoMaterno,Email correoCliente,String passwordCliente,PhoneNumber telefonoCliente,String pais,String ciudad){
		this.cedulaCliente=cedulaCliente;
		this.nombresCliente=nomCliente;
		this.apellidosCliente=apellidoPaterno;

		this.correoCliente=correoCliente;
		this.passwordCliente=passwordCliente;
		this.telefonoCliente=telefonoCliente;
		this.pais=pais;
		this.ciudad=ciudad;
	}
	
	public String getCedulaCliente(){
		return cedulaCliente;
	}
	public String getNombresCliente(){
		return nombresCliente;
	}
	public String getApellidosCliente(){
		return apellidosCliente;
	}

	public Email getCorreoCliente(){
		return correoCliente;
	}
	public String getPasswordCliente(){
		return passwordCliente;
	}
	public PhoneNumber getTelefonoCliente(){
		return telefonoCliente;
	}
	public String getPais(){
		return pais;
	}
	public String getCiudad(){
		return ciudad;
	}

}
