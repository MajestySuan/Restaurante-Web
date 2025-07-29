/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author zudex
 */
import model.Receta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecetaDAO {
    public List<Receta> listarTodos() throws SQLException {
        List<Receta> lista = new ArrayList<>();
        String sql = "SELECT id_plato, id_ingrediente, cantidad_requerida, descripcion_preparacion FROM receta";
        try (Connection cn = ConexionBD.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Receta(
                    rs.getInt("id_plato"),
                    rs.getInt("id_ingrediente"),
                    rs.getDouble("cantidad_requerida"),
                    rs.getString("descripcion_preparacion")
                ));
            }
        }
        return lista;
    }

    public Receta buscarPorId(int idPlato, int idIngrediente) throws SQLException {
        String sql = "SELECT id_plato, id_ingrediente, cantidad_requerida, descripcion_preparacion FROM receta WHERE id_plato = ? AND id_ingrediente = ?";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idPlato);
            ps.setInt(2, idIngrediente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Receta(
                        rs.getInt("id_plato"),
                        rs.getInt("id_ingrediente"),
                        rs.getDouble("cantidad_requerida"),
                        rs.getString("descripcion_preparacion")
                    );
                }
            }
        }
        return null;
    }

    public void insertar(Receta r) throws SQLException {
        String sql = "INSERT INTO receta (id_plato, id_ingrediente, cantidad_requerida, descripcion_preparacion) VALUES (?, ?, ?, ?)";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, r.getIdPlato());
            ps.setInt(2, r.getIdIngrediente());
            ps.setDouble(3, r.getCantidadRequerida());
            ps.setString(4, r.getDescripcionPreparacion());
            ps.executeUpdate();
        }
    }

    public void actualizar(Receta r) throws SQLException {
        String sql = "UPDATE receta SET cantidad_requerida = ?, descripcion_preparacion = ? WHERE id_plato = ? AND id_ingrediente = ?";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setDouble(1, r.getCantidadRequerida());
            ps.setString(2, r.getDescripcionPreparacion());
            ps.setInt(3, r.getIdPlato());
            ps.setInt(4, r.getIdIngrediente());
            ps.executeUpdate();
        }
    }

    public void eliminar(int idPlato, int idIngrediente) throws SQLException {
        String sql = "DELETE FROM receta WHERE id_plato = ? AND id_ingrediente = ?";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idPlato);
            ps.setInt(2, idIngrediente);
            ps.executeUpdate();
        }
    }
    public void eliminarPorPlato(int idPlato) throws SQLException {
    String sql = "DELETE FROM receta WHERE id_plato = ?";
    try (Connection cn = ConexionBD.getConnection();
         PreparedStatement ps = cn.prepareStatement(sql)) {
        ps.setInt(1, idPlato);
        ps.executeUpdate();
    }
}

// Listar recetas de un plato
public List<Receta> listarPorPlato(int idPlato) throws SQLException {
    List<Receta> lista = new ArrayList<>();
    String sql = "SELECT id_plato, id_ingrediente, cantidad_requerida, descripcion_preparacion FROM receta WHERE id_plato = ?";
    try (Connection cn = ConexionBD.getConnection();
         PreparedStatement ps = cn.prepareStatement(sql)) {
        ps.setInt(1, idPlato);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Receta(
                    rs.getInt("id_plato"),
                    rs.getInt("id_ingrediente"),
                    rs.getDouble("cantidad_requerida"),
                    rs.getString("descripcion_preparacion")
                ));
            }
        }
    }
    return lista;
}
}