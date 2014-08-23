/*
 * GraphicsEngineScrapper.java
 */

package game.graphicsEngine;

import game.AnimSet;
import game.Animation;
import game.GameEngine;
import game.LocalGame;
import game.logicEngine.*;

import java.io.File;
import java.util.TreeMap;

import util.*;

public class GraphicsEngineScrapper extends GraphicsEngineActorPlayer {

	protected static Animation andarAnim;

	protected static Animation reposo1Anim;

	protected static Animation reposo2Anim;

	protected static Animation reposo3Anim;

	protected static Animation preSaltoAnim;

	protected static Animation saltoAnim;

	protected static Animation danhoAnim;

	protected static Animation muerteAnim;
	
	protected static Animation shootAnim;
	
	protected Animation melee1Anim;
	
	protected Animation melee2Anim;
	
	protected Animation melee3Anim;
	
	protected Animation parabolic1Anim;
	
	protected Animation parabolic2Anim;
	
	protected Animation parabolic3Anim;
	
	protected Animation grenade1Anim;
	
	protected Animation grenade2Anim;
	
	protected Animation grenade3Anim;
	
	protected Animation rectilinear1Anim;
	
	protected Animation rectilinear2Anim;
	
	protected Animation rectilinear3Anim;


	private static int frameDuration;
	

	public GraphicsEngineScrapper(LogicActorPlayer logic, Direction initial) {
		super(logic, initial);
		frameDuration = 10;		
		//generateAnims();
		loadAnimations();
		activeAnimation = reposo2Anim;

	}

	public void updateState() {
		switch (logic.getActualState()) {
		case LogicActor.STATE_STILL:
			if (activeAnimation != reposo2Anim) {
				if (activeAnimation == reposo1Anim) {
					if (activeAnimation.isLastTime()) {
						activeAnimation.setStopped(true);
						activeAnimation = null;
						activeAnimation = reposo2Anim;
						activeAnimation.setStopped(false);
						activeAnimation.start();
					}
				} else {
					activeAnimation.setStopped(true);
					activeAnimation = null;
					activeAnimation = reposo1Anim;
					activeAnimation.setStopped(false);
					activeAnimation.start();
				}
			}
			break;
		case LogicActor.STATE_MOVING:
			if (activeAnimation != andarAnim) {
				if (activeAnimation == reposo3Anim) {
					if (activeAnimation.isLastTime()) {
						activeAnimation.setStopped(true);
						activeAnimation = null;
						activeAnimation = andarAnim;
						activeAnimation.setStopped(false);
						activeAnimation.start();
					}
				} else {
					activeAnimation.setStopped(true);
					activeAnimation = null;
					activeAnimation = reposo3Anim;
					activeAnimation.setStopped(false);
					activeAnimation.start();
				}
			}
			break;
		case LogicActor.STATE_SHOOTING:
			switch (this.logic.getCurrentWeaponType()) {
			case Constants.WEAP_MELEE:
				switch (this.logic.getCurrentWeaponPower()) {
				case Constants.POW_1:
					shootAnim = melee1Anim;
					break;
				case Constants.POW_2:
					shootAnim = melee2Anim;
					break;
				case Constants.POW_3:
					shootAnim = melee3Anim;
					break;
				default:
					break;
				}
				break;//del melle
			case Constants.WEAP_PARABOLIC:
				switch (this.logic.getCurrentWeaponPower()) {
				case Constants.POW_1:
					shootAnim = parabolic1Anim;
					break;
				case Constants.POW_2:
					shootAnim = parabolic2Anim;
					break;
				case Constants.POW_3:
					shootAnim = parabolic3Anim;
					break;
				default:
					break;
				}
				break;//del parabolic
			case Constants.WEAP_GRENADE:
				switch (this.logic.getCurrentWeaponPower()) {
				case Constants.POW_1:
					shootAnim = grenade1Anim;
					break;
				case Constants.POW_2:
					shootAnim = grenade2Anim;
					break;
				case Constants.POW_3:
					shootAnim = grenade3Anim;
					break;
				default:
					break;
				}
				break;//del grenade
			case Constants.WEAP_RECTILINEAR:
				switch (this.logic.getCurrentWeaponPower()) {
				case Constants.POW_1:
					shootAnim = rectilinear1Anim;
					break;
				case Constants.POW_2:
					shootAnim = rectilinear2Anim;
					break;
				case Constants.POW_3:
					shootAnim = rectilinear3Anim;
					break;
				default:
					break;
				}
				break;//del rectilinear
			}//del switch			
			if (activeAnimation != shootAnim) {
				if (activeAnimation == reposo3Anim) {
					if (activeAnimation.isLastTime()) {
						activeAnimation.setStopped(true);
						activeAnimation = null;
						activeAnimation = shootAnim;
						activeAnimation.setStopped(false);
						activeAnimation.start();
					}
				} else {
					activeAnimation.setStopped(true);
					activeAnimation = null;
					activeAnimation = reposo3Anim;
					activeAnimation.setStopped(false);
					activeAnimation.start();
				}
			} else {
				if (activeAnimation.isLastTime()) {
					activeAnimation.setStopped(true);
					activeAnimation = null;
					activeAnimation = reposo2Anim;
					activeAnimation.setStopped(false);
					activeAnimation.start();
					this.logic.setActualState(LogicActor.STATE_ENDSHOOTING);
				}
			}
			break;
		case LogicActor.STATE_PREJUMP:
			if (activeAnimation != preSaltoAnim) {
				if (activeAnimation == reposo3Anim) {
					if (activeAnimation.isLastTime()) {
						activeAnimation.setStopped(true);
						activeAnimation = null;
						activeAnimation = preSaltoAnim;
						activeAnimation.setStopped(false);
						activeAnimation.start();
					}
				} else {
					activeAnimation.setStopped(true);
					activeAnimation = null;
					activeAnimation = reposo3Anim;
					activeAnimation.setStopped(false);
					activeAnimation.start();
				}
			}

			else {
				//fin de saltoanim
				if (activeAnimation.isLastTime()) {					
					this.logic.setActualState(LogicActor.STATE_JUMP);
				}
			}
			break;

		case LogicActor.STATE_JUMP:
			if (activeAnimation != saltoAnim) {
				activeAnimation.setStopped(true);
				activeAnimation = null;
				activeAnimation = saltoAnim;
				activeAnimation.setStopped(false);
				activeAnimation.start();
			}

			break;

		case LogicActor.STATE_HIT_ON_AIR:
			if (activeAnimation != danhoAnim) {
				activeAnimation.setStopped(true);
				activeAnimation = null;
				activeAnimation = danhoAnim;
				activeAnimation.setStopped(false);
				activeAnimation.start();
			}
			break;

		case LogicActor.STATE_DYING:
			if (activeAnimation != muerteAnim) {
				activeAnimation.setStopped(true);
				activeAnimation = null;
				activeAnimation = muerteAnim;
				activeAnimation.setStopped(false);
				activeAnimation.start();
			}
			break;

		case LogicActor.STATE_HIT_ON_GROUND:
			if (activeAnimation != danhoAnim) {
				activeAnimation.setStopped(true);
				activeAnimation = null;
				activeAnimation = danhoAnim;
				activeAnimation.setStopped(false);
				activeAnimation.start();
			}
			break;

		default:
			break;
		}
		activeAnimation.updateState();
	}

	
	
