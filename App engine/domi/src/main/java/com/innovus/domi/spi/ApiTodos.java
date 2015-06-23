package com.innovus.domi.spi;

import static com.innovus.domi.service.OfyService.ofy;

import java.util.ArrayList;
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
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;
import com.innovus.domi.Constants;
import com.innovus.domi.domain.Categoria;
import com.innovus.domi.domain.Cliente;
import com.innovus.domi.domain.Grupo;
import com.innovus.domi.domain.Login;
import com.innovus.domi.domain.Producto;
import com.innovus.domi.domain.Sucursal;
import com.innovus.domi.domain.User;
import com.innovus.domi.form.LoginForm;

@Api(name = "doomiTodos", version = "v1", scopes = { Constants.EMAIL_SCOPE }, clientIds = {
		Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID,
		Constants.API_EXPLORER_CLIENT_ID }, audiences = { Constants.ANDROID_AUDIENCE }, description = "Doomi Central API que es usada por todos "

)
public class ApiTodos {
	private static final Logger LOG = Logger.getLogger(ApiUsuarios.class
			.getName());

	/**
	 * A wrapper class that can embrace a generic result or some kind of
	 * exception.
	 *
	 * Use this wrapper class for the return type of objectify transaction.
	 * 
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
	 * @param <ResultType>
	 *            The type of the actual return object.
	 */
	private static class TxResult<ResultType> {

		private ResultType result;

		private Throwable exception;

		private TxResult(ResultType result) {
			this.result = result;
		}

		private TxResult(Throwable exception) {
			if (exception instanceof NotFoundException
					|| exception instanceof ForbiddenException
					|| exception instanceof ConflictException) {
				this.exception = exception;
			} else {
				throw new IllegalArgumentException("Exception not supported.");
			}
		}

		private ResultType getResult() throws NotFoundException,
				ForbiddenException, ConflictException {
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

	// consulta todas las categorias
	@ApiMethod(name = "consultaGrupos", path = "consultaGrupos", httpMethod = HttpMethod.GET)
	public List<Grupo> consultaGrupos() {
		Query query = ofy().load().type(Grupo.class).order("nombreGrupo");
		return query.list();// me retorns en una lista
	}

	// obtener la categoria segun su id

	// le mando el correo y la contraseña y me retorna un bolean si esta bn o
	// mal
	@ApiMethod(name = "loginEstado", path = "login", httpMethod = HttpMethod.GET)
	public Login loginEstado(@Named("email") final String email,
			@Named("password") final String password) {

		User user = ofy().load().type(User.class).filter("email",email)
				.first().now();
		// si no ahi usuarios con este email agregelo
		

		if (user == null) {
			Login login = new Login();
			LOG.info("aqui user null");
			

			return login;
		} else if (user.traerPassword().equals(password)) {
			Login login = new Login(user);
			LOG.info("aqui trae el usuario " + user.getIs_cliente());
			
			
			login.loegearlo();

			return login;

		} else {
			Login login = new Login();
			LOG.info("aqui contraseña mal");
			
			return login;
		}

	}

	@ApiMethod(name = "getCategoriasXEmpresa", path = "getCategoriasXEmpresa/{webSafeEmpresaKey}/", httpMethod = HttpMethod.GET)
	public List<Categoria> getCategoriasXEmpresa(
			@Named("webSafeEmpresaKey") final String webSafeEmpresaKey) {
		return ofy().load().type(Categoria.class)
				.ancestor(Key.create(webSafeEmpresaKey)).list();

	}

	@ApiMethod(name = "getProductosXEmpresa", path = "getProductosXEmpresa", httpMethod = HttpMethod.POST)
	public List<Producto> getProductosXEmpresa(
			@Named("webSafeEmpresaKey") final String webSafeEmpresaKey) {

		List<Categoria> categorias = ofy().load().type(Categoria.class)
				.ancestor(Key.create(webSafeEmpresaKey)).list();

		List<Producto> Productos = new ArrayList<Producto>();
		for (int i = 0; i < categorias.size(); i++) {
			Categoria categoria = categorias.get(i);

			List<Producto> aux = ofy().load().type(Producto.class)
					.ancestor(categoria).list();
			for (int j = 0; j < aux.size(); j++) {
				Producto producto = aux.get(j);
				Productos.add(producto);

			}

		}

		return Productos;

	}

	@ApiMethod(name = "getProductosXCategoria", path = "getProductosXCategoria", httpMethod = HttpMethod.POST)
	public List<Producto> getProductosXCategoria(
			@Named("webSafeCategoriaKey") final String webSafeCategoriaKey) {

		return ofy().load().type(Producto.class)
				.ancestor(Key.create(webSafeCategoriaKey)).list();

	}
	
	@ApiMethod(name = "getProductosXSucursal", path = "getProductosXSucursal", httpMethod = HttpMethod.POST)
	public List<Producto> getProductosXSucursal(
			@Named("websafeSucursalKey") final String websafeSucursalKey) {
		
		Key<Sucursal> sucursalKey = Key.create(websafeSucursalKey);
		Sucursal sucursal = ofy().load().key(sucursalKey).now();
		List<String> keysProductos = sucursal.getProductosKeysPertenece();
		List<Producto> productos = new ArrayList<>();
		for (String keyString : keysProductos) {
			
			Key<Producto> productoKey = Key.create(keyString);
			Producto producto = ofy().load().key(productoKey).now();
			productos.add(producto);
		
		}
		return productos;

	}
	

}
