package com.innovus.domi.form;

public class ClienteForm {
	
	private String nomCliente;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String correoCliente;
	private String passwordCliente;
	private String preguntaCliente;
	private String respuestaCliente;
	
	private ClienteForm(String nomClienet,String apellidoPaterno,String apellidoMaterno,String correoCliente,String passwordCliente,String preguntaCliente,String respuestaCliente){
		this.nomCliente=nomClienet;
		this.apellidoPaterno=apellidoPaterno;
		this.apellidoMaterno=apellidoMaterno;
		this.correoCliente=correoCliente;
		this.passwordCliente=passwordCliente;
		this.preguntaCliente=preguntaCliente;
		this.respuestaCliente=respuestaCliente;
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

}
