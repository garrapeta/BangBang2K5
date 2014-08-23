/*
 * Created on 30-ene-2005
 *
 */
package weapons;

import game.logicEngine.*;
import game.stages.*;

import java.awt.geom.Point2D;

import util.Direction;

/**
 * Esta clase proporciona la fnhsica dedicada a los personajes.
 * 
 * @author CarlosG
 *  
 */
public class PhysicPlayer {

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

    protected StageInterface stage;

    private Point2D hitPoint;

    /**
     * Tiempo actual, necesario para calcular la canhda.
     */
    private float time;

    private static double MAX_CLIMB_ANGLE = StrictMath.toRadians(65);

    private static double MIN_CLIMB_ANGLE = StrictMath.toRadians(-65);

    private double FALL_DAMAGES_VALUE;

    /**
     * Constructor. El parnhmetro gravity se lee de fichero, con los atributos
     * del escenario.
     * 
     * @param angle
     *            nhngulo del disparo en radianes
     * @param power
     *            Fuerza del disparo
     * @param gravity
     *            Gravedad del espacio fnhsico
     * @param position
     *            Posicinhn actual del objeto fnhsico
     */
    public PhysicPlayer(LogicActorInterface logic, Stage stage) {

        this.position = new Point2D.Float();
        this.hitPoint = new Point2D.Float(-1, -1);
        this.initPhysicPlayer(logic, stage);
    }

    /*
     * (non-Javadoc)
     * 
     * @see weapons.PhysicAmmoInterface#init(game.LogicActor, game.Stage)
     */
    public void initPhysicPlayer(LogicActorInterface logic, StageInterface stage) {
        this.logic = logic;
        this.angle = logic.getAngle();
        this.power = logic.getPower();
        this.environment = stage.getEnvironment();
        this.time = 0;
        this.motionVector = logic.getVelocity();

        FALL_DAMAGES_VALUE = 1.1 * environment.getGravity();
        
        //Cnhlculo del punto de masa
        double posX, posY;
        posX = logic.getInternalPosition().getX();
        posY = logic.getInternalPosition().getY();

        this.position.setLocation(posX + (logic.getWidth() / 2), posY
                + logic.getHeight());

        //this.position = logic.getInternalPosition();
        this.direction = logic.getDirection();
        this.stage = stage;

        float x = (float) (this.power * StrictMath.cos(angle));
        float y = (float) (this.power * StrictMath.sin(angle));
        motionVector.setLocation(x, y);
    }

    private boolean isPlayerOutOfBounds(double x, double y) {
        boolean isOut = false;

        if (y < 0 || isXOut(x, y)) {
            isOut = true;
        }
        return isOut;
    }

    private boolean isXOut(double x, double y) {
        boolean isOut = false;

        //Compruebo que no me he salido de los lnhmites
        if (x + (logic.getWidth() / 2) >= stage.getSizeX()) {
            isOut = true;
        } else if (x - (logic.getWidth() / 2) < 0) {
            isOut = true;
        }
        return isOut;
    }

    private void checkTerrainLevel(double oldX, double oldY) {
        double x, y;

        x = hitPoint.getX();
        y = hitPoint.getY();

        x = oldX - x;
        y = oldY - y;

        double angAux = StrictMath.atan((-y / x)
                * this.direction.getDirectionValue());

        if (angAux > MAX_CLIMB_ANGLE) {
            hitPoint.setLocation(oldX, oldY);
        }
        if (angAux < MIN_CLIMB_ANGLE) {
            hitPoint.setLocation(oldX, oldY);
            logic.setActualState(LogicActor.STATE_FALLING);
        }
    }

    /**
     * @param angle
     *            Nuevo valor del nhngulo.
     */
    protected void setAngle(float angle) {
        this.logic.setAngle(angle);
    }

    /**
     * @return Devuelva la fuerza del disparo.
     */
    protected float getPower() {
        return logic.getPower();
    }

