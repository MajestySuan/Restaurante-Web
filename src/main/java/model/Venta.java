/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Venta {
    private int idVenta;
    private LocalDate fechaVenta;
    private int cantidad;
    private BigDecimal precioUnitario;
    private int idPlato;
    private int idTurno;
    private int idCarta;
    private int idEmpleado;

    // Constructor vacío
    public Venta() {}

    // Constructor completo
    public Venta(int idVenta, LocalDate fechaVenta, int cantidad,
                 BigDecimal precioUnitario, int idPlato,
                 int idTurno, int idCarta, int idEmpleado) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.idPlato = idPlato;
        this.idTurno = idTurno;
        this.idCarta = idCarta;
        this.idEmpleado = idEmpleado;
    }

    // Getters y Setters
    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }

    public LocalDate getFechaVenta() { return fechaVenta; }
    public void setFechaVenta(LocalDate fechaVenta) { this.fechaVenta = fechaVenta; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }

    public int getIdPlato() { return idPlato; }
    public void setIdPlato(int idPlato) { this.idPlato = idPlato; }

    public int getIdTurno() { return idTurno; }
    public void setIdTurno(int idTurno) { this.idTurno = idTurno; }

    public int getIdCarta() { return idCarta; }
    public void setIdCarta(int idCarta) { this.idCarta = idCarta; }

    public int getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(int idEmpleado) { this.idEmpleado = idEmpleado; }

    @Override
    public String toString() {
        return "Venta{" +
                "idVenta=" + idVenta +
                ", fechaVenta=" + fechaVenta +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", idPlato=" + idPlato +
                ", idTurno=" + idTurno +
                ", idCarta=" + idCarta +
                ", idEmpleado=" + idEmpleado +
                '}';
    }
}