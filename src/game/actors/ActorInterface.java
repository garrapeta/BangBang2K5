package game.actors;

import game.ScreenInterface;
import game.logicEngine.LogicActorInterface;
import game.stages.StageInterface;

/*
 * 
 */

/**
 * Interfaz comnhn para todos los actores del juego del juego
 * 
 * @author
 *  
 */
public interface ActorInterface {

    public LogicActorInterface getLogic();


    /**
     * Actualiza el estado del sprite
     */
    public void updateState(StageInterface s);
    
    public void setLogic(LogicActorInterface la);


    /**
     *  
     */
    public void draw(ScreenInterface s);


    public void playSounds();

}//SpriteInterface