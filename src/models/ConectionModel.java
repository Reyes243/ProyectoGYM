package models;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConectionModel {
	
	private Connection conn;

	public ConectionModel() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_ProyectoGYM",
					"freedb_CarlosLuquin", "sXYz68y3@ts6$@E");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return conn;
	}

	public void close() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
