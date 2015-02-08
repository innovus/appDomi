package com.innovus.domi.spi;




import static com.innovus.domi.service.OfyService.factory;
import static com.innovus.domi.service.OfyService.ofy;

import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.ForbiddenException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.api.server.spi.response.UnauthorizedException;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Work;
import com.innovus.domi.Constants;
import com.innovus.domi.domain.Categoria;
import com.innovus.domi.domain.Empresa;
import com.innovus.domi.domain.Producto;
import com.innovus.domi.form.EmpresaForm;
import com.innovus.domi.form.ProductoForm;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.cmd.Query;

@Api(
        name = "domi",
        version = "v1",
        scopes = { Constants.EMAIL_SCOPE },
        clientIds = { Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID,
                Constants.API_EXPLORER_CLIENT_ID},
        audiences = {Constants.ANDROID_AUDIENCE},
        description = "Domi Central API para crear y consultar empresas," +
                " y para crear y obtener productos"
)
public class ApiDomi {
	
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

	  /*
	  @ApiMethod(
	          name = "updateEmpresa",
	          path = "empresa/{websafeConferenceKey}",
	          httpMethod = HttpMethod.PUT
	  )
	  public Empresa updateEmpresa(final User user, final EmpresaForm empresaForm,
	                                     @Named("websafeEmpresaKey")
	                                     final String websafeEmpresaKey)
	          throws UnauthorizedException, NotFoundException, ForbiddenException, ConflictException {
	      // If not signed in, throw a 401 error.
		  
		  //final String userId = getUserId(user);
	      // Update the conference with the conferenceForm sent from the client.
	      // Need a transaction because we need to safely preserve the number of allocated seats.
	      TxResult<Empresa> result = ofy().transact(new Work<TxResult<Empresa>>() {
	          @Override
	          public TxResult<Empresa> run() {
	              // If there is no Conference with the id, throw a 404 error.
	              Key<Empresa> empresaKey = Key.create(Empresa);
	              Empresa conference = ofy().load().key(conferenceKey).now();
	              if (conference == null) {
	                  return new TxResult<>(
	                          new NotFoundException("No Conference found with the key: "
	                                  + websafeConferenceKey));
	              }
	              // If the user is not the owner, throw a 403 error.
	              Profile profile = ofy().load().key(Key.create(Profile.class, userId)).now();
	              if (profile == null ||
	                      !conference.getOrganizerUserId().equals(userId)) {
	                  return new TxResult<>(
	                          new ForbiddenException("Only the owner can update the conference."));
	              }
	              conference.updateWithConferenceForm(conferenceForm);
	              ofy().save().entity(conference).now();
	              return new TxResult<>(conference);
	          }
	      });
	      // NotFoundException or ForbiddenException is actually thrown here.
	      return result.getResult();
	  }
	  */
	  
	  /**
	   * Creates a new Conference object and stores it to the datastore.
	   *
	   * @param user A user who invokes this method, null when the user is not signed in.
	   * @param conferenceForm A ConferenceForm object representing user's inputs.
	   * @return A newly created Conference Object.
	   * @throws UnauthorizedException when the user is not signed in.
	   */
	  @ApiMethod(name = "createEmpresa", path = "empresa", httpMethod = HttpMethod.POST)
	  public Empresa createConference(final EmpresaForm empresaForm)
	       {
	     
	            
	      final Key<Empresa> empresaKey = factory().allocateId(Empresa.class);
	      final long empresaId = empresaKey.getId();
	      //final Queue queue = QueueFactory.getDefaultQueue();
	      
	      // Start a transaction.
	      Empresa empresa = ofy().transact(new Work<Empresa>() {
	          @Override
	          public Empresa run() {
	              // Fetch user's Profile.
	              //Profile profile = getProfileFromUser(user, userId);
	              Empresa empresa = new Empresa(empresaId,  empresaForm);
	              // Save Conference and Profile.
	              ofy().save().entities(empresa).now();
	           /*   queue.add(ofy().getTransaction(),
	                      TaskOptions.Builder.withUrl("/tasks/send_confirmation_email")
	                      .param("email", profile.getMainEmail())
	                      .param("conferenceInfo", conference.toString()));*/
	              return empresa;
	          }
	      });
	      return empresa;
	  }
	  
	 @ApiMethod(name = "creaCategoria", path = "categoria", httpMethod = HttpMethod.POST)
	  public Categoria creaCategoria(@Named("nombre")final String nombre,  @Named("keyEmpresa") final Long keyEmpresa)//me devuelva una categoria
	       {
	     
	      // Allocate Id first, in order to make the transaction idempotent.
	      
	      final Key<Categoria> categoriaKey = factory().allocateId(Categoria.class);
	      final long categoriaId = categoriaKey.getId();
	      //final Queue queue = QueueFactory.getDefaultQueue();
	      //Key<Empresa> EmpresaKey = Key.create(keyE;
	      Empresa empresa = ofy().load().key(Key.create(Empresa.class, keyEmpresa)).now();
	      final long empresaId = empresa.getidEmpresa();
	      
	      // Start a transaction.
	      Categoria categoria = ofy().transact(new Work<Categoria>() {
	          @Override
	          public Categoria run() {//inicia comit
	              // Fetch user's Profile.
	              //Profile profile = getProfileFromUser(user, userId);
	              Categoria categoria = new Categoria(categoriaId,  nombre,empresaId);
	              // Save Conference and Profile.
	              ofy().save().entities(categoria).now();
	           
	              return categoria;
	          }
	      });
	      return categoria;
	  }
	 
