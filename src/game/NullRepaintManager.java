/*
 * NullRepaintManager.java
 */

package game;

import javax.swing.*;

/**
 *  
 */
public class NullRepaintManager extends RepaintManager {

    public static void install() {
        RepaintManager rm = new NullRepaintManager();
        NullRepaintManager.setCurrentManager(rm);
    }


    public void addInvalidComponent(JComponent c) {

    }


    public void addDirtyRegion(JComponent c, int x, int y, int w, int h) {

    }


    public void markCompletelyDirty(JComponent c) {

    }


    public void paintDirtyRegions() {

    }

}