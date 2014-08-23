/*
 * PowerBar.java
 */

package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JComponent;

import util.*;


/**
 * Clase que implementa la barra de potencia del disparo
 */
public class PowerBar extends JComponent{
    
    /**
     * Grosor del marco de la barra
     */
	public final int borderThick=2;
    
    /*Imagen del relleno la barra*/
	protected BufferedImage image;
    /*Cota superior del tiempo de pulsacinhn del diparo en ms*/
	protected long maxShotTime;
    /*Guarda el tiempo de la barra*/
	protected long shotTime;
    
    
    
    /**
     * Constructor
     * @param maxShotTime indica el tiempo de pulsacinhn mnhximo
     */
    public PowerBar(long maxShotTime){
        image=Tools.loadBufferedImage("art"+File.separator+"powerBar.png");
        initPowerBar(maxShotTime);
    }
    
    public void initPowerBar(long maxShotTime){
        this.maxShotTime=maxShotTime;
        this.shotTime=0;
        this.setSize(image.getWidth()+2*borderThick,image.getHeight()+2*borderThick);
        this.setBorder(javax.swing.BorderFactory.createLineBorder(ColoursDefinitions.HUD,borderThick));
    }
    
    /*Pinta el componente*/
    public void paint(Graphics g){
        
        this.paintBorder(g);
        
        int width=image.getWidth();
        int height=image.getHeight();
        
        float power=(float)shotTime/(float)maxShotTime;
        
        width=Math.round(width*power);
        g.drawImage(image,borderThick,borderThick,width+borderThick,height+borderThick,0,0,width,height,null);
    }
    
    public void repaint(){
        
    }

    /**
     * @return Returns the shotTime.
     */
    public long getShotTime() {
        return shotTime;
    }
    /**
     * @param shotTime The shotTime to set.
     */
    public void setShotTime(long shotTime) {
        this.shotTime = shotTime;
    }
}
