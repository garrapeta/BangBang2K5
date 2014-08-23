/*
 * Created on 15-feb-2005
 *
 */
package util;

/**
 * Representa la direccinhn hacia donde mira el personaje.
 * 
 * @author CarlosG
 *  
 */
public class Direction {

    /**
     * Constante de direccinhn igual a -1.
     */
    public static final int LEFT = -1;

    /**
     * Constante de direccinhn igual a 1.
     */
    public static final int RIGHT = 1;

    private int direction;


    public Direction() {
        setDirection(LEFT);
    }


    /**
     * @return Devuelve la direccinhn.
     */
    public int getDirectionValue() {
        return direction;
    }


    /**
     * @param direction La nueva direccinhn.
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }


    public int changeDirection() {
        this.setDirection(direction * -1);
        return this.getDirectionValue();
    }
}