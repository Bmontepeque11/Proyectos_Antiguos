/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipc1vacasjunio2020tarea3;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author C55D-B5319-PC
 */
public class CambiarColoresLabel extends Thread {
    int CuentaRojo = 0, CuentaAzul = 0, delay = 40;
    String CuentaTextFieldRojo, CuentaTextFieldAzul;
    JLabel RLabel1 = new JLabel(), RLabel2 = new JLabel();
    JTextField JTFAzul = new JTextField(CuentaAzul), JTFRojo = new JTextField(CuentaRojo);
    JFrame T3 = new JFrame("Tarea3");
    public CambiarColoresLabel() {
        //0 = Rojo, 1 = Azul 
        //Inicializador del MenuPrincipal
        T3.setLocationRelativeTo(null);
        T3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        T3.getContentPane();
        T3.setLayout(null);
        T3.pack();
        T3.setBounds(150, 100, 330, 200);
        T3.setResizable(false);
        T3.setVisible(true);
        //JLabel1
        RLabel1.setBounds(10, 50, 100, 100);
        RLabel1.setVisible(true);
        RLabel1.setOpaque(true);
        RLabel1.setBackground(Color.red);
        
        //JLabel2
        RLabel2.setBounds(120, 50, 100, 100);
        RLabel2.setVisible(true);
        RLabel2.setOpaque(true);
        RLabel2.setBackground(Color.blue);
        
        //JabelRRojo
        JLabel RRojo = new JLabel("Rojo:");
        RRojo.setBounds(230, 125, 50, 15);
        RRojo.setVisible(true);
        RRojo.setFont(RRojo.getFont().deriveFont(10.0f));
        //JabelRAzul
        JLabel RAzul = new JLabel("Azúl:");
        RAzul.setBounds(230, 100, 50, 15);
        RAzul.setVisible(true);
        RAzul.setFont(RAzul.getFont().deriveFont(10.0f));
        //JTFAzul
        JTextField JTFAzul = new JTextField();
        JTFAzul.setBounds(260, 100, 50, 15);
        JTFAzul.setVisible(true);
        JTFAzul.setFont(JTFAzul.getFont().deriveFont(10.0f));
        //JTFRojo
        JTextField JTFRojo = new JTextField();
        JTFRojo.setBounds(260, 125, 50, 15);
        JTFRojo.setVisible(true);
        JTFRojo.setFont(JTFRojo.getFont().deriveFont(10.0f));
        //Click en Labels
        HiloActualizarTextFields ATextFields = new HiloActualizarTextFields(JTFAzul, JTFRojo, CuentaTextFieldRojo, CuentaTextFieldAzul);
        ATextFields.start();
        //Izquierda
        RLabel1.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e){
                if (RLabel1.getBackground() == Color.red){
                    CuentaRojo = CuentaRojo + 1;
                    CuentaTextFieldRojo = String.valueOf(CuentaRojo);
                    ATextFields.setCuentaTextFieldRojo(CuentaTextFieldRojo);
                } else if(RLabel1.getBackground() == Color.blue){
                    CuentaAzul = CuentaAzul + 1;
                    CuentaTextFieldAzul = String.valueOf(CuentaAzul);
                    ATextFields.setCuentaTextFieldAzul(CuentaTextFieldAzul);
                }
            }
        });
        //Derecha
        RLabel2.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e){
                if (RLabel2.getBackground() == Color.red){
                    CuentaRojo = CuentaRojo + 1;
                    CuentaTextFieldRojo = String.valueOf(CuentaRojo);
                    ATextFields.setCuentaTextFieldRojo(CuentaTextFieldRojo);
                } else if(RLabel2.getBackground() == Color.blue){
                    CuentaAzul = CuentaAzul + 1;
                    CuentaTextFieldAzul = String.valueOf(CuentaAzul);
                    ATextFields.setCuentaTextFieldAzul(CuentaTextFieldAzul);
                }
            }
        });
        //adds
        T3.add(RLabel1);
        T3.add(RLabel2);
        T3.add(RAzul);
        T3.add(RRojo);
        T3.add(JTFAzul);
        T3.add(JTFRojo);
        T3.repaint();
    }
//    @Override
    public void run(){       
        HiloColoresGG CColores = new HiloColoresGG(RLabel1, RLabel2);
        CColores.start();
    }
}