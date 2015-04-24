package com.innovus.domi.spi;



import static com.innovus.domi.service.OfyService.factory;
import static com.innovus.domi.service.OfyService.ofy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.ForbiddenException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.users.User;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Work;
import com.googlecode.objectify.cmd.Query;
import com.innovus.domi.Constants;
import com.innovus.domi.domain.Cliente;
import com.innovus.domi.domain.Empresa;
import com.innovus.domi.domain.Grupo;
import com.innovus.domi.form.ClienteForm;
import com.innovus.domi.form.EmpresaForm;

@Api(
        name = "doomiClientes",
        version = "v1",
        scopes = { Constants.EMAIL_SCOPE },
        clientIds = { Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID,
                Constants.API_EXPLORER_CLIENT_ID},
        audiences = {Constants.ANDROID_AUDIENCE},
        description = "DoomiClientes Donde estaran todos los procesos que puedan realizar solo los clientes"
)

public class ApiClientes {
	private static final Logger LOG = Logger.getLogger(ApiClientes.class.getName());

	
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


	
	///
	////
	////
	///
	//Clientes
	///
	////
	 @ApiMethod(name="crearCliente",path="crearCliente",httpMethod=HttpMethod.POST)
	  public Cliente crearCliente(final ClienteForm clienteForm){
		  
		  final Key<Cliente> clienteKey=factory().allocateId(Cliente.class);
		  final long clienteId=clienteKey.getId();
		  Cliente cliente =ofy().transact(new Work<Cliente>(){
			  @Override
	          public Cliente run(){
				  Cliente cliente = new Cliente(clienteId,clienteForm);
				  ofy().save().entities(cliente).now();
				  return cliente;
			  }  
		  });
		  return cliente;
	  }
	 
	 @ApiMethod(name = "consultaClienteID", path = "clientes/{idCliente}", httpMethod = HttpMethod.GET)
	  public Cliente consutaCLienteID( @Named("idCliente") final Long keyCliente)
	  {
		 Key <Cliente> keyCli = Key.create(Cliente.class,keyCliente);
		 Cliente cliente = ofy().load().key(keyCli).now();
		 return cliente;
	  }
 
	 @ApiMethod(name = "crearEmpresa", path = "empresa", httpMethod = HttpMethod.POST)
	  public Empresa crearEmpresa( @Named("websafeKeyCliente") final String websafeKeyCliente, final EmpresaForm empresaForm)
	  {
		  //crear clientes padres
		  Key<Cliente> keyCliente =Key.create(websafeKeyCliente);	
		  Cliente cliente = ofy().load().key(keyCliente).now(); // carga el cliente con el id del cliente
	      final long clienteId = cliente.getIdCliente(); // obtiene el idcliente
	      //crear grupos	      
	     
	      final Key<Empresa> empresaKey = factory().allocateId(keyCliente, Empresa.class);
	      final long empresaId = empresaKey.getId();//se saca el id de la empresa q se va a crear
	       
	      // Start a transaction. 
	      Empresa empresa = ofy().transact(new Work<Empresa>() {
	          @Override
	          public Empresa run() {
	              
	              Empresa empresa = new Empresa(empresaId, websafeKeyCliente, empresaForm);
	              
	              ofy().save().entities(empresa).now();
	          
	              return empresa;
	          }
	      });
	      return empresa;
	  }
	 
	 /**
	  * Just a wrapper for Boolean.
	  * We need this wrapped Boolean because endpoints functions must return
	  * an object instance, they can't return a Type class such as
	  * String or Integer or Boolean
	  */
	 public static class WrappedBoolean {

	     private final Boolean result;
	     private final String reason;

	     public WrappedBoolean(Boolean result) {
	         this.result = result;
	         this.reason = "";
	     }

	     public WrappedBoolean(Boolean result, String reason) {
	         this.result = result;
	         this.reason = reason;
	     }

	     public Boolean getResult() {
	         return result;
	     }

	     public String getReason() {
	         return reason;
	     }
	 }

	 

/**
 * Agregar Grupos a los que pertenece la empresa.
 *
 * @param user An user who invokes this method, null when the user is not signed in.
 * @param websafeConferenceKey The String representation of the Conference Key.
 * @return Boolean true when success, otherwise false
 * @throws UnauthorizedException when the user is not signed in.
 * @throws NotFoundException when there is no Conference with the given conferenceId.
 */
	 
