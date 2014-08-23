/*
 * Created on 26-abr-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package game.actors;

import game.ScreenInterface;
import game.actors.ammo.projGrenade.ActorAmmoProjGrenadeG_Marine;
import game.actors.ammo.projParabolic.ActorAmmoProjParabolicG_Marine;
import game.actors.ammo.projRectilinear.ActorAmmoProjRectilinearG_Marine;
import game.graphicsEngine.GraphicsEngineG_Marine;
import game.logicEngine.LogicActor;
import game.logicEngine.LogicActorPlayer;
import game.races.G_Marines;
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
 * Implementa el actor G_Marine extendiendo la clase ActorPlayer
 */
public class ActorPlayerG_Marine extends ActorPlayer {
    /**
     * Constructor
     *  
     */

    private Direction initial;

    public ActorPlayerG_Marine(String userID) {

        super(new G_Marines());
        initial = new Direction();
        initial.setDirection(Direction.LEFT);
        graphicsEngine = new GraphicsEngineG_Marine((LogicActorPlayer) logic,
                initial);
        target = new ActorTarget(Tools.loadBufferedImage("art" + File.separator
                + "gmarine"+File.separator+"puntoMira"+File.separator+"miragmarine.png"), this);

        initActorG_Marine(userID);
    }

    /**
     * Constructor
     * 
     * @param stage
     */
    public ActorPlayerG_Marine(Stage stage, String userID) {

        this(userID);

        behavior = new PhysicPlayer(this.getLogic(), stage);
        audioPlayer = new sound.AudioPlayer(stage.getAudioManager(), this
                .getLogic(), "sound.audioProperties.AudioPropertiesGMarine");
    }

    public void initActorG_Marine(String userID) {
    	raceCode=Constants.RACE_GMARINE;
        ((LogicActorPlayer) this.logic).setUserID(userID);

        this.initial.setDirection(Direction.LEFT);
        this.logic.setSize(70, 110);
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
            newAmmo = new ActorAmmoProjRectilinearG_Marine((Stage) stage, this,
                    powerWeapon);
            super.shot(stage, 1900);
            break;
        case Constants.WEAP_PARABOLIC:
            newAmmo = new ActorAmmoProjParabolicG_Marine((Stage) stage, this,
                    powerWeapon);
        	super.shot(stage,shotTime);
            break;
        case Constants.WEAP_GRENADE:
            newAmmo = new ActorAmmoProjGrenadeG_Marine((Stage) stage, this,
                    powerWeapon);
        	super.shot(stage,shotTime);
            break;
        case Constants.WEAP_MELEE:
        	if(!this.escaping)
        	this.getLogic().setActualState(LogicActor.STATE_SHOOTING);
            // nada
            break;
        default:
            System.out.println("Error en currentWeapon");
            break;
        }
    }

}