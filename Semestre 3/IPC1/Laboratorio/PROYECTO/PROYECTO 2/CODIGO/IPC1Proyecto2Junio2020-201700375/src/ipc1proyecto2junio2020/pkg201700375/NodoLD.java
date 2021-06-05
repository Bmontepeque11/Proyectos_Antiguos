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
public class NodoLD {
    private int ID;
    private int FilaNodo;
    private int ColNodo;
    private int ValorNodo;
    private String ColorNodo;
    private NodoLD Anterior;
    private NodoLD Siguiente;

    public NodoLD(int ID, int FilaNodo, int ColNodo, int ValorNodo, String ColorNodo) {
        this.ID = ID;
        this.FilaNodo = FilaNodo;
        this.ColNodo = ColNodo;
        this.ValorNodo = ValorNodo;
        this.ColorNodo = ColorNodo;
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the FilaNodo
     */
    public int getFilaNodo() {
        return FilaNodo;
    }

    /**
     * @param FilaNodo the FilaNodo to set
     */
    public void setFilaNodo(int FilaNodo) {
        this.FilaNodo = FilaNodo;
    }

    /**
     * @return the ColNodo
     */
    public int getColNodo() {
        return ColNodo;
    }

    /**
     * @param ColNodo the ColNodo to set
     */
    public void setColNodo(int ColNodo) {
        this.ColNodo = ColNodo;
    }

    /**
     * @return the ValorNodo
     */
    public int getValorNodo() {
        return ValorNodo;
    }

    /**
     * @param ValorNodo the ValorNodo to set
     */
    public void setValorNodo(int ValorNodo) {
        this.ValorNodo = ValorNodo;
    }

    /**
     * @return the ColorNodo
     */
    public String getColorNodo() {
        return ColorNodo;
    }

    /**
     * @param ColorNodo the ColorNodo to set
     */
    public void setColorNodo(String ColorNodo) {
        this.ColorNodo = ColorNodo;
    }

    /**
     * @return the Anterior
     */
    public NodoLD getAnterior() {
        return Anterior;
    }

    /**
     * @param Anterior the Anterior to set
     */
    public void setAnterior(NodoLD Anterior) {
        this.Anterior = Anterior;
    }

    /**
     * @return the Siguiente
     */
    public NodoLD getSiguiente() {
        return Siguiente;
    }

    /**
     * @param Siguiente the Siguiente to set
     */
    public void setSiguiente(NodoLD Siguiente) {
        this.Siguiente = Siguiente;
    }
    
}
