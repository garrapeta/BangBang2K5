package weapons;

import game.logicEngine.LogicActorInterface;
import game.stages.StageInterface;

import java.awt.geom.Point2D;

/*
 * Created on 29-ene-2005
 *
 */

/**
 * Este interfaz proporciona mnhtodos para mover los objetos sujetos a fnhsica.
 * 
 * @author CarlosG
 *  
 */
public interface PhysicAmmoInterface {

    /**
     * Proporciona la siguiente posicinhn del objeto fnhsico, y actualiza la
     * posicinhn del <em>Sprite</em>.
     */
    public void nextStep();


    /**
     * Recalcula el vector de movimiento segnhn los valores de fuerza y nhngulo
     * pasados por parnhmetro. Ademnhs, los valores de los atributos se
     * actualizan. Se emplea al hacer un disparo.
     * 
     * @param logic
     * @param stage
     */
    public void initPhysicAmmo(LogicActorInterface logic, StageInterface stage);


    /**
     * Proporciona el nhngulo que debe tener el objeto fnhsico en ese momento. Una
     * bala tendrnh siempre el mismo, mientras que en un proyectil, depen dernh de
     * su posicinhn en la trayectoria.
     * 
     * @return Un nhngulo en radianes, entre -PI/2 y +PI/2 [-90,90]
     */
    public double getAngle();


    /**
     * @return Devuelve <em>true</em> si se debe producir explosinhn. Devuelve
     *         <em>false</em> en otro caso.
     */
    public boolean isExplosion();


    /**
     * @param explosion <em>True</em> si se debe producir explosinhn.
     *            <em>False</em> en otro caso.
     */
    public void setExplosion(boolean explosion);
    
    /**
     * @return Devuelve la posicinhn.
     */
    public Point2D getPosition();
    
    public void setPosition(int x, int y);
  
}