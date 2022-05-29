/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.edd.p1.f1;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author Bryan
 */

public class main {
    //Variables Globales
    public static int NumeroVentanillas;
    
    //Listas Globales
    public static CRecepcion ColaRecepcion = new CRecepcion();
    public static LSVentanillas ListaVentanillas = new LSVentanillas();
    public static CImpresoras ColaImpresoras = new CImpresoras();
    
    public static void main(String[] args) {
        // TODO code application logic here
        Menu();
    }
    
    public static void Menu(){
        int OpcionMenuP;
        System.out.println("Bienvenido a UDrawing Paper, Ingrese la opción que desee:");
        System.out.println("1) Parametros Iniciales");
        System.out.println("2) Ejecutar Paso");
        System.out.println("3) Estado de Estructuras");
        System.out.println("4) Reportes");
        System.out.println("5) Acerca De");
        System.out.println("6) Salir");
        
        Scanner LeerOpcionMenu = new Scanner (System.in);
        OpcionMenuP = LeerOpcionMenu.nextInt();
        switch(OpcionMenuP){
            case 1:
                ParametrosIniciales();
                break;
            case 2:
                EjecutarPaso();
                break;
            case 3:
                EstadoEstructuras();
                break;
            case 4:
                Reportes();
                break;
            case 5:
                AcercaDe();
                break;
            case 6:
                System.exit(0);
                break;
            default:
                System.out.println("Solo puede elegir entre las opciones que ve en pantalla, Intentelo de nuevo");
                System.out.println(""+"");
                Menu();
                break;
        }
    }
    static void ParametrosIniciales(){
        //Línea Extra para más orden
        System.out.println("");
        
        int OpcionMenuPIniciales;
        System.out.println("Parámetros Iniciales, Ingrese la opción que desee:");
        System.out.println("1) Carga Masiva de Clientes");
        System.out.println("2) Numero de Ventanillas");
        System.out.println("3) Volver al Menú Principal");
        
        //Crear Scanner
        Scanner LeerOpcionMenu = new Scanner (System.in);
        OpcionMenuPIniciales = LeerOpcionMenu.nextInt();
        
        switch(OpcionMenuPIniciales){
            case 1:
                LeerJSON();
                break;
            case 2:
                //Línea Extra para más orden
                System.out.println("");
                
                System.out.println("Ingrese la cantidad de Ventanillas que desea: ");
                Scanner SCVentanillas = new Scanner(System.in);
                NumeroVentanillas = SCVentanillas.nextInt();
                
                System.out.println("La Cantidad de Ventanillas ingresda es: " + NumeroVentanillas);
                
                //Crear ese numero de ventanillas en la Lista de Ventanillas
                for (int i = 1; i <= NumeroVentanillas; i++) {
                    
                    PImgVentanilla Pilai = new PImgVentanilla();
                    ListaVentanillas.AgregarAlFinal(i, 0, false, Pilai, null);
                    
                }
                
                //Confirmación para volver al menú principal
                System.out.println("\nIngrese cualquier valor para volver al menú de Parámetros Iniciales: ");
        
                //Leer la confirmación
                Scanner LeerVCVentanillas = new Scanner (System.in);
                String OLV = LeerVCVentanillas.nextLine();
        
                //Volver al Menú
                ParametrosIniciales();
                
                break;
            case 3:
                //Línea Extra para más orden
                System.out.println("");
                
                //Confirmación para volver al menú principal
                System.out.println("\nIngrese cualquier valor para volver al menú principal: ");
        
                //Leer la confirmación
                Scanner LeerVMenu = new Scanner (System.in);
                String OVM = LeerVMenu.nextLine();
        
                //Volver al Menú
                Menu();
                
                break;
                
            default:
                System.out.println("Solo puede elegir entre las opciones que ve en pantalla, Intentelo de nuevo");
                System.out.println("");
                ParametrosIniciales();
                break;
        }           
        
    }
    
