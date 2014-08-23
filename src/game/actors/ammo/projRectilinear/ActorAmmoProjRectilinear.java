package game.actors.ammo.projRectilinear;

import game.actors.ActorPlayer;
import game.actors.ammo.ActorAmmo;
import game.stages.Stage;

import weapons.ProjRectilinear;
/*
 * Created on 25-abr-2005
 */

/**
 * @author nhCarlos Garcnha? Retocado por Carlos Segovia
 */

/**
 * Clase ActorAmmoProjRectilinear, extiende a ActorAmmo.
 * Representa generalidades comunes a los disparos de las armas
 * de tiro rectilnhneo para las cinco razas
 */
public class ActorAmmoProjRectilinear extends ActorAmmo {
	
	/**
     * Constructor de la clase
     * 
     * @param stage Pantalla
     * @param owner Actor-personaje que dispara
     */
	public ActorAmmoProjRectilinear(Stage stage, ActorPlayer owner) {

		super(owner);
        this.behavior = new ProjRectilinear(this.getLogic(), stage);
    
	}
}
