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
import com.innovus.domi.domain.Cliente;
import com.innovus.domi.domain.Empresa;
import com.innovus.domi.domain.Producto;
import com.innovus.domi.domain.Usuario;
import com.innovus.domi.domain.Pedido;
import com.innovus.domi.form.ClienteForm;
import com.innovus.domi.form.EmpresaForm;
import com.innovus.domi.form.PedidoForm;
import com.innovus.domi.form.ProductoForm;
import com.innovus.domi.form.UsuarioForm;

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


	  
	  /**
	   * Creates a new Conference object and stores it to the datastore.
	   *
	   * @param user A user who invokes this method, null when the user is not signed in.
	   * @param conferenceForm A ConferenceForm object representing user's inputs.
	   * @return A newly created Conference Object.
	   * @throws UnauthorizedException when the user is not signed in.
	   */
	  @ApiMethod(name="crearPedido",path="pedido",httpMethod=HttpMethod.POST)
      public Pedido crearPedido(final PedidoForm pedidoForm){
		  
		  final Key<Pedido> pedidoKey=factory().allocateId(Pedido.class);
		  final long pedidoId=pedidoKey.getId();
		  Pedido pedido =ofy().transact(new Work<Pedido>(){
			  @Override
	          public Pedido run(){
				  Pedido pedido = new Pedido(pedidoId,pedidoForm);
				  ofy().save().entities(pedido).now();
				  return pedido;
			  }  
		  });
		  return pedido;
	  }
	  
	  @ApiMethod(name="crearUsuario",path="usuario",httpMethod=HttpMethod.POST)
	  public Usuario crearUsuario(final UsuarioForm usuarioForm){
		  
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
	  
	  @ApiMethod(name="crearCliente",path="cliente",httpMethod=HttpMethod.POST)
	  public Cliente createCliente(final ClienteForm clienteForm){
		  
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
	    
	  @ApiMethod(name = "crearEmpresa", path = "empresa", httpMethod = HttpMethod.POST)
	  public Empresa crearEmpresa( @Named("keyCliente") final long keyCliente, final EmpresaForm empresaForm)
	  {
		  Cliente cliente = ofy().load().key(Key.create(Cliente.class, keyCliente)).now(); // carga el cliente con el id del cliente
	      final long clienteId = cliente.getIdCliente(); // obtiene el idcliente
	      Key <Cliente> keyCli = Key.create(Cliente.class,keyCliente);// crea la clave cliente
	      final Key<Empresa> empresaKey = factory().allocateId(keyCli, Empresa.class);
	      final long empresaId = empresaKey.getId();//se saca el id de la empresa q se va a crear
	      
	      
	      
	      // Start a transaction. 
	      Empresa empresa = ofy().transact(new Work<Empresa>() {
	          @Override
	          public Empresa run() {
	              
	              Empresa empresa = new Empresa(clienteId, empresaId,  empresaForm);
	              
	              ofy().save().entities(empresa).now();
	          
	              return empresa;
	          }
	      });
	      return empresa;
	  }
	  
	 @ApiMethod(name = "creaCategoria", path = "cliente/{keyCliente}/empresa/{keyEmpresa}/categoria", httpMethod = HttpMethod.POST)
	  public Categoria creaCategoria(  @Named("keyCliente") final Long keyCliente, @Named("nombre")final String nombre,  @Named("keyEmpresa") final Long keyEmpresa)//me devuelva una categoria
	  {
		 
	      Key<Cliente> keyCli = Key.create(Cliente.class, keyCliente);
	      Cliente cliente = ofy().load().key(keyCli).now();
	      final long clienteId = cliente.getIdCliente();
	      
	      Key<Empresa> keyEmp = Key.create(keyCli,Empresa.class, keyEmpresa);
	      Empresa empresa = ofy().load().key(keyEmp).now();
	      final long empresaId = empresa.getidEmpresa();
	      
	      final Key<Categoria> categoriaKey = factory().allocateId(keyEmp,Categoria.class);
	      final long categoriaId = categoriaKey.getId();
	      
	     
	      
	      // Start a transaction.
	      Categoria categoria = ofy().transact(new Work<Categoria>() {
	          @Override
	          public Categoria run() {//inicia comit
	              // Fetch user's Profile.
	              //Profile profile = getProfileFromUser(user, userId);
	              Categoria categoria = new Categoria (categoriaId,clienteId,  nombre,empresaId);
	              // Save Conference and Profile.
	              ofy().save().entities(categoria).now();
	           
	              return categoria;
	          }
	      });
	      return categoria;
	  }
	 
	 @ApiMethod(name = "creaCate", path = "cliente/empresa/categoria", httpMethod = HttpMethod.POST)
	  public Categoria creaCate(  @Named("websafeEmpresaKey") final String websafeEmpresaKey,  @Named("nombre")final String nombre)//me devuelva una categoria
	       {
	     
	      
	      Key<Empresa> keyEmp = Key.create(websafeEmpresaKey);
	      final Empresa empresa = ofy().load().key(keyEmp).now();
	      final long empresaId = empresa.getidEmpresa();
	      final long clienteId = empresa.getIdCliente();
	      
	      
	      final Key<Categoria> categoriaKey = factory().allocateId(keyEmp,Categoria.class);
	      final long categoriaId = categoriaKey.getId();
	      
	     
	      
	      // Start a transaction.
	      Categoria categoria = ofy().transact(new Work<Categoria>() {
	          @Override
	          public Categoria run() {//inicia comit
	              // Fetch user's Profile.
	              //Profile profile = getProfileFromUser(user, userId);
	              Categoria categoria = new Categoria (categoriaId,clienteId,  nombre,empresaId);
	              // Save Conference and Profile.
	              ofy().save().entities(categoria ,empresa).now();
	           
	              return categoria;
	          }
	      });
	      return categoria;
	  }
	 
	 
	 
	 
	 @ApiMethod(
	            name = "consultaEmpresas",
	            path = "consultaEmpresas",
	            httpMethod = HttpMethod.GET
	    )
	    public List<Empresa> consultaEmpresas() {
	        Query query = ofy().load().type(Empresa.class).order("nombre");
	        return query.list();//me retorns en una lista
	    }
	 @ApiMethod(
	            name = "consultaProductos",
	            path = "consultaProductos",
	            httpMethod = HttpMethod.GET
	    )
	    public List<Producto> consultaProductos() {
	        Query query = ofy().load().type(Producto.class).order("nombreProducto");
	        return query.list();//me retorns en una lista
	    }
	 @ApiMethod(
	            name = "consultaCategorias",
	            path = "consultaCategorias",
	            httpMethod = HttpMethod.GET
	    )
	    public List<Categoria> consultaCategorias() {
	        Query query = ofy().load().type(Categoria.class).order("nombreCategoria");
	        return query.list();//me retorns en una lista
	    }
	 
	 
	 @ApiMethod(name = "consultaClienteID", path = "cliente/{keyCliente}", httpMethod = HttpMethod.POST)
	  public Cliente consutaCLienteID( @Named("keyCliente") final Long keyCliente)//me devuelva un producto
	  {
		 Key <Cliente> keyCli = Key.create(Cliente.class,keyCliente);
		 Cliente cliente = ofy().load().key(keyCli).now();
		 return cliente;
	  }
  
	 
	 @ApiMethod(
	            name = "getEmpresaXCliente",
	            path = "cliente/{keyCliente}/empresaxCliente",
	            httpMethod = HttpMethod.POST
	    )
	    public List<Empresa> getEmpresaXCliente(@Named("keyCliente") final long keyCliente)  {
		 Cliente parent =  ofy().load().key(Key.create(Cliente.class, keyCliente)).now();
		 return ofy().load().type(Empresa.class).ancestor(parent).list();
		 
	                
	    }
	 @ApiMethod(name = "consultaClientes", path = "consultaClientes", httpMethod = HttpMethod.GET)
	 public List<Cliente> consultaCliente() {
	        Query query = ofy().load().type(Cliente.class).order("nomCliente");
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
	    public List<Categoria> getCategoriasXEmpresa(@Named("webSafeEmpresaKey") final String webSafeEmpresaKey)  {
		
	        return ofy().load().type(Categoria.class)
	                .ancestor(Key.create(webSafeEmpresaKey))
	                .list();
	                
	    }
	   
	   @ApiMethod(
	            name = "getProductosXEmpresa",
	            path = "getProductosXEmpresa",
	            httpMethod = HttpMethod.POST
	    )
	    public List<Producto> getProductosXEmpresa(@Named("webSafeEmpresaKey") final String webSafeEmpresaKey)  {
		   
		   List<Categoria>  categorias = ofy().load().type(Categoria.class).ancestor(Key.create(webSafeEmpresaKey)).list();
		
		   List<Producto> Productos = new ArrayList<Producto>();
		   for(int i=0; i< categorias.size(); i++){
			   Categoria categoria= categorias.get(i);
			   
			   List<Producto> aux=ofy().load().type(Producto.class).ancestor(categoria).list();
			   for(int j=0; j< aux.size(); j++){
				   Producto producto = aux.get(j);
				   Productos.add(producto);
				   
			   }
			   
		   }

		   return Productos;
		  
	                
	    }
	   
	   @ApiMethod(
	            name = "getProductosXCtegoria",
	            path = "getProductosXCategoria",
	            httpMethod = HttpMethod.POST
	    )
	    public List<Producto> getProductosXCategoria(@Named("webSafeCategoriaKey") final String webSafeCategoriaKey)  {
		
		   
	        return ofy().load().type(Producto.class)
	                .ancestor(Key.create(webSafeCategoriaKey))
	                .list();
	                
	    }
	   
	   @ApiMethod(name = "crearProductos", path = "productos", httpMethod = HttpMethod.POST)
		  public Producto crearProductos( @Named("websafeKeyCategoria") final String websafeKeyCategoria,  final ProductoForm productoForm)//me devuelva un producto
		       {
		   Key<Categoria> keyCat = Key.create(websafeKeyCategoria);
		   final Key<Producto> productoKey = factory().allocateId(keyCat,Producto.class);
		   final long productoId = productoKey.getId();
		  
		   
		      // Start a transaction.
		      Producto producto = ofy().transact(new Work<Producto>() {
		          @Override
		          public Producto run() {//inicia comit
		              // Fetch user's Profile.
		              //Profile profile = getProfileFromUser(user, userId);
		              Producto producto = new Producto (productoId,websafeKeyCategoria, productoForm);
		              // Save Conference and Profile.
		              ofy().save().entities(producto).now();
		           
		              return producto;
		          }
		      });
		      return producto;
		  }

}