	public void loadAnimations(){
		
		
		AnimSet animSet =GameEngine.getAnimSet();
		TreeMap anims=(TreeMap)animSet.getAnims().get("scraper");
		
		reposo1Anim=(Animation)anims.get("reposo1");
		
		reposo2Anim=(Animation)anims.get("reposo2");
		
		reposo3Anim=(Animation)anims.get("reposo3");

		andarAnim=(Animation)anims.get("andar");
		
		preSaltoAnim=(Animation)anims.get("preSalto");

		saltoAnim=(Animation)anims.get("salto");

		danhoAnim=(Animation)anims.get("danho");

		muerteAnim=(Animation)anims.get("muerte");		

		melee1Anim=(Animation)anims.get("melee1");
		
		melee2Anim=(Animation)anims.get("melee2");
		
		melee3Anim=(Animation)anims.get("melee3");
		
		parabolic1Anim=(Animation)anims.get("parabolic1");
				
		parabolic2Anim=(Animation)anims.get("parabolic1");
		
		parabolic3Anim=(Animation)anims.get("parabolic1");
		
		grenade1Anim=(Animation)anims.get("grenade1");
		
		grenade2Anim=(Animation)anims.get("grenade2");
		
		grenade3Anim=(Animation)anims.get("grenade3");
		
		rectilinear1Anim=(Animation)anims.get("rectilinear1");			
		
		rectilinear2Anim=(Animation)anims.get("rectilinear1");

		rectilinear3Anim=(Animation)anims.get("rectilinear1");

	}

	public int getFrameDuration() {
		return frameDuration;
	}

	public void setFrameDuration(int duration) {
		frameDuration = duration;
	}

}//class GraphicsEngineActorPlayerTest

