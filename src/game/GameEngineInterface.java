package game;

/*
 *  
 */

/**
 * Interfaz para el motor del juego
 * 
 * @author
 *  
 */
public interface GameEngineInterface {

    /**
     * Actualiza el estado del juego
     */
    public void updateState();


    /**
     * Dibuja el juego en un objeto ScreenInterface
     * 
     * @param g el objeto Graphics en el que se pinta el juego
     */
    public void draw(Screen s);


    public void playSounds();


    /**
     * Ejecuta el juego
     */
    public void run();
}//GameInterface
