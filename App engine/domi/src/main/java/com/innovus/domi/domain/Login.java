package com.innovus.domi.domain;
import static com.innovus.domi.service.OfyService.ofy;

//etsa clase devuelve si esta logeado o no
public class Login {
	Boolean estado;
	User user;
	
	
	public Login(){
		this.estado=false;
		user = null;
		
		
	}
	public Login(User user){
		
		this.estado=false;
		this.user = user;
		
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public void loegearlo(){
		estado = true;
	}
	public String getWebsafekeyClienteoUsuario(){
		if(user != null){
			if(user.getIs_cliente()){
				 Cliente cliente = ofy().load().type(Cliente.class).ancestor(user).first().now();
				 return cliente.getWebsafeKey();
			}else{
				Usuario usuario = ofy().load().type(Usuario.class).ancestor(user).first().now();
				 return usuario.getWebsafeKey();
				
			}
			
		}else{
			return null;
		}
		
		
	}
	

}
