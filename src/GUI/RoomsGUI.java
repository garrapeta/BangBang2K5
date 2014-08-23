package GUI;

import game.GameEngine;
import game.Screen;
import game.stages.Stage;
import game.stages.StageEleuron;
import game.stages.StageNodrubaz;
import game.stages.StageOcb5;
import game.stages.StageSilicosis;
import game.stages.StageTest;

import java.awt.*;
import java.awt.event.*;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.File;

import javax.swing.*;
import javax.swing.border.Border;

import network.Chat;
import network.ClientPlayer;

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

public class RoomsGUI extends JFrame {

    JFrame frame;
    
    RoomsGUI room = this;

    JButton CreatejButton = new JButton();

    JButton UnitejButton = new JButton();

    JButton QuitjButton = new JButton();
    
   // JButton RefreshjButton = new JButton();

    JTextArea Room1 = new JTextArea();

    JTextArea Room2 = new JTextArea();

    JTextArea Room3 = new JTextArea();

    JTextArea Room4 = new JTextArea();

    JTextArea Room5 = new JTextArea();

    JTextArea Room6 = new JTextArea();

    JTextArea Room7 = new JTextArea();

    JTextArea Room8 = new JTextArea();

    JTextArea Room9 = new JTextArea();

    JTextArea Room10 = new JTextArea();
    
    //Ponlo a menos uno (-1) porque la numeracion de nuestras salas va de 0 a 9
    int SelectedRoom = -1;
    int escenario=-1;
   // int SelectedRoom = 0;
    
    JLabel BackgroundjLabel = new JLabel();

    private Border normalBorder;

    private Border selectedBorder;

    private JTextArea RoomActivated;
    CreateMatchGUI createMatchGUI;
    
    boolean bo=false;
    boolean unirButton = false;
    Chat chat;
    public boolean esperando;
    ByteBuffer recibo;
    CharsetDecoder descodificador;
    StringTokenizer separador;
    List jugadores = new ArrayList();
   /* 
    private int StateActivated=0;//Se refiere al estado de la sala seleccionada.
    //0:Vacnha; 1:Ocupada y 2:Llena   lo uso para los enables de lo botones crear y unirse
*/

