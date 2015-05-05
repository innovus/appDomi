package com.innovus.domi.form;

public class CarritoConPedidosForm {
	private ListaPedidosForms listaPedidosForms;
	private CarritoForm carritoForm;
	public CarritoConPedidosForm(){}
	
	public void setListaPedidosForms(ListaPedidosForms lpf){
		this.listaPedidosForms = lpf;
	}
	public ListaPedidosForms getListaPedidosForms(){
		 return listaPedidosForms; 
	 }
	public void setCarritoForm (CarritoForm cf){
		this.carritoForm = cf;
		
		
	}
	public CarritoForm getCarritoForm (){
		return carritoForm;
		
	}

}
