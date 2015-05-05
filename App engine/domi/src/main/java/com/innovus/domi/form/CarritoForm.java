package com.innovus.domi.form;

import java.util.Date;

import com.google.appengine.api.datastore.GeoPt;

public class CarritoForm {
	private String websafeKeyUsuario;
	private float total;
	private String observacion;
	private float latitud;
	private float longitud;
	private String formaDePago;

	private CarritoForm() {
	}

	private CarritoForm(String websafeKeyUsuario,float latitud, float longitud, String observacion,
			String formaDePago, float total) {
		this.websafeKeyUsuario = websafeKeyUsuario;
		this.total = total;
		this.observacion = observacion;
		this.latitud = latitud;
		this.longitud = longitud;

		this.formaDePago = formaDePago;

	}

	public float getTotal() {
		return total;
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
