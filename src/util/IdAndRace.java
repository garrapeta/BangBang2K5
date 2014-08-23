/*
 * Created on 10-may-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package util;

/**
 * @author Carlos Segovia
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IdAndRace {
	private String id;
	private int race;
	private int pos;
	
	
	
	public IdAndRace(String id, int race){
		this.id=id;
		this.race=race;
	}
	public IdAndRace(){
		this.id="";
		this.race=-1;
	}
	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	
	public int getPosition() {
		return pos;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	public void setPosition(int p) {
		this.pos = p;
	}
	/**
	 * @return Returns the race.
	 */
	public int getRace() {
		return race;
	}
	/**
	 * @param race The race to set.
	 */
	public void setRace(int race) {
		this.race = race;
	}
}
