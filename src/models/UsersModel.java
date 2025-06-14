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

	public int registrarCliente(String nombre, String primerApellido, String segundoApellido, String telefono,
			String correo, String contraseña, String tarifaSeleccionada) {
		ConectionModel conexion = new ConectionModel();

		// Validación de campos obligatorios
		if (nombre == null || nombre.trim().isEmpty() || primerApellido == null || primerApellido.trim().isEmpty()
				|| correo == null || correo.trim().isEmpty() || contraseña == null || contraseña.trim().isEmpty()) {
			System.err.println("Error: Nombre, todos los campos son obligatorios");
			return -1;
		}

		// Verificar si el correo ya existe
		if (existeUsuario(correo)) {
			System.err.println("Error: El correo electrónico ya está registrado");
			return -1;
		}

		String sql = "INSERT INTO usuario (nombre, primer_apellido, segundo_apellido, "
				+ "telefono, correo, contraseña, id_rol) VALUES (?, ?, ?, ?, ?, ?, 2)";

		try {
			Connection conn = conexion.getConnection();

			try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				// Configurar todos los parámetros
				pstmt.setString(1, nombre.trim());
				pstmt.setString(2, primerApellido.trim());
				pstmt.setString(3, segundoApellido != null ? segundoApellido.trim() : null);
				pstmt.setString(4, telefono != null ? telefono.trim() : null);
				pstmt.setString(5, correo.trim());
				pstmt.setString(6, contraseña.trim());

				int affectedRows = pstmt.executeUpdate();

				if (affectedRows > 0) {
					try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
						if (generatedKeys.next()) {
							int idUsuario = generatedKeys.getInt(1);

							if (tarifaSeleccionada != null && !tarifaSeleccionada.equals("NINGUNA")) {
								int idTarifa = obtenerIdTarifa(tarifaSeleccionada);
								registrarUsuarioTarifa(idUsuario, idTarifa);
							}

							return idUsuario;
						}
					}
				}
				return -1;
			}
		} catch (SQLException e) {
			System.err.println("Error al registrar cliente: " + e.getMessage());
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

	private int obtenerIdTarifa(String nombreTarifa) {
		ConectionModel conexion = new ConectionModel();
		String sql = "SELECT id_tarifa FROM tarifa WHERE nombre_tarifa = ?";

		try {
			Connection conn = conexion.getConnection();
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, nombreTarifa.toLowerCase());
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						return rs.getInt("id_tarifa");
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al obtener ID de tarifa: " + e.getMessage());
		} finally {
			conexion.close();
		}
		return -1;
	}

	private boolean registrarUsuarioTarifa(int idUsuario, int idTarifa) {
		if (idTarifa == -1)
			return false;

		ConectionModel conexion = new ConectionModel();
		String sql = "INSERT INTO usuario_tarifa (id_usuario, id_tarifa) VALUES (?, ?)";

		try {
			Connection conn = conexion.getConnection();
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, idUsuario);
				pstmt.setInt(2, idTarifa);

				int affectedRows = pstmt.executeUpdate();
				return affectedRows > 0;
			}
		} catch (SQLException e) {
			System.err.println("Error al registrar usuario-tarifa: " + e.getMessage());
			return false;
		} finally {
			conexion.close();
		}
	}

	public Map<String, String> obtenerDatosParaPDF(int idCliente) {
		Map<String, String> datos = new HashMap<>();
		ConectionModel conexion = new ConectionModel();
		String sql = "SELECT u.id_usuario, u.nombre, u.primer_apellido, u.segundo_apellido, "
				+ "u.correo, u.telefono, t.nombre_tarifa, t.precio " + "FROM usuario u "
				+ "LEFT JOIN usuario_tarifa ut ON u.id_usuario = ut.id_usuario "
				+ "LEFT JOIN tarifa t ON ut.id_tarifa = t.id_tarifa " + "WHERE u.id_usuario = ?";

		try {
			Connection conn = conexion.getConnection();
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, idCliente);
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						datos.put("primer_apellido", rs.getString("primer_apellido"));
						datos.put("id", rs.getString("id_usuario"));
						datos.put("nombre", rs.getString("nombre"));
						datos.put("nombre_completo", rs.getString("nombre") + " " + rs.getString("primer_apellido")
								+ " "
								+ (rs.getString("segundo_apellido") != null ? rs.getString("segundo_apellido") : ""));
						datos.put("correo", rs.getString("correo"));
						datos.put("telefono", rs.getString("telefono"));

						// Agregar información de la tarifa
						String nombreTarifa = rs.getString("nombre_tarifa");
						Double precioTarifa = rs.getDouble("precio");

						datos.put("tarifa", nombreTarifa != null ? nombreTarifa : "Ninguna");
						datos.put("precio_tarifa", precioTarifa != 0 ? String.format("$%.2f", precioTarifa) : "$0.00");
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al obtener datos para PDF: " + e.getMessage());
		} finally {
			conexion.close();
		}
		return datos;
	}

	public Map<String, String> obtenerDatosBasicosCliente(int idCliente) {
		Map<String, String> datosCliente = new HashMap<>();
		ConectionModel conexion = new ConectionModel();

		String sql = "SELECT u.id_usuario, u.nombre, u.primer_apellido, u.segundo_apellido, "
				+ "u.telefono, u.correo, t.nombre_tarifa, t.precio, c.nombre_clase " + "FROM usuario u "
				+ "LEFT JOIN usuario_tarifa ut ON u.id_usuario = ut.id_usuario "
				+ "LEFT JOIN tarifa t ON ut.id_tarifa = t.id_tarifa "
				+ "LEFT JOIN inscripcion i ON u.id_usuario = i.id_usuario "
				+ "LEFT JOIN clase c ON i.id_clase = c.id_clase " + "WHERE u.id_usuario = ? AND u.id_rol = 2";

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
						datosCliente.put("tarifa",
								rs.getString("nombre_tarifa") != null ? rs.getString("nombre_tarifa") : "Ninguna");
						datosCliente.put("clase",
								rs.getString("nombre_clase") != null ? rs.getString("nombre_clase") : "Ninguna");

						if (rs.getString("nombre_tarifa") != null) {
							datosCliente.put("precio_tarifa", String.valueOf(rs.getDouble("precio")));
						} else {
							datosCliente.put("precio_tarifa", "0.00");
						}
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
		Connection conn = null;

		try {
			conn = conexion.getConnection();
			conn.setAutoCommit(false);

			String sqlAsistencia = "DELETE FROM asistencia WHERE id_usuario = ?";
			try (PreparedStatement pstmt = conn.prepareStatement(sqlAsistencia)) {
				pstmt.setInt(1, idCliente);
				pstmt.executeUpdate();
			}

			String sqlInscripciones = "DELETE FROM inscripcion WHERE id_usuario = ?";
			try (PreparedStatement pstmt = conn.prepareStatement(sqlInscripciones)) {
				pstmt.setInt(1, idCliente);
				pstmt.executeUpdate();
			}

			String sqlTarifas = "DELETE FROM usuario_tarifa WHERE id_usuario = ?";
			try (PreparedStatement pstmt = conn.prepareStatement(sqlTarifas)) {
				pstmt.setInt(1, idCliente);
				pstmt.executeUpdate();
			}

			String sqlUsuario = "DELETE FROM usuario WHERE id_usuario = ? AND id_rol = 2";
			try (PreparedStatement pstmt = conn.prepareStatement(sqlUsuario)) {
				pstmt.setInt(1, idCliente);
				int affectedRows = pstmt.executeUpdate();

				conn.commit();
				return affectedRows > 0;
			}
		} catch (SQLException e) {
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (SQLException ex) {
				System.err.println("Error al hacer rollback: " + ex.getMessage());
			}
			System.err.println("Error al eliminar cliente: " + e.getMessage());
			return false;
		} finally {
			try {
				if (conn != null) {
					conn.setAutoCommit(true);
				}
			} catch (SQLException e) {
				System.err.println("Error al restaurar auto-commit: " + e.getMessage());
			}
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

	public List<User> getClientesPorTarifa(String nombreTarifa) {
		List<User> clientes = new ArrayList<>();
		ConectionModel conexion = new ConectionModel();

		// Debug: Mostrar la tarifa que estamos buscando
		System.out.println("Buscando clientes con tarifa: " + nombreTarifa);

		String sql = "SELECT u.id_usuario, u.nombre, u.primer_apellido, u.telefono, u.correo " + "FROM usuario u "
				+ "JOIN usuario_tarifa ut ON u.id_usuario = ut.id_usuario "
				+ "JOIN tarifa t ON ut.id_tarifa = t.id_tarifa "
				+ "WHERE LOWER(t.nombre_tarifa) = LOWER(?) AND u.id_rol = 2 " + "ORDER BY u.primer_apellido, u.nombre";

		try (Connection conn = conexion.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, nombreTarifa);
			ResultSet rs = pstmt.executeQuery();

			// Debug: Contar resultados
			int count = 0;
			while (rs.next()) {
				count++;
				User user = new User(rs.getInt("id_usuario"), rs.getString("nombre"), rs.getString("primer_apellido"),
						rs.getString("telefono"), rs.getString("correo"));
				clientes.add(user);
			}
			System.out.println("Clientes encontrados: " + count);

		} catch (SQLException e) {
			System.err.println("Error al obtener clientes por tarifa: " + e.getMessage());
			e.printStackTrace();
		} finally {
			conexion.close();
		}

		return clientes;
	}

	// En UsersModel o InstructorModel
	public boolean eliminarInstructor(int idInstructor) {
		Connection conn = null;
		PreparedStatement psDeleteHorarios = null;
		PreparedStatement psDeleteClases = null;
		PreparedStatement psDeleteUsuario = null;

		try {
			conn = new ConectionModel().getConnection();
			conn.setAutoCommit(false); // 🔒 Empezar transacción

			// 1. Eliminar horarios de clases que imparte este instructor
			String sqlDeleteHorarios = """
					    DELETE ch FROM clase_horario ch
					    JOIN clase c ON ch.id_clase = c.id_clase
					    WHERE c.id_usuario = ?
					""";
			psDeleteHorarios = conn.prepareStatement(sqlDeleteHorarios);
			psDeleteHorarios.setInt(1, idInstructor);
			psDeleteHorarios.executeUpdate();

			// 2. Eliminar clases del instructor
			String sqlDeleteClases = "DELETE FROM clase WHERE id_usuario = ?";
			psDeleteClases = conn.prepareStatement(sqlDeleteClases);
			psDeleteClases.setInt(1, idInstructor);
			psDeleteClases.executeUpdate();

			// 3. Eliminar al instructor de la tabla usuario
			String sqlDeleteUsuario = "DELETE FROM usuario WHERE id_usuario = ? AND id_rol = 3";
			psDeleteUsuario = conn.prepareStatement(sqlDeleteUsuario);
			psDeleteUsuario.setInt(1, idInstructor);
			int filas = psDeleteUsuario.executeUpdate();

			conn.commit(); // ✅ Confirmar
			return filas > 0;

		} catch (SQLException e) {
			if (conn != null)
				try {
					conn.rollback();
				} catch (SQLException ignored) {
				}
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (psDeleteHorarios != null)
					psDeleteHorarios.close();
				if (psDeleteClases != null)
					psDeleteClases.close();
				if (psDeleteUsuario != null)
					psDeleteUsuario.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean registrarAsistenciaManual(int idUsuario, String fecha, String hora) {
		ConectionModel conexion = new ConectionModel();
		String sql = "INSERT INTO asistencia (id_usuario, fecha, hora) VALUES (?, ?, ?)";

		try {
			Connection conn = conexion.getConnection();
			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setInt(1, idUsuario);
				ps.setString(2, fecha); // "2025-06-08"
				ps.setString(3, hora); // "14:30"
				return ps.executeUpdate() > 0;
			}
		} catch (SQLException e) {
			System.err.println("Error al registrar asistencia manual: " + e.getMessage());
			return false;
		} finally {
			conexion.close();
		}
	}

	public boolean esCliente(int idUsuario) {
		ConectionModel conexion = new ConectionModel();
		String sql = "SELECT id_rol FROM usuario WHERE id_usuario = ?";

		try {
			Connection conn = conexion.getConnection();
			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setInt(1, idUsuario);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					return rs.getInt("id_rol") == 2;
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al verificar si es cliente: " + e.getMessage());
		} finally {
			conexion.close();
		}

		return false;
	}

	public List<String[]> obtenerAsistenciasRecientes() {
		List<String[]> lista = new ArrayList<>();
		ConectionModel conexion = new ConectionModel();
		String sql = """
				    SELECT a.id_usuario, u.nombre, u.primer_apellido, a.fecha, a.hora
				    FROM asistencia a
				    JOIN usuario u ON a.id_usuario = u.id_usuario
				    ORDER BY a.fecha DESC, a.hora DESC
				    LIMIT 50
				""";

		try (Connection conn = conexion.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				String[] fila = { String.valueOf(rs.getInt("id_usuario")),
						rs.getString("nombre") + " " + rs.getString("primer_apellido"), rs.getString("fecha"),
						rs.getString("hora") };
				lista.add(fila);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}

		return lista;
	}

	public boolean yaRegistroHoy(int idUsuario) {
		ConectionModel conexion = new ConectionModel();
		String sql = "SELECT COUNT(*) FROM asistencia WHERE id_usuario = ? AND fecha = CURRENT_DATE";

		try (Connection conn = conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} catch (SQLException e) {
			System.err.println("Error al verificar asistencia del día: " + e.getMessage());
		} finally {
			conexion.close();
		}

		return false;
	}

	public List<String[]> obtenerHistorialAsistencia(int idUsuario) {
		List<String[]> historial = new ArrayList<>();
		ConectionModel conexion = new ConectionModel();

		String sql = "SELECT fecha, hora FROM asistencia WHERE id_usuario = ? ORDER BY fecha DESC, hora DESC";

		try (Connection conn = conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, idUsuario);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String fecha = rs.getString("fecha"); // formato: YYYY-MM-DD
				String[] partes = fecha.split("-");
				historial.add(new String[] { partes[2], // día
						partes[1], // mes
						partes[0], // año
						rs.getString("hora") });
			}

		} catch (SQLException e) {
			System.err.println("Error al obtener historial de asistencia: " + e.getMessage());
		} finally {
			conexion.close();
		}

		return historial;
	}
}
