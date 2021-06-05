/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import PartePOO.*;
import java.util.Properties;
import java.util.Scanner;
import javax.swing.table.DefaultTableModel;
import org.jdatepicker.impl.*;
/**
 *
 * @author C55D-B5319-PC
 */
public class ClasePrincipal extends JFrame {
    //Usuarios
    static Persona VectorPersona[] = new Persona[20];
    //UsuariosAsegurados
    static Persona VectorAseguradas[] = new Persona[20];
    //UsuariosNoAsegurados
    static Persona VectorNoAseguradas[] = new Persona[20];
    
    //CargaMasiva
    static SSTipo VectorSSTipo[] = new SSTipo [30];
    static SSModelo VectorSSModelo[] = new SSModelo [30];
    static SSMarca VectorSSMarca[] = new SSMarca [30];
    static SSLinea VectorSSLinea[] = new SSLinea [30];
    static SSUso VectorSSUso[] = new SSUso [30];
    static SSMecanica VectorSSMecanica[] = new SSMecanica [30];
    static SSRepuesto VectorSSRepuesto[] = new SSRepuesto [30];
    //MatrizTabla Solicitudes Seguro por Aprovar
    static int IndexMatrizSS=-1;
    static String MatrizSSPorAprovar [][] = new String [11][8];
    //PersonaLogueadaActual
    static String NombreSujetoLogueado;
    static String TelefonoSujetoLogueado;
    static String DPISujetoLogueado;
    static String AseguradoSujetoLogueado;
    static String MontoSujetoLogueado;
    static String CostoPrimaSujetoLogueado;
    static String DeducibleSujetoLogueado;
    static String TipoSujetoLogueado;
    //CrearModeloTabla
    static int IndexMatrizAR=-1;
    static Object ModeloTablaAgregarRepuestos[][] = new Object[30][3];
    
    public ClasePrincipal() throws HeadlessException {
       
    }
    
