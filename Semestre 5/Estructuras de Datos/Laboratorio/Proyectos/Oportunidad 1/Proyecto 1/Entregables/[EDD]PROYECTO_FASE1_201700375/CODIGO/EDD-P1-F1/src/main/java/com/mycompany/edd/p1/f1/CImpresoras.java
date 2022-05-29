/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.edd.p1.f1;

/**
 *
 * @author Bryan
 */
public class CImpresoras {
        private NodoCImpresoras Cabeza;
    int Tamaño;

    public CImpresoras() {
        this.Cabeza = null;
        this.Tamaño = 0;
    }
    
    public boolean estaVacia() {
        return Cabeza == null;
    }
    
    public void push(int id_cliente,String nombre_Impresora, String nombre_cliente, int imagenes_a_imprimir){
        NodoCImpresoras NuevoNodo = new NodoCImpresoras(id_cliente, nombre_Impresora, nombre_cliente, imagenes_a_imprimir);
        
        if(this.getCabeza() == null){
            this.setCabeza(NuevoNodo);
        }else{
            NodoCImpresoras Auxiliar = this.getCabeza();
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
        
    public NodoCImpresoras pop(){
        NodoCImpresoras Auxiliar = null;
        NodoCImpresoras NodoRetornado = null;
        
        if(this.getCabeza() != null){
            
            Auxiliar = this.getCabeza();
            NodoRetornado = this.getCabeza();
            
            if(this.getCabeza().getSiguiente() != null){
                this.setCabeza(Auxiliar.getSiguiente());
            }else{
                this.setCabeza(null);
            }
            Tamaño--;
        }
        System.out.println("No hay datos");
        return NodoRetornado;
    }

    public String GenerarCodigoGraphViz() {
        if (Cabeza == null) {
            return "La Cola está vacía.";
        } else {
            String CInicial, CIntermedio, CFinal;
            CInicial = "subgraph cluster_Impresoras {" + "\n" + "label=\"Impresoras\"\n" + "I0[label=\"INICIO\"];"+"\n"+"I0->I"+Cabeza.getId_cliente()+";"+"\n\n";
            CIntermedio = CInicial;
            NodoCImpresoras Auxiliar = Cabeza;
            int IDAnterior;
            CIntermedio = CIntermedio + "I" + Auxiliar.getId_cliente() + "[shape=\"box\" label=\"ID_Cliente: " + Auxiliar.getId_cliente() + "\nNombre_Impresora: " + Auxiliar.getNombre_Impresora() +"\nNombre_Cliente: " + Auxiliar.getNombre_cliente() + "\nImg_a_Imprimir: " + Auxiliar.getImagenes_a_imprimir() +"\"];" + "\n";
            while (Auxiliar.getSiguiente() != null) {
                IDAnterior = Auxiliar.getId_cliente();
                Auxiliar = Auxiliar.getSiguiente();
                CIntermedio = CIntermedio + "I" + Auxiliar.getId_cliente()+ "[shape=\"box\" label=\"ID_Cliente: " + Auxiliar.getId_cliente() + "\nNombre_Impresora: " + Auxiliar.getNombre_Impresora() + "\nNombre_Cliente: " + Auxiliar.getNombre_cliente() + "\nImg_a_Imprimir: " + Auxiliar.getImagenes_a_imprimir()+ "\"];" + "\n";
                CIntermedio = CIntermedio + "I" + IDAnterior + " -> I" + Auxiliar.getId_cliente() + ";" + "\n\n";
            }
            CFinal = CIntermedio + "}";
            return CFinal;
        }
    }
    
    /**
     * @return the Cabeza
     */
    public NodoCImpresoras getCabeza() {
        return Cabeza;
    }

    /**
     * @param Cabeza the Cabeza to set
     */
    public void setCabeza(NodoCImpresoras Cabeza) {
        this.Cabeza = Cabeza;
    }
}
