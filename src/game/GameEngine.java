package game;

import game.actors.ActorPlayer;
import game.actors.ActorPlayerBiomorph;
import game.actors.ActorPlayerEleo;
import game.actors.ActorPlayerG_Marine;
import game.actors.ActorPlayerNinfa;
import game.actors.ActorPlayerScrapper;
import game.actors.ammo.ActorAmmo;
import game.logicEngine.LogicActor;
import game.logicEngine.LogicActorPlayer;
import game.stages.*;
import game.stages.Stage;
import game.stages.StageInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import game.graphicsEngine.*;

import network.Chat;
import network.ClientPlayer;
import network.ClientServer;
import org.lwjgl.Sys;
import util.ColoursDefinitions;
import util.Constants;
import util.Direction;
import GUI.*;
import GUI.ChooseGUI;
import GUI.MainWindowGUI;
import GUI.WeaponJButton;

import java.util.Iterator;
import java.util.ListIterator;
import util.IdAndRace;

/*
 * He metido el temporizador de alta resolucinhn de las librerias lwjgl que
 * se encuentran en el directorio lib
 */

/**
 * Motor Principal del juego
 *  
 */
public class GameEngine implements GameEngineInterface {

	/**
	 * Indica el nombre del sistema operativo sobre el que se estnh ejecutando
	 */
	public static String osName = "";

	//duracinhn de un frame en ms

	private final int DELAY_TIME = 20;

	//indica si el juego se estnh ejecutando
	private boolean gameRunning = true;

	/*
	 * Lleva la cuenta del nnhmero de ciclos del bucle principal ejecutados
	 */
	private long loopCycles = 0;

	//Pantalla del juego
	private Screen screen;

	//Escenario del juego
	private StageInterface stage;

	//Muestra el nnhmero de frames por segundo
	protected String fps = "FPS=0";

	protected String time = "--";

	protected String messageString = "--";

	//Tiempo de actualizacinhn de los fps en ms
	protected long fpsUpdate = 250;

	//Gestor de los dispositivos de entrada (teclado y ratnhn)
	private InputManager inputManager;

	//Ventana principal del juego
	private MainWindowGUI window;

	//Frontend del chat
	private ChatFrontend chatUI;
	
	private Chat chat;
	
	private List arrListOfIdsAndRaces;

	//Barra de fuerza del disparo
	private PowerBar powerBar;

	//Barra de fuerza del viento
	private WindBar windBar;

	//Indica si se ha de "congelar" el dibujado de la pantalla del juego
	private boolean freezeDrawing = false;

	//Gestiona el sistema de turnos
	private GameInterface currentGame;

	//Indica si estnh habilitado el control del personaje mediante el teclado
	private static boolean moveEnabled = true;

	//Constante que indica el tiempo mnhximo de pulsacinhn del disparo
	protected static final long MAX_SHOT_TIME = InputManager.getMAX_SHOT_TIME();

	private String myID;

	private boolean network;

	private int roomGame;

	private static AnimSet animSet;

	/**
	 * Constructor
	 * 
	 * @param stage
	 *            escenario del juego
	 * @param w
	 *            ventana principal del juego
	 * @param screen
	 *            pantalla en la que se pinta la accinhn del juego
	 * @param cs
	 *            red
	 * @param arrListOfIdsAndRaces
	 *            lista con pares id-raza
	 */
	public GameEngine(Stage stage, Screen screen, List arrListOfIdsAndRaces,
			boolean network,Chat chat,int room) {
		
		//se crea el array de animaciones
		GameEngine.animSet = new AnimSet();

		powerBar = new PowerBar(InputManager.MAX_SHOT_TIME);
		windBar = new WindBar(stage.getEnvironment().getMAXWIND());


		initGameEngine(stage, screen, arrListOfIdsAndRaces, network,chat,room);

	}//GameEngine()