    //JFrames Principales
    public static void MenuPrincipal(){
        //Inicializador del MenuPrincipal
        JFrame Menu = new JFrame("MenuPrincipal");
        Menu.setLocationRelativeTo(null);
        Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Menu.getContentPane();
        Menu.setLayout(null);
        Menu.pack();
        Menu.setBounds(150, 100, 480, 280);
        Menu.setResizable(false);
        Menu.setVisible(true);
        //JLabels
        JLabel ISolicitarSeguro = new JLabel();
        ISolicitarSeguro.setBounds(20, 60, 130 , 130);
        ImageIcon ISS = new ImageIcon(new ImageIcon("src/Assets/Solicitar Seguro.png").getImage().getScaledInstance(ISolicitarSeguro.getWidth(), ISolicitarSeguro.getHeight(), Image.SCALE_DEFAULT));
        ISolicitarSeguro.setIcon(ISS);
        ISolicitarSeguro.setVisible(true);
        ISolicitarSeguro.setFont(ISolicitarSeguro.getFont().deriveFont(16.0f));
        JLabel IIniciarSesion = new JLabel();
        IIniciarSesion.setBounds(170, 60, 130 , 130);
        ImageIcon IIS = new ImageIcon(new ImageIcon("src/Assets/Login.jpg").getImage().getScaledInstance(IIniciarSesion.getWidth(), IIniciarSesion.getHeight(), Image.SCALE_DEFAULT));
        IIniciarSesion.setIcon(IIS);
        IIniciarSesion.setVisible(true);
        IIniciarSesion.setFont(IIniciarSesion.getFont().deriveFont(16.0f));
        JLabel ISalir = new JLabel();
        ISalir.setBounds(320, 60, 130 , 130);
        ImageIcon IS = new ImageIcon(new ImageIcon("src/Assets/Salir.png").getImage().getScaledInstance(ISalir.getWidth(), ISalir.getHeight(), Image.SCALE_DEFAULT));
        ISalir.setIcon(IS);
        ISalir.setVisible(true);
        ISalir.setFont(ISalir.getFont().deriveFont(16.0f));
        //JButtons
        JButton BCargarDatos = new JButton("CargarDatos");
        BCargarDatos.setBounds(10, 5, 100, 15);
        BCargarDatos.setVisible(true);
        BCargarDatos.setFont(BCargarDatos.getFont().deriveFont(10.0f));
        BCargarDatos.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             ClasePrincipal.CargarDatos();
             Menu.dispose();
         }
        });
        JButton BSolicitarSeguro = new JButton("Solicitar Seguro");
        BSolicitarSeguro.setBounds(20, 200, 130, 15);
        BSolicitarSeguro.setVisible(true);
        BSolicitarSeguro.setFont(BSolicitarSeguro.getFont().deriveFont(12.0f));
        BSolicitarSeguro.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             ClasePrincipal.SolicitudSeguro();
             Menu.dispose();
         }
        });
        JButton BIniciarSesion = new JButton("Iniciar Sesión");
        BIniciarSesion.setBounds(170, 200, 130, 15);
        BIniciarSesion.setVisible(true);
        BIniciarSesion.setFont(BIniciarSesion.getFont().deriveFont(12.0f));
        BIniciarSesion.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             ClasePrincipal.LogIn();
             Menu.dispose();
         }
        });
        JButton BSalir = new JButton("Salir");
        BSalir.setBounds(320, 200, 130, 15);
        BSalir.setVisible(true);
        BSalir.setFont(BSalir.getFont().deriveFont(12.0f));
        BSalir.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             System.exit(0);
         }
        });
        //adds
        Menu.add(ISolicitarSeguro);
        Menu.add(IIniciarSesion);
        Menu.add(ISalir);
        Menu.add(BCargarDatos);
        Menu.add(BSolicitarSeguro);
        Menu.add(BIniciarSesion);
        Menu.add(BSalir);
        Menu.repaint();
    }
    public static void SolicitudSeguro(){
        JFrame SSeguro = new JFrame("Solicitud Seguro");
        SSeguro.setLocationRelativeTo(null);
        SSeguro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SSeguro.getContentPane();
        SSeguro.setLayout(null);
        SSeguro.pack();
        SSeguro.setBounds(150, 100, 320, 450);
        SSeguro.setResizable(false);
        SSeguro.setVisible(true);
       
        //Jlabels
        JLabel RNombres = new JLabel("Nombres:");
        RNombres.setBounds(5, 5, 100, 15);
        RNombres.setVisible(true);
        RNombres.setFont(RNombres.getFont().deriveFont(10.0f));
        JLabel RApellidos = new JLabel("Apellidos:");
        RApellidos.setBounds(5, 30, 100, 15);
        RApellidos.setVisible(true);
        RApellidos.setFont(RApellidos.getFont().deriveFont(10.0f));
        JLabel RDPI = new JLabel("DPI:");
        RDPI.setBounds(5, 55, 100, 15);
        RDPI.setVisible(true);
        RDPI.setFont(RDPI.getFont().deriveFont(10.0f));
        JLabel RTel = new JLabel("Teléfono:");
        RTel.setBounds(5, 80, 100, 15);
        RTel.setVisible(true);
        RTel.setFont(RTel.getFont().deriveFont(10.0f));
        JLabel RTDV = new JLabel("Tipo de Vehículo:");
        RTDV.setBounds(5, 105, 100, 15);
        RTDV.setVisible(true);
        RTDV.setFont(RTDV.getFont().deriveFont(10.0f));
        JLabel RUDV = new JLabel("Uso de Vehículo:");
        RUDV.setBounds(5, 130, 100, 15);
        RUDV.setVisible(true);
        RUDV.setFont(RUDV.getFont().deriveFont(10.0f));
        JLabel RMarca = new JLabel("Marca:");
        RMarca.setBounds(5, 155, 100, 15);
        RMarca.setVisible(true);
        RMarca.setFont(RMarca.getFont().deriveFont(10.0f));
        JLabel RLinea = new JLabel("Linea:");
        RLinea.setBounds(5, 180, 100, 15);
        RLinea.setVisible(true);
        RLinea.setFont(RLinea.getFont().deriveFont(10.0f));
        JLabel RModelo = new JLabel("Modelo:");
        RModelo.setBounds(5, 205, 100, 15);
        RModelo.setVisible(true);
        RModelo.setFont(RModelo.getFont().deriveFont(10.0f));
        JLabel RVDV = new JLabel("Valor Del Vehículo:");
        RVDV.setBounds(5, 230, 100, 15);
        RVDV.setVisible(true);
        RVDV.setFont(RVDV.getFont().deriveFont(10.0f));
        
        //JTextFields
        JTextField JTFNombres = new JTextField();
        JTFNombres.setBounds(110, 5, 150, 15);
        JTFNombres.setVisible(true);
        JTFNombres.setFont(JTFNombres.getFont().deriveFont(10.0f));
        JTextField JTFApellidos = new JTextField();
        JTFApellidos.setBounds(110, 30, 150, 15);
        JTFApellidos.setVisible(true);
        JTFApellidos.setFont(JTFApellidos.getFont().deriveFont(10.0f));
        JTextField JTFDPI = new JTextField();
        JTFDPI.setBounds(110, 55, 150, 15);
        JTFDPI.setVisible(true);
        JTFDPI.setFont(JTFDPI.getFont().deriveFont(10.0f));
        JTextField JTFTel = new JTextField();
        JTFTel.setBounds(110, 80, 150, 15);
        JTFTel.setVisible(true);
        JTFTel.setFont(JTFTel.getFont().deriveFont(10.0f));
        
        //JComboBox
        //Combo TipoVehiculo
        String MCBXTipo[] = new String [30];
            for(int i=0;i<VectorSSTipo.length;i++){
                if (VectorSSTipo[i] != null){
                    MCBXTipo[i] = VectorSSTipo[i].getNombre();
                }
            }
        JComboBox JCMBTDV = new JComboBox(MCBXTipo);
        JCMBTDV.setBounds(110, 105, 150, 15);
        JCMBTDV.setVisible(true);
        JCMBTDV.setFont(JCMBTDV.getFont().deriveFont(10.0f));
        //ComboBoxUso
        String MCBXUso[] = new String [30];
            for(int i=0;i<VectorSSTipo.length;i++){
                if (VectorSSUso[i] != null){
                    MCBXUso[i] = VectorSSUso[i].getNombre();
                }
            }
        JComboBox JCMBUDV = new JComboBox(MCBXUso);
        JCMBUDV.setBounds(110, 130, 150, 15);
        JCMBUDV.setVisible(true);
        JCMBUDV.setFont(JCMBUDV.getFont().deriveFont(10.0f));
        //ComboBoxMarca
        String MCBXMarca[] = new String [30];
            for(int i=0;i<VectorSSMarca.length;i++){
                if (VectorSSMarca[i] != null){
                    MCBXMarca[i] = VectorSSMarca[i].getNombre();
                }
            }
        JComboBox JCMBMarca = new JComboBox(MCBXMarca);
        JCMBMarca.setBounds(110, 155, 150, 15);
        JCMBMarca.setVisible(true);
        JCMBMarca.setFont(JCMBMarca.getFont().deriveFont(10.0f));
        //ComboBox Linea
        String MCBXLinea[] = new String [30];
            for(int i=0;i<VectorSSLinea.length;i++){
                if (VectorSSLinea[i] != null){
                    MCBXLinea[i] = VectorSSLinea[i].getNombre();
                }
            }
        JComboBox JCMBLinea = new JComboBox(MCBXLinea);
        JCMBLinea.setBounds(110, 180, 150, 15);
        JCMBLinea.setVisible(true);
        JCMBLinea.setFont(JCMBLinea.getFont().deriveFont(10.0f));
        //ComboBox Modelo
        String MCBXModelo[] = new String [30];
            for(int i=0;i<VectorSSModelo.length;i++){
                if (VectorSSModelo[i] != null){
                    MCBXModelo[i] = VectorSSModelo[i].getAño();
                }
            }
        JComboBox JCMBModelo = new JComboBox(MCBXModelo);
        JCMBModelo.setBounds(110, 205, 150, 15);
        JCMBModelo.setVisible(true);
        JCMBModelo.setFont(JCMBModelo.getFont().deriveFont(10.0f));
        
        //JTextField
        JTextField JTFVDV = new JTextField();
        JTFVDV.setBounds(110, 230, 150, 15);
        JTFVDV.setVisible(true);
        JTFVDV.setFont(JTFVDV.getFont().deriveFont(10.0f));
        
        
        //CostoPrima
        JLabel RCPrima = new JLabel("Costo Prima:");
        RCPrima.setBounds(5, 280, 100, 15);
        RCPrima.setVisible(true);
        RCPrima.setFont(RCPrima.getFont().deriveFont(10.0f));
        JTextField JTFCPrima = new JTextField();
        JTFCPrima.setBounds(70, 280, 86, 15);
        JTFCPrima.setVisible(true);
        JTFCPrima.setFont(JTFCPrima.getFont().deriveFont(10.0f));
        JTFCPrima.setEditable(false);
        //Deducible
        JLabel RDeducible = new JLabel("Deducible:");
        RDeducible.setBounds(166, 280, 100, 15);
        RDeducible.setVisible(true);
        RDeducible.setFont(RCPrima.getFont().deriveFont(10.0f));
        JTextField JTFDeducible = new JTextField();
        JTFDeducible.setBounds(220, 280, 86, 15);
        JTFDeducible.setVisible(true);
        JTFDeducible.setFont(JTFDeducible.getFont().deriveFont(10.0f));
        JTFDeducible.setEditable(false);
        //PosibleCostoPrima
        JLabel RPCP = new JLabel("Posible Costo Prima:");
        RPCP.setBounds(35, 305, 150, 15);
        RPCP.setVisible(true);
        RPCP.setFont(RPCP.getFont().deriveFont(10.0f));
        JTextField JTFPCP = new JTextField();
        JTFPCP.setBounds(140, 305, 150, 15);
        JTFPCP.setVisible(true);
        JTFPCP.setFont(JTFPCP.getFont().deriveFont(10.0f));
        JTFPCP.setEditable(false);
        //PosibleDeducible
        JLabel RPD = new JLabel("Posible Deducible:");
        RPD.setBounds(35, 330, 150, 15);
        RPD.setVisible(true);
        RPD.setFont(RPD.getFont().deriveFont(10.0f));
        JTextField JTFPD = new JTextField();
        JTFPD.setBounds(140, 330, 150, 15);
        JTFPD.setVisible(true);
        JTFPD.setFont(JTFPD.getFont().deriveFont(10.0f));
        JTFPD.setEditable(false);
        
        //JButton
        JButton BCotizar = new JButton("Cotizar");
        BCotizar.setBounds(110, 255, 150, 15);
        BCotizar.setVisible(true);
        BCotizar.setFont(BCotizar.getFont().deriveFont(12.0f));
        BCotizar.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             //Ver si Valor Ingresado < Valor Maximo por Tipo Vehiculo
             float ValorUsuario = Float.parseFloat(JTFVDV.getText());
             Object ValorMaxPermitido = JCMBTDV.getSelectedItem();
             String NombreSelec = ValorMaxPermitido.toString();
             int IndexTipo = 0;
             for(int i=0;i<VectorSSTipo.length;i++){
                 if(VectorSSTipo[i]!=null){
                     if (NombreSelec.equals(VectorSSTipo[i].getNombre())){
                     IndexTipo = i;
                    }
                 }
             }
             
            if (ValorUsuario > VectorSSTipo[IndexTipo].getValorMaximo()){
                JFrame g = new JFrame();
                JOptionPane.showMessageDialog(g, "Debido al tipo de vehiculo el valor no puede ser mayor a "+VectorSSTipo[IndexTipo].getValorMaximo());
            } else{
                
            }
            //Calculo Costo Prima y Deducible
            float ValorReal, PDepreciacion;
            Object Modelo = JCMBModelo.getSelectedItem();
            String ModeloSelec = Modelo.toString();
            int IndexModelo = 0;
            for(int i=0;i<VectorSSModelo.length;i++){
                 if(VectorSSTipo[i]!=null){
                     if (ModeloSelec.equals(VectorSSModelo[i].getAño())){
                     IndexModelo = i;
                    }
                 }
             }
            PDepreciacion = VectorSSModelo[IndexModelo].getPDepreciacion();
            ValorReal = ValorUsuario - (PDepreciacion * ValorUsuario);
            
            //Porcentaje Total Riesgo
            float PTotalRiesgo, PDMarca, PDLinea, PDUso;
            //PDMarca
            Object Marca = JCMBMarca.getSelectedItem();
            String MarcaSelec = Marca.toString();
            int IndexMarca = 0;
            for(int i=0;i<VectorSSMarca.length;i++){
                if(VectorSSMarca[i]!=null){
                     if (MarcaSelec.equals(VectorSSMarca[i].getNombre())){
                     IndexMarca = i;
                    }
                 }
            }
            PDMarca = VectorSSMarca[IndexMarca].getPAumentoPoliza();
            //PDLinea
            Object Linea = JCMBLinea.getSelectedItem();
            String LineaSelec = Linea.toString();
            int IndexLinea = 0;
            for(int i=0;i<VectorSSLinea.length;i++){
                if(VectorSSLinea[i]!=null){
                     if (LineaSelec.equals(VectorSSLinea[i].getNombre())){
                     IndexLinea = i;
                    }
                 }
            }
            PDLinea = VectorSSLinea[IndexLinea].getPAumentoPoliza();
            //PDUso
            Object Uso = JCMBUDV.getSelectedItem();
            String UsoSelec = Uso.toString();
            int IndexUso = 0;
            for(int i=0;i<VectorSSUso.length;i++){
                if(VectorSSUso[i]!=null){
                     if (UsoSelec.equals(VectorSSUso[i].getNombre())){
                     IndexUso = i;
                    }
                 }
            }
            PDUso = VectorSSUso[IndexUso].getPAumentoPoliza();
            PTotalRiesgo = PDMarca + PDLinea + PDUso;
            //Prima Total
            float CostoDeducible, CostoPrima, PrimaTotal = (float) (ValorReal * (0.1 + PTotalRiesgo));
            CostoPrima = PrimaTotal/12;
            CostoDeducible = (float) (0.07 * ValorReal);
            String TextoCPrima = Float.toString(CostoPrima);
            JTFCPrima.setText(TextoCPrima);
            String TextoCDeducible = Float.toString(CostoDeducible);
            JTFDeducible.setText(TextoCDeducible);
            JTFPCP.setText(TextoCPrima);
            JTFPD.setText(TextoCDeducible);
         }
        });
        
        //JButtons Del Fondo
        JButton BMenosD = new JButton("-Deducible");
        BMenosD.setBounds(5, 355, 86, 15);
        BMenosD.setVisible(true);
        BMenosD.setFont(BMenosD.getFont().deriveFont(10.0f));
        BMenosD.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
            float PMenosDeducible = Float.parseFloat(JTFPD.getText());
            float PMasPrima = Float.parseFloat(JTFPCP.getText());
            if (PMenosDeducible > 0){
                PMenosDeducible = (float) (PMenosDeducible - (PMenosDeducible*0.1));
                PMasPrima = (float) (PMasPrima + (PMasPrima*0.03));
            }
            JTFPCP.setText(Float.toString(PMasPrima));
            JTFPD.setText(Float.toString(PMenosDeducible));
         }
        });
        JButton BMasD = new JButton("+Deducible");
        BMasD.setBounds(93, 355, 90, 15);
        BMasD.setVisible(true);
        BMasD.setFont(BMasD.getFont().deriveFont(10.0f));
        BMasD.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
            float PMasDeducible = Float.parseFloat(JTFPD.getText());
            float PMenosPrima = Float.parseFloat(JTFPCP.getText());
            if (PMasDeducible > 0){
                PMasDeducible = (float) (PMasDeducible + (PMasDeducible*0.1));
                PMenosPrima = (float) (PMenosPrima - (PMenosPrima*0.03));
            }
            JTFPCP.setText(Float.toString(PMenosPrima));
            JTFPD.setText(Float.toString(PMasDeducible));
         }
        });
        JButton BSSeguro = new JButton("Solicitar Seguro");
        BSSeguro.setBounds(185, 355, 120, 15);
        BSSeguro.setVisible(true);
        BSSeguro.setFont(BSSeguro.getFont().deriveFont(10.0f));
        BSSeguro.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
            IndexMatrizSS = IndexMatrizSS + 1;
            MatrizSSPorAprovar[IndexMatrizSS][0]=JTFDPI.getText();
            MatrizSSPorAprovar[IndexMatrizSS][1]=JTFNombres.getText();
            MatrizSSPorAprovar[IndexMatrizSS][2]=JTFTel.getText();
            MatrizSSPorAprovar[IndexMatrizSS][3]="AUTOMAS";
            
            //Descripcion
            //ObtenerTipo
             Object ValorMaxPermitido = JCMBTDV.getSelectedItem();
             String NombreSelec = ValorMaxPermitido.toString();
             int IndexTipo = 0;
             for(int i=0;i<VectorSSTipo.length;i++){
                 if(VectorSSTipo[i]!=null){
                     if (NombreSelec.equals(VectorSSTipo[i].getNombre())){
                     IndexTipo = i;
                    }
                 }
             }
             String TipoReal = VectorSSTipo[IndexTipo].getNombre();
             //ObtenerModelo
             Object Modelo = JCMBModelo.getSelectedItem();
             String ModeloSelec = Modelo.toString();
             int IndexModelo = 0;
             for(int i=0;i<VectorSSModelo.length;i++){
                 if(VectorSSTipo[i]!=null){
                     if (ModeloSelec.equals(VectorSSModelo[i].getAño())){
                     IndexModelo = i;
                     }
                  }
              }
             String ModeloReal = VectorSSModelo[IndexModelo].getAño();
             //ObtenerUso
             Object Uso = JCMBUDV.getSelectedItem();
             String UsoSelec = Uso.toString();
             int IndexUso = 0;
             for(int i=0;i<VectorSSUso.length;i++){
                 if(VectorSSUso[i]!=null){
                      if (UsoSelec.equals(VectorSSUso[i].getNombre())){
                      IndexUso = i;
                     }
                  }
             }
             String UsoReal = VectorSSUso[IndexUso].getNombre();
             //ObtenerLinea
             Object Linea = JCMBLinea.getSelectedItem();
             String LineaSelec = Linea.toString();
             int IndexLinea = 0;
             for(int i=0;i<VectorSSLinea.length;i++){
                 if(VectorSSLinea[i]!=null){
                      if (LineaSelec.equals(VectorSSLinea[i].getNombre())){
                      IndexLinea = i;
                     }
                 }
             }
             String LineaReal = VectorSSLinea[IndexLinea].getNombre();
             //DescripcionReal
             String DescripcionReal = TipoReal+","+ModeloReal+","+UsoReal+","+LineaReal;
             MatrizSSPorAprovar[IndexMatrizSS][4]= DescripcionReal;
             
            //Monto
            //ConseguirMonto
            String TextoCPrima = JTFCPrima.getText();
            float MontoTemporal = Float.parseFloat(TextoCPrima); 
            float MontoTotal = MontoTemporal*12;
            String MontoTotalReal = Float.toString(MontoTotal);
            MatrizSSPorAprovar[IndexMatrizSS][5] = MontoTotalReal;
            MatrizSSPorAprovar[IndexMatrizSS][6]=JTFCPrima.getText();
            MatrizSSPorAprovar[IndexMatrizSS][7]=JTFDeducible.getText();
            
            //Mensaje
            JFrame g = new JFrame();
            JOptionPane.showMessageDialog(g, "Su solicitud ha sido enviada, haga click en Cancelar para volver al Menú Principal :) ");
         }
        });
        JButton BCancelar = new JButton("Cancelar");
        BCancelar.setBounds(5, 380, 300, 15);
        BCancelar.setVisible(true);
        BCancelar.setFont(BCancelar.getFont().deriveFont(12.0f));
        BCancelar.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
            SSeguro.dispose();
            ClasePrincipal.MenuPrincipal();
         }
        });
        //adds
        SSeguro.add(RNombres);
        SSeguro.add(RApellidos);
        SSeguro.add(RDPI);
        SSeguro.add(RTel);
        SSeguro.add(RTDV);
        SSeguro.add(RUDV);
        SSeguro.add(RMarca);
        SSeguro.add(RLinea);
        SSeguro.add(RModelo);
        SSeguro.add(RVDV);
        SSeguro.add(JTFNombres);
        SSeguro.add(JTFApellidos);
        SSeguro.add(JTFDPI);
        SSeguro.add(JTFTel);
        SSeguro.add(JCMBTDV);
        SSeguro.add(JCMBUDV);
        SSeguro.add(JCMBMarca);
        SSeguro.add(JCMBLinea);
        SSeguro.add(JCMBModelo);
        SSeguro.add(JTFVDV);
        SSeguro.add(BCotizar);
        SSeguro.add(RCPrima);
        SSeguro.add(JTFCPrima);
        SSeguro.add(RDeducible);
        SSeguro.add(JTFDeducible);
        SSeguro.add(RPCP);
        SSeguro.add(JTFPCP);
        SSeguro.add(RPD);
        SSeguro.add(JTFPD);
        SSeguro.add(BMenosD);
        SSeguro.add(BMasD);
        SSeguro.add(BSSeguro);
        SSeguro.add(BCancelar);
        SSeguro.repaint();
    }
    public static void CargarDatos(){
        JFrame CDatos = new JFrame("Cargar Datos");
        CDatos.setLocationRelativeTo(null);
        CDatos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CDatos.getContentPane();
        CDatos.setLayout(null);
        CDatos.pack();
        CDatos.setBounds(150, 100, 500, 300);
        CDatos.setResizable(false);
        CDatos.setVisible(true);
        
        //JTextArea
        JTextArea IngresarDatos = new JTextArea();
        IngresarDatos.setBounds(10, 15, 470, 200);
        IngresarDatos.setVisible(true);
        //JScrollPane
        JScrollPane Scroll = new JScrollPane(IngresarDatos);
        Scroll.setBounds(10, 15, 470, 200);
        Scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        Scroll.setVisible(true);
        //JButtons
        JButton BCargar = new JButton("Cargar");
        BCargar.setBounds(260, 220, 100, 15);
        BCargar.setVisible(true);
        BCargar.setFont(BCargar.getFont().deriveFont(12.0f));
        BCargar.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
            String Texto = IngresarDatos.getText();
            String Lineas[] = Texto.split("\n");
            for(String Contador:Lineas){
                String Elementos[] = Contador.split(",");
                switch(Elementos[0]){
                    case "TIPO":
                        ClasePrincipal.AgregarTipo(new SSTipo(Elementos[1], Float.parseFloat(Elementos[2])));
                        break;
                    case "MODELO":
                        ClasePrincipal.AgregarModelo(new SSModelo(Elementos[1], Float.parseFloat(Elementos[2])));
                        break;
                    case "MARCA":
                        ClasePrincipal.AgregarMarca(new SSMarca(Elementos[1], Float.parseFloat(Elementos[2])));
                        break;
                    case "LINEA":
                        ClasePrincipal.AgregarLinea(new SSLinea(Elementos[1], Float.parseFloat(Elementos[2])));
                        break;
                    case "USO":
                        ClasePrincipal.AgregarUso(new SSUso(Elementos[1], Float.parseFloat(Elementos[2])));
                        break;
                    case "MECANICA":
                        ClasePrincipal.AgregarMecanica(new SSMecanica(Elementos[1], Float.parseFloat(Elementos[2])));
                        break;
                    case "REPUESTO":
                        ClasePrincipal.AgregarRepuesto(new SSRepuesto(Elementos[1], Float.parseFloat(Elementos[2])));
                }
            }
            IngresarDatos.setText("");
            JFrame g = new JFrame();
            JOptionPane.showMessageDialog(g, "Haga click en Cancelar para volver al Menu Principal :)");
         }
        });
        
        JButton BCancelar = new JButton("Cancelar");
        BCancelar.setBounds(370, 220, 100, 15);
        BCancelar.setVisible(true);
        BCancelar.setFont(BCancelar.getFont().deriveFont(12.0f));
        BCancelar.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
            CDatos.dispose();
            ClasePrincipal.MenuPrincipal();
         }
        });
        //adds
        CDatos.add(Scroll);
        CDatos.add(BCargar);
        CDatos.add(BCancelar);
        CDatos.repaint();
    }
    //Llenar los Combobox de Solicitud Seguro
    public static void AgregarTipo(SSTipo NuevoTipo){
            for(int i=0;i<VectorSSTipo.length;i++){
                if(VectorSSTipo[i]==null){
                    VectorSSTipo[i]=NuevoTipo;
                    return;
                }
            }
    }
    public static void AgregarModelo(SSModelo NuevoModelo){
            for(int i=0;i<VectorSSModelo.length;i++){
                if(VectorSSModelo[i]==null){
                    VectorSSModelo[i]=NuevoModelo;
                    return;
                }
            }
    }
    public static void AgregarMarca(SSMarca NuevaMarca){
            for(int i=0;i<VectorSSMarca.length;i++){
                if(VectorSSMarca[i]==null){
                    VectorSSMarca[i]=NuevaMarca;
                    return;
                }
            }
    }
    public static void AgregarLinea(SSLinea NuevaLinea){
            for(int i=0;i<VectorSSLinea.length;i++){
                if(VectorSSLinea[i]==null){
                    VectorSSLinea[i]=NuevaLinea;
                    return;
                }
            }
    }
    public static void AgregarUso(SSUso NuevoUso){
            for(int i=0;i<VectorSSUso.length;i++){
                if(VectorSSUso[i]==null){
                    VectorSSUso[i]=NuevoUso;
                    return;
                }
            }
    }
    public static void AgregarMecanica(SSMecanica NuevaMecanica){
            for(int i=0;i<VectorSSMecanica.length;i++){
                if(VectorSSMecanica[i]==null){
                    VectorSSMecanica[i]=NuevaMecanica;
                    return;
                }
            }
    }
    public static void AgregarRepuesto(SSRepuesto NuevoRepuesto){
            for(int i=0;i<VectorSSRepuesto.length;i++){
                if(VectorSSRepuesto[i]==null){
                    VectorSSRepuesto[i]=NuevoRepuesto;
                    return;
                }
            }
    }
    public static void LogIn(){
        //Inicializador JFrame LogIn
        JFrame LogIn = new JFrame("Log In");
        LogIn.setLocationRelativeTo(null);
        LogIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LogIn.getContentPane();
        LogIn.setLayout(null);
        LogIn.pack();
        LogIn.setBounds(150, 100, 230, 120);
        LogIn.setResizable(false);
        LogIn.setVisible(true);
        //JLabels
        JLabel RID = new JLabel("ID:");
        RID.setBounds(20, 10, 20, 15);
        RID.setVisible(true);
        RID.setFont(RID.getFont().deriveFont(10.0f));
        //JTextFields
        JTextField JTFID = new JTextField();
        JTFID.setBounds(40, 10, 150, 15);
        JTFID.setVisible(true);
        JTFID.setFont(JTFID.getFont().deriveFont(10.0f));
        //JButton
        JButton BEntrar = new JButton("Entrar");
        BEntrar.setBounds(40, 35, 150, 15);
        BEntrar.setVisible(true);
        BEntrar.setFont(BEntrar.getFont().deriveFont(10.0f));
        BEntrar.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             String IDIngresado = JTFID.getText(), IDILowerCase;
             IDILowerCase = IDIngresado.toLowerCase();
             if(IDILowerCase.equals("admin")){
                     ClasePrincipal.Administradores();
                     LogIn.dispose();
                 } else {
                   for(int i=0;i<20;i++){
                    if(VectorPersona[i]!=null){
                    String IDEnVectorPersona = String.valueOf(VectorPersona[i].getID());
                    if (IDIngresado.equals(IDEnVectorPersona)){
                     if(VectorPersona[i].getAsegurado() == 0){
                         NombreSujetoLogueado = VectorPersona[i].getNombre();
                         TelefonoSujetoLogueado = VectorPersona[i].getTelefono();
                         DPISujetoLogueado = String.valueOf(VectorPersona[i].getID());
                         AseguradoSujetoLogueado = "No Asegurado";
                         MontoSujetoLogueado = String.valueOf(VectorPersona[i].getMonto());
                         CostoPrimaSujetoLogueado = String.valueOf(VectorPersona[i].getPoliza());
                         DeducibleSujetoLogueado = String.valueOf(VectorPersona[i].getDeducible());
                         TipoSujetoLogueado = VectorPersona[i].getTipo();
                         ClasePrincipal.NoAsegurados();
                         LogIn.dispose();
                     } else if(VectorPersona[i].getAsegurado() == 1){
                         NombreSujetoLogueado = VectorPersona[i].getNombre();
                         TelefonoSujetoLogueado = VectorPersona[i].getTelefono();
                         DPISujetoLogueado = String.valueOf(VectorPersona[i].getID());
                         MontoSujetoLogueado = String.valueOf(VectorPersona[i].getMonto());
                         CostoPrimaSujetoLogueado = String.valueOf(VectorPersona[i].getPoliza());
                         DeducibleSujetoLogueado = String.valueOf(VectorPersona[i].getDeducible());
                         TipoSujetoLogueado = VectorPersona[i].getTipo();
                         AseguradoSujetoLogueado = "Asegurado";
                         ClasePrincipal.Asegurados();
                         LogIn.dispose();
                        }
                     }
                    }
                   }
                   if(DPISujetoLogueado == null){
                   JFrame g = new JFrame();
                   JOptionPane.showMessageDialog(g, "No existe ese usuario :(");
                   }
              }
         }
        });
        JButton BCancelar = new JButton("Cancelar");
        BCancelar.setBounds(40, 60, 150, 15);
        BCancelar.setVisible(true);
        BCancelar.setFont(BCancelar.getFont().deriveFont(10.0f));
        BCancelar.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             ClasePrincipal.MenuPrincipal();
             LogIn.dispose();
         }
        });
        //adds
        LogIn.add(RID);
        LogIn.add(JTFID);
        LogIn.add(BEntrar);
        LogIn.add(BCancelar);
        LogIn.repaint();
    }
    
    //JFrames Asegurados
    public static void Asegurados(){
        JFrame PAsegurados = new JFrame("Menu Asegurado");
        PAsegurados.setLocationRelativeTo(null);
        PAsegurados.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PAsegurados.getContentPane();
        PAsegurados.setLayout(null);
        PAsegurados.pack();
        PAsegurados.setBounds(150, 100, 500, 300);
        PAsegurados.setResizable(false);
        PAsegurados.setVisible(true);
        //Cerrar Sesion
        JButton BCerrarSesion = new JButton("Cerrar Sesión");
        BCerrarSesion.setBounds(10, 5, 110, 15);
        BCerrarSesion.setVisible(true);
        BCerrarSesion.setFont(BCerrarSesion.getFont().deriveFont(10.0f));
        BCerrarSesion.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             NombreSujetoLogueado = null;
             TelefonoSujetoLogueado = null;
             DPISujetoLogueado = null;
             MontoSujetoLogueado = null;
             CostoPrimaSujetoLogueado = null;
             DeducibleSujetoLogueado = null;
             TipoSujetoLogueado = null;
             AseguradoSujetoLogueado = null;
             PAsegurados.dispose();
             ClasePrincipal.MenuPrincipal();
         }
        });
        //DatosUsuario
        JTextArea DatosU = new JTextArea();
        DatosU.setBounds(10, 30, 300, 50);
        DatosU.setVisible(true);
        DatosU.setEditable(false);
        DatosU.setText("Nombre: " + NombreSujetoLogueado+"\n"+"Telefono: "+TelefonoSujetoLogueado+"\n"+"DPI: "+DPISujetoLogueado+"\n"+"Tipo: "+AseguradoSujetoLogueado);
        //Fecha
        JLabel RFecha = new JLabel("Fecha:");
        RFecha.setBounds(330, 30, 40, 15);
        RFecha.setVisible(true);
        RFecha.setFont(RFecha.getFont().deriveFont(10.0f));
        JTextField JTFFecha = new JTextField();
        JTFFecha.setBounds(380, 30, 100, 15);
        JTFFecha.setVisible(true);
        JTFFecha.setFont(JTFFecha.getFont().deriveFont(10.0f));
        JTFFecha.setEditable(false);
        //IconosMenu
        JLabel IMisSeguros = new JLabel();
        IMisSeguros.setBounds(20, 100, 130 , 130);
        ImageIcon IMS = new ImageIcon(new ImageIcon("src/Assets/MisSeguros.png").getImage().getScaledInstance(IMisSeguros.getWidth(), IMisSeguros.getHeight(), Image.SCALE_DEFAULT));
        IMisSeguros.setIcon(IMS);
        IMisSeguros.setVisible(true);
        IMisSeguros.setFont(IMisSeguros.getFont().deriveFont(16.0f));
        JLabel IMisIncidentes = new JLabel();
        IMisIncidentes.setBounds(180, 100, 130 , 130);
        ImageIcon IMI = new ImageIcon(new ImageIcon("src/Assets/Incidentes.png").getImage().getScaledInstance(IMisIncidentes.getWidth(), IMisIncidentes.getHeight(), Image.SCALE_DEFAULT));
        IMisIncidentes.setIcon(IMI);
        IMisIncidentes.setVisible(true);
        IMisIncidentes.setFont(IMisIncidentes.getFont().deriveFont(16.0f));
        JLabel IPagos = new JLabel();
        IPagos.setBounds(340, 100, 130 , 130);
        ImageIcon IP = new ImageIcon(new ImageIcon("src/Assets/Pagos.png").getImage().getScaledInstance(IPagos.getWidth(), IPagos.getHeight(), Image.SCALE_DEFAULT));
        IPagos.setIcon(IP);
        IPagos.setVisible(true);
        IPagos.setFont(IPagos.getFont().deriveFont(16.0f));
        //Botones
        JButton BMisSeguros = new JButton("Mis Seguros");
        BMisSeguros.setBounds(20, 240, 130, 15);
        BMisSeguros.setVisible(true);
        BMisSeguros.setFont(BMisSeguros.getFont().deriveFont(12.0f));
        BMisSeguros.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             ClasePrincipal.AMisSeguros();
             PAsegurados.dispose();
         }
        });
        JButton BMisIncidentes = new JButton("Mis Incidentes");
        BMisIncidentes.setBounds(180, 240, 130, 15);
        BMisIncidentes.setVisible(true);
        BMisIncidentes.setFont(BMisIncidentes.getFont().deriveFont(12.0f));
        BMisIncidentes.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             ClasePrincipal.AMisIncidentes();
             PAsegurados.dispose();
         }
        });
        JButton BPagos = new JButton("Pagos");
        BPagos.setBounds(340, 240, 130, 15);
        BPagos.setVisible(true);
        BPagos.setFont(BPagos.getFont().deriveFont(12.0f));
        BPagos.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             ClasePrincipal.AMisPagos();
             PAsegurados.dispose();
         }
        });
        //adds
        PAsegurados.add(BCerrarSesion);
        PAsegurados.add(DatosU);
        PAsegurados.add(RFecha);
        PAsegurados.add(JTFFecha);
        PAsegurados.add(IMisSeguros);
        PAsegurados.add(IMisIncidentes);
        PAsegurados.add(IPagos);
        PAsegurados.add(BMisSeguros);
        PAsegurados.add(BMisIncidentes);
        PAsegurados.add(BPagos);
        PAsegurados.repaint();
    }
    public static void AMisSeguros(){
        JFrame AMisSeguros = new JFrame();
        AMisSeguros.setLocationRelativeTo(null);
        AMisSeguros.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AMisSeguros.getContentPane();
        AMisSeguros.setLayout(null);
        AMisSeguros.pack();
        AMisSeguros.setBounds(150, 100, 650, 300);
        AMisSeguros.setResizable(false);
        AMisSeguros.setVisible(true);
        //DatosUsuario
        JTextArea DatosU = new JTextArea();
        DatosU.setBounds(20, 10, 155, 75);
        DatosU.setVisible(true);
        DatosU.setEnabled(false);
        DatosU.setText("Nombre: " + NombreSujetoLogueado+"\n"+"Telefono: "+TelefonoSujetoLogueado+"\n"+"DPI: "+DPISujetoLogueado+"\n"+"Tipo: "+AseguradoSujetoLogueado);
        //JLabels
        JLabel RPPoliza = new JLabel("Precio Prima/Poliza:");
        RPPoliza.setBounds(186, 5, 125, 15);
        RPPoliza.setVisible(true);
        RPPoliza.setFont(RPPoliza.getFont().deriveFont(10.0f));
        JLabel PrecioPolizaReal = new JLabel(CostoPrimaSujetoLogueado);
        PrecioPolizaReal.setBounds(186, 20, 125, 15);
        PrecioPolizaReal.setVisible(true);
        PrecioPolizaReal.setFont(PrecioPolizaReal.getFont().deriveFont(10.0f));
        JLabel RPDeducible = new JLabel("Precio Deducible:");
        RPDeducible.setBounds(321, 5, 125, 15);
        RPDeducible.setVisible(true);
        RPDeducible.setFont(RPDeducible.getFont().deriveFont(10.0f));
        JLabel PrecioDeducibleReal = new JLabel(DeducibleSujetoLogueado);
        PrecioDeducibleReal.setBounds(321, 20, 125, 15);
        PrecioDeducibleReal.setVisible(true);
        PrecioDeducibleReal.setFont(PrecioDeducibleReal.getFont().deriveFont(10.0f));
        JLabel RMAsegurado = new JLabel("Monto Asegurado: ");
        RMAsegurado.setBounds(186, 45, 125, 15);
        RMAsegurado.setVisible(true);
        RMAsegurado.setFont(RMAsegurado.getFont().deriveFont(10.0f));
        JLabel MAseguradoReal = new JLabel(MontoSujetoLogueado);
        MAseguradoReal.setBounds(186, 60, 125, 15);
        MAseguradoReal.setVisible(true);
        MAseguradoReal.setFont(MAseguradoReal.getFont().deriveFont(10.0f));
        JLabel REstado = new JLabel("Estado: ");
        REstado.setBounds(321, 45, 125, 15);
        REstado.setVisible(true);
        REstado.setFont(REstado.getFont().deriveFont(10.0f));
        JLabel EstadoReal = new JLabel(AseguradoSujetoLogueado);
        EstadoReal.setBounds(321, 60, 125, 15);
        EstadoReal.setVisible(true);
        EstadoReal.setFont(EstadoReal.getFont().deriveFont(10.0f));
        JLabel RFecha = new JLabel("Fecha Sistema:");
        RFecha.setBounds(420, 0, 125, 15);
        RFecha.setVisible(true);
        RFecha.setFont(RFecha.getFont().deriveFont(10.0f));
        JTextField JTFFecha = new JTextField();
        JTFFecha.setBounds(500, 0, 125, 15);
        JTFFecha.setEditable(false);
        JTFFecha.setVisible(true);
        JTFFecha.setFont(JTFFecha.getFont().deriveFont(10.0f));
        //JButton
        JButton BRenovar = new JButton("Renovar");
        BRenovar.setBounds(450, 70, 86, 15);
        BRenovar.setVisible(true);
        BRenovar.setFont(BRenovar.getFont().deriveFont(10.0f));
        BRenovar.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             
         }
        });
        JButton BVolver = new JButton("Volver");
        BVolver.setBounds(544, 70, 86, 15);
        BVolver.setVisible(true);
        BVolver.setFont(BVolver.getFont().deriveFont(10.0f));
        BVolver.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             AMisSeguros.dispose();
             ClasePrincipal.Asegurados();
         }
        });
        String VTitulosTAMisSeguros[] = {"Correlativo", "Tipo", "Total", "Fecha Inicio", "Fecha Fin"};
        JPanel PanelTabla = new JPanel();
        PanelTabla.setLayout(null);
        PanelTabla.setVisible(true);
        PanelTabla.setBackground(Color.GRAY);
        PanelTabla.setBounds(20, 90, 610, 175);
        JTable Tabla = new JTable(11, 5);
        Tabla.setVisible(true);
        Tabla.setBounds(10, 0, 590, 180);
        Tabla.setEnabled(true);
        PanelTabla.add(Tabla);
        //adds
        AMisSeguros.add(RPPoliza);
        AMisSeguros.add(DatosU);
        AMisSeguros.add(PrecioPolizaReal);
        AMisSeguros.add(RPDeducible);
        AMisSeguros.add(PrecioDeducibleReal);
        AMisSeguros.add(RMAsegurado);
        AMisSeguros.add(MAseguradoReal);
        AMisSeguros.add(REstado);
        AMisSeguros.add(EstadoReal);
        AMisSeguros.add(RFecha);
        AMisSeguros.add(JTFFecha);
        AMisSeguros.add(BRenovar);
        AMisSeguros.add(BVolver);
        AMisSeguros.add(PanelTabla);
        AMisSeguros.repaint();
    }
    public static void AMisIncidentes(){
        JFrame AMisIncidentes = new JFrame();
        AMisIncidentes.setLocationRelativeTo(null);
        AMisIncidentes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AMisIncidentes.getContentPane();
        AMisIncidentes.setLayout(null);
        AMisIncidentes.pack();
        AMisIncidentes.setBounds(150, 100, 650, 450);
        AMisIncidentes.setResizable(false);
        AMisIncidentes.setVisible(true);
        //DatosUsuario
        JTextArea DatosU = new JTextArea();
        DatosU.setBounds(20, 10, 300, 75);
        DatosU.setVisible(true);
        DatosU.setEnabled(false);
        DatosU.setText("Nombre: " + NombreSujetoLogueado+"\n"+"Telefono: "+TelefonoSujetoLogueado+"\n"+"DPI: "+DPISujetoLogueado+"\n"+"Tipo: "+AseguradoSujetoLogueado);
        //JLabels
        JLabel RPPoliza = new JLabel("Precio Prima/Poliza:");
        RPPoliza.setBounds(25, 5, 125, 15);
        RPPoliza.setVisible(true);
        RPPoliza.setFont(RPPoliza.getFont().deriveFont(10.0f));
        JLabel PrecioPolizaReal = new JLabel("500");
        PrecioPolizaReal.setBounds(25, 20, 125, 15);
        PrecioPolizaReal.setVisible(true);
        PrecioPolizaReal.setFont(PrecioPolizaReal.getFont().deriveFont(10.0f));
        JLabel RPDeducible = new JLabel("Precio Deducible:");
        RPDeducible.setBounds(150, 5, 125, 15);
        RPDeducible.setVisible(true);
        RPDeducible.setFont(RPDeducible.getFont().deriveFont(10.0f));
        JLabel PrecioDeducibleReal = new JLabel("500");
        PrecioDeducibleReal.setBounds(150, 20, 125, 15);
        PrecioDeducibleReal.setVisible(true);
        PrecioDeducibleReal.setFont(PrecioDeducibleReal.getFont().deriveFont(10.0f));
        //JButton
        JButton BDetalle = new JButton("Detalle");
        BDetalle.setBounds(323, 250, 86, 15);
        BDetalle.setVisible(true);
        BDetalle.setFont(BDetalle.getFont().deriveFont(10.0f));
        BDetalle.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             
         }
        });
        JButton BPagar = new JButton("Pagar");
        BPagar.setBounds(419, 250, 86, 15);
        BPagar.setVisible(true);
        BPagar.setFont(BPagar.getFont().deriveFont(10.0f));
        BPagar.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             
         }
        });
        JButton BVolver = new JButton("Volver");
        BVolver.setBounds(515, 250, 86, 15);
        BVolver.setVisible(true);
        BVolver.setFont(BVolver.getFont().deriveFont(10.0f));
        BVolver.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             AMisIncidentes.dispose();
             ClasePrincipal.Asegurados();
         }
        });
        String VTitulosTAMisSeguros[] = {"Código", "Rol", "Costo Real", "Pago Requerido", "Estado", "Pago"};
        //JPanel
        JPanel PanelTabla = new JPanel();
        PanelTabla.setLayout(null);
        PanelTabla.setVisible(true);
        PanelTabla.setBackground(Color.GRAY);
        PanelTabla.setBounds(20, 90, 610, 300);
        JTable Tabla = new JTable(11, 6);
        Tabla.setVisible(true);
        Tabla.setBounds(10, 50, 590, 180);
        Tabla.setEnabled(true);
        PanelTabla.add(RPPoliza);
        PanelTabla.add(PrecioPolizaReal);
        PanelTabla.add(RPDeducible);
        PanelTabla.add(PrecioDeducibleReal);
        PanelTabla.add(Tabla);
        PanelTabla.add(BDetalle);
        PanelTabla.add(BPagar);
        PanelTabla.add(BVolver);
        //adds
        AMisIncidentes.add(DatosU);;
        AMisIncidentes.add(PanelTabla);
        AMisIncidentes.repaint();
    }
    public static void AMisPagos(){
        JFrame AMisPagos = new JFrame();
        AMisPagos.setLocationRelativeTo(null);
        AMisPagos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AMisPagos.getContentPane();
        AMisPagos.setLayout(null);
        AMisPagos.pack();
        AMisPagos.setBounds(150, 100, 650, 450);
        AMisPagos.setResizable(false);
        AMisPagos.setVisible(true);
        //DatosUsuario
        JTextArea DatosU = new JTextArea();
        DatosU.setBounds(20, 10, 300, 75);
        DatosU.setVisible(true);
        DatosU.setEnabled(false);
        DatosU.setText("Nombre: " + NombreSujetoLogueado+"\n"+"Telefono: "+TelefonoSujetoLogueado+"\n"+"DPI: "+DPISujetoLogueado+"\n"+"Tipo: "+AseguradoSujetoLogueado);
        //JButton
        JButton BVolver = new JButton("Volver");
        BVolver.setBounds(515, 250, 86, 15);
        BVolver.setVisible(true);
        BVolver.setFont(BVolver.getFont().deriveFont(10.0f));
        BVolver.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             AMisPagos.dispose();
             ClasePrincipal.Asegurados();
         }
        });
        String VTitulosTAMisSeguros[] = {"Correlativo", "Tipo", "Total", "Fecha Inicio", "Fecha Fin"};
        //JPanel
        JPanel PanelTabla = new JPanel();
        PanelTabla.setLayout(null);
        PanelTabla.setVisible(true);
        PanelTabla.setBackground(Color.GRAY);
        PanelTabla.setBounds(20, 90, 610, 300);
        JTable Tabla = new JTable(11, 5);
        Tabla.setVisible(true);
        Tabla.setBounds(10, 50, 590, 180);
        Tabla.setEnabled(true);
        PanelTabla.add(Tabla);
        PanelTabla.add(BVolver);
        //adds
        AMisPagos.add(DatosU);;
        AMisPagos.add(PanelTabla);
        AMisPagos.repaint();
    }
    
    //JFrames No Asegurados
    public static void NoAsegurados(){
        JFrame PNAsegurados = new JFrame("Menu No Asegurado");
        PNAsegurados.setLocationRelativeTo(null);
        PNAsegurados.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PNAsegurados.getContentPane();
        PNAsegurados.setLayout(null);
        PNAsegurados.pack();
        PNAsegurados.setBounds(150, 100, 500, 300);
        PNAsegurados.setResizable(false);
        PNAsegurados.setVisible(true);
        //Cerrar Sesion
        JButton BCerrarSesion = new JButton("Cerrar Sesión");
        BCerrarSesion.setBounds(10, 5, 110, 15);
        BCerrarSesion.setVisible(true);
        BCerrarSesion.setFont(BCerrarSesion.getFont().deriveFont(10.0f));
        BCerrarSesion.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             NombreSujetoLogueado = null;
             TelefonoSujetoLogueado = null;
             DPISujetoLogueado = null;
             MontoSujetoLogueado = null;
             CostoPrimaSujetoLogueado = null;
             DeducibleSujetoLogueado = null;
             TipoSujetoLogueado = null;
             AseguradoSujetoLogueado = null;
             PNAsegurados.dispose();
             ClasePrincipal.MenuPrincipal();
         }
        });
        //DatosUsuario
        JTextArea DatosU = new JTextArea();
        DatosU.setBounds(10, 30, 300, 50);
        DatosU.setVisible(true);
        DatosU.setEditable(false);
        DatosU.setText("Nombre: " + NombreSujetoLogueado+"\n"+"Telefono: "+TelefonoSujetoLogueado+"\n"+"DPI: "+DPISujetoLogueado+"\n"+"Tipo: "+AseguradoSujetoLogueado);
        //Fecha
        JLabel RFecha = new JLabel("Fecha:");
        RFecha.setBounds(330, 30, 40, 15);
        RFecha.setVisible(true);
        RFecha.setFont(RFecha.getFont().deriveFont(10.0f));
        JTextField JTFFecha = new JTextField();
        JTFFecha.setBounds(380, 30, 100, 15);
        JTFFecha.setVisible(true);
        JTFFecha.setFont(JTFFecha.getFont().deriveFont(10.0f));
        JTFFecha.setEditable(false);
        //IconosMenu
        JLabel IMisIncidentes = new JLabel();
        IMisIncidentes.setBounds(80, 100, 130 , 130);
        ImageIcon IMI = new ImageIcon(new ImageIcon("src/Assets/Incidentes.png").getImage().getScaledInstance(IMisIncidentes.getWidth(), IMisIncidentes.getHeight(), Image.SCALE_DEFAULT));
        IMisIncidentes.setIcon(IMI);
        IMisIncidentes.setVisible(true);
        IMisIncidentes.setFont(IMisIncidentes.getFont().deriveFont(16.0f));
        JLabel IPagos = new JLabel();
        IPagos.setBounds(250, 100, 130 , 130);
        ImageIcon IP = new ImageIcon(new ImageIcon("src/Assets/Pagos.png").getImage().getScaledInstance(IPagos.getWidth(), IPagos.getHeight(), Image.SCALE_DEFAULT));
        IPagos.setIcon(IP);
        IPagos.setVisible(true);
        IPagos.setFont(IPagos.getFont().deriveFont(16.0f));
        //Botones
        JButton BMisIncidentes = new JButton("Mis Incidentes");
        BMisIncidentes.setBounds(80, 240, 130, 15);
        BMisIncidentes.setVisible(true);
        BMisIncidentes.setFont(BMisIncidentes.getFont().deriveFont(12.0f));
        BMisIncidentes.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             PNAsegurados.dispose();
             ClasePrincipal.NAMisIncidentes();
         }
        });
        JButton BPagos = new JButton("Pagos");
        BPagos.setBounds(250, 240, 130, 15);
        BPagos.setVisible(true);
        BPagos.setFont(BPagos.getFont().deriveFont(12.0f));
        BPagos.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){

         }
        });
        //adds
        PNAsegurados.add(BCerrarSesion);
        PNAsegurados.add(DatosU);
        PNAsegurados.add(RFecha);
        PNAsegurados.add(JTFFecha);
        PNAsegurados.add(IMisIncidentes);
        PNAsegurados.add(IPagos);
        PNAsegurados.add(BMisIncidentes);
        PNAsegurados.add(BPagos);
        PNAsegurados.repaint();
    }
    public static void NAMisIncidentes(){
        JFrame NAMisIncidentes = new JFrame();
        NAMisIncidentes.setLocationRelativeTo(null);
        NAMisIncidentes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        NAMisIncidentes.getContentPane();
        NAMisIncidentes.setLayout(null);
        NAMisIncidentes.pack();
        NAMisIncidentes.setBounds(150, 100, 650, 450);
        NAMisIncidentes.setResizable(false);
        NAMisIncidentes.setVisible(true);
        //DatosUsuario
        JTextArea DatosU = new JTextArea();
        DatosU.setBounds(20, 10, 300, 75);
        DatosU.setVisible(true);
        DatosU.setEnabled(false);
        DatosU.setText("Nombre: " + NombreSujetoLogueado+"\n"+"Telefono: "+TelefonoSujetoLogueado+"\n"+"DPI: "+DPISujetoLogueado+"\n"+"Tipo: "+AseguradoSujetoLogueado);
        //JButton
        JButton BDetalle = new JButton("Detalle");
        BDetalle.setBounds(323, 250, 86, 15);
        BDetalle.setVisible(true);
        BDetalle.setFont(BDetalle.getFont().deriveFont(10.0f));
        BDetalle.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             
         }
        });
        JButton BPagar = new JButton("Pagar");
        BPagar.setBounds(419, 250, 86, 15);
        BPagar.setVisible(true);
        BPagar.setFont(BPagar.getFont().deriveFont(10.0f));
        BPagar.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             
         }
        });
        JButton BVolver = new JButton("Volver");
        BVolver.setBounds(515, 250, 86, 15);
        BVolver.setVisible(true);
        BVolver.setFont(BVolver.getFont().deriveFont(10.0f));
        BVolver.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             NAMisIncidentes.dispose();
             ClasePrincipal.Asegurados();
         }
        });
        String VTitulosTAMisSeguros[] = {"Código", "Rol", "Costo Real", "Pago Requerido", "Estado", "Pago"};
        //JPanel
        JPanel PanelTabla = new JPanel();
        PanelTabla.setLayout(null);
        PanelTabla.setVisible(true);
        PanelTabla.setBackground(Color.GRAY);
        PanelTabla.setBounds(20, 90, 610, 300);
        JTable Tabla = new JTable(11, 6);
        Tabla.setVisible(true);
        Tabla.setBounds(10, 50, 590, 180);
        Tabla.setEnabled(true);
        PanelTabla.add(Tabla);
        PanelTabla.add(BDetalle);
        PanelTabla.add(BPagar);
        PanelTabla.add(BVolver);
        //adds
        NAMisIncidentes.add(DatosU);;
        NAMisIncidentes.add(PanelTabla);
        NAMisIncidentes.repaint();
    }
    public static void NAMisPagos(){
        JFrame NAMisPagos = new JFrame();
        NAMisPagos.setLocationRelativeTo(null);
        NAMisPagos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        NAMisPagos.getContentPane();
        NAMisPagos.setLayout(null);
        NAMisPagos.pack();
        NAMisPagos.setBounds(150, 100, 650, 450);
        NAMisPagos.setResizable(false);
        NAMisPagos.setVisible(true);
        //DatosUsuario
        JTextArea DatosU = new JTextArea();
        DatosU.setBounds(20, 10, 300, 75);
        DatosU.setVisible(true);
        DatosU.setEnabled(false);
        DatosU.setText("Nombre: " + NombreSujetoLogueado+"\n"+"Telefono: "+TelefonoSujetoLogueado+"\n"+"DPI: "+DPISujetoLogueado+"\n"+"Tipo: "+AseguradoSujetoLogueado);
        //JButton
        JButton BVolver = new JButton("Volver");
        BVolver.setBounds(515, 250, 86, 15);
        BVolver.setVisible(true);
        BVolver.setFont(BVolver.getFont().deriveFont(10.0f));
        BVolver.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             NAMisPagos.dispose();
             ClasePrincipal.Asegurados();
         }
        });
        String VTitulosTAMisSeguros[] = {"Correlativo", "Tipo", "Total", "Fecha Inicio", "Fecha Fin", "Codigo"};
        //JPanel
        JPanel PanelTabla = new JPanel();
        PanelTabla.setLayout(null);
        PanelTabla.setVisible(true);
        PanelTabla.setBackground(Color.GRAY);
        PanelTabla.setBounds(20, 90, 610, 300);
        JTable Tabla = new JTable(11, 6);
        Tabla.setVisible(true);
        Tabla.setBounds(10, 50, 590, 180);
        Tabla.setEnabled(true);
        PanelTabla.add(Tabla);
        PanelTabla.add(BVolver);
        //adds
        NAMisPagos.add(DatosU);;
        NAMisPagos.add(PanelTabla);
        NAMisPagos.repaint();
    }
    
    //JFrames Administrador
    public static void Administradores(){
        //Inicializador del JFrame Administradores
        JFrame Administradores = new JFrame("Administradores");
        Administradores.setLocationRelativeTo(null);
        Administradores.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Administradores.getContentPane();
        Administradores.setLayout(null);
        Administradores.pack();
        Administradores.setBounds(150, 100, 555, 400);
        Administradores.setResizable(false);
        Administradores.setVisible(true);
        
        //CerrarSesion
        JButton BCerrarSesion = new JButton("Cerrar Sesión");
        BCerrarSesion.setBounds(10, 5, 120, 15);
        BCerrarSesion.setVisible(true);
        BCerrarSesion.setFont(BCerrarSesion.getFont().deriveFont(10.0f));
        BCerrarSesion.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             ClasePrincipal.MenuPrincipal();
             Administradores.dispose();
         }
        });
        
        //Solicitudes Seguro
        JLabel ISolicitudesSeguro = new JLabel();
        ISolicitudesSeguro.setBounds(20, 30, 120 , 120);
        ImageIcon ISS = new ImageIcon(new ImageIcon("src/Assets/MisSeguros.png").getImage().getScaledInstance(ISolicitudesSeguro.getWidth(), ISolicitudesSeguro.getHeight(), Image.SCALE_DEFAULT));
        ISolicitudesSeguro.setIcon(ISS);
        ISolicitudesSeguro.setVisible(true);
        ISolicitudesSeguro.setFont(ISolicitudesSeguro.getFont().deriveFont(16.0f));
        JButton BSolicitudesSeguro = new JButton("Solicitudes Seguro");
        BSolicitudesSeguro.setBounds(20, 160, 120, 15);
        BSolicitudesSeguro.setVisible(true);
        BSolicitudesSeguro.setFont(BSolicitudesSeguro.getFont().deriveFont(8.0f));
        BSolicitudesSeguro.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             ClasePrincipal.SolicitudesSeguro();
             Administradores.dispose();
         }
        });
        
        //ReportarIncidente
        JLabel IRIncidentes = new JLabel();
        IRIncidentes.setBounds(150, 30, 120 , 120);
        ImageIcon IRI = new ImageIcon(new ImageIcon("src/Assets/Incidentes.png").getImage().getScaledInstance(IRIncidentes.getWidth(), IRIncidentes.getHeight(), Image.SCALE_DEFAULT));
        IRIncidentes.setIcon(IRI);
        IRIncidentes.setVisible(true);
        IRIncidentes.setFont(IRIncidentes.getFont().deriveFont(16.0f));
        JButton BRIncidentes = new JButton("Reportar Incidentes");
        BRIncidentes.setBounds(150, 160, 120, 15);
        BRIncidentes.setVisible(true);
        BRIncidentes.setFont(BRIncidentes.getFont().deriveFont(8.0f));
        BRIncidentes.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             ClasePrincipal.ReportarIncidentes();
             Administradores.dispose();
         }
        });
        
        //ColaTaller
        JLabel ICTaller = new JLabel();
        ICTaller.setBounds(280, 30, 120 , 120);
        ImageIcon ICT = new ImageIcon(new ImageIcon("src/Assets/ColaTaller.png").getImage().getScaledInstance(ICTaller.getWidth(), ICTaller.getHeight(), Image.SCALE_DEFAULT));
        ICTaller.setIcon(ICT);
        ICTaller.setVisible(true);
        ICTaller.setFont(ICTaller.getFont().deriveFont(16.0f));
        JButton BCTaller = new JButton("Cola Taller");
        BCTaller.setBounds(280, 160, 120, 15);
        BCTaller.setVisible(true);
        BCTaller.setFont(BCTaller.getFont().deriveFont(8.0f));
        BCTaller.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             ClasePrincipal.ColaTaller();
             Administradores.dispose();
         }
        });
        //ColaClinica
        JLabel ICClinica = new JLabel();
        ICClinica.setBounds(410, 30, 120 , 120);
        ImageIcon ICC = new ImageIcon(new ImageIcon("src/Assets/Cola Clinica.png").getImage().getScaledInstance(ICClinica.getWidth(), ICClinica.getHeight(), Image.SCALE_DEFAULT));
        ICClinica.setIcon(ICC);
        ICClinica.setVisible(true);
        ICClinica.setFont(ICTaller.getFont().deriveFont(16.0f));
        JButton BCClinica = new JButton("Cola Clinica");
        BCClinica.setBounds(410, 160, 120, 15);
        BCClinica.setVisible(true);
        BCClinica.setFont(BCTaller.getFont().deriveFont(8.0f));
        BCClinica.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             JFrame g = new JFrame();
             JOptionPane.showMessageDialog(g, "No hay nadie en la clinica :)");
         }
        });
        
        //Lista Asegurados
        JLabel ILAsegurados = new JLabel();
        ILAsegurados.setBounds(20, 195, 120 , 120);
        ImageIcon ILA = new ImageIcon(new ImageIcon("src/Assets/ListaAsegurados.png").getImage().getScaledInstance(ILAsegurados.getWidth(), ILAsegurados.getHeight(), Image.SCALE_DEFAULT));
        ILAsegurados.setIcon(ILA);
        ILAsegurados.setVisible(true);
        ILAsegurados.setFont(ILAsegurados.getFont().deriveFont(16.0f));
        JButton BLAsegurados = new JButton("Lista Asegurados");
        BLAsegurados.setBounds(20, 325, 120, 15);
        BLAsegurados.setVisible(true);
        BLAsegurados.setFont(BLAsegurados.getFont().deriveFont(8.0f));
        BLAsegurados.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             ClasePrincipal.ListaAsegurados();
             Administradores.dispose();
         }
        });
        
        //ListaNoAsegurados
        JLabel ILNAsegurados = new JLabel();
        ILNAsegurados.setBounds(150, 195, 120 , 120);
        ImageIcon ILNA = new ImageIcon(new ImageIcon("src/Assets/ListaNoAsegurados.JPG").getImage().getScaledInstance(ILNAsegurados.getWidth(), ILNAsegurados.getHeight(), Image.SCALE_DEFAULT));
        ILNAsegurados.setIcon(ILNA);
        ILNAsegurados.setVisible(true);
        ILNAsegurados.setFont(ILAsegurados.getFont().deriveFont(16.0f));
        JButton BLNAsegurados = new JButton("Lista No Asegurados");
        BLNAsegurados.setBounds(150, 325, 120, 15);
        BLNAsegurados.setVisible(true);
        BLNAsegurados.setFont(BLNAsegurados.getFont().deriveFont(8.0f));
        BLNAsegurados.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             ClasePrincipal.ListaNoAsegurados();
             Administradores.dispose();
         }
        });
        
        //Reportes
        JLabel IReportes = new JLabel();
        IReportes.setBounds(280, 195, 120 , 120);
        ImageIcon IR = new ImageIcon(new ImageIcon("src/Assets/Reportes.png").getImage().getScaledInstance(IReportes.getWidth(), IReportes.getHeight(), Image.SCALE_DEFAULT));
        IReportes.setIcon(IR);
        IReportes.setVisible(true);
        IReportes.setFont(IReportes.getFont().deriveFont(16.0f));
        JButton BReportes = new JButton("Reportes");
        BReportes.setBounds(280, 325, 120, 15);
        BReportes.setVisible(true);
        BReportes.setFont(BReportes.getFont().deriveFont(8.0f));
        BReportes.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             ClasePrincipal.Reportes();
             Administradores.dispose();
         }
        });
        
        //Calendario
        
        UtilDateModel Modelo = new UtilDateModel();
        //Propiedades
        Properties p = new Properties();
        p.put("text.today","Hoy");
        p.put("text.month","Mes");
        p.put("text.year","Año");
        
        JDatePanelImpl datePanel = new JDatePanelImpl(Modelo, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
        //JPanel
        JPanel Calendario = new JPanel();
        Calendario.setLayout(null);
        Calendario.setVisible(true);
        Calendario.setBounds(410, 195, 120, 120);
        Calendario.setBackground(Color.WHITE);
        Calendario.add(datePicker);
        
        //adds
        Administradores.add(ISolicitudesSeguro);
        Administradores.add(IRIncidentes);
        Administradores.add(ICTaller);
        Administradores.add(BCerrarSesion);
        Administradores.add(BSolicitudesSeguro);
        Administradores.add(BRIncidentes);
        Administradores.add(BCTaller);
        Administradores.add(ICClinica);
        Administradores.add(BCClinica);
        Administradores.add(ILAsegurados);
        Administradores.add(BLAsegurados);
        Administradores.add(ILNAsegurados);
        Administradores.add(BLNAsegurados);
        Administradores.add(IReportes);
        Administradores.add(BReportes);
        Administradores.add(Calendario);
        Administradores.repaint();
    }
    public static void SolicitudesSeguro(){
        //Inicializador del JFrame Solicitudes Seguro
        JFrame SolicitudesS = new JFrame("Solicitudes Seguro");
        SolicitudesS.setLocationRelativeTo(null);
        SolicitudesS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SolicitudesS.getContentPane();
        SolicitudesS.setLayout(null);
        SolicitudesS.pack();
        SolicitudesS.setBounds(150, 100, 700, 300);
        SolicitudesS.setResizable(false);
        SolicitudesS.setVisible(true);
        //JPanel
        JPanel PanelTabla = new JPanel();
        PanelTabla.setLayout(null);
        PanelTabla.setVisible(true);
        PanelTabla.setBackground(Color.GRAY);
        PanelTabla.setBounds(10, 10, 680, 200);
        String Titulos[] = {"DPI","Nombre", "Teléfono", "Tipo", "Descripción", "Monto", "Poliza", "Deducible"};
        DefaultTableModel ModeloSS = new DefaultTableModel(MatrizSSPorAprovar, Titulos);
        JTable Tabla = new JTable(ModeloSS);
        Tabla.setVisible(true);
        Tabla.setBounds(5, 0, 670, 190);
        Tabla.setEnabled(true);
        Tabla.getModel();
        
        PanelTabla.add(Tabla);
        
        //JButtons
        JButton BAprovar = new JButton("Aprovar");
        BAprovar.setBounds(180, 220, 120, 15);
        BAprovar.setVisible(true);
        BAprovar.setFont(BAprovar.getFont().deriveFont(10.0f));
        BAprovar.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             int NID, NAsegurado;
             float NMonto, NPoliza, NDeducible;
             String NNombre, NTelefono, NTipo, NDescripcion, NIDTemporal, NMontoTemporal, NPolizaTemporal, NDeducibleTemporal;
             if (Tabla.getSelectedRow() != -1){
                 int FilaSeleccionadaTabla = Tabla.getSelectedRow();
                 NIDTemporal = String.valueOf(ModeloSS.getValueAt(FilaSeleccionadaTabla, 0));
                 NID = Integer.parseInt(NIDTemporal);
                 NNombre = String.valueOf(ModeloSS.getValueAt(FilaSeleccionadaTabla,1));
                 NTelefono = String.valueOf(ModeloSS.getValueAt(FilaSeleccionadaTabla,2));
                 NTipo = String.valueOf(ModeloSS.getValueAt(FilaSeleccionadaTabla,3));
                 NDescripcion = String.valueOf(ModeloSS.getValueAt(FilaSeleccionadaTabla, 4));
                 NMontoTemporal = String.valueOf(ModeloSS.getValueAt(FilaSeleccionadaTabla,5));
                 NMonto = Float.parseFloat(NMontoTemporal);
                 NPolizaTemporal = String.valueOf(ModeloSS.getValueAt(FilaSeleccionadaTabla,6));
                 NPoliza = Float.parseFloat(NPolizaTemporal);
                 NDeducibleTemporal = String.valueOf(ModeloSS.getValueAt(FilaSeleccionadaTabla,7));
                 NDeducible = Float.parseFloat(NDeducibleTemporal);
                 NAsegurado = 1;
                 ClasePrincipal.AgregarAsegurado(new Persona(NID, NNombre, NTelefono, NTipo, NDescripcion, NMonto, NPoliza, NDeducible, NAsegurado));
                 ClasePrincipal.AgregarListaAsegurado(new Persona(NID, NNombre, NTelefono, NTipo, NDescripcion, NMonto, NPoliza, NDeducible, NAsegurado));
                 //Mensaje
                 JFrame g = new JFrame();
                 JOptionPane.showMessageDialog(g, "Solicitud Aprovada ;v");
                 //BorrarLaEntrada
                 ModeloSS.removeRow(Tabla.getSelectedRow());
                 
             }
         }
        });
        
        JButton BRechazar = new JButton("Rechazar");
        BRechazar.setBounds(310, 220, 120, 15);
        BRechazar.setVisible(true);
        BRechazar.setFont(BRechazar.getFont().deriveFont(10.0f));
        BRechazar.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
            //Ver cual fila está seleccionada
            if (Tabla.getSelectedRow()!=-1){//La condición es por si no hay ninguna fila seleccionada, según la documentación el valor "-1" significa que no hay nada seleccionado
                ModeloSS.removeRow(Tabla.getSelectedRow());
            }
            //Mensaje
            JFrame g = new JFrame();
            JOptionPane.showMessageDialog(g, "Solicitud Rechazada ;v");
         }
        });
        
        JButton BCancelar = new JButton("Cancelar");
        BCancelar.setBounds(440, 220, 120, 15);
        BCancelar.setVisible(true);
        BCancelar.setFont(BCancelar.getFont().deriveFont(10.0f));
        BCancelar.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             ClasePrincipal.Administradores();
             SolicitudesS.dispose();
         }
        });
        //adds
        SolicitudesS.add(PanelTabla);
        SolicitudesS.add(BAprovar);
        SolicitudesS.add(BRechazar);
        SolicitudesS.add(BCancelar);
        SolicitudesS.repaint();
    }
    //NuevaPersona
    public static void AgregarAsegurado(Persona NuevaPersona){
            for(int i=0;i<VectorPersona.length;i++){
                if(VectorPersona[i]==null){
                    VectorPersona[i]=NuevaPersona;
                    return;
                }
            }
    }
    public static void AgregarListaAsegurado(Persona NuevaPersona){
            for(int i=0;i<VectorAseguradas.length;i++){
                if(VectorAseguradas[i]==null){
                    VectorAseguradas[i]=NuevaPersona;
                    return;
                }
            }
    }
    public static void ReportarIncidentes(){
        //Inicializador del JFrame Reportar Incidentes
        JFrame RIncidentes = new JFrame("Reportar Incidentes");
        RIncidentes.setLocationRelativeTo(null);
        RIncidentes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RIncidentes.getContentPane();
        RIncidentes.setLayout(null);
        RIncidentes.pack();
        RIncidentes.setBounds(150, 100, 450, 400);
        RIncidentes.setResizable(false);
        RIncidentes.setVisible(true);
        //DPIAsegurado
        JLabel RDPIA = new JLabel("DPI Asegurado:");
        RDPIA.setBounds(10, 5, 100, 15);
        RDPIA.setVisible(true);
        RDPIA.setFont(RDPIA.getFont().deriveFont(10.0f));
        JTextField JTFDPIA = new JTextField();
        JTFDPIA.setBounds(100, 5, 150, 15);
        JTFDPIA.setVisible(true);
        JTFDPIA.setFont(JTFDPIA.getFont().deriveFont(10.0f));
        JCheckBox AseguradoC = new JCheckBox("El asegurado es culpable");
        AseguradoC.setBounds(260, 5,200,15);
        AseguradoC.setVisible(true);
        //DPINoAsegurado
        JLabel RDPITercero = new JLabel("DPI Tercero:");
        RDPITercero.setBounds(10, 30, 100, 15);
        RDPITercero.setVisible(true);
        RDPITercero.setFont(RDPITercero.getFont().deriveFont(10.0f));
        JTextField JTFDPITercero = new JTextField();
        JTFDPITercero.setBounds(100, 30, 150, 15);
        JTFDPITercero.setVisible(true);
        JTFDPITercero.setFont(JTFDPITercero.getFont().deriveFont(10.0f));
        JCheckBox TerceroS = new JCheckBox("El tercero tiene seguro");
        TerceroS.setBounds(260, 30, 200,15);
        TerceroS.setVisible(true);
        //TipoServicios
        JLabel RTServicio = new JLabel("Tipo Servicio:");
        RTServicio.setBounds(10, 55, 100, 15);
        RTServicio.setVisible(true);
        RTServicio.setFont(RTServicio.getFont().deriveFont(10.0f));
        //ComboBoxTServicio
        String MCBXTServicio[] = new String [30];
            for(int i=0;i<VectorSSMarca.length;i++){
                if (VectorSSMecanica[i] != null){
                    MCBXTServicio[i] = VectorSSMecanica[i].getNombreActividad();
                }
            }
        JComboBox JCMBTServicio = new JComboBox(MCBXTServicio);
        JCMBTServicio.setBounds(100, 55, 150, 15);
        JCMBTServicio.setVisible(true);
        JCMBTServicio.setFont(JCMBTServicio.getFont().deriveFont(10.0f));
        //Repuestos
        JLabel RRepuestos = new JLabel("Repuestos:");
        RRepuestos.setBounds(10, 80, 100, 15);
        RRepuestos.setVisible(true);
        RRepuestos.setFont(RRepuestos.getFont().deriveFont(10.0f));
        //ComboBoxRepuestos
        String MCBXRepuesto[] = new String [30];
            for(int i=0;i<VectorSSRepuesto.length;i++){
                if (VectorSSRepuesto[i] != null){
                    MCBXRepuesto[i] = VectorSSRepuesto[i].getNombre();
                }
            }
        JComboBox JCMBRepuestos = new JComboBox(MCBXRepuesto);
        JCMBRepuestos.setBounds(100, 80, 150, 15);
        JCMBRepuestos.setVisible(true);
        JCMBRepuestos.setFont(JCMBRepuestos.getFont().deriveFont(10.0f));
        //JPanel
        JPanel PanelTabla = new JPanel();
        PanelTabla.setLayout(null);
        PanelTabla.setVisible(true);
        PanelTabla.setBackground(Color.GRAY);
        PanelTabla.setBounds(10, 105, 280, 200);
        JTable Tabla = new JTable(11, 3);
        Tabla.setVisible(true);
        Tabla.setBounds(5, 0, 270, 195);
        Tabla.setEnabled(true);
        PanelTabla.add(Tabla);
        //Agregar
        JButton BAgregar = new JButton("Agregar");
        BAgregar.setBounds(260, 80, 86, 15);
        BAgregar.setVisible(true);
        BAgregar.setFont(BAgregar.getFont().deriveFont(10.0f));
        BAgregar.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             IndexMatrizAR = IndexMatrizAR + 1;
             //ConseguirMecanica
             Object Mecanica = JCMBTServicio.getSelectedItem();
             String MecanicaSelec = Mecanica.toString();
             int IndexMecanica = 0;
             for(int i=0;i<VectorSSMecanica.length;i++){
                  if(VectorSSMecanica[i]!=null){
                      if (MecanicaSelec.equals(VectorSSMecanica[i].getNombreActividad())){
                      IndexMecanica = i;
                     }
                  }
              }
             ModeloTablaAgregarRepuestos[IndexMatrizAR][1] = VectorSSMecanica[IndexMecanica].getNombreActividad();
             ModeloTablaAgregarRepuestos[IndexMatrizAR][2] = VectorSSMecanica[IndexMecanica].getPrecio();
             IndexMatrizAR = IndexMatrizAR + 1;
             //ConseguirRepuesto
             Object Repuesto = JCMBRepuestos.getSelectedItem();
             String RepuestoSelec = Repuesto.toString();
             int IndexRepuesto = 0;
             for(int i=0;i<VectorSSRepuesto.length;i++){
                  if(VectorSSRepuesto[i]!=null){
                      if (RepuestoSelec.equals(VectorSSRepuesto[i].getNombre())){
                      IndexRepuesto = i;
                     }
                  }
              }
             ModeloTablaAgregarRepuestos[IndexMatrizAR][1] = VectorSSRepuesto[IndexRepuesto].getNombre();
             ModeloTablaAgregarRepuestos[IndexMatrizAR][2] = VectorSSRepuesto[IndexRepuesto].getPrecio();
             Tabla.repaint();
         }
        });
        
        //Total
        JLabel RTotal = new JLabel("Total:");
        RTotal.setBounds(10, 315, 100, 15);
        RTotal.setVisible(true);
        RTotal.setFont(RTotal.getFont().deriveFont(10.0f));
        JTextField JTFTotal = new JTextField();
        JTFTotal.setBounds(100, 315, 150, 15);
        JTFTotal.setVisible(true);
        JTFTotal.setFont(JTFTotal.getFont().deriveFont(10.0f));
        JTFTotal.setEditable(false);
        //ReportarIncidente
        JButton BRIncidente = new JButton("Reportar Incidente");
        BRIncidente.setBounds(5, 340, 150, 15);
        BRIncidente.setVisible(true);
        BRIncidente.setFont(BRIncidente.getFont().deriveFont(10.0f));
        BRIncidente.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             
         }
        });
        //Cancelar
        JButton BCancelar = new JButton("Cancelar");
        BCancelar.setBounds(165, 340, 86, 15);
        BCancelar.setVisible(true);
        BCancelar.setFont(BCancelar.getFont().deriveFont(10.0f));
        BCancelar.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             RIncidentes.dispose();
             ClasePrincipal.Administradores();
         }
        });
        //adds
        RIncidentes.add(RDPIA);
        RIncidentes.add(JTFDPIA);
        RIncidentes.add(AseguradoC);
        RIncidentes.add(RDPITercero);
        RIncidentes.add(JTFDPITercero);
        RIncidentes.add(TerceroS);
        RIncidentes.add(RTServicio);
        RIncidentes.add(JCMBTServicio);
        RIncidentes.add(RRepuestos);
        RIncidentes.add(JCMBRepuestos);
        RIncidentes.add(BAgregar);
        RIncidentes.add(PanelTabla);
        RIncidentes.add(RTotal);
        RIncidentes.add(JTFTotal);
        RIncidentes.add(BRIncidente);
        RIncidentes.add(BCancelar);
        RIncidentes.repaint();
    }
    public static void ColaTaller(){
        //Inicializador del JFrame Cola Taller
        JFrame CTaller = new JFrame("Cola Taller");
        CTaller.setLocationRelativeTo(null);
        CTaller.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CTaller.getContentPane();
        CTaller.setLayout(null);
        CTaller.pack();
        CTaller.setBounds(150, 100, 700, 300);
        CTaller.setResizable(false);
        CTaller.setVisible(true);
        
        //JPanel
        JPanel PanelTabla = new JPanel();
        PanelTabla.setLayout(null);
        PanelTabla.setVisible(true);
        PanelTabla.setBackground(Color.GRAY);
        PanelTabla.setBounds(10, 10, 680, 200);
        JTable Tabla = new JTable(11, 5);
        Tabla.setVisible(true);
        Tabla.setBounds(5, 0, 670, 190);
        Tabla.setEnabled(true);
        PanelTabla.add(Tabla);
        
        //JButtons
        JButton BMTerminado = new JButton("Marcar Como Terminado");
        BMTerminado.setBounds(180, 220, 140, 15);
        BMTerminado.setVisible(true);
        BMTerminado.setFont(BMTerminado.getFont().deriveFont(8.0f));
        BMTerminado.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             
             
         }
        });
                
        JButton BCancelar = new JButton("Cancelar");
        BCancelar.setBounds(340, 220, 120, 15);
        BCancelar.setVisible(true);
        BCancelar.setFont(BCancelar.getFont().deriveFont(10.0f));
        BCancelar.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             ClasePrincipal.Administradores();
             CTaller.dispose();
         }
        });
        //adds
        CTaller.add(PanelTabla);
        CTaller.add(BMTerminado);
        CTaller.add(BCancelar);
        CTaller.repaint();
    }
    public static void ListaAsegurados(){
        //Inicializador del JFrame Lista Asegurados
        JFrame LAsegurados = new JFrame("Lista Asegurados");
        LAsegurados.setLocationRelativeTo(null);
        LAsegurados.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LAsegurados.getContentPane();
        LAsegurados.setLayout(null);
        LAsegurados.pack();
        LAsegurados.setBounds(150, 100, 700, 300);
        LAsegurados.setResizable(false);
        LAsegurados.setVisible(true);
        
        //JPanel
        JPanel PanelTabla = new JPanel();
        PanelTabla.setLayout(null);
        PanelTabla.setVisible(true);
        PanelTabla.setBackground(Color.GRAY);
        PanelTabla.setBounds(10, 10, 680, 200);
        String Titulos[] = {"DPI","Nombre", "Teléfono", "Tipo", "Descripción", "Monto", "Poliza", "Deducible"};
        DefaultTableModel ModeloLA = new DefaultTableModel(VectorAseguradas,8);
        JTable Tabla = new JTable(ModeloLA);
        Tabla.setVisible(true);
        Tabla.setBounds(5, 0, 670, 190);
        Tabla.setEnabled(true);
        PanelTabla.add(Tabla);
        
        //JButtons
        JButton BCancelar = new JButton("Cancelar");
        BCancelar.setBounds(570, 220, 120, 15);
        BCancelar.setVisible(true);
        BCancelar.setFont(BCancelar.getFont().deriveFont(10.0f));
        BCancelar.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             ClasePrincipal.Administradores();
             LAsegurados.dispose();
         }
        });
        //adds
        LAsegurados.add(PanelTabla);
        LAsegurados.add(BCancelar);
        LAsegurados.repaint();
    }
    public static void ListaNoAsegurados(){
        //Inicializador del JFrame Lista Asegurados
        JFrame LNAsegurados = new JFrame("Lista No Asegurados");
        LNAsegurados.setLocationRelativeTo(null);
        LNAsegurados.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LNAsegurados.getContentPane();
        LNAsegurados.setLayout(null);
        LNAsegurados.pack();
        LNAsegurados.setBounds(150, 100, 700, 300);
        LNAsegurados.setResizable(false);
        LNAsegurados.setVisible(true);
        
        //JPanel
        JPanel PanelTabla = new JPanel();
        PanelTabla.setLayout(null);
        PanelTabla.setVisible(true);
        PanelTabla.setBackground(Color.GRAY);
        PanelTabla.setBounds(10, 10, 680, 200);
        String Titulos[] = {"DPI","Nombre", "Teléfono", "Tipo", "Descripción", "Monto", "Poliza", "Deducible"};
        DefaultTableModel ModeloLNA = new DefaultTableModel(VectorNoAseguradas, 8);
        JTable Tabla = new JTable(ModeloLNA);
        Tabla.setVisible(true);
        Tabla.setBounds(5, 0, 670, 190);
        Tabla.setEnabled(true);
        PanelTabla.add(Tabla);
        
        //JButtons
        JButton BCancelar = new JButton("Cancelar");
        BCancelar.setBounds(570, 220, 120, 15);
        BCancelar.setVisible(true);
        BCancelar.setFont(BCancelar.getFont().deriveFont(10.0f));
        BCancelar.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent Evento){
             ClasePrincipal.Administradores();
             LNAsegurados.dispose();
         }
        });
        //adds
        LNAsegurados.add(PanelTabla);
        LNAsegurados.add(BCancelar);
        LNAsegurados.repaint();
    }
    public static void Reportes(){
        
    }
}