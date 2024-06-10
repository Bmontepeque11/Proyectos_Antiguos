/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labeddtarea1;

/**
 *
 * @author Bryan
 */
public class main {
    
    public static void GenerarGrafo(){
        
        
    }
    public static void main(String[] Args){
        //Crear Lista Simple
        LS ListaCarnet = new LS();
        System.out.println("Lista Creada");
        
        //Llenar Lista
        ListaCarnet.AgregarF(2);
        ListaCarnet.AgregarF(0);
        ListaCarnet.AgregarF(1);
        ListaCarnet.AgregarF(7);
        ListaCarnet.AgregarF(0);
        ListaCarnet.AgregarF(0);
        ListaCarnet.AgregarF(3);
        ListaCarnet.AgregarF(7);
        ListaCarnet.AgregarF(5);
        
        //Comprobar que la Lista esté llena
        System.out.println("Lista Llena");
        System.out.println("");
        
        //Mostrar la Lista
        System.out.println("Nodos en la Lista:");
        ListaCarnet.Mostrar();
        System.out.println("Fin de la Lista");
        
        //Generar Codigo GraphViz
        ListaCarnet.ArchivoGraphViz();
        
        //Comprobar que sí generó el código de GraphViz
        System.out.println("");
        System.out.println("Fin del Código de GraphViz");
    }
}
