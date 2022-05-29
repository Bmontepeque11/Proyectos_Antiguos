/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.edd.tarea1;

import com.mycompany.edd.tarea1.ListaSimple.ListaSimple;
import com.mycompany.edd.tarea1.Pila.Pila;

/**
 *
 * @author Bryan
 */
public class main {
    public static void main(String[] args){
        //Crear Objetos Pila
        Pila Pila1 = new Pila();
        Pila Pila2 = new Pila();
        Pila Pila3 = new Pila();
        
        System.out.println("Pilas Creadas");
    
        //Agregar Objetos a las Pilas
    
        //Pila 1
        Pila1.push(1, "Xbox");
        Pila1.push(2, "PlayStation");
        Pila1.push(3, "Nintendo");
        
        System.out.println("Pila 1 Llena");
        
        //Pila 2
        Pila2.push(1, "Toyota GR 86");
        Pila2.push(2, "BMW M2 Competition");
        Pila2.push(3, "Porsche 911 GT3");
        
        System.out.println("Pila 2 Llena");
        
        //Pila 3
        Pila3.push(1, "Fullmetal Alchemist Brotherhood");
        Pila3.push(2, "Shingeki No Kyojin");
        Pila3.push(3, "Kimetsu No Yaiba");
        
        System.out.println("Pila 3 Llena");
        System.out.println("");
        
        
        //Crear Objeto Lista Simple
        ListaSimple LS = new ListaSimple();
        
        System.out.println("Lista Simple creada");        
        
        //AgregarPilasAListaSimple
        LS.AgregarF(Pila1);
        LS.AgregarF(Pila2);
        LS.AgregarF(Pila3);
        
        System.out.println("Lista Simple llena");
        System.out.println("");
        
        //Mostrar Lista Simple
        LS.Mostrar();
        
        System.out.println("Lista Simple de Pilas Mostrada");
    }   
    
}
