<%-- 
    Document   : cartaForm
    Created on : 5/07/2025, 3:58:15 a. m.
    Author     : zudex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html><head>
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
</head><body>
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
  <h2>${carta.idCarta==0?'Nueva Carta':'Editar Carta'}</h2>
  <form action="CartaController" method="post">
    <input type="hidden" name="idCarta" value="${carta.idCarta}"/>
    <div class="mb-3">
      <label>Nombre</label>
      <input class="form-control" name="nombreCarta" value="${carta.nombreCarta}" required/>
    </div>
    <div class="row mb-3">
      <div class="col"><label>Inicio vigencia</label>
        <input type="date" class="form-control" name="fechaInicioVigencia"
               value="${carta.fechaInicioVigencia}"/></div>
      <div class="col"><label>Fin vigencia</label>
        <input type="date" class="form-control" name="fechaFinVigencia"
               value="${carta.fechaFinVigencia}"/></div>
      <div class="col d-flex align-items-end">
        <div class="form-check">
          <input class="form-check-input" type="checkbox" name="activa"
                 ${carta.activa?'checked':''}/>
          <label class="form-check-label">Activa</label>
        </div>
      </div>
    </div>
    <div class="mb-3">
      <label>Platos</label><br/>
      <c:forEach var="p" items="${platos}">
        <div class="form-check form-check-inline">
          <input class="form-check-input" type="checkbox" name="platos"
                 value="${p.idPlato}" ${asignados.contains(p.idPlato)?'checked':''}/>
          <label class="form-check-label">${p.nombrePlato}</label>
        </div>
      </c:forEach>
    </div>
    <button class="btn btn-primary">Guardar</button>
    <a href="CartaController" class="btn btn-secondary">Cancelar</a>
  </form>
</div>
</body></html>>