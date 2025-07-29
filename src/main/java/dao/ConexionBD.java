/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author zudex
 */
public class ConexionBD {
    private static final String URL = "jdbc:postgresql://localhost:5432/restaurante";
    private static final String USER = "postgres";
    private static final String PASS = "admin";

    public static Connection getConnection() throws SQLException {
         try {
            // Cargar explícitamente el driver
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver PostgreSQL no encontrado", e);
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
