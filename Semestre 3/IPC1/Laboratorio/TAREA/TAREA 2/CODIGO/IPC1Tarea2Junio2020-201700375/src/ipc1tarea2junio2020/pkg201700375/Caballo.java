/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipc1tarea2junio2020.pkg201700375;

/**
 *
 * @author C55D-B5319-PC
 */
public class Caballo extends MedioDeTransporte {
    private String Nombre;
    private String Raza;
    private int Edad;

    public Caballo(String Nombre, String Raza, int edad) {
        this.Nombre = Nombre;
        this.Raza = Raza;
        this.Edad = edad;
    }
    
    @Override
    public void Avanzar() {
        System.out.println("AVANCE-CABALLO-"+this.getNombre()+"-"+this.getRaza()+"-"+this.getEdad());
    }

    @Override
    public void Frenar() {
        System.out.println("FRENAR-CABALLO-"+this.getNombre()+"-"+this.getRaza()+"-"+this.getEdad());
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
     * @return the Raza
     */
    public String getRaza() {
        return Raza;
    }

    /**
     * @param Raza the Raza to set
     */
    public void setRaza(String Raza) {
        this.Raza = Raza;
    }

    /**
     * @return the edad
     */
    public int getEdad() {
        return Edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(int Edad) {
        this.Edad = Edad;
    }
    
}
