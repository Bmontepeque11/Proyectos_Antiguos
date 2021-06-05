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
public class SSModelo {
    private String Año;
    private float PDepreciacion;

    public SSModelo(String Año, float PDepreciacion) {
        this.Año = Año;
        this.PDepreciacion = PDepreciacion;
    }

    /**
     * @return the Año
     */
    public String getAño() {
        return Año;
    }

    /**
     * @param Año the Año to set
     */
    public void setAño(String Año) {
        this.Año = Año;
    }

    /**
     * @return the PDepreciacion
     */
    public float getPDepreciacion() {
        return PDepreciacion;
    }

    /**
     * @param PDepreciacion the PDepreciacion to set
     */
    public void setPDepreciacion(float PDepreciacion) {
        this.PDepreciacion = PDepreciacion;
    }
}