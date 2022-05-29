/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.edd.p1.f1;

/**
 *
 * @author Bryan
 */
public class CRecepcion {
    private NodoCRecepcion Cabeza;
    int Tamaño;

    public CRecepcion() {
        this.Cabeza = null;
        this.Tamaño = 0;
    }
    
    public boolean estaVacia() {
        return Cabeza == null;
    }
    
    public void push(int id_cliente, String nombre_cliente, int img_color, int img_bw){
        NodoCRecepcion NuevoNodo = new NodoCRecepcion(id_cliente, nombre_cliente, img_color, img_bw);
        if(this.getCabeza() == null){
            this.setCabeza(NuevoNodo);
        }else{
            NodoCRecepcion Auxiliar = this.getCabeza();
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
        
    public NodoCRecepcion pop(){
        NodoCRecepcion Auxiliar = null;
        NodoCRecepcion NodoRetornado = null;
        
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
            CInicial = "subgraph cluster_Recepcion {" + "\n" + "label=\"Recepción\"\n" + "R0[label=\"INICIO\"];"+"\n"+"R0->R"+Cabeza.getId_cliente()+";"+"\n\n";
            CIntermedio = CInicial;
            NodoCRecepcion Auxiliar = Cabeza;
            int IDAnterior;
            CIntermedio = CIntermedio + "R" + Auxiliar.getId_cliente() + "[shape=\"box\" label=\"ID_Cliente: " + Auxiliar.getId_cliente() + "\nNombre_Cliente: " + Auxiliar.getNombre_cliente() + "\nImg_Color: " + Auxiliar.getImg_color()+ "\nImg_BW: "+ Auxiliar.getImg_bw() +"\"];" + "\n";
            while (Auxiliar.getSiguiente() != null) {
                IDAnterior = Auxiliar.getId_cliente();
                Auxiliar = Auxiliar.getSiguiente();
                CIntermedio = CIntermedio + "R" + Auxiliar.getId_cliente()+ "[shape=\"box\" label=\"ID_Cliente: " + Auxiliar.getId_cliente() + "\nNombre_Cliente: " + Auxiliar.getNombre_cliente() + "\nImg_Color: " + Auxiliar.getImg_color()+ "\nImg_BW: "+ Auxiliar.getImg_bw() +"\"];" + "\n";
                CIntermedio = CIntermedio + "R" + IDAnterior + " -> R" + Auxiliar.getId_cliente() + ";" + "\n\n";
            }
            CFinal = CIntermedio + "}";
            return CFinal;
        }
    }
    
    /**
     * @return the Cabeza
     */
    public NodoCRecepcion getCabeza() {
        return Cabeza;
    }

    /**
     * @param Cabeza the Cabeza to set
     */
    public void setCabeza(NodoCRecepcion Cabeza) {
        this.Cabeza = Cabeza;
    }
}
