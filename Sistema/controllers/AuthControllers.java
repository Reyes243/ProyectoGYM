package controllers;

import views.AuthView;

public class AuthControllers {
	
	private AuthView vista;
	
public AuthControllers() { 
		
		vista = new AuthView();
	}
	
	public void login() {
		
		vista.login();
	}

}
