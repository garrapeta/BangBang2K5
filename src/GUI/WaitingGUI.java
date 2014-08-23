package GUI;

import game.GameEngine;
import game.Screen;
import game.stages.Stage;
import game.stages.StageEleuron;
import game.stages.StageNodrubaz;
import game.stages.StageOcb5;
import game.stages.StageSilicosis;
import game.stages.StageTest;
import java.util.List;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.util.*;

import java.util.StringTokenizer;

import javax.swing.*;

import util.Constants;
import util.Tools;

import network.Chat;
import network.ClientPlayer;

/**
 * <p>
 * Tnhtulo:
 * </p>
 * <p>
 * Descripcinhn:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Empresa:
 * </p>
 * 
 * @author Germantxo
 * @version 1.0
 */

public class WaitingGUI extends JFrame {

	JLabel ScenejLabel = new JLabel();

	JLabel BackgroundjLabel = new JLabel();
	
	JLabel NumPlayersjLabel = new JLabel();

	JLabel RoomjLabel = new JLabel();

	JTextField ScenejTextField = new JTextField();

	JTextField NumPlayersjTextField = new JTextField();

	JTextField RoomjTextField = new JTextField();

	DefaultListModel RacesModel = new DefaultListModel();

	DefaultListModel PlayersModel = new DefaultListModel();

	JList PlayersjList = new JList(PlayersModel);

	JList RacejList = new JList(RacesModel);

	JTextField PlayersjTextField = new JTextField();

	JTextField RacesjTextField = new JTextField();

	JButton PlayjButton = new JButton();

	int room/* ,numPlayers */;

	WaitingGUI wait = this;

	String scene;

	int escenario;

	int limite;

	Chat chat;

	ByteBuffer recibo;

	List jugadores = new ArrayList();

	StringTokenizer separador;

	CharsetDecoder descodificador;
	
    private	boolean esperando;

