/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.t2_edd;

/**
 *
 * @author Bryan
 */
public class main {
    public static void main(String[] Args){
        //Crear Pila
        Pila P1 = new Pila();
        System.out.println("Pila Creada");
        System.out.println("");
        
        //Agregar datos a la pila
        P1.push(4);
        P1.push(6);
        P1.push(8);
        P1.push(0);
        P1.push(9);
        
        //Confirmar que la pila esté lista
        System.out.println("Pila Llena");
        System.out.println("Esta es la pila:");
        System.out.println("");
        
        //Mostrar la la pila
        P1.MostrarP();
        System.out.println("Pila Mostrada");
        System.out.println("");
         
        //Generar Archivo GraphViz para el Grafo Normal
        P1.ArchivoGraphViz();
        System.out.println("Grafo Normal Generado");
        
        //Crear la pila que invertida ;v
        Pila EvilP1 = new Pila();
        System.out.println("Pila 1 Inversa creada");
        System.out.println("");
        
        //Llenar la pila malvada invirtiendo la Pila 1
        while (P1.TamañoPila() != 0){
                EvilP1.push(P1.pop());
            
        }
        
        //Generar Archivo GraphViz para el Grafo Inverso
        EvilP1.ArchivoGraphViz();
        
    }
}
