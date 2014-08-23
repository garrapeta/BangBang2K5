package game.actors;

import game.*;
import game.actors.ammo.ActorAmmo;
import game.actors.ammo.projParabolic.ActorAmmoProjParabolicBiomorph;
import game.actors.ammo.projParabolic.ActorAmmoProjParabolicEleo;
import game.actors.ammo.projParabolic.ActorAmmoProjParabolicG_Marine;
import game.actors.ammo.projParabolic.ActorAmmoProjParabolicNinfa;
import game.actors.ammo.projParabolic.ActorAmmoProjParabolicScrapper;
import game.graphicsEngine.GraphicsEngineActorPlayer;
import game.logicEngine.*;
import game.races.RaceInterface;
import game.stages.*;
import weapons.PhysicAmmo;
import weapons.PhysicAmmoInterface;
import weapons.PhysicPlayer;
import util.Constants;

/*
 * SpritePlayer.java
 */

/**
 * Clase que implementa la funcionalidad comnhn de los actores de los jugadores
 */
public abstract class ActorPlayer extends Actor {

	protected ActorTarget target;

	/**
	 * Motor grnhfico
	 */
	protected GraphicsEngineActorPlayer graphicsEngine;

	protected PhysicPlayer behavior;

	protected RaceInterface race;

	protected int raceCode;

	protected final float MAX_SHOT_POWER = 14f;

	protected final long MAX_SHOT_TIME = InputManager.getMAX_SHOT_TIME();

	protected final int INITIAL_SHOOTS = 1;

	protected boolean shooting;

	protected boolean escaping;

	protected int shootCounter;

	protected ActorAmmo newAmmo;

	private boolean jumping = false;
	
    protected int damage_melee1;
    
    protected int damage_melee2;
    
    protected int damage_melee3;
    
    protected int radius_melee1;
    
    protected int radius_melee2;
    
    protected int radius_melee3;

	public ActorPlayer(RaceInterface race) {
		super();
		//TODO Cnhmo meto la lnhgica??
		logic = new LogicActorPlayer("el Palankas", "0.0.0.0");
		this.race = race;
		this.shooting = false;

		//TODO meter el valor de shootCounter desde fuera, al seleccionar un
		// arma
		this.shootCounter = INITIAL_SHOOTS;
		logic = (LogicActorPlayer) (super.logic);
	}

	public void updateState(StageInterface stage) {

		logic.updateState();
		target.getLogic().getDirection().setDirection(
				logic.getDirection().getDirectionValue());

		if (logic.needsPhysics()) {
			if (behavior != null)
				behavior.nextStep();
		}

		switch (this.getLogic().getActualState()) {
		case LogicActor.STATE_ENDSHOOTING:
		    //this.setShooting(false);
			//Cuando se acaba el final de la animacion de disparo
			//es cuando se anhade el proyectil o se mete el capon melee
			switch (getCurrentWeaponType()) {
			case Constants.WEAP_PARABOLIC:
			case Constants.WEAP_GRENADE:
			case Constants.WEAP_RECTILINEAR:
				stage.addActor(newAmmo);
				newAmmo.logic.setActualState(LogicActor.STATE_MOVING);
				logic.setActualState(LogicActor.STATE_STILL);
				break;
			case Constants.WEAP_MELEE:				
				if (this.shootCounter>0) {
					meleeShot(stage, getCurrentMeleeRadius(),
							getCurrentMeleePower());					
				}
				break;
			default:
				System.out.println("Error codigo de arma");
				break;

			}

			break;//del ENDSHOOTING

		case LogicActor.STATE_HIT_ON_GROUND:
			if (graphicsEngine.getActiveAnimation().isFirstTime()) {
				//El actor se hace danho porque ha canhdo desde muy alto
				stage.calculateFallDamage(this);
				//se le frena un poco
				float newX = (this.getLogic().getXVelocity()) / 2;
				this.getLogic().setVelocity(newX, 0);
			}

			else if (graphicsEngine.getActiveAnimation().isLastTime()) {
				//TODO Habrnha q cambiar + tarde pa que dure un ciclo.
				this.getLogic().setActualState(LogicActor.STATE_STILL);
				this.getLogic().setVelocity(0, 0);
			}
			break;

		case LogicActor.STATE_MAKING_DAMAGE:
			stage.calculateDamage(this);

			break;

		case LogicActor.STATE_DYING:
			//Se comprueba q la anim de muerte se termina de ejecutar, y luego
			//se borra al personaje
			if (graphicsEngine.getActiveAnimation().isLastTime()) {
				this.getLogic().setActualState(LogicActor.STATE_REMOVING);
			}
			break;
		case LogicActor.STATE_STILL:
			jumping = false;
			break;
		default:
			break;
		}

		/*
		 * TODO nhapa para indicar que el actor tiene el turno
		 */
		((LogicActorPlayer) (this.logic)).setActivePlayer(stage
				.getActivePlayer() == this);
		graphicsEngine.updateState();
	}

