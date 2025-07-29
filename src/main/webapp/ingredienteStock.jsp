<%-- 
    Document   : ingredienteStock
    Created on : 7/07/2025, 6:46:55?p. m.
    Author     : zudex
--%>

<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Ingredientes y Stock</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Montserrat:700,400&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

        
        <style>
            body {
                font-family: 'Montserrat', 'Roboto', Arial, sans-serif;
                background-color: #f8f9fa;
            }
            .hero {
                background: url('hero-plato.jpg') center/cover no-repeat;
                color: #fff;
                min-height: 60vh;
                display: flex;
                align-items: center;
                justify-content: center;
                position: relative;
            }
            .hero-overlay {
                background: rgba(0,0,0,0.5);
                padding: 3rem;
                border-radius: 1rem;
            }
            .navbar-brand img {
                border-radius: 50%;
                margin-right: 10px;
            }
            .section-title {
                color: #4e342e;
                margin-top: 2rem;
                margin-bottom: 1rem;
                font-weight: 700;
            }
        </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container">
    <a class="navbar-brand d-flex align-items-center" href="index.jsp">
      <img src="IMG/logo.png" alt="Logo" width="40" height="40" class="me-2">
      COLOMBIANO, COMA CONTENTO
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" 
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item">
          <a class="nav-link d-flex align-items-center" href="index.jsp">
            <i class="bi bi-house-door-fill me-2"></i> Inicio
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link d-flex align-items-center" href="PlatoController">
            <i class="bi bi-egg-fried me-2"></i> Menú
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link d-flex align-items-center" href="CartaController">
            <i class="bi bi-journal-text me-2"></i> Cartas
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link d-flex align-items-center" href="VentaController">
            <i class="bi bi-cash-coin me-2"></i> Ventas
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link d-flex align-items-center" href="IngredienteController">
            <i class="bi bi-box-seam me-2"></i> Ingredientes / Stock
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link d-flex align-items-center" href="gestionarPlato">
            <i class="bi bi-ui-checks-grid me-2"></i> Gestión de Platos
          </a>
        </li>
        <!-- DROPDOWN DE REPORTES -->
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle d-flex align-items-center" href="#" id="navbarDropdown" role="button" 
             data-bs-toggle="dropdown" aria-expanded="false">
            <i class="bi bi-graph-up me-2"></i> Reportes
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><h6 class="dropdown-header">Ventas</h6></li>
            <li>
              <a class="dropdown-item d-flex align-items-center" href="ReporteController?tipo=ventas_mensual">
                <i class="bi bi-calendar-month me-2"></i> Ventas Mensuales
              </a>
            </li>
            <li>
              <a class="dropdown-item d-flex align-items-center" href="ReporteController?tipo=ventas_anual">
                <i class="bi bi-calendar-year me-2"></i> Ventas Anuales
              </a>
            </li>
            <li><hr class="dropdown-divider"></li>
            <li><h6 class="dropdown-header">Por Región</h6></li>
            <li>
              <a class="dropdown-item d-flex align-items-center" href="ReporteController?tipo=region_mensual">
                <i class="bi bi-geo-alt me-2"></i> Platos por Región (Mensual)
              </a>
            </li>
            <li>
              <a class="dropdown-item d-flex align-items-center" href="ReporteController?tipo=region_anual">
                <i class="bi bi-geo-alt-fill me-2"></i> Platos por Región (Anual)
              </a>
            </li>
            <li><hr class="dropdown-divider"></li>
            <li><h6 class="dropdown-header">Por Turno</h6></li>
            <li>
              <a class="dropdown-item d-flex align-items-center" href="ReporteController?tipo=turno_mensual">
                <i class="bi bi-clock me-2"></i> Platos por Turno (Mensual)
              </a>
            </li>
            <li>
              <a class="dropdown-item d-flex align-items-center" href="ReporteController?tipo=turno_anual">
                <i class="bi bi-clock-fill me-2"></i> Platos por Turno (Anual)
              </a>
            </li>
            <li><hr class="dropdown-divider"></li>
            <li><h6 class="dropdown-header">Otros</h6></li>
            <li>
              <a class="dropdown-item d-flex align-items-center" href="ReporteController?tipo=stock_bajo">
                <i class="bi bi-exclamation-triangle me-2"></i> Stock Bajo
              </a>
            </li>
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link d-flex align-items-center" href="Contacto.jsp">
            <i class="bi bi-envelope-fill me-2"></i> Contacto
          </a>
        </li>
      </ul>
    </div>
  </div>
</nav>
<div class="container mt-4">
    <h2>Ingredientes y Stock</h2>
    <!-- Mensajes -->
    <% String mensajeExito = (String) request.getAttribute("mensajeExito");
       if (mensajeExito != null) { %>
        <div class="alert alert-success"><%= mensajeExito %></div>
    <% }
       String mensajeError = (String) request.getAttribute("mensajeError");
       if (mensajeError != null) { %>
        <div class="alert alert-danger"><%= mensajeError %></div>
    <% } %>
    <!-- Formulario para añadir ingrediente -->
    <form action="IngredienteController" method="post" class="row g-3 mb-4">
        <input type="hidden" name="action" value="nuevo"/>
        <div class="col-md-5">
            <input type="text" name="nombreIngrediente" class="form-control" placeholder="Nombre ingrediente" required>
        </div>
        <div class="col-md-3">
            <input type="text" name="unidadMedida" class="form-control" placeholder="Unidad de medida (ej: gramos)" required>
        </div>
        <div class="col-md-2">
            <button class="btn btn-success" type="submit">Añadir Ingrediente</button>
        </div>
    </form>
    <!-- Tabla de ingredientes y stock -->
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Ingrediente</th>
                <th>Unidad</th>
                <th>Stock actual</th>
                <th>Añadir stock</th>
                <th>Eliminar</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<model.Ingrediente> ingredientes = (List<model.Ingrediente>) request.getAttribute("ingredientes");
                dao.StockDAO stockDAO = new dao.StockDAO();
                for (model.Ingrediente ing : ingredientes) {
                    double stockActual = stockDAO.obtenerCantidadActual(ing.getIdIngrediente());
            %>
            <tr>
                <td><%= ing.getNombreIngrediente() %></td>
                <td><%= ing.getUnidadMedida() %></td>
                <td><%= stockActual %></td>
                <td>
                    <form action="IngredienteController" method="post" class="d-inline">
                        <input type="hidden" name="action" value="agregarStock"/>
                        <input type="hidden" name="idIngrediente" value="<%= ing.getIdIngrediente() %>"/>
                        <input type="number" name="cantidad" step="0.01" min="0.01" style="width: 80px;" required>
                        <button class="btn btn-primary btn-sm" type="submit">+</button>
                    </form>
                </td>
                <td>
                    <form action="IngredienteController" method="get" class="d-inline" onsubmit="return confirm('¿Eliminar ingrediente?');">
                        <input type="hidden" name="action" value="eliminar"/>
                        <input type="hidden" name="id" value="<%= ing.getIdIngrediente() %>"/>
                        <button class="btn btn-danger btn-sm" type="submit">??</button>
                    </form>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>
</div>
</body>
</html>
