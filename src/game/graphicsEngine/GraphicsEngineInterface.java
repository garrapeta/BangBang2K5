/*
 * GraphicsEngineInterface.java
 */

package game.graphicsEngine;

import game.ScreenInterface;

/**
 * Interfaz comnhn a todos los motores grnhficos
 */
public interface GraphicsEngineInterface {

    /** Actualiza el estado del motor grnhfico */
    public void updateState();


    /**
     * Pinta en la pantalla
     * 
     * @param screen la pantalla en la que se pinta
     */
    public void draw(ScreenInterface screen);

}//interface GraphicsEngineInterface
