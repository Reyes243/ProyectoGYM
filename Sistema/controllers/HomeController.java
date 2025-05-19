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
	public void Tarifas() {
		vista.Tarifas();
	}
		
	

}
