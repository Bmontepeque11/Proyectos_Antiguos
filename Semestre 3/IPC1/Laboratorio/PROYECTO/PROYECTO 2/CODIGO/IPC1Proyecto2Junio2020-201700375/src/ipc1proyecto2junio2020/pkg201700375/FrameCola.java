/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipc1proyecto2junio2020.pkg201700375;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import static ipc1proyecto2junio2020.pkg201700375.FrameTableroPrincipal.C;
/**
 *
 * @author C55D-B5319-PC
 */
public class FrameCola {
    static JFrame FrameCola;

    public FrameCola() {
        //Inicializador del MenuPrincipal
        FrameCola = new JFrame("Cola");
        FrameCola.setLocationRelativeTo(null);
        FrameCola.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameCola.getContentPane();
        FrameCola.setLayout(null);
        FrameCola.pack();
        FrameCola.setBounds(150, 20, 370, 520);
        FrameCola.setResizable(false);
        FrameCola.setVisible(true);
        
        //JTextAreaGraphViz
        JTextArea JTACodigo = new JTextArea();
        JTACodigo.setBounds(5, 5, 350, 350);
        JTACodigo.setVisible(true);
        JTACodigo.setText(C.GenerarCodigoGraphViz());
        
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
        JButton BPush = new JButton("Push");
        BPush.setBounds(5, 425, 350, 15);
        BPush.setVisible(true);
        BPush.setFont(BPush.getFont().deriveFont(10.0f));
        BPush.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
                C.push(Integer.parseInt(JTFID.getText()), 0, 0, Integer.parseInt(JTFValor.getText()), JTFColor.getText());
                JTACodigo.setText(C.GenerarCodigoGraphViz());
         }
        });
        
        //ButtonEliminar
        JButton BPop = new JButton("Pop");
        BPop.setBounds(5, 445, 350, 15);
        BPop.setVisible(true);
        BPop.setFont(BPop.getFont().deriveFont(10.0f));
        BPop.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             C.pop();
             JTACodigo.setText(C.GenerarCodigoGraphViz());
         }
        });
        
        //ButtonVolver
        JButton BVolver = new JButton("Volver");
        BVolver.setBounds(5, 465, 350, 15);
        BVolver.setVisible(true);
        BVolver.setFont(BVolver.getFont().deriveFont(10.0f));
        BVolver.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             FrameCola.dispose();
         }
        });
        
        //adds
        FrameCola.add(Scroll);
        FrameCola.add(BPush);
        FrameCola.add(BPop);
        FrameCola.add(BVolver);
        FrameCola.add(RID);
        FrameCola.add(JTFID);
        FrameCola.add(RValor);
        FrameCola.add(JTFValor);
        FrameCola.add(RColor);
        FrameCola.add(JTFColor);
        FrameCola.repaint();
    }
    
}
