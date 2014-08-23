package database;

import java.util.*;

/*
 * Created on 23-feb-2005
 */

/**
 * @author Carlos Segovia
 */

/**
 * Implementacinhn del interface DBUser
 */

public class ImpDBUser implements DBUser {

    private String name;

    private String password;

    private List listOfPlayers;

    private DBPlayer activePlayer;


    /**
     * Constructor de la clase
     * 
     * @param name Nombre del usuario.
     * @param password Password del usuario.
     */
    public ImpDBUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.listOfPlayers = new ArrayList(5);
        this.activePlayer = null;
    }


    /**
     * Constructor de la clase
     * 
     * @param name Nombre del usuario.
     * @param password Password del usuario.
     * @param listOfPlayers lista de jugadores del usuario.
     */
    public ImpDBUser(String name, String password, List listOfPlayers) {
        this.name = name;
        this.password = password;
        this.listOfPlayers = listOfPlayers;
        this.activePlayer = null;
    }


    public String getName() {
        return this.name;
    }


    public String getPassword() {
        return this.password;
    }


    public List getPlayers() {
        return listOfPlayers;
    }


    public DBPlayer getActivePlayer() {
        return this.activePlayer;
    }


    public void setPassword(String newPassword) {
        this.password = newPassword;
    }


    public void setActivePlayer(DBPlayer newActivePlayer) {
        this.activePlayer = newActivePlayer;
    }


    public void addPlayer(DBPlayer newPlayer) {
        this.listOfPlayers.add(newPlayer);
    }
}