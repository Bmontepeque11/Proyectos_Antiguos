/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.t2_edd;

import java.io.FileWriter;
import java.io.IOException;

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
    public void push(int ValorNodo){//Agrega Valores a la Pila
        NodoPila NuevoNodo = new NodoPila(ValorNodo);
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
    
    public int pop(){//Muestra y elimina el Ultimo valor
        if (Cima.getSiguiente() != null){
            int CimaAnterior = Cima.getValorNodo();
            Cima = Cima.getSiguiente();
            Tamaño--;
            return CimaAnterior;
        } else {
            
            int CimaAnterior = Cima.getValorNodo();
            Cima = Cima.getSiguiente();
            Tamaño--;
            return CimaAnterior;
        }   
        
    }
    
    //Generar Archivo de Graphviz
    public void ArchivoGraphViz(){
        
        //Iniciar el Cog=digo de GraphViz
        String CodigoGraphViz = new String();
        CodigoGraphViz = "digraph ListaSimple {\nrankdir=\"LR\"\n";
        
        //Crear el Nodo Auxiliar y Contador
        NodoPila Aux = Cima;
        int idNodo = 1;
        
        //Recorrer la Lista
        while (Aux != null){
            
            //Agregar los nodos al Archivo
            CodigoGraphViz = CodigoGraphViz + "\n" + idNodo + "[label=\"" + String.valueOf(Aux.getValorNodo()) +"\"];\n";
            
            //Agregar los apuntadores al Archivo
            
            
            if (idNodo == 1){
                
            } else {
                CodigoGraphViz = CodigoGraphViz + String.valueOf(idNodo-1) + " -> " + String.valueOf(idNodo) + ";\n";
            }
            idNodo++;
            
            //Avanzar el Auxiliar
            Aux = Aux.getSiguiente();
        }
        
        //Terminar el Código de GraphViz
        CodigoGraphViz = CodigoGraphViz + "}";
        
        //Revisar el Código de GraphViz
        System.out.println("Este es el Codigo de GraphViz:");
        System.out.println("");
        System.out.println(CodigoGraphViz);

        //Generar el Archivo del grafo
        try {
            //Craer Archivo
            FileWriter ArchivoGraphViz = new FileWriter("GrafoInversoTarea2EDD.dot");
            System.out.println("Archivo Creado");
            //Escribir en el archivo
            ArchivoGraphViz.write(CodigoGraphViz);
            ArchivoGraphViz.close();
            System.out.println("Se ha escrito en el archivo de GraphViz correctamente");
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error :'v");
            e.printStackTrace();
        }
        
        //Convertir de dot a jpg
        try {
            //Crear la imagen a partir del archivo dot
            Process p = Runtime.getRuntime().exec("dot -Tjpg GrafoInversoTarea2EDD.dot -o GrafoInversoTarea2EDD.jpg");                   
            
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error :'v");
            e.printStackTrace();
        }
        
    }
    
}
