package com.proyect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleDBConnection {
    private final Connection connection;

    public OracleDBConnection(String connectionString, String username, String password) throws SQLException {
        this.connection = DriverManager.getConnection(connectionString, username, password);
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión a Oracle Database: " + e.getMessage());
        }
    }
}
