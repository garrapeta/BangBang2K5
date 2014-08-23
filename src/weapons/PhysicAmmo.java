/*
 * Created on 30-ene-2005
 *
 */
package weapons;

import game.logicEngine.LogicActorInterface;
import game.stages.StageInterface;

import java.awt.geom.Point2D;

import util.Direction;

/**
 * Esta clase abstracta proporciona los atributos y mnhtodos accesores comunes a
 * todos los tipos de municinhn.
 * 
 * @author CarlosG
 *  
 */
public abstract class PhysicAmmo implements PhysicAmmoInterface {

    protected LogicActorInterface logic;

    /**
     * Es el incremento que se aplica en cada paso a las coordenadas x e y.
     */
    protected Point2D motionVector;

    protected Point2D position;

    protected double angle;

    protected float power;

    protected Direction direction;

    protected PhysicEnvironment environment;

    protected boolean explosion;

    protected StageInterface stage;

    private Point2D hitPoint;


    /**
     * 
     * @param logic
     * @param environment
     * @param stage
     */

    public PhysicAmmo(LogicActorInterface logic, StageInterface stage) {

        this.hitPoint = new Point2D.Float(-1, -1);
        this.position = new Point2D.Float();

        this.initPhysicAmmo(logic, stage);
    }


    /*
     * (non-Javadoc)
     * 
     * @see weapons.PhysicAmmoInterface#initPhysicAmmo(game.LogicActor, game.Stage)
     */
    public void initPhysicAmmo(LogicActorInterface logic, StageInterface stage) {
        this.logic = logic;
        this.angle = logic.getAngle();
        this.power = logic.getPower();
        this.environment = stage.getEnvironment();

        //Cnhlculo del punto de masa
        double posX, posY;
        posX = logic.getInternalPosition().getX();
        posY = logic.getInternalPosition().getY();

        this.position.setLocation(posX + (logic.getWidth() / 2), posY
                + (logic.getHeight() / 2));

        this.direction = logic.getDirection();
        this.stage = stage;
        this.explosion = false;
        this.motionVector = logic.getVelocity();

        float x = (float) (this.power * StrictMath.cos(angle));
        float y = (float) (this.power * StrictMath.sin(angle));
        motionVector.setLocation(x, y);
    }


    /**
     * Calcula las coordenadas siguientes segnhn la trayectoria. No tiene en
     * cuenta las posibles colisiones
     * 
     * @param oldX La coordenada x de donde partimos.
     * @param oldY La coordenada y de donde partimos.
     * @return Devuelve el nuevo punto de la trayectoria.
     */
    protected abstract void calcCoord(double oldX, double oldY);


    /**
     * Comprueba que la bala estnh dentro de los lnhmites. Si no lo estnh, los
     * trata.
     * 
     * @param x
     * @param y
     * @return true si estnh fuera de la pantalla de juego.
     */
    protected abstract boolean checkAmmoOutOfBounds(double x, double y);


    /**
     * @param angle Nuevo valor del nhngulo.
     */
    protected void setAngle(float angle) {
        this.logic.setAngle(angle);
    }


    /**
     * Almacena y calcula el valor en radianes del nhngulo tangente a la
     * trayectoria en el punto (x,y). Realmente lo que actualiza es el atributo
     * <em>angle</em> de la clase <em>PhysicalObject</em>.
     * 
     * @param x Coordenada x
     * @param y Coordenada y
     * 
     * @see PhysicAmmo.java
     */
    protected void setTanAngle(double x, double y) {
        x = x - position.getX();
        y = y - position.getY();
        double angAux = StrictMath.atan((y / x)
                * this.getDirection().getDirectionValue());
        if (this.direction.getDirectionValue() == Direction.LEFT) {
            angAux = (StrictMath.PI - angAux);
        }
        this.setAngle((float) angAux);
    }


    /**
     * @return Devuelva la fuerza del disparo.
     */
    protected float getPower() {
        return logic.getPower();
    }


