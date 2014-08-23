/*
 * Created on 03-abr-2005
 *
 */
package game;

import game.actors.ActorPlayer;
import game.logicEngine.LogicActor;
import game.stages.StageInterface;

import java.util.List;
import java.util.ArrayList;

import sound.audioManagers.AudioManager;
import util.Constants;

/**
 * @author Bnhlder
 *  
 */
public class LocalGame implements GameInterface{
	
    /* (non-Javadoc)
     * @see game.GameInterface#getMyPlayer()
     */
    public ActorPlayer getMyPlayer() {
        return getActivePlayer();
    }
	//array de jugadores que intervienen en la partida
    private List playersArray;
    
    //jugador que posee el turno
    private ActorPlayer activePlayer;
    
    //resultado de la partida (ver util.Constants)
    private int winner;
    
    //escenario de juego
    private StageInterface stage;
    
    //reloj de partida (turnos, escapes, interludios)
    private SecondsTimer turnTimer;

    //indica el estado del turno (ver util.Constants)
    private int turnState;
    
    //indica si nos encontramos en transicinhn de turno
    //nhapa de ibnhn, se podrnha emplear el estado de transicinhn? 
    private boolean turnTransition = false;
    
    //gestor de eventos de entrada (ratnhn y teclado)
    private InputManager inputManager;

    /**
     * @param stage
     * @param actorsArray
     */
    public LocalGame(StageInterface stage, InputManager inputManager) {

        this.playersArray = new ArrayList();;

        initGame(stage, inputManager);
    }
    
    //inicializacinhn de atributos
    public void initGame(StageInterface stage, InputManager inputManager) {
        this.turnState = Constants.PLAYING;
        this.inputManager = inputManager;
        turnTimer = new SecondsTimer(this);

        activePlayer = null;
        winner = -1;
        this.stage = stage;

        filterArray();

        //TODO eliminar inicializaciones "a capon"
        //lo que hace falta es que realmente sea este array el 
        //que se tome como array de jugadores y no el del escenario
        activePlayer = (ActorPlayer) playersArray.get(0);
        ActorPlayer aux = null;
        for (int i = 0; i < playersArray.size(); i++) {
            aux = (ActorPlayer) playersArray.get(i);
            aux.getLogic().setActualState(LogicActor.STATE_SET);
            aux.getLogic().setVelocity(0, 0);
            aux.getLogic().setPosition((int)(Math.random()*this.stage.getSizeX()),
            		(int)(Math.random()*this.stage.getSizeY()));
            //		nhEstnh lnhnea debernha estar sin comentar?
            //      aux.getLogic().setPower(3f);
            aux.getBehavior().initPhysicPlayer(aux.getLogic(), this.stage);
            
        }
        turnTimer.reset(Constants.TURN_TIME);
    }

    //hay que revisarla y cambiar la llamada desde GameEngine.updateState()
    public void updateState() {
    	//se refresca la pantalla
        stage.updateState(this);
        
        //comprobar que la partida no ha finalizado
        if (winner == Constants.NO_WINNER) {
            if (playersArray.size() == 0) {
                winner = Constants.DRAW_MATCH;
            }

            if (playersArray.size() == 1) {
                winner = Constants.WINNER;
            }

            //Elimina los cadnhveres de los cobardes caidos en combate
            refreshPlayersArray();

            //si el contador<0 y jugando o escapando-> END
            if (!turnTimer.isRunning()
                    && (turnState == Constants.PLAYING || turnState == Constants.ESCAPING))
                this.turnState = (Constants.END);

            //si el contador<0 e interlude-> changeTurn
            if (!turnTimer.isRunning() && turnState == Constants.INTERLUDE)
                changeTurnUp();

            /*
             * cuando el jugador ya ha hecho todos sus disparos, aun puede
             * escapar unos segundos
             */
            if (getTurnState() == Constants.PLAYING
                    && activePlayer.getShootCounter() == 0) {
                this.turnState = Constants.ESCAPING;
                this.turnTimer.reset(Constants.ESCAPE_TIME);

                //TODO Hacer el escaping mnhs elegante
                activePlayer.setEscaping(true);
            }

            // cuando el jugador termina el turno no puede moverse
            if (turnState == Constants.END) {
                this.disableMove();
                //esperar a que finalice el disparo en curso
            }

            /*
             * cuando el jugador ya ha terminado el turno y ademas ha
             * explisonado el proyectil, se cambia de turno
             */
            if (turnState == Constants.END && !activePlayer.isShooting()) {
                if (!isAnyPlayerBusy()) {
                    changeTurnDown();
                }
            }
        }
    }

