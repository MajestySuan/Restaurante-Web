/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author zudex
 */
import model.Region;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegionDAO {
    public List<Region> listarTodos() throws SQLException {
        List<Region> lista = new ArrayList<>();
        String sql = "SELECT id_region, nombre_region FROM region";
        try (Connection cn = ConexionBD.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Region(
                    rs.getInt("id_region"),
                    rs.getString("nombre_region")
                ));
            }
        }
        return lista;
    }

    public Region buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_region, nombre_region FROM region WHERE id_region = ?";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Region(
                        rs.getInt("id_region"),
                        rs.getString("nombre_region")
                    );
                }
            }
        }
        return null;
    }

    public void insertar(Region r) throws SQLException {
        String sql = "INSERT INTO region (nombre_region) VALUES (?)";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, r.getNombreRegion());
            ps.executeUpdate();
        }
    }

    public void actualizar(Region r) throws SQLException {
        String sql = "UPDATE region SET nombre_region = ? WHERE id_region = ?";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, r.getNombreRegion());
            ps.setInt(2, r.getIdRegion());
            ps.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM region WHERE id_region = ?";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}