	/**
	 * Inicia la clase.
	 * 
	 * @param stage
	 *            escenario del juego
	 */
	public void initGameEngine(Stage stage, Screen screen,
			List arrListOfIdsAndRaces, boolean network,Chat chat,int room) {
		
		this.arrListOfIdsAndRaces=arrListOfIdsAndRaces;
	    //se cargan las anims
		GameEngine.animSet.init(arrListOfIdsAndRaces);


		/*
		 * Instala un el RepaintManager nulo que ignora las peticiones de
		 * repintado
		 */
		//NullRepaintManager.install();
		//RepaintManager.currentManager(window).setDoubleBufferingEnabled(false);
		GameEngine.osName = System.getProperty("os.name");
		this.stage = stage;
		this.network = network;
		this.chat=chat;
		this.roomGame = room;
		
		//        chat = window.getChat();
		//        chat.setChat(c);

		inputManager = new InputManager();
		
		ClientPlayer player = null;
		ActorPlayer actor = null;

		if (network) {
			for (int i = 0; i < arrListOfIdsAndRaces.size(); i++) {

				player = (ClientPlayer)arrListOfIdsAndRaces.get(i);

				int playerRace = player.getRace();
				String userID = player.getName();
				int posicion = player.getPosicion();
				System.out.println("La posicion es " + posicion);
				
				switch (playerRace) {
				case Constants.RACE_SCRAPER:{//"Scraper"					
					actor = new ActorPlayerScrapper(stage, userID);
				    actor.getLogic().setPosition(posicion,1);
					actor.getBehavior().initPhysicPlayer(actor.getLogic(),stage);}
					//	System.out.println("creo actor scrapper");}
					break;
				case Constants.RACE_BIOMORPH:{//"Biomorph"
					actor = new ActorPlayerBiomorph(stage, userID);
					actor.getLogic().setPosition(posicion,1);
					actor.getBehavior().initPhysicPlayer(actor.getLogic(),stage);}
					//		System.out.println("creo actor biomorph");}
					break;
				case Constants.RACE_GMARINE:{//"GMarine"
					actor = new ActorPlayerG_Marine(stage, userID);
					actor.getLogic().setPosition(posicion,1);
					actor.getBehavior().initPhysicPlayer(actor.getLogic(),stage);}
					//		System.out.println("Voy a creo Marine");}
					break;
				case Constants.RACE_NINFA:{//"Ninfa"
					actor = new ActorPlayerNinfa(stage, userID);
					actor.getLogic().setPosition(posicion,1);
					actor.getBehavior().initPhysicPlayer(actor.getLogic(),stage);}
					//	System.out.println("Voy a crear ninfa");					
					break;
				case Constants.RACE_ELEO:	{//"Eleo"
					actor = new ActorPlayerEleo(stage, userID);
					actor.getLogic().setPosition(posicion,1);
					actor.getBehavior().initPhysicPlayer(actor.getLogic(),stage);}
					//		System.out.println("creo actor eleo");}
					break;
				default:
				    System.out.println("initGameEngine - Raza desconocida");
		    		break;
				}				
				//Anhade en actorsArray todos los actores(jugadores)
				stage.addActor(actor);
			}		
			currentGame = new NetworkGame(stage, inputManager, roomGame, chat);			
		} else {
			
			Iterator it = arrListOfIdsAndRaces.listIterator();
			
			while (it.hasNext()) {
			    player = (ClientPlayer) (it.next());
			    
				String userID = player.getName();
				
				switch (player.getRace()) {
				case Constants.RACE_SCRAPER:
					//"Scraper"
					actor = new ActorPlayerScrapper(stage, userID);

					break;
				case Constants.RACE_BIOMORPH:
					//"Biomorph"
					actor = new ActorPlayerBiomorph(stage, userID);
					break;

				case Constants.RACE_GMARINE:
					//"GMarine"
					actor = new ActorPlayerG_Marine(stage, userID);
					break;
				case Constants.RACE_NINFA:
					//"Ninfa"
					actor = new ActorPlayerNinfa(stage, userID);
					break;
				case Constants.RACE_ELEO:
					//"Eleo"
					actor = new ActorPlayerEleo(stage, userID);
					break;
			    default:
			        System.out.println("initGameEngine - Raza desconocida");
			    	break;
				}
				stage.addActor(actor);
			}
			currentGame = new LocalGame(stage, inputManager);
		}

		this.screen = screen;

		screen.addKeyListener(inputManager);
		screen.addMouseListener(inputManager);
		screen.addMouseMotionListener(inputManager);

		powerBar.setLocation(20, 445);
		screen.add(powerBar);
		windBar.setLocation(516, 445);
		screen.add(windBar);
		//screen.add(chat);
		//screen.add(stage.getStageMap());
		window = new MainWindowGUI(screen, currentGame);
		window.setVisible(true);

		//        chat = window.getChat();
		//        chat.setChat(new Chat(cs));

		screen.setVisible(true);
		screen.requestFocus();
		screen.setCentered(stage,currentGame.getActivePlayer());

	}//init()

