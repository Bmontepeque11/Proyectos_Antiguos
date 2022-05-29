/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipc1practica1junio2020.pkg201700375;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author C55D-B5319-PC
 */
public class IPC1Practica1Junio2020201700375 {
         static String MatrizTablero[][] = new String [10][10];
         static Object MatrizJugadores[][] = new Object [20][4];
         //Datos MatrizJugadores [Nickname][Estado (0 = Perdió, 1 = Ganó, 2 = Abandonó)][CantidadIntentosH][CantidadBarcosH]
         static int CantidadIntentosH=0, CantidadIntentosR = 10, CPortaAviones = 1, CSubmarinos = 3, CDestructores = 3, CFragatas = 2, CEasterEgg = 1, IDJugador = -1, CantidadBarcosR=9, CantidadBarcosH= 0, CuentaBarcosIngresados = 0;
         static boolean ResumirJuego = false, IngresoBarcos = false;
         static int ArrayBarcosEnTablero [][] = new int [10][6];
//         //DatosArray [ID Barco][Horizontal(0) o Vertical(1)][Fila Inicial][Columna Inicial][Fila Final][ColumnaFinal]
//         0 = PortaAviones
//         1 = Submarino 1
//         2 = Submarino 2
//         3 = Submarino 3
//         4 = Destructor 1
//         5 = Destructor 2
//         6 = Destructor 3
//         7 = Fragata 1
//         8 = Fragata 2
//         9 = Easter Egg
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MenuPrincipal();
    }
    public static void MenuPrincipal (){
        int OpcionMenuP;
        System.out.print("Bienvenido a Battleship, Ingrese la opción que desee: "+ "\n"+"1)Menu Tablero"+"\n"+"2)Reporte Completo"+ "\n"+"3)Reporte De Victorias"+"\n"+"4)Salir"+"\n"+"La Opción que desea es: ");
        Scanner LeerOpcionMenu = new Scanner (System.in);
        OpcionMenuP = LeerOpcionMenu.nextInt();
        switch(OpcionMenuP){
            case 1:
                System.out.println(""+"");
                MenuTablero();
                break;
            case 2:
                ReporteCompleto();
                break;
            case 3:
                ReporteVictorias();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Solo puede elegir entre las opciones que ve en pantalla, Intentelo de nuevo");
                System.out.println(""+"");
                MenuPrincipal();
                break;
        }
    }
    //Opciones Menu Principal
    static void MenuTablero(){
        int OpcionMenuT;
        System.out.print("Ingrese la opción que desee: "+"\n"+"1)Ingresar Barcos"+"\n"+"2)Cambiar Cantidad de Intentos"+"\n"+"3)Iniciar Juego"+"\n"+"4)Visualizar Tablero"+"\n"+"5)Reiniciar Tablero"+"\n"+"6)Volver al Menu Principal"+"\n"+"La Opción que sesea es: ");
        Scanner LeerOpcionMenuT = new Scanner(System.in);
        OpcionMenuT = LeerOpcionMenuT.nextInt();
        switch(OpcionMenuT){
            case 1:
                System.out.println(""+"");
                IngresarBarcos();
                break;
            case 2:
                CambiarCIntentos();
                break;
            case 3:
                IniciarJuego();
                break;
            case 4:
                VisualizarTablero();
                break;
            case 5:
                ReiniciarTablero();
                break;
            case 6:
                System.out.println(""+"");
                MenuPrincipal();
                break;
            default:
                System.out.println("Solo puede elegir entre las opciones que ve en pantalla, Intentelo de nuevo");
                System.out.println(""+"");
                MenuTablero();
                break;
        }
    }
    static void ReporteCompleto(){
        System.out.print("Bienvenido, Este es el reporte de todas las partidas: Tome en cuenta que esto está en orden cronológico"+"\n"+"Los datos se mostraran en este orden: "+"\n"+"Nickname|Estado|Intentos|Barcos Hundidos"+"\n");
        for(int i=0;i<20;i++){
                System.out.println(MatrizJugadores[i][0]+"|"+MatrizJugadores[i][1]+"|"+MatrizJugadores[i][2]+"|"+MatrizJugadores[i][3]);
        }
        Scanner LeerOpcionReporteCompleto = new Scanner(System.in);
        String OpcionIngresadaReporteCompleto;
        System.out.print("Ingrese cualquier tecla para volver al Menú Principal: ");
        OpcionIngresadaReporteCompleto = LeerOpcionReporteCompleto.next();
        System.out.println(""+"\n");
        MenuPrincipal();
    }
    static void ReporteVictorias(){
        
    }
    //Opciones Menu Tablero
    static void IngresarBarcos(){
        int OpcionMenuB;
        System.out.print("Ingrese la opción que desee:"+"\n"+"1)Ingresar Portaaviones"+"\n"+"2)Ingresar Submarinos"+"\n"+"3)Ingresar Destructores"+"\n"+"4)Ingresar Fragatas"+"\n"+"5)Insertar EasterEgg"+"\n"+"6)Volver al Menu Tablero"+"\n"+"La Opción que desea es: ");
        Scanner LeerOpcionMneuB = new Scanner(System.in);
        OpcionMenuB = LeerOpcionMneuB.nextInt();
        switch(OpcionMenuB){
            case 1:
                IPortaaviones();
                break;
            case 2:
                ISubmarinos();
                break;
            case 3:
                IDestructores();
                break;
            case 4:
                IFragatas();
                break;
            case 5:
                IEasterEgg();
                break;
            case 6:
                System.out.println(""+"");
                MenuTablero();
                break;
            default:
                System.out.println("Solo puede elegir entre las opciones que ve en pantalla, Intentelo de nuevo");
                System.out.println(""+"");
                IngresarBarcos();
                break;
        }
    }
    static void CambiarCIntentos(){
        int NCantidadIntentos;
        System.out.println(""+"");
        System.out.println("La Cantidad Actual de Intentos es de: "+ CantidadIntentosR +"\n"+"Ingrese la nueva cantidad de intentos: ");
        Scanner LeerNCIntentos = new Scanner(System.in);
        NCantidadIntentos = LeerNCIntentos.nextInt();
        CantidadIntentosR = NCantidadIntentos;
        System.out.println("La Nueva Cantidad de Intentos es de: "+ CantidadIntentosR+"\n"+"Ingrese cualquier tecla para volver al Menu Tablero: ");
        LeerNCIntentos.next();
        System.out.println(""+"");
        MenuTablero();
    }
    static void IniciarJuego(){
        String CoordenadasIngresadas;
        Scanner LeerOpcionIniciarJuego = new Scanner(System.in);
        SimpleDateFormat Formato = new SimpleDateFormat();
        Date Fecha = new Date();
        int OpcionElegidaIniciarJuego;
        if (CuentaBarcosIngresados > 4){
            System.out.println("Barcos Ingresados :)");
        }else {
            System.out.println("No ha ingresado todos los barcos, presione cualquier tecla para ir al menu Ingresar Barcos");
            String OpcionIngresarBarco = LeerOpcionIniciarJuego.next();
            IngresarBarcos();
        }
        if (ResumirJuego != true){
            IDJugador = IDJugador + 1;
            String NicknameIngresado;
            System.out.print("Ingrese el Nickname con el cual va a jugar: ");
            NicknameIngresado = LeerOpcionIniciarJuego.next();
            MatrizJugadores[IDJugador][0] = NicknameIngresado;
        } //Si ResumirJuego = true entonces no hace nada porque sigue como si nada :) pero si es false entonces debe volver a pedir datos.
        do{
            System.out.println(""+"\n");
            System.out.println("Hora: "+ Formato.format(Fecha) +"\n"+"\n");
            System.out.println("Barcos:");
            System.out.println("           "+CantidadBarcosH+"/9 Hundidos");
            System.out.println("           "+CantidadBarcosR+"/9 En Acción");
            System.out.println("Intentos:");
            System.out.println("            "+CantidadIntentosH+"/10 Realizados");
            System.out.println("            "+CantidadIntentosR+"/10 Restantes");
            
            //EstadoActual Tablero
            System.out.println("Este es el estado actual del tablero: "+"");
            System.out.println(""+"");
            //Linea de Arriba
            for (int i=0;i<36;i++){
                System.out.print("-");
            }
            System.out.print("\n"+"| |->");
            for (int j=0;j<10;j++){
                System.out.print("|"+j+"|");
            }
            System.out.println("");
            for (int k=0;k<36;k++){
                System.out.print("-");
            }
            System.out.print("\n");
            //Columna de Izquierda y Matriz
            for (int a=0;a<10;a++){
                System.out.print("|"+a+"|->");
                for(int b=0;b<10;b++){
                    System.out.print("|"+MatrizTablero[a][b]+"|");
                }
                System.out.print("\n");
            }
            System.out.println("Ingrese la opción que desee: "+"\n"+"1)Lanzar Misil"+"\n"+"2)Terminar Partida y volver a Menu Tablero");
            OpcionElegidaIniciarJuego = LeerOpcionIniciarJuego.nextInt();
            switch(OpcionElegidaIniciarJuego){
                case 1:
                    System.out.println("");
                    System.out.print("Ingrese las coordenadas que desea atacar de la siguiente forma (Fila,Columna): ");
                    CoordenadasIngresadas = LeerOpcionIniciarJuego.next();
                    String CoordenadaParaComparar;
                    //Validar si golpeó algún barco:
                        for (int i=0;i<10;i++){
                          //Ver si es Vertical
                          if(ArrayBarcosEnTablero[i][1] == 1){
                            //For que recorre las coordenadas del barco en cuaestión:
                            for(int j=ArrayBarcosEnTablero[i][2];j<ArrayBarcosEnTablero[i][4]+1;j++){
                                //String que guarda la coordenada actual que va recorriendo el for
                                CoordenadaParaComparar = j + ","+ArrayBarcosEnTablero[i][3];
                                //Comparar la coordenada actual a la coordenada ingresada, si concuerda entonces le dió
                                if (CoordenadasIngresadas.equals(CoordenadaParaComparar)){
                                    System.out.println("Le díste a un barco!");
                                    //For que recorre la Matriz Tablero
                                    for(int k=ArrayBarcosEnTablero[i][2];k<ArrayBarcosEnTablero[i][4];k++){
                                        //Cambiar los "O" a "X"
                                        MatrizTablero[k][ArrayBarcosEnTablero[i][3]]="X";
                                    }
                                    CantidadBarcosH = CantidadBarcosH + 1;
                                    CantidadBarcosR = CantidadBarcosR - 1;
                                }
                            }
                          }else if(ArrayBarcosEnTablero[i][1] == 0){
                            //For que recorre las coordenadas del barco en cuaestión:
                            for(int j=ArrayBarcosEnTablero[i][3];j<ArrayBarcosEnTablero[i][5]+1;j++){
                                //String que guarda la coordenada actual que va recorriendo el for
                                CoordenadaParaComparar = ArrayBarcosEnTablero[i][2] + "," + j;
                                //Comparar la coordenada actual a la coordenada ingresada, si concuerda entonces le dió
                                if (CoordenadasIngresadas.equals(CoordenadaParaComparar)){
                                    //For que recorre la Matriz Tablero
                                    for(int k=ArrayBarcosEnTablero[i][3];k<ArrayBarcosEnTablero[i][5];k++){
                                        //Cambiar los "O" a "X"
                                        MatrizTablero[ArrayBarcosEnTablero[i][2]][k]="X";
                                    }
                                    CantidadBarcosH = CantidadBarcosH + 1;
                                    CantidadBarcosR = CantidadBarcosR - 1;
                                }
                            }
                          } 
                        }
                        CantidadIntentosH = CantidadIntentosH + 1;
                        CantidadIntentosR = CantidadIntentosR - 1;
                    break;
                case 2:
                    System.out.println("Si desea iniciar una partida nueva primero vaya a 'ReiniciarTablero' y luego vuelva a entrar a 'IniciarJuego'");
                    System.out.println("Adios, "+MatrizJugadores[IDJugador][0]);
                    MatrizJugadores[IDJugador][1] = 2; //Abandonó
                    ResumirJuego = true;
                    MenuTablero();
                    break;
                default:
                    System.out.println("Solo puede elegir entre las opciones que ve en pantalla, Intentelo de nuevo");
                    break;
            }
        }while(CantidadIntentosR>0||CantidadBarcosH<9);
        //Aquí evaluar con if cuál de las condiciones se rompió y poner Ganó o Perdió en base a eso :)
        if(CantidadIntentosR == 0){
            System.out.print(MatrizJugadores[IDJugador][0]+" ha perdido.");
            MatrizJugadores[IDJugador][1] = 0; //0  Perdió
        } else if(CantidadBarcosH == 9){
            System.out.print(MatrizJugadores[IDJugador][0]+" ha ganado ;v");
            MatrizJugadores[IDJugador][1] = 1; //0 = Ganó
        }
        MatrizJugadores[IDJugador][2] = CantidadIntentosH;
        MatrizJugadores[IDJugador][3] = CantidadBarcosH;
    }
    static void VisualizarTablero(){
        Scanner LeerTeclaVTablero = new Scanner(System.in);
        System.out.println(""+"");
        //Linea de Arriba
        for (int i=0;i<36;i++){
            System.out.print("-");
        }
        System.out.print("\n"+"| |->");
        for (int j=0;j<10;j++){
            System.out.print("|"+j+"|");
        }
        System.out.println("");
        for (int k=0;k<36;k++){
            System.out.print("-");
        }
        System.out.print("\n");
        //Llenar Matriz
//        for(int x=0;x<10;x++){
//            for(int y=0;y<10;y++){
//                MatrizTablero [x][y]= "O";
//            }
//        }
        //Columna de Izquierda y Matriz
        for (int a=0;a<10;a++){
            System.out.print("|"+a+"|->");
            for(int b=0;b<10;b++){
                System.out.print("|"+MatrizTablero[a][b]+"|");
            }
            System.out.print("\n");
        }
        System.out.print("Ingrese Cualquier tecla para continuar: ");
        LeerTeclaVTablero.next();
        System.out.println(""+"");
        MenuTablero();
    }
    static void ReiniciarTablero(){
      Scanner LeerTeclaRTablero = new Scanner(System.in);
      //Vaciar Matriz
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                MatrizTablero [i][j]= null;
            }
        }
        
        //ReiniciarCantidades
        CPortaAviones = 1;
        CSubmarinos = 3;
        CDestructores = 3;
        CFragatas = 2;
        CEasterEgg = 1;
        CantidadIntentosH = 0;
        CantidadIntentosR = 10;
        CantidadBarcosR = 9;
        CantidadBarcosH = 0;
        ResumirJuego = false;
        
        //EstadoActual Tablero
        System.out.println("Este es el estado actual del tablero: "+"");
        System.out.println(""+"");
        //Linea de Arriba
        for (int i=0;i<36;i++){
            System.out.print("-");
        }
        System.out.print("\n"+"| |->");
        for (int j=0;j<10;j++){
            System.out.print("|"+j+"|");
        }
        System.out.println("");
        for (int k=0;k<36;k++){
            System.out.print("-");
        }
        System.out.print("\n");
        //Columna de Izquierda y Matriz
        for (int a=0;a<10;a++){
            System.out.print("|"+a+"|->");
            for(int b=0;b<10;b++){
                System.out.print("|"+MatrizTablero[a][b]+"|");
            }
            System.out.print("\n");
        }
        
        System.out.print("Ingrese Cualquier tecla para continuar: ");
        LeerTeclaRTablero.next();
        System.out.println(""+"");
        MenuTablero();
    }
    //Opciones Menu Barcos
    static void IPortaaviones (){
        if (CPortaAviones==0){
            System.out.println("Ya Ingresaste un Portaaviones, si querés ingresar otro primero tenés que reiniciar el tablero, presione cualquier tecla para volver al menu Barcos");
            Scanner LeerRespuesta = new Scanner(System.in);
            String Res;
            Res=LeerRespuesta.next();
            IngresarBarcos();
        }else{
          int DistanciaFilas=0, DistanciaColumnas=0, Horizontal=0, Vertical=0, CoordenadaFija=0;
        Scanner LeerCoordenadasIPA = new Scanner(System.in);
        System.out.println("Ingrese las coordenadas de Inicio y Final en donde desea colocar su PortaAviones (Recuerde que ocupa 4 casillas y que no puede ponerlo en diagonal)");
        String CoordenadasPortaAviones = LeerCoordenadasIPA.next();
        //(FI,CI)-(FF,CF)
        String VectorCoorAux1 [] = CoordenadasPortaAviones.split("-"); 
        //(FI,CI)
        String CoordIniciales = VectorCoorAux1[0];
        //(FF,CF)
        String CoordFinales = VectorCoorAux1[1];
        
        //Iniciales
        String VectorCInicialCercano [] = CoordIniciales.split(",");
        //(FI
        String FilaInicialCercana = VectorCInicialCercano[0];
        //CI)
        String ColumnaInicialCercana = VectorCInicialCercano[1];
        
        //Finales
        String VectorCFinalCercano [] = CoordFinales.split(",");
        //(FF
        String FilaFinalCercana = VectorCFinalCercano[0];
        //CF)
        String ColumnaFinalCercana = VectorCFinalCercano[1];
        
        //Coordenadas Reales Iniciales
        //FI
        String VectorCFilaInicial [] = FilaInicialCercana.split("");
        int FilaInicial = Integer.parseInt(VectorCFilaInicial[1]);
        //CI
        String VectorCColumnaInicial [] = ColumnaInicialCercana.split("");
        int ColumnaInicial = Integer.parseInt(VectorCColumnaInicial[0]);
        
        //Coordenadas Reales Finales
        //FF
        String VectorCFilaFinal [] = FilaFinalCercana.split("");
        int FilaFinal = Integer.parseInt(VectorCFilaFinal[1]);
        //CF
        String VectorCColumnaFinal [] = ColumnaFinalCercana.split("");
        int ColumnaFinal = Integer.parseInt(VectorCColumnaFinal[0]);
       
        //Distancia de Fila Inicial a Fila Final
        for(int i=FilaInicial;i<FilaFinal;i++){
            DistanciaFilas = DistanciaFilas+1;
        }
        //Distancia de Columna Inicial a Columna Final
        for(int j=ColumnaInicial;j<ColumnaFinal;j++){
            DistanciaColumnas = DistanciaColumnas+1;
        }
        //Preparacion para Validacion
        if(DistanciaFilas == 0 && DistanciaColumnas == 4){
            Horizontal = 1;
            CoordenadaFija=FilaInicial;
        }
        if(DistanciaFilas == 4 && DistanciaColumnas == 0){
            Vertical = 1;
            CoordenadaFija=ColumnaInicial;
        }
        //Validacion
        if (Horizontal == 0 && Vertical == 0){
            System.out.println("Solo puede ponerlo de forma Horizontal o Vertical (Y como es Portaavion solo puede ocupar 4 casillas), Intentelo de nuevo.");
            System.out.println(""+"");
            IPortaaviones();
        } else {
            if(Vertical == 1){
                //Llenar PortaAviones en Matriz Vertical
                    for(int a=FilaInicial;a<FilaFinal;a++){
                            if(MatrizTablero[a][CoordenadaFija]!= null){
                                System.out.println("Los barcos se interceptan, ponga otras coordenadas.");
                                System.out.println(""+"");
                                IPortaaviones();
                            } else{
                                MatrizTablero[a][CoordenadaFija]="O";
                            }
                    }
                    //AlmacenarDirección
                    ArrayBarcosEnTablero[0][1] = 1;
                    //AlmacenarCoordIniciales
                    ArrayBarcosEnTablero[0][2] = FilaInicial;
                    ArrayBarcosEnTablero[0][3] = ColumnaInicial;
                    //AlmacenarCoordFinales
                    ArrayBarcosEnTablero[0][4] = FilaFinal;
                    ArrayBarcosEnTablero[0][5] = ColumnaFinal;
            } else if (Horizontal == 1){
                //Llenar PortaAviones en Matriz Horizontal
                    for(int a=ColumnaInicial;a<ColumnaFinal;a++){
                        if(MatrizTablero[CoordenadaFija][a]!= null){
                                System.out.println("Los barcos se interceptan, ponga otras coordenadas.");
                                System.out.println(""+"");
                                IPortaaviones();
                            } else{
                                MatrizTablero[CoordenadaFija][a]="O";
                            }
                    }
                    //AlmacenarDirección
                    ArrayBarcosEnTablero[0][1] = 0;
                    //AlmacenarCoordIniciales
                    ArrayBarcosEnTablero[0][2] = FilaInicial;
                    ArrayBarcosEnTablero[0][3] = ColumnaInicial;
                    //AlmacenarCoordFinales
                    ArrayBarcosEnTablero[0][4] = FilaFinal;
                    ArrayBarcosEnTablero[0][5] = ColumnaFinal;
            }
            CPortaAviones = 0;
            CuentaBarcosIngresados = CuentaBarcosIngresados + 1;
        }
        //EstadoActual Tablero
        System.out.println("Este es el estado actual del tablero: "+"");
        System.out.println(""+"");
        //Linea de Arriba
        for (int i=0;i<36;i++){
            System.out.print("-");
        }
        System.out.print("\n"+"| |->");
        for (int j=0;j<10;j++){
            System.out.print("|"+j+"|");
        }
        System.out.println("");
        for (int k=0;k<36;k++){
            System.out.print("-");
        }
        System.out.print("\n");
        //Columna de Izquierda y Matriz
        for (int a=0;a<10;a++){
            System.out.print("|"+a+"|->");
            for(int b=0;b<10;b++){
                System.out.print("|"+MatrizTablero[a][b]+"|");
            }
            System.out.print("\n");
        }
        System.out.print("Ingrese cualquier tecla para volver al Menu Ingresar Barcos: ");
        LeerCoordenadasIPA.next();
        System.out.println(""+"");
        IngresarBarcos();  
        }
    }
    static void ISubmarinos(){
        if (CSubmarinos==0){
            System.out.println("Ya Ingresaste 3 Submarinos, si querés ingresarlos otra vez primero tenés que reiniciar el tablero, presione cualquier tecla para volver al menu Barcos");
            Scanner LeerRespuesta = new Scanner(System.in);
            String Res;
            Res=LeerRespuesta.next();
            IngresarBarcos();
        }else{
          int DistanciaFilas=0, DistanciaColumnas=0, Horizontal=0, Vertical=0, CoordenadaFija=0;
        Scanner LeerCoordenadasIPA = new Scanner(System.in);
        System.out.println("Ingrese las coordenadas de Inicio y Final en donde desea colocar su Submarino (Recuerde que ocupa 3 casillas y que no puede ponerlo en diagonal)");
        String CoordenadasPortaAviones = LeerCoordenadasIPA.next();
        //(FI,CI)-(FF,CF)
        String VectorCoorAux1 [] = CoordenadasPortaAviones.split("-"); 
        //(FI,CI)
        String CoordIniciales = VectorCoorAux1[0];
        //(FF,CF)
        String CoordFinales = VectorCoorAux1[1];
        
        //Iniciales
        String VectorCInicialCercano [] = CoordIniciales.split(",");
        //(FI
        String FilaInicialCercana = VectorCInicialCercano[0];
        //CI)
        String ColumnaInicialCercana = VectorCInicialCercano[1];
        
        //Finales
        String VectorCFinalCercano [] = CoordFinales.split(",");
        //(FF
        String FilaFinalCercana = VectorCFinalCercano[0];
        //CF)
        String ColumnaFinalCercana = VectorCFinalCercano[1];
        
        //Coordenadas Reales Iniciales
        //FI
        String VectorCFilaInicial [] = FilaInicialCercana.split("");
        int FilaInicial = Integer.parseInt(VectorCFilaInicial[1]);
        //CI
        String VectorCColumnaInicial [] = ColumnaInicialCercana.split("");
        int ColumnaInicial = Integer.parseInt(VectorCColumnaInicial[0]);
        
        //Coordenadas Reales Finales
        //FF
        String VectorCFilaFinal [] = FilaFinalCercana.split("");
        int FilaFinal = Integer.parseInt(VectorCFilaFinal[1]);
        //CF
        String VectorCColumnaFinal [] = ColumnaFinalCercana.split("");
        int ColumnaFinal = Integer.parseInt(VectorCColumnaFinal[0]);
       
        //Distancia de Fila Inicial a Fila Final
        for(int i=FilaInicial;i<FilaFinal;i++){
            DistanciaFilas = DistanciaFilas+1;
        }
        //Distancia de Columna Inicial a Columna Final
        for(int j=ColumnaInicial;j<ColumnaFinal;j++){
            DistanciaColumnas = DistanciaColumnas+1;
        }
        //Preparacion para Validacion
        if(DistanciaFilas == 0 && DistanciaColumnas == 3){
            Horizontal = 1;
            CoordenadaFija=FilaInicial;
        }
        if(DistanciaFilas == 3 && DistanciaColumnas == 0){
            Vertical = 1;
            CoordenadaFija=ColumnaInicial;
        }
        //Validacion
        if (Horizontal == 0 && Vertical == 0){
            System.out.println("Solo puede ponerlo de forma Horizontal o Vertical (Y como es Submarino solo puede ocupar 3 casillas), Intentelo de nuevo.");
            System.out.println(""+"");
            ISubmarinos();
        } else {
            if(Vertical == 1){
                //Llenar PortaAviones en Matriz Vertical
                    for(int a=FilaInicial;a<FilaFinal;a++){
                            if(MatrizTablero[a][CoordenadaFija]!= null){
                                System.out.println("Los barcos se interceptan, ponga otras coordenadas.");
                                System.out.println(""+"");
                                ISubmarinos();
                            } else{
                                MatrizTablero[a][CoordenadaFija]="O";
                            }
                    }
                    switch(CSubmarinos){
                                    case 3:
                                        //AlmacenarDirección
                                        ArrayBarcosEnTablero[1][1] = 1;
                                        //AlmacenarCoordIniciales
                                        ArrayBarcosEnTablero[1][2] = FilaInicial;
                                        ArrayBarcosEnTablero[1][3] = ColumnaInicial;
                                        //AlmacenarCoordFinales
                                        ArrayBarcosEnTablero[1][4] = FilaFinal;
                                        ArrayBarcosEnTablero[1][5] = ColumnaFinal;
                                        break;
                                    case 2:
                                        //AlmacenarDirección
                                        ArrayBarcosEnTablero[2][1] = 1;
                                        //AlmacenarCoordIniciales
                                        ArrayBarcosEnTablero[2][2] = FilaInicial;
                                        ArrayBarcosEnTablero[2][3] = ColumnaInicial;
                                        //AlmacenarCoordFinales
                                        ArrayBarcosEnTablero[2][4] = FilaFinal;
                                        ArrayBarcosEnTablero[2][5] = ColumnaFinal;
                                        break;
                                    case 1:
                                        //AlmacenarDirección
                                        ArrayBarcosEnTablero[3][1] = 1;
                                        //AlmacenarCoordIniciales
                                        ArrayBarcosEnTablero[3][2] = FilaInicial;
                                        ArrayBarcosEnTablero[3][3] = ColumnaInicial;
                                        //AlmacenarCoordFinales
                                        ArrayBarcosEnTablero[3][4] = FilaFinal;
                                        ArrayBarcosEnTablero[3][5] = ColumnaFinal;
                                        break;
                    }
            } else if (Horizontal == 1){
                //Llenar PortaAviones en Matriz Horizontal
                    for(int a=ColumnaInicial;a<ColumnaFinal;a++){
                        if(MatrizTablero[CoordenadaFija][a]!= null){
                                System.out.println("Los barcos se interceptan, ponga otras coordenadas.");
                                System.out.println(""+"");
                                ISubmarinos();
                            } else{
                                MatrizTablero[CoordenadaFija][a]="O";
                            }
                    }
                    switch(CSubmarinos){
                                    case 3:
                                        //AlmacenarDirección
                                        ArrayBarcosEnTablero[1][1] = 0;
                                        //AlmacenarCoordIniciales
                                        ArrayBarcosEnTablero[1][2] = FilaInicial;
                                        ArrayBarcosEnTablero[1][3] = ColumnaInicial;
                                        //AlmacenarCoordFinales
                                        ArrayBarcosEnTablero[1][4] = FilaFinal;
                                        ArrayBarcosEnTablero[1][5] = ColumnaFinal;
                                        break;
                                    case 2:
                                        //AlmacenarDirección
                                        ArrayBarcosEnTablero[2][1] = 0;
                                        //AlmacenarCoordIniciales
                                        ArrayBarcosEnTablero[2][2] = FilaInicial;
                                        ArrayBarcosEnTablero[2][3] = ColumnaInicial;
                                        //AlmacenarCoordFinales
                                        ArrayBarcosEnTablero[2][4] = FilaFinal;
                                        ArrayBarcosEnTablero[2][5] = ColumnaFinal;
                                        break;
                                    case 1:
                                        //AlmacenarDirección
                                        ArrayBarcosEnTablero[3][1] = 1;
                                        //AlmacenarCoordIniciales
                                        ArrayBarcosEnTablero[3][2] = FilaInicial;
                                        ArrayBarcosEnTablero[3][3] = ColumnaInicial;
                                        //AlmacenarCoordFinales
                                        ArrayBarcosEnTablero[3][4] = FilaFinal;
                                        ArrayBarcosEnTablero[3][5] = ColumnaFinal;
                                        break;
                    }
            }
            CSubmarinos = CSubmarinos - 1;
            CuentaBarcosIngresados = CuentaBarcosIngresados + 1;
        }
        //EstadoActual Tablero
        System.out.println("Este es el estado actual del tablero: "+"");
        System.out.println(""+"");
        //Linea de Arriba
        for (int i=0;i<36;i++){
            System.out.print("-");
        }
        System.out.print("\n"+"| |->");
        for (int j=0;j<10;j++){
            System.out.print("|"+j+"|");
        }
        System.out.println("");
        for (int k=0;k<36;k++){
            System.out.print("-");
        }
        System.out.print("\n");
        //Columna de Izquierda y Matriz
        for (int a=0;a<10;a++){
            System.out.print("|"+a+"|->");
            for(int b=0;b<10;b++){
                System.out.print("|"+MatrizTablero[a][b]+"|");
            }
            System.out.print("\n");
        }
        System.out.print("Ingrese cualquier tecla para volver al Menu Ingresar Barcos: ");
        LeerCoordenadasIPA.next();
        System.out.println(""+"");
        IngresarBarcos();  
        }
    }
    static void IDestructores(){
        if (CDestructores==0){
            System.out.println("Ya Ingresaste 3 Destructores, si querés ingresarlos otra vez primero tenés que reiniciar el tablero, presione cualquier tecla para volver al menu Barcos");
            Scanner LeerRespuesta = new Scanner(System.in);
            String Res;
            Res=LeerRespuesta.next();
            IngresarBarcos();
        }else{
          int DistanciaFilas=0, DistanciaColumnas=0, Horizontal=0, Vertical=0, CoordenadaFija=0;
        Scanner LeerCoordenadasIPA = new Scanner(System.in);
        System.out.println("Ingrese las coordenadas de Inicio y Final en donde desea colocar su Destructor (Recuerde que ocupa 2 casillas y que no puede ponerlo en diagonal)");
        String CoordenadasPortaAviones = LeerCoordenadasIPA.next();
        //(FI,CI)-(FF,CF)
        String VectorCoorAux1 [] = CoordenadasPortaAviones.split("-"); 
        //(FI,CI)
        String CoordIniciales = VectorCoorAux1[0];
        //(FF,CF)
        String CoordFinales = VectorCoorAux1[1];
        
        //Iniciales
        String VectorCInicialCercano [] = CoordIniciales.split(",");
        //(FI
        String FilaInicialCercana = VectorCInicialCercano[0];
        //CI)
        String ColumnaInicialCercana = VectorCInicialCercano[1];
        
        //Finales
        String VectorCFinalCercano [] = CoordFinales.split(",");
        //(FF
        String FilaFinalCercana = VectorCFinalCercano[0];
        //CF)
        String ColumnaFinalCercana = VectorCFinalCercano[1];
        
        //Coordenadas Reales Iniciales
        //FI
        String VectorCFilaInicial [] = FilaInicialCercana.split("");
        int FilaInicial = Integer.parseInt(VectorCFilaInicial[1]);
        //CI
        String VectorCColumnaInicial [] = ColumnaInicialCercana.split("");
        int ColumnaInicial = Integer.parseInt(VectorCColumnaInicial[0]);
        
        //Coordenadas Reales Finales
        //FF
        String VectorCFilaFinal [] = FilaFinalCercana.split("");
        int FilaFinal = Integer.parseInt(VectorCFilaFinal[1]);
        //CF
        String VectorCColumnaFinal [] = ColumnaFinalCercana.split("");
        int ColumnaFinal = Integer.parseInt(VectorCColumnaFinal[0]);
       
        //Distancia de Fila Inicial a Fila Final
        for(int i=FilaInicial;i<FilaFinal;i++){
            DistanciaFilas = DistanciaFilas+1;
        }
        //Distancia de Columna Inicial a Columna Final
        for(int j=ColumnaInicial;j<ColumnaFinal;j++){
            DistanciaColumnas = DistanciaColumnas+1;
        }
        //Preparacion para Validacion
        if(DistanciaFilas == 0 && DistanciaColumnas == 2){
            Horizontal = 1;
            CoordenadaFija=FilaInicial;
        }
        if(DistanciaFilas == 2 && DistanciaColumnas == 0){
            Vertical = 1;
            CoordenadaFija=ColumnaInicial;
        }
        //Validacion
        if (Horizontal == 0 && Vertical == 0){
            System.out.println("Solo puede ponerlo de forma Horizontal o Vertical (Y como es Destructor solo puede ocupar 2 casillas), Intentelo de nuevo.");
            System.out.println(""+"");
            IDestructores();
        } else {
            if(Vertical == 1){
                //Llenar PortaAviones en Matriz Vertical
                    for(int a=FilaInicial;a<FilaFinal;a++){
                            if(MatrizTablero[a][CoordenadaFija]!= null){
                                System.out.println("Los barcos se interceptan, ponga otras coordenadas.");
                                System.out.println(""+"");
                                IDestructores();
                            } else{
                                MatrizTablero[a][CoordenadaFija]="O";
                            }
                    }
                    switch(CSubmarinos){
                                    case 3:
                                        //AlmacenarDirección
                                        ArrayBarcosEnTablero[4][1] = 1;
                                        //AlmacenarCoordIniciales
                                        ArrayBarcosEnTablero[4][2] = FilaInicial;
                                        ArrayBarcosEnTablero[4][3] = ColumnaInicial;
                                        //AlmacenarCoordFinales
                                        ArrayBarcosEnTablero[4][4] = FilaFinal;
                                        ArrayBarcosEnTablero[4][5] = ColumnaFinal;
                                        break;
                                    case 2:
                                        //AlmacenarDirección
                                        ArrayBarcosEnTablero[5][1] = 1;
                                        //AlmacenarCoordIniciales
                                        ArrayBarcosEnTablero[5][2] = FilaInicial;
                                        ArrayBarcosEnTablero[5][3] = ColumnaInicial;
                                        //AlmacenarCoordFinales
                                        ArrayBarcosEnTablero[5][4] = FilaFinal;
                                        ArrayBarcosEnTablero[5][5] = ColumnaFinal;
                                        break;
                                    case 1:
                                        //AlmacenarDirección
                                        ArrayBarcosEnTablero[6][1] = 1;
                                        //AlmacenarCoordIniciales
                                        ArrayBarcosEnTablero[6][2] = FilaInicial;
                                        ArrayBarcosEnTablero[6][3] = ColumnaInicial;
                                        //AlmacenarCoordFinales
                                        ArrayBarcosEnTablero[6][4] = FilaFinal;
                                        ArrayBarcosEnTablero[6][5] = ColumnaFinal;
                                        break;
                    }
            } else if (Horizontal == 1){
                //Llenar PortaAviones en Matriz Horizontal
                    for(int a=ColumnaInicial;a<ColumnaFinal;a++){
                        if(MatrizTablero[CoordenadaFija][a]!= null){
                                System.out.println("Los barcos se interceptan, ponga otras coordenadas.");
                                System.out.println(""+"");
                                IDestructores();
                            } else{
                                MatrizTablero[CoordenadaFija][a]="O";
                            }
                    }
                    switch(CSubmarinos){
                                    case 3:
                                        //AlmacenarDirección
                                        ArrayBarcosEnTablero[4][1] = 0;
                                        //AlmacenarCoordIniciales
                                        ArrayBarcosEnTablero[4][2] = FilaInicial;
                                        ArrayBarcosEnTablero[4][3] = ColumnaInicial;
                                        //AlmacenarCoordFinales
                                        ArrayBarcosEnTablero[4][4] = FilaFinal;
                                        ArrayBarcosEnTablero[4][5] = ColumnaFinal;
                                        break;
                                    case 2:
                                        //AlmacenarDirección
                                        ArrayBarcosEnTablero[5][1] = 0;
                                        //AlmacenarCoordIniciales
                                        ArrayBarcosEnTablero[5][2] = FilaInicial;
                                        ArrayBarcosEnTablero[5][3] = ColumnaInicial;
                                        //AlmacenarCoordFinales
                                        ArrayBarcosEnTablero[5][4] = FilaFinal;
                                        ArrayBarcosEnTablero[5][5] = ColumnaFinal;
                                        break;
                                    case 1:
                                        //AlmacenarDirección
                                        ArrayBarcosEnTablero[6][1] = 0;
                                        //AlmacenarCoordIniciales
                                        ArrayBarcosEnTablero[6][2] = FilaInicial;
                                        ArrayBarcosEnTablero[6][3] = ColumnaInicial;
                                        //AlmacenarCoordFinales
                                        ArrayBarcosEnTablero[6][4] = FilaFinal;
                                        ArrayBarcosEnTablero[6][5] = ColumnaFinal;
                                        break;
                    }
            }
            CDestructores = CDestructores - 1;
            CuentaBarcosIngresados = CuentaBarcosIngresados + 1;
        }
        //EstadoActual Tablero
        System.out.println("Este es el estado actual del tablero: "+"");
        System.out.println(""+"");
        //Linea de Arriba
        for (int i=0;i<36;i++){
            System.out.print("-");
        }
        System.out.print("\n"+"| |->");
        for (int j=0;j<10;j++){
            System.out.print("|"+j+"|");
        }
        System.out.println("");
        for (int k=0;k<36;k++){
            System.out.print("-");
        }
        System.out.print("\n");
        //Columna de Izquierda y Matriz
        for (int a=0;a<10;a++){
            System.out.print("|"+a+"|->");
            for(int b=0;b<10;b++){
                System.out.print("|"+MatrizTablero[a][b]+"|");
            }
            System.out.print("\n");
        }
        System.out.print("Ingrese cualquier tecla para volver al Menu Ingresar Barcos: ");
        LeerCoordenadasIPA.next();
        System.out.println(""+"");
        IngresarBarcos();  
        }
    }
    static void IFragatas (){
        if (CFragatas==0){
            System.out.println("Ya Ingresaste 2 Fragatas, si querés ingresarlas otra vez primero tenés que reiniciar el tablero, presione cualquier tecla para volver al menu Barcos");
            Scanner LeerRespuesta = new Scanner(System.in);
            String Res;
            Res=LeerRespuesta.next();
            IngresarBarcos();
        }else{
          int DistanciaFilas=0, DistanciaColumnas=0, Horizontal=0, Vertical=0, CoordenadaFija=0;
        Scanner LeerCoordenadasIPA = new Scanner(System.in);
        System.out.println("Ingrese las coordenadas de Inicio y Final en donde desea colocar su Fragata (Recuerde que ocupa 1 casilla y que no puede ponerlo en diagonal)");
        String CoordenadasPortaAviones = LeerCoordenadasIPA.next();
        //(FI,CI)-(FF,CF)
        String VectorCoorAux1 [] = CoordenadasPortaAviones.split("-"); 
        //(FI,CI)
        String CoordIniciales = VectorCoorAux1[0];
        //(FF,CF)
        String CoordFinales = VectorCoorAux1[1];
        
        //Iniciales
        String VectorCInicialCercano [] = CoordIniciales.split(",");
        //(FI
        String FilaInicialCercana = VectorCInicialCercano[0];
        //CI)
        String ColumnaInicialCercana = VectorCInicialCercano[1];
        
        //Finales
        String VectorCFinalCercano [] = CoordFinales.split(",");
        //(FF
        String FilaFinalCercana = VectorCFinalCercano[0];
        //CF)
        String ColumnaFinalCercana = VectorCFinalCercano[1];
        
        //Coordenadas Reales Iniciales
        //FI
        String VectorCFilaInicial [] = FilaInicialCercana.split("");
        int FilaInicial = Integer.parseInt(VectorCFilaInicial[1]);
        //CI
        String VectorCColumnaInicial [] = ColumnaInicialCercana.split("");
        int ColumnaInicial = Integer.parseInt(VectorCColumnaInicial[0]);
        
        //Coordenadas Reales Finales
        //FF
        String VectorCFilaFinal [] = FilaFinalCercana.split("");
        int FilaFinal = Integer.parseInt(VectorCFilaFinal[1]);
        //CF
        String VectorCColumnaFinal [] = ColumnaFinalCercana.split("");
        int ColumnaFinal = Integer.parseInt(VectorCColumnaFinal[0]);
       
        //Distancia de Fila Inicial a Fila Final
        for(int i=FilaInicial;i<FilaFinal;i++){
            DistanciaFilas = DistanciaFilas+1;
        }
        //Distancia de Columna Inicial a Columna Final
        for(int j=ColumnaInicial;j<ColumnaFinal;j++){
            DistanciaColumnas = DistanciaColumnas+1;
        }
        //Preparacion para Validacion
        if(DistanciaFilas == 0 && DistanciaColumnas == 1){
            Horizontal = 1;
            CoordenadaFija=FilaInicial;
        }
        if(DistanciaFilas == 1 && DistanciaColumnas == 0){
            Vertical = 1;
            CoordenadaFija=ColumnaInicial;
        }
        //Validacion
        if (Horizontal == 0 && Vertical == 0){
            System.out.println("Solo puede ponerlo de forma Horizontal o Vertical (Y como es Fragata solo puede ocupar 1 casilla), Intentelo de nuevo.");
            System.out.println(""+"");
            IFragatas();
        } else {
            if(Vertical == 1){
                //Llenar PortaAviones en Matriz Vertical
                    for(int a=FilaInicial;a<FilaFinal;a++){
                            if(MatrizTablero[a][CoordenadaFija]!= null){
                                System.out.println("Los barcos se interceptan, ponga otras coordenadas.");
                                System.out.println(""+"");
                                IFragatas();
                            } else{
                                MatrizTablero[a][CoordenadaFija]="O";
                            }
                    }
                    switch(CSubmarinos){
                                    case 2:
                                        //AlmacenarDirección
                                        ArrayBarcosEnTablero[7][1] = 1;
                                        //AlmacenarCoordIniciales
                                        ArrayBarcosEnTablero[7][2] = FilaInicial;
                                        ArrayBarcosEnTablero[7][3] = ColumnaInicial;
                                        //AlmacenarCoordFinales
                                        ArrayBarcosEnTablero[7][4] = FilaFinal;
                                        ArrayBarcosEnTablero[7][5] = ColumnaFinal;
                                        break;
                                    case 1:
                                        //AlmacenarDirección
                                        ArrayBarcosEnTablero[8][1] = 1;
                                        //AlmacenarCoordIniciales
                                        ArrayBarcosEnTablero[8][2] = FilaInicial;
                                        ArrayBarcosEnTablero[8][3] = ColumnaInicial;
                                        //AlmacenarCoordFinales
                                        ArrayBarcosEnTablero[8][4] = FilaFinal;
                                        ArrayBarcosEnTablero[8][5] = ColumnaFinal;
                                        break;
                    }
            } else if (Horizontal == 1){
                //Llenar PortaAviones en Matriz Horizontal
                    for(int a=ColumnaInicial;a<ColumnaFinal;a++){
                        if(MatrizTablero[CoordenadaFija][a]!= null){
                                System.out.println("Los barcos se interceptan, ponga otras coordenadas.");
                                System.out.println(""+"");
                                IFragatas();
                            } else{
                                MatrizTablero[CoordenadaFija][a]="O";
                            }
                    }
                    switch(CSubmarinos){
                                    case 2:
                                        //AlmacenarDirección
                                        ArrayBarcosEnTablero[7][1] = 0;
                                        //AlmacenarCoordIniciales
                                        ArrayBarcosEnTablero[7][2] = FilaInicial;
                                        ArrayBarcosEnTablero[7][3] = ColumnaInicial;
                                        //AlmacenarCoordFinales
                                        ArrayBarcosEnTablero[7][4] = FilaFinal;
                                        ArrayBarcosEnTablero[7][5] = ColumnaFinal;
                                        break;
                                    case 1:
                                        //AlmacenarDirección
                                        ArrayBarcosEnTablero[8][1] = 0;
                                        //AlmacenarCoordIniciales
                                        ArrayBarcosEnTablero[8][2] = FilaInicial;
                                        ArrayBarcosEnTablero[8][3] = ColumnaInicial;
                                        //AlmacenarCoordFinales
                                        ArrayBarcosEnTablero[8][4] = FilaFinal;
                                        ArrayBarcosEnTablero[8][5] = ColumnaFinal;
                                        break;
                    }
            }
            CFragatas = CFragatas - 1;
            CuentaBarcosIngresados = CuentaBarcosIngresados + 1;
        }
        //EstadoActual Tablero
        System.out.println("Este es el estado actual del tablero: "+"");
        System.out.println(""+"");
        //Linea de Arriba
        for (int i=0;i<36;i++){
            System.out.print("-");
        }
        System.out.print("\n"+"| |->");
        for (int j=0;j<10;j++){
            System.out.print("|"+j+"|");
        }
        System.out.println("");
        for (int k=0;k<36;k++){
            System.out.print("-");
        }
        System.out.print("\n");
        //Columna de Izquierda y Matriz
        for (int a=0;a<10;a++){
            System.out.print("|"+a+"|->");
            for(int b=0;b<10;b++){
                System.out.print("|"+MatrizTablero[a][b]+"|");
            }
            System.out.print("\n");
        }
        System.out.print("Ingrese cualquier tecla para volver al Menu Ingresar Barcos: ");
        LeerCoordenadasIPA.next();
        System.out.println(""+"");
        IngresarBarcos();  
        }
    }
    static void IEasterEgg(){
        if (CEasterEgg==0){
            System.out.println("Ya Ingresaste 1 EasterEgg, si querés ingresarlo otra vez primero tenés que reiniciar el tablero, presione cualquier tecla para volver al menu Barcos");
            Scanner LeerRespuesta = new Scanner(System.in);
            String Res;
            Res=LeerRespuesta.next();
            IngresarBarcos();
        }else{
          int DistanciaFilas=0, DistanciaColumnas=0, Horizontal=0, Vertical=0, CoordenadaFija=0;
        Scanner LeerCoordenadasIPA = new Scanner(System.in);
        System.out.println("Ingrese las coordenadas de Inicio y Final en donde desea colocar su EasterEgg (Recuerde que ocupa 1 casilla y que no puede ponerlo en diagonal)");
        String CoordenadasPortaAviones = LeerCoordenadasIPA.next();
        //(FI,CI)-(FF,CF)
        String VectorCoorAux1 [] = CoordenadasPortaAviones.split("-"); 
        //(FI,CI)
        String CoordIniciales = VectorCoorAux1[0];
        //(FF,CF)
        String CoordFinales = VectorCoorAux1[1];
        
        //Iniciales
        String VectorCInicialCercano [] = CoordIniciales.split(",");
        //(FI
        String FilaInicialCercana = VectorCInicialCercano[0];
        //CI)
        String ColumnaInicialCercana = VectorCInicialCercano[1];
        
        //Finales
        String VectorCFinalCercano [] = CoordFinales.split(",");
        //(FF
        String FilaFinalCercana = VectorCFinalCercano[0];
        //CF)
        String ColumnaFinalCercana = VectorCFinalCercano[1];
        
        //Coordenadas Reales Iniciales
        //FI
        String VectorCFilaInicial [] = FilaInicialCercana.split("");
        int FilaInicial = Integer.parseInt(VectorCFilaInicial[1]);
        //CI
        String VectorCColumnaInicial [] = ColumnaInicialCercana.split("");
        int ColumnaInicial = Integer.parseInt(VectorCColumnaInicial[0]);
        
        //Coordenadas Reales Finales
        //FF
        String VectorCFilaFinal [] = FilaFinalCercana.split("");
        int FilaFinal = Integer.parseInt(VectorCFilaFinal[1]);
        //CF
        String VectorCColumnaFinal [] = ColumnaFinalCercana.split("");
        int ColumnaFinal = Integer.parseInt(VectorCColumnaFinal[0]);
       
        //Distancia de Fila Inicial a Fila Final
        for(int i=FilaInicial;i<FilaFinal;i++){
            DistanciaFilas = DistanciaFilas+1;
        }
        //Distancia de Columna Inicial a Columna Final
        for(int j=ColumnaInicial;j<ColumnaFinal;j++){
            DistanciaColumnas = DistanciaColumnas+1;
        }
        //Preparacion para Validacion
        if(DistanciaFilas == 0 && DistanciaColumnas == 1){
            Horizontal = 1;
            CoordenadaFija=FilaInicial;
        }
        if(DistanciaFilas == 1 && DistanciaColumnas == 0){
            Vertical = 1;
            CoordenadaFija=ColumnaInicial;
        }
        //Validacion
        if (Horizontal == 0 && Vertical == 0){
            System.out.println("Solo puede ponerlo de forma Horizontal o Vertical (Y como es EasterEgg solo puede ocupar 1 casilla), Intentelo de nuevo.");
            System.out.println(""+"");
            IEasterEgg();
        } else {
            if(Vertical == 1){
                //Llenar PortaAviones en Matriz Vertical
                    for(int a=FilaInicial;a<FilaFinal;a++){
                            if(MatrizTablero[a][CoordenadaFija]!= null){
                                System.out.println("Los barcos se interceptan, ponga otras coordenadas.");
                                System.out.println(""+"");
                                IEasterEgg();
                            } else{
                                MatrizTablero[a][CoordenadaFija]="$";
                            }
                    }
                    //AlmacenarDirección
                    ArrayBarcosEnTablero[9][1] = 1;
                    //AlmacenarCoordIniciales
                    ArrayBarcosEnTablero[9][2] = FilaInicial;
                    ArrayBarcosEnTablero[9][3] = ColumnaInicial;
                    //AlmacenarCoordFinales
                    ArrayBarcosEnTablero[9][4] = FilaFinal;
                    ArrayBarcosEnTablero[9][5] = ColumnaFinal;
            } else if (Horizontal == 1){
                //Llenar PortaAviones en Matriz Horizontal
                    for(int a=ColumnaInicial;a<ColumnaFinal;a++){
                        if(MatrizTablero[CoordenadaFija][a]!= null){
                                System.out.println("Los barcos se interceptan, ponga otras coordenadas.");
                                System.out.println(""+"");
                                IEasterEgg();
                            } else{
                                MatrizTablero[CoordenadaFija][a]="$";
                            }
                    }
                    //AlmacenarDirección
                    ArrayBarcosEnTablero[9][1] = 0;
                    //AlmacenarCoordIniciales
                    ArrayBarcosEnTablero[9][2] = FilaInicial;
                    ArrayBarcosEnTablero[9][3] = ColumnaInicial;
                    //AlmacenarCoordFinales
                    ArrayBarcosEnTablero[9][4] = FilaFinal;
                    ArrayBarcosEnTablero[9][5] = ColumnaFinal;
            }
            CEasterEgg = CEasterEgg - 1;
            CuentaBarcosIngresados = CuentaBarcosIngresados + 1;
        }
        //EstadoActual Tablero
        System.out.println("Este es el estado actual del tablero: "+"");
        System.out.println(""+"");
        //Linea de Arriba
        for (int i=0;i<36;i++){
            System.out.print("-");
        }
        System.out.print("\n"+"| |->");
        for (int j=0;j<10;j++){
            System.out.print("|"+j+"|");
        }
        System.out.println("");
        for (int k=0;k<36;k++){
            System.out.print("-");
        }
        System.out.print("\n");
        //Columna de Izquierda y Matriz
        for (int a=0;a<10;a++){
            System.out.print("|"+a+"|->");
            for(int b=0;b<10;b++){
                System.out.print("|"+MatrizTablero[a][b]+"|");
            }
            System.out.print("\n");
        }
        System.out.print("Ingrese cualquier tecla para volver al Menu Ingresar Barcos: ");
        LeerCoordenadasIPA.next();
        System.out.println(""+"");
        IngresarBarcos();  
        }
    }       
}