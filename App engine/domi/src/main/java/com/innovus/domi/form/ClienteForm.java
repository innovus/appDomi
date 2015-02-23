package com.innovus.domi.form;

public class ClienteForm {
	
	private String cedulaCliente;
	private String nomCliente;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String correoCliente;
	private String passwordCliente;
	private String telefonoCliente;
	private String preguntaCliente;
	private String respuestaCliente;
	
	private ClienteForm(){}
	
	private ClienteForm(String cedulaCliente,String nomCliente,String apellidoPaterno,String apellidoMaterno,String correoCliente,String passwordCliente,String telefonoCliente,String preguntaCliente,String respuestaCliente){
		this.cedulaCliente=cedulaCliente;
		this.nomCliente=nomCliente;
		this.apellidoPaterno=apellidoPaterno;
		this.apellidoMaterno=apellidoMaterno;
		this.correoCliente=correoCliente;
		this.passwordCliente=passwordCliente;
		this.telefonoCliente=telefonoCliente;
		this.preguntaCliente=preguntaCliente;
		this.respuestaCliente=respuestaCliente;
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

}
