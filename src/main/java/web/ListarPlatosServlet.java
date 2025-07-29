/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package web;
import dao.PlatoDAO;
import model.Plato;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zudex
 */
@WebServlet(name = "ListarPlatosServlet", urlPatterns = {"/ListarPlatosServlet"})
public class ListarPlatosServlet extends HttpServlet {

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            try {
                List<Plato> platos = new PlatoDAO().listarTodos();
                req.setAttribute("platos", platos);
                RequestDispatcher rd = req.getRequestDispatcher("listaPlatos.jsp");
                rd.forward(req, resp);
            } catch (IOException | SQLException | ServletException e) {
                throw new ServletException(e);
            }
        }
}
