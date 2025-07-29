/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package web;

import dao.IngredienteDAO;
import dao.StockDAO;
import model.Ingrediente;
import model.Stock;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/IngredienteController")
public class IngredienteController extends HttpServlet {

    private final IngredienteDAO ingredienteDAO = new IngredienteDAO();
    private final StockDAO stockDAO = new StockDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "listar";

        try {
            switch (action) {
                case "nuevo":
                    req.getRequestDispatcher("ingredienteForm.jsp").forward(req, resp);
                    break;
                case "eliminar":
                    eliminarIngrediente(req, resp);
                    break;
                case "agregarStock":
                    mostrarAgregarStock(req, resp);
                    break;
                default:
                    listarIngredientes(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listarIngredientes(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        List<Ingrediente> ingredientes = ingredienteDAO.listarTodos();
        req.setAttribute("ingredientes", ingredientes);
        req.setAttribute("stocks", stockDAO.listarTodos());
        req.getRequestDispatcher("ingredienteStock.jsp").forward(req, resp);
    }

    private void eliminarIngrediente(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        boolean puedeEliminar = true;

        // Verifica si el ingrediente está en uso en recetas o stock
        if (ingredienteDAO.estaEnUso(id)) {
            puedeEliminar = false;
            req.setAttribute("mensajeError", "No se puede eliminar: el ingrediente está en uso.");
        } else {
            ingredienteDAO.eliminar(id);
            req.setAttribute("mensajeExito", "Ingrediente eliminado correctamente.");
        }
        listarIngredientes(req, resp);
    }

    private void mostrarAgregarStock(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        Ingrediente ing = ingredienteDAO.buscarPorId(id);
        req.setAttribute("ingrediente", ing);
        req.getRequestDispatcher("agregarStock.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("nuevo".equals(action)) {
                String nombre = req.getParameter("nombreIngrediente");
                String unidad = req.getParameter("unidadMedida");
                Ingrediente ing = new Ingrediente();
                ing.setNombreIngrediente(nombre);
                ing.setUnidadMedida(unidad);
                ingredienteDAO.insertar(ing);
                req.setAttribute("mensajeExito", "Ingrediente añadido correctamente.");
                listarIngredientes(req, resp);
            } else if ("agregarStock".equals(action)) {
                int id = Integer.parseInt(req.getParameter("idIngrediente"));
                double cantidad = Double.parseDouble(req.getParameter("cantidad"));
                Stock s = new Stock(0, id, cantidad, LocalDateTime.now());
                stockDAO.insertar(s);
                req.setAttribute("mensajeExito", "Stock añadido correctamente.");
                listarIngredientes(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("mensajeError", "Error: " + e.getMessage());
            try {
                listarIngredientes(req, resp);
            } catch (Exception ex) {
                throw new ServletException(ex);
            }
        }
    }
}
