/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipc1practica2junio2020.pkg201700375;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
/**
 *
 * @author C55D-B5319-PC
 */
public class HiloActualizarTextFields extends Thread{

    /**
     * @return the JTFVidas
     */
    public JTextField getJTFVidas() {
        return JTFVidas;
    }

    /**
     * @param JTFVidas the JTFVidas to set
     */
    public void setJTFVidas(JTextField JTFVidas) {
        this.JTFVidas = JTFVidas;
    }

    /**
     * @return the JTFPoder
     */
    public JTextField getJTFPoder() {
        return JTFPoder;
    }

    /**
     * @param JTFPoder the JTFPoder to set
     */
    public void setJTFPoder(JTextField JTFPoder) {
        this.JTFPoder = JTFPoder;
    }

    /**
     * @return the JTFVel
     */
    public JTextField getJTFVel() {
        return JTFVel;
    }

    /**
     * @param JTFVel the JTFVel to set
     */
    public void setJTFVel(JTextField JTFVel) {
        this.JTFVel = JTFVel;
    }

    /**
     * @return the JTFPunteo
     */
    public JTextField getJTFPunteo() {
        return JTFPunteo;
    }

    /**
     * @param JTFPunteo the JTFPunteo to set
     */
    public void setJTFPunteo(JTextField JTFPunteo) {
        this.JTFPunteo = JTFPunteo;
    }

    /**
     * @return the JTFTiempo
     */
    public JTextField getJTFTiempo() {
        return JTFTiempo;
    }

    /**
     * @param JTFTiempo the JTFTiempo to set
     */
    public void setJTFTiempo(JTextField JTFTiempo) {
        this.JTFTiempo = JTFTiempo;
    }

    /**
     * @return the TextFieldVidas
     */
    public String getTextFieldVidas() {
        return TextFieldVidas;
    }

    /**
     * @param TextFieldVidas the TextFieldVidas to set
     */
    public void setTextFieldVidas(String TextFieldVidas) {
        this.TextFieldVidas = TextFieldVidas;
    }

    /**
     * @return the TextFieldPoder
     */
    public String getTextFieldPoder() {
        return TextFieldPoder;
    }

    /**
     * @param TextFieldPoder the TextFieldPoder to set
     */
    public void setTextFieldPoder(String TextFieldPoder) {
        this.TextFieldPoder = TextFieldPoder;
    }

    /**
     * @return the TextFieldVel
     */
    public String getTextFieldVel() {
        return TextFieldVel;
    }

    /**
     * @param TextFieldVel the TextFieldVel to set
     */
    public void setTextFieldVel(String TextFieldVel) {
        this.TextFieldVel = TextFieldVel;
    }

    /**
     * @return the TextFieldPunteo
     */
    public String getTextFieldPunteo() {
        return TextFieldPunteo;
    }

    /**
     * @param TextFieldPunteo the TextFieldPunteo to set
     */
    public void setTextFieldPunteo(String TextFieldPunteo) {
        this.TextFieldPunteo = TextFieldPunteo;
    }

    /**
     * @return the TextFieldTiempo
     */
    public String getTextFieldTiempo() {
        return TextFieldTiempo;
    }

    /**
     * @param TextFieldTiempo the TextFieldTiempo to set
     */
    public void setTextFieldTiempo(String TextFieldTiempo) {
        this.TextFieldTiempo = TextFieldTiempo;
    }
    private JTextField JTFVidas;
    private JTextField JTFPoder;
    private JTextField JTFVel;
    private JTextField JTFPunteo;
    private JTextField JTFTiempo;
    private String TextFieldVidas;
    private String TextFieldPoder;
    private String TextFieldVel;
    private String TextFieldPunteo;
    private String TextFieldTiempo;

    public HiloActualizarTextFields(JTextField JTFVidas, JTextField JTFPoder, JTextField JTFVel, JTextField JTFPunteo, JTextField JTFTiempo, String TextFieldVidas, String TextFieldPoder, String TextFieldVel, String TextFieldPunteo, String TextFieldTiempo) {
        this.JTFVidas = JTFVidas;
        this.JTFPoder = JTFPoder;
        this.JTFVel = JTFVel;
        this.JTFPunteo = JTFPunteo;
        this.JTFTiempo = JTFTiempo;
        this.TextFieldVidas = TextFieldVidas;
        this.TextFieldPoder = TextFieldPoder;
        this.TextFieldVel = TextFieldVel;
        this.TextFieldPunteo = TextFieldPunteo;
        this.TextFieldTiempo = TextFieldTiempo;
    }

    @Override
    public void run() {
        for(;;){
            JTFVidas.setText(TextFieldVidas);
            JTFPoder.setText(TextFieldPoder);
            JTFVel.setText(TextFieldVel);
            JTFPunteo.setText(TextFieldPunteo);
            JTFTiempo.setText(TextFieldTiempo);
            System.out.println(Thread.currentThread().getName());
            try {
            Thread.sleep(1000);
            } catch (InterruptedException ex) {
            Logger.getLogger(HiloActualizarTextFields.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
