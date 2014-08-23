
import java.io.File;

import game.*;
import game.stages.*;

import javax.swing.*;

import util.Tools;

import network.ClientServer;


//import test.StageTest;
import GUI.*;

/*
 * 
 */

/**
 * @author
 *  
 */
public class Main {
    
    public static final int SPLASH_TIME=2000;

    /**
     * Mnhtodo principal
     * 
     * @param args
     */
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        SplashGUI splashGUI=new SplashGUI(null,Tools.loadBufferedImage("art"+File.separator+"splash.png"));
        splashGUI.setVisible(true);
        splashGUI.toFront();
        
        try {
            Thread.sleep(SPLASH_TIME);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        splashGUI.dispose();

        ChooseGUI chooseGUI1 = new ChooseGUI(); chooseGUI1.setVisible(true);
    }
}