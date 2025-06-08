package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClaseModel {
	private Connection connection;

	public ClaseModel(Connection connection) {
	    this.connection = connection;
	}
	
	private String normalizarTexto(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
                .toUpperCase();
    }

    // 👇 Y luego tu método corregido
    public List<ClaseHorario> obtenerHorariosPorClase(int idClase) {
        List<ClaseHorario> horarios = new ArrayList<>();
        String sql = "SELECT * FROM clase_horario WHERE id_clase = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idClase);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int idHorario = rs.getInt("id_clase_horario");
                String diaRaw = rs.getString("dia_semana");
                String turnoRaw = rs.getString("turno");

                // 👇 Normalizamos para que coincida con el enum
                String diaNormalizado = normalizarTexto(diaRaw);
                String turnoNormalizado = normalizarTexto(turnoRaw);

                ClaseHorario.DiaSemana dia = ClaseHorario.DiaSemana.valueOf(diaNormalizado);
                ClaseHorario.Turno turno = ClaseHorario.Turno.valueOf(turnoNormalizado);

                ClaseHorario horario = new ClaseHorario(idHorario, idClase, dia, turno);
                horarios.add(horario);
            }
        } catch (SQLException | IllegalArgumentException e) {
            System.err.println("Error al obtener horarios: " + e.getMessage());
            e.printStackTrace();
        }

        return horarios;
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
	    	    clase.setNombreEntrenador(rs.getString("nombre_entrenador"));

	    	    List<ClaseHorario> horarios = obtenerHorariosPorClase(clase.getIdClase());
	    	    clase.setHorarios(horarios);

	    	    if (!horarios.isEmpty()) {
	    	        clase.setTurno(horarios.get(0).getTurno().name()); // ← Setea el turno visible
	    	    }

	    	    clases.add(clase);
	    	}


	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return clases;
	}
	
	
	public int inscribirUsuarioAClase(int idInscripcion, int idUsuario, int idClase) {
	    String verificarSql = "SELECT COUNT(*) FROM inscripcion WHERE id_usuario = ? AND id_clase = ?";
	    String insertarSql = "INSERT INTO inscripcion (id_inscripcion, id_usuario, id_clase) VALUES (?, ?, ?)";

	    try {
	        try (PreparedStatement verificarStmt = connection.prepareStatement(verificarSql)) {
	            verificarStmt.setInt(1, idUsuario);
	            verificarStmt.setInt(2, idClase);
	            ResultSet rs = verificarStmt.executeQuery();
	            if (rs.next() && rs.getInt(1) > 0) {
	                // Ya inscrito
	                return 0;
	            }
	        }

	        // Insertar inscripción
	        try (PreparedStatement insertStmt = connection.prepareStatement(insertarSql)) {
	            insertStmt.setInt(1, idInscripcion);
	            insertStmt.setInt(2, idUsuario);
	            insertStmt.setInt(3, idClase);
	            int filas = insertStmt.executeUpdate();
	            return filas > 0 ? 1 : -1;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return -2;
	    }
	}
	
	public Clase obtenerClasePorId(int idClase) {
	    Clase clase = null;
	    String sql = "SELECT c.id_clase, c.nombre_clase, c.id_usuario, u.nombre AS nombre_entrenador " +
	                 "FROM clase c " +
	                 "JOIN usuario u ON c.id_usuario = u.id_usuario " +
	                 "WHERE c.id_clase = ?";

	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setInt(1, idClase);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            clase = new Clase();
	            clase.setIdClase(rs.getInt("id_clase"));
	            clase.setNombreClase(rs.getString("nombre_clase"));
	            clase.setIdUsuario(rs.getInt("id_usuario"));
	            clase.setNombreEntrenador(rs.getString("nombre_entrenador"));

	            List<ClaseHorario> horarios = obtenerHorariosPorClase(idClase);
	            clase.setHorarios(horarios);

	            if (!horarios.isEmpty()) {
	                clase.setTurno(horarios.get(0).getTurno().name()); // Convertir enum a String
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return clase;
	}

	public boolean actualizarClase(int idClase, String nuevoNombre, int idEntrenador, String turno, String diaSemana) {
	    try {
	        // 1. Actualizar nombre e instructor
	        String updateClase = "UPDATE clase SET nombre_clase = ?, id_usuario = ? WHERE id_clase = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(updateClase)) {
	            stmt.setString(1, nuevoNombre);
	            stmt.setInt(2, idEntrenador);
	            stmt.setInt(3, idClase);
	            stmt.executeUpdate();
	        }

	        // 2. Borrar horarios anteriores
	        String deleteHorarios = "DELETE FROM clase_horario WHERE id_clase = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(deleteHorarios)) {
	            stmt.setInt(1, idClase);
	            stmt.executeUpdate();
	        }

	        // 3. Insertar nuevo horario
	        String insertHorario = "INSERT INTO clase_horario (id_clase, dia_semana, turno) VALUES (?, ?, ?)";
	        try (PreparedStatement stmt = connection.prepareStatement(insertHorario)) {
	            stmt.setInt(1, idClase);
	            stmt.setString(2, diaSemana.toUpperCase());
	            stmt.setString(3, turno.toUpperCase());
	            stmt.executeUpdate();
	        }

	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public String getDiasPorClase(int idClase) {
	    List<ClaseHorario> horarios = obtenerHorariosPorClase(idClase);
	    return horarios.stream()
	        .map(h -> h.getDiaSemana().name())
	        .collect(Collectors.joining(", "));
	}
	
	public int insertarClase(String nombre, int idEntrenador) throws SQLException {
		String sql = "INSERT INTO clase (nombre_clase, id_usuario) VALUES (?, ?)";
		PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, nombre.toUpperCase());
		stmt.setInt(2, idEntrenador);
		stmt.executeUpdate();

		ResultSet rs = stmt.getGeneratedKeys();
		if (rs.next()) {
			return rs.getInt(1); // ID generado
		}
		return -1;
	}

	public void insertarHorario(int idClase, String dia, String turno) throws SQLException {
		String sql = "INSERT INTO clase_horario (id_clase, dia_semana, turno) VALUES (?, ?, ?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, idClase);
		stmt.setString(2, normalizarTexto(dia));   // ← usa el método para quitar tildes
		stmt.setString(3, normalizarTexto(turno));
		stmt.executeUpdate();
	}
	
	public boolean eliminarClase(int idClase) throws SQLException {
	    Connection conn = connection;

	    // Paso 1: eliminar horarios relacionados
	    PreparedStatement ps1 = conn.prepareStatement("DELETE FROM clase_horario WHERE id_clase = ?");
	    ps1.setInt(1, idClase);
	    ps1.executeUpdate();
	    ps1.close();

	    // Paso 2: eliminar inscripciones (si también están relacionadas)
	    PreparedStatement ps2 = conn.prepareStatement("DELETE FROM inscripcion WHERE id_clase = ?");
	    ps2.setInt(1, idClase);
	    ps2.executeUpdate();
	    ps2.close();

	    // Paso 3: eliminar la clase
	    PreparedStatement ps3 = conn.prepareStatement("DELETE FROM clase WHERE id_clase = ?");
	    ps3.setInt(1, idClase);
	    int rows = ps3.executeUpdate();
	    ps3.close();

	    return rows > 0;
	}

	
}


