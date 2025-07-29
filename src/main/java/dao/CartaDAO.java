/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author zudex
 */
import model.Carta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartaDAO {

    public boolean existePlatoEnCartaActiva(int idPlato) throws SQLException {
    String sql = "SELECT 1 FROM plato_carta pc "
               + "JOIN carta c ON pc.id_carta = c.id_carta "
               + "WHERE pc.id_plato = ? AND c.activa = TRUE "
               + "LIMIT 1";
    try (Connection cn = ConexionBD.getConnection();
         PreparedStatement ps = cn.prepareStatement(sql)) {
        ps.setInt(1, idPlato);
        try (ResultSet rs = ps.executeQuery()) {
            return rs.next();
        }
    }
}
    public List<Carta> listarCartasVigentesYActivas() throws SQLException {
        List<Carta> lista = new ArrayList<>();
        String sql = "SELECT id_carta, nombre_carta, fecha_inicio_vigencia, fecha_fin_vigencia, activa " +
                     "FROM carta WHERE activa = TRUE AND CURRENT_DATE BETWEEN fecha_inicio_vigencia AND fecha_fin_vigencia";
        try (Connection cn = ConexionBD.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Carta c = new Carta();
                c.setIdCarta(rs.getInt("id_carta"));
                c.setNombreCarta(rs.getString("nombre_carta"));
                c.setFechaInicioVigencia(rs.getDate("fecha_inicio_vigencia").toLocalDate());
                c.setFechaFinVigencia(rs.getDate("fecha_fin_vigencia").toLocalDate());
                c.setActiva(rs.getBoolean("activa"));
                lista.add(c);
            }
        }
        System.out.println("DEBUG [CartaDAO] cartas vigentes encontradas: " + lista.size());
for (Carta c : lista) {
    System.out.println("DEBUG [CartaDAO] -> " + c);
}
        return lista;
    }

    // Verificar si un plato está en alguna carta activa y vigente para una fecha dada
    public List<Carta> cartasDisponiblesParaPlato(int idPlato, String fechaVenta) throws SQLException {
        List<Carta> lista = new ArrayList<>();
        String sql = "SELECT c.id_carta, c.nombre_carta, c.fecha_inicio_vigencia, c.fecha_fin_vigencia, c.activa " +
                     "FROM carta c " +
                     "JOIN plato_carta pc ON c.id_carta = pc.id_carta " +
                     "WHERE pc.id_plato = ? " +
                     "AND c.activa = TRUE " +
                     "AND ? BETWEEN c.fecha_inicio_vigencia AND c.fecha_fin_vigencia";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idPlato);
            ps.setDate(2, Date.valueOf(fechaVenta));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Carta c = new Carta();
                    c.setIdCarta(rs.getInt("id_carta"));
                    c.setNombreCarta(rs.getString("nombre_carta"));
                    c.setFechaInicioVigencia(rs.getDate("fecha_inicio_vigencia").toLocalDate());
                    c.setFechaFinVigencia(rs.getDate("fecha_fin_vigencia").toLocalDate());
                    c.setActiva(rs.getBoolean("activa"));
                    lista.add(c);
                }
            }
        }
        return lista;
    }

    // Verificar si una carta está vigente para una fecha dada
    public boolean cartaVigenteParaFecha(int idCarta, String fecha) throws SQLException {
        String sql = "SELECT 1 FROM carta WHERE id_carta = ? AND ? BETWEEN fecha_inicio_vigencia AND fecha_fin_vigencia AND activa = TRUE";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idCarta);
            ps.setDate(2, Date.valueOf(fecha));
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    // Verificar si una carta contiene un plato
    public boolean cartaContienePlato(int idCarta, int idPlato) throws SQLException {
        String sql = "SELECT 1 FROM plato_carta WHERE id_carta = ? AND id_plato = ?";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idCarta);
            ps.setInt(2, idPlato);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
    
    public List<Carta> listarTodos() throws SQLException {
        List<Carta> lista = new ArrayList<>();
        String sql = "SELECT id_carta, nombre_carta, fecha_inicio_vigencia, fecha_fin_vigencia, activa FROM carta";
        try (Connection cn = ConexionBD.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Carta(
                    rs.getInt("id_carta"),
                    rs.getString("nombre_carta"),
                    rs.getDate("fecha_inicio_vigencia").toLocalDate(),
                    rs.getDate("fecha_fin_vigencia").toLocalDate(),
                    rs.getBoolean("activa")
                ));
            }
        }
        return lista;
    }

    public Carta buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_carta, nombre_carta, fecha_inicio_vigencia, fecha_fin_vigencia, activa FROM carta WHERE id_carta = ?";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Carta(
                        rs.getInt("id_carta"),
                        rs.getString("nombre_carta"),
                        rs.getDate("fecha_inicio_vigencia").toLocalDate(),
                        rs.getDate("fecha_fin_vigencia").toLocalDate(),
                        rs.getBoolean("activa")
                    );
                }
            }
        }
        return null;
    }

    public void insertar(Carta c) throws SQLException {
        String sql = "INSERT INTO carta (nombre_carta, fecha_inicio_vigencia, fecha_fin_vigencia, activa) VALUES (?, ?, ?, ?)";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, c.getNombreCarta());
            ps.setDate(2, Date.valueOf(c.getFechaInicioVigencia()));
            ps.setDate(3, Date.valueOf(c.getFechaFinVigencia()));
            ps.setBoolean(4, c.isActiva());
            ps.executeUpdate();
        }
    }

    public void actualizar(Carta c) throws SQLException {
        String sql = "UPDATE carta SET nombre_carta = ?, fecha_inicio_vigencia = ?, fecha_fin_vigencia = ?, activa = ? WHERE id_carta = ?";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, c.getNombreCarta());
            ps.setDate(2, Date.valueOf(c.getFechaInicioVigencia()));
            ps.setDate(3, Date.valueOf(c.getFechaFinVigencia()));
            ps.setBoolean(4, c.isActiva());
            ps.setInt(5, c.getIdCarta());
            ps.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM carta WHERE id_carta = ?";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
    public List<Carta> cartasDisponiblesParaCarta(int idPlato, String fechaVenta) throws SQLException {
    List<Carta> lista = new ArrayList<>();
    String sql =
        "SELECT c.id_carta, c.nombre_carta, c.fecha_inicio_vigencia, c.fecha_fin_vigencia, c.activa " +
        "FROM carta c " +
        "JOIN plato_carta pc ON c.id_carta = pc.id_carta " +
        "WHERE pc.id_plato = ? " +
        "  AND c.activa = TRUE " +
        "  AND ? BETWEEN c.fecha_inicio_vigencia AND c.fecha_fin_vigencia";
    try (Connection cn = ConexionBD.getConnection();
         PreparedStatement ps = cn.prepareStatement(sql)) {
        ps.setInt(1, idPlato);
        ps.setDate(2, Date.valueOf(fechaVenta));
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Carta c = new Carta();
                c.setIdCarta(rs.getInt("id_carta"));
                c.setNombreCarta(rs.getString("nombre_carta"));
                c.setFechaInicioVigencia(rs.getDate("fecha_inicio_vigencia").toLocalDate());
                c.setFechaFinVigencia(rs.getDate("fecha_fin_vigencia").toLocalDate());
                c.setActiva(rs.getBoolean("activa"));
                lista.add(c);
            }
        }
    }
    return lista;
}
}