    public RoomsGUI(Chat c) throws HeadlessException {
        try {
        	chat =c;
    		descodificador = Charset.forName("ISO-8859-1").newDecoder();
    		recibo = ByteBuffer.allocate(Constants.BYTE_BUFFER_SIZE_LARGE);
        	jbInit();
        	 recibo = chat.makePeticionSala();	
    		 chat.sendPeticionSalas(recibo);
    		 chat.setRooms(this);
    //    	listen(this);
        		         
        } catch (Exception e) {
            e.printStackTrace();
        }
       // listen();
       
    }

    
    public void drawEmpty(int room2) {
    	int room=room2+1;
		switch (room2) {
		case 0:
			Room1.setText("[Sala" + room +" Vacnha]");
			Room1.setBackground(Color.WHITE);
			break;
		case 1:
			Room2.setText("[Sala" + room +" Vacnha]");
			Room2.setBackground(Color.WHITE);
			break;
		case 2:
			Room3.setText("[Sala" + room +" Vacnha]");
			Room3.setBackground(Color.WHITE);
			break;
		case 3:
			Room4.setText("[Sala" + room +" Vacnha]");
			Room4.setBackground(Color.WHITE);
			break;
		case 4:
			Room5.setText("[Sala" + room +" Vacnha]");
			Room5.setBackground(Color.WHITE);
			break;
		case 5:
			Room6.setText("[Sala" + room +" Vacnha]");
			Room6.setBackground(Color.WHITE);
			break;
		case 6:
			Room7.setText("[Sala" + room +" Vacnha]");
			Room7.setBackground(Color.WHITE);
			break;
		case 7:
			Room8.setText("[Sala" + room +" Vacnha]");
			Room8.setBackground(Color.WHITE);
			break;
		case 8:
			Room9.setText("[Sala" + room +" Vacnha]");
			Room9.setBackground(Color.WHITE);
			break;
		case 9:
			Room10.setText("[Sala" + room +" Vacnha]");
			Room10.setBackground(Color.WHITE);
			break;
		default:
			break;
		}
	}
    public void drawRoom(int room2, int estado, int numplayers, String players){
    	int room=room2+1;
    	players.trim();
    	switch (room2){
    		case 0:
    			if(estado==1 || estado == 3){
    				Room1.setText("[Sala" + room +" Llena] Nnhmero de jugadores: " +
    						(new Integer(numplayers)).toString() + ".\n Jugadores:" + players);
    				Room1.setBackground(Color.RED);
    			}
    			else{
    				Room1.setText("[Sala" + room +" Ocupada] Nnhmero de jugadores: " +
    						(new Integer(numplayers)).toString() + ".\n Jugadores:" + players);
    				Room1.setBackground(Color.GREEN);
    			}
    		break;
    		case 1:
    			if(estado==1 || estado == 3){
    				Room2.setText("[Sala" + room +" Llena] Nnhmero de jugadores: " +
    						(new Integer(numplayers)).toString() + ".\n Jugadores:" + players);
    				Room2.setBackground(Color.RED);
    			}
    			else{
    				Room2.setText("[Sala" + room +" Ocupada] Nnhmero de jugadores: " +
    						(new Integer(numplayers)).toString() + ".\n Jugadores:" + players);
    				Room2.setBackground(Color.GREEN);
    			}
    		break;
    		case 2:
    			if(estado==1 || estado == 3){
    				Room3.setText("[Sala" + room +" Llena] Nnhmero de jugadores: " +
    						(new Integer(numplayers)).toString() + ".\n Jugadores:" + players);
    				Room3.setBackground(Color.RED);
    			}
    			else{
    				Room3.setText("[Sala" + room +" Ocupada] Nnhmero de jugadores: " +
    						(new Integer(numplayers)).toString() + ".\n Jugadores:" + players);
    				Room3.setBackground(Color.GREEN);
    			}
    		break;
    		case 3:
    			if(estado==1 || estado == 3){
    				Room4.setText("[Sala" + room +" Llena] Nnhmero de jugadores: " +
    						(new Integer(numplayers)).toString() + ".\n Jugadores:" + players);
    				Room4.setBackground(Color.RED);
    			}
    			else{
    				Room4.setText("[Sala" + room +" Ocupada] Nnhmero de jugadores: " +
    						(new Integer(numplayers)).toString() + ".\n Jugadores:" + players);
    				Room4.setBackground(Color.GREEN);
    			}
    		break;
    		case 4:
    			if(estado==1 || estado == 3){
    				Room5.setText("[Sala" + room +" Llena] Nnhmero de jugadores: " +
    						(new Integer(numplayers)).toString() + ".\n Jugadores:" + players);
    				Room5.setBackground(Color.RED);
    			}
    			else{
    				Room5.setText("[Sala" + room +" Ocupada] Nnhmero de jugadores: " +
    						(new Integer(numplayers)).toString() + ".\n Jugadores:" + players);
    				Room5.setBackground(Color.GREEN);
    			}
    		break;
    		case 5:
    			if(estado==1 || estado == 3){
    				Room6.setText("[Sala" + room +" Llena] Nnhmero de jugadores: " +
    						(new Integer(numplayers)).toString() + ".\n Jugadores:" + players);
    				Room6.setBackground(Color.RED);
    			}
    			else{
    				Room6.setText("[Sala" + room +" Ocupada] Nnhmero de jugadores: " +
    						(new Integer(numplayers)).toString() + ".\n Jugadores:" + players);
    				Room6.setBackground(Color.GREEN);
    			}
    		break;
    		case 6:
    			if(estado==1 || estado == 3){
    				Room7.setText("[Sala" + room +" Llena] Nnhmero de jugadores: " +
    						(new Integer(numplayers)).toString() + ".\n Jugadores:" + players);
    				Room7.setBackground(Color.RED);
    			}
    			else{
    				Room7.setText("[Sala Ocupada] Nnhmero de jugadores: " +
    						(new Integer(numplayers)).toString() + ".\n Jugadores:" + players);
    				Room7.setBackground(Color.GREEN);
    			}
    		break;
    		case 7:
    			if(estado==1 || estado == 3){
    				Room8.setText("[Sala" + room +" Llena] Nnhmero de jugadores: " +
    						(new Integer(numplayers)).toString() + ".\n Jugadores:" + players);
    				Room8.setBackground(Color.RED);
    			}
    			else{
    				Room8.setText("[Sala" + room +" Ocupada] Nnhmero de jugadores: " +
    						(new Integer(numplayers)).toString() + ".\n Jugadores:" + players);
    				Room8.setBackground(Color.GREEN);
    			}
    		break;
    		case 8:
    			if(estado==1 || estado == 3){
    				Room9.setText("[Sala" + room +" Llena] Nnhmero de jugadores: " +
    						(new Integer(numplayers)).toString() + ".\n Jugadores:" + players);
    				Room9.setBackground(Color.RED);
    			}
    			else{
    				Room9.setText("[Sala" + room +" Ocupada] Nnhmero de jugadores: " +
    						(new Integer(numplayers)).toString() + ".\n Jugadores:" + players);
    				Room9.setBackground(Color.GREEN);
    			}
    		break;
    		case 9:
    			if(estado==1 || estado == 3){
    				Room10.setText("[Sala" + room +" Llena] Nnhmero de jugadores: " +
    						(new Integer(numplayers)).toString() + ".\n Jugadores:" + players);
    				Room10.setBackground(Color.RED);
    			}
    			else{
    				Room10.setText("[Sala" + room +" Ocupada] Nnhmero de jugadores: " +
    						(new Integer(numplayers)).toString() + ".\n Jugadores:" + players);
    				Room10.setBackground(Color.GREEN);
    			}
    		break;
    		default: break;
    	}
    }
    
