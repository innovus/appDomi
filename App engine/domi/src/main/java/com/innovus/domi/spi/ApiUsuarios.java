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
import com.google.appengine.repackaged.com.google.protobuf.Internal.ListAdapter;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Work;
import com.googlecode.objectify.cmd.Query;
import com.innovus.domi.Constants;
import com.innovus.domi.domain.Carrito;
import com.innovus.domi.domain.Cliente;
import com.innovus.domi.domain.Empresa;
import com.innovus.domi.domain.Pedido;
import com.innovus.domi.domain.Producto;
import com.innovus.domi.domain.ReputacionSucursal;
import com.innovus.domi.domain.ReputacionUsuario;
import com.innovus.domi.domain.Sucursal;
import com.innovus.domi.domain.Usuario;
import com.innovus.domi.domain.User;
import com.innovus.domi.form.CarritoConPedidosForm;
import com.innovus.domi.form.CarritoForm;
import com.innovus.domi.form.ClienteForm;
import com.innovus.domi.form.PedidoForm;
import com.innovus.domi.form.ProductoForm;
import com.innovus.domi.form.ReputacionSucursalForm;
import com.innovus.domi.form.ReputacionUsuarioForm;
import com.innovus.domi.form.UsuarioForm;
import com.innovus.domi.form.ListaPedidosForms;
import com.innovus.domi.spi.ApiClientes.WrappedBoolean;

@Api(name = "doomiUsuarios", version = "v1", scopes = { Constants.EMAIL_SCOPE }, clientIds = {
		Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID,
		Constants.API_EXPLORER_CLIENT_ID }, audiences = { Constants.ANDROID_AUDIENCE }, description = "DoomiUsuarios Donde estaran todos los procesos que puedan realizar solo los usuarios")
public class ApiUsuarios {
	private static final Logger LOG = Logger.getLogger(ApiUsuarios.class
			.getName());

	@ApiMethod(name = "crearUsuario", path = "crearUsuario", httpMethod = HttpMethod.POST)
	public Usuario crearUsuario(final UsuarioForm usuarioForm)
			throws NotFoundException {

		LOG.info("asi trae el correo " + usuarioForm.obtenerCorreoUsuarioString());
		User user = ofy().load().type(User.class)
				.filter("email", usuarioForm.obtenerCorreoUsuarioString()).first()
				.now();
		// si no ahi usuarios con este email agregelo
		if (user != null) {

			throw new NotFoundException("Este email "
					+ usuarioForm.obtenerCorreoUsuarioString() + " ya existe!!!");
		}

		final Key<Usuario> usuarioKey = factory().allocateId(Usuario.class);
		final long usuarioId = usuarioKey.getId();

		Usuario usuario = ofy().transact(new Work<Usuario>() {
			@Override
			public Usuario run() {

				Usuario usuario = new Usuario(usuarioId, usuarioForm);
				ofy().save().entities(usuario).now();
				return usuario;

			}
		});
		return usuario;
	}

	// //
	@ApiMethod(name = "agregarPedido", path = "agregarPedido", httpMethod = HttpMethod.POST)
	public Pedido agregarPedido(final PedidoForm pedidoForm) {

		final Key<Pedido> pedidoKey = factory().allocateId(Pedido.class);
		final long pedidoId = pedidoKey.getId();
		Pedido pedido = ofy().transact(new Work<Pedido>() {
			@Override
			public Pedido run() {
				Pedido pedido = new Pedido(pedidoId, pedidoForm);
				ofy().save().entities(pedido).now();
				return pedido;
			}
		});
		return pedido;
	}

