/*
 * SplashGUI.java
 */

package GUI;

import java.awt.*;
import java.io.File;

import javax.swing.*;

import util.Tools;


/**
 *
 */
public class SplashGUI extends JWindow{
    
    JLabel label;
    
    public SplashGUI(Frame owner,Image image){        
        super(owner);
        label=new JLabel();
        initSplashGUI(image);
    }
    
    
    public void initSplashGUI(Image image){
        //this.getContentPane().setLayout(null);
       
        Dimension screenRes=Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(image.getWidth(null),image.getHeight(null));
        int x=screenRes.width/2-this.getWidth()/2;
        int y=screenRes.height/2-this.getHeight()/2;
        this.setLocation(x,y);
        
        label.setIcon(new ImageIcon(image));
        label.setLocation(0,0);
        this.getContentPane().add(label);
     
        
    }

    public static void main(String[] args) {
        SplashGUI l=new SplashGUI(null,Tools.loadBufferedImage("art"+File.separator+"loading.png"));
        l.setVisible(true);
    }

    
}
