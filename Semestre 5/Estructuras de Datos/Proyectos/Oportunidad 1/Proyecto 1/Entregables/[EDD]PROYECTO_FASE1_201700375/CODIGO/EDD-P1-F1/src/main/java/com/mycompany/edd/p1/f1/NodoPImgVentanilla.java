/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.edd.p1.f1;

/**
 *
 * @author Bryan
 */
public class NodoPImgVentanilla {
    private int id_img;
    private int id_clienteDueño;
    private String tipo_de_img;
    private NodoPImgVentanilla Siguiente;

    public NodoPImgVentanilla(int id_img, int id_clienteDueño, String tipo_de_img) {
        this.id_img = id_img;
        this.id_clienteDueño = id_clienteDueño;
        this.tipo_de_img = tipo_de_img;
    }

    /**
     * @return the id_img
     */
    public int getId_img() {
        return id_img;
    }

    /**
     * @param id_img the id_img to set
     */
    public void setId_img(int id_img) {
        this.id_img = id_img;
    }

    /**
     * @return the id_clienteDueño
     */
    public int getId_clienteDueño() {
        return id_clienteDueño;
    }

    /**
     * @param id_clienteDueño the id_clienteDueño to set
     */
    public void setId_clienteDueño(int id_clienteDueño) {
        this.id_clienteDueño = id_clienteDueño;
    }

    /**
     * @return the tipo_de_img
     */
    public String getTipo_de_img() {
        return tipo_de_img;
    }

    /**
     * @param tipo_de_img the tipo_de_img to set
     */
    public void setTipo_de_img(String tipo_de_img) {
        this.tipo_de_img = tipo_de_img;
    }

    /**
     * @return the Siguiente
     */
    public NodoPImgVentanilla getSiguiente() {
        return Siguiente;
    }

    /**
     * @param Siguiente the Siguiente to set
     */
    public void setSiguiente(NodoPImgVentanilla Siguiente) {
        this.Siguiente = Siguiente;
    }

}
