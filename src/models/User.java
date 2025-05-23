package models;

import java.util.Date;

public class User {
	
	public int id;
	public String nombre;
	public String correo;
	public int rol;
	public String telefono ;
	public String primer_apellido;
	public String segundo_apellido;
	

	
	public User(int id,String nombre,String correo,int rol,String telefono,String primer_apellido,String segundo_apellido) {
		
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.rol = rol;
		this.telefono = telefono;
		this.primer_apellido = primer_apellido;
		this.segundo_apellido = segundo_apellido;
	
	}
	
}
