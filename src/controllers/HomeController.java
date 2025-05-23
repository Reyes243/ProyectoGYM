package controllers;

import views.HomeView;

public class HomeController {
	
	private HomeView vista;
	
	public HomeController(){		
		vista = new HomeView();
	}	
	public void Panel_inicio() {		
		vista.Panel_inicio();
	}
	public void Clientes() {
		vista.Clientes();
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
