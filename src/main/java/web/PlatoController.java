/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package web;

import dao.CartaDAO;
import dao.CategoriaDAO;
import dao.PlatoDAO;
import dao.RegionDAO;
import dao.TurnoDAO;
import dao.IngredienteDAO;
import model.Plato;
import model.Receta;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/PlatoController")
public class PlatoController extends HttpServlet {

    private final PlatoDAO platoDAO = new PlatoDAO();
    private final CategoriaDAO categoriaDAO = new CategoriaDAO();
    private final RegionDAO regionDAO = new RegionDAO();
    private final TurnoDAO turnoDAO = new TurnoDAO();
    private final IngredienteDAO ingredienteDAO = new IngredienteDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("eliminar".equals(action)) {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            // Verificar cartas activas que incluyan el plato
            boolean enCartaActiva = new CartaDAO().existePlatoEnCartaActiva(id);
            if (enCartaActiva) {
                resp.sendError(HttpServletResponse.SC_CONFLICT,
                    "No se puede eliminar: el plato está en la carta activa.");
                return;
            }
            platoDAO.eliminar(id);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        return;
    }
        if (action == null) {
            action = "listar";
        }

        try {
            
            if ("listar".equals(action)) {
            String categoria = req.getParameter("categoria");
            String region = req.getParameter("region");
            System.out.println(">> [Filtro] categoria=" + req.getParameter("categoria") +
                   ", region=" + req.getParameter("region"));
System.out.println(">> [Filtro] action=" + action);

            List<Plato> lista;
            if ((categoria != null && !categoria.isEmpty()) || (region != null && !region.isEmpty())) {
                System.out.println(">> [Filtro] Llamando a platoDAO.filtrar(" + categoria + ", " + region + ")");

                lista = platoDAO.filtrar(categoria, region);
                
            } else {
                lista = platoDAO.listarTodos();
            }
System.out.println(">> [Filtro] Platos encontrados: " + lista.size());
for (Plato p : lista) {
    System.out.println("   - " + p.getNombrePlato());
}

            req.setAttribute("listaPlatos", lista);
            req.setAttribute("categorias", categoriaDAO.listarTodos());
            req.setAttribute("regiones", regionDAO.listarTodos());
            req.getRequestDispatcher("listaPlatos.jsp").forward(req, resp);
            return;
        }
            
            
            // AJAX: filtrar platos por región
            if ("filtrarPorRegion".equals(action)) {
                int idRegion = Integer.parseInt(req.getParameter("region"));
                System.out.println("DEBUG: Parámetro region recibido: " + idRegion);
                List<Plato> platos = platoDAO.listarPorRegion(idRegion);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                try (PrintWriter out = resp.getWriter()) {
                    out.print("[");
                    for (int i = 0; i < platos.size(); i++) {
                        Plato p = platos.get(i);
                        out.print("{"
                                + "\"idPlato\":" + p.getIdPlato() + ","
                                + "\"nombrePlato\":\"" + p.getNombrePlato().replace("\"", "\\\"") + "\""
                                + "}");
                        if (i < platos.size() - 1) {
                            out.print(",");
                        }
                    }
                    out.print("]");
                }
                return;
            }

            // AJAX: obtener datos de un plato
            if ("obtener".equals(action)) {
                int idPlato = Integer.parseInt(req.getParameter("idPlato"));
                Plato plato = platoDAO.buscarPorId(idPlato);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                try (PrintWriter out = resp.getWriter()) {
                    out.print("{"
                            + "\"idPlato\":" + plato.getIdPlato() + ","
                            + "\"nombrePlato\":\"" + plato.getNombrePlato().replace("\"", "\\\"") + "\","
                            + "\"descripcion\":\"" + plato.getDescripcion().replace("\"", "\\\"") + "\","
                            + "\"precioVenta\":" + plato.getPrecioVenta() + ","
                            + "\"nivelComplejidad\":" + plato.getNivelComplejidad() + ","
                            + "\"foto\":\"" + (plato.getFoto() != null
                            ? plato.getFoto().replace("\"", "\\\"") : "") + "\","
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
                                + "\"unidadMedida\":\"" + r.getUnidadMedida().replace("\"", "\\\"") + "\","
                                + "\"descripcionPreparacion\":\"" + r.getDescripcionPreparacion().replace("\"", "\\\"") + "\""
                                + "}");
                        if (i < receta.size() - 1) {
                            out.print(",");
                        }
                    }
                    out.print("]"
                            + "}");
                }
                return;
            }

