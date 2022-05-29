/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipc1ht2junio2020.pkg201700375;

/**
 *
 * @author C55D-B5319-PC
 */
public class Controlador {
    private Avion Avion1;
    private Avion Avion2;
    private Caballo CaballoCarga;
    private Caballo CaballoMascota;
    private Carro CarroChido;
    private Carro CarroChido2;
    
    public Controlador(){
        this.Avion1 = new Avion(1, "Lockheed Martin", "F-22 Raptor");
        this.Avion2 = new Avion(1, "Lockheed", "F-117 Nighthawk");
        
        this.CaballoCarga = new Caballo("Shelby", "Mustang", 35);
        this.CaballoMascota = new Caballo("Murciélago", "Altai", 20);
        
        this.CarroChido = new Carro("Toyota", "Gazoo Racing Supra A90 Edition 2020", "Deportivo");
        this.CarroChido2 = new Carro("Bentley","Flying Spur 2020","De Lujo");
        
    }

    /**
     * @return the Avion1
     */
    public Avion getAvion1() {
        return Avion1;
    }

    /**
     * @param Avion1 the Avion1 to set
     */
    public void setAvion1(Avion Avion1) {
        this.Avion1 = Avion1;
    }

    /**
     * @return the Avion2
     */
    public Avion getAvion2() {
        return Avion2;
    }

    /**
     * @param Avion2 the Avion2 to set
     */
    public void setAvion2(Avion Avion2) {
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
     * @return the CarroChido2
     */
    public Carro getCarroChido2() {
        return CarroChido2;
    }

    /**
     * @param CarroChido2 the CarroChido2 to set
     */
    public void setCarroChido2(Carro CarroChido2) {
        this.CarroChido2 = CarroChido2;
    }
    public void Imprimir(){
        //Aviones
        this.getAvion1().Avanzar();
        this.getAvion1().Frenar();
        
        System.out.println("");
        this.getAvion2().Avanzar();
        this.getAvion2().Frenar();
        //Caballos
        System.out.println("");
        this.getCaballoCarga().Avanzar();
        this.getCaballoCarga().Frenar();
        
        System.out.println("");
        this.getCaballoMascota().Avanzar();
        this.getCaballoMascota().Frenar();
        //Carros
        System.out.println("");
        this.getCarroChido().Avanzar();
        this.getCarroChido().Frenar();
        
        System.out.println("");
        this.getCarroChido2().Avanzar();
        this.getCarroChido2().Frenar();
    }
            
}
