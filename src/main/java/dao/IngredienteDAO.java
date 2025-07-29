/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author zudex
 */
import model.Ingrediente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredienteDAO {
    public List<Ingrediente> listarTodos() throws SQLException {
        List<Ingrediente> lista = new ArrayList<>();
        String sql = "SELECT id_ingrediente, nombre_ingrediente, unidad_medida FROM ingrediente";
        try (Connection cn = ConexionBD.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Ingrediente(
                    rs.getInt("id_ingrediente"),
                    rs.getString("nombre_ingrediente"),
                    rs.getString("unidad_medida")
                ));
            }
        }
        return lista;
    }

    public Ingrediente buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_ingrediente, nombre_ingrediente, unidad_medida FROM ingrediente WHERE id_ingrediente = ?";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Ingrediente(
                        rs.getInt("id_ingrediente"),
                        rs.getString("nombre_ingrediente"),
                        rs.getString("unidad_medida")
                    );
                }
            }
        }
        return null;
    }

    public void insertar(Ingrediente i) throws SQLException {
        String sql = "INSERT INTO ingrediente (nombre_ingrediente, unidad_medida) VALUES (?, ?)";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, i.getNombreIngrediente());
            ps.setString(2, i.getUnidadMedida());
            ps.executeUpdate();
        }
    }

    public void actualizar(Ingrediente i) throws SQLException {
        String sql = "UPDATE ingrediente SET nombre_ingrediente = ?, unidad_medida = ? WHERE id_ingrediente = ?";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, i.getNombreIngrediente());
            ps.setString(2, i.getUnidadMedida());
            ps.setInt(3, i.getIdIngrediente());
            ps.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM ingrediente WHERE id_ingrediente = ?";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
    public boolean estaEnUso(int idIngrediente) throws SQLException {
    // Verifica si el ingrediente está en alguna receta
    String sqlReceta = "SELECT 1 FROM receta WHERE id_ingrediente = ? LIMIT 1";
    // Verifica si el ingrediente tiene movimientos de stock
    String sqlStock = "SELECT 1 FROM stock WHERE id_ingrediente = ? LIMIT 1";
    try (Connection cn = ConexionBD.getConnection()) {
        // Verificar en receta
        try (PreparedStatement ps = cn.prepareStatement(sqlReceta)) {
            ps.setInt(1, idIngrediente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        }
        // Verificar en stock
        try (PreparedStatement ps = cn.prepareStatement(sqlStock)) {
            ps.setInt(1, idIngrediente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        }
    }
    // No está en uso
    return false;
}

}