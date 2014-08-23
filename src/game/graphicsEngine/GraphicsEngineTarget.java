/*
 * GraphicsEngineTarget.java
 */

package game.graphicsEngine;

import game.ScreenInterface;
import game.logicEngine.LogicActorInterface;

import java.awt.*;
import java.awt.geom.AffineTransform;

import util.*;

/**
 *  
 */
public class GraphicsEngineTarget implements GraphicsEngineInterface {

    protected LogicActorInterface logic;

    protected Image image;
    
    protected AffineTransform transform;


    public GraphicsEngineTarget(LogicActorInterface logic, Image image) {
        init(logic,image);
        transform=new AffineTransform();
    }


    public void init(LogicActorInterface logic, Image image) {
        this.logic = logic;
        this.image = image;
    }


    /*
     * (non-Javadoc)
     * 
     * @see game.GraphicsEngineInterface#draw(game.ScreenInterface)
     */
    public void draw(ScreenInterface screen) {
        Graphics2D g2=(Graphics2D)screen.getScreenGraphics();
        
        int x=logic.getXScreen(screen);//+logic.getWidth()/2;
        int y=logic.getYScreen(screen);//+logic.getHeight()/2;
        
        //transform.setToTranslation(x,y);
        if (logic.getDirection().getDirectionValue()==Direction.LEFT){
            transform.setToIdentity();
            transform.scale(-1,1);
                        
            transform.rotate(logic.getAngle(),-x,y);
            transform.translate(-(x-30),y-logic.getWidth()/2);
            
            //g2.drawImage(Tools.imagePoint,x-1,y-1,null);
        }       
        else{
            transform.setToIdentity();
	        transform.rotate(logic.getAngle(),x,y);
	        transform.translate(x+30,y-logic.getWidth()/2);
	        
	        //g2.drawImage(Tools.imagePoint,x-1,y-1,null);
        }
        g2.drawImage(image,transform,null);

        //g2.drawImage(Tools.imagePoint,transform,null);
        //screen.getScreenGraphics().drawImage(image,logic.getXScreen(screen),logic.getYScreen(screen),null);
    }


    /*
     * (non-Javadoc)
     * 
     * @see game.GraphicsEngineInterface#updateState()
     */
    public void updateState() {
    }
}