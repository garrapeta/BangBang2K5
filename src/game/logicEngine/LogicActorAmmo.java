/*
 * Created on 01-abr-2005
 */
package game.logicEngine;

import game.actors.ActorPlayer;

/**
 * @author CarlosG
 * 
 * Esta clase se aplica a la lnhgica de las balas, ya que necesita mnhs
 * atributos.
 */
public class LogicActorAmmo extends LogicActor {
	/**
	 * Representa el danho que producen las balas
	 */
	protected int damage;

	/**
	 * Representa la onda expansiva
	 */
	protected int radius;
	
	/**
	 * Representa al jugador que disparnh la bala
	 */
	protected ActorPlayer owner;


	public LogicActorAmmo(ActorPlayer owner, int damage, int radius) {
		super();
		initLogicActorAmmo(owner,damage,radius);
	}
	

	
	public void initLogicActorAmmo(ActorPlayer owner, int damage, int radius) {
		super.initLogicActor();
		this.damage = damage;
		this.radius = radius;
		this.owner = owner;
	}
	

	/**
	 * @return Returns the damage.
	 */
	public int getDamage() {
		return damage;
	}
	/**
	 * @param damage The damage to set.
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}
	/**
	 * @return Returns the radius.
	 */
	public int getRadius() {
		return radius;
	}
	/**
	 * @param radius The radius to set.
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}
	/**
	 * @return Returns the owner.
	 */
	public ActorPlayer getOwner() {
		return owner;
	}
}