/*
 * Created on 09-abr-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package game.actors;

import game.ScreenInterface;
import game.actors.ammo.projGrenade.ActorAmmoProjGrenadeNinfa;
import game.actors.ammo.projParabolic.ActorAmmoProjParabolicNinfa;
import game.actors.ammo.projRectilinear.ActorAmmoProjRectilinearNinfa;
import game.graphicsEngine.GraphicsEngineNinfa;
import game.logicEngine.LogicActor;
import game.logicEngine.LogicActorPlayer;
import game.races.Ninfas;
import game.stages.Stage;
import game.stages.StageInterface;

import java.io.File;

import util.Constants;
import util.Direction;
import util.Tools;
import weapons.PhysicPlayer;

/**
 * @author David Toro
 */

/**
 * Implementa el actor Ninfa extendiendo la clase ActorPlayer
 */
public class ActorPlayerNinfa extends ActorPlayer {
    /**
     * Constructor
     *  
     */

    private Direction initial;
    

    public ActorPlayerNinfa(String userID) {

        super(new Ninfas());
        initial = new Direction();

        graphicsEngine = new GraphicsEngineNinfa((LogicActorPlayer) logic,
                initial);
        target = new ActorTarget(Tools.loadBufferedImage("art" + File.separator
                + "pibitas"+File.separator+"puntoMira"+File.separator+"miraninfa.png"), this);

        initActorNinfa(userID);
    }

    /**
     * Constructor
     * 
     * @param stage
     */
    public ActorPlayerNinfa(Stage stage, String userID) {

        this(userID);

        behavior = new PhysicPlayer(this.getLogic(), stage);
        audioPlayer = new sound.AudioPlayer(stage.getAudioManager(), this
                .getLogic(), "sound.audioProperties.AudioPropertiesNinfa");
        
    }

    public void initActorNinfa(String userID) {
    	raceCode=Constants.RACE_NINFA;
        ((LogicActorPlayer) this.logic).setUserID(userID);

        this.initial.setDirection(Direction.LEFT);
        this.logic.setSize(45, 105);
        target.getLogic().setPosition(logic.getX(), logic.getY());
        damage_melee1 = 50;
        damage_melee2 = 100;
        damage_melee3 = 200;
        radius_melee1 = 75;
        radius_melee2 = 75;
        radius_melee3 = 75;
    }

    public void draw(ScreenInterface screen) {
        graphicsEngine.draw(screen);
        target.draw(screen);
    }

    public void updateState(StageInterface stage) {
        super.updateState(stage);
        target.updateState(stage);
        target.updatePosition(this);
    }

    public void shot(StageInterface stage, long shotTime) {
        int typeWeapon = ((LogicActorPlayer) this.getLogic())
                .getCurrentWeapon() / 10;
        int powerWeapon = ((LogicActorPlayer) this.getLogic())
                .getCurrentWeapon() % 10;

        //en funcion del arma seleccionada se crea un tipo de arma u
        //otra, y se le pasa el grado al constuctor del arma

        switch (typeWeapon) {
        case Constants.WEAP_RECTILINEAR:
            newAmmo = new ActorAmmoProjRectilinearNinfa((Stage) stage, this,
                    powerWeapon);
        	super.shot(stage, 1900);
            break;
        case Constants.WEAP_PARABOLIC:
            newAmmo = new ActorAmmoProjParabolicNinfa((Stage) stage, this,
                    powerWeapon);
        	super.shot(stage, shotTime);
            break;
        case Constants.WEAP_GRENADE:
            newAmmo = new ActorAmmoProjGrenadeNinfa((Stage) stage, this,
                    powerWeapon);
        	super.shot(stage, shotTime);
            break;
        case Constants.WEAP_MELEE:
        	if(!this.escaping)
        	this.getLogic().setActualState(LogicActor.STATE_SHOOTING);
        	//a shootMelee se le llama cando termine shooting
            break;//del caso melee
        case Constants.WEAP_SPECIAL:
            // nada
            break;

        default:
            System.out.println("Error en currentWeapon");
            break;
        }        
    }
}