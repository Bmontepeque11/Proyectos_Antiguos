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
public class ListaSimple {
    private NodoListaSimple Inicio;
    private int Size;
    
    public void ListaSimple(){
        Inicio = null;
        Size = 0;
    }
    
    //Revisar si está vacía la Lista
    public boolean EsVacia(){
        return Inicio == null;
    }
    
    //Agregar al Final
    public void AgregarF(Pila PilaNueva){
        NodoListaSimple Nuevo = new NodoListaSimple();
        Nuevo.setPilaNodo(PilaNueva);
        
        if (EsVacia()){
            Inicio = Nuevo;    
        } else {
            NodoListaSimple Aux = Inicio;
            
            //Recorrer la Lista hasta el final
            while(Aux.getSiguiente() != null){
                Aux = Aux.getSiguiente();
            }
            Aux.setSiguiente(Nuevo);
        }
        Size++;
    } 
    
    //Mostrar todos los nodos de la lista
    public void Mostrar(){
        NodoListaSimple Aux = Inicio;
        while(Aux != null){
            
            System.out.println("Pila del Nodo: ");            
            Aux.getPilaNodo().MostrarP();
            System.out.println("");
            System.out.println("");
                    
            Aux = Aux.getSiguiente();
        }
    }
}
