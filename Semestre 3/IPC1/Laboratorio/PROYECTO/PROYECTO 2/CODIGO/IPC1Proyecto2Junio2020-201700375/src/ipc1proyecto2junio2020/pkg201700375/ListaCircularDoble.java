/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipc1proyecto2junio2020.pkg201700375;

/**
 *
 * @author C55D-B5319-PC
 */
public class ListaCircularDoble {
    NodoLCD Cabeza;
    NodoLCD Final;
    int Tamaño;

    public ListaCircularDoble() {
        Cabeza = null;
        Final = null;
        Tamaño = 0;
    }
    
    public void AgregarAlFinal(int ID, int FilaNodo, int ColNodo, int ValorNodo, String ColorNodo){
        NodoLCD NuevoNodo = new NodoLCD(ID, FilaNodo, ColNodo, ValorNodo, ColorNodo);
        if(Cabeza == null){
            Cabeza = NuevoNodo;
            Cabeza.setSiguiente(NuevoNodo);
            NuevoNodo.setAnterior(Final);
            Final = NuevoNodo;
        } else {
            Final.setSiguiente(NuevoNodo);
            NuevoNodo.setSiguiente(Cabeza);
            NuevoNodo.setAnterior(Final);
            Final = NuevoNodo;
            Cabeza.setAnterior(Final);
        }
    }
    
    public void EliminarPorPosicion(int ID){
        NodoLCD Auxiliar = Cabeza;
        NodoLCD Anterior = Final;
        do {
            if(Auxiliar.getID() == ID){
                if(Auxiliar == Cabeza){
                    Cabeza = Cabeza.getSiguiente();
                    Final.setSiguiente(Cabeza);
                    Cabeza.setAnterior(Final);
                }else if(Auxiliar == Final){
                    Final = Anterior;
                    Cabeza.setAnterior(Final);
                    Final.setSiguiente(Cabeza);
                } else {
                    Anterior.setSiguiente(Auxiliar.getSiguiente());
                    Auxiliar.getSiguiente().setAnterior(Anterior);
                }
            }
            Anterior = Auxiliar;
            Auxiliar= Auxiliar.getSiguiente();
        }while(Auxiliar != Cabeza);
    }
    
    public void LimpiarLista() {
        Cabeza = null;
        Final = null;
        Tamaño = 0;
    }
    
      public String GenerarCodigoGraphViz() {
        if (Cabeza == null) {
            return "La Lista Circular Doblemente Enlazada está vacía.";
        } else {
            String CInicial, CIntermedio, CFinal;
            CInicial = "digraph G {" + "\n";
            CIntermedio = CInicial;
            NodoLCD Auxiliar = Cabeza;
            int IDAnterior;
            CIntermedio = CIntermedio + Auxiliar.getID() + "[label=\"" + Auxiliar.getValorNodo() + "," + Auxiliar.getColorNodo() + "\"];" + "\n";
            do {
                IDAnterior = Auxiliar.getID();
                Auxiliar = Auxiliar.getSiguiente();
                CIntermedio = CIntermedio + Auxiliar.getID() + "[label=\"" + Auxiliar.getValorNodo() + "," + Auxiliar.getColorNodo() + "\"];" + "\n";
                CIntermedio = CIntermedio + IDAnterior + "->" + Auxiliar.getID() + ";" + "\n";
                CIntermedio = CIntermedio + Auxiliar.getID() + "->" + IDAnterior + ";" + "\n";
            } while (Auxiliar != Cabeza);
            CFinal = CIntermedio + "}";
            return CFinal;
        }
    }
    
    public void Listar(){
        if (Cabeza == null){
            System.out.println("La Lista Circular Doblemente Enlazada está vacía.");
        } else {
            NodoLCD Auxiliar = Cabeza;
            System.out.println("Los Nodos de la Lista Circular Doblemente Enlazada son:");
            do{
                System.out.println("["+Auxiliar.getID()+", "+Auxiliar.getFilaNodo()+", "+Auxiliar.getColNodo()+", "+Auxiliar.getValorNodo()+", "+Auxiliar.getColorNodo()+" ]");
                Auxiliar = Auxiliar.getSiguiente();
            }while(Auxiliar != Cabeza);
        }
    }
}
