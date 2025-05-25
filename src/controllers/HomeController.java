package controllers;

import java.util.ArrayList;
import java.util.List;

import models.User;
import models.UsersModel;
import views.HomeView;

public class HomeController {
	
	private List<User> clientes = new ArrayList<>();
	private HomeView vista;
	
	public HomeController(){		
		vista = new HomeView();
	}	
	public void Panel_inicio() {		
		vista.Panel_inicio();
	}
	public void Clientes() {		
		UsersModel um = new UsersModel();
		clientes = um.getall();
		vista.Clientes(clientes);
	}
	public void Tarifas() {
		vista.Tarifas();
	}
	public void Instructores() {
		vista.Instructores();
	}
	public void Clases() {
		vista.Clases();
	}
	public void Panel_checador() {
		vista.Panel_checador();
	}
		
	

}
