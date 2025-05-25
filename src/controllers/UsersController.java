package controllers;

import java.util.ArrayList;
import java.util.List;

import models.User;
import views.UsersView;

public class UsersController {
	
	
	private UsersView vista;
	private List<User> clientes = new ArrayList<>();
	
	public UsersController() {
		
		vista = new UsersView();
	}
	
	public void Informacion_de_cliente(int idCliente){
		vista.Informacion_de_cliente(idCliente);
	}
	public void Edicion_de_informacion_de_cliente(int idcliente) {
		vista.Edicion_de_informacion_de_cliente(idcliente);
	}
	public void Historial_de_pagos(int idcliente) {
		vista.Historial_de_pagos(idcliente);
	}
	public void HIstorial_de_asistencias(int idcliente) {
		vista.HIstorial_de_asistencias(idcliente);
	}
	public void Añadir_cliente() {
		vista.Añadir_cliente();
	}
	public void Editar_tarifas() {
		vista.Editar_tarifas();
	}
	public void Editar_tarifas_PREMIUM() {
		vista.Editar_tarifas_PREMIUM();
	}
	public void Editar_tarifas_FAMILIAR() {
		vista.Editar_tarifas_FAMILIAR();
	}
	public void Editar_tarifas2() {
		vista.Editar_tarifas_2();
	}
	public void Añadir_tarifa() {
		vista.Añadir_tarifa();
	}
	public void Clientes_con_tarifa_FAMILIAR() {
		vista.Clientes_con_tarifa_FAMILIAR();
	}
	public void Clientes_con_tarifa_ESTANDAR() {
		vista.Clientes_con_tarifa_ESTANDAR();
	}
	public void Clientes_con_tarifa_PREMIUM() {
		vista.Clientes_con_tarifa_PREMIUM();
	}
	public void Credencial_usuario(int idcliente) {
		vista.Credencial_usuario(idcliente);
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
