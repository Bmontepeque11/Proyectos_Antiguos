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
public class SSRepuesto {
    private String Nombre;
    private float Precio;

    public SSRepuesto(String Nombre, float Precio) {
        this.Nombre = Nombre;
        this.Precio = Precio;
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
