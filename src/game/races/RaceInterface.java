/*
 * Created on 14-mar-2005
 */
package game.races;

/**
 * Interfaz de las razas que constan en el juego. Proporciona los atributos
 * bnhsicos.
 * 
 * @author CarlosG
 */
public interface RaceInterface {

    /**
     * @return La velocidad de la raza.
     */
    public float getWalkValue();


    /**
     * @return La altura del salto
     */
    public float getJumpValue();


    /**
     * 
     * @return El angulo del salto
     */
    public double getJumpAngle();

}