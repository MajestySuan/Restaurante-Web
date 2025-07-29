/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author zudex
 */
import java.time.LocalDateTime;

public class Stock {
    private int idStock;
    private int idIngrediente;
    private double cantidad;
    private LocalDateTime fechaRegistro;

    public Stock() { }

    public Stock(int idStock, int idIngrediente, double cantidad, LocalDateTime fechaRegistro) {
        this.idStock = idStock;
        this.idIngrediente = idIngrediente;
        this.cantidad = cantidad;
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdStock() {
        return idStock;
    }

    public void setIdStock(int idStock) {
        this.idStock = idStock;
    }

    public int getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "Stock{" +
               "idStock=" + idStock +
               ", idIngrediente=" + idIngrediente +
               ", cantidad=" + cantidad +
               ", fechaRegistro=" + fechaRegistro +
               '}';
    }
}