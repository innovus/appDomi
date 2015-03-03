package com.innovus.domi.domain;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.innovus.domi.form.UsuarioForm;

@Entity
public class Usuario {
	
	 @Id
	private long keyUsuario;
	private String nombreUsuario;
	private String celularUsuario;	
	@Index
	private String correoUsuario;
	private String passwordUsuario;
    public Usuario(final long keyUsuario, final UsuarioForm usuarioForm){
		
		// Preconditions.checkNotNull(clienteForm.getNomCliente(),"El nombre es requerido");
		 //Preconditions.checkNotNull(clienteForm.getApellidoMaterno(),"El primer apellido es requerido");
		 //Preconditions.checkNotNull(clienteForm.getCorreoCliente(),"El correo es requerido");
		 
		this.keyUsuario=keyUsuario;
		ActualizarUsuarioForm(usuarioForm);
	}

	public long getKeyUsuario(){
		return keyUsuario;
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
	
	void ActualizarUsuarioForm(UsuarioForm usuarioForm){
		this.nombreUsuario=usuarioForm.getNombreUsuario();
		this.celularUsuario=usuarioForm.getCelularUsuario();
		this.correoUsuario=usuarioForm.getCorreoUsuario();
		this.passwordUsuario=usuarioForm.getPasswordUsuario();
	}
}
