package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersModel {

	private List<User> clientes = new ArrayList<>();

	public UsersModel() {

	}

	public boolean registrarCliente(String nombre, String primerApellido, String segundoApellido, String telefono,
			String correo, String contraseña) {
		ConectionModel conexion = new ConectionModel();

		// Validación de campos obligatorios
		if (nombre == null || nombre.trim().isEmpty() || primerApellido == null || primerApellido.trim().isEmpty()
				|| correo == null || correo.trim().isEmpty() || contraseña == null || contraseña.trim().isEmpty()) {
			System.err.println("Error: Nombre, todos los campos son obligatorios");
			return false;
		}

		// Verificar si el correo ya existe
		if (existeUsuario(correo)) {
			System.err.println("Error: El correo electrónico ya está registrado");
			return false;
		}

		String sql = "INSERT INTO usuario (nombre, primer_apellido, segundo_apellido, "
				+ "telefono, correo, contraseña, id_rol) VALUES (?, ?, ?, ?, ?, ?, 2)";

		try {
			Connection conn = conexion.getConnection();

			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				// Configurar todos los parámetros
				pstmt.setString(1, nombre.trim());
				pstmt.setString(2, primerApellido.trim());
				pstmt.setString(3, segundoApellido != null ? segundoApellido.trim() : null);
				pstmt.setString(4, telefono != null ? telefono.trim() : null);
				pstmt.setString(5, correo.trim());
				pstmt.setString(6, contraseña.trim());

				int affectedRows = pstmt.executeUpdate();
				return affectedRows > 0;
			}
		} catch (SQLException e) {
			System.err.println("Error al registrar cliente: " + e.getMessage());
			return false;
		} finally {
			conexion.close();
		}
	}

	private boolean existeUsuario(String correo) {
		ConectionModel conexion = new ConectionModel();
		String sql = "SELECT COUNT(*) FROM usuario WHERE correo = ?";

		try {
			Connection conn = conexion.getConnection();

			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, correo.trim());

				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						return rs.getInt(1) > 0;
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al verificar usuario: " + e.getMessage());
		} finally {
			conexion.close();
		}

		return false;
	}

	public Map<String, String> obtenerDatosParaPDF(int idCliente) {
		Map<String, String> datos = new HashMap<>();
		ConectionModel conexion = new ConectionModel();
		String sql = "SELECT id_usuario, nombre,primer_apellido,segundo_apellido, correo, telefono FROM usuario WHERE id_usuario = ?";

		try {
			Connection conn = conexion.getConnection();
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, idCliente);
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						datos.put("id", rs.getString("id_usuario"));
						datos.put("nombre", rs.getString("nombre"));
						datos.put("nombre", rs.getString("nombre") + " " + rs.getString("primer_apellido") + " "
								+ rs.getString("segundo_apellido"));
						datos.put("correo", rs.getString("correo"));
						datos.put("telefono", rs.getString("telefono"));
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al obtener datos para PDF: " + e.getMessage());
		} finally {
			conexion.close(); // Cierra la conexión
		}
		return datos;
	}

	public Map<String, String> obtenerDatosBasicosCliente(int idCliente) {
		Map<String, String> datosCliente = new HashMap<>();
		ConectionModel conexion = new ConectionModel();

		String sql = "SELECT id_usuario, nombre, primer_apellido,segundo_apellido, telefono, correo "
				+ "FROM usuario WHERE id_usuario = ? AND id_rol = 2";

		try {
			Connection conn = conexion.getConnection();
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, idCliente);

				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
	                    datosCliente.put("id", String.valueOf(rs.getInt("id_usuario")));
	                    datosCliente.put("nombre", rs.getString("nombre")); 
	                    datosCliente.put("primer_apellido", rs.getString("primer_apellido"));
	                    datosCliente.put("segundo_apellido", rs.getString("segundo_apellido"));
	                    datosCliente.put("telefono", rs.getString("telefono"));
	                    datosCliente.put("correo", rs.getString("correo"));
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al obtener datos del cliente: " + e.getMessage());
		} finally {
			conexion.close();
		}

		return datosCliente;
	}

	public boolean eliminarCliente(int idCliente) {
	    ConectionModel conexion = new ConectionModel();
	    
	    try {
	        Connection conn = conexion.getConnection();
	        
	       
	        String sqlInscripciones = "DELETE FROM inscripcion WHERE id_usuario = ?";
	        try (PreparedStatement pstmtInscripciones = conn.prepareStatement(sqlInscripciones)) {
	            pstmtInscripciones.setInt(1, idCliente);
	            pstmtInscripciones.executeUpdate();
	        }
	        
	        String sqlUsuario = "DELETE FROM usuario WHERE id_usuario = ? AND id_rol = 2";
	        try (PreparedStatement pstmtUsuario = conn.prepareStatement(sqlUsuario)) {
	            pstmtUsuario.setInt(1, idCliente);
	            int affectedRows = pstmtUsuario.executeUpdate();
	            return affectedRows > 0;
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al eliminar cliente: " + e.getMessage());
	        return false;
	    } finally {
	        conexion.close();
	    }
	}

	public List<User> getall() {
		clientes.clear();
		ConectionModel conexion = new ConectionModel();

		try {

			String sql = "SELECT id_usuario, nombre,correo,telefono,primer_apellido FROM usuario WHERE id_rol = 2";
			Connection conn = conexion.getConnection();

			try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					clientes.add(new User(rs.getInt("id_usuario"), rs.getString("nombre"), rs.getString("correo"),
							rs.getString("telefono"), rs.getString("primer_apellido")

					));
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al obtener clientes: " + e.getMessage());
		} finally {
			conexion.close();
		}
		return clientes;
	}

	public boolean actualizarCliente(int idCliente, String nombre, String primerApellido, String segundoApellido,
			String telefono, String correo, String contraseña) {
		ConectionModel conexion = new ConectionModel();

		StringBuilder sql = new StringBuilder("UPDATE usuario SET ");
		List<Object> parametros = new ArrayList<>();

		if (nombre != null) {
			sql.append("nombre = ?, ");
			parametros.add(nombre.trim());
		}

		if (primerApellido != null) {
			sql.append("primer_apellido = ?, ");
			parametros.add(primerApellido.trim());
		}

		if (segundoApellido != null) {
			sql.append("segundo_apellido = ?, ");
			parametros.add(segundoApellido != null ? segundoApellido.trim() : null);
		}

		if (telefono != null) {
			sql.append("telefono = ?, ");
			parametros.add(telefono != null ? telefono.trim() : null);
		}

		if (correo != null) {
			sql.append("correo = ?, ");
			parametros.add(correo.trim());
		}

		if (contraseña != null && !contraseña.isEmpty()) {
			sql.append("contraseña = ?, ");
			parametros.add(contraseña.trim());
		}


		sql.delete(sql.length() - 2, sql.length());

		sql.append(" WHERE id_usuario = ?");
		parametros.add(idCliente);

		try {
			Connection conn = conexion.getConnection();
			try (PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
				for (int i = 0; i < parametros.size(); i++) {
					pstmt.setObject(i + 1, parametros.get(i));
				}

				int affectedRows = pstmt.executeUpdate();
				return affectedRows > 0;
			}
		} catch (SQLException e) {
			System.err.println("Error al actualizar cliente: " + e.getMessage());
			return false;
		} finally {
			conexion.close();
		}
	}

}
