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
public class Cola {
    private NodoC Cabeza;
    int Tamaño;

    public Cola() {
        this.Cabeza = null;
        this.Tamaño = 0;
    }
    
    public boolean estaVacia() {
        return Cabeza == null;
    }
    
    public void push(int ID, int FilaNodo, int ColNodo, int ValorNodo, String ColorNodo){
        NodoC NuevoNodo = new NodoC(ID, FilaNodo, ColNodo, ValorNodo, ColorNodo);
        if(this.getCabeza() == null){
            this.setCabeza(NuevoNodo);
        }else{
            NodoC Auxiliar = this.getCabeza();
            while(Auxiliar.getSiguiente() != null){
               Auxiliar = Auxiliar.getSiguiente();
            }
            Auxiliar.setSiguiente(NuevoNodo);
        }
        Tamaño++;
    }
    
    public void LimpiarCola() {
        while (!estaVacia()) {
            pop();
        }
    }
        
    public void pop(){
        NodoC Auxiliar = null;
        if(this.getCabeza() != null){
            Auxiliar = this.getCabeza();
            if(this.getCabeza().getSiguiente() != null){
                this.setCabeza(Auxiliar.getSiguiente());
            }else{
                this.setCabeza(null);
            }
            Tamaño--;
        }
        System.out.println("No hay datos");
    }

    public String GenerarCodigoGraphViz() {
        if (Cabeza == null) {
            return "La Cola está vacía.";
        } else {
            String CInicial, CIntermedio, CFinal;
            CInicial = "digraph G {" + "\n"+"0[label=\"INICIO\"];"+"\n"+"0->"+Cabeza.getID()+";"+"\n";
            CIntermedio = CInicial;
            NodoC Auxiliar = Cabeza;
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
    
    /**
     * @return the Cabeza
     */
    public NodoC getCabeza() {
        return Cabeza;
    }

    /**
     * @param Cabeza the Cabeza to set
     */
    public void setCabeza(NodoC Cabeza) {
        this.Cabeza = Cabeza;
    }
}
