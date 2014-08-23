package game.stages;

import game.*;
import game.ScreenInterface;
import game.actors.*;
import game.actors.ammo.ActorAmmo;
import game.logicEngine.LogicActorAmmo;
import game.logicEngine.LogicActorInterface;
import game.logicEngine.LogicActorPlayer;

import java.util.ArrayList;

import weapons.*;

/*
 * 
 */

/**
 * Interfaz para el escenario del juego
 * 
 * @author
 *  
 */
public interface StageInterface {

    /**
     * Devuelve la dimensinhn horizontal (ancho) del escenario
     * 
     * @return el ancho del escenario
     */
    public int getSizeX();

    /**
     * Devuelve la dimensinhn vertical (alto) del escenario
     * 
     * @return el alto del escenario
     */
    public int getSizeY();

    /**
     * Pinta el escenario en un ScreenInterface
     * 
     * @param s
     */
    public void draw(ScreenInterface s);

    /**
     * Actualiza el estado del escenario y todos sus sprites
     */
    public void updateState(GameInterface game);

    // Ejecuta todas las acciones de sonido para los actores de un stage
    public void playSounds();


    // Devuelve el AudioManager de un stage
    public sound.audioManagers.AudioManagerInterface getAudioManager();

    public void addActor(ActorInterface actor);

    public void removeActor(ActorInterface actor);

    public boolean getCollision(double x, double y);

    /*
     * Devuelve el componente del mapa del escenario que debe salir en la
     * pantalla en pequenhito para mostrar tu posicinhn @return
     */
    public Radar getStageMap();

    public PhysicEnvironment getEnvironment();

    public void commitExplossion(int x, int y, Explossion e);

    public void updatePlayersState();

    public ActorPlayer getActivePlayer();

    public void setActivePlayer(ActorPlayer activePlayer);

    /**
     * Informa si existe colisinhn con algnhn personaje. Emplea las bounding boxes
     * para ello.
     * 
     * @param logic
     *            La logica del proyectil
     * @return true si choca, false en otro caso
     */
    public boolean getPlayersCollision(LogicActorInterface logic);

    public ActorAmmo getActiveProjectile();

    /**
     * @param newAmmo
     */
    public void setActiveProjectile(ActorAmmo newAmmo);

    /**
     * @return
     */
    public ArrayList getActorsArray();

    /**
     * Busca en el array de actores segnhn el nombre.
     * 
     * @param actorID
     *            Nombre que buscamos
     * @return Referencia al actor.
     */
    public ActorPlayer searchActor(String actorID);

    /**
     * @param player
     *  
     */
    public void calculateDamage(ActorPlayer player);

    /**
     * @param player
     */
    public void calculateFallDamage(ActorPlayer player);
    
    

}//StageInterface;
