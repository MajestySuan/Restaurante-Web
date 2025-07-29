<%-- 
    Document   : listaPlatos
    Created on : 5/07/2025, 3:19:24 a. m.
    Author     : zudex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head><title>Lista de Platos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:700,400&display=swap" rel="stylesheet">

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

        <form method="get" action="PlatoController" class="row mb-4">
            <h2 class="mb-4 text-center">Nuestro Menú</h2>
            <div class="col-md-4">
                
                <select name="categoria" class="form-select">
                    <option value="">Todas las categorías</option>
                    <c:forEach var="c" items="${categorias}">
                        <option value="${c.idCategoria}" ${param.categoria == c.idCategoria.toString() ? 'selected' : ''}>
                            ${c.nombreCategoria}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-4">
                <select name="region" class="form-select">
                    <option value="">Todas las regiones</option>
                    <c:forEach var="r" items="${regiones}">
                        <option value="${r.idRegion}" ${param.region == r.idRegion.toString() ? 'selected' : ''}>
                            ${r.nombreRegion}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-4">
                <button class="btn btn-warning w-100" type="submit">
                    <i class="bi bi-funnel"></i> Filtrar
                </button>
            </div>
        </form>

        <div class="row">
            <c:forEach var="p" items="${listaPlatos}">
                <div class="col-md-4 mb-4">
                    <div class="card h-100 shadow-sm">
                        <img src="${p.foto}" class="card-img-top" alt="${p.nombrePlato}" style="height:220px;object-fit:cover;">
                        <div class="card-body">
                            <h5 class="card-title">${p.nombrePlato}</h5>
                            <span class="badge bg-success mb-2">$${p.precioVenta}</span>
                            <p class="card-text">${p.descripcion}</p>
                            <p>
                                <span class="badge bg-secondary">${p.nivelComplejidad == 1 ? 'Fácil' : p.nivelComplejidad == 5 ? 'Experto' : 'Intermedio'}</span>
                                <span class="badge bg-info">${p.categoriaNombre}</span>
                                <span class="badge bg-warning text-dark">${p.regionNombre}</span>
                                
                            </p>
                            <a href="PlatoController?action=ver&id=${p.idPlato}" class="btn btn-outline-primary btn-sm mt-2">
                                <i class="bi bi-eye"></i> Ver detalles
                            </a>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <c:if test="${empty listaPlatos}">
                <div class="col-12 text-center">
                    <div class="alert alert-info">No hay platos disponibles con los filtros seleccionados.</div>
                </div>
            </c:if>
        </div>
    </div>
</body>
<footer class="bg-dark text-white text-center py-3 mt-5">
    <div class="container">
        <small>&copy; 2025 COLOMBIANO, COMA CONTENTO. Todos los derechos reservados.</small>
    </div>
</footer>
</html>