	/**
	 * @param s
	 */
	public void draw(ScreenInterface s) {
		graphicsEngine.draw(s);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.actors.ActorInterface#setLogic(game.logicEngine.LogicActorInterface)
	 */
	public void setLogic(LogicActorPlayer la) {
		super.setLogic(la);
	}

	/**
	 * @return Returns the behavior.
	 */
	public PhysicPlayer getBehavior() {
		return behavior;
	}

	/**
	 * Realiza un disparo por parte del jugador
	 * 
	 * @param stage
	 *            el escenario en el que se produce el disparo
	 * @param shotTime
	 *            el tiempo (ms) que se ha mantenido presionada la tecla de
	 *            disparo
	 */
	public void shot(StageInterface stage, long shotTime) {

		if (!isShooting() && !isEscaping()
				&& logic.getActualState() < LogicActor.STATE_JUMP) {
			// inicializar valores de velocidad
			int x = this.getLogic().getX();
			int y = this.getLogic().getY();
			
		    newAmmo.getBehavior().setPosition(x,y);

			// inicializar valores de direccion
			int direction = this.getLogic().getDirection().getDirectionValue();
			newAmmo.getLogic().getDirection().setDirection(direction);

			// fijar el angulo de disparo
			newAmmo.getLogic().setAngle(-target.getLogic().getAngle());

			// fijar potencia segun el tiempo de pulsacion de tecla
			float power = MAX_SHOT_POWER
					* ((float) shotTime / (float) MAX_SHOT_TIME);
			newAmmo.getLogic().setPower(power);
			newAmmo.getBehavior().initPhysicAmmo(newAmmo.getLogic(), (Stage) stage);

			// anhadir nuevo proyectil al array de actores de stage
			this.setShooting(true);
			this.logic.setActualState(LogicActor.STATE_SHOOTING);

			// enlazar el proyectil creado con stage.activeProjectile
			stage.setActiveProjectile(newAmmo);
		}

	}

	/**
	 * El jugador salta
	 * 
	 * @param stage
	 *            El escenario en el que se produce el salto
	 */
	public void jump(StageInterface stage) {
		if (!jumping) {
			this.getLogic().setAngle(this.race.getJumpAngle());
			this.getLogic().setPower(this.race.getJumpValue());
			jumping = true;
			this.behavior.initPhysicPlayer(this.getLogic(), stage);
		}
	}

	/*
	 * public void prejump(StageInterface stage) {
	 * if(this.getLogic().getActualState() == LogicActor.STATE_JUMP){
	 * this.jump(stage); System.out.println("se llama a jump()"); } else if
	 * (this.getLogic().getActualState() != LogicActor.STATE_PREJUMP) {
	 * this.getLogic().setActualState(LogicActor.STATE_PREJUMP);
	 * System.out.println("se pnhne en state_prejump"); } }
	 */

	/**
	 * El jugador anda en una direccinhn
	 * 
	 * @param stage
	 *            El escenario en el que se produce el movimiento
	 */
	public void walk(StageInterface stage, int direction) {
		//Esto es para que Jump y Falling sea + prioritario.
		if (this.getLogic().getActualState() < LogicActor.STATE_JUMP) {
			this.getLogic().getDirection().setDirection(direction);
			this.getLogic().setAngle(0);
			this.getLogic().setPower(this.race.getWalkValue());
			this.getLogic().setActualState(LogicActor.STATE_MOVING);

			this.behavior.initPhysicPlayer(this.getLogic(), stage);
		}
	}

	/**
	 * Da la vuelta al jugador sin moverlo del escenario
	 */
	public void reverse() {
		this.getLogic().getDirection().changeDirection();
	}

	public void stop() {
		//Esto es para que Jump y Falling sea + prioritario.
		if (this.getLogic().getActualState() < LogicActor.STATE_JUMP) {
			this.getLogic().setVelocity(0, 0);
		}
	}

	public void moveTargetUp(boolean m) {
		LogicActorTarget targetLogic = (LogicActorTarget) target.getLogic();

		if (m)
			targetLogic.setAngleVelocity(-targetLogic.SPEED);
		else
			targetLogic.setAngleVelocity(0);
	}

	public void moveTargetDown(boolean m) {
		LogicActorTarget targetLogic = (LogicActorTarget) target.getLogic();
		if (m)
			targetLogic.setAngleVelocity(+targetLogic.SPEED);
		else
			targetLogic.setAngleVelocity(0);
	}

	/**
	 * @return Returns the shooting.
	 */
	public boolean isShooting() {
		return shooting;
	}

	/**
	 * @param shooting
	 *            The shooting to set.
	 */
	public void setShooting(boolean shooting) {
	        this.shooting = shooting;
	}

	/**
	 * @return Returns the shootCounter.
	 */
	public int getShootCounter() {
		return shootCounter;
	}

	/**
	 * @param shootCounter
	 *            The shootCounter to set.
	 */
	public void setShootCounter(int shootCounter) {
		this.shootCounter = shootCounter;
	}

	/**
	 * Decremento el contador de disparos en una unidad
	 *  
	 */
	public void decShootCounter() {
		this.shootCounter--;
	}

	/**
	 * Inicializa los atributos al comienzo de un turno
	 */
	public void resetTurn() {
		this.setShooting(false);
		this.setEscaping(false);
		this.setShootCounter(INITIAL_SHOOTS);

	}

	/**
	 * @return Returns the escaping.
	 */
	public boolean isEscaping() {
		return escaping;
	}

	/**
	 * @param escaping
	 *            The escaping to set.
	 */
	public void setEscaping(boolean escaping) {
		this.escaping = escaping;
	}

	/**
	 * Informa si existe colisinhn con algnhn personaje. Emplea las bounding boxes
	 * para ello.
	 * 
	 * @param logic
	 *            La logica del proyectil
	 * @return true si choca, false en otro caso
	 */
	public boolean getCollision(LogicActorInterface logic) {
		return this.getLogic().getCollision(logic);
	}

	/**
	 * @return Returns the graphicsEngine.
	 */
	public GraphicsEngineActorPlayer getGraphicsEngine() {
		return graphicsEngine;
	}

	/**
	 * @return Returns the target.
	 */
	public ActorTarget getTarget() {
		return target;
	}

	/**
	 * @return Returns the raceCode.
	 */
	public int getRaceCode() {
		return raceCode;
	}

	public int getCurrentWeapon() {
		return ((LogicActorPlayer) this.getLogic()).getCurrentWeapon();
	}

	public void setCurrentWeapon(int w) {
		((LogicActorPlayer) this.getLogic()).setCurrentWeapon(w);
	}

	public int getCurrentWeaponType() {
		return getCurrentWeapon() / 10;
	}

	public int getCurrentWeaponPower() {
		return getCurrentWeapon() % 10;
	}

	/**
	 * @return Returns the jumping.
	 */
	public boolean isJumping() {
		return jumping;
	}

	public void meleeShot(StageInterface stage, int radius, int power) {
		if (!isEscaping()
				&& logic.getActualState() < LogicActor.STATE_JUMP) {			
			this.shooting = true;
			ActorInterface actor;
			for (int i = 0; i < stage.getActorsArray().size(); i++) {
				actor = (ActorInterface) stage.getActorsArray().get(i);
				//para no danharse a uno mismo
				if (actor != this) {
					if (actor instanceof ActorPlayer) {

						double distance;

						distance = this.logic.getInternalPosition().distance(
								actor.getLogic().getInternalPosition());

						//si le ha dado, se le computa el danho
						if (distance < radius) {
							//                    stage.calculateDamage((ActorPlayer)actor);
							actor.getLogic().setActualState(
									LogicActor.STATE_HIT_ON_GROUND);

							((ActorPlayer) actor).drainLifeInMelee(distance,
									power);
						}//if
					}//if
				}
			}//for
			this.shootCounter--;
			this.setShooting(false);
		}
	} //en el melee, el danho se compute desde el propio actor danhado

	public void drainLifeInMelee(double distance, int damage) {

		int life = ((LogicActorPlayer) this.getLogic()).getLife();
		double distanceFactor = 100 / (100 + distance);
		int newLife = (int) (life - StrictMath.round(damage * distanceFactor));
		((LogicActorPlayer) this.getLogic()).setLife(newLife);
	}
	
	private int getCurrentMeleeRadius(){
    	int radius=0;    	
    	switch (this.getCurrentWeaponPower()){
    	case Constants.POW_1:
    		radius=radius_melee1;
    		break;
    	case Constants.POW_2:
    		radius=radius_melee2;
    		break;
    	case Constants.POW_3:
    		radius=radius_melee3;
    		break;
    	}
		return radius;
	}
	
	
	private int getCurrentMeleePower(){
    	int damage=0;    	
    	switch (this.getCurrentWeaponPower()){
    	case Constants.POW_1:
    		damage=damage_melee1;
    		break;
    	case Constants.POW_2:
    		damage=damage_melee2;
    		break;
    	case Constants.POW_3:
    		damage=damage_melee3;
    		break;
    	}
		return damage;
	}

}