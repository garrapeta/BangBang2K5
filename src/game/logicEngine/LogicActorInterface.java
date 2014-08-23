/*
 * Created on 02-abr-2005
 *
 */
package game.logicEngine;

import game.ScreenInterface;

import java.awt.geom.*;

import util.Direction;

/**
 * @author CarlosG
 * 
 */
public interface LogicActorInterface {
    /**
     * Inicializa el actor.
     *  
     */
    public void initLogicActor();

    /**
     * Devuelve la coordenada x de la posicinhn del actor
     */
    public int getX();

    /**
     * Devuelve la coordenada y de la posicinhn del actor
     */
    public int getY();

    /**
     * Devuelve el ancho del actor
     */
    public int getHeight();

    /**
     * Devuelve el alto del actor
     */
    public int getWidth();

    /**
     * Devuelve la velocidad por defecto del actor
     */
    public float getPower();

    /**
     * Establece la velocidad por defecto del actor
     */
    public void setPower(float s);

    /**
     * @return Returns the velocity.
     */
    public Point2D.Float getVelocity();

    /**
     * Devuelve la componente x de la velocidad del actor
     */
    public float getXVelocity();

    /**
     * Devuelve la componente y de la velocidad del actor
     */
    public float getYVelocity();

    /**
     * Establece la velocidad del actor
     * 
     * @param x
     *            componente x de la velocidad
     * @param y
     *            componente y de la velocidad
     */
    public void setVelocity(float x, float y);

    /**
     * Establece la posicinhn del actor
     * 
     * @param x
     *            componente x de la posicinhn
     * @param y
     *            componente y de la posicinhn
     */
    public void setPosition(int x, int y);

    /*
     * (non-Javadoc)
     * 
     * @see game.actorInterface#setDimension(int, int)
     */public void setSize(int w, int h);

    /*
     * (non-Javadoc)
     * 
     * @see game.ActorInterface#getXScreen(game.ScreenInterface)
     */public int getXScreen(ScreenInterface s);

    /*
     * (non-Javadoc)
     * 
     * @see game.ActorInterface#getYScreen(game.ScreenInterface)
     */public int getYScreen(ScreenInterface s);

    /**
     * @return Devulelve un puntero al valor de la posicion interna del Actor.
     */
    public Point2D getInternalPosition();

    /**
     * @param internalPosition
     *            The internalPosition to set.
     */
    public void setInternalPosition(Point2D.Float internalPosition);

    /**
     * @param internalPosition
     *            The internalPosition to set.
     */
    public void setInternalPosition(double x, double y);

    /**
     * @return Devuelve la direccinhn a la que apunta el Actor.
     */
    public Direction getDirection();

    /**
     * @return Returns the angle.
     */
    public double getAngle();

    /**
     * @param angle
     *            The angle to set.
     */
    public void setAngle(double angle);

    public void updateState();

    public void setX(int x);

    public void setY(int y);

    /**
     * @return Returns the actualState.
     */
    public int getActualState();

    /**
     * @param actualState
     *            The actualState to set.
     */
    public void setActualState(int actualState);

    public boolean needsPhysics();

    public boolean getCollision(double x, double y);
    
    public boolean getCollision(int x, int y);
    public boolean getCollision(LogicActorInterface l);
    public Rectangle2D.Float getBounds();

    /**
     * @return Returns the angleVelocity.
     */
    public double getAngleVelocity();

    /**
     * @param angleVelocity
     *            The angleVelocity to set.
     */
    public void setAngleVelocity(double angleVelocity);

    /**
     * @param directionValue
     */
    public void setDirection(int directionValue);
}