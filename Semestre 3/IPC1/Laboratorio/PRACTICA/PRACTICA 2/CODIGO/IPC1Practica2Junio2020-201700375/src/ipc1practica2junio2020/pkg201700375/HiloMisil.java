/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipc1practica2junio2020.pkg201700375;
import static ipc1practica2junio2020.pkg201700375.JuegoReal.MatrizVisualTablero;
import static ipc1practica2junio2020.pkg201700375.JuegoReal.FilaNM;
import static ipc1practica2junio2020.pkg201700375.JuegoReal.ColNM;
//Asteroide
import static ipc1practica2junio2020.pkg201700375.JuegoReal.FilaAster;
import static ipc1practica2junio2020.pkg201700375.JuegoReal.ColAster;
//Corazon
import static ipc1practica2junio2020.pkg201700375.JuegoReal.FilaCora;
import static ipc1practica2junio2020.pkg201700375.JuegoReal.ColCora;
//Caracol
import static ipc1practica2junio2020.pkg201700375.JuegoReal.FilaCaracol;
import static ipc1practica2junio2020.pkg201700375.JuegoReal.ColCaracol;
//Rayo
import static ipc1practica2junio2020.pkg201700375.JuegoReal.FilaRayo;
import static ipc1practica2junio2020.pkg201700375.JuegoReal.ColRayo;
//Ojo
import static ipc1practica2junio2020.pkg201700375.JuegoReal.FilaOjo;
import static ipc1practica2junio2020.pkg201700375.JuegoReal.ColOjo;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
/**
 *
 * @author C55D-B5319-PC
 */
public class HiloMisil extends Thread{
    private int FilaIni;
    private int ColIni;
    private int FilaAct;
    private int FilaAnter;
    private int ColAnter;
    public HiloMisil(int FilaIni, int ColIni) {
        this.FilaIni = FilaIni;
        this.ColIni = ColIni;
        FilaAct = FilaIni-1;
        ImageIcon INI = new ImageIcon(new ImageIcon("src/Assets/Misil.png").getImage().getScaledInstance(MatrizVisualTablero[getFilaAct()][ColIni].getWidth(), MatrizVisualTablero[getFilaAct()][ColIni].getHeight(), Image.SCALE_DEFAULT));
        MatrizVisualTablero[FilaAct][ColIni].setIcon(INI);
    }

    @Override
    public void run() {
        while(getFilaAct() >= 0){
            ImageIcon INI = new ImageIcon(new ImageIcon("src/Assets/Misil.png").getImage().getScaledInstance(MatrizVisualTablero[getFilaAct()][getColIni()].getWidth(), MatrizVisualTablero[getFilaAct()][getColIni()].getHeight(), Image.SCALE_DEFAULT));
            MatrizVisualTablero[FilaAct][ColIni].setIcon(INI);
            FilaAnter = FilaAct;
            FilaNM = FilaAnter;
            ColNM = ColIni;
            FilaAct = FilaAct - 1;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloMisil.class.getName()).log(Level.SEVERE, null, ex);
            }
            MatrizVisualTablero[getFilaAnter()][getColIni()].setIcon(null);
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
}
