/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labeddtarea1;

import java.io.FileWriter;
import java.io.IOException;
import java.awt.Desktop;

/**
 *
 * @author Bryan
 */
public class LS {
    private NodoLS Inicio;
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
    public void AgregarF(int Dato){
        NodoLS Nuevo = new NodoLS();
        Nuevo.setDatoNodo(Dato);
        
        if (EsVacia()){
            Inicio = Nuevo;    
        } else {
            NodoLS Aux = Inicio;
            
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
        NodoLS Aux = Inicio;
        while(Aux != null){
            
            System.out.println("Dato del Nodo: ");            
            System.out.println(Aux.getDatoNodo());
                                
            Aux = Aux.getSiguiente();
        }
    }
    
    //Generar Archivo de Graphviz
    public void ArchivoGraphViz(){
        
        //Iniciar el Cog=digo de GraphViz
        String CodigoGraphViz = new String();
        CodigoGraphViz = "digraph ListaSimple {\nrankdir=\"LR\"\n";
        
        //Crear el Nodo Auxiliar y Contador
        NodoLS Aux = Inicio;
        int idNodo = 1;
        
        //Recorrer la Lista
        while (Aux != null){
            
            //Agregar los nodos al Archivo
            CodigoGraphViz = CodigoGraphViz + "\n" + idNodo + "[label=\"" + String.valueOf(Aux.getDatoNodo()) +"\"];\n";
            
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
            FileWriter ArchivoGraphViz = new FileWriter("GrafoTarea1EDD.dot");
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
            Process p = Runtime.getRuntime().exec("dot -Tjpg GrafoTarea1EDD.dot -o GrafoTarea1EDD.jpg");                   
            
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error :'v");
            e.printStackTrace();
        }
        
    }
}
