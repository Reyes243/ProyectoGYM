package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TarifaModel {

	public boolean insertarTarifa(Tarifa tarifa) {
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
		ConectionModel conexion = new ConectionModel();
		List<Tarifa> tarifas = new ArrayList<>();
		String sql = "SELECT * FROM tarifa";

		try {
			Connection conn = conexion.getConnection();
			try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					tarifas.add(new Tarifa(rs.getInt("id_tarifa"), rs.getString("nombre_tarifa"),
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
}
