/*
 * Created on 01-abr-2005
 */
package game.logicEngine;

/**
 * @author CarlosG
 * 
 * Esta clase se aplica a la lnhgica de los jugadores, ya que necesita mnhs
 * atributos.
 */
public class LogicActorPlayer extends LogicActor {
	public static final int LIFE_INITIAL_VALUE = 500;

	//public static final int LIFE_INITIAL_VALUE = 30;

	/**
	 * Representa la vida actual del jugador
	 */
	protected int life;

	/**
	 * Almacena la puntuacinhn del jugador en la partida en curso.
	 */
	protected int score;

	/**
	 * Representa el nombre de usuario del jugador
	 */
	protected String userID;

	/**
	 * Almacena la direccinhn IP del cliente del jugador.
	 */
	protected String userIP;

	protected boolean isActivePlayer;

	/**
	 * Arma actual / Convenio: / decenas: tipo arma / 1-rectilineo /
	 * 2-parabolico / 3-rebotes / 4-de cerca / unidades: potencia (1-3) / /
	 * estos valores estan reflejados en ctes.java / ej: 21 = parabolico de
	 * portencia 1
	 */
	//TODO: cambiar la inicializacion de aki
	protected int currentWeapon = 21;

	public LogicActorPlayer(String userID, String userIP) {
		super();
		initLogicActorPlayer(userID, userIP);
	}

	public void initLogicActorPlayer(String userID, String userIP) {
		super.initLogicActor();
		isActivePlayer = false;
		life = LIFE_INITIAL_VALUE;
		score = 0;
		this.userID = userID;
		this.userIP = userIP;
	}

	/**
	 * @return Returns the userIP.
	 */
	public String getUserIP() {
		return userIP;
	}

	/**
	 * @return Returns the userID.
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @param score
	 *            The score to set.
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return Returns the score.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param life
	 *            The life to set.
	 */
	public void setLife(int life) {
		this.life = life;
	}

	/**
	 * @return Returns the life.
	 */
	public int getLife() {
		return life;
	}

	/**
	 * @param userID
	 *            The userID to set.
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * @param userIP
	 *            The userIP to set.
	 */
	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}

	public void updateState() {
		super.updateState();

		//si la vida==0 y ha terminado de ejecutar sus animaciones
		//de danho se le pone muriendo
		if (life <= 0 && actualState != LogicActor.STATE_HIT_ON_AIR
				&& actualState != LogicActor.STATE_HIT_ON_GROUND)
			actualState = LogicActor.STATE_DYING;
	}

	/**
	 * @return Returns the isActivePlayer.
	 */
	public boolean isActivePlayer() {
		return isActivePlayer;
	}

	/**
	 * @param isActivePlayer
	 *            The isActivePlayer to set.
	 */
	public void setActivePlayer(boolean isActivePlayer) {
		this.isActivePlayer = isActivePlayer;
	}

	public int getCurrentWeapon() {
		return currentWeapon;
	}

	public void setCurrentWeapon(int currentWeapon) {
		this.currentWeapon = currentWeapon;
	}

	public int getCurrentWeaponType() {
		return currentWeapon / 10;
	}

	public int getCurrentWeaponPower() {
		return currentWeapon % 10;
	}

}