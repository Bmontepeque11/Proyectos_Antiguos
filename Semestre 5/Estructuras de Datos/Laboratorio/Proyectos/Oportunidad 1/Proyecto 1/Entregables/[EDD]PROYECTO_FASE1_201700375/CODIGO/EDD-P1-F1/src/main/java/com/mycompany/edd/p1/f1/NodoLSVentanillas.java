/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.edd.p1.f1;

/**
 *
 * @author Bryan
 */
public class NodoLSVentanillas {
    private int id_ventanilla;
    private int pasos_ocupada;
    private boolean estaOcupada;
    private PImgVentanilla Pila_img;
    private NodoCRecepcion ClienteActual; 
    private NodoLSVentanillas Siguiente;

    public NodoLSVentanillas(int id_ventanilla, int pasos_ocupada, boolean estaOcupada, PImgVentanilla Pila_img, NodoCRecepcion ClienteActual) {
        this.id_ventanilla = id_ventanilla;
        this.pasos_ocupada = pasos_ocupada;
        this.estaOcupada = estaOcupada;
        this.ClienteActual = ClienteActual;
        this.Pila_img = Pila_img;
    }

    /**
     * @return the id_ventanilla
     */
    public int getId_ventanilla() {
        return id_ventanilla;
    }

    /**
     * @param id_ventanilla the id_ventanilla to set
     */
    public void setId_ventanilla(int id_ventanilla) {
        this.id_ventanilla = id_ventanilla;
    }

    /**
     * @return the pasos_ocupada
     */
    public int getPasos_ocupada() {
        return pasos_ocupada;
    }

    /**
     * @param pasos_ocupada the pasos_ocupada to set
     */
    public void setPasos_ocupada(int pasos_ocupada) {
        this.pasos_ocupada = pasos_ocupada;
    }

    /**
     * @return the estaOcupada
     */
    public boolean isEstaOcupada() {
        return estaOcupada;
    }

    /**
     * @param estaOcupada the estaOcupada to set
     */
    public void setEstaOcupada(boolean estaOcupada) {
        this.estaOcupada = estaOcupada;
    }

    /**
     * @return the Siguiente
     */
    public NodoLSVentanillas getSiguiente() {
        return Siguiente;
    }

    /**
     * @param Siguiente the Siguiente to set
     */
    public void setSiguiente(NodoLSVentanillas Siguiente) {
        this.Siguiente = Siguiente;
    }

    /**
     * @return the Pila_img
     */
    public PImgVentanilla getPila_img() {
        return Pila_img;
    }

    /**
     * @param Pila_img the Pila_img to set
     */
    public void setPila_img(PImgVentanilla Pila_img) {
        this.Pila_img = Pila_img;
    }

    /**
     * @return the ClienteActual
     */
    public NodoCRecepcion getClienteActual() {
        return ClienteActual;
    }

    /**
     * @param ClienteActual the ClienteActual to set
     */
    public void setClienteActual(NodoCRecepcion ClienteActual) {
        this.ClienteActual = ClienteActual;
    }

}
