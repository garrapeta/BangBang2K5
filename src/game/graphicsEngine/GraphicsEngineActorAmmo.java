/*
 * GraphicsEngineActorAmmo.java
 */

package game.graphicsEngine;

import game.*;
import game.logicEngine.*;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import util.Tools;


/**
 *  
 */
public class GraphicsEngineActorAmmo implements GraphicsEngineInterface {

    protected LogicActorInterface logic;

    protected Animation normalAnim;

    protected Animation bangAnim;

    protected Animation activeAnimation;

    protected AffineTransform transform;
    


    public GraphicsEngineActorAmmo(LogicActorInterface logic) {
        transform = new AffineTransform();
        init(logic);
    }


    public void init(LogicActorInterface logic) {
        this.logic = logic;
    }


    /*
     * (non-Javadoc)
     * 
     * @see game.GraphicsEngineInterface#updateState()
     */
    public void updateState() {
        if (logic.getActualState() == LogicActor.STATE_BANGBANGING) {
            activeAnimation = bangAnim;
        }
        activeAnimation.updateState();
    }


    /*
     * (non-Javadoc)
     * 
     * @see game.GraphicsEngineInterface#draw(game.ScreenInterface)
     */
    public void draw(ScreenInterface screen) {
        Graphics2D g2 = (Graphics2D) screen.getScreenGraphics();
        BufferedImage image = activeAnimation.getCurrentFrame();

        transform.setToTranslation(logic.getXScreen(screen)
                + activeAnimation.getOffsetX(), logic.getYScreen(screen)
                + activeAnimation.getOffsetY());

        //Eto lo he hecho yo, CarlosG OOEE OOE!!
        transform.rotate(logic.getAngle(), (logic.getWidth() / 2), (logic
                .getHeight() / 2));

        g2.drawImage(image, transform, null);
        g2.drawImage(Tools.imagePoint, transform, null);

    }


    /*
     * (non-Javadoc)
     * 
     * @see game.GraphicsEngineInterface#setActiveAnimation(game.Animation)
     */
    public void setActiveAnimation(Animation a) {
        activeAnimation = a;

    }


    /**
     * @return Returns the bangAnim.
     */
    public Animation getBangAnim() {
        return bangAnim;
    }


    /**
     * @param bangAnim The bangAnim to set.
     */
    public void setBangAnim(Animation bangAnim) {
        this.bangAnim = bangAnim;
    }


    /**
     * @return Returns the activeAnimation.
     */
    public Animation getActiveAnimation() {
        return activeAnimation;
    }
}