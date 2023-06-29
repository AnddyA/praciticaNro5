/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author andy
 */
public class Anime {
    
    private Integer id;
    private String nombre;
    private String estado;
    private Integer generoA;
    private String NumTemp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getGeneroA() {
        return generoA;
    }

    public void setGeneroA(Integer generoA) {
        this.generoA = generoA;
    }

    public String getNumTemp() {
        return NumTemp;
    }

    public void setNumTemp(String NumTemp) {
        this.NumTemp = NumTemp;
    } 
}
