/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package web;

import dao.*;
import model.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@WebServlet("/VentaController")
public class VentaController extends HttpServlet {

    private final VentaDAO ventaDAO = new VentaDAO();
    private final PlatoDAO platoDAO = new PlatoDAO();
    private final TurnoDAO turnoDAO = new TurnoDAO();
    private final CartaDAO cartaDAO = new CartaDAO();
    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    private final StockDAO stockDAO = new StockDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        String action = req.getParameter("action");
        if ("historial".equals(action)) {
        String fechaInicio = req.getParameter("fechaInicio");
        String fechaFin = req.getParameter("fechaFin");
        Integer idPlato = req.getParameter("idPlato") != null && !req.getParameter("idPlato").isEmpty() ? Integer.parseInt(req.getParameter("idPlato")) : null;
        Integer idEmpleado = req.getParameter("idEmpleado") != null && !req.getParameter("idEmpleado").isEmpty() ? Integer.parseInt(req.getParameter("idEmpleado")) : null;
        Integer idTurno = req.getParameter("idTurno") != null && !req.getParameter("idTurno").isEmpty() ? Integer.parseInt(req.getParameter("idTurno")) : null;
        try {
            List<Map<String, Object>> ventas = new VentaDAO().listarFiltrado(fechaInicio, fechaFin, idPlato, idEmpleado, idTurno);
            req.setAttribute("ventas", ventas);
            // Cargar listas para selects de filtro
            req.setAttribute("platos", new PlatoDAO().listarTodos());
            req.setAttribute("empleados", new EmpleadoDAO().listarTodos());
            req.setAttribute("turnos", new TurnoDAO().listarTodos());
            req.getRequestDispatcher("listaVentas.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
        return;
    }
        if (action == null) {
            action = "mostrarFormulario";
        }

        try {
            switch (action) {
                case "mostrarFormulario":
                    mostrarFormularioVenta(req, resp);
                    break;
                case "platosPorCarta":
                    obtenerPlatosPorCarta(req, resp);
                    break;
                case "listar":
                    listarVentas(req, resp);
                    break;
                default:
                    mostrarFormularioVenta(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException("Error en VentaController", e);
        }
    }

    private void mostrarFormularioVenta(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {
        
        // Cargar datos necesarios para el formulario
        List<Carta> cartas = cartaDAO.listarCartasVigentesYActivas();
        List<Empleado> empleados = empleadoDAO.listarTodos();
        
        req.setAttribute("cartas", cartas);
        req.setAttribute("empleados", empleados);
        req.setAttribute("fechaHoy", LocalDate.now().toString());
        
        req.getRequestDispatcher("ventaForm.jsp").forward(req, resp);
    }

    private void obtenerPlatosPorCarta(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, SQLException {
        
        String idCartaStr = req.getParameter("idCarta");
        
        if (idCartaStr == null || idCartaStr.isEmpty()) {
            resp.setStatus(400);
            resp.setContentType("application/json");
            resp.getWriter().print("{\"error\":\"Parámetro idCarta requerido\"}");
            return;
        }

        int idCarta = Integer.parseInt(idCartaStr);
        List<Plato> platos = platoDAO.listarPorCarta(idCarta);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < platos.size(); i++) {
            Plato p = platos.get(i);
            
            // Obtener turnos del plato
            Plato platoCompleto = platoDAO.buscarPorId(p.getIdPlato());
            List<Integer> turnos = platoCompleto.getTurnosAsignados();

            json.append("{\"idPlato\":").append(p.getIdPlato())
                .append(",\"nombrePlato\":\"").append(p.getNombrePlato().replace("\"", "\\\""))
                .append("\",\"precio\":").append(p.getPrecioVenta())
                .append(",\"turnos\":").append(turnos.toString()).append("}");

            if (i < platos.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");

        resp.getWriter().print(json.toString());
    }

    private void listarVentas(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {
        
        List<Venta> ventas = ventaDAO.listarTodos();
        req.setAttribute("ventas", ventas);
        req.getRequestDispatcher("listaVentas.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        try {
            // Obtener parámetros del formulario
            String fechaVentaStr = req.getParameter("fechaVenta");
            int idPlato = Integer.parseInt(req.getParameter("idPlato"));
            int cantidad = Integer.parseInt(req.getParameter("cantidad"));
            BigDecimal precioUnitario = new BigDecimal(req.getParameter("precioUnitario"));
            int idTurno = Integer.parseInt(req.getParameter("idTurno"));
            int idCarta = Integer.parseInt(req.getParameter("idCarta"));
            int idEmpleado = Integer.parseInt(req.getParameter("idEmpleado"));

            // Validaciones de negocio
            if (!validarVenta(idCarta, idPlato, idTurno, fechaVentaStr, cantidad)) {
                req.setAttribute("mensajeError", "Error en validación de venta");
                mostrarFormularioVenta(req, resp);
                return;
            }

            // Crear objeto Venta
            Venta venta = new Venta(
                0, // ID se genera automáticamente
                LocalDate.parse(fechaVentaStr),
                cantidad,
                precioUnitario,
                idPlato,
                idTurno,
                idCarta,
                idEmpleado
            );

            // Insertar venta
            ventaDAO.insertar(venta);
            
            req.setAttribute("mensajeExito", "Venta registrada exitosamente");
            mostrarFormularioVenta(req, resp);

        } catch (Exception e) {
            req.setAttribute("mensajeError", "Error al registrar venta: " + e.getMessage());
            try {
                mostrarFormularioVenta(req, resp);
            } catch (SQLException ex) {
                throw new ServletException(ex);
            }
        }
    }

    private boolean validarVenta(int idCarta, int idPlato, int idTurno, 
                                String fechaVenta, int cantidad) throws SQLException {
        
        // 1. Validar que la carta esté activa y vigente
        Carta carta = cartaDAO.buscarPorId(idCarta);
        if (carta == null || !carta.isActiva() || 
            !cartaDAO.cartaVigenteParaFecha(idCarta, fechaVenta)) {
            return false;
        }

        // 2. Validar que el plato esté en la carta
        if (!cartaDAO.cartaContienePlato(idCarta, idPlato)) {
            return false;
        }

        // 3. Validar que el turno sea válido para el plato
        Plato plato = platoDAO.buscarPorId(idPlato);
        if (plato == null || !plato.getTurnosAsignados().contains(idTurno)) {
            return false;
        }

        // 4. Validar stock de ingredientes
        List<Receta> receta = plato.getReceta();
        for (Receta r : receta) {
            double necesario = r.getCantidadRequerida() * cantidad;
            double disponible = stockDAO.obtenerCantidadActual(r.getIdIngrediente());
            if (disponible < necesario) {
                return false;
            }
        }

        return true;
    }
}
