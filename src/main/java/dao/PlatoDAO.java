package dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import model.Plato;
import model.Receta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlatoDAO {

    // Listar todos los platos con categoría, región, turnos y recetas
    public List<Plato> listarTodos() throws SQLException {
        List<Plato> lista = new ArrayList<>();
        String sql = "SELECT p.id_plato, p.nombre_plato, p.descripcion, p.nivel_complejidad, "
                + "p.foto, p.precio_venta, p.id_categoria, c.nombre_categoria, "
                + "p.id_region, r.nombre_region "
                + "FROM plato p "
                + "JOIN categoria c ON p.id_categoria = c.id_categoria "
                + "JOIN region r ON p.id_region = r.id_region";
        try (Connection cn = ConexionBD.getConnection(); Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Plato plato = new Plato();
                plato.setIdPlato(rs.getInt("id_plato"));
                plato.setNombrePlato(rs.getString("nombre_plato"));
                plato.setDescripcion(rs.getString("descripcion"));
                plato.setNivelComplejidad(rs.getInt("nivel_complejidad"));
                plato.setFoto(rs.getString("foto"));
                plato.setPrecioVenta(rs.getBigDecimal("precio_venta"));
                plato.setIdCategoria(rs.getInt("id_categoria"));
                plato.setCategoriaNombre(rs.getString("nombre_categoria"));
                plato.setIdRegion(rs.getInt("id_region"));
                plato.setRegionNombre(rs.getString("nombre_region"));

                // Cargar turnos asignados
                plato.setTurnosAsignados(listarTurnosPorPlato(plato.getIdPlato(), cn));
                // Cargar recetas asociadas
                plato.setReceta(listarRecetaPorPlato(plato.getIdPlato(), cn));

                lista.add(plato);
            }
        }
        return lista;
    }

    public boolean existeNombreEnRegion(String nombre, int idRegion) throws SQLException {
  String sql = "SELECT 1 FROM plato WHERE nombre_plato = ? AND id_region = ?";
  try (PreparedStatement ps = ConexionBD.getConnection().prepareStatement(sql)) {
    ps.setString(1, nombre);
    ps.setInt(2, idRegion);
    try (ResultSet rs = ps.executeQuery()) {
      return rs.next();
    }
  }
}
    
    public List<Plato> filtrar(String idCategoria, String idRegion) throws SQLException {
    List<Plato> lista = new ArrayList<>();

    StringBuilder sql = new StringBuilder("SELECT * FROM plato WHERE 1=1");
    List<Object> params = new ArrayList<>();

    if (idCategoria != null && !idCategoria.isEmpty()) {
        sql.append(" AND id_categoria = ?");
        params.add(Integer.parseInt(idCategoria));
    }
    if (idRegion != null && !idRegion.isEmpty()) {
        sql.append(" AND id_region = ?");
        params.add(Integer.parseInt(idRegion));
    }

    System.out.println(">> [DAO] SQL ejecutado: " + sql);

    try (Connection cn = ConexionBD.getConnection();
         PreparedStatement ps = cn.prepareStatement(sql.toString())) {

        // Asignar parámetros dinámicamente
        for (int i = 0; i < params.size(); i++) {
            ps.setObject(i + 1, params.get(i));
        }

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Plato p = new Plato();
                p.setIdPlato(rs.getInt("id_plato"));
                p.setNombrePlato(rs.getString("nombre_plato"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setNivelComplejidad(rs.getInt("nivel_complejidad"));
                p.setFoto(rs.getString("foto"));
                p.setPrecioVenta(rs.getBigDecimal("precio_venta"));
                p.setIdCategoria(rs.getInt("id_categoria"));
                p.setIdRegion(rs.getInt("id_region"));
                // Si necesitas cargar turnos o receta, deberás hacerlo aparte
                lista.add(p);
            }
        }
    }
    System.out.println(">> [DAO] Platos devueltos: " + lista.size());
    return lista;
}

    
    
    // Buscar un plato por ID (incluye categoría, región, turnos y recetas)
    public Plato buscarPorId(int id) throws SQLException {
        String sql = "SELECT p.id_plato, p.nombre_plato, p.descripcion, p.nivel_complejidad, "
                + "p.foto, p.precio_venta, p.id_categoria, c.nombre_categoria, "
                + "p.id_region, r.nombre_region "
                + "FROM plato p "
                + "JOIN categoria c ON p.id_categoria = c.id_categoria "
                + "JOIN region r ON p.id_region = r.id_region "
                + "WHERE p.id_plato = ?";
        try (Connection cn = ConexionBD.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Plato plato = new Plato();
                    plato.setIdPlato(rs.getInt("id_plato"));
                    plato.setNombrePlato(rs.getString("nombre_plato"));
                    plato.setDescripcion(rs.getString("descripcion"));
                    plato.setNivelComplejidad(rs.getInt("nivel_complejidad"));
                    plato.setFoto(rs.getString("foto"));
                    plato.setPrecioVenta(rs.getBigDecimal("precio_venta"));
                    plato.setIdCategoria(rs.getInt("id_categoria"));
                    plato.setCategoriaNombre(rs.getString("nombre_categoria"));
                    plato.setIdRegion(rs.getInt("id_region"));
                    plato.setRegionNombre(rs.getString("nombre_region"));

                    plato.setTurnosAsignados(listarTurnosPorPlato(id, cn));
                    plato.setReceta(listarRecetaPorPlato(id, cn));

                    return plato;
                }
            }
        }
        return null;
    }

    // Insertar un nuevo plato con sus turnos y receta
    public void insertarConRecetaYTurnos(Plato p) throws SQLException {
    String sqlPlato = ""
        + "INSERT INTO plato ("
        + "  nombre_plato, descripcion, nivel_complejidad, foto, precio_venta, "
        + "  id_categoria, id_region"
        + ") VALUES (?, ?, ?, ?, ?, ?, ?) "
        + "RETURNING id_plato";

    try (Connection cn = ConexionBD.getConnection();
         PreparedStatement ps = cn.prepareStatement(sqlPlato)) {

        // 1. Insertar datos básicos del plato y obtener el id generado
        ps.setString(1, p.getNombrePlato());
        ps.setString(2, p.getDescripcion());
        ps.setInt(3, p.getNivelComplejidad());
        ps.setString(4, p.getFoto());
        ps.setBigDecimal(5, p.getPrecioVenta());
        ps.setInt(6, p.getIdCategoria());
        ps.setInt(7, p.getIdRegion());

        try (ResultSet rs = ps.executeQuery()) {
            if (!rs.next()) {
                throw new SQLException("No se pudo insertar el plato, no se retornó id.");
            }
        }
        int nuevoId = ps.getResultSet().getInt(1);
        System.out.println(">> [DAO] ID de nuevo plato generado: " + nuevoId);

        // 2. Insertar relación plato-turno
        String sqlTurno = "INSERT INTO plato_turno (id_plato, id_turno) VALUES (?, ?)";
        try (PreparedStatement pt = cn.prepareStatement(sqlTurno)) {
            for (Integer turnoId : p.getTurnosAsignados()) {
                pt.setInt(1, nuevoId);
                pt.setInt(2, turnoId);
                pt.executeUpdate();
                System.out.println(">> [DAO] Turno insertado: plato=" + nuevoId + ", turno=" + turnoId);
            }
        }

        // 3. Insertar receta (ingredientes)
        String sqlReceta = ""
            + "INSERT INTO receta ("
            + "  id_plato, id_ingrediente, cantidad_requerida, descripcion_preparacion"
            + ") VALUES (?, ?, ?, ?)";
        try (PreparedStatement pr = cn.prepareStatement(sqlReceta)) {
            for (Receta r : p.getReceta()) {
                pr.setInt(1, nuevoId);
                pr.setInt(2, r.getIdIngrediente());
                pr.setDouble(3, r.getCantidadRequerida());
                pr.setString(4, r.getDescripcionPreparacion());
                pr.executeUpdate();
                System.out.println(">> [DAO] Receta insertada: plato=" + nuevoId
                    + ", ingrediente=" + r.getIdIngrediente()
                    + ", cantidad=" + r.getCantidadRequerida());
            }
        }

        System.out.println(">> [DAO] insertarConRecetaYTurnos FINALIZADO para plato ID=" + nuevoId);
    }
}

    // Actualizar un plato existente (y sus relaciones)
    public void actualizarConRecetaYTurnos(Plato p) throws SQLException {
    try (Connection cn = ConexionBD.getConnection()) {
        // 1. Actualizar datos básicos de Plato
        String sqlUp = "UPDATE plato SET nombre_plato=?, descripcion=?, "
                     + "nivel_complejidad=?, foto=?, precio_venta=?, "
                     + "id_categoria=?, id_region=? WHERE id_plato=?";
        try (PreparedStatement ps = cn.prepareStatement(sqlUp)) {
            ps.setString(1, p.getNombrePlato());
            ps.setString(2, p.getDescripcion());
            ps.setInt(3, p.getNivelComplejidad());
            ps.setString(4, p.getFoto());
            ps.setBigDecimal(5, p.getPrecioVenta());
            ps.setInt(6, p.getIdCategoria());
            ps.setInt(7, p.getIdRegion());
            ps.setInt(8, p.getIdPlato());
            ps.executeUpdate();
        }

        // 2. Borrar viejos turnos y recetas
        try (PreparedStatement pt = cn.prepareStatement(
                 "DELETE FROM plato_turno WHERE id_plato=?");
             PreparedStatement pr = cn.prepareStatement(
                 "DELETE FROM receta WHERE id_plato=?")) {
            pt.setInt(1, p.getIdPlato());
            pt.executeUpdate();
            pr.setInt(1, p.getIdPlato());
            pr.executeUpdate();
        }

        // 3. Reinsertar turnos
        String sqlT = "INSERT INTO plato_turno (id_plato, id_turno) VALUES (?, ?)";
        try (PreparedStatement psT = cn.prepareStatement(sqlT)) {
            for (int t : p.getTurnosAsignados()) {
                psT.setInt(1, p.getIdPlato());
                psT.setInt(2, t);
                psT.executeUpdate();
            }
        }

        // 4. Reinsertar receta
        String sqlR = "INSERT INTO receta "
                    + "(id_plato, id_ingrediente, cantidad_requerida, descripcion_preparacion) "
                    + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement psR = cn.prepareStatement(sqlR)) {
            for (Receta r : p.getReceta()) {
                psR.setInt(1, p.getIdPlato());
                psR.setInt(2, r.getIdIngrediente());
                psR.setDouble(3, r.getCantidadRequerida());
                psR.setString(4, r.getDescripcionPreparacion());
                psR.executeUpdate();
            }
        }
    }
}

    // Eliminar un plato (y sus relaciones)
    public void eliminar(int idPlato) throws SQLException {
        String sqlReceta = "DELETE FROM receta WHERE id_plato = ?";
        String sqlTurno = "DELETE FROM plato_turno WHERE id_plato = ?";
        String sqlCarta = "DELETE FROM plato_carta WHERE id_plato = ?";
        String sqlPlato = "DELETE FROM plato WHERE id_plato = ?";

        try (Connection cn = ConexionBD.getConnection()) {
            // Deshabilitar autocommit para asegurar atomicidad
            cn.setAutoCommit(false);
            try (PreparedStatement pr = cn.prepareStatement(sqlReceta);
                 PreparedStatement pt = cn.prepareStatement(sqlTurno);
                 PreparedStatement pc = cn.prepareStatement(sqlCarta);
                 PreparedStatement pp = cn.prepareStatement(sqlPlato)) {

                // 1. Eliminar recetas asociadas
                pr.setInt(1, idPlato);
                pr.executeUpdate();

                // 2. Eliminar asignaciones de turnos
                pt.setInt(1, idPlato);
                pt.executeUpdate();

                // 3. Eliminar asignaciones en cartas
                pc.setInt(1, idPlato);
                pc.executeUpdate();

                // 4. Eliminar el propio plato
                pp.setInt(1, idPlato);
                int rows = pp.executeUpdate();
                if (rows == 0) {
                    throw new SQLException("No se encontró el plato con id=" + idPlato);
                }

                // Confirmar transacción
                cn.commit();
            } catch (SQLException e) {
                // Revertir en caso de error
                cn.rollback();
                throw e;
            } finally {
                cn.setAutoCommit(true);
            }
        }
    }

    // Métodos auxiliares para cargar turnos y recetas
    private List<Integer> listarTurnosPorPlato(int idPlato, Connection cn) throws SQLException {
        List<Integer> turnos = new ArrayList<>();
        String sql = "SELECT id_turno FROM plato_turno WHERE id_plato=?";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idPlato);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    turnos.add(rs.getInt("id_turno"));
                }
            }
        }
        return turnos;
    }

    private List<Receta> listarRecetaPorPlato(int idPlato, Connection cn) throws SQLException {
        List<Receta> lista = new ArrayList<>();
        String sql = "SELECT r.id_ingrediente, r.cantidad_requerida, r.descripcion_preparacion, i.unidad_medida "
                + "FROM receta r JOIN ingrediente i ON r.id_ingrediente = i.id_ingrediente "
                + "WHERE r.id_plato = ?";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idPlato);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Receta r = new Receta();
                    r.setIdPlato(idPlato);
                    r.setIdIngrediente(rs.getInt("id_ingrediente"));
                    r.setCantidadRequerida(rs.getDouble("cantidad_requerida"));
                    r.setDescripcionPreparacion(rs.getString("descripcion_preparacion"));
                    r.setUnidadMedida(rs.getString("unidad_medida")); // <-- Aquí se asigna la unidad
                    lista.add(r);
                }
            }
        }
        return lista;
    }

    public List<Plato> listarMenuActivo(Integer idCategoriaFiltro, Integer idRegionFiltro) throws SQLException {
        List<Plato> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT p.id_plato, p.nombre_plato, p.descripcion, p.nivel_complejidad, p.foto, p.precio_venta, "
                + "p.id_categoria, c.nombre_categoria, p.id_region, r.nombre_region "
                + "FROM plato p "
                + "JOIN plato_carta pc ON p.id_plato = pc.id_plato "
                + "JOIN carta ca ON pc.id_carta = ca.id_carta "
                + "JOIN categoria c ON p.id_categoria = c.id_categoria "
                + "JOIN region r ON p.id_region = r.id_region "
                + "WHERE ca.activa = TRUE"
        );
        if (idCategoriaFiltro != null) {
            sql.append(" AND p.id_categoria = ").append(idCategoriaFiltro);
        }
        if (idRegionFiltro != null) {
            sql.append(" AND p.id_region = ").append(idRegionFiltro);
        }
        try (Connection cn = ConexionBD.getConnection(); Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql.toString())) {
            while (rs.next()) {
                Plato plato = new Plato();
                // set campos básicos...
                plato.setIdPlato(rs.getInt("id_plato"));
                plato.setNombrePlato(rs.getString("nombre_plato"));
                plato.setDescripcion(rs.getString("descripcion"));
                plato.setNivelComplejidad(rs.getInt("nivel_complejidad"));
                plato.setFoto(rs.getString("foto"));
                plato.setPrecioVenta(rs.getBigDecimal("precio_venta"));
                plato.setIdCategoria(rs.getInt("id_categoria"));
                plato.setCategoriaNombre(rs.getString("nombre_categoria"));
                plato.setIdRegion(rs.getInt("id_region"));
                plato.setRegionNombre(rs.getString("nombre_region"));
                // cargar turnos y receta si lo deseas
                lista.add(plato);
            }
        }
        return lista;
    }

    public List<Plato> listarPorRegion(int idRegion) throws SQLException {
        List<Plato> lista = new ArrayList<>();
        String sql = "SELECT id_plato, nombre_plato FROM plato WHERE id_region = ?";
        try (Connection cn = ConexionBD.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idRegion);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Plato p = new Plato();
                    p.setIdPlato(rs.getInt("id_plato"));
                    p.setNombrePlato(rs.getString("nombre_plato")); 
                    System.out.println("DAO listPorRegion → id=" + rs.getInt("id_plato")
                   + ", nombre=" + rs.getString("nombre_plato"));
                    lista.add(p);
                }
            }
        }
        return lista;
    }
    public List<Plato> listarPorCarta(int idCarta) throws SQLException {
    List<Plato> lista = new ArrayList<>();
    String sql = "SELECT p.id_plato, p.nombre_plato, p.precio_venta\n" +
             "FROM plato p\n" +
             "JOIN plato_carta pc ON pc.id_plato = p.id_plato\n" +
             "WHERE pc.id_carta = ?";
    try (Connection cn = ConexionBD.getConnection();
         PreparedStatement ps = cn.prepareStatement(sql)) {
        ps.setInt(1, idCarta);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Plato p = new Plato();
                p.setIdPlato(rs.getInt("id_plato"));
                p.setNombrePlato(rs.getString("nombre_plato"));
                p.setPrecioVenta(rs.getBigDecimal("precio_venta"));
                // turnos se cargan más adelante si hace falta
                lista.add(p);
            }
        }
    }
    return lista;
}
}
