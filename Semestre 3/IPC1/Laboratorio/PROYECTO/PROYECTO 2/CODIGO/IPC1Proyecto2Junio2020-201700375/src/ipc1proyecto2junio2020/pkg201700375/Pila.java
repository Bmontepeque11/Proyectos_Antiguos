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
public class Pila {
    NodoP Cima;
    int Tamaño;

    public Pila() {
        Cima = null;
        Tamaño = 0;
    }
    
    public boolean estaVacia(){
        return Cima == null;
    }
    
    public void push(int ID, int FilaNodo, int ColNodo, int ValorNodo, String ColorNodo){//Agrega Valores a la Pila
        NodoP NuevoNodo = new NodoP(ID, FilaNodo, ColNodo, ValorNodo, ColorNodo);
        NuevoNodo.setSiguiente(Cima);
        Cima = NuevoNodo;
        Tamaño++;
    }
    
    public NodoP peek(){//Muestra el Ultimo Valor y ya
        return Cima;
    }
    
    public int TamañoPila(){
        return Tamaño;
    }
    
    public void LimpiarPila(){
        while(!estaVacia()){
            pop();
        }
    }
    
    public void pop(){//Muestra y elimina el Ultimo valor, en este caso solo Elimina
        Cima = Cima.getSiguiente();
        Tamaño--;
    }
    
        public String GenerarCodigoGraphViz() {
        if (Cima == null){
            return "La Pila está vacía.";
        } else {
            String CInicial, CIntermedio, CFinal;
            int IDCima = Tamaño + 1;
            CInicial = "digraph G {" + "\n" + IDCima + "[label=\"CIMA\"];"+"\n";
            CIntermedio = CInicial;
            NodoP Auxiliar = Cima;
            int IDAnterior;
            CIntermedio = CIntermedio + Auxiliar.getID() + "[label=\"" + Auxiliar.getValorNodo() + "," + Auxiliar.getColorNodo() + "\"];" + "\n"+IDCima +"->"+Auxiliar.getID()+";"+"\n";
            while (Auxiliar.getSiguiente() != null) {
                IDAnterior = Auxiliar.getID();
                Auxiliar = Auxiliar.getSiguiente();
                CIntermedio = CIntermedio + Auxiliar.getID() + "[label=\"" + Auxiliar.getValorNodo() + "," + Auxiliar.getColorNodo() + "\"];" + "\n";
                CIntermedio = CIntermedio + IDAnterior + "->" + Auxiliar.getID() + ";" + "\n";
            }
            CFinal = CIntermedio +"1->0;"+"\n"+ "0[label=\"TOPE\"];"+"\n"+"}";
            return CFinal;
        }
    }
}
