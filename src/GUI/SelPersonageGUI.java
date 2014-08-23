package GUI;

import game.ChatFrontend;

import java.awt.*;
import java.awt.event.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import network.Chat;
import network.ClientPlayer;
import network.ClientServer;

import util.ColoursDefinitions;
import util.Constants;
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

public class SelPersonageGUI extends JFrame {

	private JFrame frame;
	
	private JButton ContinuejButton = new JButton();

	private JButton CreatejButton = new JButton();

	private JButton BuyjButton = new JButton();

	private JLabel UserjLabel = new JLabel();
    
	private JLabel BackgroundjLabel = new JLabel();

	private JTextField UserjTextField = new JTextField();

	private DefaultListModel RacesModel = new DefaultListModel();

	private JList RacesjList = new JList(RacesModel);

	private JList PointsjList = new JList();

	private JList WeaponsjList = new JList();

	private JTextField WeaponsjTextField = new JTextField();

	private JTextField PointsjTextField = new JTextField();

	private JTextField RacejTextField = new JTextField();
    
	private List lAux = new ArrayList();//Aqunh es donde voy a meter el 
    //                nombre del personaje con su tipo de raza
    private boolean JuegoLocal;
    
    ClientServer cs;
    Chat chat;

	private JButton ErasejButton = new JButton();
	

    ClientPlayer client;
    
    ByteBuffer b = ByteBuffer.allocate(Constants.BYTE_BUFFER_SIZE);
    