    /**
     * @param power
     *            Nuevo valor de fuerza de disparo.
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

    protected void calcCoord(double oldX, double oldY) {
        //Obtengo las nuevas coordenadas, segun trayectoria
        double newX = oldX + motionVector.getX()
                * direction.getDirectionValue();
        double newY = oldY - motionVector.getY()
                + (environment.getGravity() * time) / 2;
        this.time = this.time + environment.getTimeStep();
        //        this.setTanAngle(newX, newY);
        this.setPosition(newX, newY);
    }

    /**
     * Este mnhtodo traza una recta "fina" desde unas coordenadas iniciales,
     * probando si existe colisinhn con el terreno. Aproximamos la trayectoria
     * con una recta, ya sea trayectoria parabnhlica o no, y obtenemos el punto
     * de impacto con la superficie.
     * 
     * @param oldX
     *            Coordenada x inicial.
     * @param oldY
     *            Coordenada y inicial.
     */
    protected void calcHitPoint(double x, double y) {
        //Trazo trayectoria fina hasta que encuentre un pto de impacto

        boolean collision = true;

        while (collision) {
            y--;
            collision = stage.getCollision(x, y);
        }
        while (!collision) {
            y++;

            if (y == stage.getSizeY()) {
                //logic.setActualState(LogicActor.STATE_STILL);
                collision = true;
                continue;
            }
            collision = stage.getCollision(x, y);
        }
        //Siempre encuentra un hitPoint. NO HAY BUCLE INFINITO
        hitPoint.setLocation(x, y);
    }

    /**
     * @return Devuelve el nhngulo de tiro.
     */
    public double getAngle() {
        return logic.getAngle();
    }

    public void setPosition(double x, double y) {
        //Posicion interna. Desde dnhnde empieza a pintar
        this.logic.setInternalPosition(x - (logic.getWidth() / 2), y
                - logic.getHeight());
        //Posicion fnhsica. El centro bajo del sprite
        this.position.setLocation(x, y);
    }

    public void setPosition(Point2D point) {
        double x, y;
        x = point.getX();
        y = point.getY();

        this.logic.setInternalPosition(x - (logic.getWidth() / 2), y
                - logic.getHeight());

        this.position.setLocation(point);
    }

    /*
     * (non-Javadoc)
     * 
     * @see weapons.PhysicObjectInterface#nextStep()
     */
    public void nextStep() {

        double oldX = position.getX();
        double oldY = position.getY();

        calcCoord(oldX, oldY);

        double newX = position.getX();
        double newY = position.getY();

        switch (logic.getActualState()) {

        //Cuando ponemos un player en el stage
        case LogicActor.STATE_SET:
            this.calcHitPoint(newX, newY);
            this.setPosition(getHitPoint().getX(), getHitPoint().getY());

            logic.setActualState(LogicActor.STATE_STILL);
            //TODO nhapa pa que pare el player. Cambiar en LogicActor
            logic.setVelocity(0, 0);
            break;

        //Cuando un player se mueve por el suelo
        case LogicActor.STATE_MOVING:
            if (!isPlayerOutOfBounds(newX, newY)) {
                this.calcHitPoint(newX, newY);

                checkTerrainLevel(oldX, oldY);

                this.setPosition(getHitPoint().getX(), getHitPoint().getY());
            } else {
                this.setPosition(oldX, oldY);
            }
            break;

        default:
            break;
        }

        if (logic.getActualState() >= LogicActor.STATE_JUMP) {
            if (!isPlayerOutOfBounds(newX, newY)
                    && stage.getCollision(newX, newY)) {

                //Comprobacinhn de que el salto no "hace trampas"
                if (oldY - newY >= 0) {
                    motionVector.setLocation(motionVector.getX(), 0);
                    this.setPosition(oldX, oldY);
                    return;
                }

                //Control del danho por canhda
                if (newY - oldY > FALL_DAMAGES_VALUE) {
                    //Hacer danho
                    this.logic.setActualState(LogicActor.STATE_HIT_ON_GROUND);
                } else {
                    logic.setActualState(LogicActor.STATE_STILL);
                    //TODO nhapa pa que pare el player. Cambiar en LogicActor
                    logic.setVelocity(0, 0);
                }

                this.calcHitPoint(newX, newY);
                this.setPosition(getHitPoint().getX(), getHitPoint().getY());

            } else if (isXOut(newX, newY)) {
                this.direction.changeDirection();
            }
        }
    }
}