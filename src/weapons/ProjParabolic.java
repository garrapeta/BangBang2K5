/*
 * Created on 30-ene-2005
 *
 */
package weapons;

import game.logicEngine.LogicActorInterface;
import game.stages.Stage;
import util.Direction;
/**
 * Esta clase representa a las balas con trayectoria parabnhlica.
 * 
 * @author CarlosG
 *  
 */
public class ProjParabolic extends PhysicAmmo implements PhysicAmmoInterface {

    /**
     * Tiempo actual, necesario para calcular la canhda.
     */
    private float time;

    private float wind;


    /**
     * 
     * @param angle nhngulo del disparo en radianes
     * @param power Fuerza del disparo
     * @param gravity Gravedad del espacio fnhsico
     * @param timeStep Resolucinhn del tiempo
     * @param position Posicinhn actual del objeto fnhsico
     * @param direction La direccinhn a la que se estnh apuntando
     */
    public ProjParabolic(LogicActorInterface logic, Stage stage) {
        super(logic, stage);

        initProjectile();
    }


    /**
     * Inicializa el objeto.
     *  
     */
    public void initProjectile() {
        this.time = stage.getEnvironment().getTimeStep();
        this.wind = stage.getEnvironment().getWind();
    }


    /*
     * (non-Javadoc)
     * 
     * @see weapons.PhysicObject#calcCoord(double, double)
     */
    protected void calcCoord(double oldX, double oldY) {
        //Obtengo las nuevas coordenadas, segun trayectoria
    	double newX;
    	if ((motionVector.getX()*direction.getDirectionValue() + wind)<0){
    		direction.setDirection(Direction.LEFT);
    	}
    	if ((motionVector.getX()*direction.getDirectionValue() + wind)>=0){
    		direction.setDirection(Direction.RIGHT);
    	}
        newX = oldX + (motionVector.getX() + StrictMath.abs(wind))*
		       direction.getDirectionValue(); 
        double newY = oldY - motionVector.getY()
                + (environment.getGravity() * time) / 2;
        this.time = this.time + environment.getTimeStep();
        
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

        //Compruebo que no me he salido de los lnhmites horizontales y reboto
        if (x + (logic.getWidth() / 2) >= stage.getSizeX()) {
            direction.changeDirection();
            isOut = true;
        } else if (x <= 0) {
            direction.changeDirection();
            isOut = true;
        }

        //Compruebo que no me he salido de los lnhmites verticales y exploto
        if (y + (logic.getHeight() / 2) >= stage.getSizeY()) {
            setExplosion(true);
            isOut = true;
        } else if (y - (logic.getHeight() / 2) <= 0) {
            isOut = true;
        }

        return isOut;
    }
}