    private void jbInit() throws Exception {
    	Dimension screenRes=Toolkit.getDefaultToolkit().getScreenSize();
        int x=(screenRes.width/2)-(650/2);
        int y=(screenRes.height/2)-(600/2);
        this.setLocation(x,y);
        this.setSize(650, 600);
        this.getContentPane().setBackground(new Color(212, 224, 230));
        this.setLocale(java.util.Locale.getDefault());
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);
        this.setTitle("Salas");
        CreatejButton.setBounds(new Rectangle(488, 33, 130, 25));
        CreatejButton.setDoubleBuffered(false);
        CreatejButton.setText("Crear Partida");
        CreatejButton.setBackground(new Color(195, 206, 212));
        CreatejButton
                .addActionListener(new RoomsGUI_CreatejButton_actionAdapter(
                        this));
        CreatejButton.setMnemonic(KeyEvent.VK_C);
        CreatejButton.setEnabled(false);
        UnitejButton.setBounds(new Rectangle(488, 95, 130, 25));
        UnitejButton.setText("Unirse");
        UnitejButton.setBackground(new Color(195, 206, 212));
        UnitejButton.addActionListener(new RoomsGUI_UnitejButton_actionAdapter(
                this));
        UnitejButton.setMnemonic(KeyEvent.VK_U);
        UnitejButton.setEnabled(false);
  /*      RefreshjButton.setBounds(new Rectangle(488, 95, 130, 25));
        RefreshjButton.setText("Unirse");
        RefreshjButton.setBackground(new Color(195, 206, 212));
        RefreshjButton.setMnemonic(KeyEvent.VK_U);
        RefreshjButton.setEnabled(false);*/
        //UnitejButton.setEnabled(false);
        QuitjButton.setBounds(new Rectangle(488, 158, 130, 25));
        QuitjButton.setText("Abandonar Juego");
        QuitjButton.setBackground(new Color(195, 206, 212));
        QuitjButton.addActionListener(new RoomsGUI_QuitjButton_actionAdapter(
                this));
        QuitjButton.setMnemonic(KeyEvent.VK_A);
        Room1.setBorder(BorderFactory.createLineBorder(Color.black));
        Room1.setEditable(false);
        this.drawRoom(1,2,6,"la anterior");
        Room1.setBounds(new Rectangle(29, 33, 420, 50));
        Room1.addMouseListener(new RoomsGUI_Room1_mouseAdapter(this));
        Room2.setForeground(Color.black);
        Room2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Room2.setEditable(false);
        //   this.drawEmpty(2);
        Room2.setBounds(new Rectangle(29, 83, 420, 50));
        Room2.addMouseListener(new RoomsGUI_Room2_mouseAdapter(this));
        Room3.setBounds(new Rectangle(29, 133, 420, 50));
        Room3.addMouseListener(new RoomsGUI_Room3_mouseAdapter(this));
        Room3.setBorder(BorderFactory.createLineBorder(Color.black));
        Room3.setEditable(false);
        //     this.drawEmpty(3);
        Room4.setBounds(new Rectangle(29, 183, 420, 50));
        Room4.addMouseListener(new RoomsGUI_Room4_mouseAdapter(this));
        Room4.setAlignmentY((float) 0.5);
        Room4.setBorder(BorderFactory.createLineBorder(Color.black));
        Room4.setEditable(false);

        //  this.drawEmpty(4);
        Room5.setBounds(new Rectangle(29, 233, 420, 50));
        Room5.addMouseListener(new RoomsGUI_Room5_mouseAdapter(this));
        Room5.setAlignmentY((float) 0.5);
        Room5.setBorder(BorderFactory.createLineBorder(Color.black));
        Room5.setEditable(false);
        //   this.drawEmpty(5);
        Room6.setBounds(new Rectangle(29, 283, 420, 50));
        Room6.addMouseListener(new RoomsGUI_Room6_mouseAdapter(this));
        Room6.setBorder(BorderFactory.createLineBorder(Color.black));
        Room6.setEditable(false);
        // this.drawEmpty(6);
        Room7.setBounds(new Rectangle(29, 333, 420, 50));
        Room7.addMouseListener(new RoomsGUI_Room7_mouseAdapter(this));
        Room7.setBorder(BorderFactory.createLineBorder(Color.black));
        Room7.setEditable(false);
        //this.drawEmpty(7);
        Room8.setBounds(new Rectangle(29, 383, 420, 50));
        Room8.addMouseListener(new RoomsGUI_Room8_mouseAdapter(this));
        Room8.setAlignmentY((float) 0.5);
        Room8.setBorder(BorderFactory.createLineBorder(Color.black));
        Room8.setEditable(false);
        //this.drawEmpty(8);
        Room9.setBounds(new Rectangle(29, 433, 420, 50));
        Room9.addMouseListener(new RoomsGUI_Room9_mouseAdapter(this));
        Room9.setBorder(BorderFactory.createLineBorder(Color.black));
        Room9.setEditable(false);
        //this.drawEmpty(9);
        Room10.setBounds(new Rectangle(29, 483, 420, 50));
        Room10.addMouseListener(new RoomsGUI_Room10_mouseAdapter(this));
        Room10.setBorder(BorderFactory.createLineBorder(Color.black));
        Room10.setEditable(false);
        //this.drawEmpty(10);        
     
        BackgroundjLabel.setIcon(new ImageIcon(Tools.loadBufferedImage("src"+File.separator+"GUI"+File.separator+"Images"+File.separator+"stars.png")));
        BackgroundjLabel.setText("");
        BackgroundjLabel.setBounds(new Rectangle(0, 0, 945, 565));
        
        this.getContentPane().add(Room1, null);
        this.getContentPane().add(Room2, null);
        this.getContentPane().add(Room3, null);
        this.getContentPane().add(Room4, null);
        this.getContentPane().add(Room5, null);
        this.getContentPane().add(Room6, null);
        this.getContentPane().add(Room7, null);
        this.getContentPane().add(Room8, null);
        this.getContentPane().add(Room9, null);
        this.getContentPane().add(Room10, null);
        this.getContentPane().add(CreatejButton, null);
        this.getContentPane().add(UnitejButton, null);
        this.getContentPane().add(QuitjButton, null);
        
