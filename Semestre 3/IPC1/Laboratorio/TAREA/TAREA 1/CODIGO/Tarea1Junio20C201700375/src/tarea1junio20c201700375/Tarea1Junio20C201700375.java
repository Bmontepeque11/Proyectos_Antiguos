/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea1junio20c201700375;
import java.util.Scanner;
/**
 *
 * @author C55D-B5319-PC
 */
public class Tarea1Junio20C201700375 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Menu();
    }
    static void Menu(){
        Seccion A = new Seccion();
        Seccion B = new Seccion();
        int OpcionMenu;
        String NombreE,CarnetE;
        Scanner LeerOpcionMenu = new Scanner(System.in);
        boolean Repetir = true;
        do{
        System.out.println("Bienvenido al sistema de asignación, Ingrese su Nombre: ");
        NombreE=LeerOpcionMenu.next();
        System.out.println("Ingrese su Carnet: ");
        CarnetE=LeerOpcionMenu.next();
        Estudiante E1 = new Estudiante();
        E1.setCarnet(CarnetE);
        E1.setNombre(NombreE);
        System.out.print("Ingrese el numero de la sección a la cual desea asignarse (Recuerde que ambas tienen cupo de 5 Estudiantes Máximo)"+"\n"+"1)Sección A"+"\n"+"2)Sección B"+ "\n"+"3)Mostrar Datos de Ambas Secciones y Salir"+"\n"+"La opción que desea es: ");
        OpcionMenu = LeerOpcionMenu.nextInt();
        System.out.println(""+"\n");
        switch (OpcionMenu){
            case 1:
                A.CantidadAsignados = A.CantidadAsignados+1;
                if(A.CantidadAsignados > 5){
                    A.CantidadAsignados = 5;
                    if(B.CantidadAsignados == 5){
                        System.out.println("Las dos están llenas, Intentá abrir otra sección");
                    } else{
                    System.out.println("Esa Sección está llena, intentá on la otra :)");
                    }
                } else{
                    A.AgregarAlumno(E1);
                    System.out.println("Asignación en la Sección A Exitosa ;v");
                    System.out.println(""+"\n");
                }
                break;
            case 2:
                B.CantidadAsignados = B.CantidadAsignados+1;
                if(B.CantidadAsignados > 5){
                    B.CantidadAsignados = 5;
                    if(A.CantidadAsignados == 5){
                        System.out.println("Las dos están llenas, Intentá abrir otra sección");
                    } else{
                    System.out.println("Esa Sección está llena, intentá on la otra :)");
                    }
                } else{
                    B.AgregarAlumno(E1);
                    System.out.println("Asignación en la Sección B Exitosa ;v");
                    System.out.println(""+"\n");
                }
                break;
            case 3:
                System.out.println(""+"\n");
                System.out.println("Los Asignados de la Sección A son: ");
                A.MostrarDatos();
                System.out.println("Los Asignados de la Sección B son: ");
                B.MostrarDatos();
                Repetir = false;
                break;
            default:
                System.out.println("Solo puede ingresar una de las opcions que ve en pantalla.");
                break;
        }
        }while(Repetir != false);
    }
}
