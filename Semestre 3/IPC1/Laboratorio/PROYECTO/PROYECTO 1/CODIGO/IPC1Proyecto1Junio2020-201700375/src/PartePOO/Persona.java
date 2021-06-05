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
public class Persona {
    private int ID;
    private String Nombre;
    private String Telefono;
    private String Tipo;
    private String Descripcion;
    private float Monto;
    private float Poliza;
    private float Deducible;
    private int Asegurado;//Asegurado = 1, si es 0 entonces no esta Asegurado

    public Persona(int ID, String Nombre, String Telefono, String Tipo, String Descripcion, float Monto, float Poliza, float Deducible, int Asegurado) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Telefono = Telefono;
        this.Tipo = Tipo;
        this.Descripcion = Descripcion;
        this.Monto = Monto;
        this.Poliza = Poliza;
        this.Deducible = Deducible;
        this.Asegurado = Asegurado;
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
     * @return the Telefono
     */
    public String getTelefono() {
        return Telefono;
    }

    /**
     * @param Telefono the Telefono to set
     */
    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    /**
     * @return the Tipo
     */
    public String getTipo() {
        return Tipo;
    }

    /**
     * @param Tipo the Tipo to set
     */
    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    /**
     * @return the Descripcion
     */
    public String getDescripcion() {
        return Descripcion;
    }

    /**
     * @param Descripcion the Descripcion to set
     */
    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    /**
     * @return the Monto
     */
    public float getMonto() {
        return Monto;
    }

    /**
     * @param Monto the Monto to set
     */
    public void setMonto(float Monto) {
        this.Monto = Monto;
    }

    /**
     * @return the Poliza
     */
    public float getPoliza() {
        return Poliza;
    }

    /**
     * @param Poliza the Poliza to set
     */
    public void setPoliza(float Poliza) {
        this.Poliza = Poliza;
    }

    /**
     * @return the Deducible
     */
    public float getDeducible() {
        return Deducible;
    }

    /**
     * @param Deducible the Deducible to set
     */
    public void setDeducible(float Deducible) {
        this.Deducible = Deducible;
    }

    /**
     * @return the Asegurado
     */
    public int getAsegurado() {
        return Asegurado;
    }

    /**
     * @param Asegurado the Asegurado to set
     */
    public void setAsegurado(int Asegurado) {
        this.Asegurado = Asegurado;
    }
}