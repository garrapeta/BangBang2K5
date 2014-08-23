package game.actors.ammo.projGrenade;

import game.actors.ActorPlayer;
import game.actors.ammo.ActorAmmo;
import game.stages.Stage;

import weapons.ProjGrenade;
/*
 * Created on 26-abr-2005
 */

/**
 * @author nhCarlos Garcnha? Retocado por Carlos Segovia
 */

/**
 * Clase ActorAmmoProjGrenade, extiende a ActorAmmo.
 * Representa generalidades comunes a los disparos de las armas
 * de tiro rectilnhneo para las cinco razas
 */
public class ActorAmmoProjGrenade extends ActorAmmo {
	
	/**
     * Constructor de la clase
     * 
     * @param stage Pantalla
     * @param owner Actor-personaje que dispara
     */
	public ActorAmmoProjGrenade(Stage stage, ActorPlayer owner) {

		super(owner);
        this.behavior = new ProjGrenade(this.getLogic(), stage);
    
	}
}