/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipc1practica2junio2020.pkg201700375;
import static ipc1practica2junio2020.pkg201700375.MenuPrincipal.MatrizJugadores;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author C55D-B5319-PC
 */
public class JuegoReal implements KeyListener {
    static JLabel MatrizVisualTablero[][] = new JLabel[6][4];
    static int FilaN=5, ColN=1, MilisCaidaObjetos=1000;//1000 Vel1, 800 Vel2, 600 Vel3, 400 Vel4, 200 Vel5
    static int FilaNM=0, ColNM=0;
    static int FilaAster = 0, ColAster=0;
    static int FilaCora = 0, ColCora=0;
    static int FilaRayo = 0, ColRayo =0;
    static int FilaCaracol =0, ColCaracol=0;
    static int FilaOjo = 0, ColOjo=0;
    //DecalrarHilos
    HiloMisil Misil;
    HiloCorazon Corazon;
    HiloRayo Rayo;
    HiloCaracol Caracol;
    HiloOjo Ojo;
    HiloAsteroide Asteroide;
    HiloActualizarTextFields ActualizarJTF;
    //DeclararTimers
    Timer TimerCorazon;
    Timer TimerRayo;
    Timer TimerCaracol;
    Timer TimerOjo;
    Timer TimerAsteroide;
    Timer TimerInvisible;
    Timer Tiempo;
    //Variables TextFields
    static int CVidas=3, CPoder=0, CVel=1, CPunteo=0, CTiempo=240,CInvisible=6;
    static String TextoVidas, TextoPoder, TextoVel, TextoPunteo, TextoTiempo;
    private int IndexMatrizJugadores;
    static JFrame JR;
    
