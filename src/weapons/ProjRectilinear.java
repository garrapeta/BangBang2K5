/*
 * Created on 30-ene-2005
 *
 * 
 */
package weapons;


import game.logicEngine.LogicActorInterface;
import game.stages.Stage;

/**
 * Esta clase representa a las balas con trayectoria recta. Requiere los mnhtodos
 * <em>getCollision(double x, double y)</em> y <em>getWidth()</em> del motor
 * grnhfico.
 * 
 * @author CarlosG
 *  
 */
public class ProjRectilinear extends PhysicAmmo {

    /**
     * 
     * @param angle nhngulo del disparo en radianes
     * @param power Fuerza del disparo
     * @param gravity Gravedad del espacio fnhsico
     * @param position Posicinhn actual del objeto fnhsico
     */
    public ProjRectilinear(LogicActorInterface logic, Stage stage) {
        
        super(logic, stage);

    }


    /*
     * (non-Javadoc)
     * 
     * @see weapons.PhysicObject#calcCoord(double, double)
     */
    protected void calcCoord(double oldX, double oldY) {
        //Obtengo las nuevas coordenadas, segun trayectoria
        double newX = oldX + motionVector.getX()
                * direction.getDirectionValue();
        double newY = oldY - motionVector.getY();
        this.setTanAngle(newX, newY);
        this.setPosition(newX, newY);
    }


    /*
     * (non-Javadoc)
     * 
     * @see weapons.PhysicAmmo#checkAmmoOutOfBounds(double, double)
     */
    protected boolean checkAmmoOutOfBounds(double x, double y) {
        boolean isOut = false;
        //TODO Peta cuando apuntamos hacia arriba, se que da explotao a medio
        // camino

        //Compruebo que no me he salido de los lnhmites horizontales y reboto
        if (x + (logic.getWidth() / 2) >= stage.getSizeX()) {
            direction.changeDirection();
            isOut = true;
        } else if (x - (logic.getWidth() / 2) <= 0) {
            direction.changeDirection();
            isOut = true;
        }

        //Compruebo que no me he salido de los lnhmites verticales y exploto
        if (y + (logic.getHeight() / 2) >= stage.getSizeY()) {
            setExplosion(true);
            isOut = true;
        } else if (y - (logic.getHeight() / 2) <= 0) {
            setExplosion(true);
            isOut = true;
        }
        return isOut;
    }
}