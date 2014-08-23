package game.actors;

import game.logicEngine.LogicActorInterface;

/*
 *
 */

/**
 * Clase abstracta que define e implementa funcionalidad comnhn para la mayornha
 * de los actores
 */
public abstract class Actor implements ActorInterface {

    /**
     * Lnhgica del actor
     */
    protected LogicActorInterface logic;

    // Audio player que usa este actor
    protected sound.AudioPlayer audioPlayer;


    /**
     * Constructor
     *  
     */
    public Actor() {
        initActor();
    }


    public void initActor() {
    	logic = null;
    }


    /**
     * @return Devuelve la lnhgica del actor
     */
    public LogicActorInterface getLogic() {
        return logic;
    }
    
    public void setLogic(LogicActorInterface la){
    	logic=la;   
    }


    public void playSounds() {
        if (audioPlayer != null) audioPlayer.playSounds();
    }
}