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
public class SSUso {
    private String Nombre;
    private float PAumentoPoliza;

    public SSUso(String Nombre, float PAumentoPoliza) {
        this.Nombre = Nombre;
        this.PAumentoPoliza = PAumentoPoliza;
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
     * @return the PAumentoPoliza
     */
    public float getPAumentoPoliza() {
        return PAumentoPoliza;
    }

    /**
     * @param PAumentoPoliza the PAumentoPoliza to set
     */
    public void setPAumentoPoliza(float PAumentoPoliza) {
        this.PAumentoPoliza = PAumentoPoliza;
    }
}
