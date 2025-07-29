/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author zudex
 */
public class Receta {

    private int idPlato;
    private int idIngrediente;
    private double cantidadRequerida;
    private String descripcionPreparacion;
    private String unidadMedida;

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Receta() {
    }

    public Receta(int idPlato, int idIngrediente,
            double cantidadRequerida, String descripcionPreparacion) {
        this.idPlato = idPlato;
        this.idIngrediente = idIngrediente;
        this.cantidadRequerida = cantidadRequerida;
        this.descripcionPreparacion = descripcionPreparacion;
    }

    public int getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(int idPlato) {
        this.idPlato = idPlato;
    }

    public int getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public double getCantidadRequerida() {
        return cantidadRequerida;
    }

    public void setCantidadRequerida(double cantidadRequerida) {
        this.cantidadRequerida = cantidadRequerida;
    }

    public String getDescripcionPreparacion() {
        return descripcionPreparacion;
    }

    public void setDescripcionPreparacion(String descripcionPreparacion) {
        this.descripcionPreparacion = descripcionPreparacion;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Receta{"
                + "idPlato=" + idPlato
                + ", idIngrediente=" + idIngrediente
                + ", cantidadRequerida=" + cantidadRequerida
                + ", descripcionPreparacion='" + descripcionPreparacion + '\''
                + '}';
    }
}