	/**
	 * Dibuja el juego en un objeto ScreenInterface
	 * 
	 * @param g
	 *            el objeto Graphics en el que se pinta el juego
	 */
	public void draw(Screen s) {
		Graphics2D g2 = (Graphics2D) s.getScreenGraphics();
		int x = 0;
		int y = 0;
		/*
		 * g2.setColor(ColoursDefinitions.LIGHT_BLUE); g2.fillRect(0, 0,
		 * s.getXRes(), s.getYRes());
		 */
		stage.draw(s);
		g2.setColor(Color.YELLOW);

		//TODO: nhapa. esto deberia estar
		//		en Game.changeTurnInterlude()
		if (currentGame.getTurnState() == Constants.INTERLUDE) {
			// crear del mensaje a mostrar
			messageString = "Turno de "
					+ ((LogicActorPlayer) currentGame.getActivePlayer()
							.getLogic()).getUserID();
			// calcular coordenadas en pantalla del mensaje
			Font oldFont = g2.getFont();
			Font newFont = oldFont.deriveFont(Font.BOLD, 30f);
			Rectangle2D stringSize = newFont.getStringBounds(messageString, g2
					.getFontRenderContext());

			x = (int) (screen.getWidth() / 2 - stringSize.getWidth() / 2);
			y = 240;

			// mostrar mensaje
			drawMessage(g2, messageString, x, y);

		}

		if (!gameRunning && currentGame.getWinner() == Constants.WINNER) {
			// crear del mensaje a mostrar
			messageString = "Ha ganado "
					+ ((LogicActorPlayer) currentGame.getActorWinner()
							.getLogic()).getUserID();
			// calcular coordenadas en pantalla del mensaje
			Font oldFont = g2.getFont();
			Font newFont = oldFont.deriveFont(Font.BOLD, 30f);
			Rectangle2D stringSize = newFont.getStringBounds(messageString, g2
					.getFontRenderContext());

			x = (int) (screen.getWidth() / 2 - stringSize.getWidth() / 2);
			y = 160;

			// mostrar mensaje
			drawMessage(g2, messageString, x, y);

		}

		else if (!gameRunning
				&& currentGame.getWinner() == Constants.DRAW_MATCH) {
			// crear del mensaje a mostrar
			messageString = "Hubo empate";
			// calcular coordenadas en pantalla del mensaje
			Font oldFont = g2.getFont();
			Font newFont = oldFont.deriveFont(Font.BOLD, 30f);
			Rectangle2D stringSize = newFont.getStringBounds(messageString, g2
					.getFontRenderContext());

			x = (int) (screen.getWidth() / 2 - stringSize.getWidth() / 2);
			y = 160;

			// mostrar mensaje
			drawMessage(g2, messageString, x, y);
		}

		if (currentGame.getTurnState() == Constants.PLAYING) {
			g2.drawString(time, 615, 20);

			if (currentGame.getTurnTime() <= 3) {
				Font oldFont = g2.getFont();
				Font newFont = oldFont.deriveFont(Font.BOLD, 30f);
				Rectangle2D stringSize = newFont.getStringBounds(time, g2
						.getFontRenderContext());
				x = (int) (screen.getWidth() / 2 - stringSize.getWidth() / 2);
				y = 240;
				drawMessage(g2, time, x, y);

			}

		}
		g2.drawString(fps, 575, 35);
		g2.drawString("Disparo", 20, 440);
		g2.drawString("Viento", 516, 440);
		//g2.drawString(inputManager.getMousePosition().toString(), 400, 30);

		/*
		 * Vuelca el buffer sobre el componente de la pantalla de juego. Hay que
		 * desactivarlo si se dibujan todos los componentes de la ventana como
		 * se hace a continuacinhn.
		 */
		s.showScreen();

		/*
		 * Pinta todos los componentes de la ventana. Necesario para superponer
		 * componentes sobre la pantalla de juego
		 */

		/*
		 * window.getLayeredPane().paintComponents(window.getBufferGraphics());
		 * window.showWindow();
		 */

	}//draw()

	private void drawMessage(Graphics2D g2, String message, int x, int y) {
		Font oldFont = g2.getFont();
		Font newFont = oldFont.deriveFont(Font.BOLD, 30f);
		Color oldColor = g2.getColor();
		g2.setFont(newFont);
		g2.setColor(ColoursDefinitions.YELLOW_ALPHA);
		g2.drawString(message, x, y);
		g2.setFont(oldFont);
		g2.setColor(oldColor);
	}

	/**
	 * Reproduce los sonidos del juego
	 */
	public void playSounds() {
		stage.playSounds();
	}//playSounds()