	@ApiMethod(name = "agregarCarrito", path = "agregarCarrito", httpMethod = HttpMethod.POST)
	public Carrito agregarCarrito(
			@Named("websafeKeySucursal") final String websafeKeySucursal,
			final CarritoForm carritoForm) {

		final Key<Sucursal> sucursalKey = Key.create(websafeKeySucursal);
		final Sucursal sucursal = ofy().load().key(sucursalKey).now();

		final Key<Carrito> carritoKey = factory().allocateId(sucursalKey,
				Carrito.class);
		final long carritoId = carritoKey.getId();// se saca el id de la empresa

		Carrito carrito = ofy().transact(new Work<Carrito>() {
			@Override
			public Carrito run() {
				Carrito carrito = new Carrito(carritoId, carritoForm,
						websafeKeySucursal);
				ofy().save().entities(sucursal, carrito).now();
				return carrito;
			}
		});
		return carrito;
	}

	public WrappedBoolean addPedidoForCarrito(
			@Named("websafeCarritoKey") final String websafeCarritoKey,
			@Named("websafePedidoKey") final String websafePedidoKey)
			throws NotFoundException, ForbiddenException, ConflictException {
		/*
		 * // If not signed in, throw a 401 error. if (user == null) { throw new
		 * UnauthorizedException("Authorization required"); }
		 * 
		 * // Get the userId
		 */// debemos validar cuando el usuario este registrado

		WrappedBoolean result = ofy().transact(new Work<WrappedBoolean>() {
			@Override
			public WrappedBoolean run() {

				try {

					Key<Pedido> pedidoKey = Key.create(websafePedidoKey);

					// Get the Grupo entity from the datastore
					Pedido pedido = ofy().load().key(pedidoKey).now();

					// 404 when there is no Grupo with the given grupoId.
					if (pedido == null) {

						return new WrappedBoolean(false,
								"No Pedido found with key: " + websafePedidoKey);
					}
					Key<Carrito> keyCarrito = Key.create(websafeCarritoKey);
					Carrito carrito = ofy().load().key(keyCarrito).now();

					// Has the Grupo already registered in this empresa?

					List<String> keyStringsPedidos = carrito
							.getListaPedidosKey();
					// List<Key<Grupo>> keysToAttend = new ArrayList<>();
					/*
					 * for (String keyString : keyStringsToAttend) { LOG
					 * keysToAttend.add(Key.<Conference>create(keyString)); }
					 * return ofy().load().keys(keysToAttend).values();
					 */
					if (carrito.getListaPedidosKey().contains(websafePedidoKey)) {

						return new WrappedBoolean(false,
								"No Pedido ya registrado key:  "
										+ websafePedidoKey);

					} else {

						carrito.addPedidoKeys(websafePedidoKey);

						// Save the Conference and Profile entities
						ofy().save().entities(pedido, carrito).now();
						// We are booked!
						return new WrappedBoolean(true);

					}

				} catch (Exception e) {
					return new WrappedBoolean(false, "Unknown exception");

				}
			}
		});

		// if result is false
		if (!result.getResult()) {
			if (result.getReason() == "Already registered") {
				throw new ConflictException("You have already registered");
			} else if (result.getReason() == "No seats available") {
				throw new ConflictException("There are no seats available");
			} else {
				throw new ForbiddenException("Unknown exception");
			}
		}
		return result;
	}
	
