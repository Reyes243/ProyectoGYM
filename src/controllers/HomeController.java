package controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Clase;
import models.ClaseModel;
import models.ConectionModel;
import models.InstrucoresModel;
import models.Instructor;
import models.Tarifa;
import models.TarifaModel;
import models.User;
import models.UsersModel;
import views.HomeView;

public class HomeController {
	
	private List<User> clientes = new ArrayList<>();
	private List<Instructor> instructores = new ArrayList<>();
	private List<Tarifa> tarifas = new ArrayList<>();
	private List<Clase> clases = new ArrayList<>();
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
	   TarifaModel tm = new TarifaModel();
	   tarifas = tm.obtenerTodas();
	    vista.Tarifas(tarifas);
	}
	public void Instructores() {
		InstrucoresModel im = new InstrucoresModel();
		instructores = im.getall();
		vista.Instructores(instructores);
	}
	public void Clases() {
	    try {
	        Connection conn = new ConectionModel().getConnection(); // Obtener conexión
	        ClaseModel cm = new ClaseModel(conn);                   // Usar el constructor correcto
	        clases = cm.obtenerTodasLasClases();
	        vista.Clases(clases);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        // Puedes mostrar un error en la vista si lo deseas
	    }
	}
	public void Panel_checador() {
		vista.Panel_checador();
	}	

}