    public JuegoReal(int IndexMatrizJugadores) throws HeadlessException {
        this.IndexMatrizJugadores = IndexMatrizJugadores;
        //Inicializador del Juego
        JR = new JFrame("Juego");
        JR.setLocationRelativeTo(null);
        JR.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JR.getContentPane();
        JR.setLayout(null);
        JR.pack();
        JR.setBounds(150, 10, 680, 700);
        JR.setResizable(false);
        JR.setVisible(true);
        //JPanelEstadisticas
        JPanel PanelE = new JPanel();
        PanelE.setLayout(null);
        PanelE.setVisible(true);
        PanelE.setBackground(Color.GRAY);
        PanelE.setBounds(500, 10, 150, 650);
        //JTextField
        JTextField JTFNombres = new JTextField(String.valueOf(MatrizJugadores[IndexMatrizJugadores][0]));
        JTFNombres.setBounds(5, 5, 140, 50);
        JTFNombres.setVisible(true);
        JTFNombres.setFont(JTFNombres.getFont().deriveFont(14.0f));
        JTFNombres.setEditable(false);
        JTFNombres.setFocusable(false);
        PanelE.add(JTFNombres);
        //Vidas
        JLabel RVidas = new JLabel("Vidas:");
        RVidas.setBounds(5, 60, 40, 15);
        RVidas.setVisible(true);
        RVidas.setFont(RVidas.getFont().deriveFont(10.0f));
        PanelE.add(RVidas);
        JTextField JTFVidas = new JTextField(String.valueOf(CVidas));
        JTFVidas.setBounds(15, 80, 120, 50);
        JTFVidas.setVisible(true);
        JTFVidas.setFont(JTFVidas.getFont().deriveFont(14.0f));
        JTFVidas.setEditable(false);
        JTFVidas.setFocusable(false);
        PanelE.add(JTFVidas);
        //Poder
        JLabel RPoder = new JLabel("Poder:");
        RPoder.setBounds(5, 135, 40, 15);
        RPoder.setVisible(true);
        RPoder.setFont(RPoder.getFont().deriveFont(10.0f));
        PanelE.add(RPoder);
        JTextField JTFPoder = new JTextField(String.valueOf(CPoder));
        JTFPoder.setBounds(15, 155, 120, 50);
        JTFPoder.setVisible(true);
        JTFPoder.setFont(JTFPoder.getFont().deriveFont(14.0f));
        JTFPoder.setEditable(false);
        JTFPoder.setFocusable(false);
        PanelE.add(JTFPoder);
        //Velocidad
        JLabel RVel = new JLabel("Velocidad:");
        RVel.setBounds(5, 210, 150, 15);
        RVel.setVisible(true);
        RVel.setFont(RVel.getFont().deriveFont(10.0f));
        PanelE.add(RVel);
        JTextField JTFVel = new JTextField(String.valueOf(CVel));
        JTFVel.setBounds(15, 230, 120, 50);
        JTFVel.setVisible(true);
        JTFVel.setFont(JTFVel.getFont().deriveFont(14.0f));
        JTFVel.setEditable(false);
        JTFVel.setFocusable(false);
        PanelE.add(JTFVel);
        //Puntos
        JLabel RPunt = new JLabel("Puntos:");
        RPunt.setBounds(5, 285, 150, 15);
        RPunt.setVisible(true);
        RPunt.setFont(RPunt.getFont().deriveFont(10.0f));
        PanelE.add(RPunt);
        JTextField JTFPunt = new JTextField(String.valueOf(CPunteo));
        JTFPunt.setBounds(15, 310, 120, 50);
        JTFPunt.setVisible(true);
        JTFPunt.setFont(JTFPunt.getFont().deriveFont(14.0f));
        JTFPunt.setEditable(false);
        JTFPunt.setFocusable(false);
        PanelE.add(JTFPunt);
        //TiempoRes
        JLabel RT= new JLabel("Tiempo Restante:");
        RT.setBounds(5, 365, 150, 15);
        RT.setVisible(true);
        RT.setFont(RT.getFont().deriveFont(10.0f));
        PanelE.add(RT);
        JTextField JTFT = new JTextField(String.valueOf(CTiempo));
        JTFT.setBounds(15, 385, 120, 50);
        JTFT.setVisible(true);
        JTFT.setFont(JTFT.getFont().deriveFont(14.0f));
        JTFT.setEditable(false);
        JTFT.setFocusable(false);
        PanelE.add(JTFT);
        
        //JPanelTablero
        JPanel PanelT = new JPanel();
        PanelT.setLayout(null);
        PanelT.setVisible(true);
        PanelT.setBackground(Color.gray);
        PanelT.setBounds(10, 10, 470, 650);
        //JLabels, JLabels Everywhere
        int X=0,Y=0;
        for(int i=0;i<6;i++){
            for(int j=0;j<4;j++){
                //              JLabelX,Y
                MatrizVisualTablero[i][j] = new JLabel();
                MatrizVisualTablero[i][j].setBounds(X, Y, 100, 100);
                MatrizVisualTablero[i][j].setVisible(true);
                MatrizVisualTablero[i][j].setFont(RT.getFont().deriveFont(10.0f));
                MatrizVisualTablero[i][j].setOpaque(true);
                MatrizVisualTablero[i][j].setBackground(Color.white);
                PanelT.add(MatrizVisualTablero[i][j]);
                X=X+100;
            }
            X=0;
            Y=Y+100;
        }
        //PrimeraAparicion Nave
        ImageIcon IN = new ImageIcon(new ImageIcon("src/Assets/NaveNormal.png").getImage().getScaledInstance(MatrizVisualTablero[FilaN][ColN].getWidth(), MatrizVisualTablero[FilaN][ColN].getHeight(), Image.SCALE_DEFAULT));
        MatrizVisualTablero[FilaN][ColN].setIcon(IN);
        MatrizVisualTablero[FilaN][ColN].repaint();
        JR.addKeyListener(this);
        ActualizarJTF = new HiloActualizarTextFields(JTFVidas, JTFPoder, JTFVel, JTFPunt, JTFT, TextoVidas, TextoPoder, TextoVel, TextoPunteo, TextoTiempo);
        
        //CrearTimers
        TimerCorazon = new Timer(14000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                int ColCora;
                Random PosCora = new Random();
                ColCora = PosCora.nextInt(4);
                Corazon = new HiloCorazon(0, ColCora , MilisCaidaObjetos);
                Corazon.start();
                System.out.println(Thread.currentThread().getName());
            }
            
        });
        TimerRayo= new Timer(10000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                int ColRayo;
                Random PosRayo = new Random();
                ColRayo = PosRayo.nextInt(4);
                Rayo = new HiloRayo(0, ColRayo , MilisCaidaObjetos);
                Rayo.start();
                System.out.println(Thread.currentThread().getName());
            }
            
        });
        TimerCaracol = new Timer(15000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                int ColCaracol;
                Random PosCaracol = new Random();
                ColCaracol = PosCaracol.nextInt(4);
                Caracol = new HiloCaracol(0, ColCaracol , MilisCaidaObjetos);
                Caracol.start();
                System.out.println(Thread.currentThread().getName());
            }
            
        });
        TimerOjo = new Timer(10000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                int ColOjo;
                Random PosOjo = new Random();
                ColOjo = PosOjo.nextInt(4);
                Ojo = new HiloOjo(0, ColOjo , MilisCaidaObjetos);
                Ojo.start();
                System.out.println(Thread.currentThread().getName());
            }
            
        });
        TimerAsteroide = new Timer(2000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                int ColAsteroide;
                Random PosAsteroide = new Random();
                ColAsteroide = PosAsteroide.nextInt(4);
                Asteroide = new HiloAsteroide(0, ColAsteroide , MilisCaidaObjetos);
                Asteroide.start();
                System.out.println(Thread.currentThread().getName());
            }
            
        });
        //TimerInvisible
        TimerInvisible = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(CInvisible > 0){
//                    Invisible = true;
                    CInvisible = CInvisible - 1;
                    ImageIcon INI = new ImageIcon(new ImageIcon("src/Assets/NaveInvisible.png").getImage().getScaledInstance(MatrizVisualTablero[FilaN][ColN].getWidth(), MatrizVisualTablero[FilaN][ColN].getHeight(), Image.SCALE_DEFAULT));
                    MatrizVisualTablero[FilaN][ColN].setIcon(INI);
                } else {
                    ImageIcon INI = new ImageIcon(new ImageIcon("src/Assets/NaveNormal.png").getImage().getScaledInstance(MatrizVisualTablero[FilaN][ColN].getWidth(), MatrizVisualTablero[FilaN][ColN].getHeight(), Image.SCALE_DEFAULT));
                    MatrizVisualTablero[FilaN][ColN].setIcon(INI);
//                    Invisible = false;
                }
            }
            
        });
        TimerInvisible.setRepeats(false);
        //JuegoEnSí
