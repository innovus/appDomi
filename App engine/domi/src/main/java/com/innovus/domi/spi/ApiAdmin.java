package com.innovus.domi.spi;

import static com.innovus.domi.service.OfyService.factory;
import static com.innovus.domi.service.OfyService.ofy;

import java.util.List;

import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.ForbiddenException;
import com.google.api.server.spi.response.NotFoundException;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Work;
import com.googlecode.objectify.cmd.Query;
import com.innovus.domi.Constants;
import com.innovus.domi.domain.Categoria;
import com.innovus.domi.domain.Cliente;
import com.innovus.domi.domain.Grupo;
import com.innovus.domi.domain.Producto;
import com.innovus.domi.form.ClienteForm;

@Api(
        name = "doomiAdmin",
        version = "v1",
        scopes = { Constants.EMAIL_SCOPE },
        clientIds = { Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID,
                Constants.API_EXPLORER_CLIENT_ID},
        audiences = {Constants.ANDROID_AUDIENCE},
        description = "Doomi Central API que solo podra usar el administrador"
            
)
public class ApiAdmin {
	
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
	  
	  //Api  para crear un Grupo Padre
	  @ApiMethod(name="crearGrupoPadre",path="gruposPadres",httpMethod=HttpMethod.POST)
	  public Grupo crearGrupoPadre(@Named("nombreGrupo")final String nombreGrupo){
		//creamos datos para crear el grupo padre Key e id
		  final Key<Grupo> grupoKey=factory().allocateId(Grupo.class);
		  final long grupoId=grupoKey.getId();
		  //inicia una transaccion
		  Grupo grupo =ofy().transact(new Work<Grupo>(){
			  @Override
	          public Grupo run(){
				  //agregamos el grupo a la base de datos
				  Grupo grupo = new Grupo(grupoId,nombreGrupo);
				  ofy().save().entities(grupo).now();
				  return grupo;
			  }  
		  });
		  return grupo;
	  }
	  
	//Api para crear un Grupo Hijo
	  @ApiMethod(name="crearGrupoHijo",path="gruposHijos",httpMethod=HttpMethod.POST)
	  public Grupo crearGrupoHijo( @Named("websafekeyGrupoPadre") final String websafekeyGrupoPadre, @Named("nombreGrupo")final String nombreGrupo){
		  
		  Key<Grupo> keyGP = Key.create(websafekeyGrupoPadre);
		  final Key<Grupo> KeyGH = factory().allocateId(keyGP,Grupo.class);
		  final long grupoId = KeyGH.getId();
		  
		  Grupo grupo =ofy().transact(new Work<Grupo>(){
			  @Override
	          public Grupo run(){
				  Grupo grupo = new Grupo(grupoId,nombreGrupo,websafekeyGrupoPadre);
				  ofy().save().entities(grupo).now();
				  return grupo;
			  }  
		  });
		  return grupo;
	  }
	  
	  //consulta todos los clientes
	  @ApiMethod(name = "consultaClientes", path = "clientes", httpMethod = HttpMethod.GET)
		 public List<Cliente> consultaCliente() {
		        Query query = ofy().load().type(Cliente.class).order("nomCliente");
		        return query.list();//me retorns en una lista
		    }
	  
	  

}
