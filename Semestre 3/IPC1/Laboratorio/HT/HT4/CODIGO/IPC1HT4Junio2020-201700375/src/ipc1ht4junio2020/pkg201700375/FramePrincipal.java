/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipc1ht4junio2020.pkg201700375;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author C55D-B5319-PC
 */
public class FramePrincipal {
    ListaCircular ListaCircular = new ListaCircular();
    int IDGrafico;
    String ValorGrafico;
    public FramePrincipal() {
        //Inicializador del Juego
        JFrame HT4 = new JFrame("Hoja De Trabajo 4");
        HT4.setLocationRelativeTo(null);
        HT4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HT4.getContentPane();
        HT4.setLayout(null);
        HT4.pack();
        HT4.setBounds(150, 10, 600, 400);
        HT4.setResizable(false);
        HT4.setVisible(true);
        //ID
        JLabel RID = new JLabel("ID:");
        RID.setBounds(10, 10, 20, 15);
        RID.setVisible(true);
        RID.setFont(RID.getFont().deriveFont(10.0f));
        JTextField JTFID = new JTextField();
        JTFID.setBounds(70, 10, 120, 15);
        JTFID.setVisible(true);
        JTFID.setFont(JTFID.getFont().deriveFont(10.0f));
        JTFID.setEditable(true);
        //Valor
        JLabel RValor = new JLabel("Valor:");
        RValor.setBounds(10, 35, 50, 15);
        RValor.setVisible(true);
        RValor.setFont(RValor.getFont().deriveFont(10.0f));
        JTextField JTFValor = new JTextField();
        JTFValor.setBounds(70, 35, 120, 15);
        JTFValor.setVisible(true);
        JTFValor.setFont(JTFValor.getFont().deriveFont(10.0f));
        JTFValor.setEditable(true);
        
        //JTextAreaGraphViz
        JTextArea JTACodigo = new JTextArea();
        JTACodigo.setBounds(200, 5, 360, 360);
        JTACodigo.setVisible(true);
        
        //ButtonAgregar
        JButton BAgregar = new JButton("Agregar");
        BAgregar.setBounds(50, 70, 80, 15);
        BAgregar.setVisible(true);
        BAgregar.setFont(BAgregar.getFont().deriveFont(10.0f));
        BAgregar.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             IDGrafico = Integer.parseInt(JTFID.getText());
             ValorGrafico = JTFValor.getText();
             ListaCircular.AgregarAlFinal(IDGrafico, ValorGrafico);
             ListaCircular.Listar();
             JTACodigo.setText(ListaCircular.GenerarCodigoGraphViz());
         }
        });
        //ButtonEditar
        JButton BEditar = new JButton("Editar");
        BEditar.setBounds(50, 95, 80, 15);
        BEditar.setVisible(true);
        BEditar.setFont(BEditar.getFont().deriveFont(10.0f));
        BEditar.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             IDGrafico = Integer.parseInt(JTFID.getText());
             ValorGrafico = JTFValor.getText();
             ListaCircular.Editar(IDGrafico, ValorGrafico);
             ListaCircular.Listar();
             JTACodigo.setText(ListaCircular.GenerarCodigoGraphViz());
         }
        });
        
        //adds
        HT4.add(RID);
        HT4.add(JTFID);
        HT4.add(RValor);
        HT4.add(JTFValor);
        HT4.add(BAgregar);
        HT4.add(BEditar);
        HT4.add(JTACodigo);
        HT4.repaint();
    }
    
}
