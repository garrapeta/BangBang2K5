package game.actors.ammo;

import game.Animation;
import game.ScreenInterface;
import game.actors.Actor;
import game.actors.ActorInterface;
import game.actors.ActorPlayer;
import game.graphicsEngine.GraphicsEngineActorAmmo;
import game.logicEngine.LogicActor;
import game.logicEngine.LogicActorAmmo;
import game.stages.StageInterface;

import java.awt.Point;
import java.util.List;
import java.util.ListIterator;

import util.Tools;
import weapons.Explossion;
import weapons.PhysicAmmoInterface;
import weapons.ProjParabolic;

/*
 * SpritePlayer.java
 */

/**
 * Clase que implementa el actor municinhn
 *  
 */
public abstract class ActorAmmo extends Actor {

    protected PhysicAmmoInterface behavior;

    /**
     * Motor grnhfico
     */
    protected GraphicsEngineActorAmmo graphicsEngine;

    protected Explossion explossion;

    protected ActorPlayer owner;

    /**
     * Constructor
     *  
     */
    public ActorAmmo(ActorPlayer owner) {
        super();
        this.owner = owner;

        //Le decimos duenho,danho y onda expansiva
        //TODO usar en las explosiones los datos de lnhgica.
        this.logic = new LogicActorAmmo(owner, 10, 100);
        graphicsEngine = new GraphicsEngineActorAmmo(logic);
        this.getLogic().setActualState(LogicActor.STATE_STILL);

    }

    /**
     * Actualiza el estado del actor
     */
    public void updateState(StageInterface s) {

        //si no ha explotado sige moviendose
        if (!behavior.isExplosion()
                && this.logic.getActualState() == LogicActor.STATE_MOVING)
            behavior.nextStep();
        //si snh ha explotado depende del estado
        else {
            switch (this.getLogic().getActualState()) {
            //si aun estaba en moving se pone en MAKING_DAMAGE
            case LogicActor.STATE_MOVING:
                this.getLogic().setActualState(LogicActor.STATE_MAKING_DAMAGE);
                break;

            //en MAKING_DAMAGE se computan los danhos y destruccion
            //del terreno durante 1 solo frame y se pone BANBANGING
            case LogicActor.STATE_MAKING_DAMAGE:
                this.getLogic().setActualState(LogicActor.STATE_BANGBANGING);

                //Coloco la explosion
                int x = logic.getX();
                int y = logic.getY();

                this.logic.setAngle(0);
                Point p = new Point(x, y);
                this.explossion.init(p, this.explossion.getType(),
                        this.explossion.getNumberOfPoints(), this.explossion
                                .getRatio());
                //se destruye el terreno
                s.commitExplossion(x, y, this.explossion);

                //se calculan los danhos
                calcAffectedPlayers(s);


                //Actualiza la info de disparos, que estnh en Player
                this.owner.setShooting(false);
                this.owner.decShootCounter();

                break;

            //en BANGBANGSING se deja terminar la animacion y se pone REMOVING
            case LogicActor.STATE_BANGBANGING:
                if (graphicsEngine.getActiveAnimation().isLastTime()) {
                    this.getLogic().setActualState(LogicActor.STATE_REMOVING);
                }
                break;

            case LogicActor.STATE_STILL:
                this.logic.setActualState(LogicActor.STATE_REMOVING);
                break;

            }

        }

        //Coloco al actor en su sitio para dibujarlo
        double x = ((PhysicAmmoInterface) behavior).getPosition().getX();
        double y = ((PhysicAmmoInterface) behavior).getPosition().getY();
        logic.setX((int) Math.round(x));
        logic.setY((int) Math.round(y));

        graphicsEngine.updateState();
    }

    /**
     * @param s
     *  
     */
    private void calcAffectedPlayers(StageInterface stage) {
        ActorInterface actor;
        for (int i = 0; i < stage.getActorsArray().size(); i++) {
            actor = (ActorInterface) stage.getActorsArray().get(i);
            if (actor instanceof ActorPlayer) {
                
                double distance;
                LogicActorAmmo logic;
                
                distance = this.getLogic().getInternalPosition().distance(
                        actor.getLogic().getInternalPosition());
                logic = (LogicActorAmmo) this.getLogic();
                
                //si le ha dado, se le computa el danho
                if (distance < (logic.getRadius())) {
//                    stage.calculateDamage((ActorPlayer)actor);
                    actor.getLogic().setActualState(LogicActor.STATE_MAKING_DAMAGE);
                }//if
            }//if
        }//for

    }

    /**
     * @return Returns the behavior.
     */
    public PhysicAmmoInterface getBehavior() {
        return behavior;
    }

    /**
     * Pinta el actor en el parnhmetro screen
     * 
     * @param screen
     */
    public void draw(ScreenInterface screen) {
        graphicsEngine.draw(screen);
    }
    /**
     * Anhade la animacinhn que le corresponde al disparo
     * @param rute ruta en la que se encuentra la imagen del disparo
     */
	protected void newFlyingAnim(String rute){
		Animation flyingAnim = new Animation();
        flyingAnim.addFrame(Tools.loadBufferedImage(rute), 1);
        flyingAnim.setStopped(true);
        flyingAnim.start();
        graphicsEngine.setActiveAnimation(flyingAnim);
	}
	
	/**
	 * Anhade al disparo las animaciones de la explosinhn
     * @param listofRutes lista de las rutas en las que se encuentran 
     * las imnhgenes de la explosinhn
     */
    protected void newBangAnim(List listOfRutes) {
    	ListIterator t=listOfRutes.listIterator();
        Animation bangAnim = new Animation();
        bangAnim.setDefaultFrameDuration(3);
        while (t.hasNext()){
        	bangAnim.addFrame(Tools.loadBufferedImage((String)(t.next())));
        }	
        //TODO Esto es una nhapa de lo peor. Cogemos el pto medio del png.
        bangAnim.setOffset(-100, -100);
        bangAnim.start();
        bangAnim.setStopped(false);
        graphicsEngine.setBangAnim(bangAnim);
    }   
    
    protected void newExplosion(Point p, int type,int nPoints, double expRatio){
		explossion = new Explossion(p, type, nPoints, expRatio);
	}
    
    protected void initLogic(int posX, int posY, int damage,int radius){
		this.logic.setSize(posX, posY);
		LogicActorAmmo logic = (LogicActorAmmo) this.getLogic();
		logic.setDamage(damage);
		logic.setRadius(radius);
	}
}