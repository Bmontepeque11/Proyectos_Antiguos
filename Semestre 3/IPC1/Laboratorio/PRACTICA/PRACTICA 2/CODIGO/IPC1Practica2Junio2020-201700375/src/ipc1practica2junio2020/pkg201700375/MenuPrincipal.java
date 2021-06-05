/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipc1practica2junio2020.pkg201700375;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author C55D-B5319-PC
 */
public class MenuPrincipal extends JFrame{
    
    static Object MatrizJugadores[][] = new Object [20][4];//Username|Vidas|Punteo|TiempoRestante
    static int IndexMatrizJugadores = -1;
    public MenuPrincipal() throws HeadlessException {
        //Inicializador del MenuPrincipal
        JFrame Menu = new JFrame("MenuPrincipal");
        Menu.setLocationRelativeTo(null);
        Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Menu.getContentPane();
        Menu.setLayout(null);
        Menu.pack();
        Menu.setBounds(150, 100, 200, 300);
        Menu.setResizable(false);
        Menu.setVisible(true);
        
        //Button TOP5QuickSort
        JButton BTop5QuickSort = new JButton("Top 5 QuickSort");
        BTop5QuickSort.setBounds(10, 10, 175, 40);
        BTop5QuickSort.setVisible(true);
        BTop5QuickSort.setFont(BTop5QuickSort.getFont().deriveFont(14.0f));
        BTop5QuickSort.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             
         }
        });
        //Button TOP5BubbleSort
        JButton BTop5BubbleSort = new JButton("Top 5 BubbleSort");
        BTop5BubbleSort.setBounds(10, 60, 175, 40);
        BTop5BubbleSort.setVisible(true);
        BTop5BubbleSort.setFont(BTop5BubbleSort.getFont().deriveFont(14.0f));
        BTop5BubbleSort.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             
         }
        });
        //Button IniciarJuego
        JButton BIniciarJuego = new JButton("Iniciar Juego");
        BIniciarJuego.setBounds(10, 110, 175, 40);
        BIniciarJuego.setVisible(true);
        BIniciarJuego.setFont(BIniciarJuego.getFont().deriveFont(14.0f));
        BIniciarJuego.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             Menu.dispose();
             MenuPrincipal.LogIn();
         }
        });
        //Button AcercaDe
        JButton BAcercaDe = new JButton("Acerca De");
        BAcercaDe.setBounds(10, 160, 175, 40);
        BAcercaDe.setVisible(true);
        BAcercaDe.setFont(BAcercaDe.getFont().deriveFont(14.0f));
        BAcercaDe.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
            JFrame g = new JFrame();
            JOptionPane.showMessageDialog(g, "Nombre: Bryan Steve Montepeque Santos"+"\n"+"Carnet: 201700375"+"\n"+"Correlativo: 24");
         }
        });
        //Button Salir
        JButton BSalir = new JButton("Salir");
        BSalir.setBounds(10, 210, 175, 40);
        BSalir.setVisible(true);
        BSalir.setFont(BSalir.getFont().deriveFont(14.0f));
        BSalir.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             System.exit(0);
         }
        });
        //adds
        Menu.add(BTop5QuickSort);
        Menu.add(BTop5BubbleSort);
        Menu.add(BIniciarJuego);
        Menu.add(BAcercaDe);
        Menu.add(BSalir);
        Menu.repaint();
    }
    public static void LogIn(){
        //Inicializador del LogIn
        JFrame JL = new JFrame("LogIn");
        JL.setLocationRelativeTo(null);
        JL.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JL.getContentPane();
        JL.setLayout(null);
        JL.pack();
        JL.setBounds(150, 10, 50, 100);
        JL.setResizable(false);
        JL.setVisible(true);
        //Username
        JLabel RUsername = new JLabel("Username:");
        RUsername.setBounds(5, 5, 60, 15);
        RUsername.setVisible(true);
        RUsername.setFont(RUsername.getFont().deriveFont(10.0f));
        JTextField JTFUsername = new JTextField();
        JTFUsername.setBounds(15, 25, 100, 15);
        JTFUsername.setVisible(true);
        JTFUsername.setFont(JTFUsername.getFont().deriveFont(10.0f));
        //Button Jugar
        JButton BJugar = new JButton("Jugar");
        BJugar.setBounds(15, 45, 100, 15);
        BJugar.setVisible(true);
        BJugar.setFont(BJugar.getFont().deriveFont(10.0f));
        BJugar.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             IndexMatrizJugadores = IndexMatrizJugadores + 1;
             MatrizJugadores[IndexMatrizJugadores][0] = JTFUsername.getText();
             JuegoReal JR = new JuegoReal(IndexMatrizJugadores);
             JL.dispose();
         }
        });
        //adds
        JL.add(RUsername);
        JL.add(JTFUsername);
        JL.add(BJugar);
        JL.repaint();
    }
}
