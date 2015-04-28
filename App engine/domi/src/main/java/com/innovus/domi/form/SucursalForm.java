package com.innovus.domi.form;

import java.util.logging.Logger;

import com.google.appengine.api.datastore.GeoPt;
import com.innovus.domi.domain.Sucursal;

public class SucursalForm {
	private static final Logger LOG = Logger.getLogger(SucursalForm.class.getName());

	
	private String nombreSucursal;
	private int tiempoMinimo;
	private float costoDomicilio;
	private float pedidoMinimo;
	private float latitud;
	private float longitud;
	
	
	public SucursalForm(){}
	

	
	public SucursalForm(String nombreSucursal, int tiempoMinimo,
			float costoDomicilio, float pedidoMinimo, float latitud, float longitud) {
		
		this.nombreSucursal = nombreSucursal;
		this.tiempoMinimo = tiempoMinimo;
		this.costoDomicilio = costoDomicilio;
		this.pedidoMinimo = pedidoMinimo;
		this.latitud = latitud;
		this.longitud=longitud;
		
		
		
		
		
	}



	public String getNombreSucursal() {
		return nombreSucursal;
	}

	public int getTiempoMinimo() {
		return tiempoMinimo;
	}

	public float getCostoDomicilio() {
		return costoDomicilio;
	}

	public float getPedidoMinimo() {
		return pedidoMinimo;
	}

	public GeoPt obtUbicacion() {
		
		return new GeoPt(latitud, longitud);
	}
	public float getLatitud(){
		return this.latitud;
		
	}
	public float getLongitud(){
		return this.longitud;
		
	}
	
}