    /**
     *  este metodo comprueba que no haya jugadores saltando o muriendo
     * antes de cambiar el turno 
     */
    private boolean isAnyPlayerBusy() {
        boolean dying = false;
        int i = 0;
        while (i < playersArray.size() && !dying) {
            int actualState;

            actualState = ((ActorPlayer) playersArray.get(i)).getLogic()
                    .getActualState();
            dying = (actualState >= LogicActor.STATE_JUMP)
                    || (actualState == LogicActor.STATE_DYING);

            i++;
        }
        return dying;
    }

    
    /**
     * fin del turno activo
     */
    private void changeTurnDown() {
        //se cambia el player activo
        int pos = playersArray.indexOf(activePlayer);
        pos = pos + 1;

        if (pos < playersArray.size()) {
            activePlayer = (ActorPlayer) playersArray.get(pos);

        } else {
            activePlayer = (ActorPlayer) playersArray.get(0);
        }
        
        //indicar que se inicia el interludio entre turnos
        setTurnTransition(true);
        this.setTurnState(Constants.INTERLUDE);
        
        //deshabilitar movimiento del personaje
        this.disableMove();

        /*
         * aqui yo creo que pondrnhamos lo del cambio de turno
         */

        AudioManager.getStaticAm().play("quack", false);
        //AudioManager.getStaticAm().play("whistleDown", false);
        
        //iniciar cuenta de tiempo de interludio
        turnTimer.reset(Constants.INTERLUDE_TIME);
    }
    
    
    /**
     * comienzo del nuevo turno
     *
     */
    private void changeTurnUp() {
        //indicar al player que comienza un nuevo turno
        activePlayer.resetTurn();
        //habilitar movimiento del nuevo personaje
        this.enableMove();
        AudioManager.getStaticAm().play("whistleUp", false);
        //TODO : nhapa para cambiar el valor del viento del stage
        double random = StrictMath.random();
        double random2 = StrictMath.random();
        float newWind = (float) this.stage.getEnvironment().getMAXWIND()
                * (float) random;
        if (random2 > 0.5)
            newWind = -newWind;
        this.stage.getEnvironment().setWind(newWind);
        
        //iniciar cuenta del nuevo turno
        turnTimer.reset(Constants.TURN_TIME);
        this.turnState = Constants.PLAYING;
    }
    
    
    //habilita los eventos de teclado y ratnhn
    private void enableMove() {
        this.inputManager.setEnabled(true);
    }

    //deshabilita los eventos de teclado y ratnhn
    private void disableMove() {
        activePlayer.stop();
        this.inputManager.setEnabled(false);
    }

    /**
     * este metodo toma el array de actores del escenario e
     * inserta en playersArray los jugadores encontrados 
     * 
     * @param actorsArray
     * @return
     */
    private void filterArray() {
    	//borrar playersArray
        playersArray.clear();
        
        ArrayList actorsArray = stage.getActorsArray();
        int size = actorsArray.size();

        ActorPlayer actorPlayer = null;

        //recorrer array de actores
        for (int i = 0; i < size; i++) {
            if (actorsArray.get(i) instanceof ActorPlayer) {
                //insertar en array de jugadores playersArray
                actorPlayer = (ActorPlayer) actorsArray.get(i);
                playersArray.add(actorPlayer);
            }
        }
    }

    /**
     *  recarga el array de jugadores
     */
    private void refreshPlayersArray() {
        filterArray();
    }

    public void setTurnState(int val) {
        this.turnState = val;
    }

    public int getTurnTime() {
        return turnTimer.getTime();
    }

	/* (non-Javadoc)
	 * @see game.GameInterface#getTurnState()
	 */
    public int getTurnState() {
        return this.turnState;
    }

    /* (non-Javadoc)
	 * @see game.GameInterface#getActivePlayer()
	 */
    public ActorPlayer getActivePlayer() {
        return activePlayer;
    }

    /**
     * @return Returns the turnTransition.
     */
    public boolean isTurnTransition() {
        return turnTransition;
    }

    /**
     * @param turnTransition
     *            The turnTransition to set.
     */
    public void setTurnTransition(boolean turnTransition) {
        this.turnTransition = turnTransition;
    }

    /**
     * @return Returns the winner.
     */
    public int getWinner() {
        return winner;
    }
    
    
    public ActorPlayer getActorWinner() {
    	//por como funciona un arrayList, el ganador siempre acaba en la 
        //posicion 0 del array
        return (ActorPlayer) playersArray.get(0);
    }
    
    public SecondsTimer getTimer() {
        return turnTimer;
    }


    /**
     * @return Returns the turnTimer.
     */
    public SecondsTimer getTurnTimer() {
        return turnTimer;
    }
	/**
	 * @return Returns the stage.
	 */
	public StageInterface getStage() {
		return stage;
	}
}