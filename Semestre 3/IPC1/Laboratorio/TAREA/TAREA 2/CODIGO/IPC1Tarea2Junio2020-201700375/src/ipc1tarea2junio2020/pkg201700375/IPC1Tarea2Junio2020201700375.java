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
public class IPC1Tarea2Junio2020201700375 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Controlador Iniciar = new Controlador();
        //Aviones
        Iniciar.getAvion1().Avanzar();
        Iniciar.getAvion1().Frenar();
        
        System.out.println("");
        Iniciar.getAvion2().Avanzar();
        Iniciar.getAvion2().Frenar();
        //Caballos
        System.out.println("");
        Iniciar.getCaballoCarga().Avanzar();
        Iniciar.getCaballoCarga().Frenar();
        
        System.out.println("");
        Iniciar.getCaballoMascota().Avanzar();
        Iniciar.getCaballoMascota().Frenar();
        //Carros
        System.out.println("");
        Iniciar.getCarroChido().Avanzar();
        Iniciar.getCarroChido().Frenar();
        
        System.out.println("");
        Iniciar.getCarroComun().Avanzar();
        Iniciar.getCarroComun().Frenar();
    }
}
