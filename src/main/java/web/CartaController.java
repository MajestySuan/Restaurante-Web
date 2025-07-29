/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package web;
import dao.CartaDAO;
import dao.PlatoDAO;
import dao.PlatoCartaDAO;
import model.Carta;
import model.Plato;
import model.PlatoCarta;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author zudex
 */
@WebServlet(name = "CartaController", urlPatterns = {"/CartaController"})
public class CartaController extends HttpServlet {
    private CartaDAO cartaDAO = new CartaDAO();
    private PlatoDAO platoDAO = new PlatoDAO();
    private PlatoCartaDAO pcDAO = new PlatoCartaDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action==null) action="listar";
        try {
            switch(action) {
                case "nuevo":
                    prepararForm(req, resp, new Carta());
                    break;
                case "editar":
                    int idEd = Integer.parseInt(req.getParameter("id"));
                    Carta c = cartaDAO.buscarPorId(idEd);
                    prepararForm(req, resp, c);
                    break;
                case "eliminar":
                    int idDel = Integer.parseInt(req.getParameter("id"));
                    cartaDAO.eliminar(idDel);
                    resp.sendRedirect("CartaController");
                    break;
                default:
                    req.setAttribute("listaCartas", cartaDAO.listarTodos());
                    req.getRequestDispatcher("listaCartas.jsp").forward(req, resp);
            }
        } catch(Exception e) { throw new ServletException(e); }
    }

    private void prepararForm(HttpServletRequest req, HttpServletResponse resp, Carta carta) throws Exception {
        List<Plato> todos = platoDAO.listarTodos();
        List<Integer> asignados = carta.getIdCarta()==0
            ? List.of()
            : pcDAO.listarPorCarta(carta.getIdCarta()).stream()
                    .map(PlatoCarta::getIdPlato).collect(Collectors.toList());
        req.setAttribute("carta", carta);
        req.setAttribute("platos", todos);
        req.setAttribute("asignados", asignados);
        RequestDispatcher rd = req.getRequestDispatcher("cartaForm.jsp");
        rd.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = req.getParameter("idCarta").isEmpty() ? 0 : Integer.parseInt(req.getParameter("idCarta"));
            Carta carta = new Carta();
            carta.setIdCarta(id);
            carta.setNombreCarta(req.getParameter("nombreCarta"));
            carta.setFechaInicioVigencia(LocalDate.parse(req.getParameter("fechaInicioVigencia")));
            carta.setFechaFinVigencia(LocalDate.parse(req.getParameter("fechaFinVigencia")));
            carta.setActiva(req.getParameter("activa")!=null);

            if (id==0) cartaDAO.insertar(carta);
            else cartaDAO.actualizar(carta);

            // asignar platos
            pcDAO.eliminarPorCarta(carta.getIdCarta());
            String[] sel = req.getParameterValues("platos");
            if (sel!=null) {
                for(String pid:sel) {
                    pcDAO.insertar(new PlatoCarta(carta.getIdCarta(), Integer.parseInt(pid)));
                }
            }
            resp.sendRedirect("CartaController");
        } catch(Exception e) { throw new ServletException(e); }
    }
}