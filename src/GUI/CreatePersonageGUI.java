package GUI;

import game.actors.ActorPlayerBiomorph;
import game.actors.ActorPlayerEleo;
import game.actors.ActorPlayerG_Marine;
import game.actors.ActorPlayerNinfa;
import game.actors.ActorPlayerScrapper;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

import network.ClientPlayer;

import util.ColoursDefinitions;
import util.Constants;
import util.IdAndRace;
import util.Tools;

/**
 * <p>
 * Tnhtulo:
 * </p>
 * <p>
 * Descripcinhn:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Empresa: Bang-Bang_3005
 * </p>
 * 
 * @author Germnhn Martnhnez Sancho
 * @version 1.0
 */

public class CreatePersonageGUI extends JFrame {

    private String raceName = "";

    private JButton CanceljButton = new JButton();

    private  JButton OkjButton = new JButton();
    
    private JLabel BackgroundjLabel = new JLabel();
    
    private  JLabel NamejLabel = new JLabel();
    
    private JTextField NamejTextField = new JTextField();

    private JTextArea DescriptionjTextArea = new JTextArea();

    private JLabel DescriptionjLabel = new JLabel();

    private  JLabel RacejLabel = new JLabel();

    private String[] racesStrings = { "Scrapper", "Biomorph", "Eleo", "Ninfa",
            "G-Marine" };

    private JComboBox racesjComboBox = new JComboBox(racesStrings);

    private JLabel LogojLabel = new JLabel();
    
    private String playerName;
    
    private int typeRace=0;
    
    private boolean JuegoLocal;

    SelPersonageGUI selGUI;// = new SelPersonageGUI(nombre);