    public SelPersonageGUI(boolean juegoLocal) throws HeadlessException {
        try {
        	JuegoLocal = juegoLocal;
        	jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SelPersonageGUI(ClientServer css) throws HeadlessException {
        try {
        		cs = css;
        		chat = new Chat(css);
        		client = new ClientPlayer(chat.getClientPlayer().getName());
        		//nombre = chat.getClientPlayer().getName();
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
  //  public void setTipoJugador(String tipo){tipoJugador = tipo;}

  /*  public static void main(String[] args) throws HeadlessException {
        SelPersonageGUI selPersonajeGUI1 = new SelPersonageGUI();
    }*/


    private void jbInit() throws Exception {
        //this.setLocation(30, 30);
        Dimension screenRes=Toolkit.getDefaultToolkit().getScreenSize();
        int x=(screenRes.width/2)-(583/2);
        int y=(screenRes.height/2)-(300/2);
        this.setLocation(x,y);
        this.setSize(new Dimension(583, 300));
        this.getContentPane().setBackground(new Color(212, 224, 230));
        this.getContentPane().setLayout(null);
        UserjLabel.setMaximumSize(new Dimension(30, 15));
        UserjLabel.setText("Usuario:");
        UserjLabel.setFont(UserjLabel.getFont().deriveFont(Font.BOLD));
        UserjLabel.setForeground(Color.YELLOW);
        UserjLabel.setBounds(new Rectangle(27, 8, 45, 24));
        UserjTextField.setEnabled(false);
        //UserjTextField.setBorder(BorderFactory.createLineBorder(Color.black));
        UserjTextField.setBorder(BorderFactory.createLineBorder(ColoursDefinitions.TRANSPARENT));
        UserjTextField.setDisabledTextColor(Color.YELLOW);
        UserjTextField.setFont(UserjTextField.getFont().deriveFont(Font.BOLD));
        UserjTextField.setBackground(ColoursDefinitions.TRANSPARENT);
        //UserjTextField.setMargin(new Insets(0,56,0,0));
        UserjTextField.setEditable(false);
        UserjTextField.setScrollOffset(0);
        UserjTextField.setBounds(new Rectangle(80, 8, 121, 24));
        ContinuejButton.setBounds(new Rectangle(320, 231, 110, 25));
        ContinuejButton.setText("Siguiente");
        ContinuejButton.setBorder(BorderFactory.createRaisedBevelBorder());
        ContinuejButton.setBackground(new Color(195, 206, 212));
        ContinuejButton.setMnemonic(KeyEvent.VK_S);
        ContinuejButton
                .addActionListener(new SelPersonageGUI_ContinuejButton_actionAdapter(
                        this));
        CreatejButton.setBounds(new Rectangle(27, 231, 170, 25));
        CreatejButton.setText("Crear Nuevo Personaje");
        CreatejButton.setBorder(BorderFactory.createRaisedBevelBorder());
        CreatejButton.setBackground(new Color(195, 206, 212));
        CreatejButton.setMnemonic(KeyEvent.VK_N);
        CreatejButton
                .addActionListener(new SelPersonageGUI_CreatejButton_actionAdapter(
                        this));
        BuyjButton.setBounds(new Rectangle(440, 231, 112, 25));
        BuyjButton.setText("Comprar");
        BuyjButton.setBorder(BorderFactory.createRaisedBevelBorder());
        BuyjButton.setBackground(new Color(195, 206, 212));
        BuyjButton.setMnemonic(KeyEvent.VK_C);
        BuyjButton
                .addActionListener(new SelPersonageGUI_BuyjButton_actionAdapter(
                        this));
        BuyjButton.setEnabled(false);
        ErasejButton.setBounds(new Rectangle(207, 231, 103, 25));
        ErasejButton.setText("Borrar");
        ErasejButton.setBorder(BorderFactory.createRaisedBevelBorder());
        ErasejButton.setBackground(new Color(195, 206, 212));
        ErasejButton.setMnemonic(KeyEvent.VK_B);
        ErasejButton.setEnabled(false);
        ErasejButton
                .addActionListener(new SelPersonageGUI_ErasejButton_actionAdapter(
                        this));
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Seleccionar Personaje");
        RacesjList.setBackground(Color.white);
        RacesjList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        RacesjList.setBorder(BorderFactory.createLineBorder(Color.black));
        RacesjList.setBounds(new Rectangle(27, 66, 130, 150));
        RacesjList.addListSelectionListener(new SelPersonageGUI_RacesjList_listSelectionListener(this));
        PointsjList.setBackground(Color.white);
        PointsjList.setBorder(BorderFactory.createLineBorder(Color.black));
        PointsjList.setBounds(new Rectangle(156, 66, 86, 150));
        WeaponsjList.setBackground(Color.white);
        WeaponsjList.setBorder(BorderFactory.createLineBorder(Color.black));
        WeaponsjList.setBounds(new Rectangle(241, 66, 312, 150));
        WeaponsjTextField.setBackground(Color.darkGray);
        WeaponsjTextField.setEnabled(false);
        WeaponsjTextField.setForeground(Color.white);
        WeaponsjTextField
                .setBorder(BorderFactory.createLineBorder(Color.black));
        WeaponsjTextField.setDisabledTextColor(Color.white);
        WeaponsjTextField.setEditable(false);
        WeaponsjTextField.setText("    Armas que posee");
        WeaponsjTextField.setBounds(new Rectangle(241, 42, 312, 25));
        PointsjTextField.setBackground(Color.darkGray);
        PointsjTextField.setEnabled(false);
        PointsjTextField.setForeground(Color.white);
        PointsjTextField.setBorder(BorderFactory.createLineBorder(Color.black));
        PointsjTextField.setDisabledTextColor(Color.white);
        PointsjTextField.setEditable(false);
        PointsjTextField.setText("    Puntos");
        PointsjTextField.setBounds(new Rectangle(156, 42, 86, 25));
        RacejTextField.setBackground(Color.darkGray);
        RacejTextField.setEnabled(false);
        RacejTextField.setFont(new java.awt.Font("Dialog", 0, 11));
        RacejTextField.setForeground(Color.white);
        RacejTextField.setBorder(BorderFactory.createLineBorder(Color.black));
        RacejTextField.setDisabledTextColor(Color.white);
        RacejTextField.setEditable(false);
        RacejTextField.setText("   Jugador");
        RacejTextField.setBounds(new Rectangle(27, 42, 130, 25));
        BackgroundjLabel.setIcon(new ImageIcon(Tools.loadBufferedImage("src"+File.separator+"GUI"+File.separator+"Images"+File.separator+"stars.png")));
        BackgroundjLabel.setText("");
        BackgroundjLabel.setBounds(new Rectangle(0, 0, 945, 565));
        if (!JuegoLocal){
        	this.getContentPane().add(UserjLabel, null);
        	this.getContentPane().add(UserjTextField, null);
        }
        this.getContentPane().add(CreatejButton, null);
        this.getContentPane().add(ContinuejButton, null);
        this.getContentPane().add(ErasejButton, null);
        this.getContentPane().add(BuyjButton, null);
        this.getContentPane().add(RacesjList, null);
        this.getContentPane().add(RacejTextField, null);
        this.getContentPane().add(PointsjList, null);
        this.getContentPane().add(PointsjTextField, null);
        this.getContentPane().add(WeaponsjTextField, null);
        this.getContentPane().add(WeaponsjList, null);
        this.getContentPane().add(BackgroundjLabel);
    }
    
    public ClientPlayer getClientPlayer(){return client;}


    void CreatejButton_actionPerformed(ActionEvent e) {
        CreatePersonageGUI createPersonageGUI = new CreatePersonageGUI(this);
        createPersonageGUI.setVisible(true);
    }


    void ContinuejButton_actionPerformed(ActionEvent e) {
    	if (this.isJuegoLocal()) {
    		if (this.lAux.size()<2){
    			JOptionPane.showMessageDialog(frame,
                        "Necesitas al menos dos jugadores para poder jugar en Local",
                        "Actor Selection Error", JOptionPane.INFORMATION_MESSAGE);
    		}
    		else{
				CreateMatchGUI createMatch = new CreateMatchGUI(lAux, !this
						.isJuegoLocal());
				createMatch.setVisible(true);
				this.dispose();
    		}
    	}
    	else {
    		if (this.lAux.size()<1){
    			JOptionPane.showMessageDialog(frame,
                        "Necesitas al menos un jugador para poder jugar en Red",
                        "Actor Selection Error", JOptionPane.INFORMATION_MESSAGE);
        	}
    		else {
				chat.getClientPlayer().setPlayerType(client.getPlayerType());
				RoomsGUI roomsGUI = new RoomsGUI(chat);
				roomsGUI.setVisible(true);
				this.dispose();
				roomsGUI.listen();
			}
    	}
    }
    
    void ErasejButton_actionPerformed(ActionEvent e) {
    	this.getLAux().remove(RacesjList.getSelectedIndex());
    	RacesModel.remove(RacesjList.getSelectedIndex());
        this.ErasejButton.setEnabled(false);
        //this.getLAux().remove(RacesjList.getSelectedIndex());
    }
    
    void RacesjList_valueChanged(ListSelectionEvent e) {
       ErasejButton.setEnabled(true); 
    }


    void BuyjButton_actionPerformed(ActionEvent e) {
        BuyGUI buyGUI = new BuyGUI();
        buyGUI.setVisible(true);
    }

	/**
	 * @return Returns the racesModel.
	 */
	public DefaultListModel getRacesModel() {
		return RacesModel;
	}
	/**
	 * @return Returns the userjTextField.
	 */
	public JTextField getUserjTextField() {
		return UserjTextField;
	}
	/**
	 * @return Returns the lAux.
	 */
	public List getLAux() {
		return lAux;
	}
	/**
	 * @param aux The lAux to set.
	 */
	public void setLAux(List aux) {
		lAux = aux;
	}
	/**
	 * @return Returns the juegoLocal.
	 */
	public boolean isJuegoLocal() {
		return JuegoLocal;
	}
	/**
	 * @param juegoLocal The juegoLocal to set.
	 */
	public void setJuegoLocal(boolean juegoLocal) {
		JuegoLocal = juegoLocal;
	}
}


class SelPersonageGUI_CreatejButton_actionAdapter implements
        java.awt.event.ActionListener {

    SelPersonageGUI adaptee;


    SelPersonageGUI_CreatejButton_actionAdapter(SelPersonageGUI adaptee) {
        this.adaptee = adaptee;
    }


    public void actionPerformed(ActionEvent e) {
        adaptee.CreatejButton_actionPerformed(e);
    }
}
class SelPersonageGUI_ContinuejButton_actionAdapter implements
        java.awt.event.ActionListener {

	private SelPersonageGUI adaptee;


    SelPersonageGUI_ContinuejButton_actionAdapter(SelPersonageGUI adaptee) {
        this.adaptee = adaptee;
    }


    public void actionPerformed(ActionEvent e) {
        adaptee.ContinuejButton_actionPerformed(e);
    }
}
class SelPersonageGUI_ErasejButton_actionAdapter implements
		java.awt.event.ActionListener {

	private SelPersonageGUI adaptee;

	SelPersonageGUI_ErasejButton_actionAdapter(SelPersonageGUI adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.ErasejButton_actionPerformed(e);
	}
}
class SelPersonageGUI_BuyjButton_actionAdapter implements
        java.awt.event.ActionListener {

	private SelPersonageGUI adaptee;


    SelPersonageGUI_BuyjButton_actionAdapter(SelPersonageGUI adaptee) {
        this.adaptee = adaptee;
    }


    public void actionPerformed(ActionEvent e) {
        adaptee.BuyjButton_actionPerformed(e);
    }
}
class SelPersonageGUI_RacesjList_listSelectionListener implements
        ListSelectionListener {

    private SelPersonageGUI adaptee;


    SelPersonageGUI_RacesjList_listSelectionListener(SelPersonageGUI adaptee) {
        this.adaptee = adaptee;
    }


    public void valueChanged(ListSelectionEvent e) {
        adaptee.RacesjList_valueChanged(e);
    }
}