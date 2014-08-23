/*
 * Created on 09-abr-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package game.races;

/**
 * @author David Toro
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Biomorphs implements RaceInterface{
	    private static final float walkValue = 6f;

	    private static final float jumpValue = 8f;

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
