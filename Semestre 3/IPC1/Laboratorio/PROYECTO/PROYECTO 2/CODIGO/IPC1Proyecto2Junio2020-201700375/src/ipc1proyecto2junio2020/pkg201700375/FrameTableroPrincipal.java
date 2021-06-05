/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipc1proyecto2junio2020.pkg201700375;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileFilter;
/**
 *
 * @author C55D-B5319-PC
 */
public class FrameTableroPrincipal implements KeyListener {
    static JLabel MatrizVisualTablero [][] = new JLabel[5][7];
    static int FilaB=0, ColB=0;
    static JFrame RB;
    static String VectorLineasArchivo;
    //IDs Listas
    static int IDListaDoble=0;
    static int IDPila=0;
    static int IDCola=0;
    static int IDCircularDoble=0;
    //DeclararEstructuras
    static ListaSimple LS = new ListaSimple();
    static ListaDoble LD = new ListaDoble();
    static ListaCircularDoble LCD = new ListaCircularDoble();
    static Pila P = new Pila();
    static Cola C = new Cola();
    public FrameTableroPrincipal() {
        //Inicializador del MenuPrincipal
        RB = new JFrame("Recoge Bloques");
        RB.setLocationRelativeTo(null);
        RB.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RB.getContentPane();
        RB.setLayout(null);
        RB.pack();
        RB.setBounds(150, 20, 540, 350);
        RB.setResizable(false);
        RB.setVisible(true);
        
        //Button CargarArchivo
        JButton BCA = new JButton("Cargar Archivo");
        BCA.setBounds(10, 10, 120, 15);
        BCA.setVisible(true);
        BCA.setFont(BCA.getFont().deriveFont(12.0f));
        BCA.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             JFileChooser CargarArchivo = new JFileChooser();
             CargarArchivo.addChoosableFileFilter(new FileFilter(){
                 @Override
                 public boolean accept(File file) {
                     if (file.isDirectory()){
                         return true;
                     } else {
                         return file.getName().toLowerCase().endsWith(".ipc1");
                     }
                 }

                 @Override
                 public String getDescription() {
                     return "Archivos IPC1 (*.ipc1)";
                 }
             });
             CargarArchivo.setDialogTitle("Cargar Archivo");
             CargarArchivo.setVisible(true);
             CargarArchivo.showOpenDialog(RB);
             File Archivo = CargarArchivo.getSelectedFile();
             String NombreArchivo = Archivo.getAbsolutePath();
             System.out.println("El Archivo cargado es: "+ NombreArchivo);
             String LineaActual;
             int FilaBColor, ColBColor, ValorBColor;
             String ColorBColor;
             int ContadorLineaRecorrida = -1; //Contador = 0 -> Header, Contador>0 -> Datos ;v
             
             try {
                 //Scanner (Leer el Archivo Linea Por Línea)
                 Scanner Scan = new Scanner(Archivo);
                 Scan.useDelimiter("\n");
                 while(Scan.hasNext()){
                     ContadorLineaRecorrida = ContadorLineaRecorrida + 1;
                     LineaActual = Scan.next();
                     System.out.println(LineaActual);
                     VectorLineasArchivo = LineaActual;
                     if(ContadorLineaRecorrida>0){
                         System.out.println("Elemento Real " + ContadorLineaRecorrida + ": "+ VectorLineasArchivo);
                         String VectorObjetosLineaArchivo[] = VectorLineasArchivo.split(",");
                         FilaBColor = Integer.parseInt(VectorObjetosLineaArchivo[0]);
                         ColBColor = Integer.parseInt(VectorObjetosLineaArchivo[1]);
                         ValorBColor = Integer.parseInt(VectorObjetosLineaArchivo[2]);
                         ColorBColor = VectorObjetosLineaArchivo[3];
                         //PintarTablero
                         MatrizVisualTablero[FilaBColor][ColBColor].setText(String.valueOf(ValorBColor));
                         switch(ColorBColor){
                             case "AZUL"://ListaDoble
                                 MatrizVisualTablero[FilaBColor][ColBColor].setBackground(Color.blue);
                                 break;
                             case "ROJO"://ListaCircular
                                 MatrizVisualTablero[FilaBColor][ColBColor].setBackground(Color.red);
                                 break;
                             case "AMARILLO"://Pila
                                 MatrizVisualTablero[FilaBColor][ColBColor].setBackground(Color.yellow);
                                 break;
                             case "VERDE"://Cola
                                 MatrizVisualTablero[FilaBColor][ColBColor].setBackground(Color.green);
                                 break;
                         }
                         LS.AgregarAlFinal(ContadorLineaRecorrida, FilaBColor, ColBColor, ValorBColor, ColorBColor);
                     }
                 }
                 //Ver si guardó los objetos en la lista
                 LS.Listar();
                 //Cerrar Scanner
                 Scan.close();
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(FrameTableroPrincipal.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
        });
        BCA.setFocusable(false);
        
        //Button Reiniciar
        JButton BR = new JButton("Reiniciar");
        BR.setBounds(10, 30, 120, 15);
        BR.setVisible(true);
        BR.setFont(BR.getFont().deriveFont(12.0f));
        BR.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             //ReiniciarCuentaID
             IDCircularDoble = 0;
             IDCola = 0;
             IDListaDoble = 0;
             IDPila = 0;
             //VaciarEstructuras
             LS.LimpiarLista();
             LD.LimpiarLista();
             LCD.LimpiarLista();
             P.LimpiarPila();
             C.LimpiarCola();
             //LimpiarTablero
             for (int i = 0; i < 5; i++) {
                 for (int j = 0; j < 7; j++) {
                     //              JLabelX,Y
                     MatrizVisualTablero[i][j].setVisible(true);
                     MatrizVisualTablero[i][j].setFont(MatrizVisualTablero[i][j].getFont().deriveFont(14.0f));
                     MatrizVisualTablero[i][j].setOpaque(true);
                     MatrizVisualTablero[i][j].setBackground(Color.white);
                     MatrizVisualTablero[i][j].setText("");
                 }
             }
             //ReiniciarBloque
             FilaB = 0;
             ColB = 0;
             MatrizVisualTablero[FilaB][ColB].setBackground(Color.black);
         }
        });
        BR.setFocusable(false);
        
        //Button ListaCircular
        JButton BLC = new JButton("Lista Circular");
        BLC.setBounds(140, 15, 120, 15);
        BLC.setVisible(true);
        BLC.setFont(BLC.getFont().deriveFont(12.0f));
        BLC.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             FrameListaCircularD FLCD = new FrameListaCircularD();
         }
        });
        BLC.setFocusable(false);
        
        //Button ListaSimple
        JButton BLS = new JButton("Lista Simple");
        BLS.setBounds(270, 10, 120, 15);
        BLS.setVisible(true);
        BLS.setFont(BLS.getFont().deriveFont(12.0f));
        BLS.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             FrameListaS FLS = new FrameListaS();
         }
        });
        BLS.setFocusable(false);
        
        //Button ListaDoble
        JButton BLD = new JButton("Lista Doble");
        BLD.setBounds(400, 10, 120, 15);
        BLD.setVisible(true);
        BLD.setFont(BLD.getFont().deriveFont(12.0f));
        BLD.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             FrameListaD FLD = new FrameListaD();
         }
        });
        BLD.setFocusable(false);
        
        //Button Pila
        JButton BP = new JButton("Pila");
        BP.setBounds(270, 30, 120, 15);
        BP.setVisible(true);
        BP.setFont(BP.getFont().deriveFont(12.0f));
        BP.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             FramePila FP = new FramePila();
         }
        });
        BP.setFocusable(false);
        
        //Button ListaDoble
        JButton BC = new JButton("Cola");
        BC.setBounds(400, 30, 120, 15);
        BC.setVisible(true);
        BC.setFont(BC.getFont().deriveFont(12.0f));
        BC.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             FrameCola FC = new FrameCola();
         }
        });
        BC.setFocusable(false);
        
        //JPanel
        JPanel PanelT = new JPanel();
        PanelT.setLayout(null);
        PanelT.setVisible(true);
        PanelT.setBackground(Color.GRAY);
        PanelT.setBounds(10, 60, 350, 250);
  
        //CrearTablero
        int X=0,Y=0;
        for(int i=0;i<5;i++){
            for(int j=0;j<7;j++){
                //              JLabelX,Y
                MatrizVisualTablero[i][j] = new JLabel();
                MatrizVisualTablero[i][j].setBounds(X, Y, 50, 50);
                MatrizVisualTablero[i][j].setVisible(true);
                MatrizVisualTablero[i][j].setFont(MatrizVisualTablero[i][j].getFont().deriveFont(14.0f));
                MatrizVisualTablero[i][j].setOpaque(true);
                MatrizVisualTablero[i][j].setBackground(Color.white);
                PanelT.add(MatrizVisualTablero[i][j]);
                X=X+50;
            }
            X=0;
            Y=Y+50;
        }
        
        //PosicionInicialBloque
        MatrizVisualTablero[FilaB][ColB].setBackground(Color.black);
        MatrizVisualTablero[FilaB][ColB].repaint();
        RB.addKeyListener(this);
        
        //adds
        RB.add(BCA);
        RB.add(BR);
        RB.add(BLC);
        RB.add(BLS);
        RB.add(BLD);
        RB.add(BP);
        RB.add(BC);
        RB.add(PanelT);
        RB.repaint();
    }

    @Override
    public void keyTyped(KeyEvent ke){ 
           
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_RIGHT:
                  MatrizVisualTablero[FilaB][ColB].setBackground(Color.white);
                  MatrizVisualTablero[FilaB][ColB].setText("");
                  MatrizVisualTablero[FilaB][ColB].repaint();
                  if(ColB<6){
                      ColB = ColB+1;
                  }
                  //Ver a donde pertenece el Bloque
                  //ListaDoble
                  if(MatrizVisualTablero[FilaB][ColB].getBackground() == Color.blue){
                      IDListaDoble = IDListaDoble + 1;
                      LD.AgregarAlFinal(IDListaDoble, FilaB, ColB, Integer.parseInt(MatrizVisualTablero[FilaB][ColB].getText()), "AZUL");
                  }
                  //ListaCircularDoblementeEnlazada
                  if(MatrizVisualTablero[FilaB][ColB].getBackground() == Color.red){
                      IDCircularDoble = IDCircularDoble + 1;
                      LCD.AgregarAlFinal(IDCircularDoble, FilaB, ColB, Integer.parseInt(MatrizVisualTablero[FilaB][ColB].getText()), "ROJO");
                  }
                  //Pila
                  if(MatrizVisualTablero[FilaB][ColB].getBackground() == Color.yellow){
                      IDPila = IDPila + 1;
                      P.push(IDPila, FilaB, ColB, Integer.parseInt(MatrizVisualTablero[FilaB][ColB].getText()), "AMARILLO");
                  }
                  //Cola
                  if(MatrizVisualTablero[FilaB][ColB].getBackground() == Color.green){
                      IDCola = IDCola + 1;
                      C.push(IDCola, FilaB, ColB, Integer.parseInt(MatrizVisualTablero[FilaB][ColB].getText()), "VERDE");
                  }
                      //PintarElBloque
                      MatrizVisualTablero[FilaB][ColB].setBackground(Color.black);
                      MatrizVisualTablero[FilaB][ColB].repaint();
                break;
                
            case KeyEvent.VK_LEFT:
                MatrizVisualTablero[FilaB][ColB].setBackground(Color.white);
                MatrizVisualTablero[FilaB][ColB].setText("");
                MatrizVisualTablero[FilaB][ColB].repaint();
                  if(ColB>0){
                      ColB = ColB-1;
                  }
                  //Ver a donde pertenece el Bloque
                  //ListaDoble
                  if(MatrizVisualTablero[FilaB][ColB].getBackground() == Color.blue){
                      IDListaDoble = IDListaDoble + 1;
                      LD.AgregarAlFinal(IDListaDoble, FilaB, ColB, Integer.parseInt(MatrizVisualTablero[FilaB][ColB].getText()), "AZUL");
                  }
                  //ListaCircularDoblementeEnlazada
                  if(MatrizVisualTablero[FilaB][ColB].getBackground() == Color.red){
                      IDCircularDoble = IDCircularDoble + 1;
                      LCD.AgregarAlFinal(IDCircularDoble, FilaB, ColB, Integer.parseInt(MatrizVisualTablero[FilaB][ColB].getText()), "ROJO");
                  }
                  //Pila
                  if(MatrizVisualTablero[FilaB][ColB].getBackground() == Color.yellow){
                      IDPila = IDPila + 1;
                      P.push(IDPila, FilaB, ColB, Integer.parseInt(MatrizVisualTablero[FilaB][ColB].getText()), "AMARILLO");
                  }
                  //Cola
                  if(MatrizVisualTablero[FilaB][ColB].getBackground() == Color.green){
                      IDCola = IDCola + 1;
                      C.push(IDCola, FilaB, ColB, Integer.parseInt(MatrizVisualTablero[FilaB][ColB].getText()), "VERDE");
                  }
                      //PintarElBloque
                      MatrizVisualTablero[FilaB][ColB].setBackground(Color.black);
                      MatrizVisualTablero[FilaB][ColB].repaint();
                break;
            case KeyEvent.VK_DOWN:
                MatrizVisualTablero[FilaB][ColB].setBackground(Color.white);
                MatrizVisualTablero[FilaB][ColB].setText("");
                MatrizVisualTablero[FilaB][ColB].repaint();
                  if(FilaB<4){
                      FilaB = FilaB + 1;
                  }
                  //Ver a donde pertenece el Bloque
                  //ListaDoble
                  if(MatrizVisualTablero[FilaB][ColB].getBackground() == Color.blue){
                      IDListaDoble = IDListaDoble + 1;
                      LD.AgregarAlFinal(IDListaDoble, FilaB, ColB, Integer.parseInt(MatrizVisualTablero[FilaB][ColB].getText()), "AZUL");
                  }
                  //ListaCircularDoblementeEnlazada
                  if(MatrizVisualTablero[FilaB][ColB].getBackground() == Color.red){
                      IDCircularDoble = IDCircularDoble + 1;
                      LCD.AgregarAlFinal(IDCircularDoble, FilaB, ColB, Integer.parseInt(MatrizVisualTablero[FilaB][ColB].getText()), "ROJO");
                  }
                  //Pila
                  if(MatrizVisualTablero[FilaB][ColB].getBackground() == Color.yellow){
                      IDPila = IDPila + 1;
                      P.push(IDPila, FilaB, ColB, Integer.parseInt(MatrizVisualTablero[FilaB][ColB].getText()), "AMARILLO");
                  }
                  //Cola
                  if(MatrizVisualTablero[FilaB][ColB].getBackground() == Color.green){
                      IDCola = IDCola + 1;
                      C.push(IDCola, FilaB, ColB, Integer.parseInt(MatrizVisualTablero[FilaB][ColB].getText()), "VERDE");
                  }
                      //PintarElBloque
                      MatrizVisualTablero[FilaB][ColB].setBackground(Color.black);
                      MatrizVisualTablero[FilaB][ColB].repaint();
                break;
            case KeyEvent.VK_UP:
                MatrizVisualTablero[FilaB][ColB].setBackground(Color.white);
                MatrizVisualTablero[FilaB][ColB].setText("");
                MatrizVisualTablero[FilaB][ColB].repaint();
                  if(FilaB>0){
                      FilaB = FilaB - 1;
                  }
                  //Ver a donde pertenece el Bloque
                  //ListaDoble
                  if(MatrizVisualTablero[FilaB][ColB].getBackground() == Color.blue){
                      IDListaDoble = IDListaDoble + 1;
                      LD.AgregarAlFinal(IDListaDoble, FilaB, ColB, Integer.parseInt(MatrizVisualTablero[FilaB][ColB].getText()), "AZUL");
                  }
                  //ListaCircularDoblementeEnlazada
                  if(MatrizVisualTablero[FilaB][ColB].getBackground() == Color.red){
                      IDCircularDoble = IDCircularDoble + 1;
                      LCD.AgregarAlFinal(IDCircularDoble, FilaB, ColB, Integer.parseInt(MatrizVisualTablero[FilaB][ColB].getText()), "ROJO");
                  }
                  //Pila
                  if(MatrizVisualTablero[FilaB][ColB].getBackground() == Color.yellow){
                      IDPila = IDPila + 1;
                      P.push(IDPila, FilaB, ColB, Integer.parseInt(MatrizVisualTablero[FilaB][ColB].getText()), "AMARILLO");
                  }
                  //Cola
                  if(MatrizVisualTablero[FilaB][ColB].getBackground() == Color.green){
                      IDCola = IDCola + 1;
                      C.push(IDCola, FilaB, ColB, Integer.parseInt(MatrizVisualTablero[FilaB][ColB].getText()), "VERDE");
                  }
                      //PintarElBloque
                      MatrizVisualTablero[FilaB][ColB].setBackground(Color.black);
                      MatrizVisualTablero[FilaB][ColB].repaint();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        switch(ke.getKeyCode()){
            case KeyEvent.VK_RIGHT:
                break;
            case KeyEvent.VK_LEFT:
                break;
            case KeyEvent.VK_DOWN:
                break;
            case KeyEvent.VK_UP:
                break;
            }
    }
    
}