	 @ApiMethod(
	            name = "consultaEmpresa",
	            path = "consultaEmpresa",
	            httpMethod = HttpMethod.GET
	    )
	    public List<Empresa> consultaEmpresa() {
	        Query query = ofy().load().type(Empresa.class).order("nombre");
	        return query.list();//me retorns en una lista
	    }
	 
	 /**
	     * Retorna una  lista de Caterogorias segun el restaurante q se mande
	     * In order to receive the websafeConferenceKey via the JSON params, uses a POST method.
	     *
	     * @param usa la clave deun restaurante para obtener sus categorias
	     * @return una lista de categorias queel restaurante creo
	     * @throws UnauthorizedException when the user is not signed in.
	     */
	   @ApiMethod(
	            name = "getCategoriasXEmpresa",
	            path = "getCategoriasXEmpresa",
	            httpMethod = HttpMethod.POST
	    )
	    public List<Categoria> getCategoriasXEmpresa(@Named("empresaId") final long empresaId)  {
		/*   Query query = ofy().load().type(Categoria.class);
	        return query.list();//me retorns en una lista
	     */ 
	        return ofy().load().type(Categoria.class)
	                .ancestor(Key.create(Empresa.class, empresaId))
	                .order("NombreCategoria").list();
	                
	    }
	   
	   @ApiMethod(name = "crearProductos", path = "productos", httpMethod = HttpMethod.POST)
		  public Producto crearProductos( @Named("keyCategoria") final Long keyCategoria,@Named("keyEmpresa") final Long keyEmpresa,final  ProductoForm productoForm)//me devuelva un producto
		       {
		     
		   Empresa empresa = ofy().load().key(Key.create(Empresa.class,keyEmpresa)).now();
		   Key <Empresa> keyEmp = Key.create(Empresa.class,keyEmpresa);
		   Key<Categoria> keyCat =Key.create(keyEmp,Categoria.class,keyCategoria);
		   
		   
		   Categoria categoria = ofy().load().key(keyCat).now();
		 
		      // Allocate Id first, in order to make the transaction idempotent.
		      
		      final Key<Producto> productoKey = factory().allocateId(keyCat, Producto.class);
		      final long productoId = productoKey.getId();
		      final long categoriaId = categoria.getidCategoria();
		      
		      // Start a transaction.
		      Producto producto = ofy().transact(new Work<Producto>() {
		          @Override
		          public Producto run() {//inicia comit
		              // Fetch user's Profile.
		              //Profile profile = getProfileFromUser(user, userId);
		              Producto producto = new Producto(productoId, categoriaId,productoForm);
		              // Save Conference and Profile.
		              ofy().save().entity(producto).now();
		           
		              return producto;
		          }
		      });
		      return producto;
		  }
	   
	   @ApiMethod(name = "prueba", path = "prueba", httpMethod = HttpMethod.POST)
	   public Categoria Prueba( @Named("keyCategoria") final Long keyCategoria,  @Named("keyEmpresa")final Long keyEmpresa)//me devuelva un producto
       {
		   Empresa empresa = ofy().load().key(Key.create(Empresa.class,keyEmpresa)).now();
		   Key <Empresa> keyEmp = Key.create(Empresa.class,keyEmpresa);
		   Key<Categoria> keyCat =Key.create(keyEmp,Categoria.class,keyCategoria);
		   
		   
		   Categoria categoria = ofy().load().key(keyCat).now();
		    
		  
		 //  Categoria categoria = ofy().load().key(Key.create( Categoria.class, keyCategoria,keyEmpresa)).now();
		   
		   //final long empresaId = categoria.getidCategoria();
		      
		 //  Categoria th = ofy().load().type(Categoria.class).ancestor(Empresa.class).i .now();
		
		   
	/*
		   List<Categoria> result = new ArrayList<>(0);
		  // Query<Categoria> q = ofy().load().type(Car.class).filter("vin >", "123456789");
		   Query<Categoria> categoriaIterable = ofy().load().type(Empresa.class).filter("Key =", keyCategoria);
		   for (Categoria categoria : categoriaIterable) {
	            organizersKeyList.add(Key.create(Profile.class, conference.getOrganizerUserId()));
	            result.add(conference);
	        }
		  
     
		
		   Query query = ofy().load().type(Empresa.class).filter("Key =", keyCategoria);
	        return query.list();//me retorns en una lista
		   // Allocate Id first, in order to make the transaction idempotent.
      
		   //final Key<Producto> productoKey = factory().allocateId(Producto.class);
		   //final long productoId = productoKey.getId();
		   //final Queue queue = QueueFactory.getDefaultQueue();
		   //Key<Empresa> EmpresaKey = Key.create(keyE;
		   Categoria categoria = ofy().load().key(Key.create(Categoria.class, keyCategoria)).now();
		   //long categoriaId = categoria.getidCategoria();
*/
		  return categoria;
       }
 

}