    public CreatePersonageGUI(SelPersonageGUI gui) throws HeadlessException {
        try {
            selGUI = gui;
            JuegoLocal  = selGUI.isJuegoLocal();
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateText(String name) {
    	LogojLabel.setIcon(new ImageIcon(Tools.loadBufferedImage(
    			"src"+File.separator+"GUI"+File.separator+"Images"+File.separator+ name + ".png")));
        if (name.equals("Scrapper")) {
        	typeRace = 1;
        	RacejLabel.setIcon(new ImageIcon(Tools.loadBufferedImage("src"+File.separator+"GUI"+File.separator+"Images"+File.separator+"ScraperGUI.png")));
        	DescriptionjTextArea
                    .setText("    Son robots de aspecto simpnhtico debido a su aspecto chatarrero"
                            + " y las modificaciones y tunings. Dinastnha de robots expertos "
                            + "en mecnhnica y de origen dudoso, no son tan amigables como "
                            + "parecen. Siempre en busca de nueva chatarra con la que seguir"
                            + " modificando su aspecto no dudaron en aniquilar a sus "
                            + "parientes lejanos los Transformers y a su lnhder nhptimus "
                            + "Prime para conseguir sus preciadas aleaciones. Hoy en "
                            + "dnha continnhan vagando por el espacio exterior."
                            + "\n\n"
                            + "    En su bnhsqueda de nueva chatarra nhtil que les ayudara a confeccionar"
                            + " sus fnhsicos, se toparon de manera inesperada con una raza para ellos"
                            + " todavnha desconocida, los GMarines. Los GMarines al ver que un pequenho "
                            + "destacamento de estos robots deambulaba por sus alrededores buscando"
                            + " chatarra sin su consentimiento, decidieron acabar con ellos "
                            + "reseteando sus sistemas principales. Esto causnh la inmediata "
                            + "alerta de la dinastnha de robots que de acuerdo con su cnhdigo "
                            + "marcial del honor, les obligaba a restaurar ahora mismo el "
                            + "honor mancillado de sus canhdos. Que tengan cuidado esos G-Marines...");
        } else if (name.equals("Biomorph")) {
        	typeRace = 2;
        	RacejLabel.setIcon(new ImageIcon(Tools.loadBufferedImage("src"+File.separator+"GUI"+File.separator+"Images"+File.separator+"BioMorphGUI.png")));
            DescriptionjTextArea
                    .setText("    Su origen es incierto, los indicios indican que son "
                            + "seres muy apegados a su antiguo planeta Ignix, del que "
                            + "fueron aislados para su posterior estudio por parte de "
                            + "los Eleos. nhstos los mantienen en un hnhbitat controlado"
                            + " para evitar su diseminacinhn por todo el Cosmos."
                            +

                            "\n\n    Su aparicinhn en el conflicto es por un error mnhs de los"
                            + " torpes GMarines. Bombardearon el laboratorio central de los"
                            + " Eleos y por error abrieron las jaulas de los BioMorph, "
                            + "desatando al conjunto de criaturas y bestias mnhs temido de "
                            + "la Galaxia. Ahora no hay lugar donde esconderse de esta plaga.");
        } else if (name.equals("Eleo")) {
        	typeRace = 5;
        	RacejLabel.setIcon(new ImageIcon(Tools.loadBufferedImage("src"+File.separator+"GUI"+File.separator+"Images"+File.separator+"EleoGUI.png")));
            DescriptionjTextArea
                    .setText("    Estos pequenhos especnhmenes, aunque de apariencia en "
                            + "principio algo endeble son rnhgidamente educados y entrenados"
                            + " en el autocontrol y respeto por el equilibrio planetario. "
                            + "Organizados en una perfecta estructura jernhrquica, su fin "
                            + "nhltimo es el de conseguir la perfecta armonnha en toda la galaxia."
                            + " Dominadores de la raza de los biomorphs, los nhleos ejercen de"
                            + " guardianes de tan despreciables parnhsitos, pues una plaga de "
                            + "tales engendros supondrnha el caos y la amenaza de toda vida "
                            + "conocida hasta ahora. Amantes del orden, la disciplina y "
                            + "reticentes a cualquier tipo de conflicto, sin embargo poseen "
                            + "un gran sentido de la justicia y que harnhn prevalecer en caso"
                            + " de cualquier tipo de agresinhn."
                            +

                            "\n\n    Su aparicinhn en el conflicto como no podnha ser de otra forma"
                            + " fue inducida y provocada. Aunque todavnha turbios los motivos del"
                            + " saqueo de los G-Marines al laboratorio central de los nhleos, lo"
                            + " cierto es que nada salinh como pronosticaron. Por las prisas o "
                            + "tal vez por la irracionalidad de sus actos, los G-Marines "
                            + "destruyeron la puerta principal del principal campo de contencinhn"
                            + " de los Biomorphs, que no tardaron en escapar durante el desorden"
                            + ". Reunidos en gabinete de crisis los principales mandatarios nhleos,"
                            + " decidieron restaurar la armonnha planetaria dando caza a los "
                            + "biomorphs ahora liberados y aplicando la justicia a la "
                            + "inconsciente raza de los GMarines.");
        } else if (name.equals("Ninfa")) {
        	typeRace = 4;
        	RacejLabel.setIcon(new ImageIcon(Tools.loadBufferedImage("src"+File.separator+"GUI"+File.separator+"Images"+File.separator+"NinfaGUI.png"))); 
            DescriptionjTextArea
                    .setText("    Las Ninfas de la Alegrnha, son las sacerdotisas guerreras designadas"
                            + " por la diosa de la alegrnha, MariJuana, para proteger su templo y la"
                            + " Plantacinhn Sagrada, una plantacinhn de marihuana mnhgicamente inagotable,"
                            + " a la que, de vez en cuando, le sustraen unas muestras para su cata y"
                            + " comprobacinhn."
                            + "\n\n"
                            + "    Las muy \"descuidadas\" salieron a comprar papelillos, y se dejaron "
                            + "la puerta del garaje del templo abierta, cuando regresaron, unos "
                            + "apestosos humanos amanerados habnhan desvalijado la choza, y lo mnhs "
                            + "importante: nhnhnhse habnhan llevado la Plantacinhn Sagrada!!! Los humanos"
                            + " no tardaron mucho en pasar toda la marihuana. Ahora su misinhn es "
                            + "recuperarla, e impedir que todos los seres de la galaxia que pillaron"
                            + " a los humanos lleguen a fumarse la marnha sagrada, un poderoso arma,"
                            + " que en manos irresponsables podrnha llegar a provocar el caos en la"
                            + " madre naturaleza.");
        } else if (name.equals("G-Marine")) {
        	typeRace = 3;
        	RacejLabel.setIcon(new ImageIcon(Tools.loadBufferedImage("src"+File.separator+"GUI"+File.separator+"Images"+File.separator+"GMarineGUI.png")));
        	DescriptionjTextArea
                    .setText("    Compuesta por los peores criminales exiliados de la tierra, los "
                            + "GMarine formaron la calanha espacial galnhctica durante 10 anhos. "
                            + "Ahora y teniendo en cuenta que no hay mujeres entre ellos se han "
                            + "hecho un poco mnhs alegres, un poco mnhs sueltos, en definitivanhun "
                            + "poco mnhs LOCAS! Son empleados de la Union Space Agency, y viajan"
                            + " por las galaxias buscando las materias primas necesarias para "
                            + "mantener sus constantes guerras. nhsto implica invadir planetas "
                            + "soberanos, anilquilando cualquier foco de resistencia."
                            + "\n\n"
                            + "    Su llegada al sistema de los nhleos es debido a la existencia "
                            + "en dicho sistema de vetas casi inagotables de mineral. Siguiendo el "
                            + "procedimiento, bombardean los planetas que van encontrando antes de"
                            + " aterrizar. Lo que no saben, es que hay nhalgonh espernhndoles...");
        }
    }
    
    private void jbInit() throws Exception {
        this.setLocation(80, 80);
        this.setSize(new Dimension(670, 520));
        this.getContentPane().setBackground(new Color(212, 224, 230));
        this.setForeground(Color.black);
        this.setResizable(false);
        this.setTitle("Crear Personaje");
        this.getContentPane().setLayout(null);
        
        CanceljButton.setBounds(new Rectangle(52, 380, 120, 25));
        CanceljButton.setText("Cancelar");
        CanceljButton.setBorder(BorderFactory.createRaisedBevelBorder());
        CanceljButton.setBackground(new Color(195, 206, 212));
        CanceljButton.setMnemonic(KeyEvent.VK_C);
        CanceljButton
                .addActionListener(new CreatePersonageGUI_CanceljButton_actionAdapter(
                        this));
        OkjButton.setBounds(new Rectangle(52, 330, 120, 25));
        OkjButton.setText("Aceptar");
        OkjButton.setBorder(BorderFactory.createRaisedBevelBorder());
        OkjButton.setBackground(new Color(195, 206, 212));
        OkjButton
                .addActionListener(new CreatePersonageGUI_OkjButton_actionAdapter(
                        this));
        OkjButton.setMnemonic(KeyEvent.VK_A);
        
        DescriptionjTextArea.setBackground(Color.white);
        DescriptionjTextArea.setFont(new java.awt.Font("MonoSpaced", 0, 12));
        DescriptionjTextArea.setForeground(Color.black);
        DescriptionjTextArea.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        DescriptionjTextArea.setDisabledTextColor(Color.gray);
        DescriptionjTextArea.setLineWrap(true);
        DescriptionjTextArea.setWrapStyleWord(true);
        DescriptionjTextArea.enable(true);
        DescriptionjTextArea.setEditable(false);
        //DescriptionjTextArea.setBounds(new Rectangle(175, 70, 429, 200));
        
        
        JScrollPane areaScrollPane = new JScrollPane(DescriptionjTextArea);
        areaScrollPane.setBounds(new Rectangle(220, 40, 429, 200));
        areaScrollPane.setPreferredSize(new Dimension(429, 429));
        
        DescriptionjLabel.setForeground(Color.YELLOW);
        DescriptionjLabel.setFont(DescriptionjLabel.getFont().deriveFont(Font.BOLD));
        DescriptionjLabel.setText("Descripcinhn de la raza");
        DescriptionjLabel.setBounds(new Rectangle(220, 15, 134, 26));
        
        NamejLabel.setForeground(Color.yellow);
        NamejLabel.setFont(DescriptionjLabel.getFont().deriveFont(Font.BOLD));
        NamejLabel.setText("Nombre");
        NamejLabel.setBounds(new Rectangle(19, 15, 100, 26));
      
        NamejTextField.setText("");
        NamejTextField.setBounds(new Rectangle(80, 20, 120, 20));
        NamejTextField.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
      
        
        RacejLabel.setText("");
        RacejLabel.setBounds(new Rectangle(235, 270, 400, 200));
        RacejLabel.setIcon(new ImageIcon(Tools.loadBufferedImage("src"+File.separator+"GUI"+File.separator+"Images"+File.separator+"EleoGUI.png")));
        
        racesjComboBox.setBackground(SystemColor.activeCaptionBorder);
        racesjComboBox.setBounds(new Rectangle(49, 70, 127, 22));
        racesjComboBox.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        racesjComboBox
                .addActionListener(new CreatePersonageGUI_racesjComboBox_actionAdapter(
                        this));
        LogojLabel.setMaximumSize(new Dimension(34, 14));
        LogojLabel.setText("");
        LogojLabel.setBounds(new Rectangle(60, 98, 102, 102));
        LogojLabel.setIcon(new ImageIcon(Tools.loadBufferedImage("src"+File.separator+"GUI"+File.separator+"Images"+File.separator+"Eleo.png")));
        LogojLabel.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        
        racesjComboBox.setSelectedIndex(2);
        
        BackgroundjLabel.setIcon(new ImageIcon(Tools.loadBufferedImage("src"+File.separator+"GUI"+File.separator+"Images"+File.separator+"stars.png")));
        BackgroundjLabel.setText("");
        BackgroundjLabel.setBounds(new Rectangle(0, 0, 945, 565));
        
        this.getContentPane().add(RacejLabel, null);
        this.getContentPane().add(racesjComboBox, null);
        this.getContentPane().add(LogojLabel, null);
        this.getContentPane().add(DescriptionjLabel, null);
        this.getContentPane().add(areaScrollPane);
        //this.getContentPane().add(DescriptionjTextArea, null);
        this.getContentPane().add(OkjButton, null);
        this.getContentPane().add(CanceljButton, null);
        this.getContentPane().add(NamejLabel,null);
        this.getContentPane().add(NamejTextField,null);
        this.getContentPane().add(BackgroundjLabel);
    }


    void CanceljButton_actionPerformed(ActionEvent e) {
        this.dispose();
    }


    void OkjButton_actionPerformed(ActionEvent e) {
       
    	if (!JuegoLocal){
    		selGUI.getClientPlayer().setPlayerType(raceName);
    	}
    	playerName=this.NamejTextField.getText();
    	this.dispose();
        selGUI.getRacesModel().addElement(playerName+" ("+raceName+")");
                
        selGUI.getLAux().add(new ClientPlayer(playerName,raceName));
        
    }


    void racesjComboBox_actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        raceName = (String) cb.getSelectedItem();
        updateText(raceName);
    }
}


class CreatePersonageGUI_CanceljButton_actionAdapter implements
        java.awt.event.ActionListener {

    CreatePersonageGUI adaptee;


    CreatePersonageGUI_CanceljButton_actionAdapter(CreatePersonageGUI adaptee) {
        this.adaptee = adaptee;
    }


    public void actionPerformed(ActionEvent e) {
        adaptee.CanceljButton_actionPerformed(e);
    }
}


class CreatePersonageGUI_OkjButton_actionAdapter implements
        java.awt.event.ActionListener {

    CreatePersonageGUI adaptee;


    CreatePersonageGUI_OkjButton_actionAdapter(CreatePersonageGUI adaptee) {
        this.adaptee = adaptee;
    }


    public void actionPerformed(ActionEvent e) {
        adaptee.OkjButton_actionPerformed(e);
    }
}


class CreatePersonageGUI_racesjComboBox_actionAdapter implements
        java.awt.event.ActionListener {

    CreatePersonageGUI adaptee;


    CreatePersonageGUI_racesjComboBox_actionAdapter(CreatePersonageGUI adaptee) {
        this.adaptee = adaptee;
    }


    public void actionPerformed(ActionEvent e) {
        adaptee.racesjComboBox_actionPerformed(e);
    }
}