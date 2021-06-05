/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hojadetrabajo.pkg1;
import java.util.Scanner;
/**
 *
 * @author C55D-B5319-PC
 */
public class HojaDeTrabajo1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Menu();
    }
    public static void Menu (){
        int OpcionMenu, CantidadDeAsignadosA=0, CantidadDeAsignadosB=15;
        System.out.println("Bienvenido, A qué sección desea asignarse? "+ "\n"+"1)Sección A"+"\n"+"2)Sección B");
        Scanner LeerOpcionMenu = new Scanner(System.in);
        System.out.print("La Opción que desea es: ");
        OpcionMenu = LeerOpcionMenu.nextInt();
        switch(OpcionMenu){
            case 1:
                if (CantidadDeAsignadosA<15){
                    System.out.println("Asignación a la Sección A Exitosa");
                    CantidadDeAsignadosA = CantidadDeAsignadosA+1;
                }
                else{
                    System.out.println("Esa Sección está llena :v, intentá en la otra"+"\n"+"1)Sección B");
                    OpcionMenu = LeerOpcionMenu.nextInt();
                    if(CantidadDeAsignadosB<15){
                        System.out.println("Asignación a la Sección B exitosa");
                        CantidadDeAsignadosB=CantidadDeAsignadosB+1;
                    }
                    else{
                        System.out.println("Las dos secciones están llenas, intentá abrir una nueva :)");
                    }       
                }
                break;
            case 2:
                if (CantidadDeAsignadosB<15){
                    System.out.println("Asignación a la Sección B Exitosa");
                    CantidadDeAsignadosB = CantidadDeAsignadosB+1;
                }
                else{
                    System.out.println("Esa Sección está llena :v, intentá en la otra "+"\n"+ "1)Sección A");
                    OpcionMenu = LeerOpcionMenu.nextInt();
                    if(CantidadDeAsignadosA<15){
                        System.out.println("Asignación a la Sección A exitosa");
                        CantidadDeAsignadosA=CantidadDeAsignadosA+1;
                    }
                    else{
                        System.out.println("Las dos secciones están llenas, intentá abrir una nueva :)");
                    }       
                }
                break; 
        }
    }
}
