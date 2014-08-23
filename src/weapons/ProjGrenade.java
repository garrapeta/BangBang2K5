/*
 * Created on 30-ene-2005
 *
 * TODO Mejorar los rebotes, para que no hagan cosas raras
 */
package weapons;

import game.logicEngine.*;
import game.stages.StageInterface;

import java.awt.geom.Point2D;

/**
 * Esta clase representa a las balas con comportamieto de granada. Requiere los
 * mnhtodos <em>getCollision(float x, float y)</em> y <em>getWidth()</em> del
 * motor grnhfico.
 * 
 * @author CarlosG
 *  
 */
public class ProjGrenade extends PhysicAmmo {

    /**
     * Tiempo actual, necesario para calcular la canhda.
     */
    private float time;

    private int delay;

    private float bounceFactor;
    
    private static int INITIAL_DELAY = 125;

    /**
     * Vector de choque. Anhade realismo al rebote, ya que el nuevo rebote
     * depende de la altura desde donde caiga la granada.
     */
    private Point2D hitVelocity;


    /**
     * 
     * @param interface1
     * @param stage
     */
    public ProjGrenade(LogicActorInterface logic, StageInterface stage) {

        super(logic, stage);

        hitVelocity = new Point2D.Float();
        initGrenade(logic, stage);
    }


    /*
     * (non-Javadoc)
     * 
     * @see weapons.PhysicAmmoInterface#init(game.LogicActor, game.Stage)
     */
    public void initGrenade(LogicActorInterface logic, StageInterface stage) {

        super.initPhysicAmmo(logic, stage);

        this.time = stage.getEnvironment().getTimeStep();
        this.delay = INITIAL_DELAY;
        this.bounceFactor = (float) 0.8;
    }


    /*
     * (non-Javadoc)
     * 
     * @see weapons.PhysicObject#calcCoord(double, double)
     */
    protected void calcCoord(double oldX, double oldY) {
        //Obtengo las nuevas coordenadas, segun trayectoria
        double newX = oldX
                + (motionVector.getX() * direction.getDirectionValue());
        double newY = oldY - motionVector.getY()
                + (environment.getGravity() * time) / 2;
        this.time = this.time + environment.getTimeStep();
        this.setTanAngle(newX, newY);
        this.setPosition(newX, newY);

        //Calculo el vector de fuerza de choque con el suelo
        hitVelocity.setLocation(newX - oldX, newY - oldY);
    }


    /*
     * (non-Javadoc)
     * 
     * @see weapons.PhysicAmmo#checkAmmoOutOfBounds(double, double)
     */
    protected boolean checkAmmoOutOfBounds(double x, double y) {
        boolean isOut = false;

        //Compruebo que no me he salido de los lnhmites horizontales y reboto
        if (x >= stage.getSizeX()) {
            direction.changeDirection();
            isOut = true;
        } else if (x <= 0) {
            direction.changeDirection();
            isOut = true;
        }
        return isOut;
    }

    /*
     * (non-Javadoc)
     * 
     * @see weapons.PhysicAmmoInterface#nextStep()
     */
    public void nextStep() {

        //Cuenta atrnhs
        delay--;
        if (delay <= 0) {
            setExplosion(true);
            return;
        }
        //Obtengo las nuevas coordenadas, segun trayectoria
        double oldX = position.getX();
        double oldY = position.getY();

        calcCoord(oldX, oldY);

        double newX = position.getX();
        double newY = position.getY();

        boolean isOut = checkAmmoOutOfBounds(newX, newY);

        //Chequeo colisinhn si la bala estnh en la pantalla
        if (!isOut && stage.getCollision(newX, newY)) {
            //Trazo trayectoria fina hasta que encuentre un pto de impacto

            this.calcHitPoint(oldX, oldY);
            this.setPosition(this.getHitPoint());
            setPosition(newX, newY);
            this.setExplosion(true);
            return;
        }
    }
}