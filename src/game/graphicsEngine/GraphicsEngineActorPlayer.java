/*
 * GraphicsActorPlayer.java
 */

package game.graphicsEngine;

import game.*;
import game.logicEngine.LogicActorPlayer;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

import util.*;

/**
 * Encapsula e implementa el motor grnhfico del actor
 */
public class GraphicsEngineActorPlayer implements GraphicsEngineInterface {

    public static final Color COLOR_ACTIVE_PLAYER=Color.YELLOW;
    public static final Color COLOR_INACTIVE_PLAYER=ColoursDefinitions.LIGHT_YELLOW_1;
    
    /**
     * Direccion en la que estan pintados las imagenes del actor
     */
    protected Direction initial;
	
    /**
     * Referencia a la lnhgica del nhctor que se va a pintar
     */
    protected LogicActorPlayer logic;

    /**
     * Animacinhn activa del personaje, es decir, la que tiene asignada
     * actualmente
     */
    protected Animation activeAnimation;
    

    /**
     * Transformacinhn que se aplica a la imagen del actor para modificar el lado
     * hacia la que estnh orientada
     */
    protected AffineTransform transformReverse;
    
    protected int lifeBarSize=40;
    


    /**
     * Constructor
     * 
     * @param logic2 lnhgica que sernh representada en pantalla
     */
    public GraphicsEngineActorPlayer(LogicActorPlayer logic,Direction initial) {
        transformReverse = new AffineTransform();
        init(logic,initial);
        
    }


    /**
     * Inicializa el motor grnhfico
     * 
     * @param logic lnhgica que sernh representada en pantalla
     */
    public void init(LogicActorPlayer logic,Direction initial) {
        this.logic = logic;
        this.initial = initial;
        transformReverse.setToIdentity();
        transformReverse.scale(-1, 1);
    }


    /**
     * Actualiza el estado del motor grnhfico
     */
    public void updateState() {
        activeAnimation.updateState();
    }


    /**
     * Pinta en pantalla
     */
    public void draw(ScreenInterface screen) {
        Graphics2D g2 = (Graphics2D) screen.getScreenGraphics();
        BufferedImage image = activeAnimation.getCurrentFrame();
        
        
        int xScreen=logic.getXScreen(screen);
        int yScreen=logic.getYScreen(screen);
        int offsetX=activeAnimation.getOffsetX();
        int offsetY=activeAnimation.getOffsetY();
        

        if (logic.getDirection().getDirectionValue() == Direction.LEFT) {
            transformReverse.setToIdentity();
            transformReverse.scale(-1, 1);
            transformReverse.translate(-(xScreen
                    + logic.getWidth()-offsetX), yScreen+offsetY);
            
            g2.drawImage(image, transformReverse, null);
        } else {
            g2.drawImage(image, xScreen+offsetX,yScreen+offsetY, null);
        }
        
        //g2.drawRect(xScreen,yScreen,logic.getWidth(),logic.getHeight());
        
        //g2.drawImage(Tools.imagePoint,logic.getXScreen(screen),logic.getYScreen(screen),null);
        
        
        drawLifeBar(screen);
        
        

    }//draw()
    
    
    
    private void drawLifeBar(ScreenInterface screen){
        Graphics2D g2=(Graphics2D)screen.getScreenGraphics();
        Image image=activeAnimation.getCurrentFrame();
        
        int x=logic.getXScreen(screen)+logic.getWidth()/2-lifeBarSize/2;
        int y=logic.getYScreen(screen)-12;
        
        g2.setColor(ColoursDefinitions.HUD);
        g2.drawRect(x,y,lifeBarSize+1,6);
        
        g2.setColor(ColoursDefinitions.RED_LIFE);
        int lifeLevel=Math.round(this.lifeBarSize*((float)logic.getLife()/(float)LogicActorPlayer.LIFE_INITIAL_VALUE));          
        g2.fillRect(x+1,y+1,lifeLevel,5);
        
        
        /*
         * Dibuja el nombre del jugador
         */   
        String userID=logic.getUserID();
        Font font=g2.getFont();
        Rectangle2D stringSize=font.getStringBounds(userID,g2.getFontRenderContext());
        
        x=logic.getXScreen(screen)+(int)(logic.getWidth()/2-stringSize.getWidth()/2);
        y=logic.getYScreen(screen)-18;
        
        if (logic.isActivePlayer()) 
            g2.setColor(GraphicsEngineActorPlayer.COLOR_ACTIVE_PLAYER);
        else
            g2.setColor(GraphicsEngineActorPlayer.COLOR_INACTIVE_PLAYER);
        
        g2.drawString(userID,x,y);
    }


    /**
     * Establece la animacinhn activa del actor
     * 
     * @param a
     */
    public void setActiveAnimation(Animation a) {
        activeAnimation = a;
    }//setActiveAnimation()

    /**
     * @return Returns the activeAnimation.
     */
    public Animation getActiveAnimation() {
        return activeAnimation;
    }
    
    
    public Image getImage(){
        return activeAnimation.getCurrentFrame();
    }
}//class GraphicsEngineActor
