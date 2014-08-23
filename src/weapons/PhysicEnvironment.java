/*
 * Created on 15-feb-2005
 *
 */
package weapons;

/**
 * Representa el entorno fnhsico de cada escenario. Cada escenario tendrnh un
 * <em>PhysicEnvironment</em>, que se propagarnh hasta los <em>Sprites</em>.
 * 
 * @author CarlosG
 *  
 */
public class PhysicEnvironment {

    private float gravity;

    private float timeStep;

    private float wind;
    
    public  float MAXWIND;


    /**
     * @param gravity Gravedad del entorno.
     * @param timeStep Incremento de tiempo de la simulacinhn.
     */
    public PhysicEnvironment(float gravity, float timeStep, float wind) {
        initPhysicEnvironment(gravity, timeStep, wind);
    }


    /**
     * Inicializa el objeto.
     * 
     * @param gravity Gravedad del entorno.
     * @param timeStep Incremento de tiempo de la simulacinhn.
     */
    public void initPhysicEnvironment(float gravity, float timeStep, float wind) {
        this.gravity = gravity;
        this.timeStep = timeStep;
        this.MAXWIND = wind;
        this.wind = 0f;
    }


    /**
     * @return Devuelve la gravedad.
     */
    public float getGravity() {
        return gravity;
    }


    /**
     * @param gravity La nueva gravedad.
     */
    public void setGravity(float gravity) {
        this.gravity = gravity;
    }


    /**
     * @return Devuelve el incremento de tiempo de la simulacinhn.
     */
    public float getTimeStep() {
        return timeStep;
    }


    /**
     * @param timeStep El nuevo incremento de tiempo.
     */
    public void setTimeStep(float timeStep) {
        this.timeStep = timeStep;
    }


    /**
     * @return Devuleve el viento.
     */
    public float getWind() {
        return wind;
    }


    /**
     * @param wind El valor del viento, en <em>float</em>.
     */
    public void setWind(float wind) {
        this.wind = wind;
    }
    
    
	/**
	 * @return Returns the mAXWIND.
	 */
	public float getMAXWIND() {
		return MAXWIND;
	}
	/**
	 * @param maxwind The mAXWIND to set.
	 */
	public void setMAXWIND(float maxwind) {
		MAXWIND = maxwind;
	}
}