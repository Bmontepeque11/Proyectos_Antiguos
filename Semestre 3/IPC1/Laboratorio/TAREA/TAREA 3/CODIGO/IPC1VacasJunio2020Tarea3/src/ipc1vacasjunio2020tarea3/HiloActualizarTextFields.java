/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipc1vacasjunio2020tarea3;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
/**
 *
 * @author C55D-B5319-PC
 */
public class HiloActualizarTextFields extends Thread {

    JTextField JTFAzul, JTFRojo;
    private String CuentaTextFieldRojo;
    private String CuentaTextFieldAzul;
    public HiloActualizarTextFields(JTextField JTFAzul, JTextField JTFRojo, String CuentaTextFieldRojo, String CuentaTextFieldAzul) {
        this.JTFAzul = JTFAzul;
        this.JTFRojo = JTFRojo;
        this.CuentaTextFieldAzul = CuentaTextFieldAzul;
        this.CuentaTextFieldRojo = CuentaTextFieldRojo;
    }
    @Override
    public void run(){
        for(;;){
            JTFAzul.setText(getCuentaTextFieldAzul());
            JTFRojo.setText(getCuentaTextFieldRojo());
            System.out.println(Thread.currentThread().getName());
            try {
            Thread.sleep(1000);
            } catch (InterruptedException ex) {
            Logger.getLogger(HiloActualizarTextFields.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @return the CuentaTextFieldRojo
     */
    public String getCuentaTextFieldRojo() {
        return CuentaTextFieldRojo;
    }

    /**
     * @param CuentaTextFieldRojo the CuentaTextFieldRojo to set
     */
    public void setCuentaTextFieldRojo(String CuentaTextFieldRojo) {
        this.CuentaTextFieldRojo = CuentaTextFieldRojo;
    }

    /**
     * @return the CuentaTextFieldAzul
     */
    public String getCuentaTextFieldAzul() {
        return CuentaTextFieldAzul;
    }

    /**
     * @param CuentaTextFieldAzul the CuentaTextFieldAzul to set
     */
    public void setCuentaTextFieldAzul(String CuentaTextFieldAzul) {
        this.CuentaTextFieldAzul = CuentaTextFieldAzul;
    }
}
