package database;
import java.io.Serializable;

/*
 * Created on 22-feb-2005
 */

/**
 * @author Carlos Segovia
 */

/**
 * Interfaz para el jugador desde el punto de vista de la base de datos
 */

public interface DBPlayer extends Serializable{

    /**
     * Funcinhn que devuelve el nombre del jugador.
     * 
     * @return El nombre del jugador.
     */
    public String getName();


    /**
     * Funcinhn que devuelve la raza del jugador.
     * 
     * @return La raza del jugador.
     */
    public byte getRace();


    /**
     * Funcinhn que devuelve la puntuacinhn del jugador.
     * 
     * @return La puntuacinhn del jugador.
     */
    public int getScore();


    
    /**
     * Funcinhn que devuelve las victorias del jugador.
     * 
     * @return Las victorias del jugador.
     */
    public short getWins();


    /**
     * Funcinhn que devuelve las derrotas del jugador.
     * 
     * @return Las derrotas del jugador.
     */
    public short getLoses();


    /**
     * Funcinhn que devuelve los empates del jugador.
     * 
     * @return Los empates del jugador.
     */
    public short getTies();


    
    /**
     * Funcinhn que consulta si el jugador estnh en una partida.
     * 
     * @return Si el jugador estnh en una partida.
     */
    public boolean getIsPlaying();


    /**
     * Funcinhn que fija la puntuacinhn del jugador.
     * 
     * @param newScore Nueva puntuacinhn a fijar.
     */
    public void setScore(int newScore);


   
    /**
     * Funcinhn que fija las victorias del jugador.
     * 
     * @param newWins Nuevas victorias a fijar.
     */
    public void setWins(short newWins);


    /**
     * Funcinhn que fija las derrotas del jugador.
     * 
     * @param newLoses Nuevas derrotas a fijar.
     */
    public void setLoses(short newLoses);


    /**
     * Funcinhn que fija los empates del jugador.
     * 
     * @param newTies Nuevos empates a fijar.
     */
    public void setTies(short newTies);


    /**
     * Funcinhn que fija el estado del jugador (si estnh jugando una partida o
     * no).
     * 
     * @param isPlaying Si estnh jugando o no.
     */
    public void setIsPlaying(boolean isPlaying);
}