        this.getContentPane().add(BackgroundjLabel);
    }


    void CreatejButton_actionPerformed(ActionEvent e) {
    	esperando=true;	
    	this.dispose();
    	System.out.println("Sala seleccionada al crear:  " + SelectedRoom);
        createMatchGUI = new CreateMatchGUI(SelectedRoom,chat,descodificador,this);
        createMatchGUI.setVisible(true);
        
        // La pantalla de las salas o la cierras o la inhabilitas por completo
        
    }
    

    void UnitejButton_actionPerformed(ActionEvent e) {
    	System.out.println("Entro en el button");
    	esperando= true;
    	/*
    	 * El boton de unirse debe estar inhabilitado cuando 
    	 * la sala seleccionada este vacia o llena
    	 * */
    	
    	   //if (SelectedScene.equals("")) {
    	/*if (SelectedRoom == -1){
        JOptionPane.showMessageDialog(frame,
                "Necesita elegir una sala para poder unirse",
                "Scene Error", JOptionPane.WARNING_MESSAGE);
    } else {*/
     //Aqui se envia el paquete de crearPartida
    	//y se espera a recibir la confirmacion.
    	
    		recibo.clear();
    		
    		//numPlayers= ((Integer)PlayersjSpinner.getValue()).intValue();
    		System.out.println("Sala selecc en Unirme: " + SelectedRoom);
    		recibo = chat.makeUnirmeAPartida(SelectedRoom);
    		chat.sendUnirmeAPartida(recibo);
    		System.out.println("Acabo de enviar el paquete");
    		
    		Thread t = new Thread("ListenRooms") {
                public void run(/*RoomsGUI r*/) {
                	
//                	Aqui metemos el bucle que esta recibiendo paquetes constantemente
                    // con los datos de las salas
                	
                     // while (true){                      	
                    while(esperando){  
                	//El mismo metodo que showMessageSalasTCP()
                      chat.showMessageUnirmeAPartidaTCP();
                   }                   
                }
            };
            t.start();
    }
    		
    		/*this.dispose();
        CreateMatchGUI createMatchGUI = new CreateMatchGUI(SelectedRoom,chat,descodificador);
        createMatchGUI.setVisible(true);*/
        
        // La pantalla de las salas o la cierras o la inhabilitas por completo
        
    


    void QuitjButton_actionPerformed(ActionEvent e) {
        Object[] options = { "Snh, por supuesto", "No, gracias" };
        int n = JOptionPane.showOptionDialog(frame,
                "nhEstnh seguro de que quiere abandonar el juego?",
                "Bang-Bang 3005", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        if (n == 0) {
            System.exit(0);
        }
    }


    void Room1_mouseClicked(MouseEvent e) {
        if (SelectedRoom == -1) CreatejButton.setEnabled(true);
        SelectedRoom = 0;
        if (RoomActivated != null)
                RoomActivated.setBorder(BorderFactory
                        .createLineBorder(Color.BLACK));
        RoomActivated = Room1;
        RoomActivated
                .setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        Color ColorSelected = Room1.getBackground();
        if (ColorSelected.equals(Color.GREEN)){
        	this.UnitejButton.setEnabled(true);
        	this.CreatejButton.setEnabled(false);
        }
        else if (ColorSelected.equals(Color.WHITE)){
        	this.CreatejButton.setEnabled(true);
        	this.UnitejButton.setEnabled(false);
        }
        else{
        	this.CreatejButton.setEnabled(false);
        	this.UnitejButton.setEnabled(false);
        }
    }


    void Room2_mouseClicked(MouseEvent e) {
        if (SelectedRoom == -1) CreatejButton.setEnabled(true);
        SelectedRoom = 1;
        if (RoomActivated != null)
                RoomActivated.setBorder(BorderFactory
                        .createLineBorder(Color.BLACK));
        RoomActivated = Room2;
        RoomActivated
                .setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        Color ColorSelected = Room2.getBackground();
        if (ColorSelected.equals(Color.GREEN)){
        	this.UnitejButton.setEnabled(true);
        	this.CreatejButton.setEnabled(false);
        }
        else if (ColorSelected.equals(Color.WHITE)){
        	this.CreatejButton.setEnabled(true);
        	this.UnitejButton.setEnabled(false);
        }
        else{
        	this.CreatejButton.setEnabled(false);
        	this.UnitejButton.setEnabled(false);
        }
    }


    void Room3_mouseClicked(MouseEvent e) {
        if (SelectedRoom == -1) CreatejButton.setEnabled(true);
        SelectedRoom = 2;
        if (RoomActivated != null)
                RoomActivated.setBorder(BorderFactory
                        .createLineBorder(Color.BLACK));
        RoomActivated = Room3;
        RoomActivated
                .setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        Color ColorSelected = Room3.getBackground();
        if (ColorSelected.equals(Color.GREEN)){
        	this.UnitejButton.setEnabled(true);
        	this.CreatejButton.setEnabled(false);
        }
        else if (ColorSelected.equals(Color.WHITE)){
        	this.CreatejButton.setEnabled(true);
        	this.UnitejButton.setEnabled(false);
        }
        else{
        	this.CreatejButton.setEnabled(false);
        	this.UnitejButton.setEnabled(false);
        }
    }


    void Room4_mouseClicked(MouseEvent e) {
        if (SelectedRoom == -1) CreatejButton.setEnabled(true);
        SelectedRoom = 3;
        if (RoomActivated != null)
                RoomActivated.setBorder(BorderFactory
                        .createLineBorder(Color.BLACK));
        RoomActivated = Room4;
        RoomActivated
                .setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        Color ColorSelected = Room4.getBackground();
        if (ColorSelected.equals(Color.GREEN)){
        	this.UnitejButton.setEnabled(true);
        	this.CreatejButton.setEnabled(false);
        }
        else if (ColorSelected.equals(Color.WHITE)){
        	this.CreatejButton.setEnabled(true);
        	this.UnitejButton.setEnabled(false);
        }
        else{
        	this.CreatejButton.setEnabled(false);
        	this.UnitejButton.setEnabled(false);
        }
    }


    void Room5_mouseClicked(MouseEvent e) {
        if (SelectedRoom == -1) CreatejButton.setEnabled(true);
        SelectedRoom = 4;
        if (RoomActivated != null)
                RoomActivated.setBorder(BorderFactory
                        .createLineBorder(Color.BLACK));
        RoomActivated = Room5;
        RoomActivated
                .setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        Color ColorSelected = Room5.getBackground();
        if (ColorSelected.equals(Color.GREEN)){
        	this.UnitejButton.setEnabled(true);
        	this.CreatejButton.setEnabled(false);
        }
        else if (ColorSelected.equals(Color.WHITE)){
        	this.CreatejButton.setEnabled(true);
        	this.UnitejButton.setEnabled(false);
        }
        else{
        	this.CreatejButton.setEnabled(false);
        	this.UnitejButton.setEnabled(false);
        }
    }


    void Room6_mouseClicked(MouseEvent e) {
        if (SelectedRoom == -1) CreatejButton.setEnabled(true);
        SelectedRoom = 5;
        if (RoomActivated != null)
                RoomActivated.setBorder(BorderFactory
                        .createLineBorder(Color.BLACK));
        RoomActivated = Room6;
        RoomActivated
                .setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        Color ColorSelected = Room6.getBackground();
        if (ColorSelected.equals(Color.GREEN)){
        	this.UnitejButton.setEnabled(true);
        	this.CreatejButton.setEnabled(false);
        }
        else if (ColorSelected.equals(Color.WHITE)){
        	this.CreatejButton.setEnabled(true);
        	this.UnitejButton.setEnabled(false);
        }
        else{
        	this.CreatejButton.setEnabled(false);
        	this.UnitejButton.setEnabled(false);
        }
    }


    void Room7_mouseClicked(MouseEvent e) {
        if (SelectedRoom == -1) CreatejButton.setEnabled(true);
        SelectedRoom = 6;
        if (RoomActivated != null)
                RoomActivated.setBorder(BorderFactory
                        .createLineBorder(Color.BLACK));
        RoomActivated = Room7;
        RoomActivated
                .setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        Color ColorSelected = Room7.getBackground();
        if (ColorSelected.equals(Color.GREEN)){
        	this.UnitejButton.setEnabled(true);
        	this.CreatejButton.setEnabled(false);
        }
        else if (ColorSelected.equals(Color.WHITE)){
        	this.CreatejButton.setEnabled(true);
        	this.UnitejButton.setEnabled(false);
        }
        else{
        	this.CreatejButton.setEnabled(false);
        	this.UnitejButton.setEnabled(false);
        }
    }


    void Room8_mouseClicked(MouseEvent e) {
        if (SelectedRoom == -1) CreatejButton.setEnabled(true);
        SelectedRoom = 7;
        if (RoomActivated != null)
                RoomActivated.setBorder(BorderFactory
                        .createLineBorder(Color.BLACK));
        RoomActivated = Room8;
        RoomActivated
                .setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        Color ColorSelected = Room8.getBackground();
        if (ColorSelected.equals(Color.GREEN)){
        	this.UnitejButton.setEnabled(true);
        	this.CreatejButton.setEnabled(false);
        }
        else if (ColorSelected.equals(Color.WHITE)){
        	this.CreatejButton.setEnabled(true);
        	this.UnitejButton.setEnabled(false);
        }
        else{
        	this.CreatejButton.setEnabled(false);
        	this.UnitejButton.setEnabled(false);
        }
    }


    void Room9_mouseClicked(MouseEvent e) {
        if (SelectedRoom == -1) CreatejButton.setEnabled(true);
        SelectedRoom = 8;
        if (RoomActivated != null)
                RoomActivated.setBorder(BorderFactory
                        .createLineBorder(Color.BLACK));
        RoomActivated = Room9;
        RoomActivated
                .setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        Color ColorSelected = Room9.getBackground();
        if (ColorSelected.equals(Color.GREEN)){
        	this.UnitejButton.setEnabled(true);
        	this.CreatejButton.setEnabled(false);
        }
        else if (ColorSelected.equals(Color.WHITE)){
        	this.CreatejButton.setEnabled(true);
        	this.UnitejButton.setEnabled(false);
        }
        else{
        	this.CreatejButton.setEnabled(false);
        	this.UnitejButton.setEnabled(false);
        }
    }


    void Room10_mouseClicked(MouseEvent e) {
        if (SelectedRoom == -1) CreatejButton.setEnabled(true);
        SelectedRoom = 9;
        if (RoomActivated != null)
                RoomActivated.setBorder(BorderFactory
                        .createLineBorder(Color.BLACK));
        RoomActivated = Room10;
        RoomActivated
                .setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        Color ColorSelected = Room10.getBackground();
        if (ColorSelected.equals(Color.GREEN)){
        	this.UnitejButton.setEnabled(true);
        	this.CreatejButton.setEnabled(false);
        }
        else if (ColorSelected.equals(Color.WHITE)){
        	this.CreatejButton.setEnabled(true);
        	this.UnitejButton.setEnabled(false);
        }
        else{
        	this.CreatejButton.setEnabled(false);
        	this.UnitejButton.setEnabled(false);
        }
    }    
    public void processPackageSala(ByteBuffer b){
    	//	b.rewind();
    		char ident = b.getChar();
    		System.out.println(ident);
    		if (ident== Constants.PACK_ROOM_REQUEST){ //sala modificada   			
    			int sala = b.getInt();
    			int estate = b.getInt();
    			if (estate == 0){
    				//System.out.println("una");
    			drawEmpty(sala);
    			chat.setRooms(this);}
    			else	 {
    				int numPlayers = b.getInt(); 
    				
    				try {
    				String jugadores = descodificador.decode(b).toString();
    				drawRoom(sala,estate,numPlayers,jugadores);
    				chat.setRooms(this);
    				}catch (CharacterCodingException cce){System.out.println("Error en descodificador");}	
    			}
    			}    	
    }
    
    private void beginning(Stage stage) {
		SplashGUI loading = new SplashGUI(this, Tools
				.loadBufferedImage("art" + File.separator
						+ "loading.png"));
//		this.setVisible(false);
		loading.setVisible(true);		
		Screen screen = new Screen();
		//MainWindowGUI mainWindow = new MainWindowGUI(screen, stage);
		//mainWindow.setVisible(true);
		screen.requestFocusInWindow();
     	loading.dispose();
		GameEngine ge = new GameEngine(stage, screen, jugadores,true,chat,SelectedRoom);
		ge.run();
	}
    
    public void processPackageUniroEmpezar(ByteBuffer b){
      	// bo=false;
      	//System.out.println("process package");
      	//b.rewind();
   		char ident = b.getChar();
   		//	System.out.println("He recibido" + ident);
   		if (ident== Constants.PACK_JOIN_MATCH){
   			int sala = b.getInt();
   			//System.out.println("He recibido" + sala);
   			char confirm = b.getChar();
   			//System.out.println("He recibido" + confirm);
   			if (confirm == 'o'){   				
   					int selectedScene = b.getInt();
   					//		System.out.println("He recibido" + selectedScene);
   					int limite = b.getInt();
   					//System.out.println("He recibido" + limite);
   					int numJugadores = b.getInt();
   					//	System.out.println("He recibido" + numJugadores);
   					String cadena="";
   					try {
   						cadena = descodificador.decode(b).toString();
   						//System.out.println(cadena);
   						separador = new StringTokenizer(cadena);
   						for (int i =0;i<numJugadores;i++){
   						String nombre = separador.nextToken();
   						String tipoJugador = separador.nextToken();
   						ClientPlayer client = new ClientPlayer(nombre,tipoJugador);
   						jugadores.add(client);   						
   						}
   						//bo=true;
   					} catch (CharacterCodingException e) {
   						// TODO Auto-generated catch block
   						e.printStackTrace();
   					}
   					System.out.println("Antes del limite de jugadores;");
   					if (limite!=numJugadores){
   						esperando=false;
   						this.dispose();
   	              WaitingGUI waitingGUI = new WaitingGUI(sala, selectedScene,limite,chat,descodificador,jugadores);
   	              waitingGUI.setVisible(true);
   	              waitingGUI.listen();
   	             }
   			}
   	             
   			else	 {
   				 if (confirm == 'c'){
   				 	//Ventana de partida ya creada
//   				 	Ventana de Partida ya creada
   				 	JOptionPane.showMessageDialog(frame,
   		                    "Partida ya creada",
   		                    "Created Match", JOptionPane.WARNING_MESSAGE);
   				 	//RoomsGUI roomsGUI = new RoomsGUI(chat);
   				 	this.dispose();
  				 	RoomsGUI roomsGUI = chat.getRoomsGUI();
   				 	roomsGUI.setVisible(true);   			        
   			        roomsGUI.listen();   				 
   				 }   					
   			}
   			}
   		
   		else {
   			if (ident == Constants.PACK_START_MATCH) {
   				esperando=false;
   				int sala = b.getInt();
   				System.out.println("La sala es: " + sala);
   				System.out.println("SelectedRoom es: " + SelectedRoom);
   				SelectedRoom = sala;
   				//Igual no hace falta.Sera 'o' de la confirmacion
   				//char estate = b.getChar();
   				//El estado de la partida:Sera siempre ocupada
   				 escenario = b.getInt();
   				 
   				 // System.out.println("El escenario es "+escenario);
   				int estado = b.getInt();
   				//El numero de jugadores
   				int numPlayer = b.getInt();
   				jugadores.clear();
   				for (int i=0;i<numPlayer;i++){
   					int pos = b.getInt();
   					ClientPlayer p = new ClientPlayer(pos);
   					jugadores.add(p);
   				}

   				//Almaceno el nombre de todos los jugadores y su tipo en una
   				// lista
   				
   				try {
   					String cadena = descodificador.decode(b).toString();
   					separador = new StringTokenizer(cadena);
   					for (int i = 0; i < numPlayer; i++) {
   						((ClientPlayer)jugadores.get(i)).setName(separador.nextToken());
   						((ClientPlayer)jugadores.get(i)).setPlayerType(separador.nextToken());
   					}
   					//Ahora los pinto en la GUI
   					//escribirJugadores(jugadores);
   				} catch (CharacterCodingException cce) {
   					System.out.println("Error en descodificador");
   				}
   					chat.setRooms(this);
   					this.dispose();
//   				SplashGUI loading = new SplashGUI(this, Tools
//   						.loadBufferedImage("art" + File.separator
//   								+ "loading.png"));
//   				this.setVisible(false);
//   				loading.setVisible(true);

   				Thread t = new Thread("Main") {

   					public void run() {
   						//     if (SelectedScene.equals("Eleuron")) {
   						if (escenario == 1) {
   							Stage stage = new StageEleuron();
   							//WaitingGUI.this.dispose();
   							beginning(stage);
   							//   } else if (SelectedScene.equals("Silicosis")) {
   						} else if (escenario == 2) {
   							Stage stage = new StageSilicosis();
   						//	WaitingGUI.this.dispose();
   							beginning(stage);
   							// } else if (SelectedScene.equals("Nodrubaz")) {
   						} else if (escenario == 3) {
   							Stage stage = new StageNodrubaz();
   						//	WaitingGUI.this.dispose();
   							beginning(stage);
   							//   } else if (SelectedScene.equals("OCB-5")) {
   						} else if (escenario == 4) {
   							Stage stage = new StageOcb5();
   						//	WaitingGUI.this.dispose();
   							beginning(stage);
   							//  } else if
   							// (SelectedScene.equals("CampoAsteroides")) {
   						} else if (escenario == 5) {
   							Stage stage = new StageTest();
   							// stage.setActorsArray(jugadores);
   						//	WaitingGUI.this.dispose();
   							beginning(stage);
   						}

   					}

   				};
   				t.start();
   			}		
   			
   		}
   		
   }
    
//    public void processPackageUnirmeAPartida(ByteBuffer b){
//    	 bo=false;
//     	System.out.println("process package");
//     	//b.rewind();
// 		char ident = b.getChar();
// 		System.out.println("He recibido" + ident);
// 		if (ident=='('){
// 			int sala = b.getInt();
// 			System.out.println("He recibido" + sala);
// 			char confirm = b.getChar();
// 			System.out.println("He recibido" + confirm);
// 			if (confirm == 'o'){
// 				
// 					int selectedScene = b.getInt();
// 					System.out.println("He recibido" + selectedScene);
// 					int limite = b.getInt();
// 					System.out.println("He recibido" + limite);
// 					int numJugadores = b.getInt();
// 					System.out.println("He recibido" + numJugadores);
// 					String cadena="";
// 					try {
// 						cadena = descodificador.decode(b).toString();
// 						System.out.println(cadena);
// 						separador = new StringTokenizer(cadena);
// 						for (int i =0;i<numJugadores;i++){
// 						String nombre = separador.nextToken();
// 						String tipoJugador = separador.nextToken();
// 						ClientPlayer client = new ClientPlayer(nombre,tipoJugador);
// 						jugadores.add(client);
// 						
// 						}
// 						bo=true;
// 					} catch (CharacterCodingException e) {
// 						// TODO Auto-generated catch block
// 						e.printStackTrace();
// 					}
// 					if (limite!=numJugadores){
// 					this.dispose();
// 	              WaitingGUI waitingGUI = new WaitingGUI(sala, selectedScene,limite,chat,descodificador,jugadores);
// 	              waitingGUI.setVisible(true);
// 	              waitingGUI.listen();
// 	              esperando=true;}
// 			}
// 	             
// 			else	 {
// 				 if (confirm == 'c'){
// 				 	//Ventana de partida ya creada
//// 				 	Ventana de Partida ya creada
// 				 	JOptionPane.showMessageDialog(frame,
// 		                    "Partida ya creada",
// 		                    "Created Match", JOptionPane.WARNING_MESSAGE);
// 				 //	RoomsGUI roomsGUI = new RoomsGUI(chat);
// 				 	RoomsGUI roomsGUI = chat.getRoomsGUI();
// 				 	roomsGUI.setVisible(true);
// 			        this.dispose();
// 			        roomsGUI.listen();	
// 				 
// 				 }
// 					
// 			}
// 			}
// 		
// 		else {
// 			if (ident == ')') {
// 				int sala = b.getInt();
// 				//Igual no hace falta.Sera 'o' de la confirmacion
// 				//char estate = b.getChar();
// 				//El estado de la partida:Sera siempre ocupada
// 				 escenario = b.getInt();
// 				int estado = b.getInt();
// 				//El numero de jugadores
// 				int numPlayer = b.getInt();
//
// 				//Almaceno el nombre de todos los jugadores y su tipo en una
// 				// lista
// 				jugadores.clear();
// 				try {
// 					String cadena = descodificador.decode(b).toString();
// 					separador = new StringTokenizer(cadena);
// 					for (int i = 0; i < numPlayer; i++) {
//
// 						ClientPlayer p = new ClientPlayer(
// 								separador.nextToken(), separador.nextToken());
// 						jugadores.add(p);
// 					}
// 					//Ahora los pinto en la GUI
// 					//escribirJugadores(jugadores);
// 				} catch (CharacterCodingException cce) {
// 					System.out.println("Error en descodificador");
// 				}
//
// 				SplashGUI loading = new SplashGUI(this, Tools
// 						.loadBufferedImage("art" + File.separator
// 								+ "loading.png"));
// 				this.setVisible(false);
// 				loading.setVisible(true);
//
// 				Thread t = new Thread("Main") {
//
// 					public void run() {
//
// 						//     if (SelectedScene.equals("Eleuron")) {
// 						if (escenario == Constants.ELEURON_STAGE) {
// 							Stage stage = new StageEleuron();
// 							//WaitingGUI.this.dispose();
// 							beginning(stage);
// 							//   } else if (SelectedScene.equals("Silicosis")) {
// 						} else if (escenario == Constants.SILICOSIS_STAGE) {
// 							Stage stage = new StageSilicosis();
// 						//	WaitingGUI.this.dispose();
// 							beginning(stage);
// 							// } else if (SelectedScene.equals("Nodrubaz")) {
// 						} else if (escenario == Constants.NODRUBAZ_STAGE) {
// 							Stage stage = new StageNodrubaz();
// 						//	WaitingGUI.this.dispose();
// 							beginning(stage);
// 							//   } else if (SelectedScene.equals("OCB-5")) {
// 						} else if (escenario == Constants.OCB5_STAGE) {
// 							Stage stage = new StageOcb5();
// 						//	WaitingGUI.this.dispose();
// 							beginning(stage);
// 							//  } else if
// 							// (SelectedScene.equals("CampoAsteroides")) {
// 						} else if (escenario == Constants.ASTEROIDS_STAGE) {
// 							Stage stage = new StageTest();
// 							// stage.setActorsArray(jugadores);
// 						//	WaitingGUI.this.dispose();
// 							beginning(stage);
// 						}
//
// 					}
//
// 				};
// 				t.start();
// 			}
// 		}
//}
    
      
    public void listen(){ 
    	esperando=false;
       Thread t = new Thread("") {
            public void run(/*RoomsGUI r*/) {
  
//            	Aqui metemos el bucle que esta recibiendo paquetes constantemente
                // con los datos de las salas
            	
                  //while (!unirButton){
           	while (!esperando){                  	
                  chat.setRooms(room);
                  chat.showMessageSalasTCP();                 
               }	
            }
        };
        t.start();
        

    
       }
    
    public RoomsGUI getRoom(){return room;}
    
}

