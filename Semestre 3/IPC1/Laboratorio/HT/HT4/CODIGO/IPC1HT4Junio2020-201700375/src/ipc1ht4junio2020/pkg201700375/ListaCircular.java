/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipc1ht4junio2020.pkg201700375;

/**
 *
 * @author C55D-B5319-PC
 */
public class ListaCircular {
    private Nodo Cabeza;
    private Nodo Final;
    private int Tamaño;

    public ListaCircular() {
        Cabeza = null;
        Final = null;
        Tamaño = 0;
    }
    
    public boolean EsVacia(){
        return Cabeza == null;
    }
    
    public void AgregarAlFinal(int ID, String Valor){
        Nodo NuevoNodo = new Nodo(ID, Valor);
        if (Cabeza == null){
            Cabeza = NuevoNodo;
            Final = NuevoNodo;
            NuevoNodo.setSiguiente(Cabeza);
            Tamaño = Tamaño + 1;
        } else {
            Final.setSiguiente(NuevoNodo);
            Final = NuevoNodo;
            Final.setSiguiente(Cabeza);
            Tamaño = Tamaño + 1;
        }
    }
    
    public void Eliminar(){
        Cabeza = null;
        Final = null;
        Tamaño = 0;
    }
    
    public void Listar (){
        Nodo Auxiliar = Cabeza;
        if (Cabeza == null){
            System.out.println("Lista Vacia");
        } else {
            System.out.println("Los Nodos de la lista son:");
            do{
                System.out.println("["+Auxiliar.getID()+", "+Auxiliar.getValor()+"]");
                Auxiliar = Auxiliar.getSiguiente();
            } while (Auxiliar != Cabeza);
            System.out.println("");
        }
    }
    
    public void Editar(int ID, String Valor){
        if (Buscar(ID)){
            Nodo Auxiliar = Cabeza;
            while(Auxiliar.getID() != ID){
                Auxiliar = Auxiliar.getSiguiente();
            }
            Auxiliar.setValor(Valor);
        }
    }
    
    public boolean Buscar(int ID){
          Nodo Auxiliar = Cabeza;
          boolean Encontrado = false;
          do {
              if(ID == Auxiliar.getID()){
                  Encontrado = true;
              } else{
                  Auxiliar = Auxiliar.getSiguiente();
              }
          }while(Auxiliar != Cabeza && Encontrado != true);
          return Encontrado;
    }
    
    public String GenerarCodigoGraphViz(){
        String CInicial, CIntermedio, CFinal;
        CInicial = "digraph {"+"\n";
        CIntermedio = CInicial;
        Nodo Auxiliar = Cabeza;
        int IDAnterior;
        CIntermedio = CIntermedio + Auxiliar.getID()+"[label=\""+Auxiliar.getValor()+"\"];"+"\n";
        do {
            IDAnterior = Auxiliar.getID();
            Auxiliar = Auxiliar.getSiguiente();
            CIntermedio = CIntermedio + Auxiliar.getID()+"[label=\""+Auxiliar.getValor()+"\"];"+"\n";
            CIntermedio = CIntermedio + IDAnterior+ "->"+Auxiliar.getID()+";"+"\n";
        }while(Auxiliar != Cabeza);
        CFinal = CIntermedio +"}";
        return CFinal;
    }

    /**
     * @return the Cabeza
     */
    public Nodo getCabeza() {
        return Cabeza;
    }

    /**
     * @param Cabeza the Cabeza to set
     */
    public void setCabeza(Nodo Cabeza) {
        this.Cabeza = Cabeza;
    }

    /**
     * @return the Final
     */
    public Nodo getFinal() {
        return Final;
    }

    /**
     * @param Final the Final to set
     */
    public void setFinal(Nodo Final) {
        this.Final = Final;
    }

    /**
     * @return the Tamaño
     */
    public int getTamaño() {
        return Tamaño;
    }

    /**
     * @param Tamaño the Tamaño to set
     */
    public void setTamaño(int Tamaño) {
        this.Tamaño = Tamaño;
    }
}
