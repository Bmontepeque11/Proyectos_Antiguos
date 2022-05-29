/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.edd.tarea1.Pila;

/**
 *
 * @author Bryan
 */
public class NodoPila {
    private int ID;    
    private String ValorNodo;
    private NodoPila Siguiente;

    public NodoPila(int ID, String ValorNodo) {
        this.ID = ID;
        this.ValorNodo = ValorNodo;
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the ValorNodo
     */
    public String getValorNodo() {
        return ValorNodo;
    }

    /**
     * @param ValorNodo the ValorNodo to set
     */
    public void setValorNodo(String ValorNodo) {
        this.ValorNodo = ValorNodo;
    }

    /**
     * @return the Siguiente
     */
    public NodoPila getSiguiente() {
        return Siguiente;
    }

    /**
     * @param Siguiente the Siguiente to set
     */
    public void setSiguiente(NodoPila Siguiente) {
        this.Siguiente = Siguiente;
    }
    
}
