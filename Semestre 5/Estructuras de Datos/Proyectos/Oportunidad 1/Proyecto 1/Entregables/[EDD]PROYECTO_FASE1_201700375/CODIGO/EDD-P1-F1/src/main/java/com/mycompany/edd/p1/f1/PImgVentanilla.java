/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.edd.p1.f1;

/**
 *
 * @author Bryan
 */
public class PImgVentanilla {
     NodoPImgVentanilla Cima;
    int Tamaño;

    public PImgVentanilla() {
        Cima = null;
        Tamaño = 0;
    }
    
    public boolean estaVacia(){
        return Cima == null;
    }
    
    public void push(int id_img, int id_clienteDueño, String tipo_de_img){//Agrega Valores a la Pila
        NodoPImgVentanilla NuevoNodo = new NodoPImgVentanilla(id_img, id_clienteDueño, tipo_de_img);
        NuevoNodo.setSiguiente(Cima);
        Cima = NuevoNodo;
        Tamaño++;
    }
    
    public NodoPImgVentanilla peek(){//Muestra el Ultimo Valor y ya
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
            CInicial = "subgraph PilaImgenes {" + "label=\"Pila Imagenes\"\n" + "\nPImg" + IDCima + "[label=\"CIMA\"];"+"\n\n";
            CIntermedio = CInicial;
            NodoPImgVentanilla Auxiliar = Cima;
            int IDAnterior;
            CIntermedio = CIntermedio + "PImg" + Auxiliar.getId_img() + "[shape=\"box\" label=\"ID Imagen: " + Auxiliar.getId_img() + "\nID Cliente: " + Auxiliar.getId_clienteDueño() + "\nTipo de Img: "+ Auxiliar.getTipo_de_img() +"\"];" + "\n"+IDCima +"->"+ Auxiliar.getId_img()+";"+"\n";
            while (Auxiliar.getSiguiente() != null) {
                IDAnterior = Auxiliar.getId_img();
                Auxiliar = Auxiliar.getSiguiente();
                CIntermedio = CIntermedio + "PImg" + Auxiliar.getId_img() + "[shape=\"box\" label=\"ID Imagen: " + Auxiliar.getId_img() + "\nID Cliente: " + Auxiliar.getId_clienteDueño() + "\nTipo de Img: "+ Auxiliar.getTipo_de_img() +"\"];" + "\n";
                CIntermedio = CIntermedio + "PImg" + IDAnterior + "-> PImg" + Auxiliar.getId_img() + ";" + "\n\n";
            }
            CFinal = CIntermedio +"PImg1 -> PImg0;"+"\n"+ "0[label=\"TOPE\"];"+"\n"+"}";
            return CFinal;
        }
    }
}
