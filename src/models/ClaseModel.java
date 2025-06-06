package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClaseModel {
	private Connection connection;

	public ClaseModel() {
		this.connection = connection;
	}

	// Método para crear una nueva clase con sus horarios
	public boolean crearClase(Clase clase) throws SQLException {
		String sqlClase = "INSERT INTO clase (id_usuario, nombre_clase) VALUES (?, ?)";

		try (PreparedStatement pstmt = connection.prepareStatement(sqlClase, PreparedStatement.RETURN_GENERATED_KEYS)) {
			pstmt.setInt(1, clase.getIdUsuario());
			pstmt.setString(2, clase.getNombreClase());

			int affectedRows = pstmt.executeUpdate();

			if (affectedRows == 0) {
				return false;
			}

			// Obtener el ID generado
			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					int idClaseGenerada = generatedKeys.getInt(1);

					// Insertar los horarios
					if (clase.getHorarios() != null && !clase.getHorarios().isEmpty()) {
						String sqlHorario = "INSERT INTO clase_horario (id_clase, dia_semana, turno) VALUES (?, ?, ?)";

						try (PreparedStatement pstmtHorario = connection.prepareStatement(sqlHorario)) {
							for (ClaseHorario horario : clase.getHorarios()) {
								pstmtHorario.setInt(1, idClaseGenerada);
								pstmtHorario.setString(2, horario.getDiaSemana().name());
								pstmtHorario.setString(3, horario.getTurno().name());
								pstmtHorario.addBatch();
							}
							pstmtHorario.executeBatch();
						}
					}
					return true;
				} else {
					return false;
				}
			}
		}
	}

	// Método para obtener todas las clases con sus horarios
	public List<Clase> obtenerTodasLasClases() {
		ConectionModel conexion = new ConectionModel();
	    List<Clase> clases = new ArrayList<>();
	    String sql = "SELECT c.id_clase, c.nombre_clase, u.id_usuario, u.nombre AS nombre_entrenador, ch.dia_semana, ch.turno " +
	             "FROM clase c " +
	             "JOIN usuario u ON c.id_usuario = u.id_usuario " +
	             "LEFT JOIN clase_horario ch ON ch.id_clase = c.id_clase " +
	             "WHERE u.id_rol = 3";

	    try (Connection conn = conexion.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            Clase clase = new Clase();
	            clase.setIdClase(rs.getInt("id_clase"));
	            clase.setIdUsuario(rs.getInt("id_usuario"));
	            clase.setNombreClase(rs.getString("nombre_clase"));
	            
	            // Obtener horarios
	            clase.setHorarios(obtenerHorariosPorClase(clase.getIdClase()));

	            clases.add(clase);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return clases;
	}
	private List<ClaseHorario> obtenerHorariosPorClase(int idClase) {
		ConectionModel conexion = new ConectionModel();
	    List<ClaseHorario> horarios = new ArrayList<>();
	    String sql = "SELECT * FROM clase_horario WHERE id_clase = ?";

	    try (Connection conn = conexion.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, idClase);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            ClaseHorario horario = new ClaseHorario(
	                rs.getInt("id_clase_horario"),
	                rs.getInt("id_clase"),
	                ClaseHorario.DiaSemana.valueOf(rs.getString("dia_semana").toUpperCase()),
	                ClaseHorario.Turno.valueOf(rs.getString("turno").toUpperCase())
	            );
	            horarios.add(horario);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return horarios;
	}
}