            // CRUD normal
            switch (action) {
                case "nuevo":
                    prepararFormulario(req, resp, new Plato());
                    break;
                case "editar":
                    int idEdit = Integer.parseInt(req.getParameter("id"));
                    Plato platoEdit = platoDAO.buscarPorId(idEdit);
                    prepararFormulario(req, resp, platoEdit);
                    break;
                case "eliminar":
                    int idDel = Integer.parseInt(req.getParameter("id"));
                    platoDAO.eliminar(idDel);
                    resp.sendRedirect("PlatoController");
                    break;
                default: // listar
                    List<Plato> lista = platoDAO.listarTodos();
                    req.setAttribute("listaPlatos", lista);
                    req.setAttribute("categorias", categoriaDAO.listarTodos());
                    req.setAttribute("regiones", regionDAO.listarTodos());
                    RequestDispatcher rd = req.getRequestDispatcher("listaPlatos.jsp");
                    rd.forward(req, resp);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void prepararFormulario(HttpServletRequest req, HttpServletResponse resp,
            Plato plato) throws ServletException, IOException, SQLException {
        req.setAttribute("categorias", categoriaDAO.listarTodos());
        req.setAttribute("regiones", regionDAO.listarTodos());
        req.setAttribute("turnos", turnoDAO.listarTodos());
        req.setAttribute("ingredientes", ingredienteDAO.listarTodos());
        req.setAttribute("plato", plato);
        RequestDispatcher rd = req.getRequestDispatcher("platoForm.jsp");
        rd.forward(req, resp);
    }

    @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
    String action = req.getParameter("action");
      System.out.println(">> [Servlet] doPost inicio - action=" + action);

    System.out.println(">> [Servlet] idPlato=" + req.getParameter("idPlato"));
    System.out.println(">> [Servlet] nombrePlato=" + req.getParameter("nombrePlato"));
    System.out.println(">> [Servlet] descripcion=" + req.getParameter("descripcion"));
    System.out.println(">> [Servlet] precioVenta=" + req.getParameter("precioVenta"));
    System.out.println(">> [Servlet] nivelComplejidad=" + req.getParameter("nivelComplejidad"));
    System.out.println(">> [Servlet] idCategoria=" + req.getParameter("idCategoria"));
    System.out.println(">> [Servlet] idRegion=" + req.getParameter("idRegion"));

    System.out.println(">> [Servlet] turnos=" + Arrays.toString(req.getParameterValues("turnos")));
    System.out.println(">> [Servlet] idIngrediente=" + Arrays.toString(req.getParameterValues("idIngrediente")));
    System.out.println(">> [Servlet] cantidadRequerida=" + Arrays.toString(req.getParameterValues("cantidadRequerida")));
    System.out.println(">> [Servlet] descripcionPreparacion=" + Arrays.toString(req.getParameterValues("descripcionPreparacion")));
    try {
        // 1) Leer parámetros
        int id = req.getParameter("idPlato")==null||req.getParameter("idPlato").isEmpty()
                 ? 0 : Integer.parseInt(req.getParameter("idPlato"));
        String nombrePlato = req.getParameter("nombrePlato");
        String descripcion  = req.getParameter("descripcion");
        int nivel           = Integer.parseInt(req.getParameter("nivelComplejidad"));
        String foto         = req.getParameter("foto");
        BigDecimal precio   = new BigDecimal(req.getParameter("precioVenta"));
        int idCategoria     = Integer.parseInt(req.getParameter("idCategoria"));
        int idReg = Integer.parseInt(req.getParameter("idRegion"));

        // Turnos
        String[] turnosArr  = req.getParameterValues("turnos");
        List<Integer> turnos = turnosArr!=null
            ? Arrays.stream(turnosArr).map(Integer::parseInt).toList()
            : new ArrayList<>();

        // Receta
        String[] ingredIds      = req.getParameterValues("idIngrediente");
        String[] cantidades     = req.getParameterValues("cantidadRequerida");
        String[] preparaciones  = req.getParameterValues("descripcionPreparacion");
        List<Receta> recetaList = new ArrayList<>();
        if (ingredIds!=null) {
            for (int i=0; i<ingredIds.length; i++) {
                Receta r = new Receta();
                r.setIdPlato(id);
                r.setIdIngrediente(Integer.parseInt(ingredIds[i]));
                r.setCantidadRequerida(Double.parseDouble(cantidades[i]));
                r.setDescripcionPreparacion(preparaciones[i]);
                recetaList.add(r);
            }
        }

        // 2) Construir objeto Plato
        Plato plato = new Plato(
            id, nombrePlato, descripcion, nivel, foto, precio,
            idCategoria, idReg, turnos, recetaList
        );

        // 3) Lógica nuevo vs editar
        if ("nuevo".equals(action)) {
            System.out.println(">> [doPost] ENTRANDO en NUEVO");
    boolean existe = platoDAO.existeNombreEnRegion(nombrePlato, idReg);
    System.out.println(">> [doPost] existeNombreEnRegion=" + existe);
    platoDAO.insertarConRecetaYTurnos(plato);
    System.out.println(">> [doPost] insertarConRecetaYTurnos 호출 완료");
    resp.sendRedirect("PlatoController");
            // Verificar duplicado
            if (platoDAO.existeNombreEnRegion(nombrePlato, idReg)) {
                throw new ServletException(
                  "Ya existe un plato con este nombre en la región seleccionada."
                );
            }
            platoDAO.insertarConRecetaYTurnos(plato);
        } else if ("actualizar".equals(action)) {
            platoDAO.actualizarConRecetaYTurnos(plato);
        } else if ("eliminar".equals(action)) {
            // si manejas eliminación vía POST también
            platoDAO.eliminar(id);
        }

        // 4) Redirigir o mostrar éxito
        resp.sendRedirect("PlatoController");
    } catch (Exception e) {
        req.setAttribute("mensajeError", "Error al guardar el plato: " + e.getMessage());
        try {
            prepararFormulario(req, resp, new Plato());
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
}

}
