package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstrucoresModel {

	private List<Instructor> instructores = new ArrayList<>();

	public InstrucoresModel() {

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
	public Map<String, String> obtenerDatosCompletosInstructor(int idInstructor) {
	    Map<String, String> datosInstructor = new HashMap<>();
	    ConectionModel conexion = new ConectionModel();

	    String sql = "SELECT u.id_usuario, u.nombre, u.especialidad, u.telefono, u.correo, " +
                "GROUP_CONCAT(DISTINCT CONCAT(ch.dia_semana, ' - ', ch.turno) SEPARATOR '; ') AS horarios, " +
                "GROUP_CONCAT(DISTINCT c.nombre_clase SEPARATOR ', ') AS clases " +
                "FROM usuario u " +
                "LEFT JOIN clase c ON u.id_usuario = c.id_usuario " +  
                "LEFT JOIN clase_horario ch ON c.id_clase = ch.id_clase " +  
                "WHERE u.id_usuario = ? AND u.id_rol = 3 " +
                "GROUP BY u.id_usuario";

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
}
