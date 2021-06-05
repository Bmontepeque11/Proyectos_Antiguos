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
public class HiloColoresGG extends Thread {
    JLabel RLabel1, RLabel2;
    private Timer t;
    private int Color1 = 0;//0 = Rojo, 1 = Azul;
    private int Color2 = 1;//0 = Rojo, 1 = Azul;
    
    public HiloColoresGG(JLabel RLabel1, JLabel RLabel2) {
                this.RLabel1 = RLabel1;
                this.RLabel2 = RLabel2;
    }
    
    @Override
    public void run(){
        for (;;){
            if(RLabel1.getBackground() == Color.red){//Si Label 1 = Rojo
                 RLabel1.setBackground(Color.blue);//Label 1 = Azul
                 RLabel2.setBackground(Color.red);//Label 2 = Rojo
            }else if(RLabel1.getBackground()==Color.blue){//Si Label 1 = Azul
                RLabel1.setBackground(Color.red);;//Label 1 = Rojo
                RLabel2.setBackground(Color.blue);//Label 2 = Azul
            }
             System.out.println(Thread.currentThread().getName());
 
            try {
             Thread.sleep(1000);
            } catch (InterruptedException ex) {
             Logger.getLogger(HiloColoresGG.class.getName()).log(Level.SEVERE, null, ex);
            }
        }         
    
    }
    
    /**
     * @return the t
     */
    public Timer getT() {
        return t;
    }

    /**
     * @param t the t to set
     */
    public void setT(Timer t) {
        this.t = t;
    }

    /**
     * @return the Color1
     */
    public int getColor1() {
        return Color1;
    }

    /**
     * @param Color1 the Color1 to set
     */
    public void setColor1(int Color1) {
        this.Color1 = Color1;
    }

    /**
     * @return the Color2
     */
    public int getColor2() {
        return Color2;
    }

    /**
     * @param Color2 the Color2 to set
     */
    public void setColor2(int Color2) {
        this.Color2 = Color2;
    }
}
