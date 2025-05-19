package controllers;

import views.UsersView;

public class UsersController {
	
	private UsersView vista;
	
	public UsersController() {
		
		vista = new UsersView();
	}
	
	public void Clientes() {
		
		vista.Clientes();
	}
	public void Informacion_de_cliente(int idCliente){
		vista.Informacion_de_cliente(idCliente);
	}
	public void Edicion_de_informacion_de_cliente() {
		vista.Edicion_de_informacion_de_cliente();
	}
	public void Historial_de_pagos() {
		vista.Historial_de_pagos();
	}
	public void HIstorial_de_asistencias() {
		vista.HIstorial_de_asistencias();
	}
	public void Añadir_cliente() {
		vista.Añadir_cliente();
	}
}
