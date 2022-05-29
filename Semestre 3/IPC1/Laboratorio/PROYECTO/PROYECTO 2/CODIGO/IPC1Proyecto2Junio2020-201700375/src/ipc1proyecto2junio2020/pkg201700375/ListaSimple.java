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
public class ListaSimple {
    NodoLS Cabeza;
    NodoLS Final;
    int Tamaño;

    public ListaSimple() {
        Cabeza = null;
        Final = null;
        Tamaño = 0;
    }
    public boolean EsVacia(){
        return Cabeza == null;
    }
    
    public void AgregarAlFinal(int ID, int FilaNodo, int ColNodo, int ValorNodo, String ColorNodo){
        NodoLS NuevoNodo = new NodoLS(ID, FilaNodo, ColNodo, ValorNodo, ColorNodo);
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
        NodoLS Auxiliar = Cabeza;
        boolean Encontrado = false;
        while (Auxiliar != null && Encontrado != true){
            if(ID == Auxiliar.getID()){
                Encontrado = true;
            } else {
                Auxiliar.getSiguiente();
            }
        }
        return Encontrado;
    }
    public void EliminarPorPosicion(int ID){
        NodoLS Auxiliar = Cabeza;
        NodoLS Anterior = null;
        while(Auxiliar != null){
            if (Auxiliar.getID() == ID){
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
            CInicial = "digraph G {" + "\n";
            CIntermedio = CInicial;
            NodoLS Auxiliar = Cabeza;
            int IDAnterior;
            CIntermedio = CIntermedio + Auxiliar.getID() + "[label=\"" + Auxiliar.getValorNodo() + "," + Auxiliar.getColorNodo() + "\"];" + "\n";
            while (Auxiliar.getSiguiente() != null) {
                IDAnterior = Auxiliar.getID();
                Auxiliar = Auxiliar.getSiguiente();
                CIntermedio = CIntermedio + Auxiliar.getID() + "[label=\"" + Auxiliar.getValorNodo() + "," + Auxiliar.getColorNodo() + "\"];" + "\n";
                CIntermedio = CIntermedio + IDAnterior + "->" + Auxiliar.getID() + ";" + "\n";
            }
            CFinal = CIntermedio + "}";
            return CFinal;
        }
    }
    
    public void Listar(){
        if(Cabeza == null){
            System.out.println("La Lista Simple está vacía.");
        } else {
            NodoLS Auxiliar = Cabeza;
            System.out.println("Los Nodos de la Lista Simple son:");
            while(Auxiliar != null){
                System.out.println("["+Auxiliar.getID()+", "+Auxiliar.getFilaNodo()+", "+Auxiliar.getColNodo()+", "+Auxiliar.getValorNodo()+", "+Auxiliar.getColorNodo()+" ]");
                Auxiliar = Auxiliar.getSiguiente();
            }
        }
    }
}
