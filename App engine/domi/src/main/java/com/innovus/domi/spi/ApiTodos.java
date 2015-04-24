package com.innovus.domi.spi;

import static com.innovus.domi.service.OfyService.ofy;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.ForbiddenException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Email;
import com.googlecode.objectify.cmd.Query;
import com.innovus.domi.Constants;
import com.innovus.domi.domain.Categoria;
import com.innovus.domi.domain.Cliente;
import com.innovus.domi.domain.Grupo;
import com.innovus.domi.domain.Login;
import com.innovus.domi.domain.User;
import com.innovus.domi.form.LoginForm;

@Api(
        name = "doomiTodos",
        version = "v1",
        scopes = { Constants.EMAIL_SCOPE },
        clientIds = { Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID,
                Constants.API_EXPLORER_CLIENT_ID},
        audiences = {Constants.ANDROID_AUDIENCE},
        description = "Doomi Central API que es usada por todos "
            
)

public class ApiTodos {
	private static final Logger LOG = Logger.getLogger(ApiUsuarios.class.getName());

	
	/**
	   * A wrapper class that can embrace a generic result or some kind of exception.
	   *
	   * Use this wrapper class for the return type of objectify transaction.
	   * <pre>
	   * {@code
	   * // The transaction that returns Conference object.
	   * TxResult<Conference> result = ofy().transact(new Work<TxResult<Conference>>() {
	   *     public TxResult<Conference> run() {
	   *         // Code here.
	   *         // To throw 404
	   *         return new TxResult<>(new NotFoundException("No such conference"));
	   *         // To return a conference.
	   *         Conference conference = somehow.getConference();
	   *         return new TxResult<>(conference);
	   *     }
	   * }
	   * // Actually the NotFoundException will be thrown here.
	   * return result.getResult();
	   * </pre>
	   *
	   * @param <ResultType> The type of the actual return object.
	   */
	  private static class TxResult<ResultType> {

	      private ResultType result;

	      private Throwable exception;

	      private TxResult(ResultType result) {
	          this.result = result;
	      }

	      private TxResult(Throwable exception) {
	          if (exception instanceof NotFoundException ||
	                  exception instanceof ForbiddenException ||
	                  exception instanceof ConflictException) {
	              this.exception = exception;
	          } else {
	              throw new IllegalArgumentException("Exception not supported.");
	          }
	      }

	      private ResultType getResult() throws NotFoundException, ForbiddenException, ConflictException {
	          if (exception instanceof NotFoundException) {
	              throw (NotFoundException) exception;
	          }
	          if (exception instanceof ForbiddenException) {
	              throw (ForbiddenException) exception;
	          }
	          if (exception instanceof ConflictException) {
	              throw (ConflictException) exception;
	          }
	          return result;
	      }
	  }
	  
	  //consulta todas las categorias
	  @ApiMethod(
	            name = "consultaGrupos",
	            path = "consultaGrupos",
	            httpMethod = HttpMethod.GET
	    )
	    public List<Grupo> consultaGrupos() {
	        Query query = ofy().load().type(Grupo.class).order("nombreGrupo");
	        return query.list();//me retorns en una lista
	    }
	  
	  //obtener la categoria segun su id
	  
	  
	  //le mando el correo y la contraseña y me retorna un bolean si esta bn o mal
	  @ApiMethod(
	            name = "loginEstado",
	            path = "login",
	            httpMethod = HttpMethod.GET
	    )
	    public Login loginEstado(@Named("email") final  String email, @Named("password") final  String password)  {
		  
		  User user = ofy().load().type(User.class).filter("email",/* loginForm.gEmailString()*/ email).first().now();
			 //si no ahi usuarios con este email agregelo
		  Login login = new Login();
		  
		  if (user == null) {
			  
				 
			  return login;
		  }
		  else if (user.getPassword().equals(password)){
			  login.loegearlo();
			  
			  return login;
			  
		  }
		  else{
			  
				
			  return login;  
		  }
		   
	        
	    }
	  
}
