/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labeddtarea1;


/**
 *
 * @author Bryan
 */
public class NodoLS {
    private int DatoNodo;
    private NodoLS Siguiente;
    
    public void NodoListaSimple(){
        setDatoNodo(0);
        setSiguiente(null);
    }

    /**
     * @return the DatoNodo
     */
    public int getDatoNodo() {
        return DatoNodo;
    }

    /**
     * @param DatoNodo the DatoNodo to set
     */
    public void setDatoNodo(int DatoNodo) {
        this.DatoNodo = DatoNodo;
    }

    /**
     * @return the Siguiente
     */
    public NodoLS getSiguiente() {
        return Siguiente;
    }

    /**
     * @param Siguiente the Siguiente to set
     */
    public void setSiguiente(NodoLS Siguiente) {
        this.Siguiente = Siguiente;
    }
}
