/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author zudex
 */
import model.PlatoTurno;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlatoTurnoDAO {
    public List<PlatoTurno> listarTodos() throws SQLException {
        List<PlatoTurno> lista = new ArrayList<>();
        String sql = "SELECT id_plato, id_turno FROM plato_turno";
        try (Connection cn = ConexionBD.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new PlatoTurno(
                    rs.getInt("id_plato"),
                    rs.getInt("id_turno")
                ));
            }
        }
        return lista;
    }

    public PlatoTurno buscarPorId(int idPlato, int idTurno) throws SQLException {
        String sql = "SELECT id_plato, id_turno FROM plato_turno WHERE id_plato = ? AND id_turno = ?";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idPlato);
            ps.setInt(2, idTurno);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new PlatoTurno(rs.getInt("id_plato"), rs.getInt("id_turno"));
                }
            }
        }
        return null;
    }

    public void insertar(PlatoTurno pt) throws SQLException {
        String sql = "INSERT INTO plato_turno (id_plato, id_turno) VALUES (?, ?)";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, pt.getIdPlato());
            ps.setInt(2, pt.getIdTurno());
            ps.executeUpdate();
        }
    }

    public void eliminar(int idPlato, int idTurno) throws SQLException {
        String sql = "DELETE FROM plato_turno WHERE id_plato = ? AND id_turno = ?";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idPlato);
            ps.setInt(2, idTurno);
            ps.executeUpdate();
        }
    }
    public void eliminarPorPlato(int idPlato) throws SQLException {
    String sql = "DELETE FROM plato_turno WHERE id_plato = ?";
    try (Connection cn = ConexionBD.getConnection();
         PreparedStatement ps = cn.prepareStatement(sql)) {
        ps.setInt(1, idPlato);
        ps.executeUpdate();
    }
}

// Listar turnos asociados a un plato
public List<PlatoTurno> listarPorPlato(int idPlato) throws SQLException {
    List<PlatoTurno> lista = new ArrayList<>();
    String sql = "SELECT id_plato, id_turno FROM plato_turno WHERE id_plato = ?";
    try (Connection cn = ConexionBD.getConnection();
         PreparedStatement ps = cn.prepareStatement(sql)) {
        ps.setInt(1, idPlato);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new PlatoTurno(rs.getInt("id_plato"), rs.getInt("id_turno")));
            }
        }
    }
    return lista;
}
}