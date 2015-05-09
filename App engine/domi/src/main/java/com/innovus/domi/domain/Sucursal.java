package com.innovus.domi.domain;

import static com.innovus.domi.service.OfyService.ofy;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.google.appengine.api.datastore.GeoPt;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import com.innovus.domi.form.EmpresaForm;
import com.innovus.domi.form.ProductoForm;
import com.innovus.domi.form.SucursalForm;
import com.innovus.domi.spi.ApiUsuarios;


@Entity
public class Sucursal {
	private static final Logger LOG = Logger.getLogger(Sucursal.class.getName());

	@Id
	 private long idSucursal;

	 //Falta Poligono
	 @Index
	 private String nombreSucursal;
	 @Index
	 private int tiempoMinimo;
	 private float costoDomicilio;
	 private float pedidoMinimo;
	 
	 private GeoPt Ubicacion;
	 private Boolean is_active;
	 private Boolean is_disponible;
	 private List<String> productosKeysPertenece = new ArrayList<>(0);
	 
	 @Parent
	 @ApiResourceProperty(ignored=AnnotationBoolean.TRUE)
	 private Key<Empresa> empresaKey;
	 
	 @ApiResourceProperty(ignored=AnnotationBoolean.TRUE)
	 private long idEmpresa;
	 
	 public Sucursal() {
		 
		// TODO Auto-generated constructor stub
	}

	public Sucursal(final long idSucursal,final SucursalForm sucursalForm, final String websafekeyEmpresa) {
		
		this.idSucursal = idSucursal;
		this.is_active = true;
		this.is_disponible = true;
		this.empresaKey=Key.create(websafekeyEmpresa);
		Empresa empresa = ofy().load().key(empresaKey).now();
		this.idEmpresa = empresa.getidEmpresa();
		actualizarSucursalForm(sucursalForm); 
		
	}
	 public void actualizarSucursalForm(SucursalForm sucursalForm) {
	     this.nombreSucursal = sucursalForm.getNombreSucursal();
	     this.costoDomicilio = sucursalForm.getCostoDomicilio();
	     this.pedidoMinimo = sucursalForm.getPedidoMinimo();
	     this.tiempoMinimo = sucursalForm.getTiempoMinimo();
	     this.Ubicacion = sucursalForm.obtUbicacion();
	     //LOG.info("LATITUD: "+  Ubicacion.getLatitude());

	 }

	public long getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(long idSucursal) {
		this.idSucursal = idSucursal;
	}

	public String getNombreSucursal() {
		return nombreSucursal;
	}

	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}

	public int getTiempoMinimo() {
		return tiempoMinimo;
	}

	public void setTiempoMinimo(int tiempoMinimo) {
		this.tiempoMinimo = tiempoMinimo;
	}

	public float getCostoDomicilio() {
		return costoDomicilio;
	}

	public void setCostoDomicilio(float costoDomicilio) {
		this.costoDomicilio = costoDomicilio;
	}

	public float getPedidoMinimo() {
		return pedidoMinimo;
	}

	public void setPedidoMinimo(float pedidoMinimo) {
		this.pedidoMinimo = pedidoMinimo;
	}

	public GeoPt getUbicacion() {
		return Ubicacion;
	}

	public void setUbicacion(GeoPt ubicacion) {
		this.Ubicacion = ubicacion;
	}

	public Boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}

	public Boolean getIs_disponible() {
		return is_disponible;
	}

	public void setIs_disponible(Boolean is_disponible) {
		this.is_disponible = is_disponible;
	}

	public List<String> getProductosKeysPertenece() {
		return productosKeysPertenece;
	}

	public void setProductosKeysPertenece(List<String> productosKeysPertenece) {
		this.productosKeysPertenece = productosKeysPertenece;
	}

	@ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	public Key<Empresa> getEmpresaKey() {
		return empresaKey;
	}

	public void setEmpresaKey(Key<Empresa> empresaKey) {
		this.empresaKey = empresaKey;
	}

	@ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	public long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	public String getWebsafekeySucursal(){
		 return Key.create(empresaKey, Sucursal.class,idSucursal).getString();
			
		
	}
	 //agrega grupos a la empresa
	public void addProductoKeysPertenece(String productoKey){
		productosKeysPertenece.add(productoKey);
//				
	}
	public void deleteProductoOfSucursal (String productoKey){
		if(productosKeysPertenece.contains(productoKey)){
			productosKeysPertenece.remove(productoKey);
		}
		else{
			throw new IllegalArgumentException("El Producto No Existe: "+ productoKey);
		}
	}
	
	
	public void addProductosKeysPertenece(List<String> listaKeysProductos){
		for (String keyString : listaKeysProductos) {
			productosKeysPertenece.add(keyString);
	    }
	}
	
	public void deleteProductosOfSucursal (List<String> listaKeysProductos){
		for (String productosKey : listaKeysProductos) {
			this.deleteProductoOfSucursal(productosKey);			
	    }
				
	}	
	
	public String getNombreEmpresa() {
        // Profile organizer = ofy().load().key(Key.create(Profile.class, organizerUserId)).now();
        Empresa organizer = ofy().load().key(getEmpresaKey()).now();
        if (organizer == null) {
            return "";
        } else {
            return organizer.getNombreEmpresa();
        }
    }
	public String getDescripcionEmpresa() {
        // Profile organizer = ofy().load().key(Key.create(Profile.class, organizerUserId)).now();
        Empresa organizer = ofy().load().key(getEmpresaKey()).now();
        if (organizer == null) {
            return "";
        } else {
            return organizer.getDescripcion();
        }
    }
	public int CantidadProductos(){
		int dev = 0;
		for (String keyString : productosKeysPertenece) {
			dev ++;
	    }
		return dev;
		
	}

}
