/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author zudex
 */
public class Ingrediente {
    private int idIngrediente;
    private String nombreIngrediente;
    private String unidadMedida;

    public Ingrediente() { }

    public Ingrediente(int idIngrediente, String nombreIngrediente, String unidadMedida) {
        this.idIngrediente = idIngrediente;
        this.nombreIngrediente = nombreIngrediente;
        this.unidadMedida = unidadMedida;
    }

    public int getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    @Override
    public String toString() {
        return "Ingrediente{" +
               "idIngrediente=" + idIngrediente +
               ", nombreIngrediente='" + nombreIngrediente + '\'' +
               ", unidadMedida='" + unidadMedida + '\'' +
               '}';
    }
}