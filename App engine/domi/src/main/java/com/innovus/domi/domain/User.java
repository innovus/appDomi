package com.innovus.domi.domain;

import com.google.appengine.api.datastore.Email;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class User {
	@Id
	private long idUser;
	
	@Index
	private String password;
	
	@Index
	private Email email;
	private Boolean is_user=true;
	private Boolean is_cliente=true;

	protected User(){}
	
	protected User(long idUser, Email email,String password, Boolean is_user, Boolean is_cliente){
		{
			this.idUser = idUser;
			this.email = email;
			this.password = password;
			this.is_user = is_user;
			this.is_cliente = is_cliente;
		}
	}
	
	//public User()
	
	
	public long getIdUser() {
		return idUser;
	}


	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Email getEmail() {
		return email;
	}


	public void setEmail(Email email) {
		this.email = email;
	}
	
	public Boolean getIs_user() {
		return is_user;
	}

	public void setIs_user(Boolean is_user) {
		this.is_user = is_user;
	}

	public Boolean getIs_cliente() {
		return is_cliente;
	}

	public void setIs_cliente(Boolean is_cliente) {
		this.is_cliente = is_cliente;
	}

}