class RoomsGUI_CreatejButton_actionAdapter implements
        java.awt.event.ActionListener {

    RoomsGUI adaptee;


    RoomsGUI_CreatejButton_actionAdapter(RoomsGUI adaptee) {
        this.adaptee = adaptee;
    }


    public void actionPerformed(ActionEvent e) {
        adaptee.CreatejButton_actionPerformed(e);
    }
}

class RoomsGUI_UnitejButton_actionAdapter implements
java.awt.event.ActionListener {

RoomsGUI adaptee;


RoomsGUI_UnitejButton_actionAdapter(RoomsGUI adaptee) {
this.adaptee = adaptee;
}


public void actionPerformed(ActionEvent e) {
adaptee.UnitejButton_actionPerformed(e);
}
}

class RoomsGUI_Room1_mouseAdapter extends java.awt.event.MouseAdapter {

    RoomsGUI adaptee;


    RoomsGUI_Room1_mouseAdapter(RoomsGUI adaptee) {
        this.adaptee = adaptee;
    }


    public void mouseClicked(MouseEvent e) {
        adaptee.Room1_mouseClicked(e);
    }
}


class RoomsGUI_QuitjButton_actionAdapter implements
        java.awt.event.ActionListener {

    RoomsGUI adaptee;


    RoomsGUI_QuitjButton_actionAdapter(RoomsGUI adaptee) {
        this.adaptee = adaptee;
    }


    public void actionPerformed(ActionEvent e) {
        adaptee.QuitjButton_actionPerformed(e);
    }
}


