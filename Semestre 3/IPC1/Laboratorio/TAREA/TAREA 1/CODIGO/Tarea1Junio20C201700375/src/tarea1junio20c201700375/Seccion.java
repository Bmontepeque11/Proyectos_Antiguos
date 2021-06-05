/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea1junio20c201700375;
import tarea1junio20c201700375.Estudiante;
/**
 *
 * @author C55D-B5319-PC
 */
public class Seccion {
    int CantidadAsignados;
    String AlumnosAsignados[][] = new String [5][2]; //5 Alumnos; 0=Nombre;1=Carnet
    public Seccion(){
    }
    public void AgregarAlumno(Estudiante ENuevo){
        this.AlumnosAsignados[CantidadAsignados-1][0]=ENuevo.getNombre();
        this.AlumnosAsignados[CantidadAsignados-1][1]=ENuevo.getCarnet();
    }
    public void MostrarDatos(){
        for(int i=0;i<5;i++){
          System.out.println(i+1 + ") Nombre: "+AlumnosAsignados[i][0]+ " Carnet: "+AlumnosAsignados[i][1]);  
        }
    }
}
