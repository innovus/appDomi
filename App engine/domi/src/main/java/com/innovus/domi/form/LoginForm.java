package com.innovus.domi.form;

import com.google.appengine.api.datastore.Email;

public class LoginForm {
	private Email email ;
	private String paswword;
	private LoginForm(){
	    }
	
	public LoginForm(Email email,String password){
		this.email = email;
		this.paswword = password;
	}
	public Email getEmail() {
		return email;
	}
	public void setEmail(Email email) {
		this.email = email;
	}
	public String getPaswword() {
		return paswword;
	}
	public void setPaswword(String paswword) {
		this.paswword = paswword;
	}
	
	public String gEmailString() {
		return email.getEmail();
	}
	
	

	    

}
