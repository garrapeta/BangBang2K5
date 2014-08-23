package GUI;

import game.GameEngine;
import game.Screen;
import game.stages.Stage;
import game.stages.StageEleuron;
import game.stages.StageNodrubaz;
import game.stages.StageOcb5;
import game.stages.StageSilicosis;
import game.stages.StageTest;

import javax.swing.*;
import javax.swing.border.Border;

import util.Constants;
import util.Tools;

import java.util.ArrayList;
import java.util.List;

import network.Chat;
import network.ClientServer;
import network.ClientPlayer;

import java.awt.event.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.charset.CharsetDecoder;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import network.Chat;
import util.Tools;

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

public class CreateMatchGUI extends JFrame {

	JFrame frame;
	
	SpinnerModel model =
        new SpinnerNumberModel(2, //initial value
                               2, //min
                               4, //max
                               1);//step

	JTextArea DescriptionScene = new JTextArea();

	JSpinner PlayersjSpinner = new JSpinner(model);

	JLabel BackgroundjLabel = new JLabel();

	JLabel PlayersjLabel = new JLabel();

	JLabel ScenesjLabel = new JLabel();

	JLabel OCB5ScenejLabel = new JLabel();

	JLabel AsteroideScenejLabel = new JLabel();

	JLabel NodrubazScenejLabel = new JLabel();

	JLabel SilicosisScenejLabel = new JLabel();

	JLabel EleuronScenejLabel = new JLabel();

	JButton OkjButton = new JButton();

	JButton CanceljButton = new JButton();

	private Border normalBorder;

	private Border selectedBorder;

	private JLabel labelActivated;

	CreateMatchGUI c = this;

	int room;

	Chat chat;

	ByteBuffer recibo;

	CharsetDecoder descodificador;

	String jugador;

	String SelectedScene = "";

	int selectedScene = -1;

	int numPlayers;

	List jugadores;

	private boolean networkGame;
	private boolean creada;
	private RoomsGUI roomgui;