//        do{
//            //Timers que Activan los Hilos cada Elemento
//        }while(VidasJugador > 0 || TiempoRestante > 0);
        //Tablero
        JR.add(PanelT);
        JR.add(PanelE);
        JR.repaint();
        EmpezarJuego();
    }
    public void EmpezarJuego(){
        //EmpezarTimersE HilosEsenciales
        //Hilos
        ActualizarJTF.start();
        //Timers
        TimerCorazon.start();
        TimerRayo.start();
        TimerCaracol.start();
        TimerOjo.start();
        TimerAsteroide.start();
        //TimerGeneral
        Tiempo = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                //AvanzarSegundos
                CTiempo = CTiempo - 1;
                //ObtenerStringVariables
                TextoVidas = String.valueOf(CVidas);
                TextoPoder = String.valueOf(CPoder);
                TextoVel = String.valueOf(CVel);
                TextoPunteo = String.valueOf(CPunteo);
                TextoTiempo = String.valueOf(CTiempo);
                //ActualizarTextFields
                ActualizarJTF.setTextFieldVidas(TextoVidas);
                ActualizarJTF.setTextFieldPoder(TextoPoder);
                ActualizarJTF.setTextFieldVel(TextoVel);
                ActualizarJTF.setTextFieldPunteo(TextoPunteo);
                ActualizarJTF.setTextFieldTiempo(TextoTiempo);
                System.out.println(Thread.currentThread().getName());
                if(CVidas == 0){
                    JFrame g = new JFrame();
                    JOptionPane.showMessageDialog(g, "Perdiste, F por tí :(");
                    TimerAsteroide.stop();
                    TimerCaracol.stop();
                    TimerCorazon.stop();
                    TimerRayo.stop();
                    TimerOjo.stop();
                    Tiempo.stop();
                    MenuPrincipal M = new MenuPrincipal();
                    JR.dispose();
                } else if(CTiempo == 0){
                    JFrame g = new JFrame();
                    JOptionPane.showMessageDialog(g, "Ganáste! :O");
                    TimerAsteroide.stop();
                    TimerCaracol.stop();
                    TimerCorazon.stop();
                    TimerRayo.stop();
                    TimerOjo.stop();
                    Tiempo.stop();
                    MenuPrincipal M = new MenuPrincipal();
                    JR.dispose();
                }
            }
        });
        Tiempo.start();
        Tiempo.setRepeats(true);
        
        
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_RIGHT:
                  MatrizVisualTablero[FilaN][ColN].setIcon(null);
                  MatrizVisualTablero[FilaN][ColN].repaint();
                  if(ColN<3){
                      ColN = ColN+1;
                  }
