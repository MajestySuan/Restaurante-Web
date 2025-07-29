<%-- 
    Document   : index
    Created on : 5/07/2025, 5:53:01 p. m.
    Author     : zudex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head><title>Menú Principal</title>
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



      
        <div class="hero">
            <div class="hero-overlay text-center">
                <h1 class="display-4 fw-bold">¡Bienvenido a COLOMBIANO, COMA CONTENTO!</h1>
                <p class="lead">Sabores auténticos de Colombia, tradición y pasión en cada plato.</p>
                <a href="PlatoController" class="btn btn-warning btn-lg mt-3">
                    <i class="bi bi-egg-fried"></i> Explora nuestro menú
                </a>
            </div>
        </div>
        <div class="container text-center mt-5">
            <h2 class="section-title">¿Por qué elegirnos?</h2>
            <div class="row justify-content-center">
                <div class="col-md-4 mb-3">
                    <i class="bi bi-emoji-heart-eyes display-4 text-warning"></i>
                    <h4>Platos típicos</h4>
                    <p>Recetas auténticas de todas las regiones de Colombia, preparadas con ingredientes frescos y locales.</p>
                </div>
                <div class="col-md-4 mb-3">
                    <i class="bi bi-people-fill display-4 text-warning"></i>
                    <h4>Ambiente familiar</h4>
                    <p>Un espacio cálido y acogedor para compartir con amigos, familia o compañeros de trabajo.</p>
                </div>
                <div class="col-md-4 mb-3">
                    <i class="bi bi-star-fill display-4 text-warning"></i>
                    <h4>Calidad y servicio</h4>
                    <p>Atención personalizada y compromiso con la excelencia en cada detalle.</p>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
    <footer class="bg-dark text-white text-center py-3 mt-5">
        <div class="container">
            <small>&copy; 2025 COLOMBIANO, COMA CONTENTO. Todos los derechos reservados.</small>
        </div>
    </footer>
    
</html>