package com.innovus.domi.domain;


import com.google.common.base.Preconditions;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.innovus.domi.form.ClienteForm;

@Entity
public class Cliente {

	 @Id
	private long idCliente;
	 @Index
	private String cedulaCliente;
	 @Index
	private String nomCliente;
	private String apellidoPaterno;
	private String apellidoMaterno;
	
	@Index
	private String correoCliente;
	private String telefonoCliente;
	private String passwordCliente;
	
	private String preguntaCliente;
	private String respuestaCliente;
	private Cliente(){}
	
	public Cliente(final long idCliente, final ClienteForm clienteForm){
		
		// Preconditions.checkNotNull(clienteForm.getNomCliente(),"El nombre es requerido");
		 //Preconditions.checkNotNull(clienteForm.getApellidoMaterno(),"El primer apellido es requerido");
		 //Preconditions.checkNotNull(clienteForm.getCorreoCliente(),"El correo es requerido");
		 
		this.idCliente=idCliente;
		ActualizaconClienteForm(clienteForm);
	}
	
	public long getIdCliente(){		
		return idCliente;
	}	
	public String getCedulaCliente(){
		return cedulaCliente;
	}
	public String getNomCliente(){
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
	public String getTelefonoCliente(){
		return telefonoCliente;
	}
	public String getPreguntaCliente(){
		return preguntaCliente;
	}
	public String getRespuestaCliente(){
		return respuestaCliente;
	}
	
	public void ActualizaconClienteForm(ClienteForm clienteForm){
		this.cedulaCliente=clienteForm.getCedulaCliente();
		this.nomCliente=clienteForm.getNomCliente();
		this.apellidoPaterno=clienteForm.getApellidoPaterno();
		this.apellidoMaterno=clienteForm.getApellidoMaterno();
		this.correoCliente=clienteForm.getCorreoCliente();
		this.passwordCliente=clienteForm.getPasswordCliente();
		this.telefonoCliente=clienteForm.getTelefonoCliente();
		this.preguntaCliente=clienteForm.getPreguntaCliente();
		this.respuestaCliente=clienteForm.getRespuestaCliente();
	}
	
	
}
