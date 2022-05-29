/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.edd.tarea1.ListaSimple;
import com.mycompany.edd.tarea1.Pila.Pila;
/**
 *
 * @author Bryan
 */
public class NodoListaSimple {
    private Pila PilaNodo;
    private NodoListaSimple Siguiente;
    
    public void NodoListaSimple(){
        this.setPilaNodo(null);
        this.setSiguiente(null);
    }

    /**
     * @return the PilaNodo
     */
    public Pila getPilaNodo() {
        return PilaNodo;
    }

    /**
     * @param PilaNodo the PilaNodo to set
     */
    public void setPilaNodo(Pila PilaNodo) {
        this.PilaNodo = PilaNodo;
    }

    /**
     * @return the Siguiente
     */
    public NodoListaSimple getSiguiente() {
        return Siguiente;
    }

    /**
     * @param Siguiente the Siguiente to set
     */
    public void setSiguiente(NodoListaSimple Siguiente) {
        this.Siguiente = Siguiente;
    }

}