class RoomsGUI_Room2_mouseAdapter extends java.awt.event.MouseAdapter {

    RoomsGUI adaptee;


    RoomsGUI_Room2_mouseAdapter(RoomsGUI adaptee) {
        this.adaptee = adaptee;
    }


    public void mouseClicked(MouseEvent e) {
        adaptee.Room2_mouseClicked(e);
    }
}


class RoomsGUI_Room3_mouseAdapter extends java.awt.event.MouseAdapter {

    RoomsGUI adaptee;


    RoomsGUI_Room3_mouseAdapter(RoomsGUI adaptee) {
        this.adaptee = adaptee;
    }


    public void mouseClicked(MouseEvent e) {
        adaptee.Room3_mouseClicked(e);
    }
}


class RoomsGUI_Room4_mouseAdapter extends java.awt.event.MouseAdapter {

    RoomsGUI adaptee;


    RoomsGUI_Room4_mouseAdapter(RoomsGUI adaptee) {
        this.adaptee = adaptee;
    }


    public void mouseClicked(MouseEvent e) {
        adaptee.Room4_mouseClicked(e);
    }
}


class RoomsGUI_Room5_mouseAdapter extends java.awt.event.MouseAdapter {

    RoomsGUI adaptee;


    RoomsGUI_Room5_mouseAdapter(RoomsGUI adaptee) {
        this.adaptee = adaptee;
    }


