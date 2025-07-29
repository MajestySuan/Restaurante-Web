<%-- 
    Document   : listaVentas
    Created on : 7/07/2025, 8:45:50 p. m.
    Author     : zudex
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="get" action="VentaController">
  <input type="hidden" name="action" value="historial"/>
  <div class="row mb-3">
    <div class="col">
      <label>Desde</label>
      <input type="date" name="fechaInicio" class="form-control"/>
    </div>
    <div class="col">
      <label>Hasta</label>
      <input type="date" name="fechaFin" class="form-control"/>
    </div>
    <div class="col">
      <label>Plato</label>
      <select name="idPlato" class="form-select">
        <option value="">Todos</option>
        <% for (model.Plato p : (List<model.Plato>)request.getAttribute("platos")) { %>
          <option value="<%= p.getIdPlato() %>"><%= p.getNombrePlato() %></option>
        <% } %>
      </select>
    </div>
    <div class="col">
      <label>Empleado</label>
      <select name="idEmpleado" class="form-select">
        <option value="">Todos</option>
        <% for (model.Empleado e : (List<model.Empleado>)request.getAttribute("empleados")) { %>
          <option value="<%= e.getIdEmpleado() %>"><%= e.getNombreEmpleado() %></option>
        <% } %>
      </select>
    </div>
    <div class="col">
      <label>Turno</label>
      <select name="idTurno" class="form-select">
        <option value="">Todos</option>
        <% for (model.Turno t : (List<model.Turno>)request.getAttribute("turnos")) { %>
          <option value="<%= t.getIdTurno() %>"><%= t.getNombreTurno() %></option>
        <% } %>
      </select>
    </div>
    <div class="col d-flex align-items-end">
      <button class="btn btn-primary">Filtrar</button>
    </div>
  </div>
</form>

<table class="table table-bordered">
  <thead>
    <tr>
      <th>Fecha</th>
      <th>Plato</th>
      <th>Cantidad</th>
      <th>Precio Unitario</th>
      <th>Total</th>
      <th>Empleado</th>
      <th>Turno</th>
      <th>Carta</th>
    </tr>
  </thead>
  <tbody>
    <% List<Map<String, Object>> ventas = (List<Map<String, Object>>)request.getAttribute("ventas");
       if (ventas == null || ventas.isEmpty()) { %>
      <tr><td colspan="8" class="text-center">No hay ventas registradas.</td></tr>
    <% } else {
         for (Map<String, Object> v : ventas) { %>
      <tr>
        <td><%= v.get("fechaVenta") %></td>
        <td><%= v.get("nombrePlato") %></td>
        <td><%= v.get("cantidad") %></td>
        <td>$<%= v.get("precioUV") %></td>
        <td>$<%= ((java.math.BigDecimal)v.get("precioUV")).multiply(new java.math.BigDecimal((int)v.get("cantidad"))) %></td>
        <td><%= v.get("nombreEmpleado") %></td>
        <td><%= v.get("nombreTurno") %></td>
        <td><%= v.get("nombreCarta") %></td>
      </tr>
    <%   }
       } %>
  </tbody>
</table>

    </body>
</html>
