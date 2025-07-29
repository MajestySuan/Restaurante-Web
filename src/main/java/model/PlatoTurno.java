/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author zudex
 */
public class PlatoTurno {
    private int idPlato;
    private int idTurno;

    public PlatoTurno() { }

    public PlatoTurno(int idPlato, int idTurno) {
        this.idPlato = idPlato;
        this.idTurno = idTurno;
    }

    public int getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(int idPlato) {
        this.idPlato = idPlato;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    @Override
    public String toString() {
        return "PlatoTurno{" +
               "idPlato=" + idPlato +
               ", idTurno=" + idTurno +
               '}';
    }
}