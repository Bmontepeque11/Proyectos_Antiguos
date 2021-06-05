/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipc1practica2junio2020.pkg201700375;
import static ipc1practica2junio2020.pkg201700375.JuegoReal.MatrizVisualTablero;
import static ipc1practica2junio2020.pkg201700375.JuegoReal.FilaN;
import static ipc1practica2junio2020.pkg201700375.JuegoReal.ColN;
import static ipc1practica2junio2020.pkg201700375.JuegoReal.CVidas;
import static ipc1practica2junio2020.pkg201700375.JuegoReal.FilaNM;
import static ipc1practica2junio2020.pkg201700375.JuegoReal.ColNM;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
/**
 *
 * @author C55D-B5319-PC
 */
public class HiloCorazon extends Thread{
    private int FilaIni;
    private int ColIni;
    private int FilaAct;
    private int FilaAnter;
    private int ColAnter;
    private int MilisegundosTimer;

    public HiloCorazon(int FilaIni, int ColIni, int MilisegundosTimer) {
        this.FilaIni = FilaIni;
        this.ColIni = ColIni;
        this.MilisegundosTimer = MilisegundosTimer;
        FilaAct = FilaIni;
        ImageIcon INI = new ImageIcon(new ImageIcon("src/Assets/Corazon.png").getImage().getScaledInstance(MatrizVisualTablero[FilaAct][ColIni].getWidth(), MatrizVisualTablero[FilaAct][ColIni].getHeight(), Image.SCALE_DEFAULT));
        MatrizVisualTablero[FilaAct][ColIni].setIcon(INI);
    }

    @Override
    public void run() {
        while(FilaAct <=5){
            ImageIcon INI = new ImageIcon(new ImageIcon("src/Assets/Corazon.png").getImage().getScaledInstance(MatrizVisualTablero[FilaAct][ColIni].getWidth(), MatrizVisualTablero[FilaAct][ColIni].getHeight(), Image.SCALE_DEFAULT));
            MatrizVisualTablero[FilaAct][ColIni].setIcon(INI);
            FilaAnter = FilaAct;
            FilaAct = FilaAct + 1;
            if(FilaNM== FilaAnter && ColNM == ColIni){
                this.stop();
            } else if(FilaN==FilaAnter && ColN == ColIni){
                if(CVidas < 3){
                   CVidas = CVidas + 1; 
                } else {
                    CVidas = CVidas;
                }
                
            }
            try {
                Thread.sleep(MilisegundosTimer);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloMisil.class.getName()).log(Level.SEVERE, null, ex);
            }
            MatrizVisualTablero[FilaAnter][ColIni].setIcon(null);
        }
        System.out.println(Thread.currentThread().getName());
    }
    
    /**
     * @return the FilaIni
     */
    public int getFilaIni() {
        return FilaIni;
    }

    /**
     * @param FilaIni the FilaIni to set
     */
    public void setFilaIni(int FilaIni) {
        this.FilaIni = FilaIni;
    }

    /**
     * @return the ColIni
     */
    public int getColIni() {
        return ColIni;
    }

    /**
     * @param ColIni the ColIni to set
     */
    public void setColIni(int ColIni) {
        this.ColIni = ColIni;
    }

    /**
     * @return the FilaAct
     */
    public int getFilaAct() {
        return FilaAct;
    }

    /**
     * @param FilaAct the FilaAct to set
     */
    public void setFilaAct(int FilaAct) {
        this.FilaAct = FilaAct;
    }

    /**
     * @return the FilaAnter
     */
    public int getFilaAnter() {
        return FilaAnter;
    }

    /**
     * @param FilaAnter the FilaAnter to set
     */
    public void setFilaAnter(int FilaAnter) {
        this.FilaAnter = FilaAnter;
    }

    /**
     * @return the ColAnter
     */
    public int getColAnter() {
        return ColAnter;
    }

    /**
     * @param ColAnter the ColAnter to set
     */
    public void setColAnter(int ColAnter) {
        this.ColAnter = ColAnter;
    }

    /**
     * @return the MilisegundosTimer
     */
    public int getMilisegundosTimer() {
        return MilisegundosTimer;
    }

    /**
     * @param MilisegundosTimer the MilisegundosTimer to set
     */
    public void setMilisegundosTimer(int MilisegundosTimer) {
        this.MilisegundosTimer = MilisegundosTimer;
    }
    
}
