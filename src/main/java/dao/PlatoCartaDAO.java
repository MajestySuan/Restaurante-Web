/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author zudex
 */
import model.PlatoCarta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlatoCartaDAO {
    
    public List<PlatoCarta> listarPorCarta(int idCarta) throws SQLException {
        List<PlatoCarta> lista = new ArrayList<>();
        String sql = "SELECT id_carta,id_plato FROM plato_carta WHERE id_carta=?";
        try (Connection cn=ConexionBD.getConnection();
             PreparedStatement ps=cn.prepareStatement(sql)) {
            ps.setInt(1,idCarta);
            try (ResultSet rs=ps.executeQuery()) {
                while(rs.next()) {
                    lista.add(new PlatoCarta(rs.getInt("id_carta"),rs.getInt("id_plato")));
                }
            }
        }
        return lista;
    }
    
    
    
    public void insertar(PlatoCarta pc) throws SQLException {
        String sql = "INSERT INTO plato_carta (id_carta, id_plato) VALUES (?, ?)";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, pc.getIdCarta());
            ps.setInt(2, pc.getIdPlato());
            ps.executeUpdate();
        }
    }

    public void eliminarPorCarta(int idCarta) throws SQLException {
    String sql = "DELETE FROM plato_carta WHERE id_carta = ?";
    try (Connection cn = ConexionBD.getConnection();
         PreparedStatement ps = cn.prepareStatement(sql)) {
        ps.setInt(1, idCarta);
        ps.executeUpdate();
    }
}
}