/*
 * stageMap.java
 */

package game.stages;

import game.ScreenInterface;
import game.actors.ActorInterface;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import util.Tools;

/**
 * Radar del escenario 
 */
public class Radar {

    private BufferedImage image;
    private int radarLocationX=0;
    private int radarLocationY=0;
    
    private float xFactorScale;
    private float yFactorScale;
    
    private int screenWidth;
    private int screenHeight;
    
    private ArrayList actorsArray;


    public Radar(BufferedImage original,BufferedImage map) {
        super();
        init(original,map);
    }


    public void init(BufferedImage original, BufferedImage map) {
        actorsArray=null;
        this.image = map;
        xFactorScale=(float)map.getWidth()/(float)original.getWidth();
        yFactorScale=(float)map.getHeight()/(float)original.getHeight();
        screenWidth=Math.round(xFactorScale*640);
        screenHeight=Math.round(yFactorScale*480);
    }
    
    
    public void draw(ScreenInterface s) {
        Graphics2D g=(Graphics2D)s.getScreenGraphics();
        g.drawImage(image, radarLocationX, radarLocationY, null);
        g.setColor(Color.YELLOW);
        int xScreen=(int)(radarLocationX+s.getOffsetX()*this.xFactorScale);
        int yScreen=(int)(radarLocationY+s.getOffsetY()*this.yFactorScale);
        g.drawRect(xScreen,yScreen,screenWidth,screenHeight);
        
        int x,y;
        int size=actorsArray.size();
        ActorInterface actor;
        Color oldColor=g.getColor();
        g.setColor(Color.RED);
        for (int i=0;i<size;i++){
            actor=(ActorInterface)actorsArray.get(i);
            x=actor.getLogic().getX();
            y=actor.getLogic().getY();
            x=(int)(x*xFactorScale)+this.radarLocationX;
            y=(int)(y*yFactorScale)+this.radarLocationY;
            g.fillRect(x,y,2,2);
        }
        g.setColor(oldColor);
        
    }


    
    /**
     * @return Returns the x.
     */
    public int getX() {
        return radarLocationX;
    }
    /**
     * @param x The x to set.
     */
    public void setX(int x) {
        this.radarLocationX = x;
    }
    /**
     * @return Returns the y.
     */
    public int getY() {
        return radarLocationY;
    }
    /**
     * @param y The y to set.
     */
    public void setY(int y) {
        this.radarLocationY = y;
    }
    
    public void setLocation(int x,int y){
        this.radarLocationX=x;
        this.radarLocationY=y;
    }
    /**
     * @return Returns the actorsArray.
     */
    public ArrayList getActorsArray() {
        return actorsArray;
    }
    /**
     * @param actorsArray The actorsArray to set.
     */
    public void setActorsArray(ArrayList actorsArray) {
        this.actorsArray = actorsArray;
    }
}