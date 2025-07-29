/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Stock;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockDAO {

    // Listar todos los movimientos de stock
    public List<Stock> listarTodos() throws SQLException {
        List<Stock> lista = new ArrayList<>();
        String sql = "SELECT id_stock, id_ingrediente, cantidad, fecha_registro FROM stock";
        try (Connection cn = ConexionBD.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Stock(
                    rs.getInt("id_stock"),
                    rs.getInt("id_ingrediente"),
                    rs.getDouble("cantidad"),
                    rs.getTimestamp("fecha_registro").toLocalDateTime()
                ));
            }
        }
        return lista;
    }

    // Buscar un movimiento de stock por ID
    public Stock buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_stock, id_ingrediente, cantidad, fecha_registro FROM stock WHERE id_stock = ?";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Stock(
                        rs.getInt("id_stock"),
                        rs.getInt("id_ingrediente"),
                        rs.getDouble("cantidad"),
                        rs.getTimestamp("fecha_registro").toLocalDateTime()
                    );
                }
            }
        }
        return null;
    }

    // Insertar movimiento de stock (positivo: entrada, negativo: salida)
    public void insertar(Stock s) throws SQLException {
        String sql = "INSERT INTO stock (id_ingrediente, cantidad, fecha_registro) VALUES (?, ?, ?)";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, s.getIdIngrediente());
            ps.setDouble(2, s.getCantidad());
            ps.setTimestamp(3, Timestamp.valueOf(s.getFechaRegistro()));
            ps.executeUpdate();
        }
    }

    // Actualizar un movimiento de stock existente
    public void actualizar(Stock s) throws SQLException {
        String sql = "UPDATE stock SET id_ingrediente = ?, cantidad = ?, fecha_registro = ? WHERE id_stock = ?";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, s.getIdIngrediente());
            ps.setDouble(2, s.getCantidad());
            ps.setTimestamp(3, Timestamp.valueOf(s.getFechaRegistro()));
            ps.setInt(4, s.getIdStock());
            ps.executeUpdate();
        }
    }

    // Eliminar un movimiento de stock por ID
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM stock WHERE id_stock = ?";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // Obtener la cantidad actual disponible de un ingrediente
    public double obtenerCantidadActual(int idIngrediente) throws SQLException {
        String sql = "SELECT COALESCE(SUM(cantidad),0) AS cant FROM stock WHERE id_ingrediente=?";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idIngrediente);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getDouble("cant") : 0;
            }
        }
    }
    
}
