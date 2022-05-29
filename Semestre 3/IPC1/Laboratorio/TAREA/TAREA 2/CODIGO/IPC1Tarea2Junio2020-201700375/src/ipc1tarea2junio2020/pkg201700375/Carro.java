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
public class Carro extends MedioDeTransporte{
    private String Marca;
    private String Modelo;
    private String TipoDeVehiculo;

    public Carro(String Marca, String Modelo, String TipoDeVehiculo) {
        this.Marca = Marca;
        this.Modelo = Modelo;
        this.TipoDeVehiculo = TipoDeVehiculo;
    }
    
    @Override
    public void Avanzar() {
        System.out.println("AVANCE-CARRO-"+this.getMarca()+"-"+this.getModelo()+"-"+this.getTipoDeVehiculo());
    }

    @Override
    public void Frenar() {
        System.out.println("FRENAR-CARRO-"+this.getMarca()+"-"+this.getModelo()+"-"+this.getTipoDeVehiculo());
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

    /**
     * @return the TipoDeVehiculo
     */
    public String getTipoDeVehiculo() {
        return TipoDeVehiculo;
    }

    /**
     * @param TipoDeVehiculo the TipoDeVehiculo to set
     */
    public void setTipoDeVehiculo(String TipoDeVehiculo) {
        this.TipoDeVehiculo = TipoDeVehiculo;
    }
}