    /**
     * @param power Nuevo valor de fuerza de disparo.
     */
    protected void setPower(float power) {
        logic.setPower(power);
    }


    /**
     * @return Returns the hitPoint.
     */
    protected Point2D getHitPoint() {
        return hitPoint;
    }


    /**
     * Este mnhtodo traza una recta "fina" desde unas coordenadas iniciales,
     * probando si existe colisinhn con el terreno. Aproximamos la trayectoria
     * con una recta, ya sea trayectoria parabnhlica o no, y obtenemos el punto
     * de impacto con la superficie.
     * 
     * @param oldX Coordenada x inicial.
     * @param oldY Coordenada y inicial.
     */
    protected void calcHitPoint(double oldX, double oldY) {
        //Trazo trayectoria fina hasta que encuentre un pto de impacto

        //Cnhlculo de la pendiente de la recta
        double x = oldX - position.getX();
        double y = oldY - position.getY();
        double m = y / (direction.getDirectionValue() * x);

        boolean collision = stage.getCollision(oldX, oldY);
        while (!collision) {
            //Nuevas coordenadas (eq. punto-pendiente)
            oldY = m + oldY;
            //direction = +- 1
            oldX = oldX + direction.getDirectionValue();

            collision = stage.getCollision(oldX, oldY);
        }//Siempre encuentra un hitPoint. NO HAY BUCLE INFINITO
        hitPoint.setLocation(oldX, oldY);
    }


    protected double normAngle(double angle) {
        // nhngulo normalizado = Entre 0 y 90 (en radianes,claro)
        return ((StrictMath.PI / 2) - getAngle());
    }


    /*
     * (non-Javadoc)
     * 
     * @see weapons.PhysicObjectInterface#setExplosion()
     */
    public void setExplosion(boolean explosion) {
        this.explosion = explosion;
    }


    /*
     * (non-Javadoc)
     * 
     * @see weapons.PhysicObjectInterface#isExplosion()
     */
    public boolean isExplosion() {
        return explosion;
    }


    /**
     * @return Devuelve el nhngulo de tiro.
     */
    public double getAngle() {
        return logic.getAngle();
    }


    /**
     * @return Devuelve la posicinhn.
     */
    public Point2D getPosition() {
        return this.position;
    }

    
    public void setPosition(int x, int y) {
        this.logic.setInternalPosition(x - (logic.getWidth() / 2), y
                - (logic.getHeight() / 2));
        this.position.setLocation(x, y);
    }

    public void setPosition(double x, double y) {
        this.logic.setInternalPosition(x - (logic.getWidth() / 2), y
                - (logic.getHeight() / 2));
        this.position.setLocation(x, y);
    }


    public void setPosition(Point2D point) {
        double x, y;
        x = point.getX();
        y = point.getY();

        this.logic.setInternalPosition(x - (logic.getWidth() / 2), y
                - (logic.getHeight() / 2));

        this.position.setLocation(point);
    }


    /**
     * @return Devuelve la direccinhn.
     */
    public Direction getDirection() {
        return this.direction;
    }


    /*
     * (non-Javadoc)
     * 
     * @see weapons.PhysicObjectInterface#nextStep()
     */
    public void nextStep() {
        //Obtengo las nuevas coordenadas, segun trayectoria
        double oldX = position.getX();
        double oldY = position.getY();

        calcCoord(oldX, oldY);

        double newX = position.getX();
        double newY = position.getY();

        boolean isOut = checkAmmoOutOfBounds(newX, newY);

        //Cheque colisinhn con los personajes
        if(stage.getPlayersCollision(this.logic)){
        	this.setPosition(newX,newY);
        	this.setExplosion(true);
        }
        //Chequeo colisinhn si la bala estnh en la pantalla
        else if (!isOut && stage.getCollision(newX, newY)) {
        	//Trazo trayectoria fina hasta que encuentre un pto de impacto
        	this.calcHitPoint(oldX, oldY);
        	this.setPosition(this.getHitPoint());
        	
        	this.setExplosion(true);
        }
    }
}