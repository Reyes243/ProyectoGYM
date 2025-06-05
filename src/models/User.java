package models;

public class User {
	private int id;
	private String nombre;
	private String correo;
	private String telefono;
	private String primer_apellido;

	public User(int id, String nombre, String correo, String telefono, String primer_apellido) {
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.telefono = telefono;
		this.primer_apellido = primer_apellido;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getPrimerApellido() {
		return primer_apellido;

	}

	public String getPrimer_apellido() {
		return primer_apellido;
	}

	public void setPrimer_apellido(String primer_apellido) {
		this.primer_apellido = primer_apellido;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
