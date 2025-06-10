package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthModel {

    public AuthModel() {}

    public boolean login(String email, String password) {
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            return false;
        }

        ConectionModel conexion = new ConectionModel();

        try {
            String sql = "SELECT contraseña FROM usuario WHERE correo = ?";
            Connection conn = conexion.getConnection();

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, email.trim());

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        String storedHash = rs.getString("contraseña");
                        String enteredHash = PasswordHasher.hashPassword(password.trim());
                        return storedHash.equals(enteredHash);
                    } else {
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en login: " + e.getMessage());
            return false;
        } finally {
            conexion.close();
        }
    }

    public boolean registrarAdmin(String email, String password) {
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            System.err.println("Error: Email y password son obligatorios");
            return false;
        }

        ConectionModel conexion = new ConectionModel();

        try {
            if (correoExiste(conexion.getConnection(), email)) {
                System.err.println("Error: El correo ya está registrado");
                return false;
            }

            String hashedPassword = PasswordHasher.hashPassword(password.trim());

            String sql = "INSERT INTO usuario (correo, contraseña, id_rol) VALUES (?, ?, 1)";

            try (PreparedStatement pstmt = conexion.getConnection().prepareStatement(sql)) {
                pstmt.setString(1, email.trim());
                pstmt.setString(2, hashedPassword);

                int affectedRows = pstmt.executeUpdate();
                return affectedRows > 0;
            }

        } catch (SQLException e) {
            System.err.println("Error al registrar admin: " + e.getMessage());
            return false;
        } finally {
            conexion.close();
        }
    }

    private boolean correoExiste(Connection conn, String email) throws SQLException {
        String sql = "SELECT 1 FROM usuario WHERE correo = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email.trim());

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }
}