	public CreateMatchGUI(int s, Chat c, CharsetDecoder desc,RoomsGUI rg)
			throws HeadlessException {
		try {
			roomgui=rg;
			this.networkGame = true;
			room = s;
			chat = c;
			descodificador = desc;
			recibo = ByteBuffer.allocate(Constants.BYTE_BUFFER_SIZE);
			jbInit();			
			jugadores = new ArrayList();
			chat.setCreateMatchGUI(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public CreateMatchGUI(List jug, boolean red)
			throws HeadlessException {
		try {
			jugadores = jug;
			networkGame = red;
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public CreateMatchGUI(int sala) throws HeadlessException {
		try {
			this.networkGame = false;
			room = sala;
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void processPackageCrearPartida(ByteBuffer b) {		
		char ident = b.getChar();		
		if (ident == '/') {
			int sala = b.getInt();			
			char confirm = b.getChar();			
			if (confirm == 'o') {
				//System.out.println("Lo proceso");
				int selectedScene = b.getInt();				
				int limite = b.getInt();			
				jugadores.add(chat.getClientPlayer());
				creada=true;
				this.dispose();
				WaitingGUI waitingGUI = new WaitingGUI(sala, selectedScene,
						limite, chat, descodificador, jugadores);
				waitingGUI.setVisible(true);				
				waitingGUI.listen();				
			}
			else {
				if (confirm == 'c') {
					//Ventana de partida ya creada
					//Ventana de Partida ya creada
					JOptionPane.showMessageDialog(frame, "Partida ya creada",
							"Created Match", JOptionPane.WARNING_MESSAGE);
					//	RoomsGUI roomsGUI = new RoomsGUI(chat);
					RoomsGUI roomsGUI = chat.getRoomsGUI();
					roomsGUI.setVisible(true);
					this.dispose();
					roomsGUI.listen();					
				}
			}
		}
	}
	

	private void updateText(String name) {
		if (name.equals("Eleuron")) {
			DescriptionScene
					.setText("Planeta de los Eleos. Aspecto idnhlico con "
							+ "grandes dunas azuladas en armonnha con la tecnolognha que lo rodea");
		} else if (name.equals("Silicosis")) {
			DescriptionScene
					.setText("Planeta con gran reserva mineral de internhs"
							+ " industrial. Poca poblacinhn, salvo la relacionada con la industria");
		} else if (name.equals("OCB-5")) {
			DescriptionScene
					.setText("Planeta de las Ninfas. Compuesto por enormes bosques "
							+ "y selvas. Grandes superficies de hierba, variedad de setas. Zona ideal para "
							+ "desconectar");
		} else if (name.equals("Nodrubaz")) {
			DescriptionScene
					.setText("Se miden las fuerzas en una estacinhn de investigacinhn "
							+ "espacial. Rodeada por numerosas estrellas y cometas.");
		} else if (name.equals("CampoAsteroides")) {
			DescriptionScene.setText("La batalla se decide encima de uno de "
					+ "los objetos volantes de un campo de asteroides");
		}
	}

	private void beginning(Stage stage) {
		Screen screen = new Screen();
		
		screen.requestFocusInWindow();
		
		GameEngine ge = new GameEngine(stage, screen, jugadores,false,chat,room);
		CreateMatchGUI.this.dispose();
		ge.run();
	}

	private void jbInit() throws Exception {
		this.getContentPane().setLayout(null);
		Dimension screenRes=Toolkit.getDefaultToolkit().getScreenSize();
	    int x=(screenRes.width/2)-(945/2);
	    int y=(screenRes.height/2)-(565/2);
	    this.setLocation(x,y);
		this.setResizable(false);
		this.getContentPane().setBackground(new Color(212, 224, 230));
		this.setSize(new Dimension(945, 565));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		PlayersjSpinner.setBounds(new Rectangle(185, 15, 53, 25));
		PlayersjSpinner.setEditor(new JSpinner.NumberEditor(PlayersjSpinner, "#"));
		ScenesjLabel.setText("Elija escenario");
		ScenesjLabel.setBounds(new Rectangle(11, 44, 179, 25));
		PlayersjLabel.setText("Nnhmero Mnhximo de Jugadores");
		PlayersjLabel.setFont(PlayersjLabel.getFont().deriveFont(Font.BOLD));
		PlayersjLabel.setForeground(Color.yellow);
		PlayersjLabel.setBounds(new Rectangle(11, 14, 179, 25));

		EleuronScenejLabel.setMaximumSize(new Dimension(300, 200));
		EleuronScenejLabel.setText("");
		EleuronScenejLabel.setBounds(new Rectangle(10, 70, 300, 200));
		EleuronScenejLabel
				.addMouseListener(new CreateMatchGUI_EleuronScenejLabel_mouseAdapter(
						this));

		EleuronScenejLabel.setIcon(new ImageIcon(Tools.loadBufferedImage("src"
				+ File.separator + "GUI" + File.separator + "Images"
				+ File.separator + "Eleuron.png")));

		NodrubazScenejLabel.setMaximumSize(new Dimension(300, 200));
		NodrubazScenejLabel.setText("");
		NodrubazScenejLabel.setBounds(new Rectangle(10, 280, 300, 200));
		NodrubazScenejLabel
				.addMouseListener(new CreateMatchGUI_NodrubazScenejLabel_mouseAdapter(
						this));

		NodrubazScenejLabel.setIcon(new ImageIcon(Tools.loadBufferedImage("src"
				+ File.separator + "GUI" + File.separator + "Images"
				+ File.separator + "Nodrubaz.png")));

		AsteroideScenejLabel.setMaximumSize(new Dimension(300, 200));
		AsteroideScenejLabel.setText("");
		AsteroideScenejLabel.setBounds(new Rectangle(630, 280, 300, 200));
		AsteroideScenejLabel
				.addMouseListener(new CreateMatchGUI_AsteroideScenejLabel_mouseAdapter(
						this));

		AsteroideScenejLabel.setIcon(new ImageIcon(Tools
				.loadBufferedImage("src" + File.separator + "GUI"
						+ File.separator + "Images" + File.separator
						+ "CampoAsteroides.png")));

		SilicosisScenejLabel.setMaximumSize(new Dimension(300, 200));
		SilicosisScenejLabel.setText("");
		SilicosisScenejLabel.setBounds(new Rectangle(320, 70, 300, 200));
		SilicosisScenejLabel
				.addMouseListener(new CreateMatchGUI_SilicosisScenejLabel_mouseAdapter(
						this));
		SilicosisScenejLabel.setIcon(new ImageIcon(Tools
				.loadBufferedImage("src" + File.separator + "GUI"
						+ File.separator + "Images" + File.separator
						+ "Silicosis.png")));

		OCB5ScenejLabel.setMaximumSize(new Dimension(34, 14));
		OCB5ScenejLabel.setText("");
		OCB5ScenejLabel.setBounds(new Rectangle(630, 70, 300, 200));
		OCB5ScenejLabel
				.addMouseListener(new CreateMatchGUI_OCB5ScenejLabel_mouseAdapter(
						this));

		OCB5ScenejLabel.setIcon(new ImageIcon(Tools.loadBufferedImage("src"
				+ File.separator + "GUI" + File.separator + "Images"
				+ File.separator + "OCB-5.png")));

		OkjButton.setBounds(new Rectangle(360, 500, 105, 25));
		OkjButton.setText("Ok");
		OkjButton.setMnemonic(KeyEvent.VK_O);
		OkjButton.setBorder(BorderFactory.createRaisedBevelBorder());
		OkjButton.setBackground(new Color(195, 206, 212));
		OkjButton.addActionListener(new CreateMatchGUI_OkjButton_actionAdapter(
				this));
		CanceljButton.setBounds(new Rectangle(486, 500, 105, 25));
		CanceljButton.setText("Cancelar");
		CanceljButton.setMnemonic(KeyEvent.VK_C);
		CanceljButton.setBorder(BorderFactory.createRaisedBevelBorder());
		CanceljButton.setBackground(new Color(195, 206, 212));
		CanceljButton
				.addActionListener(new CreateMatchGUI_CanceljButton_actionAdapter(
						this));
		DescriptionScene.setFont(new java.awt.Font("MonoSpaced", 0, 12));
		DescriptionScene.setForeground(Color.black);
		DescriptionScene.setBackground(new Color(195, 206, 212));
		DescriptionScene.setBorder(BorderFactory.createLoweredBevelBorder());
		DescriptionScene.setDisabledTextColor(Color.gray);
		DescriptionScene.setLineWrap(true);
		DescriptionScene.setWrapStyleWord(true);
		DescriptionScene.enable(true);
		DescriptionScene.setEditable(false);
		DescriptionScene.setBounds(new Rectangle(320, 280, 300, 200));
		DescriptionScene.setText("[Seleccione un escenario para jugar]");

		BackgroundjLabel.setIcon(new ImageIcon(Tools.loadBufferedImage("src"
				+ File.separator + "GUI" + File.separator + "Images"
				+ File.separator + "stars.png")));
		BackgroundjLabel.setText("");
		BackgroundjLabel.setBounds(new Rectangle(0, 0, 945, 565));

		this.setTitle("Crear partida: sala " + (room+1));
		this.getContentPane().add(ScenesjLabel, null);
		this.getContentPane().add(DescriptionScene, null);
		this.getContentPane().add(PlayersjLabel, null);
		this.getContentPane().add(EleuronScenejLabel, null);
		this.getContentPane().add(SilicosisScenejLabel, null);
		this.getContentPane().add(NodrubazScenejLabel, null);
		this.getContentPane().add(OCB5ScenejLabel, null);
		this.getContentPane().add(AsteroideScenejLabel, null);
		this.getContentPane().add(PlayersjSpinner, null);
		this.getContentPane().add(OkjButton, null);
		this.getContentPane().add(CanceljButton, null);
		this.getContentPane().add(BackgroundjLabel);
	}

	void OkjButton_actionPerformed(ActionEvent e) {		
		if (selectedScene == -1) {
			JOptionPane.showMessageDialog(frame,
					"Necesita elegir un escenario para poder jugar",
					"Scene Error", JOptionPane.WARNING_MESSAGE);
		} else {
			if (this.networkGame) {
				//roomgui.esperando=true;
				//Aqui se envia el paquete de crearPartida
				//y se espera a recibir la confirmacion.
				recibo.clear();
				numPlayers = ((Integer) PlayersjSpinner.getValue()).intValue();
				
				recibo = chat.makeCrearPartida(room, selectedScene, numPlayers);					
				chat.sendCrearPartida(recibo);				
				hilo();
//				Thread t = new Thread("RoomsThread") {
//					public void run(/* RoomsGUI r */) {
//						System.out.println("Dentro del room");
//
//						//Aqui metemos el bucle que esta recibiendo paquetes
//						// constantemente
//						// con los datos de las salas
//
//						while (!creada) {
//							//El mismo metodo que showMessageSalasTCP()
//							chat.showMessageCreaPartidaTCP();
//						}
//					}
//				};
//				t.start();
//				if (creada){
//					this.dispose();
//				WaitingGUI waitingGUI = new WaitingGUI(2, 2,
//						2, chat, descodificador, jugadores);
//				waitingGUI.setVisible(true);				
//				waitingGUI.listen();
//				}
			}

			if (!this.networkGame) {
				SplashGUI loading = new SplashGUI(this, Tools
						.loadBufferedImage("art" + File.separator
								+ "loading.png"));
				this.setVisible(false);
				loading.setVisible(true);
				loading.toFront();

				Thread t = new Thread("main") {
				    long millis=System.currentTimeMillis();
					public void run() {
						if (SelectedScene.equals("Eleuron")) {							
						    Stage stage = new StageEleuron();							
							beginning(stage);
						} else if (SelectedScene.equals("Silicosis")) {
							Stage stage = new StageSilicosis();
							//System.out.println(System.currentTimeMillis()-millis);
							beginning(stage);
						} else if (SelectedScene.equals("Nodrubaz")) {
							Stage stage = new StageNodrubaz();
							//System.out.println(System.currentTimeMillis()-millis);
							beginning(stage);
						} else if (SelectedScene.equals("OCB-5")) {
							Stage stage = new StageOcb5();
							beginning(stage);
						} else if (SelectedScene.equals("CampoAsteroides")) {
							Stage stage = new StageTest();
							beginning(stage);
						}
					}
				};
				t.start();
			}
		}
	}

	/**
	 * 
	 */
	private void hilo() {
		creada=false;
		Thread t = new Thread("createMatch") {
			public void run(/* RoomsGUI r */) {
				//Aqui metemos el bucle que esta recibiendo paquetes
				// constantemente
				// con los datos de las salas
				while (!creada) {
					//El mismo metodo que showMessageSalasTCP()
					chat.showMessageCreaPartidaTCP();
				}
			}
		};
		t.start();
		// TODO Auto-generated method stub
		
	}

	void CanceljButton_actionPerformed(ActionEvent e) {
		if(this.networkGame){
			RoomsGUI roomsGUI = new RoomsGUI(chat);
			roomsGUI.setVisible(true);
			this.dispose();
			roomsGUI.listen();		
		}
		else{
			this.dispose();
			System.exit(0);
		}
	}

	void EleuronScenejLabel_mouseClicked(MouseEvent e) {
		this.SelectedScene = "Eleuron";
		//***********************************
			//this.selectedScene=ELEURON_STAGE;

		//****************************
		this.selectedScene = Constants.ELEURON_STAGE;
		updateText(SelectedScene);
		if (labelActivated != null)
			labelActivated.setBorder(BorderFactory
					.createLineBorder(Color.BLACK));
		labelActivated = EleuronScenejLabel;
		labelActivated.setBorder(BorderFactory
				.createLineBorder(Color.YELLOW, 2));

	}

	void SilicosisScenejLabel_mouseClicked(MouseEvent e) {
		this.SelectedScene = "Silicosis";

		this.selectedScene = Constants.SILICOSIS_STAGE;

		updateText(SelectedScene);
		if (labelActivated != null)
			labelActivated.setBorder(BorderFactory
					.createLineBorder(Color.BLACK));
		labelActivated = SilicosisScenejLabel;
		labelActivated.setBorder(BorderFactory
				.createLineBorder(Color.YELLOW, 2));
	}

	void OCB5ScenejLabel_mouseClicked(MouseEvent e) {
		this.SelectedScene = "OCB-5";
		this.selectedScene = Constants.OCB5_STAGE;
		updateText(SelectedScene);
		if (labelActivated != null)
			labelActivated.setBorder(BorderFactory
					.createLineBorder(Color.BLACK));
		labelActivated = OCB5ScenejLabel;
		labelActivated.setBorder(BorderFactory
				.createLineBorder(Color.YELLOW, 2));
	}

	void AsteroideScenejLabel_mouseClicked(MouseEvent e) {
		this.SelectedScene = "CampoAsteroides";
		this.selectedScene = Constants.ASTEROIDS_STAGE;
		updateText(SelectedScene);
		if (labelActivated != null)
			labelActivated.setBorder(BorderFactory
					.createLineBorder(Color.BLACK));
		labelActivated = AsteroideScenejLabel;
		labelActivated.setBorder(BorderFactory
				.createLineBorder(Color.YELLOW, 2));
	}

	void NodrubazScenejLabel_mouseClicked(MouseEvent e) {
		this.SelectedScene = "Nodrubaz";
		this.selectedScene = Constants.NODRUBAZ_STAGE;
		updateText(SelectedScene);
		if (labelActivated != null)
			labelActivated.setBorder(BorderFactory
					.createLineBorder(Color.BLACK));
		labelActivated = NodrubazScenejLabel;
		labelActivated.setBorder(BorderFactory
				.createLineBorder(Color.YELLOW, 2));
	}

}

class CreateMatchGUI_OkjButton_actionAdapter implements
		java.awt.event.ActionListener {

	CreateMatchGUI adaptee;

	CreateMatchGUI_OkjButton_actionAdapter(CreateMatchGUI adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.OkjButton_actionPerformed(e);
	}
}

class CreateMatchGUI_CanceljButton_actionAdapter implements
		java.awt.event.ActionListener {

	CreateMatchGUI adaptee;

	CreateMatchGUI_CanceljButton_actionAdapter(CreateMatchGUI adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.CanceljButton_actionPerformed(e);
	}
}

class CreateMatchGUI_EleuronScenejLabel_mouseAdapter extends
		java.awt.event.MouseAdapter {

	CreateMatchGUI adaptee;

	CreateMatchGUI_EleuronScenejLabel_mouseAdapter(CreateMatchGUI adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.EleuronScenejLabel_mouseClicked(e);
	}
}

class CreateMatchGUI_SilicosisScenejLabel_mouseAdapter extends
		java.awt.event.MouseAdapter {
	CreateMatchGUI adaptee;

	CreateMatchGUI_SilicosisScenejLabel_mouseAdapter(CreateMatchGUI adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.SilicosisScenejLabel_mouseClicked(e);
	}
}

class CreateMatchGUI_OCB5ScenejLabel_mouseAdapter extends
		java.awt.event.MouseAdapter {
	CreateMatchGUI adaptee;

	CreateMatchGUI_OCB5ScenejLabel_mouseAdapter(CreateMatchGUI adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.OCB5ScenejLabel_mouseClicked(e);
	}
}

class CreateMatchGUI_AsteroideScenejLabel_mouseAdapter extends
		java.awt.event.MouseAdapter {

	CreateMatchGUI adaptee;

	CreateMatchGUI_AsteroideScenejLabel_mouseAdapter(CreateMatchGUI adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.AsteroideScenejLabel_mouseClicked(e);
	}
}

class CreateMatchGUI_NodrubazScenejLabel_mouseAdapter extends
		java.awt.event.MouseAdapter {
	CreateMatchGUI adaptee;

	CreateMatchGUI_NodrubazScenejLabel_mouseAdapter(CreateMatchGUI adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.NodrubazScenejLabel_mouseClicked(e);
	}
}
