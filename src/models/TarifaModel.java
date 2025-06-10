package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TarifaModel {

	public boolean insertarTarifa(Tarifa tarifa) {
		if (tarifa.getPrecio() < 0) {
	        System.err.println("El precio no puede ser negativo.");
	        return false;
	    }
		
		ConectionModel conexion = new ConectionModel();
		String sql = "INSERT INTO tarifa (nombre_tarifa, descripcion, precio) VALUES (?, ?, ?)";

		try {
			Connection conn = conexion.getConnection();
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, tarifa.getNombreTarifa().toLowerCase());
				pstmt.setString(2, tarifa.getDescripcion());
				pstmt.setInt(3, tarifa.getPrecio());

				return pstmt.executeUpdate() > 0;
			}
		} catch (SQLException e) {
			System.err.println("Error al insertar tarifa: " + e.getMessage());
			return false;
		} finally {
			conexion.close();
		}
	}

	public List<Tarifa> obtenerTodas() {	
		List<Tarifa> tarifas = new ArrayList<>();
		ConectionModel conexion = new ConectionModel();
		String sql = "SELECT * FROM tarifa";

		try {
			Connection conn = conexion.getConnection();
			try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					tarifas.add(new Tarifa(rs.getString("nombre_tarifa"),
							rs.getString("descripcion"), rs.getInt("precio")));
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al obtener tarifas: " + e.getMessage());
		} finally {
			conexion.close();
		}

		return tarifas;
	}
	public boolean eliminarTarifa(String nombre, int precio, String descripcion) {
	    ConectionModel conexion = new ConectionModel();

	    try (Connection conn = conexion.getConnection()) {
	        // Paso 1: obtener ID
	        Integer id = obtenerIdTarifaPorDatos(nombre, precio, descripcion);
	        if (id == null) {
	            System.err.println("Tarifa no encontrada.");
	            return false;
	        }

	        // Paso 2: eliminar relaciones en usuario_tarifa
	        String sqlUsuarioTarifa = "DELETE FROM usuario_tarifa WHERE id_tarifa = ?";
	        try (PreparedStatement stmt1 = conn.prepareStatement(sqlUsuarioTarifa)) {
	            stmt1.setInt(1, id);
	            stmt1.executeUpdate();
	        }

	        // Paso 3: eliminar tarifa
	        String sqlTarifa = "DELETE FROM tarifa WHERE id_tarifa = ?";
	        try (PreparedStatement stmt2 = conn.prepareStatement(sqlTarifa)) {
	            stmt2.setInt(1, id);
	            int filas = stmt2.executeUpdate();
	            return filas > 0;
	        }

	    } catch (SQLException e) {
	        System.err.println("Error al eliminar tarifa: " + e.getMessage());
	        return false;

	    } finally {
	        conexion.close();
	    }
	}
	private Integer obtenerIdTarifaPorDatos(String nombre, int precio, String descripcion) {
	    String sql = "SELECT id_tarifa FROM tarifa WHERE nombre_tarifa = ? AND precio = ? AND descripcion = ?";
	    try (Connection conn = new ConectionModel().getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, nombre);
	        stmt.setInt(2, precio);
	        stmt.setString(3, descripcion);

	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getInt("id_tarifa");
	        }

	    } catch (SQLException e) {
	        System.err.println("Error al obtener ID de tarifa: " + e.getMessage());
	    }
	    return null;
	}
	public boolean actualizarTarifa(String nombreOriginal, Tarifa nueva) {
		
		if (nueva.getPrecio() < 0) {
	        System.err.println("El precio no puede ser negativo.");
	        return false;
	    }
	    String sql = "UPDATE tarifa SET nombre_tarifa = ?, descripcion = ?, precio = ? WHERE nombre_tarifa = ?";

	    try (Connection conn = new ConectionModel().getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, nueva.getNombreTarifa());
	        pstmt.setString(2, nueva.getDescripcion());
	        pstmt.setInt(3, nueva.getPrecio());
	        pstmt.setString(4, nombreOriginal);

	        return pstmt.executeUpdate() > 0;

	    } catch (SQLException e) {
	        System.err.println("Error al actualizar tarifa: " + e.getMessage());
	        return false;
	    }
	}
	
	
}
