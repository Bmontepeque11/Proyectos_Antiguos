/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipc1proyecto2junio2020.pkg201700375;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import static ipc1proyecto2junio2020.pkg201700375.FrameTableroPrincipal.LD;
/**
 *
 * @author C55D-B5319-PC
 */
public class FrameListaD {
    static JFrame FrameLD;
    public FrameListaD() {
        //Inicializador del MenuPrincipal
        FrameLD = new JFrame("Lista Doble");
        FrameLD.setLocationRelativeTo(null);
        FrameLD.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameLD.getContentPane();
        FrameLD.setLayout(null);
        FrameLD.pack();
        FrameLD.setBounds(150, 20, 370, 520);
        FrameLD.setResizable(false);
        FrameLD.setVisible(true);
        
        //JTextAreaGraphViz
        JTextArea JTACodigo = new JTextArea();
        JTACodigo.setBounds(5, 5, 350, 350);
        JTACodigo.setVisible(true);
        JTACodigo.setText(LD.GenerarCodigoGraphViz());
        
        //JScrollPane
        JScrollPane Scroll = new JScrollPane(JTACodigo);
        Scroll.setBounds(5, 5, 350, 350);
        Scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        Scroll.setVisible(true);
        
        //ID
        JLabel RID = new JLabel("ID:");
        RID.setBounds(5, 365, 20, 15);
        RID.setVisible(true);
        RID.setFont(RID.getFont().deriveFont(10.0f));
        JTextField JTFID = new JTextField();
        JTFID.setBounds(60, 365, 280, 15);
        JTFID.setVisible(true);
        JTFID.setFont(JTFID.getFont().deriveFont(10.0f));
        
        //Valor
        JLabel RValor = new JLabel("Valor:");
        RValor.setBounds(5, 385, 60, 15);
        RValor.setVisible(true);
        RValor.setFont(RValor.getFont().deriveFont(10.0f));
        JTextField JTFValor = new JTextField();
        JTFValor.setBounds(60, 385, 280, 15);
        JTFValor.setVisible(true);
        JTFValor.setFont(JTFValor.getFont().deriveFont(10.0f));
        
        //Color
        JLabel RColor = new JLabel("Color:");
        RColor.setBounds(5, 405, 60, 15);
        RColor.setVisible(true);
        RColor.setFont(RColor.getFont().deriveFont(10.0f));
        JTextField JTFColor = new JTextField();
        JTFColor.setBounds(60, 405, 280, 15);
        JTFColor.setVisible(true);
        JTFColor.setFont(JTFColor.getFont().deriveFont(10.0f));
        
        //ButtonAgregar
        JButton BAgregar = new JButton("Agregar");
        BAgregar.setBounds(5, 425, 350, 15);
        BAgregar.setVisible(true);
        BAgregar.setFont(BAgregar.getFont().deriveFont(10.0f));
        BAgregar.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
                LD.AgregarAlFinal(Integer.parseInt(JTFID.getText()), 0, 0, Integer.parseInt(JTFValor.getText()), JTFColor.getText());
                JTACodigo.setText(LD.GenerarCodigoGraphViz());
         }
        });
        
        //ButtonEliminar
        JButton BEliminar = new JButton("Eliminar");
        BEliminar.setBounds(5, 445, 350, 15);
        BEliminar.setVisible(true);
        BEliminar.setFont(BEliminar.getFont().deriveFont(10.0f));
        BEliminar.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             LD.EliminarPorPosicion(Integer.parseInt(JTFID.getText()));
             JTACodigo.setText(LD.GenerarCodigoGraphViz());
         }
        });
        
        //ButtonVolver
        JButton BVolver = new JButton("Volver");
        BVolver.setBounds(5, 465, 350, 15);
        BVolver.setVisible(true);
        BVolver.setFont(BVolver.getFont().deriveFont(10.0f));
        BVolver.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             FrameLD.dispose();
         }
        });
        
        //adds
        FrameLD.add(Scroll);
        FrameLD.add(BAgregar);
        FrameLD.add(BEliminar);
        FrameLD.add(BVolver);
        FrameLD.add(RID);
        FrameLD.add(JTFID);
        FrameLD.add(RValor);
        FrameLD.add(JTFValor);
        FrameLD.add(RColor);
        FrameLD.add(JTFColor);
        FrameLD.repaint();
    }
    
}
