package game;

import game.actors.Actor;
import game.stages.StageInterface;

import java.awt.Graphics;

/*
 * 
 */

/**
 * Interfaz para el componente donde se pinta el juego
 * 
 * @author
 *  
 */
public interface ScreenInterface {

    /**
     * Devuelve la resolucnhon horizontal de la pantalla
     * 
     * @return resolucinhn horizontal
     */
    public int getXRes();


    /**
     * Devuelve la resolucnhon vertical de la pantalla
     * 
     * @return resolucinhn vertical
     */
    public int getYRes();


    /**
     * Devuelve el desplzamiento horizontal de la pantalla respecto del
     * escenario
     * 
     * @return desplazamiento horizontal
     */
    public int getOffsetX();


    /**
     * Devuelve el desplazamiento vertical de la pantalla respecto del escenario
     * 
     * @return desplazamiento vertical
     */
    public int getOffsetY();


    public void setOffsetX(int x);


    public void setOffsetY(int y);


    /**
     * Devuelve el objeto Graphics para pintar sobre nhl en la pantalla
     * 
     * @return
     */
    public Graphics getScreenGraphics();


    /**
     * Debe ser invocado una vez finalizado el dibujado sobre el objeto graphics
     * para asnh volcar el buffer sobre la pantalla y mostrar el resultado final
     *  
     */
    public void showScreen();
    
    public void setCentered(StageInterface stage,Actor actor);

}