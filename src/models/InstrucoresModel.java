package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstrucoresModel {

	private List<Instructor> instructores = new ArrayList<>();

	public InstrucoresModel() {

	}

	public int registrarInstructor(String nombre, String telefono, String correo, String especialidad,
			String claseSeleccionada) {
		ConectionModel conexion = new ConectionModel();

		// Validación de campos obligatorios
		if (nombre == null || nombre.trim().isEmpty() || correo == null || correo.trim().isEmpty()
				|| especialidad == null || especialidad.trim().isEmpty()) {
			System.err.println("Error: Nombre, todos los campos son obligatorios");
			return -1;
		}

		// Verificar si el correo ya existe
		if (existeUsuario(correo)) {
			System.err.println("Error: El correo electrónico ya está registrado");
			return -1;
		}

		String sql = "INSERT INTO usuario (nombre, telefono, correo, especialidad, id_rol) " + "VALUES (?, ?, ?, ?, 3)";

		try {
			Connection conn = conexion.getConnection();

			try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				// Configurar todos los parámetros
				pstmt.setString(1, nombre.trim());
				pstmt.setString(2, telefono != null ? telefono.trim() : null);
				pstmt.setString(3, correo.trim());
				pstmt.setString(4, especialidad != null ? especialidad.trim() : null);

				int affectedRows = pstmt.executeUpdate();

				if (affectedRows > 0) {
					try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
						if (generatedKeys.next()) {
							int idUsuario = generatedKeys.getInt(1);

							if (claseSeleccionada != null && !claseSeleccionada.equalsIgnoreCase("NINGUNA")) {
								int idClase = obtenerIdClase(claseSeleccionada);
								registrarUsuarioClase(idUsuario, idClase);
							}

							return idUsuario;
						}
					}
				}
				return -1;
			}
		} catch (SQLException e) {
			System.err.println("Error al registrar instructor: " + e.getMessage());
			return -1;
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

	private int obtenerIdClase(String nombreclase) {
		ConectionModel conexion = new ConectionModel();
		String sql = "SELECT id_clase FROM clase WHERE nombre_clase = ?";

		try {
			Connection conn = conexion.getConnection();
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, nombreclase);
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						return rs.getInt("id_clase");
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al obtener ID de clase: " + e.getMessage());
		} finally {
			conexion.close();
		}
		return -1;
	}

	private boolean registrarUsuarioClase(int idUsuario, int idClase) {
		if (idClase == -1)
			return false;

		ConectionModel conexion = new ConectionModel();
		String sql = "UPDATE clase SET id_usuario = ? WHERE id_clase = ?";
		try {
			Connection conn = conexion.getConnection();
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, idUsuario);
				pstmt.setInt(2, idClase);

				int affectedRows = pstmt.executeUpdate();
				return affectedRows > 0;
			}
		} catch (SQLException e) {
			System.err.println("Error al asignar clase al instructor: " + e.getMessage());
			return false;
		} finally {
			conexion.close();
		}
	}

	public boolean actualizarInstructor(int idInstructor, String nombre, String telefono, String correo,
			String especialidad, String claseSeleccionada) {
		ConectionModel conexion = new ConectionModel();

		StringBuilder sql = new StringBuilder("UPDATE usuario SET ");
		List<Object> parametros = new ArrayList<>();

		if (nombre != null) {
			sql.append("nombre = ?, ");
			parametros.add(nombre.trim());
		}

		if (telefono != null) {
			sql.append("telefono = ?, ");
			parametros.add(telefono.trim());
		}

		if (correo != null) {
			sql.append("correo = ?, ");
			parametros.add(correo.trim());
		}

		if (especialidad != null) {
			sql.append("especialidad = ?, ");
			parametros.add(especialidad.trim());
		}

		if (sql.toString().endsWith(", ")) {
			sql.delete(sql.length() - 2, sql.length());
		}

		sql.append(" WHERE id_usuario = ?");
		parametros.add(idInstructor);

		try {
			Connection conn = conexion.getConnection();
			conn.setAutoCommit(false);

			try {
				// 1. Actualizar datos básicos del instructor
				try (PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
					for (int i = 0; i < parametros.size(); i++) {
						pstmt.setObject(i + 1, parametros.get(i));
					}
					int affectedRows = pstmt.executeUpdate();
					if (affectedRows == 0) {
						conn.rollback();
						return false;
					}
				}

				// 2. Manejo de la clase asignada
				if (claseSeleccionada != null) {
					// Quitar al instructor de cualquier clase actual
					String clearClassSql = "UPDATE clase SET id_usuario = NULL WHERE id_usuario = ?";
					try (PreparedStatement pstmt = conn.prepareStatement(clearClassSql)) {
						pstmt.setInt(1, idInstructor);
						pstmt.executeUpdate();
					}

					// Asignar nueva clase si se seleccionó una
					if (!claseSeleccionada.equalsIgnoreCase("NINGUNA")) {
						int idClase = obtenerIdClase(claseSeleccionada);
						if (idClase != -1) {
							String assignClassSql = "UPDATE clase SET id_usuario = ? WHERE id_clase = ?";
							try (PreparedStatement pstmt = conn.prepareStatement(assignClassSql)) {
								pstmt.setInt(1, idInstructor);
								pstmt.setInt(2, idClase);
								int rowsUpdated = pstmt.executeUpdate();
								if (rowsUpdated == 0) {
									conn.rollback();
									return false;
								}
							}
						}
					}
				}

				conn.commit();
				return true;
			} catch (SQLException e) {
				conn.rollback();
				System.err.println("Error al actualizar instructor: " + e.getMessage());
				return false;
			} finally {
				conn.setAutoCommit(true);
			}
		} catch (SQLException e) {
			System.err.println("Error al conectar con la base de datos: " + e.getMessage());
			return false;
		} finally {
			conexion.close();
		}
	}

	public Map<String, String> obtenerDatosParaPDFInstructor(int idInstructor) {
		Map<String, String> datos = new HashMap<>();
		ConectionModel conexion = new ConectionModel();

		String sql = "SELECT u.id_usuario, u.nombre, u.correo, u.telefono, u.especialidad, " + "c.nombre_clase, "
				+ "GROUP_CONCAT(DISTINCT CONCAT(ch.dia_semana, ' - ', ch.turno) SEPARATOR '\n') AS horarios "
				+ "FROM usuario u " + "LEFT JOIN clase c ON u.id_usuario = c.id_usuario "
				+ "LEFT JOIN clase_horario ch ON c.id_clase = ch.id_clase " + "WHERE u.id_usuario = ? AND u.id_rol = 3 "
				+ "GROUP BY u.id_usuario, u.nombre, u.correo, u.telefono, u.especialidad, c.nombre_clase";

		try {
			Connection conn = conexion.getConnection();
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, idInstructor);
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						// Datos básicos del instructor
						datos.put("id", rs.getString("id_usuario"));
						datos.put("nombre", rs.getString("nombre"));
						datos.put("nombre_completo", rs.getString("nombre")); // Instructores no tienen apellidos
						datos.put("correo", rs.getString("correo"));
						datos.put("telefono", rs.getString("telefono"));
						datos.put("especialidad", rs.getString("especialidad"));

						// Información de la clase asignada
						String nombreClase = rs.getString("nombre_clase");
						datos.put("clase_asignada", nombreClase != null ? nombreClase : "Ninguna");

						// Horarios (separados por saltos de línea)
						String horarios = rs.getString("horarios");
						datos.put("horarios", horarios != null ? horarios : "Sin horarios asignados");
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al obtener datos del instructor para PDF: " + e.getMessage());
			e.printStackTrace();
		} finally {
			conexion.close();
		}

		return datos;
	}

	public List<Instructor> getall() {
		instructores.clear();
		ConectionModel conexion = new ConectionModel();

		try {

			String sql = "SELECT id_usuario, nombre,especialidad,correo FROM usuario WHERE id_rol = 3";
			Connection conn = conexion.getConnection();

			try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					instructores.add(new Instructor(rs.getInt("id_usuario"), rs.getString("nombre"),
							rs.getString("especialidad"), rs.getString("correo")

					));
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al obtener clientes: " + e.getMessage());
		} finally {
			conexion.close();
		}
		return instructores;
	}

	public Map<String, String> obtenerDatosBasicosInstructor(int idInstructor) {
		Map<String, String> datosInstructor = new HashMap<>();
		ConectionModel conexion = new ConectionModel();

		String sql = "SELECT u.id_usuario, u.nombre, u.telefono, u.correo, u.especialidad, " + "c.nombre_clase, "
				+ "GROUP_CONCAT(DISTINCT CONCAT(ch.dia_semana, ' - ', ch.turno) SEPARATOR '; ') AS horarios "
				+ "FROM usuario u " + "LEFT JOIN clase c ON u.id_usuario = c.id_usuario "
				+ "LEFT JOIN clase_horario ch ON c.id_clase = ch.id_clase " + "WHERE u.id_usuario = ? AND u.id_rol = 3 "
				+ "GROUP BY u.id_usuario, u.nombre, u.telefono, u.correo, u.especialidad, c.nombre_clase";

		try {
			Connection conn = conexion.getConnection();
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, idInstructor);
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						datosInstructor.put("id", String.valueOf(rs.getInt("id_usuario")));
						datosInstructor.put("nombre", rs.getString("nombre"));
						datosInstructor.put("telefono", rs.getString("telefono"));
						datosInstructor.put("correo", rs.getString("correo"));
						datosInstructor.put("especialidad", rs.getString("especialidad"));
						datosInstructor.put("clase_asignada", rs.getString("nombre_clase"));

						String horarios = rs.getString("horarios");
						datosInstructor.put("horarios", horarios != null ? horarios : "Sin horarios asignados");
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al obtener datos del instructor: " + e.getMessage());
			e.printStackTrace();
		} finally {
			conexion.close();
		}

		return datosInstructor;
	}

	public Map<String, String> obtenerDatosCompletosInstructor(int idInstructor) {
		Map<String, String> datosInstructor = new HashMap<>();
		ConectionModel conexion = new ConectionModel();

		String sql = "SELECT u.id_usuario, u.nombre, u.especialidad, u.telefono, u.correo, "
				+ "GROUP_CONCAT(DISTINCT CONCAT(ch.dia_semana, ' - ', ch.turno) SEPARATOR '; ') AS horarios, "
				+ "GROUP_CONCAT(DISTINCT c.nombre_clase SEPARATOR ', ') AS clases " + "FROM usuario u "
				+ "LEFT JOIN clase c ON u.id_usuario = c.id_usuario "
				+ "LEFT JOIN clase_horario ch ON c.id_clase = ch.id_clase " + "WHERE u.id_usuario = ? AND u.id_rol = 3 "
				+ "GROUP BY u.id_usuario";

		try {
			Connection conn = conexion.getConnection();
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, idInstructor);

				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						datosInstructor.put("id", String.valueOf(rs.getInt("id_usuario")));
						datosInstructor.put("nombre", rs.getString("nombre"));
						datosInstructor.put("especialidad", rs.getString("especialidad"));
						datosInstructor.put("telefono", rs.getString("telefono"));
						datosInstructor.put("correo", rs.getString("correo"));
						datosInstructor.put("horarios", rs.getString("horarios"));
						datosInstructor.put("clases", rs.getString("clases"));
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al obtener datos del instructor: " + e.getMessage());
		} finally {
			conexion.close();
		}

		return datosInstructor;
	}

	public List<Object[]> getClasesPorInstructor(int idInstructor) {
		List<Object[]> lista = new ArrayList<>();
		String sql = "SELECT u.nombre AS entrenador, c.nombre_clase, ch.turno, ch.dia_semana " + "FROM usuario u "
				+ "JOIN clase c ON u.id_usuario = c.id_usuario "
				+ "LEFT JOIN clase_horario ch ON c.id_clase = ch.id_clase " + "WHERE u.id_usuario = ?";

		try (Connection conn = new ConectionModel().getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idInstructor);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				lista.add(new Object[] { rs.getString("entrenador"), rs.getString("nombre_clase"),
						rs.getString("turno"), rs.getString("dia_semana") });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public List<Object[]> getClientesDeClasesDelInstructor(int idInstructor) {
		List<Object[]> lista = new ArrayList<>();
		String sql = "SELECT u.id_usuario AS id_cliente, u.nombre, u.primer_apellido, u.telefono, u.correo "
				+ "FROM usuario u " + "JOIN inscripcion i ON u.id_usuario = i.id_usuario "
				+ "JOIN clase c ON i.id_clase = c.id_clase " + "WHERE c.id_usuario = ? AND u.id_rol = 2";

		try (Connection conn = new ConectionModel().getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idInstructor);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				lista.add(new Object[] { rs.getInt("id_cliente"), rs.getString("nombre"),
						rs.getString("primer_apellido"), rs.getString("telefono"), rs.getString("correo") });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
}
