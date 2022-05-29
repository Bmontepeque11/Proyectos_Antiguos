/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.edd.tarea1.Pila;

/**
 *
 * @author Bryan
 */
public class Pila {
    NodoPila Cima;
    int Tamaño;

    public Pila() {
        Cima = null;
        Tamaño = 0;
    }
    
    public boolean estaVacia(){
        return Cima == null;
    }
    public void push(int ID, String ValorNodo){//Agrega Valores a la Pila
        NodoPila NuevoNodo = new NodoPila(ID, ValorNodo);
        NuevoNodo.setSiguiente(Cima);
        Cima = NuevoNodo;
        Tamaño++;
    }
    
    public void MostrarP(){//Muestra todo lo que hay en la pila
        NodoPila Aux = Cima;
        int ContadorP = 1;
        while(Aux != null){
            System.out.print(ContadorP + ")"+ Aux.getValorNodo()+" ");
            
            ContadorP++;
            Aux = Aux.getSiguiente();
        }
        
    }
    
    public NodoPila peek(){//Muestra el Ultimo Valor y ya
        return Cima;
    }
    
    public int TamañoPila(){
        return Tamaño;
    }
    
    public void pop(){//Muestra y elimina el Ultimo valor, en este caso solo Elimina
        Cima = Cima.getSiguiente();
        Tamaño--;
    }
    
}
