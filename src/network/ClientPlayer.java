/*
 * Created on 12-may-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package network;

import util.Constants;

/**
 * @author JRDR
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ClientPlayer {
    String name;

    String playerType;

    int posicion;

    int race;

    public ClientPlayer(String nam, String player) {

        name = nam;
        playerType = player;
        playerCodif(playerType);
    }

    public ClientPlayer(int pos) {
        name = "";
        playerType = "";
        posicion = pos;
    }

    public ClientPlayer(String nam) {

        name = nam;
        playerType = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public void setPosicion(int p) {
        posicion = p;
    }

    public String getPlayerType() {
        return playerType;
    }

    private void playerCodif(String playerType2) {
        if (playerType2.startsWith("Scrapper"))
            race = Constants.RACE_SCRAPER;
        else if (playerType2.startsWith("Biomorph"))
            race = Constants.RACE_BIOMORPH;
        else if (playerType2.startsWith("G-Marine"))
            race = Constants.RACE_GMARINE;
        else if (playerType2.startsWith("Ninfa"))
            race = Constants.RACE_NINFA;
        else if (playerType2.startsWith("Eleo"))
            race = Constants.RACE_ELEO;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPlayerType(String pt) {
        playerType = pt;
        playerCodif(pt);
    }

    /**
     * @return Returns the race.
     */
    public int getRace() {
        return race;
    }
}