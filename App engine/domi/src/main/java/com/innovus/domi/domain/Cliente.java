package com.innovus.domi.domain;


import static com.innovus.domi.service.OfyService.factory;
import static com.innovus.domi.service.OfyService.ofy;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.google.appengine.api.datastore.PhoneNumber;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Work;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.innovus.domi.form.ClienteForm;

@Entity
public class Cliente  {

	@Id
	private long idCliente;
	
	@Index
	private String cedulaCliente;
	@Index
	private String nombresCliente;
	private String apellidosCliente;	
	private PhoneNumber telefonoCliente;
	//private String passwordCliente;
	 @Parent
	 @ApiResourceProperty(ignored=AnnotationBoolean.TRUE)
	 private Key<User> keyUser;
	 @ApiResourceProperty(ignored=AnnotationBoolean.TRUE)
	 private long idUser;	
	private String pais;
	private String ciudad;
	
	
	private Cliente(){

	}
	
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
	public String getNombresCliente(){
		return nombresCliente;
	}
	public String getApellidosCliente(){
		return apellidosCliente;
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
	
	 public String getWebsafeKey(){
		 return Key.create(keyUser, Cliente.class,idCliente).getString();
	 }
	 
	
	public void ActualizaconClienteForm(final ClienteForm clienteForm){
		this.cedulaCliente=clienteForm.getCedulaCliente();
		this.nombresCliente=clienteForm.getNombresCliente();
		this.apellidosCliente=clienteForm.getApellidosCliente();

		this.telefonoCliente=clienteForm.getTelefonoCliente();
		this.pais=clienteForm.getPais();
		this.ciudad=clienteForm.getCiudad();
		
		final Key<User> userKey=factory().allocateId(User.class);
		  final long userId=userKey.getId();
		  User user =ofy().transact(new Work<User>(){
			  @Override
	          public User run(){
				  User user = new User(userId,clienteForm.getCorreoCliente(),
						  clienteForm.getPasswordCliente(),false,true);
				  ofy().save().entities(user).now();
				 return user;
			  }  
		  });
		  this.keyUser = Key.create(user.getWebsafeKey());
		  User user2 = ofy().load().key(keyUser).now();
		  this.idUser = user2.getIdUser();
		  
	}
	
	 @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	 public long getIdUser(){
		 return idUser;
	 }
	
}
