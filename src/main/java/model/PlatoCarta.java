/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author zudex
 */
public class PlatoCarta {
    private int idCarta;
    private int idPlato;

    public PlatoCarta() { }

    public PlatoCarta(int idCarta, int idPlato) {
        this.idCarta = idCarta;
        this.idPlato = idPlato;
    }

    public int getIdCarta() {
        return idCarta;
    }

    public void setIdCarta(int idCarta) {
        this.idCarta = idCarta;
    }

    public int getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(int idPlato) {
        this.idPlato = idPlato;
    }

    @Override
    public String toString() {
        return "PlatoCarta{" +
               "idCarta=" + idCarta +
               ", idPlato=" + idPlato +
               '}';
    }
}