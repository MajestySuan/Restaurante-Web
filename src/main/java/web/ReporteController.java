/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package web;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import dao.ConexionBD;

@WebServlet("/ReporteController")
public class ReporteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tipo = req.getParameter("tipo");      // ventas_mensual, ventas_anual, region_mensual, region_anual, turno_mensual, turno_anual
        String viewName;
        String sql;

        switch (tipo) {
            case "stock_bajo":
                sql = "SELECT * FROM vista_stock_bajo";
                viewName = "Stock Bajo";
                break;
            case "ventas_anual":
                sql = "SELECT * FROM vista_ventas_anuales";
                viewName = "Ventas Anuales";
                break;
            case "region_mensual":
                sql = "SELECT * FROM vista_platos_region_turno_mensual";
                viewName = "Platos Región Mensual";
                break;
            case "region_anual":
                sql = "SELECT * FROM vista_platos_region_turno_anual";
                viewName = "Platos Región Anual";
                break;
            case "turno_mensual":
                sql = "SELECT * FROM vista_platos_region_turno_mensual"; // misma vista, filtrar por turno?
                viewName = "Platos Turno Mensual";
                break;
            case "turno_anual":
                sql = "SELECT * FROM vista_platos_region_turno_anual";
                viewName = "Platos Turno Anual";
                break;
            case "ventas_mensual":
            default:
                sql = "SELECT * FROM vista_ventas_mensuales";
                viewName = "Ventas Mensuales";
        }
        
        try (Connection cn = ConexionBD.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            List<String> columnas = new ArrayList<>();
            ResultSetMetaData md = rs.getMetaData();
            int cols = md.getColumnCount();
            for (int i = 1; i <= cols; i++) {
                columnas.add(md.getColumnLabel(i));
            }

            List<Map<String, Object>> datos = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> fila = new LinkedHashMap<>();
                for (String col : columnas) {
                    fila.put(col, rs.getObject(col));
                }
                datos.add(fila);
            }

            req.setAttribute("titulo", viewName);
            req.setAttribute("columnas", columnas);
            req.setAttribute("datos", datos);
            req.getRequestDispatcher("reporte.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Error al generar reporte", e);
        }
    }
}