/*
 * Created on 08-may-2005
 */
package game;

import game.actors.ActorPlayer;
import game.stages.StageInterface;

/**
 * @author CarlosG
 *  
 */

public interface GameInterface {

    /**
     * Actualiza el estado del juego.
     */
    public void updateState();

    /**
     * @return Tiempo del turno
     */
    public int getTurnTime();
    


    /**
     * 
     * @return Estado del turno
     */
    public int getTurnState();

    /**
     * 
     * @return Referencia al jugador activo.
     */
    public ActorPlayer getActivePlayer();

    /**
     * @return Indica si estamos en la transicinhn del turno
     */
    public boolean isTurnTransition();

    /**
     * @param turnTransition
     *            Booleano que indica si estamos en la transicinhn del turno
     */
    public void setTurnTransition(boolean turnTransition);

    /**
     * @return RDevuelve el nhndice del ganador.
     */
    public int getWinner();

    /**
     * @return Devuelve una referencia al ganador.
     */
    public ActorPlayer getActorWinner();
    
    public SecondsTimer getTimer(); 
    
    public StageInterface getStage();

    /**
     * @return
     */
    public ActorPlayer getMyPlayer();
}