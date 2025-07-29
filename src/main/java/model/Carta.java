/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author zudex
 */
import java.time.LocalDate;

public class Carta {
    private int idCarta;
    private String nombreCarta;
    private LocalDate fechaInicioVigencia;
    private LocalDate fechaFinVigencia;
    private boolean activa;

    public Carta() { }

    public Carta(int idCarta, String nombreCarta,
                 LocalDate fechaInicioVigencia, LocalDate fechaFinVigencia,
                 boolean activa) {
        this.idCarta = idCarta;
        this.nombreCarta = nombreCarta;
        this.fechaInicioVigencia = fechaInicioVigencia;
        this.fechaFinVigencia = fechaFinVigencia;
        this.activa = activa;
    }

    public int getIdCarta() {
        return idCarta;
    }

    public void setIdCarta(int idCarta) {
        this.idCarta = idCarta;
    }

    public String getNombreCarta() {
        return nombreCarta;
    }

    public void setNombreCarta(String nombreCarta) {
        this.nombreCarta = nombreCarta;
    }

    public LocalDate getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    public void setFechaInicioVigencia(LocalDate fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    public LocalDate getFechaFinVigencia() {
        return fechaFinVigencia;
    }

    public void setFechaFinVigencia(LocalDate fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    @Override
    public String toString() {
        return "Carta{" +
               "idCarta=" + idCarta +
               ", nombreCarta='" + nombreCarta + '\'' +
               ", fechaInicioVigencia=" + fechaInicioVigencia +
               ", fechaFinVigencia=" + fechaFinVigencia +
               ", activa=" + activa +
               '}';
    }
}
