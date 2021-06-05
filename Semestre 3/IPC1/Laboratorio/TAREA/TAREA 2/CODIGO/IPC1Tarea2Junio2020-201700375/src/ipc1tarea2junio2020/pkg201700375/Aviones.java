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
public class Aviones extends MedioDeTransporte {
    private int CantidadPasajeros;
    private String Marca;
    private String Modelo;

    public Aviones(int CantidadPasajeros, String Marca, String Modelo) {
        this.CantidadPasajeros = CantidadPasajeros;
        this.Marca = Marca;
        this.Modelo = Modelo;
    }
    
    
    @Override
    public void Avanzar() {
        System.out.println("AVANCE-AVION"+this.getCantidadPasajeros()+"-"+this.getMarca()+"-"+this.getModelo());
    }

    @Override
    public void Frenar() {
        System.out.println("FRENAR-AVION"+this.getCantidadPasajeros()+"-"+this.getMarca()+"-"+this.getModelo());
    }

    /**
     * @return the CantidadPasajeros
     */
    public int getCantidadPasajeros() {
        return CantidadPasajeros;
    }

    /**
     * @param CantidadPasajeros the CantidadPasajeros to set
     */
    public void setCantidadPasajeros(int CantidadPasajeros) {
        this.CantidadPasajeros = CantidadPasajeros;
    }

    /**
     * @return the Marca
     */
    public String getMarca() {
        return Marca;
    }

    /**
     * @param Marca the Marca to set
     */
    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    /**
     * @return the Modelo
     */
    public String getModelo() {
        return Modelo;
    }

    /**
     * @param Modelo the Modelo to set
     */
    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }
}