	public WaitingGUI(int r, int s, int l, Chat c, CharsetDecoder desc, List jug)
			throws HeadlessException {
		try {
			room = r;
			escenario = s;
			limite = l;
			scene = this.getScene(escenario);
			chat = c;
			descodificador = desc;
			jugadores = jug;
			recibo = ByteBuffer.allocate(Constants.BYTE_BUFFER_SIZE_LARGE);
			chat.setWaitingGUI(this);
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		this.getContentPane().setLayout(null);
		this.setLocation(160, 160);
		this.setResizable(false);
		this.getContentPane().setBackground(new Color(212, 224, 230));
		this.setSize(new Dimension(372, 397));
		PlayersjList.setAlignmentY((float) 0.5);
		PlayersjList.setBorder(BorderFactory.createLineBorder(Color.black));
		PlayersjList.setBounds(new Rectangle(27, 116, 157, 187));
		PlayersjList.setName(chat.getClientPlayer().getName());
		RacejList.setAlignmentY((float) 0.5);
		RacejList.setBorder(BorderFactory.createLineBorder(Color.black));
		RacejList.setBounds(new Rectangle(184, 116, 157, 187));
		for(int i=0;i<jugadores.size();i++ ){
			ClientPlayer actual=(ClientPlayer)jugadores.get(i);
			this.PlayersModel.add(i,actual.getName().trim());
			this.RacesModel.add(i,actual.getPlayerType().trim());		
		};
//		this.PlayersModel.addElement(chat.getClientPlayer().getName());
//		this.RacesModel.addElement(chat.getClientPlayer().getPlayerType());

		this.setTitle("Esperar Jugadores");
		PlayersjTextField.setBackground(Color.darkGray);
		PlayersjTextField.setForeground(Color.white);
		PlayersjTextField
				.setBorder(BorderFactory.createLineBorder(Color.black));
		PlayersjTextField.setText("    Jugadores");
		PlayersjTextField.setEditable(false);
		PlayersjTextField.setBounds(new Rectangle(27, 92, 157, 25));
		RacesjTextField.setBackground(Color.darkGray);
		RacesjTextField.setForeground(Color.white);
		RacesjTextField.setBorder(BorderFactory.createLineBorder(Color.black));
		RacesjTextField.setText("    Raza");
		RacesjTextField.setEditable(false);
		//   RacesjTextField.add(chat.getClientPlayer().getPlayerType());
		RacesjTextField.setBounds(new Rectangle(184, 92, 157, 25));
		PlayjButton.setBounds(new Rectangle(130, 325, 100, 25));
		PlayjButton.setBorder(BorderFactory.createRaisedBevelBorder());
		PlayjButton.setText("Jugar");
		PlayjButton.setMnemonic(KeyEvent.VK_J);
		PlayjButton.setBackground(new Color(195, 206, 212));
		ScenejLabel.setText("Escenario:");
		ScenejLabel.setForeground(Color.YELLOW);
		ScenejLabel.setFont(ScenejLabel.getFont().deriveFont(Font.BOLD));
		ScenejLabel.setBounds(new Rectangle(80, 10, 80, 25));
		NumPlayersjLabel.setText("Nnhmero de jugadores:");
		NumPlayersjLabel.setForeground(Color.YELLOW);
		NumPlayersjLabel.setFont(NumPlayersjLabel.getFont().deriveFont(Font.BOLD));
		NumPlayersjLabel.setBounds(new Rectangle(80, 35, 138, 25));
		RoomjLabel.setText("Sala:");
		RoomjLabel.setBounds(new Rectangle(80, 60, 80, 25));
		RoomjLabel.setForeground(Color.YELLOW);
		RoomjLabel.setFont(RoomjLabel.getFont().deriveFont(Font.BOLD));

		ScenejTextField.setEnabled(false);
		ScenejTextField.setDisabledTextColor(Color.black);
		ScenejTextField.setEditable(false);
		ScenejTextField.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
		ScenejTextField.setText(scene);
		ScenejTextField.setScrollOffset(0);
		ScenejTextField.setBounds(new Rectangle(220, 10, 121, 24));

		NumPlayersjTextField.setEnabled(false);
		NumPlayersjTextField.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
		NumPlayersjTextField.setDisabledTextColor(Color.black);
		NumPlayersjTextField.setEditable(false);
		Integer NumPlayer = new Integer(limite);
		NumPlayersjTextField.setText(NumPlayer.toString());
		NumPlayersjTextField.setScrollOffset(0);
		NumPlayersjTextField.setBounds(new Rectangle(220, 35, 53, 24));

		RoomjTextField.setEnabled(false);
		RoomjTextField.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
		RoomjTextField.setDisabledTextColor(Color.black);
		RoomjTextField.setEditable(false);
		Integer IntRoom = new Integer((room+1));
		RoomjTextField.setText(IntRoom.toString());
		RoomjTextField.setScrollOffset(0);
		RoomjTextField.setBounds(new Rectangle(220, 60, 53, 24));
		
		BackgroundjLabel.setIcon(new ImageIcon(Tools.loadBufferedImage("src"
				+ File.separator + "GUI" + File.separator + "Images"
				+ File.separator + "stars.png")));
		BackgroundjLabel.setText("");
		BackgroundjLabel.setBounds(new Rectangle(0, 0, 945, 565));

		this.getContentPane().add(PlayersjTextField, null);
		this.getContentPane().add(RacejList, null);
		this.getContentPane().add(PlayersjList, null);
		this.getContentPane().add(RacesjTextField, null);
		this.getContentPane().add(PlayjButton, null);
		this.getContentPane().add(ScenejTextField, null);
		this.getContentPane().add(RoomjTextField, null);
		this.getContentPane().add(NumPlayersjTextField, null);
		this.getContentPane().add(RoomjLabel, null);
		this.getContentPane().add(ScenejLabel, null);
		this.getContentPane().add(NumPlayersjLabel, null);
		this.getContentPane().add(BackgroundjLabel);

	}

	public String getScene(int s) {

		switch (s) {
		case 1:
			scene = "ELEURON";
			break;
		case 3:
			scene = "NODRUBAZ";
			break;
		case 4:
			scene = "OCB5";
			break;
		case 2:
			scene = "SILICOSIS";
			break;
		case 5:
			scene = "ASTEROIDES";
			break;

		}
		return scene;
	}

	public void processPackageSalaEspera(ByteBuffer b) {
		b.rewind();
		char ident = b.getChar();
		System.out.println("char: "+ident);
		if (ident == Constants.PACK_JOIN_MATCH) {
			System.out.println("CONFIRMACION DE UNION A PARTIDA");
			int sala = b.getInt();
			char estate = b.getChar();
			if (estate == 'o') {
				int esc=b.getInt();
				int limite=b.getInt();
				//Recibo un TCP con nombre y tipo de jugador de cada cliente
				int numPlayer = b.getInt();
				//Almaceno el nombre de todos los jugadores y su tipo en una
				// lista
				jugadores.clear();
				try {
					String cadena = descodificador.decode(b).toString();

					separador = new StringTokenizer(cadena);
					this.PlayersModel.clear();
					this.RacesModel.clear();
					for (int i = 0; i < numPlayer; i++) {

						ClientPlayer p = new ClientPlayer(
								separador.nextToken(), separador.nextToken());
						jugadores.add(p);
						//Pintar Jugadores de una lista.
						//de 0 a jugadores.size() -1;
						
						this.PlayersModel.add(i,p.getName().trim());
						this.RacesModel.add(i,p.getPlayerType().trim());

					}
					//Ahora los pinto en la GUI
					//escribirJugadores(jugadores);
				} catch (CharacterCodingException cce) {
					System.out.println("Error en descodificador");
				}

				//aqunh tiene que ir una ventana intermedia de espera

			}

//			else {
//				if (estate == 'c') {
//
//					//Ventana de Partida ya creada
//					JOptionPane.showMessageDialog(null, "Partida ya creada",
//							"Created Match", JOptionPane.WARNING_MESSAGE);
//					RoomsGUI roomsGUI = new RoomsGUI(chat);
//					roomsGUI.setVisible(true);
//					this.dispose();
//					}
//			}
		}

		//Recibo paquete de empezar partida
		else {			
			if (ident == Constants.PACK_START_MATCH) {
				System.out.println("CONFIRMACION DE EMPEZAR PARTIDA"); 
				esperando = false;
   				int sala = b.getInt();
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
   					chat.setWaitingGUI(this);
   					this.dispose();
   				SplashGUI loading = new SplashGUI(this, Tools
   						.loadBufferedImage("art" + File.separator
   								+ "loading.png"));
   				this.setVisible(false);
   				loading.setVisible(true);

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
   							//WaitingGUI.this.dispose();
   							beginning(stage);
   							// } else if (SelectedScene.equals("Nodrubaz")) {
   						} else if (escenario == 3) {
   							Stage stage = new StageNodrubaz();
   						//WaitingGUI.this.dispose();
   							beginning(stage);
   							//   } else if (SelectedScene.equals("OCB-5")) {
   						} else if (escenario == 4) {
   							Stage stage = new StageOcb5();
   							//WaitingGUI.this.dispose();
   							beginning(stage);
   							//  } else if
   							// (SelectedScene.equals("CampoAsteroides")) {
   						} else if (escenario == 5) {
   							Stage stage = new StageTest();
   							// stage.setActorsArray(jugadores);
   							//WaitingGUI.this.dispose();
   							beginning(stage);
   						}

   					}

   				};
   				t.start();
   			}	
		}
	}

	private void beginning(Stage stage) {
		Screen screen = new Screen();
		//MainWindowGUI mainWindow = new MainWindowGUI(screen, stage);
		//mainWindow.setVisible(true);
		screen.requestFocusInWindow();
		this.dispose();
		// ClientServer clientServer = new ClientServer();
		GameEngine ge = new GameEngine(stage, screen, jugadores,true,chat,room);
		ge.run();
	}

	public void listen() {
		esperando=true;
		Thread t = new Thread("ListenerWaitingThread") {
			public void run(/* RoomsGUI r */) {

				// Aqui metemos el bucle que esta recibiendo paquetes
				// constantemente
				// con los datos de las salas

				while (esperando) {
					// 	while (!chat.bo){

					chat.setWaitingGUI(wait);
					chat.showMessageSalaEsperaTCP();
				}
			}
		};
		t.start();

	}

}