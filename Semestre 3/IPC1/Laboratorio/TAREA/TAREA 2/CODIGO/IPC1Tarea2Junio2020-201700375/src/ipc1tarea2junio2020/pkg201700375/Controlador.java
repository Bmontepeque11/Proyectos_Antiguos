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
public class Controlador {
    private Aviones Avion1;
    private Aviones Avion2;
    private Caballo CaballoCarga;
    private Caballo CaballoMascota;
    private Carro CarroChido;
    private Carro CarroComun;
    
    public Controlador(){
        this.Avion1 = new Aviones(500, "Boeing", "A320");
        this.Avion2 = new Aviones(8, "AirBus", "F117");
        
        this.CaballoCarga = new Caballo("Valkyrie", "Mustang", 5);
        this.CaballoMascota = new Caballo("Danny", "Cavallino Rampante", 5);
        
        this.CarroChido = new Carro("Lamborghini", "Aventador SuperVeloce Jota 2018", "SuperDeportivo");
        this.CarroComun = new Carro("Honda","Civic Touring 2019","Sedán");
        
    }

    /**
     * @return the Avion1
     */
    public Aviones getAvion1() {
        return Avion1;
    }

    /**
     * @param Avion1 the Avion1 to set
     */
    public void setAvion1(Aviones Avion1) {
        this.Avion1 = Avion1;
    }

    /**
     * @return the Avion2
     */
    public Aviones getAvion2() {
        return Avion2;
    }

    /**
     * @param Avion2 the Avion2 to set
     */
    public void setAvion2(Aviones Avion2) {
        this.Avion2 = Avion2;
    }

    /**
     * @return the CaballoCarga
     */
    public Caballo getCaballoCarga() {
        return CaballoCarga;
    }

    /**
     * @param CaballoCarga the CaballoCarga to set
     */
    public void setCaballoCarga(Caballo CaballoCarga) {
        this.CaballoCarga = CaballoCarga;
    }

    /**
     * @return the CaballoMascota
     */
    public Caballo getCaballoMascota() {
        return CaballoMascota;
    }

    /**
     * @param CaballoMascota the CaballoMascota to set
     */
    public void setCaballoMascota(Caballo CaballoMascota) {
        this.CaballoMascota = CaballoMascota;
    }

    /**
     * @return the CarroChido
     */
    public Carro getCarroChido() {
        return CarroChido;
    }

    /**
     * @param CarroChido the CarroChido to set
     */
    public void setCarroChido(Carro CarroChido) {
        this.CarroChido = CarroChido;
    }

    /**
     * @return the CarroComun
     */
    public Carro getCarroComun() {
        return CarroComun;
    }

    /**
     * @param CarroComun the CarroComun to set
     */
    public void setCarroComun(Carro CarroComun) {
        this.CarroComun = CarroComun;
    }
            
}
