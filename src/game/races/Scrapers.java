/*
 * Created on 14-mar-2005
 */
package game.races;

/**
 * @author CarlosG
 *  
 */
public class Scrapers implements RaceInterface {

    private static final float walkValue = 3f;

    private static final float jumpValue = 6f;

    private static final double jumpAngle = StrictMath.toRadians(65);


    /*
     * (non-Javadoc)
     * 
     * @see game.races.RaceInterface#getWalkValue()
     */
    public float getWalkValue() {
        return walkValue;
    }


    /*
     * (non-Javadoc)
     * 
     * @see game.races.RaceInterface#getJumpValue()
     */
    public float getJumpValue() {
        return jumpValue;
    }


    /*
     * (non-Javadoc)
     * 
     * @see game.races.RaceInterface#getJumpAngle()
     */
    public double getJumpAngle() {
        return jumpAngle;
    }
}