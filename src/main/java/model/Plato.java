/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import model.Receta;
import java.util.List;
/**
 *
 * @author zudex
 */
import java.math.BigDecimal;

public class Plato {

    private int idPlato;
    private String nombrePlato;
    private String descripcion;
    private int nivelComplejidad;
    private String foto;
    private BigDecimal precioVenta;
    private int idCategoria;
    private int idRegion;
    private String categoriaNombre;
    private String regionNombre;
 // Lista de IDs de turnos asignados (desayuno, almuerzo, cena)
    private List<Integer> turnosAsignados;

    // Lista de recetas: cada elemento indica ingrediente, cantidad y preparación
    private List<Receta> receta;

    public Plato() {
    }

    public Plato(int idPlato,
                 String nombrePlato,
                 String descripcion,
                 int nivelComplejidad,
                 String foto,
                 BigDecimal precioVenta,
                 int idCategoria,
                 int idRegion,
                 List<Integer> turnosAsignados,
                 List<Receta> receta) {
        this.idPlato = idPlato;
        this.nombrePlato = nombrePlato;
        this.descripcion = descripcion;
        this.nivelComplejidad = nivelComplejidad;
        this.foto = foto;
        this.precioVenta = precioVenta;
        this.idCategoria = idCategoria;
        this.idRegion = idRegion;
        this.turnosAsignados = turnosAsignados;
        this.receta = receta;
    }

     public List<Integer> getTurnosAsignados() {
        return turnosAsignados;
    }

    public void setTurnosAsignados(List<Integer> turnosAsignados) {
        this.turnosAsignados = turnosAsignados;
    }

    // Getters y setters para receta
    public List<Receta> getReceta() {
        return receta;
    }

    public void setReceta(List<Receta> receta) {
        this.receta = receta;
    }

    public int getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(int idPlato) {
        this.idPlato = idPlato;
    }

    public String getNombrePlato() {
        return nombrePlato;
    }

    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNivelComplejidad() {
        return nivelComplejidad;
    }

    public void setNivelComplejidad(int nivelComplejidad) {
        this.nivelComplejidad = nivelComplejidad;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    public String getRegionNombre() {
        return regionNombre;
    }

    public void setRegionNombre(String regionNombre) {
        this.regionNombre = regionNombre;
    }

    @Override
    public String toString() {
        return "Plato{"
                + "idPlato=" + idPlato
                + ", nombrePlato='" + nombrePlato + '\''
                + ", descripcion='" + descripcion + '\''
                + ", nivelComplejidad=" + nivelComplejidad
                + ", foto='" + foto + '\''
                + ", precioVenta=" + precioVenta
                + ", idCategoria=" + idCategoria
                + ", idRegion=" + idRegion
                + '}';
    }
}
