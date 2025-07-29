/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Venta;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VentaDAO {
    
    // Listar todas las ventas
    public List<Venta> listarTodos() throws SQLException {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT id_venta, fecha_venta, cantidad, precio_u_v, " +
                    "id_plato, id_turno, id_carta, id_empleado FROM venta " +
                    "ORDER BY fecha_venta DESC";
        
        try (Connection cn = ConexionBD.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                lista.add(new Venta(
                    rs.getInt("id_venta"),
                    rs.getDate("fecha_venta").toLocalDate(),
                    rs.getInt("cantidad"),
                    rs.getBigDecimal("precio_u_v"),
                    rs.getInt("id_plato"),
                    rs.getInt("id_turno"),
                    rs.getInt("id_carta"),
                    rs.getInt("id_empleado")
                ));
            }
        }
        return lista;
    }

    
    // Buscar venta por ID
    public Venta buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_venta, fecha_venta, cantidad, precio_u_v, " +
                    "id_plato, id_turno, id_carta, id_empleado FROM venta " +
                    "WHERE id_venta = ?";
        
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Venta(
                        rs.getInt("id_venta"),
                        rs.getDate("fecha_venta").toLocalDate(),
                        rs.getInt("cantidad"),
                        rs.getBigDecimal("precio_u_v"),
                        rs.getInt("id_plato"),
                        rs.getInt("id_turno"),
                        rs.getInt("id_carta"),
                        rs.getInt("id_empleado")
                    );
                }
            }
        }
        return null;
    }

    // Insertar nueva venta
    public void insertar(Venta venta) throws SQLException {
        String sql = "INSERT INTO venta (fecha_venta, cantidad, precio_u_v, " +
                    "id_plato, id_turno, id_carta, id_empleado) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            
            ps.setDate(1, Date.valueOf(venta.getFechaVenta()));
            ps.setInt(2, venta.getCantidad());
            ps.setBigDecimal(3, venta.getPrecioUnitario());
            ps.setInt(4, venta.getIdPlato());
            ps.setInt(5, venta.getIdTurno());
            ps.setInt(6, venta.getIdCarta());
            ps.setInt(7, venta.getIdEmpleado());
            
            ps.executeUpdate();
        }
    }

    // Actualizar venta existente
    public void actualizar(Venta venta) throws SQLException {
        String sql = "UPDATE venta SET fecha_venta = ?, cantidad = ?, precio_u_v = ?, " +
                    "id_plato = ?, id_turno = ?, id_carta = ?, id_empleado = ? " +
                    "WHERE id_venta = ?";
        
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            
            ps.setDate(1, Date.valueOf(venta.getFechaVenta()));
            ps.setInt(2, venta.getCantidad());
            ps.setBigDecimal(3, venta.getPrecioUnitario());
            ps.setInt(4, venta.getIdPlato());
            ps.setInt(5, venta.getIdTurno());
            ps.setInt(6, venta.getIdCarta());
            ps.setInt(7, venta.getIdEmpleado());
            ps.setInt(8, venta.getIdVenta());
            
            ps.executeUpdate();
        }
    }

    // Eliminar venta
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM venta WHERE id_venta = ?";
        
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
    
    public List<Map<String, Object>> listarFiltrado(
    String fechaInicio, String fechaFin,
    Integer idPlato, Integer idEmpleado, Integer idTurno
) throws SQLException {
    List<Map<String, Object>> lista = new ArrayList<>();
    String sql = "SELECT v.id_venta, v.fecha_venta, v.cantidad, v.precio_u_v, " +
                 "p.nombre_plato, e.nombre_empleado, t.nombre_turno, c.nombre_carta " +
                 "FROM venta v " +
                 "JOIN plato p ON v.id_plato = p.id_plato " +
                 "JOIN empleado e ON v.id_empleado = e.id_empleado " +
                 "JOIN turno t ON v.id_turno = t.id_turno " +
                 "JOIN carta c ON v.id_carta = c.id_carta " +
                 "WHERE (? IS NULL OR v.fecha_venta >= ?) " +
                 "AND (? IS NULL OR v.fecha_venta <= ?) " +
                 "AND (? IS NULL OR v.id_plato = ?) " +
                 "AND (? IS NULL OR v.id_empleado = ?) " +
                 "AND (? IS NULL OR v.id_turno = ?) " +
                 "ORDER BY v.fecha_venta DESC";
    try (Connection cn = ConexionBD.getConnection();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        // Fecha inicio
        if (fechaInicio != null && !fechaInicio.isEmpty()) {
            java.sql.Date fi = java.sql.Date.valueOf(fechaInicio);
            ps.setDate(1, fi);
            ps.setDate(2, fi);
        } else {
            ps.setNull(1, java.sql.Types.DATE);
            ps.setNull(2, java.sql.Types.DATE);
        }
        // Fecha fin
        if (fechaFin != null && !fechaFin.isEmpty()) {
            java.sql.Date ff = java.sql.Date.valueOf(fechaFin);
            ps.setDate(3, ff);
            ps.setDate(4, ff);
        } else {
            ps.setNull(3, java.sql.Types.DATE);
            ps.setNull(4, java.sql.Types.DATE);
        }
        // Plato
        if (idPlato != null) {
            ps.setInt(5, idPlato);
            ps.setInt(6, idPlato);
        } else {
            ps.setNull(5, java.sql.Types.INTEGER);
            ps.setNull(6, java.sql.Types.INTEGER);
        }
        // Empleado
        if (idEmpleado != null) {
            ps.setInt(7, idEmpleado);
            ps.setInt(8, idEmpleado);
        } else {
            ps.setNull(7, java.sql.Types.INTEGER);
            ps.setNull(8, java.sql.Types.INTEGER);
        }
        // Turno
        if (idTurno != null) {
            ps.setInt(9, idTurno);
            ps.setInt(10, idTurno);
        } else {
            ps.setNull(9, java.sql.Types.INTEGER);
            ps.setNull(10, java.sql.Types.INTEGER);
        }

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> fila = new HashMap<>();
                fila.put("idVenta", rs.getInt("id_venta"));
                fila.put("fechaVenta", rs.getDate("fecha_venta"));
                fila.put("cantidad", rs.getInt("cantidad"));
                fila.put("precioUV", rs.getBigDecimal("precio_u_v"));
                fila.put("nombrePlato", rs.getString("nombre_plato"));
                fila.put("nombreEmpleado", rs.getString("nombre_empleado"));
                fila.put("nombreTurno", rs.getString("nombre_turno"));
                fila.put("nombreCarta", rs.getString("nombre_carta"));
                lista.add(fila);
            }
        }
    }
    return lista;
}


}
