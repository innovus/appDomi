package com.innovus.domi.spi;

import static com.innovus.domi.service.OfyService.factory;
import static com.innovus.domi.service.OfyService.ofy;

import java.util.logging.Logger;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.NotFoundException;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Work;
import com.innovus.domi.Constants;
import com.innovus.domi.domain.Cliente;
import com.innovus.domi.domain.Usuario;
import com.innovus.domi.domain.User;
import com.innovus.domi.form.ClienteForm;
import com.innovus.domi.form.UsuarioForm;


@Api(
        name = "doomiUsuarios",
        version = "v1",
        scopes = { Constants.EMAIL_SCOPE },
        clientIds = { Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID,
                Constants.API_EXPLORER_CLIENT_ID},
        audiences = {Constants.ANDROID_AUDIENCE},
        description = "DoomiUsuarios Donde estaran todos los procesos que puedan realizar solo los usuarios"
)
public class ApiUsuarios {
	private static final Logger LOG = Logger.getLogger(ApiUsuarios.class.getName());

	
	 @ApiMethod(name="crearUsuario",path="crearUsuario",httpMethod=HttpMethod.POST)
	  public Usuario crearUsuario(final UsuarioForm usuarioForm) throws NotFoundException {
		 
		  
		  
		 LOG.info("asi trae el correo "+  usuarioForm.getCorreoUsuarioString());
		  User user = ofy().load().type(User.class).filter("email", usuarioForm.getCorreoUsuarioString()).first().now();
			 //si no ahi usuarios con este email agregelo
		  if (user != null) {
		       
			  throw new NotFoundException("Este email "+ usuarioForm.getCorreoUsuarioString()+" ya existe!!!" );
			}
		  
		  final Key<Usuario> usuarioKey=factory().allocateId(Usuario.class);
		  final long usuarioId=usuarioKey.getId();
		  
		  Usuario usuario =ofy().transact(new Work<Usuario>(){
			  @Override
	          public Usuario run(){
				     
				  Usuario usuario = new Usuario(usuarioId,usuarioForm);
				  ofy().save().entities(usuario).now();
				  return usuario;	  

			  }  
		  });
		  return usuario;
	  }

}