	 //aqui deben agregarse una lista de llaves de grupos, pero para prueba agregaremos uno x uno
	 public WrappedBoolean addGrupoForEmpresa(@Named("websafeEmpresaKey") final String websafeEmpresaKey,@Named("websafeGrupoKey") final String websafeGrupoKey)
					 throws UnauthorizedException, NotFoundException,
					 ForbiddenException, ConflictException {
    /*
		 // If not signed in, throw a 401 error.
    if (user == null) {
        throw new UnauthorizedException("Authorization required");
    }

    // Get the userId
    *///debemos validar cuando el usuario este registrado

		 WrappedBoolean result = ofy().transact(new Work<WrappedBoolean>() {
			 @Override
			 public WrappedBoolean run() {
				 try {

					 // Get the Grupokey
					 Key<Grupo> grupoKey = Key.create(websafeGrupoKey);

					 // Get the Grupo entity from the datastore
					 Grupo grupo = ofy().load().key(grupoKey).now();

					 // 404 when there is no Grupo with the given grupoId.
					 if (grupo == null) {
						 return new WrappedBoolean (false,
								 "No Grupo found with key: "
										 + websafeGrupoKey);
					 }

					 // Get the  Empresa entity
					 Key<Empresa> keyEmpresa = Key.create(websafeEmpresaKey);
					 Empresa empresa = ofy().load().key(keyEmpresa).now();

					 // Has the Grupo already registered in this empresa?
					 
					 List<String> keyStringsToAttend = empresa.getGruposKeysPertenece();
					  //  List<Key<Grupo>> keysToAttend = new ArrayList<>();
					    /*for (String keyString : keyStringsToAttend) {
					    	LOG
					        keysToAttend.add(Key.<Conference>create(keyString));
					    }
					    return ofy().load().keys(keysToAttend).values();
					    */
					 if (empresa.getGruposKeysPertenece().contains(
							 websafeGrupoKey)) {
						 return new WrappedBoolean (false, "Already registered");
            
					 } else {
                
						 empresa.addgruposKeysPertenece(websafeGrupoKey);
                

						 // Save the Conference and Profile entities
						 ofy().save().entities(empresa, grupo).now();
						 // We are booked!
						 return new WrappedBoolean(true);
					 }

				 }
				 catch (Exception e) {
					 return new WrappedBoolean(false, "Unknown exception");

				 }
            
			 }
		 });
		 // if result is false
		 if (!result.getResult()) {
			 if (result.getReason() == "Already registered") {
				 throw new ConflictException("You have already registered");
			 }
			 else if (result.getReason() == "No seats available") {
				 throw new ConflictException("There are no seats available");
			 }
			 else {
				 throw new ForbiddenException("Unknown exception");
			 }
		 }
		 return result;
	 }
	 
	 @ApiMethod(
		        name = "getGruposDeEmpresa",
		        path = "getGruposDeEmpresa",
		        httpMethod = HttpMethod.GET
		)
		public Collection<Grupo> getConferencesToAttend(@Named("websafeEmpresaKey") final String websafeEmpresaKey)
		        throws NotFoundException {
			
		    // If not signed in, throw a 401 error.
		 	Key<Empresa> keyEmpresa = Key.create(websafeEmpresaKey);
		 	Empresa empresa = ofy().load().key(keyEmpresa).now();
		    
		    List<String> keyStringsGrupos = empresa.getGruposKeysPertenece();
		    List<Key<Grupo>> keysGrupos = new ArrayList<>();
		    for (String keyString : keyStringsGrupos) {
		        keysGrupos.add(Key.<Grupo>create(keyString));
		    }
		    return ofy().load().keys(keysGrupos).values();
		}
		/**
		 * Unregister from the specified Conference.
		 *
		 * @param user An user who invokes this method, null when the user is not signed in.
		 * @param websafeEmpresaKey The String representation of the Conference Key to unregister
		 *                             from.
		 * @return Boolean true when success, otherwise false.
		 * @throws UnauthorizedException when the user is not signed in.
		 * @throws NotFoundException when there is no Conference with the given conferenceId.
		 */
	 
		@ApiMethod(
		        name = "deleteGrupoFromEmpresa",
		        path = "empresas/{websafeEmpresaKey}/{websafeGrupoKey}/delete",
		        httpMethod = HttpMethod.DELETE
		)
		public WrappedBoolean deleteGrupoFromEmpresa( @Named("websafeEmpresaKey")
		                                        final String websafeEmpresaKey,
		                                        @Named("websafeGrupoKey") final String websafeGrupoKey)
		        throws  NotFoundException, ForbiddenException, ConflictException {
		    // If not signed in, throw a 401 error.
		    //validar que este registrado

		    WrappedBoolean result = ofy().transact(new Work<WrappedBoolean>() {
		        @Override
		        public WrappedBoolean run() {
		            Key<Grupo> grupoKey = Key.create(websafeGrupoKey);
		            Grupo grupo = ofy().load().key(grupoKey).now();
		            //validar si esta logeado
		            
		            // 404 when there is no Grupo with the given grupoId.
		            if (grupo == null) {
		                return new  WrappedBoolean(false,
		                        "No Grupo found with key: " + websafeGrupoKey);
		            }

		            // Un-registering from the Grupo.
		            Key<Empresa> keyEmpresa = Key.create(websafeEmpresaKey);
				 	Empresa empresa = ofy().load().key(keyEmpresa).now();
				 // 404 when there is no Grupo with the given grupoId.
		            if (empresa == null) {
		                return new  WrappedBoolean(false,
		                        "No Empresa found with key: " + websafeEmpresaKey);
		            }
				    
		            if (empresa.getGruposKeysPertenece().contains(websafeGrupoKey)) {
		                empresa.deleteGrupoOfEmpresa(websafeGrupoKey);
		                
		                ofy().save().entities(empresa, grupo).now();
		                return new WrappedBoolean(true);
		            } else {
		                return new WrappedBoolean(false, "It Grupo have not this empresa");
		            }
		        }
		    });
		    // if result is false
		    if (!result.getResult()) {
		        if (result.getReason().contains("No Grupo found with key")) {
		            throw new NotFoundException (result.getReason());
		        }
		        else {
		            throw new ForbiddenException(result.getReason());
		        }
		    }
		    // NotFoundException is actually thrown here.
		    return new WrappedBoolean(result.getResult());
		}
		


}	
