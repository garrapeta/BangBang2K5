/*
 * Created on 01-mar-2005
 *
 */
package util;

import java.awt.Point;

/**
 * @author David
 * 
 */
public class Pencil {

    private double direction;

    private Point position;


    /**
     * Constructor
     * 
     * @param point Es el punto sobre el que estnh el lnhpiz
     * @param direction El anhngulo sobre el que se mueve el lnhpiz
     */

    public Pencil(Point point, double direction) {
        position = new Point();
        position.setLocation(point);
        this.direction = direction;
    }


    /**
     * Accesor
     * 
     * @return La posicinhn del lnhpiz
     */

    public Point getPosition() {
        return position;
    }


    /**
     * Mutador
     * 
     * @param newPosition La nueva posicinhn del lnhpiz
     */

    public void moveTo(Point newPosition) {
        this.position.setLocation(newPosition);
    }


    /**
     * Mutador
     * 
     * @param x La posicinhn en coordenadas x del lnhpiz
     * @param y La posicinhn en coordenadas y del lnhpiz
     */

    public void moveTo(int x, int y) {
        this.position.setLocation(x, y);
    }


    /**
     * Accesor
     * 
     * @return La direccinhn(nhngulo) del lnhpiz
     */

    public double getDirection() {
        return direction;
    }


    /**
     * Mutador
     * 
     * @param newDirection La nueva direccinhn(nhngulo) del lnhpiz
     */

    public void turnTo(double newDirection) {
        this.direction = newDirection;
        while (direction < 0)
            direction += 360;
        while (direction > 360)
            direction -= 360;
    }


    /**
     * Mutador
     * 
     * @param ang La direccinhn(nhngulo) que se sumarnh al nhngulo actual del lnhpiz
     */

    public void turn(double ang) {
        this.direction += ang;
        while (direction < 0)
            direction += 360;
        while (direction > 360)
            direction -= 360;
    }


    /**
     * Mutador
     * 
     * @param z La cantidad que avanza el lnhpiz en la direccinhn determinada por
     *            su nhngulo
     */

    public void nextStep(double z) {
        double newx, newy;
        int x, y;
        newx = z * StrictMath.cos(StrictMath.PI * direction / 180);
        newy = z * StrictMath.sin(StrictMath.PI * direction / 180);
        x = (int) (newx + position.getX());
        y = (int) (newy + position.getY());
        position.setLocation(x, y);
    }
}