<%-- 
    Document   : platoForm
    Created on : 5/07/2025, 3:54:26 a. m.
    Author     : zudex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Gestión de Plato</title>
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

        <div class="container mt-4 mb-5">
            <h2 class="section-title text-center">
                <c:out value="${plato.idPlato == 0 ? 'Nuevo Plato' : 'Editar Plato'}"/>
            </h2>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${not empty mensajeExito}">
  <div class="alert alert-success">${mensajeExito}</div>
</c:if>
<c:if test="${not empty mensajeError}">
  <div class="alert alert-danger">${mensajeError}</div>
</c:if>
            <form action="PlatoController" method="post">
                <input type="hidden" name="idPlato" value="${plato.idPlato}" />

                <div class="mb-3">
                    <label class="form-label">Nombre</label>
                    <input type="text" name="nombrePlato" class="form-control" value="${plato.nombrePlato}" required />
                </div>

                <div class="mb-3">
                    <label class="form-label">Descripción</label>
                    <textarea name="descripcion" class="form-control" rows="3" required>${plato.descripcion}</textarea>
                </div>

                <div class="row mb-3">
                    <div class="col-md-4">
                        <label class="form-label">Nivel de complejidad</label>
                        <input type="number" name="nivelComplejidad" class="form-control" min="1" max="5"
                               value="${plato.nivelComplejidad}" required />
                    </div>
                    <div class="col-md-4">
                        <label class="form-label">Precio de venta</label>
                        <input type="number" step="0.01" name="precioVenta" class="form-control"
                               value="${plato.precioVenta}" required />
                    </div>
                    <div class="col-md-4">
                        <label class="form-label">Foto (URL)</label>
                        <input type="text" name="foto" class="form-control" value="${plato.foto}" />
                    </div>
                </div>

                <div class="row mb-3">
                    <div class="col-md-6">
                        <label class="form-label">Categoría</label>
                        <select name="idCategoria" class="form-select" required>
                            <option value="">Seleccione...</option>
                            <c:forEach var="c" items="${categorias}">
                                <option value="${c.idCategoria}"
                                        ${c.idCategoria == plato.idCategoria ? 'selected' : ''}>
                                    ${c.nombreCategoria}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Región</label>
                        <select name="idRegion" class="form-select" required>
                            <option value="">Seleccione...</option>
                            <c:forEach var="r" items="${regiones}">
                                <option value="${r.idRegion}"
                                        ${r.idRegion == plato.idRegion ? 'selected' : ''}>
                                    ${r.nombreRegion}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="mb-3">
                    <label class="form-label">Turnos disponibles</label><br/>
                    <c:forEach var="t" items="${turnos}">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" name="turnos"
                                   value="${t.idTurno}"
                                   ${plato.turnosAsignados.contains(t.idTurno) ? 'checked' : ''} />
                            <label class="form-check-label">${t.nombreTurno}</label>
                        </div>
                    </c:forEach>
                </div>

                <h5 class="section-title">Ingredientes y Receta</h5>
                <table class="table table-bordered" id="tablaReceta">
                    <thead class="table-light">
                        <tr>
                            <th>Ingrediente</th>
                            <th>Cantidad</th>
                            <th>Unidad</th>
                            <th>Preparación</th>
                            <th>Acción</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="r" items="${plato.receta}">
                            <tr>
                                <td>
                                    <select name="idIngrediente" class="form-select" required>
                                        <option value="">Seleccione...</option>
                                        <c:forEach var="ing" items="${ingredientes}">
                                            <option value="${ing.idIngrediente}"
                                                    ${ing.idIngrediente == r.idIngrediente ? 'selected' : ''}>
                                                ${ing.nombreIngrediente}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td><input type="number" step="0.01" name="cantidadRequerida"
                                           class="form-control" value="${r.cantidadRequerida}" required /></td>
                                <td><input type="text" name="unidadMedida" class="form-control"
                                           value="${r.unidadMedida}" readonly /></td>
                                <td><input type="text" name="descripcionPreparacion" class="form-control"
                                           value="${r.descripcionPreparacion}" /></td>
                                <td>
                                    <button type="button" class="btn btn-danger btn-sm"
                                            onclick="eliminarFila(this)"><i class="bi bi-dash-circle"></i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <button type="button" class="btn btn-secondary mb-4" onclick="agregarFila()">
                    <i class="bi bi-plus-circle"></i> Añadir ingrediente
                </button>

                <div class="text-center">
                    <button type="submit" class="btn btn-primary btn-lg">
                        <i class="bi bi-save"></i> Guardar Plato
                    </button>
                    <a href="PlatoController" class="btn btn-outline-secondary btn-lg ms-2">
                        <i class="bi bi-arrow-left-circle"></i> Volver
                    </a>
                </div>
            </form>
                    
        </div>

        <footer class="bg-dark text-white text-center py-3">
            <small>&copy; 2025 COLOMBIANO, COMA CONTENTO. Todos los derechos reservados.</small>
        </footer>

        <!-- Bootstrap JS Bundle -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script>
                function agregarFila() {
                    const tabla = document.getElementById('tablaReceta').getElementsByTagName('tbody')[0];
                    const firstRow = tabla.rows[0];
                    const nueva = firstRow.cloneNode(true);
                    // Limpiar valores
                    nueva.querySelectorAll('input').forEach(i => i.value = '');
                    nueva.querySelectorAll('select')[0].selectedIndex = 0;
                    tabla.appendChild(nueva);
                }
                function eliminarFila(btn) {
                    const tabla = document.getElementById('tablaReceta').getElementsByTagName('tbody')[0];
                    if (tabla.rows.length > 1) {
                        btn.closest('tr').remove();
                    }
                }
        </script>
    </body>
</html>
