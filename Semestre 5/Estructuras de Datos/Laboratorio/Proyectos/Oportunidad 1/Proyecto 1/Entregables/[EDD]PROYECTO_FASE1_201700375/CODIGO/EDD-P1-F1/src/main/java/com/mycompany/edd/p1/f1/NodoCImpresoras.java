/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.edd.p1.f1;

/**
 *
 * @author Bryan
 */
public class NodoCImpresoras {
    private int id_cliente;
    private String nombre_Impresora;
    private String nombre_cliente;
    private int imagenes_a_imprimir;
    private NodoCImpresoras Siguiente;

    public NodoCImpresoras(int id_cliente,String nombre_Impresora, String nombre_cliente, int imagenes_a_imprimir) {
        this.id_cliente = id_cliente;
        this.nombre_Impresora = nombre_Impresora;
        this.nombre_cliente = nombre_cliente;
        this.imagenes_a_imprimir = imagenes_a_imprimir;
    }

    /**
     * @return the id_cliente
     */
    public int getId_cliente() {
        return id_cliente;
    }

    /**
     * @param id_cliente the id_cliente to set
     */
    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    /**
     * @return the nombre_Impresora
     */
    public String getNombre_Impresora() {
        return nombre_Impresora;
    }

    /**
     * @param nombre_Impresora the nombre_Impresora to set
     */
    public void setNombre_Impresora(String nombre_Impresora) {
        this.nombre_Impresora = nombre_Impresora;
    }

    /**
     * @return the nombre_cliente
     */
    public String getNombre_cliente() {
        return nombre_cliente;
    }

    /**
     * @param nombre_cliente the nombre_cliente to set
     */
    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    /**
     * @return the imagenes_a_imprimir
     */
    public int getImagenes_a_imprimir() {
        return imagenes_a_imprimir;
    }

    /**
     * @param imagenes_a_imprimir the imagenes_a_imprimir to set
     */
    public void setImagenes_a_imprimir(int imagenes_a_imprimir) {
        this.imagenes_a_imprimir = imagenes_a_imprimir;
    }

    /**
     * @return the Siguiente
     */
    public NodoCImpresoras getSiguiente() {
        return Siguiente;
    }

    /**
     * @param Siguiente the Siguiente to set
     */
    public void setSiguiente(NodoCImpresoras Siguiente) {
        this.Siguiente = Siguiente;
    }

}