	/**
	 * Ejecuta el juego
	 */
	public void run() {

		long startTime; //guarda el momento de inicio de la iteracinhn en
		// resolucinhn del reloj del sistema
		long elapsedTime; //guarda la duracinhn de la iteracinhn en
		// resolucinhn
		// del reloj del sistema
		long elapsedTimeMS; //guarda la duracinhn de la iteracinhn en
		// mmilisegundos

		long fpsInterval = fpsUpdate;

		long fpsCount = 0;

		long timerRes = Sys.getTimerResolution(); //resolucinhn del reloj del
		// sistema
		int msFactor = (int) (timerRes / 1000); //factor de transformacinhn de
		// resolucinhn del sistema a
		// milisegundos
		
		screen.setCentered(stage,currentGame.getActivePlayer());
		//chat.sendConfirm(roomGame);
		//System.out.println("Envio paquete de confirmacion de partida");
		//bucle principal del juego
		if(network) chat.sendConfirm(roomGame);
		while (gameRunning) {

			//Guarda el instante de inicio de la iteracinhn
			startTime = Sys.getTime();
			//startTime = System.currentTimeMillis();

			//Procesa la entrada producida en el GUI
			processGUI();

			//Procesa la entrada del teclado y el ratnhn
			if (!freezeDrawing && !screen.isMovingToPosition())
				processInput();

			//Actualiza el estado de la lnhgica del juego
			updateState();

			processTransition();

			//Dibuja el estado del juego en panatalla
			if (!freezeDrawing)
				draw(screen);

			//Reproduce los sonidos
			playSounds();

			//Calcula la duracinhn de la iteracinhn del bucle
			elapsedTime = Sys.getTime() - startTime;

			//          Calcula la duracinhn de la iteracinhn del bucle en milisegundos
			elapsedTimeMS = elapsedTime / msFactor;
			//elapsedTime = System.currentTimeMillis() - startTime;

			//Ponde a dormir el hilo durante el tiempo sobrante del frame
			try {
				Thread.sleep(Math.max(DELAY_TIME - elapsedTimeMS, 0));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// System.out.println("loop time: " + elapsedTimeMS + " ms");

			//Calcula la duracinhn de la iteracinhn con el tiempo sobrante
			elapsedTime = Sys.getTime() - startTime;
			elapsedTimeMS = elapsedTime / msFactor;
			//elapsedTime = System.currentTimeMillis() - startTime;

			//Muestra los frames por segundo en pantalla
			fpsInterval = fpsInterval - elapsedTimeMS;
			if (fpsInterval <= 0) {
				fpsCount = (1000 / elapsedTimeMS);
				fps = "FPS= " + fpsCount;
				fpsInterval = fpsUpdate;
			}

			time = Integer.toString(currentGame.getTurnTime());

			//Incrementa el contador del nnhmero de iteraciones del bucle
			loopCycles++;
						

		}//while

		/*
		 * TODO nhapa para finalizar partida
		 */

		terminate();

	}//run()
	
	
	/**
	 * Actualiza el estado del juego
	 */
	public void updateState() {
		screen.updateState(stage);
		currentGame.updateState(); //ejecuta el mnhtodo updateState() del
		// objeto
		// stage
		if (currentGame.getWinner() != -1) {
			this.gameRunning = false;
		}
		//stage.updateState();

		/*
		 * Actualiza el chat. Actualmente desactivado
		 */
		//chat.updateState();
	}//updateState

	/**
	 * Procesa la entrada (teclado y ratnhn)
	 */
	private void processInput() {
		/*
		 * int x=inputManager.getScreenDragX(); int
		 * y=inputManager.getScreenDragY();
		 * 
		 * if (x!=0 && y!=0){ x=screen.getOffsetX()-x; y=screen.getOffsetY()-y;
		 * 
		 * if ((x>=0) && (x <=(stage.getSizeX()-screen.getXRes())))
		 * screen.setOffsetX(x);
		 * 
		 * if ((y>=(-100)) && (y <=(stage.getSizeY()-screen.getYRes())))
		 * screen.setOffsetY(y);
		 * 
		 * inputManager.setScreenDrag(0,0); }
		 */

		/*
		 * Procesamiento del ratnhn
		 */

		int mouseX = inputManager.getMousePositionX();
		int mouseY = inputManager.getMousePositionY();

		int screenMargin = screen.getMoveMargin();
		int screenSpeed = screen.getMoveSpeed();

		if (mouseX > (screen.getXRes() - screenMargin)) {
			int offsetX = screen.getOffsetX();

			if (offsetX < (stage.getSizeX() - screen.getXRes()))
				screen.setOffsetX(offsetX + screenSpeed);
		} else if (mouseX < (screenMargin)) {
			int offsetX = screen.getOffsetX();

			if (offsetX > 0)
				screen.setOffsetX(offsetX - screenSpeed);
		}

		if (mouseY > (screen.getYRes() - screenMargin)) {
			int offsetY = screen.getOffsetY();

			if (offsetY < (stage.getSizeY() - screen.getYRes()))
				screen.setOffsetY(screen.getOffsetY() + screenSpeed);
		} else if (mouseY < (screenMargin)) {
			int offsetY = screen.getOffsetY();

			if (offsetY > 0)
				screen.setOffsetY(screen.getOffsetY() - screenSpeed);
		}
		//System.out.println(screen.getOffsetX());
		//System.out.println(screen.getOffsetY());

		/*
		 * Procesamiento del teclado
		 */

		//TODO: las 2 lineas siguientes se deberian hacer desde
		//Game.changeTurnUp() y no hacerlas cada frame
		ActorPlayer player = currentGame.getActivePlayer();
		float speed = player.getLogic().getPower();
		long shotTime = 0;

		if (moveEnabled) {
			if (inputManager.getLeftKey() && !player.isShooting()) {
				player.walk(stage, Direction.LEFT);
				//System.out.println("Input Manager");
			} else if (inputManager.getRightKey() && !player.isShooting()) {
				player.walk(stage, Direction.RIGHT);
			} else if (inputManager.getJumpKey() && !player.isShooting()) {
				inputManager.setJumpKey(false);
				if (player.getLogic().getActualState()==LogicActor.STATE_STILL||player.getLogic().getActualState()==LogicActor.STATE_MOVING) {
					player.getLogic().setActualState(LogicActor.STATE_PREJUMP);
				}
			} else {
				player.stop();
			}
			if (player.getLogic().getActualState() == LogicActor.STATE_JUMP)
				player.jump(stage);
			if (inputManager.getCannonUpKey()) {
				player.moveTargetUp(true);
			} else if (inputManager.getCannonDownKey()) {
				player.moveTargetDown(true);
			} else {
				player.moveTargetUp(false);
				player.moveTargetDown(false);
			}
			//calculamos el tiempo acumulado de pulsacion hasta el momento
			long initShotTime = inputManager.getInitShotTime();
			shotTime = System.currentTimeMillis()
					- inputManager.getInitShotTime();
			if ((initShotTime != -1) && (shotTime >= MAX_SHOT_TIME)) {
				//otra forma de ponerlo a false???
				inputManager.setShot(false);
				shotTime = MAX_SHOT_TIME;
				//llamar al metodo shot() del player
				player.shot(stage, shotTime);
				inputManager.setTotalShotTime(0);
				inputManager.setInitShotTime(-1);
			}
		}

		/*
		 * puesto que los eventos de teclado no se comportan bien en la
		 * implmentacinhn de la mnhquina virtual de linux, utilizamos la
		 * libreria LWJGL para calcular la pulsacinhn de disparo en linux
		 */
		/* procesa la pulsacinhn de disparo en sistemas no Linux */
		if (inputManager.getShot() && !osName.equals("Linux")) {
			// otra forma de ponerlo a false???
			inputManager.setShot(false);
			inputManager.setShotKey(false);
			shotTime = inputManager.getTotalShotTime();
			//llamar al metodo shot() del player
			player.shot(stage, shotTime);
			inputManager.setTotalShotTime(0);
		}
		/* procesa la pulsacinhn del disparo en Linux */
		else if (inputManager.getShotKey() && osName.equals("Linux")) {
			long releasedTime = System.currentTimeMillis()
					- inputManager.getFinalShotTime();
			if (releasedTime > 260) {
				inputManager.setShotKey(false);
				shotTime = inputManager.getTotalShotTime();
				//llamar al metodo shot() del player
				player.shot(stage, shotTime);
				inputManager.setTotalShotTime(0);
			}
		}//if

		if (inputManager.getShotKey()) {
			powerBar.setShotTime(shotTime);
		} else
			powerBar.setShotTime(0);

		//La powerBar se pinta dependiendo del tipo de disparo
		if (player.getCurrentWeaponType() == Constants.WEAP_RECTILINEAR
				|| player.getCurrentWeaponType() == Constants.WEAP_MELEE
				|| player.getCurrentWeaponType() == Constants.WEAP_SPECIAL)
			powerBar.setVisible(false);
		else
			powerBar.setVisible(true);

		//barra de viento
		float wind = this.stage.getEnvironment().getWind();
		windBar.setWind(wind);
	}//processInput()

	/*
	 * Procesa la entrada generada en el GUI
	 */
	private void processGUI() {
		if (!freezeDrawing && window.isFreezeDrawing()) {
			freezeDrawing = true;
			window.repaint();
		} else if (freezeDrawing && !window.isFreezeDrawing()) {
			freezeDrawing = false;
		}

		if (window.isNewGame()) {
			this.gameRunning = false;

		}

	}//processGUI

	/*
	 * Procesa las acciones que se deben realizar durante una transicion de un
	 * jugador a otro
	 */
	private void processTransition() {
		moveScreen();
		changeGUI();
		currentGame.setTurnTransition(false);
	}

	private void changeGUI() {
		if (currentGame.isTurnTransition()) {
			window.changePlayerGUI(currentGame.getActivePlayer());
		}
	}

	private void moveScreen() {
		ActorPlayer actor = currentGame.getActivePlayer();
		ActorAmmo projectile = stage.getActiveProjectile();
		LogicActorPlayer logic = (LogicActorPlayer) actor.getLogic();
		int x = actor.getLogic().getX() - screen.getSize().width / 2
				+ actor.getLogic().getWidth() / 2;
		int y = actor.getLogic().getY() - screen.getSize().height / 2
				+ actor.getLogic().getHeight() / 2;

		/*
		 * TODO nhapas para mover la cnhmara
		 */
		if (currentGame.isTurnTransition()) {

			if (x < 0)
				x = 0;
			else if (x > (stage.getSizeX() - 1 - screen.getWidth()))
				x = stage.getSizeX() - 1 - screen.getWidth();

			if (y < 0)
				y = 0;
			else if (y > (stage.getSizeY() - 1 - screen.getHeight()))
				y = stage.getSizeY() - 1 - screen.getHeight();

			screen.moveToPosition(x, y, 10);

		}
		/*
		 * TODO nhapon para detectar que hay un proyectil y que hay que
		 * seguirlo
		 */
		else if (projectile != null
				&& projectile.getLogic().getActualState() == LogicActor.STATE_MOVING) {
			screen.setCentered(stage, projectile);
		} else if (logic.getActualState() >= LogicActorPlayer.STATE_MOVING
				|| (inputManager.getCannonDownKey())
				|| (inputManager.getCannonUpKey())) {
			screen.setCentered(stage, actor);
		}

	}

	/**
	 * @return Returns the moveEnabled.
	 */
	public static boolean isMoveEnabled() {
		return moveEnabled;
	}

	/**
	 * @param moveEnabled
	 *            The moveEnabled to set.
	 */
	public static void setMoveEnabled(boolean moveEnabled) {
		GameEngine.moveEnabled = moveEnabled;
	}

	/*public void makePacket() throws IOException {
		//FileOutputStream fos=new FileOutputStream("logic.out");
		ObjectOutputStream oos = new ObjectOutputStream(chatUI.getChat()
				.getSocket().socket().getOutputStream());
		LogicActor la = (LogicActor) currentGame.getActivePlayer().getLogic();
		oos.writeObject(la);
		oos.flush();
		oos.close();
		//fos.close();
	}

	public void processPacket() throws IOException, ClassNotFoundException {
		//FileInputStream fis=new FileInputStream("logic.out");
		ObjectInputStream ois = new ObjectInputStream(chatUI.getChat()
				.getSocket().socket().getInputStream());
		LogicActor la = (LogicActor) ois.readObject();
		ois.close();
		//fis.close();
		currentGame.getActivePlayer().setLogic(la);
	}*/

	private void terminate() {
	    this.stage.getAudioManager().setMusicOn(false);
		this.currentGame.getTimer().cancel();
		
		if (currentGame.getWinner() == -1) {
			
			window.dispose();
			
			ChooseGUI chooseGUI1 = new ChooseGUI();
			chooseGUI1.setVisible(true);
		}
		else{		    
		    EndGameGUI eg = new EndGameGUI(window,arrListOfIdsAndRaces);
		    eg.setVisible(true);
		    
		}
		System.gc(); //llama explicitamente al recolector de basura
	}
	
	
	public static AnimSet getAnimSet() {		
		return GameEngine.animSet;
	}

}//class GameEngine
