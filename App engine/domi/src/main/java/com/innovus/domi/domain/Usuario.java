package com.innovus.domi.domain;

import static com.innovus.domi.service.OfyService.factory;
import static com.innovus.domi.service.OfyService.ofy;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.google.appengine.api.datastore.PhoneNumber;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Work;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import com.innovus.domi.form.ClienteForm;
import com.innovus.domi.form.UsuarioForm;

@Entity
public class Usuario {
	
	

	@Id
	private long idUsuario;
	
	@Index
	private String nombresUsuario;
	private String apellidosUsuario;	
	private PhoneNumber telefonoUsuario;
	//private String passwordCliente;
	
	 @Parent
	 @ApiResourceProperty(ignored=AnnotationBoolean.TRUE)
	 private Key<User> keyUser;
	 
	// @ApiResourceProperty(ignored=AnnotationBoolean.TRUE)
	// private long idUser;

	private Usuario(){

	}
	
	public Usuario(final long idUsuario, final UsuarioForm usuarioForm){
		
		// Preconditions.checkNotNull(clienteForm.getNomCliente(),"El nombre es requerido");
		 //Preconditions.checkNotNull(clienteForm.getApellidoMaterno(),"El primer apellido es requerido");
		 //Preconditions.checkNotNull(clienteForm.getCorreoCliente(),"El correo es requerido");
		
		this.idUsuario=idUsuario;
		ActualizaconUsuarioForm(usuarioForm);
		//this.keyUser=Key.create(Usuario.class,idCliente);
	}
	
	
	public void ActualizaconUsuarioForm(final UsuarioForm usuarioForm){
		
		this.nombresUsuario=usuarioForm.getNombresUsuario();
		this.apellidosUsuario=usuarioForm.getApellidosUsuario();
	//

		this.telefonoUsuario=usuarioForm.getCelularUsuario();
		
		final Key<User> userKey=factory().allocateId(User.class);
		  final long userId=userKey.getId();
		  //this.idUser = userId;
		  this.keyUser = userKey;
		  User user =ofy().transact(new Work<User>(){
			  @Override
	          public User run(){
				  User user = new User(userId,usuarioForm.getCorreoUsuario(),
						  usuarioForm.getPasswordUsuario(),true,false);
				  ofy().save().entities(user).now();
				 return user;
			  }  
		  });
		  
	}
	
	
	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombresUsuario() {
		return nombresUsuario;
	}

	public void setNombresUsuario(String nombresUsuario) {
		this.nombresUsuario = nombresUsuario;
	}

	public String getApellidosUsuario() {
		return apellidosUsuario;
	}

	public void setApellidosUsuario(String apellidosUsuario) {
		this.apellidosUsuario = apellidosUsuario;
	}

	public PhoneNumber getTelefonoUsuario() {
		return telefonoUsuario;
	}

	public void setTelefonoUsuario(PhoneNumber telefonoUsuario) {
		this.telefonoUsuario = telefonoUsuario;
	}

	@ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	public Key<User> getKeyUser() {
		return keyUser;
	}

	public void setKeyUser(Key<User> keyUser) {
		this.keyUser = keyUser;
	}
	
	/* public String getWebsafeKey(){
		 return Key.create(keyUser, Empresa.class,idUsuario).getString();
	 }
	 */
	public String getWebsafekeyUsuario(){
		 return Key.create(keyUser, Usuario.class,idUsuario).getString();
				
	}
	public String getCorreoUser(){   
		User user = ofy().load().key(getKeyUser()).now();
		return user.getEmail().getEmail();	
	}
	
	
}
