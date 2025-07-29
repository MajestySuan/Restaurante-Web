<%-- 
    Document   : gestionarPlato
    Created on : 5/07/2025, 11:27:44 p. m.
    Author     : zudex
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Gestión Unificada de Platos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"/>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
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

        
        <div class="container my-4">
            <h2>Gestión Unificada de Platos e Ingredientes</h2>

            <form id="frmPlato">
                <!-- Selección de Región -->
                <div class="mb-3">
                    <label class="form-label">Región</label>
                    <select id="selRegion" name="idRegion" class="form-select" required>
                        <option value="">-- Seleccione Región --</option>
                        <c:forEach var="r" items="${regiones}">
                            <option value="${r.idRegion}">${r.nombreRegion}</option>
                        </c:forEach>
                    </select>
                </div>

                <!-- Selección de Plato -->
                <div class="mb-3">
                    <label class="form-label">Plato</label>
                    <select id="selPlato" name="idPlato" class="form-select" disabled>
                        <option value="">Seleccione primero región</option>
                    </select>
                </div>

                <!-- Pestañas de navegación -->
                <ul class="nav nav-tabs mt-4" id="tabGestion" role="tablist">
                    <li class="nav-item" role="presentation">
                        <button class="nav-link active" id="tab-datos-tab" data-bs-toggle="tab" data-bs-target="#datos" type="button" role="tab">
                            Datos del Plato
                        </button>
                    </li>
                    <li class="nav-item" role="presentation">
                        <button class="nav-link" id="tab-receta-tab" data-bs-toggle="tab" data-bs-target="#receta" type="button" role="tab">
                            Ingredientes / Receta
                        </button>
                    </li>
                </ul>

                <!-- Contenido de las pestañas -->
                <div class="tab-content border p-3" id="tabContent">
                    <!-- Pestaña Datos -->
                    <div class="tab-pane fade show active" id="datos" role="tabpanel" aria-labelledby="tab-datos-tab">

                        <input type="hidden" name="idPlato" id="hidPlato"/>
                        <div class="row">
                            <div class="col-md-6 mb-2">
                                <label class="form-label">Nombre</label>
                                <input type="text" id="nombrePlato" name="nombrePlato" class="form-control" required/>
                            </div>
                            <div class="col-md-3 mb-2">
                                <label class="form-label">Precio</label>
                                <input type="number" step="0.01" id="precioVenta" name="precioVenta" class="form-control" required/>
                            </div>
                            <div class="col-md-3 mb-2">
                                <label class="form-label">Nivel Complejidad</label>
                                <input type="number" min="1" max="5" id="nivelComplejidad" name="nivelComplejidad" class="form-control" required/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-2">
                                <label class="form-label">URL Foto</label>
                                <input type="url" id="foto" name="foto" class="form-control"/>
                            </div>
                            <div class="col-md-6 mb-2">
                                <label class="form-label">Categoría</label>
                                <select id="idCategoria" name="idCategoria" class="form-select" required>
                                    <option value="">Seleccione...</option>
                                    <c:forEach var="c" items="${categorias}">
                                        <option value="${c.idCategoria}">${c.nombreCategoria}</option>
                                    </c:forEach>
                                </select>
                                <div class="mb-3">
                                    <label class="form-label">Descripción</label>
                                    <textarea id="descripcion" name="descripcion" class="form-control" required>${plato.descripcion}</textarea>
                                </div>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Turnos disponibles</label><br/>
                            <c:forEach var="t" items="${turnos}">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="chkTurno${t.idTurno}" name="turnos" value="${t.idTurno}"/>
                                    <label class="form-check-label" for="chkTurno${t.idTurno}">${t.nombreTurno}</label>
                                </div>
                            </c:forEach>
                        </div>
                        <button type="button" id="btnGuardarPlato" class="btn btn-primary">Guardar Plato</button>
                        <button type="button" id="btnEliminarPlato" class="btn btn-danger ms-2">Eliminar Plato</button>
                    </div>

                    <!-- Pestaña Receta -->
                    <div class="tab-pane fade" id="receta" role="tabpanel" aria-labelledby="tab-receta-tab">
                        <button type="button" id="btnAgregarIngrediente" class="btn btn-success mb-2">+ Añadir Ingrediente</button>
                        <table class="table table-bordered" id="tblReceta">
                            <thead class="table-light">
                                <tr>
                                    <th>Ingrediente</th>
                                    <th>Cantidad</th>
                                    <th>Unidad</th>
                                    <th>Preparación</th>
                                    <th>Acción</th>
                                </tr>
                            </thead>
                            <tbody></tbody>
                        </table>
                    </div>
                </div>
            </form>
        </div>


        <script>
            // 1. Inicializamos el array ingredientes usando JSTL UNA ÚNICA VEZ
            const ingredientes = [
            <c:forEach var="ing" items="${ingredientes}" varStatus="st">
            {
            idIngrediente: ${ing.idIngrediente},
                    nombre: '${fn:escapeXml(ing.nombreIngrediente)}',
                    unidad: '${fn:escapeXml(ing.unidadMedida)}'
            }<c:if test="${!st.last}">,</c:if>
            </c:forEach>
            ];
            console.log('ingredientes iniciales:', ingredientes);
            // 2. Filtrar platos por región
            $('#selRegion').change(function () {
                const idReg = $(this).val();
                $('#hidRegion').val(idReg);
                console.log('Se seleccionó región:', idReg);
                $('#selPlato')
                        .prop('disabled', true)
                        .html('<option>Cargando...</option>');

                if (!idReg) {
                    $('#selPlato')
                            .prop('disabled', true)
                            .html('<option>Seleccione región</option>');
                    return;
                }

                const url = '${pageContext.request.contextPath}/PlatoController';
                $.getJSON(url, {action: 'filtrarPorRegion', region: idReg}, function (list) {
                    console.log('Respuesta AJAX:', list);
                    let opts = '<option value="">-- Seleccione Plato --</option>';
                    list.forEach(p => {
                        opts += '<option value="' + p.idPlato + '">' + p.nombrePlato + '</option>';
                    });
                    console.log('HTML opciones:', opts);
                    $('#selPlato').prop('disabled', false).html(opts);
                });
            });

            // 3. Cargar datos del plato seleccionado
            $('#selPlato').change(function () {
                const id = $(this).val();
                $('#hidPlato').val(id);
                $('#hidPlato').val(id ? id : 0);
                console.log('Se seleccionó plato:', id);
                if (!id) {
                    $('#frmPlato')[0].reset();
                    $('#tblReceta tbody').empty();
                    return;
                }

                $.getJSON('${pageContext.request.contextPath}/PlatoController', {action: 'obtener', idPlato: id}, function (p) {
                    // Datos generales
                    console.log('Datos recibidos de "obtener":', p);
                    $('#idPlato').val(p.idPlato);
                    $('#nombrePlato').val(p.nombrePlato);
                    $('#descripcion').val(p.descripcion);
                    $('#precioVenta').val(p.precioVenta);
                    $('#nivelComplejidad').val(p.nivelComplejidad);
                    $('#foto').val(p.foto);
                    $('#idCategoria').val(p.idCategoria);
                    $('#idRegion').val(p.idRegion);

                    // Turnos
                    $('input[name="turnos"]').prop('checked', false);
                    p.turnosAsignados.forEach(t => {
                        $('#chkTurno' + t).prop('checked', true);
                    });

                    // Receta
                    const rows = p.receta.map(r => filaReceta(r)).join('');
                    $('#tblReceta tbody').html(rows);
                });
            });

            // 4. Función para generar una fila de receta en JS
            function filaReceta(r) {
                let options = '<option value="">Seleccione...</option>';
                ingredientes.forEach(i => {
                    const selected = i.idIngrediente === r.idIngrediente ? ' selected' : '';
                    options += '<option value="' + i.idIngrediente + '"' + selected + '>' + i.nombre + '</option>';
                });
                const cantidad = r.cantidadRequerida || '';
                const unidad = r.unidadMedida || '';
                const prep = r.descripcionPreparacion || '';

                return (
                        '<tr>' +
                        '<td><select class="form-select selIng">' + options + '</select></td>' +
                        '<td><input type="number" class="form-control txtCant" value="' + cantidad + '"/></td>' +
                        '<td><input type="text" class="form-control txtUni" value="' + unidad + '" readonly/></td>' +
                        '<td><input type="text" class="form-control txtPrep" value="' + prep + '"/></td>' +
                        '<td><button class="btn btn-danger btn-sm" onclick="$(this).closest(\'tr\').remove()">–</button></td>' +
                        '</tr>'
                        );
            }

            // 5. Añadir nueva fila vacía
            $('#btnAgregarIngrediente').click(function () {
                $('#tblReceta tbody').append(filaReceta({}));
            });

            // 6. Actualizar unidad al cambiar ingrediente
            $(document).on('change', '.selIng', function () {
                const id = parseInt($(this).val(), 10);
                const uni = (ingredientes.find(i => i.idIngrediente === id) || {}).unidad || '';
                $(this).closest('tr').find('.txtUni').val(uni);
            });

  
            $('#btnGuardarPlato').click(function () {
  // 1.1 Flag: entrada al handler
  console.log('>> [JS] Click Guardar Plato - inicio');

  // 1.2 Recopilación de datos
  const idPlato = parseInt($('#hidPlato').val(), 10) || 0;
  console.log('>> [JS] idPlato (antes de decidir acción):', idPlato);

  const action = idPlato === 0 ? 'nuevo' : 'actualizar';
  console.log('>> [JS] Acción seleccionada:', action);

  const data = { action, idPlato,
    nombrePlato: $('#nombrePlato').val(),
    descripcion: $('#descripcion').val(),
    precioVenta: $('#precioVenta').val(),
    nivelComplejidad: $('#nivelComplejidad').val(),
    foto: $('#foto').val(),
    idCategoria: $('#idCategoria').val(),
    idRegion: $('#selRegion').val(),
    turnos: [], idIngrediente: [], cantidadRequerida: [], descripcionPreparacion: [] };

  // 1.3 Flags de datos recetados
  console.log('>> [JS] Campos básicos:', {
    nombrePlato: data.nombrePlato,
    descripcion: data.descripcion,
    precioVenta: data.precioVenta,
    nivelComplejidad: data.nivelComplejidad,
    idCategoria: data.idCategoria,
    idRegion: data.idRegion
  });

  // 1.4 Turnos
  $('input[name="turnos"]:checked').each(function () {
    data.turnos.push($(this).val());
  });
  console.log('>> [JS] Turnos seleccionados:', data.turnos);

  // 1.5 Receta
  $('#tblReceta tbody tr').each(function (i) {
    const ing = $(this).find('.selIng').val();
    const cant = $(this).find('.txtCant').val();
    const prep = $(this).find('.txtPrep').val();
    data.idIngrediente.push(ing);
    data.cantidadRequerida.push(cant);
    data.descripcionPreparacion.push(prep);
    console.log(`>> [JS] Receta fila ${i}: ing=${ing}, cant=${cant}, prep=${prep}`);
  });

  console.log('>> [JS] Data final a enviar:', data);

  $.ajax({
    url: '${pageContext.request.contextPath}/PlatoController',
    method: 'POST',
    data, traditional: true,
    success: function () {
      console.log('<< [JS] Guardado exitoso, recargando...');
      alert('Plato guardado con éxito.');
      window.location.reload();
    },
    error: function (xhr, status, error) {
      console.error('<< [JS] Error en guardado:', status, error, xhr.responseText);
      alert('Error guardando plato: ' + xhr.responseText);
    }
  });
});



            // 8. Guardar sólo receta
            $('#btnGuardarReceta').click(function () {
                const filas = [];
                $('#tblReceta tbody tr').each(function () {
                    filas.push({
                        idIngrediente: $(this).find('.selIng').val(),
                        cantidadRequerida: $(this).find('.txtCant').val(),
                        descripcionPreparacion: $(this).find('.txtPrep').val()
                    });
                });
                $.ajax({
                    url: '${pageContext.request.contextPath}/PlatoController',
                    method: 'POST',
                    data: {
                        action: 'guardarReceta',
                        idPlato: $('#idPlato').val(),
                        receta: JSON.stringify(filas)
                    },
                    success: function () {
                        alert('Receta guardada con éxito.');
                    }
                });
            });
            
            
            $('#btnEliminarPlato').click(function() {
  const id = $('#hidPlato').val();
  if (!id) {
    alert('Seleccione primero un plato.');
    return;
  }
  if (!confirm('¿Seguro que desea eliminar este plato?')) return;
  $.ajax({
    url: '${pageContext.request.contextPath}/PlatoController',
    method: 'POST',
    data: { action: 'eliminar', id: id },
    success: function() {
      alert('Plato eliminado con éxito.');
      window.location.reload();
    },
    error: function(xhr) {
      alert('No se pudo eliminar: ' + xhr.responseText);
    }
  });
});
        </script>

    </body>
</html>










