/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.t2_edd;

/**
 *
 * @author Bryan
 */
public class NodoPila { 
    private int ValorNodo;
    private NodoPila Siguiente;

    public NodoPila(int ValorNodo) {
        this.ValorNodo = ValorNodo;
    }

    /**
     * @return the ValorNodo
     */
    public int getValorNodo() {
        return ValorNodo;
    }

    /**
     * @param ValorNodo the ValorNodo to set
     */
    public void setValorNodo(int ValorNodo) {
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
