package database;
import java.io.Serializable;

import java.util.List;

/*
 * Created on 23-feb-2005
 */

/**
 * @author Carlos Segovia
 */

/**
 * Interfaz para el usuario desde el punto de vista de la base de datos
 */

public interface DBUser extends Serializable{

    /**
     * Funcinhn que devuelve el nombre del usuario.
     * 
     * @return El nombre del usuario.
     */
    public String getName();


    /**
     * Funcinhn que devuelve el password del usuario.
     * 
     * @return El password del usuario.
     */
    public String getPassword();


    /**
     * Funcinhn que devuelve la lista de jugadores del usuario.
     * 
     * @return La lista de jugadores del usuario.
     */
    public List getPlayers();


    /**
     * Funcinhn que devuelve el jugador activo del usuario.
     * 
     * @return El jugador activo del usuario.
     */
    public DBPlayer getActivePlayer();


    /**
     * Funcinhn que fija el password del usuario.
     * 
     * @param newPassword Nuevo password a fijar.
     */
    public void setPassword(String newPassword);


    /**
     * Funcinhn que fija el jugador activo del usuario.
     * 
     * @param p Jugador activo a fijar.
     */
    public void setActivePlayer(DBPlayer p);


    /**
     * Funcinhn que anhade un jugador a la lista de jugadores del usuario.
     * 
     * @param p Nuevo jugador a anhadir.
     */
    public void addPlayer(DBPlayer p);
}