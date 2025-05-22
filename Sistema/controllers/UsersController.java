package controllers;

import views.UsersView;

public class UsersController {
	
	private UsersView vista;
	
	public UsersController() {
		
		vista = new UsersView();
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
	public void Editar_tarifas() {
		vista.Editar_tarifas();
	}
	public void Editar_tarifas2() {
		vista.Editar_tarifas_2();
	}
	public void Añadir_tarifa() {
		vista.Añadir_tarifa();
	}
	public void Clientes_con_tarifa_PREMIUM() {
		vista.Clientes_con_tarifa_PREMIUM();
	}
	public void Credencial_usuario() {
		vista.Credencial_usuario();
	}
	public void Ficha_de_instructor(String nombreInstructor) {
		vista.Ficha_de_instructor(nombreInstructor);
	}
	public void  Historial_de_clase() {
		vista.Historial_de_clase();
	}
	public void Editar_instructor() {
		vista.Editar_instructor();
	}
	public void Credencial_instructor() {
		vista.Credencial_instructor();
	}
	public void Añadir_instructor() {
		vista.Añadir_instructor();
		
	}
	public void Registro_de_clase(String nombreClase) {
		vista.Registro_de_clase(nombreClase);
	}
	public void Editar_eliminar_y_añadir_clases() {
		vista.Editar_eliminar_y_añadir_clases();
	}
	public void Editar_clases(String nombreClase) {
		vista.Editar_clases(nombreClase);
	}
	public void Añadir_clases() {
		vista.Añadir_clases();
		
	}
	
}
