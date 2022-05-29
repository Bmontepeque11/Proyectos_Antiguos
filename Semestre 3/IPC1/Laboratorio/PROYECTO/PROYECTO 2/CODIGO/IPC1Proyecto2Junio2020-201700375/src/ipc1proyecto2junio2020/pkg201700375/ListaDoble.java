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
public class ListaDoble {
    NodoLD Cabeza;
    NodoLD Final;
    int Tamaño;

    public ListaDoble() {
        Cabeza = null;
        Final = null;
        Tamaño = 0;
    }
    
    public void AgregarAlFinal(int ID, int FilaNodo, int ColNodo, int ValorNodo, String ColorNodo){
        NodoLD NuevoNodo = new NodoLD(ID, FilaNodo, ColNodo, ValorNodo, ColorNodo);
        if (Cabeza == null){
            Cabeza = NuevoNodo;
            Cabeza.setSiguiente(null);
            Cabeza.setAnterior(null);
            Final = Cabeza;
        } else {
            Final.setSiguiente(NuevoNodo);
            NuevoNodo.setAnterior(Final);
            NuevoNodo.setSiguiente(null);
            Final = NuevoNodo;
        }
        Tamaño++;
    }
    
    public void EliminarPorPosicion(int ID){
        NodoLD Auxiliar = Cabeza;
        NodoLD Anterior = null;
        while(Auxiliar != null){
            if(Auxiliar.getID() == ID){
                if (Auxiliar == Cabeza){
                    Cabeza = Cabeza.getSiguiente();
                    Cabeza.setAnterior(null);
                } else {
                    Anterior.setSiguiente(Auxiliar.getSiguiente());
                    Auxiliar.getSiguiente().setAnterior(Auxiliar.getAnterior());
                }
            }
            Anterior = Auxiliar;
            Auxiliar = Auxiliar.getSiguiente();
        }
    }
    
    public void LimpiarLista() {
        Cabeza = null;
        Final = null;
        Tamaño = 0;
    }
    
    public String GenerarCodigoGraphViz() {
        if (Cabeza == null){
            return "La Lista Doble está vacía.";
        } else {
            String CInicial, CIntermedio, CFinal;
            CInicial = "digraph G {" + "\n";
            CIntermedio = CInicial;
            NodoLD Auxiliar = Cabeza;
            int IDAnterior;
            CIntermedio = CIntermedio + Auxiliar.getID() + "[label=\"" + Auxiliar.getValorNodo() + "," + Auxiliar.getColorNodo() + "\"];" + "\n";
            while (Auxiliar.getSiguiente() != null) {
                IDAnterior = Auxiliar.getID();
                Auxiliar = Auxiliar.getSiguiente();
                CIntermedio = CIntermedio + Auxiliar.getID() + "[label=\"" + Auxiliar.getValorNodo() + "," + Auxiliar.getColorNodo() + "\"];" + "\n";
                CIntermedio = CIntermedio + IDAnterior + "->" + Auxiliar.getID() + ";" + "\n";
                CIntermedio = CIntermedio + Auxiliar.getID() + "->" + IDAnterior + ";" + "\n";
            }
            CFinal = CIntermedio + "}";
            return CFinal;
        }
    }
    
    public void Listar(){
        if(Cabeza == null){
            System.out.println("La Lista Doblemente Enlazada está vacía.");
        } else {
            NodoLD Auxiliar = Cabeza;
            System.out.println("Los Nodos de la Lista Doblemente Enlazada son:");
            while (Auxiliar != null) {
                System.out.println("["+Auxiliar.getID()+", "+Auxiliar.getFilaNodo()+", "+Auxiliar.getColNodo()+", "+Auxiliar.getValorNodo()+", "+Auxiliar.getColorNodo()+" ]");
                Auxiliar = Auxiliar.getSiguiente();
            }
        }
    }
}
