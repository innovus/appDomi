package com.innovus.domi.form;

import java.util.Date;

import com.google.appengine.api.datastore.GeoPt;

public class CarritoForm {
	private String websafeKeyUsuario;
	private String observacion;
	private float latitud;
	private float longitud;
	private String formaDePago;
	private String direccion;
	
	

	private CarritoForm() {
	}

	private CarritoForm(String websafeKeyUsuario,float latitud, float longitud, String observacion,
			String formaDePago,String direccion ) {
		this.websafeKeyUsuario = websafeKeyUsuario;
		
		this.observacion = observacion;
		this.latitud = latitud;
		this.longitud = longitud;

		this.formaDePago = formaDePago;
		this.direccion = direccion;

	}

	public String getDireccion(){
		return direccion;
	}

	public String getObservacion() {
		return observacion;
	}

	public GeoPt obtenerUbicacion() {
		return new GeoPt(latitud, longitud);
	}

	public String getFormaDePago() {
		return formaDePago;
	}

	public float getLatitud() {
		return latitud;
	}

	public float getLongitud() {
		return longitud;
	}
	public  String getWebsafeKeyUsuario(){
		return websafeKeyUsuario;
	}

}