	@ApiMethod(name = "agregarPedidos", path = "agregarPedidos", httpMethod = HttpMethod.POST)
	public List<Pedido> agregarPedidos( final ListaPedidosForms pedidosForms) {

		
		List<Pedido> pedidos = ofy().transact(new Work<List<Pedido>>() {
			@Override
			public List<Pedido> run() {
				List<Pedido> listPedidos = new ArrayList<>(); 
				for (PedidoForm pedidoForm : pedidosForms.getListaPedidos()) {
					final Key<Pedido> pedidoKey = factory().allocateId(Pedido.class);
					final long pedidoId = pedidoKey.getId();
					Pedido pedido = new Pedido(pedidoId, pedidoForm);
					ofy().save().entities(pedido).now();
					listPedidos.add(pedido);
			    	
			    }
				
				return listPedidos;
			}
		});
		return pedidos;
	}
	
	
	@ApiMethod(name = "agregarCarritoConPedidos", path = "agregarCarritoYPedidos", httpMethod = HttpMethod.POST)
	public Carrito agregarCarritoConPedidos(@Named("websafeKeySucursal") final String websafeKeySucursal, final CarritoConPedidosForm carritoConPedidosForm) {
		
		final Key<Sucursal> sucursalKey = Key.create(websafeKeySucursal);
		final Sucursal sucursal = ofy().load().key(sucursalKey).now();

		final Key<Carrito> carritoKey = factory().allocateId(sucursalKey,
				Carrito.class);
		final long carritoId = carritoKey.getId();// se saca el id de la empresa

		Carrito carrito = ofy().transact(new Work<Carrito>() {
			@Override
			public Carrito run() {
				
				List<Pedido> listPedidos = new ArrayList<>(); 
				for (PedidoForm pedidoForm : carritoConPedidosForm.getListaPedidosForms().getListaPedidos()) {
					final Key<Pedido> pedidoKey = factory().allocateId(Pedido.class);
					final long pedidoId = pedidoKey.getId();
					Pedido pedido = new Pedido(pedidoId, pedidoForm);
					ofy().save().entities(pedido).now();
					listPedidos.add(pedido);
			    	
			    }
				Carrito carrito = new Carrito(carritoId, carritoConPedidosForm.getCarritoForm(),
						websafeKeySucursal);
				carrito.addPedidosKeys(listPedidos);
				
	
			    	    
				ofy().save().entities(sucursal, carrito).now();
				return carrito;
			}
		});
		return carrito;
		
	}
	
	@ApiMethod(name = "agregarReputacionCliente", path = "agregarReputacionCliente", httpMethod = HttpMethod.POST)
	public ReputacionSucursal agregarReputacion(
			final ReputacionSucursalForm reputacionSucursalForm)// me devuelva un producto
	{
		Key<Sucursal> keySucursal = Key.create(reputacionSucursalForm.getWebsafeKeySucursal());
		final Key<ReputacionSucursal> reputacionSucursalKey = factory().allocateId(keySucursal,
				ReputacionSucursal.class);
		final long reputacionSucursalId = reputacionSucursalKey.getId();

		ReputacionSucursal reputacionSucursal = ofy().transact(new Work<ReputacionSucursal>() {
			@Override
			public ReputacionSucursal run() {// inicia comit
				// Fetch user's Profile.
				// Profile profile = getProfileFromUser(user, userId);
				ReputacionSucursal ReputacionSucursal = new ReputacionSucursal(reputacionSucursalId, reputacionSucursalForm);
				// Save Conference and Profile.
				ofy().save().entities(ReputacionSucursal).now();

				return ReputacionSucursal;
			}
		});
		return reputacionSucursal;
	}
	@ApiMethod(name = "getReputacionesCorreoUsuario", path = "reputacionCorreoUsuarios/{correoUser}/", httpMethod = HttpMethod.GET)
	public List<ReputacionUsuario> getReputacionesCorreoUsuarios(
			@Named("correoUser") final String correoUser) {
		//Aguilizar ahi muchas consultas
		User user = ofy().load().type(User.class).filter("email",correoUser).first().now();
		List<Usuario> usuarios = ofy().load().type(Usuario.class).ancestor(user).list();
		Usuario usuario = usuarios.get(0);
		
		return ofy().load().type(ReputacionUsuario.class)
				.ancestor(usuario).list();
	}
	
	//trae las sucursales pero que tengan productos agregados
	@ApiMethod(name = "consultaSucursales", path = "consultaSucursales", httpMethod = HttpMethod.GET)
	public List<Sucursal> consultaSucursales() {
		//
		List<Sucursal> sucursalesAux = ofy().load().type(Sucursal.class).list();
		List<Sucursal> sucursales = new ArrayList<>();
		for (Sucursal sucursal : sucursalesAux) {
			if(sucursal.CantidadProductos() > 0)
				sucursales.add(sucursal);
		}
		return sucursales;
			
			
	}
	

}