    public void mouseClicked(MouseEvent e) {
        adaptee.Room5_mouseClicked(e);
    }
}


class RoomsGUI_Room6_mouseAdapter extends java.awt.event.MouseAdapter {

    RoomsGUI adaptee;


    RoomsGUI_Room6_mouseAdapter(RoomsGUI adaptee) {
        this.adaptee = adaptee;
    }


    public void mouseClicked(MouseEvent e) {
        adaptee.Room6_mouseClicked(e);
    }
}


class RoomsGUI_Room7_mouseAdapter extends java.awt.event.MouseAdapter {

    RoomsGUI adaptee;


    RoomsGUI_Room7_mouseAdapter(RoomsGUI adaptee) {
        this.adaptee = adaptee;
    }


    public void mouseClicked(MouseEvent e) {
        adaptee.Room7_mouseClicked(e);
    }
}


class RoomsGUI_Room8_mouseAdapter extends java.awt.event.MouseAdapter {

    RoomsGUI adaptee;


    RoomsGUI_Room8_mouseAdapter(RoomsGUI adaptee) {
        this.adaptee = adaptee;
    }


    public void mouseClicked(MouseEvent e) {
        adaptee.Room8_mouseClicked(e);
    }
}


class RoomsGUI_Room9_mouseAdapter extends java.awt.event.MouseAdapter {

    RoomsGUI adaptee;


    RoomsGUI_Room9_mouseAdapter(RoomsGUI adaptee) {
        this.adaptee = adaptee;
    }


    public void mouseClicked(MouseEvent e) {
        adaptee.Room9_mouseClicked(e);
    }
}


class RoomsGUI_Room10_mouseAdapter extends java.awt.event.MouseAdapter {

    RoomsGUI adaptee;


    RoomsGUI_Room10_mouseAdapter(RoomsGUI adaptee) {
        this.adaptee = adaptee;
    }


    public void mouseClicked(MouseEvent e) {
        adaptee.Room10_mouseClicked(e);
    }
}
