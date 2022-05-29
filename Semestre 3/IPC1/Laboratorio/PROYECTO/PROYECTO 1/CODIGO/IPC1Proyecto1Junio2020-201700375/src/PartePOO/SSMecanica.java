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
public class SSMecanica {
    private String NombreActividad;
    private float Precio;

    public SSMecanica(String NombreActividad, float Precio) {
        this.NombreActividad = NombreActividad;
        this.Precio = Precio;
    } 

    /**
     * @return the NombreActividad
     */
    public String getNombreActividad() {
        return NombreActividad;
    }

    /**
     * @param NombreActividad the NombreActividad to set
     */
    public void setNombreActividad(String NombreActividad) {
        this.NombreActividad = NombreActividad;
    }

    /**
     * @return the Precio
     */
    public float getPrecio() {
        return Precio;
    }

    /**
     * @param Precio the Precio to set
     */
    public void setPrecio(float Precio) {
        this.Precio = Precio;
    }
}
