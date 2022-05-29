/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PartePOO;

/**
 *
 * @author C55D-B5319-PC
 */
public class SSTipo {
    private String Nombre;
    private float ValorMaximo;

    public SSTipo(String Nombre, float ValorMaximo) {
        this.Nombre = Nombre;
        this.ValorMaximo = ValorMaximo;
    }

    /**
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     * @return the ValorMaximo
     */
    public float getValorMaximo() {
        return ValorMaximo;
    }

    /**
     * @param ValorMaximo the ValorMaximo to set
     */
    public void setValorMaximo(float ValorMaximo) {
        this.ValorMaximo = ValorMaximo;
    }
}