    static void LeerJSON(){
        //Línea Extra para más orden
        System.out.println("");
        
        //Ingresar la Ruta del Archivo JSON
        System.out.println("Ingrese la ruta del JSON que desea ingresar: ");      
        
        Scanner SCRutaJSON = new Scanner(System.in);
        String RutaJSON = SCRutaJSON.nextLine();
        
        //Leer el JSON
        JSONParser parser = new JSONParser();
        
        try{
            Object obj = parser.parse(new FileReader(RutaJSON));
            JSONObject jsonObject = (JSONObject) obj;
            System.out.println("Este es el JSON Leido: " + jsonObject);
            
            //Línea en blanco para más orden
            System.out.println("");
            
            //Obtener las etiquetas del JSON
            for (int i = 1; i <= jsonObject.size(); i++) {
                //Crear Objeto Cliente del JSON
                JSONObject ClienteActual = (JSONObject) jsonObject.get("Cliente" + i);
                
                //Obtener Parametros del Cliente
                
                //Crear Variables que guarden los parametros del cliente
                int id_clienteA = 0;
                String nombre_clienteA = new String();
                int img_colorA = 0;
                int img_bwA=0;
                
                //Guardar los datos del cliente actual en las Variables
                id_clienteA = Integer.valueOf((String)ClienteActual.get("id_cliente"));
                nombre_clienteA = String.valueOf(ClienteActual.get("nombre_cliente"));
                img_colorA = Integer.valueOf((String)ClienteActual.get("img_color"));
                img_bwA = Integer.valueOf((String)ClienteActual.get("img_bw"));
                
                //Imprimir los datos para confirmar que todo esté bien leido
                System.out.println("Cliente" + i + ":");
                System.out.println("id: " + id_clienteA);
                System.out.println("nombre: " + nombre_clienteA);
                System.out.println("img_color: " + img_colorA);
                System.out.println("img_bw: " + img_bwA);
                System.out.println("");
                
                //Guardar los datos en la lista
                ColaRecepcion.push(id_clienteA, nombre_clienteA, img_colorA, img_bwA);
                System.out.println("Datos Guardados en la Cola de Recepción");
                
            }
            
            System.out.println("Archivo JSON Leido");
            
            
            //Imprimir Codigo GraphViz para confirmar que todo se guardó bien
            //System.out.println("");
            //System.out.println("");
            
            //System.out.println(ColaRecepcion.GenerarCodigoGraphViz());
            
            //System.out.println("");
            //System.out.println("");
            
            //Confirmación para volver al menú de Parametros Iniciales
            System.out.println("\nIngrese cualquier valor para volver al menú de parámetros iniciales: ");
        
            //Leer la confirmación
            Scanner LeerVMenuJSON = new Scanner (System.in);
            String OVMJSON = LeerVMenuJSON.nextLine();
        
            //Volver al Menú
            ParametrosIniciales();
        } 
        catch(FileNotFoundException e) {        }
        catch (IOException e) {         }
        catch(ParseException e){      }
        
    }
    static void EjecutarPaso(){
        //Confirmación para volver al menú principal
        System.out.println("\nIngrese cualquier valor para volver al menú principal: ");
        
        //Leer la confirmación
        Scanner LeerVMenu = new Scanner (System.in);
        String OVM = LeerVMenu.nextLine();
        
        //Volver al Menú
        Menu();
    }
    static void EstadoEstructuras(){
        //Línea Extra para más orden
        System.out.println("");
        
        //Informar de Generación de Grafo
        System.out.println("Generando Grafo de las Estructuras...");
        
        //Iniciar el Codigo de GraphViz
        String CodigoGraphViz = "digraph G {\n";
        
        //Agregar datos a Cola Impresoras
        ColaImpresoras.push(1, "Impresora BW", "Yang Wiggins", 4);
        ColaImpresoras.push(2, "Impresora Color", "Polly Rodriguez", 3);
        
        //Agregar los codigos de las listas
        CodigoGraphViz = CodigoGraphViz + ColaRecepcion.GenerarCodigoGraphViz();
        
        CodigoGraphViz = CodigoGraphViz + ListaVentanillas.GenerarCodigoGraphViz();
        
        CodigoGraphViz = CodigoGraphViz + ColaImpresoras.GenerarCodigoGraphViz();
        
        //Terminar el Codigo de GraphViz
        CodigoGraphViz = CodigoGraphViz + "\n}";
        
        //Generar el Archivo del grafo
        try {
            //Craer Archivo
            FileWriter ArchivoGraphViz = new FileWriter("GrafoEstadoMemoria.dot");
            System.out.println("Archivo Creado");
            //Escribir en el archivo
            ArchivoGraphViz.write(CodigoGraphViz);
            ArchivoGraphViz.close();
            System.out.println("Se ha escrito en el archivo de GraphViz correctamente");
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error :'v");
            e.printStackTrace();
        }
        
        //Convertir de dot a jpg
        try {
            //Crear la imagen a partir del archivo dot
            Process p = Runtime.getRuntime().exec("dot -Tjpg GrafoEstadoMemoria.dot -o GrafoEstadoMemoria.jpg");                   
            
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error :(");
            e.printStackTrace();
        }
        
        System.out.println("La imágen del grafo se ha generado");
        
        
        //Confirmación para volver al menú principal
        System.out.println("\nIngrese cualquier valor para volver al menú principal: ");
        
        //Leer la confirmación
        Scanner LeerVMenu = new Scanner (System.in);
        String OVM = LeerVMenu.nextLine();
        
        //Volver al Menú
        Menu();
    }
    static void Reportes(){
        //Línea Extra para más orden
        System.out.println("");
        
        //Confirmación para volver al menú principal
        System.out.println("\nIngrese cualquier valor para volver al menú principal: ");
        
        //Leer la confirmación
        Scanner LeerVMenu = new Scanner (System.in);
        String OVM = LeerVMenu.nextLine();
        
        //Volver al Menú
        Menu();
        
    }
    static void AcercaDe(){
        //Línea Extra para más orden
        System.out.println("");
        
        //Crear Línea de Arriba
        for (int i=0;i<45;i++){
            System.out.print("-");
        }
        System.out.println("\n|Nombre: Bryan Steve Montepeque Santos      |");
        System.out.println("|Carnet: 201700375                          |");
        System.out.println("|Curso: Estructuras de Datos                |");
        System.out.println("|Sección: C                                 |");
        
        //Crear Línea de Abajo
        for (int i=0;i<45;i++){
            System.out.print("-");
        }
        
        //Confirmación para volver al menú principal
        System.out.println("\nIngrese cualquier valor para volver al menú principal: ");
        
        //Leer la confirmación
        Scanner LeerVMenu = new Scanner (System.in);
        String OVM = LeerVMenu.nextLine();
        
        //Volver al Menú
        Menu();
    }
               
}