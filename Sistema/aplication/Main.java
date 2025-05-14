package aplication;

import controllers.AuthControllers;
import models.ConectionModel;


public class Main {

	public static void main(String[] args) {
		new ConectionModel();
	
		
		AuthControllers app = new AuthControllers(); 
		app.login();
	}

}
