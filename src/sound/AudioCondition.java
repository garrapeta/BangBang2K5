package sound;

/**
 * AudioCondition implementa la condicion a la que se tiene que ajustar el
 * estado actual de un actor para producir un determinado evento de sonido
 * 
 * @author Sergio Torres
 */

public class AudioCondition {

    protected int prevState;

    protected int actualState;

    protected int weapon;


    /**
     * Constructor
     * 
     * @param ps Estado previo
     * @param as Estado actual
     * @param cw Arma actual
     */
    public AudioCondition(int ps, int as, int cw) {
        this.prevState = ps;
        this.actualState = as;
        this.weapon = cw;
    }


    /**
     * @return Returns the actualState.
     */
    public int getActualState() {
        return actualState;
    }


    /**
     * @return Returns the miscCondition.
     */
    public int getCurrentWeapon() {
        return weapon;
    }


    /**
     * @return Returns the prevState.
     */
    public int getPrevState() {
        return prevState;
    }


    /**
     * @return Devuelve una representacion String
     */
    public String toString() {
        return ("<PrevSt " + prevState + ", AcSt " + actualState + ", Weapon "
                + weapon + ">");
    }

}