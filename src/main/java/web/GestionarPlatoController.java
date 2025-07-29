/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package web;

import dao.PlatoDAO;
import dao.RegionDAO;
import dao.TurnoDAO;
import dao.IngredienteDAO;
import dao.CategoriaDAO;
import model.Plato;
import model.Receta;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/gestionarPlato")
public class GestionarPlatoController extends HttpServlet {

    private final RegionDAO regionDAO = new RegionDAO();
    private final CategoriaDAO categoriaDAO = new CategoriaDAO();
    private final TurnoDAO turnoDAO = new TurnoDAO();
    private final IngredienteDAO ingredienteDAO = new IngredienteDAO();
    private final PlatoDAO platoDAO = new PlatoDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            try {
                // Carga inicial de página
                cargarDatosApoyo(req);
            } catch (Exception ex) {
                Logger.getLogger(GestionarPlatoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            req.getRequestDispatcher("gestionarPlato.jsp").forward(req, resp);
            return;
        }

        try {
            switch (action) {
                case "filtrarPorRegion":
                    // AJAX: devolver lista de platos JSON
                    int idRegion = Integer.parseInt(req.getParameter("region"));
                    List<Plato> platos = platoDAO.listarPorRegion(idRegion);
                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");
                    try (PrintWriter out = resp.getWriter()) {
                        out.print("[");
                        for (int i = 0; i < platos.size(); i++) {
                            Plato p = platos.get(i);
                            out.print("{"
                                    + "\"idPlato\":" + p.getIdPlato() + ","
                                    + "\"nombrePlato\":\"" + p.getNombrePlato().replace("\"","\\\"") + "\""
                                    + "}");
                            if (i < platos.size() - 1) out.print(",");
                        }
                        out.print("]");
                    }
                    break;

                case "obtener":
                    // AJAX: devolver datos completos de un plato JSON
                    int idPlato = Integer.parseInt(req.getParameter("idPlato"));
                    Plato plato = platoDAO.buscarPorId(idPlato);
                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");
                    try (PrintWriter out = resp.getWriter()) {
                        out.print("{"
                                + "\"idPlato\":" + plato.getIdPlato() + ","
                                + "\"nombrePlato\":\"" + plato.getNombrePlato().replace("\"","\\\"") + "\","
                                + "\"descripcion\":\"" + plato.getDescripcion().replace("\"","\\\"") + "\","
                                + "\"precioVenta\":" + plato.getPrecioVenta() + ","
                                + "\"nivelComplejidad\":" + plato.getNivelComplejidad() + ","
                                + "\"foto\":\"" + (plato.getFoto() != null
                                    ? plato.getFoto().replace("\"","\\\"") : "") + "\","
                                + "\"idCategoria\":" + plato.getIdCategoria() + ","
                                + "\"idRegion\":" + plato.getIdRegion() + ","
                                + "\"turnosAsignados\":" + plato.getTurnosAsignados().toString() + ","
                                + "\"receta\":[");
                        List<Receta> receta = plato.getReceta();
                        for (int i = 0; i < receta.size(); i++) {
                            Receta r = receta.get(i);
                            out.print("{"
                                    + "\"idIngrediente\":" + r.getIdIngrediente() + ","
                                    + "\"cantidadRequerida\":" + r.getCantidadRequerida() + ","
                                    + "\"unidadMedida\":\"" + r.getUnidadMedida().replace("\"","\\\"") + "\","
                                    + "\"descripcionPreparacion\":\"" + r.getDescripcionPreparacion().replace("\"","\\\"") + "\""
                                    + "}");
                            if (i < receta.size() - 1) out.print(",");
                        }
                        out.print("]"
                                + "}");
                    }
                    break;

                default:
                    // acción desconocida: recargar vista
                    cargarDatosApoyo(req);
                    req.getRequestDispatcher("gestionarPlato.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException("Error en GestionarPlatoController", e);
        }
    }

    private void cargarDatosApoyo(HttpServletRequest req) throws Exception {
        req.setAttribute("regiones", regionDAO.listarTodos());
        req.setAttribute("categorias", categoriaDAO.listarTodos());
        req.setAttribute("turnos", turnoDAO.listarTodos());
        req.setAttribute("ingredientes", ingredienteDAO.listarTodos());
    }
}

