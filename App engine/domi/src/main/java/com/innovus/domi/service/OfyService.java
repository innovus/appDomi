package com.innovus.domi.service;


import com.innovus.domi.domain.*;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

/**
 * Custom Objectify Service that this application should use.
 */
public class OfyService {
    /**
     * This static block ensure the entity registration.
     */
    static {
        //factory().register(AppEngineUser.class);
        factory().register(Categoria.class);
        factory().register(Empresa.class);
        factory().register(Producto.class);
        factory().register(Cliente.class);
        factory().register(Usuario.class);
        factory().register(Pedido.class);
        factory().register(Grupo.class);
        factory().register(User.class);
        factory().register(Sucursal.class);
        factory().register(Carrito.class);
        factory().register(ReputacionUsuario.class);
               
    }

    /**
     * Use this static method for getting the Objectify service object in order to make sure the
     * above static block is executed before using Objectify.
     * @return Objectify service object.
     */
    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    /**
     * Use this static method for getting the Objectify service factory.
     * @return ObjectifyFactory.
     */
    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}
