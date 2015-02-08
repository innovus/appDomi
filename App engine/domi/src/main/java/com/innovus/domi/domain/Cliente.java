package com.innovus.domi.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.innovus.domi.form.ClienteForm;

@Entity
public class Cliente {

	 @Id
	private long idCliente;
	 @Index
	private String nomCliente;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String correoCliente;
	private String passwordCliente;
	private String preguntaCliente;
	private String respuestaCliente;
	
	public Cliente(final long idCliente, final ClienteForm clienteForm){
		this.idCliente=idCliente;
		ActualizaconClienteForm(clienteForm);
	}
	
	public long getIdCliente(){		
		return idCliente;
	}	
	public String getNombreCliente(){
		return nomCliente;
	}
	public String getApellidoPaterno(){
		return apellidoPaterno;
	}
	public String getApellidoMaterno(){
		return apellidoMaterno;
	}
	public String getCorreoCliente(){
		return correoCliente;
	}
	public String getPasswordCliente(){
		return passwordCliente;
	}
	public String getPreguntaCliente(){
		return preguntaCliente;
	}
	public String getRespuestaCliente(){
		return respuestaCliente;
	}
	
	public void ActualizaconClienteForm(ClienteForm clienteForm){
		this.nomCliente=clienteForm.getNombreCliente();
		this.apellidoPaterno=clienteForm.getApellidoPaterno();
		this.apellidoMaterno=clienteForm.getApellidoMaterno();
		this.correoCliente=clienteForm.getCorreoCliente();
		this.passwordCliente=clienteForm.getPasswordCliente();
		this.preguntaCliente=clienteForm.getPreguntaCliente();
		this.respuestaCliente=clienteForm.getRespuestaCliente();
	}
	
	
}
