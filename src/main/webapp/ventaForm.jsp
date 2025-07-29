<%-- 
    Document   : ventaForm
    Created on : 5/07/2025, 4:00:41?a. m.
    Author     : zudex
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Registro de Venta</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:700,400&display=swap" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
            <h2>Registro de Venta</h2>

            <!-- Mensajes de éxito/error -->
            <c:if test="${not empty mensajeExito}">
                <div class="alert alert-success">${mensajeExito}</div>
            </c:if>
            <c:if test="${not empty mensajeError}">
                <div class="alert alert-danger">${mensajeError}</div>
            </c:if>

            <form action="VentaController" method="post">
                <div class="row mb-3">
                    <!-- Fecha de venta -->
                    <div class="col-md-6">
                        <label class="form-label">Fecha de Venta</label>
                        <input type="date" id="fechaVenta" name="fechaVenta" 
                               class="form-control" value="${fechaHoy}" required>
                    </div>

                    <!-- Empleado -->
                    <div class="col-md-6">
                        <label class="form-label">Empleado</label>
                        <select id="idEmpleado" name="idEmpleado" class="form-select" required>
                            <option value="">--Seleccione--</option>
                            <c:forEach var="e" items="${empleados}">
                                <option value="${e.idEmpleado}">${e.nombreEmpleado}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="row mb-3">
                    <!-- Carta -->
                    <div class="col-md-6">
                        <label class="form-label">Carta</label>
                        <select id="idCarta" name="idCarta" class="form-select" required>
                            <option value="">--Seleccione--</option>
                            <c:forEach var="c" items="${cartas}">
                                <option value="${c.idCarta}">${c.nombreCarta}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Plato -->
                    <div class="col-md-6">
                        <label class="form-label">Plato</label>
                        <select id="idPlato" name="idPlato" class="form-select" disabled required>
                            <option value="">Seleccione carta primero</option>
                        </select>
                    </div>
                </div>

                <div class="row mb-3">
                    <!-- Precio unitario -->
                    <div class="col-md-4">
                        <label class="form-label">Precio Unitario</label>
                        <input type="number" id="precioUnitario" name="precioUnitario" 
                               class="form-control" step="0.01" readonly>
                    </div>

                    <!-- Turno -->
                    <div class="col-md-4">
                        <label class="form-label">Turno</label>
                        <select id="idTurno" name="idTurno" class="form-select" disabled required>
                            <option value="">--Seleccione--</option>
                        </select>
                    </div>

                    <!-- Cantidad -->
                    <div class="col-md-4">
                        <label class="form-label">Cantidad</label>
                        <input type="number" id="cantidad" name="cantidad" 
                               class="form-control" min="1" disabled required>
                    </div>
                </div>

                <button type="submit" id="btnRegistrar" class="btn btn-primary" disabled>
                    Registrar Venta
                </button>
                <a href="index.jsp" class="btn btn-secondary ms-2">Cancelar</a>
            </form>
        </div>

        <script>
            // JavaScript para manejar el flujo del formulario
            $(document).ready(function () {

                // Al cambiar la carta, cargar platos
                $('#idCarta').change(function () {
                    const idCarta = $(this).val();
                    resetFormulario();

                    if (!idCarta)
                        return;

                    $.get('VentaController', {
                        action: 'platosPorCarta',
                        idCarta: idCarta
                    })
                            .done(function (data) {
                                $('#idPlato').html('<option value="">--Seleccione Plato--</option>');
                                data.forEach(function (plato) {
                                    const option = $('<option></option>')
                                            .val(plato.idPlato)
                                            .text(plato.nombrePlato)
                                            .data('precio', plato.precio)
                                            .data('turnos', plato.turnos);
                                    $('#idPlato').append(option);
                                });
                                $('#idPlato').prop('disabled', false);
                            })
                            .fail(function () {
                                alert('Error al cargar platos');
                            });
                });

                // Al cambiar el plato, cargar precio y turnos
                $('#idPlato').change(function () {
                    const selectedOption = $(this).find('option:selected');
                    if (!selectedOption.val())
                        return;

                    // Establecer precio
                    $('#precioUnitario').val(selectedOption.data('precio'));

                    // Cargar turnos
                    const turnos = selectedOption.data('turnos');
                    $('#idTurno').html('<option value="">--Seleccione Turno--</option>');

                    turnos.forEach(function (idTurno) {
                        // Aquí necesitarías los nombres de turno, por simplicidad uso IDs
                        const nombreTurno = idTurno === 1 ? 'Desayuno' :
                                idTurno === 2 ? 'Almuerzo' : 'Cena';
                        $('#idTurno').append($('<option></option>')
                                .val(idTurno).text(nombreTurno));
                    });

                    $('#idTurno').prop('disabled', false);
                    $('#cantidad').prop('disabled', false);
                    $('#btnRegistrar').prop('disabled', false);
                });

                function resetFormulario() {
                    $('#idPlato').html('<option value="">Seleccione carta primero</option>').prop('disabled', true);
                    $('#idTurno').html('<option value="">--Seleccione--</option>').prop('disabled', true);
                    $('#precioUnitario').val('');
                    $('#cantidad').val('').prop('disabled', true);
                    $('#btnRegistrar').prop('disabled', true);
                }
            });
        </script>
    </body>
    <footer class="bg-dark text-white text-center py-3 mt-5">
        <div class="container">
            <small>&copy; 2025 COLOMBIANO, COMA CONTENTO. Todos los derechos reservados.</small>
        </div>
    </footer>
</html>