//                  if(Invisible = true){
//                      ImageIcon IN = new ImageIcon(new ImageIcon("src/Assets/NaveInvisible.png").getImage().getScaledInstance(MatrizVisualTablero[FilaN][ColN].getWidth(), MatrizVisualTablero[FilaN][ColN].getHeight(), Image.SCALE_DEFAULT));
//                      MatrizVisualTablero[FilaN][ColN].setIcon(IN);
//                  } else if(Invisible = false){
                      ImageIcon IN = new ImageIcon(new ImageIcon("src/Assets/NaveNormal.png").getImage().getScaledInstance(MatrizVisualTablero[FilaN][ColN].getWidth(), MatrizVisualTablero[FilaN][ColN].getHeight(), Image.SCALE_DEFAULT));
                      MatrizVisualTablero[FilaN][ColN].setIcon(IN);
                      MatrizVisualTablero[FilaN][ColN].repaint();
//                  }
                break;
                
            case KeyEvent.VK_LEFT:
                MatrizVisualTablero[FilaN][ColN].setIcon(null);
                MatrizVisualTablero[FilaN][ColN].repaint();
                  if(ColN>0){
                      ColN = ColN-1;
                  }
//                  if(Invisible = true){
//                      ImageIcon IN = new ImageIcon(new ImageIcon("src/Assets/NaveInvisible.png").getImage().getScaledInstance(MatrizVisualTablero[FilaN][ColN].getWidth(), MatrizVisualTablero[FilaN][ColN].getHeight(), Image.SCALE_DEFAULT));
//                      MatrizVisualTablero[FilaN][ColN].setIcon(IN);
//                  } else if(Invisible = false) {
                      ImageIcon INI = new ImageIcon(new ImageIcon("src/Assets/NaveNormal.png").getImage().getScaledInstance(MatrizVisualTablero[FilaN][ColN].getWidth(), MatrizVisualTablero[FilaN][ColN].getHeight(), Image.SCALE_DEFAULT));
                      MatrizVisualTablero[FilaN][ColN].setIcon(INI);
                      MatrizVisualTablero[FilaN][ColN].repaint();
//                  }
                break;
            case KeyEvent.VK_DOWN:
                if(CPoder > 0){
                    CPoder = CPoder - 1;
                    CInvisible = 6;
                    TimerInvisible.start();
                }
                break;
            case KeyEvent.VK_SPACE:
                Misil = new HiloMisil(FilaN, ColN);
                Misil.start();
                break;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
            switch(e.getKeyCode()){
            case KeyEvent.VK_RIGHT:
                break;
                
            case KeyEvent.VK_LEFT:
                break;
            }
    }

    /**
     * @return the IndexMatrizJugadores
     */
    public int getIndexMatrizJugadores() {
        return IndexMatrizJugadores;
    }

    /**
     * @param IndexMatrizJugadores the IndexMatrizJugadores to set
     */
    public void setIndexMatrizJugadores(int IndexMatrizJugadores) {
        this.IndexMatrizJugadores = IndexMatrizJugadores;
    }

    /**
     * @return the VidasJugador
     */
    public int getVidasJugador() {
        return CVidas;
    }

    /**
     * @param VidasJugador the VidasJugador to set
     */
    public void setVidasJugador(int VidasJugador) {
        this.CVidas = VidasJugador;
    }

    /**
     * @return the CantidadPoderJugador
     */
    public int getCantidadPoderJugador() {
        return CPoder;
    }

    /**
     * @param CantidadPoderJugador the CantidadPoderJugador to set
     */
    public void setCantidadPoderJugador(int CantidadPoderJugador) {
        this.CPoder = CantidadPoderJugador;
    }

    /**
     * @return the Velocidad
     */
    public int getVelocidad() {
        return CVel;
    }

    /**
     * @param Velocidad the Velocidad to set
     */
    public void setVelocidad(int Velocidad) {
        this.CVel = Velocidad;
    }

    /**
     * @return the Punteo
     */
    public int getPunteo() {
        return CPunteo;
    }

    /**
     * @param Punteo the Punteo to set
     */
    public void setPunteo(int Punteo) {
        this.CPunteo = Punteo;
    }

    /**
     * @return the TiempoRestante
     */
    public int getTiempoRestante() {
        return CTiempo;
    }

    /**
     * @param TiempoRestante the TiempoRestante to set
     */
    public void setTiempoRestante(int TiempoRestante) {
        this.CTiempo = TiempoRestante;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }
}
