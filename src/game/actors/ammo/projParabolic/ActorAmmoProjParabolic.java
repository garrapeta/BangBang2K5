package game.actors.ammo.projParabolic;

import game.actors.ActorPlayer;
import game.actors.ammo.ActorAmmo;
import game.stages.Stage;

import weapons.ProjParabolic;

/*
 * Created on 25-abr-2005
 */

/**
 * @author nhCarlos Garcnha? Retocado por Carlos Segovia
 */

/**
 * Clase ActorAmmoProjParabolic, extiende a ActorAmmo.
 * Representa generalidades comunes a los disparos de las armas
 * de tiro parabnhlico para las cinco razas
 */
public class ActorAmmoProjParabolic extends ActorAmmo {
	
	/**
     * Constructor de la clase
     * 
     * @param stage Pantalla
     * @param owner Actor-personaje que dispara
     */
	public ActorAmmoProjParabolic(Stage stage, ActorPlayer owner) {

		super(owner);
        this.behavior = new ProjParabolic(this.getLogic(), stage);
    
	}
}
