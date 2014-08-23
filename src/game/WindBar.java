/*
 * Created on 11-abr-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package game;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JComponent;

import util.*;
/**
 * @author David Toro
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class WindBar extends JComponent{
	public final int borderThick=2;
    
    protected BufferedImage image;
    protected float maxWind;
    protected float wind;
    
    
    
    
    public WindBar(float maxWind){
        image=Tools.loadBufferedImage("art"+File.separator+"powerBar.png");
        initWindBar(maxWind);
    }
    
    public void initWindBar(float maxWind){
        this.maxWind = maxWind;
        this.wind=0;
        this.setSize(image.getWidth()+2*borderThick,image.getHeight()+2*borderThick);
        this.setBorder(javax.swing.BorderFactory.createLineBorder(ColoursDefinitions.HUD,borderThick));
    }
    
    public void paint(Graphics g){
        
        this.paintBorder(g);
        
        int width=image.getWidth();
        int height=image.getHeight();
        
        float power=(float)wind/(float)maxWind;
        
        int width2=Math.round((width*power)/2);
        if(power != 0)
        g.drawImage(image,width/2,height + borderThick,width/2 + borderThick + width2,
          		      borderThick,0,0,width,height,null);
      	
        g.drawLine(width/2,height - 30,width/2,height + 80); 
    }
    
    public void repaint(){
        
    }

    /**
     * @return Returns the wind.
     */
    public float getWind() {
        return wind;
    }
    /**
     * @param wind The wind to set.
     */
    public void setWind(float wind) {
        this.wind = wind;
    }
}
