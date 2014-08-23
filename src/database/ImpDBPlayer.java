package database;

/*
 * Created on 22-feb-2005
 */

/**
 * @author Carlos Segovia
 */

/**
 * Implementacinhn del interface DBPlayer
 */

public class ImpDBPlayer implements DBPlayer {

    
    private String name;

    private byte race;

    private int score;

    private short wins;

    private short loses;

    private short ties;

    private boolean isPlaying;


    /**
     * Constructor de la clase
     * 
     * @param name Nombre del jugador.
     * @param race Raza del jugador.
     */
    public ImpDBPlayer(String name, byte race) {
        this.name = name;
        this.race = race;
        this.score = 0;
        this.wins = 0;
        this.loses = 0;
        this.ties = 0;
        this.isPlaying = false;
    }


    public String getName() {
        return this.name;
    }


    public byte getRace() {
        return this.race;
    }


    public int getScore() {
        return this.score;
    }


    public short getWins() {
        return this.wins;
    }


    public short getLoses() {
        return this.loses;
    }


    public short getTies() {
        return this.ties;
    }


    public boolean getIsPlaying() {
        return this.isPlaying;
    }


    public void setScore(int newScore) {
        this.score = newScore;
    }


    public void setWins(short newWins) {
        this.wins = newWins;
    }


    public void setLoses(short newLoses) {
        this.loses = newLoses;
    }


    public void setTies(short newTies) {
        this.ties = newTies;
    }


    public void setIsPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }
}