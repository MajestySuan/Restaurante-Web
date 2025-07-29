/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author zudex
 */
import model.Turno;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TurnoDAO {
    public List<Turno> listarTodos() throws SQLException {
        List<Turno> lista = new ArrayList<>();
        String sql = "SELECT id_turno, nombre_turno FROM turno";
        try (Connection cn = ConexionBD.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Turno(
                    rs.getInt("id_turno"),
                    rs.getString("nombre_turno")
                ));
            }
        }
        return lista;
    }

    public Turno buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_turno, nombre_turno FROM turno WHERE id_turno = ?";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Turno(
                        rs.getInt("id_turno"),
                        rs.getString("nombre_turno")
                    );
                }
            }
        }
        return null;
    }

    public void insertar(Turno t) throws SQLException {
        String sql = "INSERT INTO turno (nombre_turno) VALUES (?)";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, t.getNombreTurno());
            ps.executeUpdate();
        }
    }

    public void actualizar(Turno t) throws SQLException {
        String sql = "UPDATE turno SET nombre_turno = ? WHERE id_turno = ?";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, t.getNombreTurno());
            ps.setInt(2, t.getIdTurno());
            ps.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM turno WHERE id_turno = ?";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
    public List<Turno> listarPorPlato(int idPlato) throws SQLException {
    List<Turno> lista = new ArrayList<>();
    String sql = "SELECT t.id_turno, t.nombre_turno " +
                 "FROM turno t " +
                 "JOIN plato_turno pt ON t.id_turno = pt.id_turno " +
                 "WHERE pt.id_plato = ?";
    try (Connection cn = ConexionBD.getConnection();
         PreparedStatement ps = cn.prepareStatement(sql)) {
        ps.setInt(1, idPlato);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Turno(
                    rs.getInt("id_turno"),
                    rs.getString("nombre_turno")
                ));
            }
        }
    }
    return lista;
}
}