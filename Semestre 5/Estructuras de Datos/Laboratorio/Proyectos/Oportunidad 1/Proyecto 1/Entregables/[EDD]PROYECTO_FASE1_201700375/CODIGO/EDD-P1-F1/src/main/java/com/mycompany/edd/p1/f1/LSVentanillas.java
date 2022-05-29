/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.edd.p1.f1;

/**
 *
 * @author Bryan
 */
public class LSVentanillas {
     NodoLSVentanillas Cabeza;
    NodoLSVentanillas Final;
    int Tamaño;

    public LSVentanillas() {
        Cabeza = null;
        Final = null;
        Tamaño = 0;
    }
    public boolean EsVacia(){
        return Cabeza == null;
    }
    
    public void AgregarAlFinal(int id_ventanilla, int pasos_ocupada, boolean estaOcupada, PImgVentanilla Pila_img, NodoCRecepcion ClienteActual){
        NodoLSVentanillas NuevoNodo = new NodoLSVentanillas(id_ventanilla, pasos_ocupada, estaOcupada, Pila_img, ClienteActual);
        if (Cabeza == null){
            Cabeza = NuevoNodo;
            Cabeza.setSiguiente(null);
            Final = Cabeza;
        } else {
            Final.setSiguiente(NuevoNodo);
            NuevoNodo.setSiguiente(null);
            Final = NuevoNodo;
        }
        Tamaño = Tamaño + 1;
    }
    
    public boolean Buscar (int ID){
        NodoLSVentanillas Auxiliar = Cabeza;
        boolean Encontrado = false;
        while (Auxiliar != null && Encontrado != true){
            if(ID == Auxiliar.getId_ventanilla()){
                Encontrado = true;
            } else {
                Auxiliar.getSiguiente();
            }
        }
        return Encontrado;
    }
    public void EliminarPorPosicion(int ID){
        NodoLSVentanillas Auxiliar = Cabeza;
        NodoLSVentanillas Anterior = null;
        while(Auxiliar != null){
            if (Auxiliar.getId_ventanilla() == ID){
                if(Auxiliar == Cabeza){
                   Cabeza = Cabeza.getSiguiente();
                } else {
                    Anterior.setSiguiente(Auxiliar.getSiguiente());
                }
            }
            Anterior = Auxiliar;
            Auxiliar = Auxiliar.getSiguiente();
        }
    }
    
    public void LimpiarLista(){
        Cabeza = null;
        Final = null;
        Tamaño = 0;
    }
    
    public String GenerarCodigoGraphViz() {
        if (Cabeza == null){
            return "La Lista Simple está vacía.";
        } else {
            String CInicial, CIntermedio, CFinal;
            CInicial = "subgraph cluster_Ventanillas {" + "\n" +"label=\"Ventanillas\"\n";
            CIntermedio = CInicial;
            NodoLSVentanillas Auxiliar = Cabeza;
            
            int IDAnterior;
            CIntermedio = CIntermedio + "V" + Auxiliar.getId_ventanilla() + "[shape=\"box\" label=\"ID_Ventnailla: " + Auxiliar.getId_ventanilla() + "\nPasos_Ocupada: " + Auxiliar.getPasos_ocupada() + "\nestaOcupada: " + Auxiliar.isEstaOcupada() + "\"];" + "\n\n";
            while (Auxiliar.getSiguiente() != null) {
                IDAnterior = Auxiliar.getId_ventanilla();
                Auxiliar = Auxiliar.getSiguiente();
                CIntermedio = CIntermedio + "V" + Auxiliar.getId_ventanilla() + "[shape=\"box\" label=\"ID_Ventnailla: " + Auxiliar.getId_ventanilla() + "\nPasos_Ocupada: " + Auxiliar.getPasos_ocupada() + "\nestaOcupada: " + Auxiliar.isEstaOcupada() + "\"];" + "\n";
                CIntermedio = CIntermedio + "V" + IDAnterior + " -> V" + Auxiliar.getId_ventanilla() + ";" + "\n\n";
            }
            CFinal = CIntermedio + "}";
            
            return CFinal;
        }
    }

}
