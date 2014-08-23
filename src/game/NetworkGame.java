/*
 * Created on 03-abr-2005
 *
 *TODO Snhlo queda hacer algo con winner, y meter las instrucciones de red
 */
package game;

import game.actors.Actor;
import game.actors.ActorPlayer;
import game.logicEngine.LogicActor;
import game.logicEngine.LogicActorPlayer;
import game.stages.StageInterface;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.util.ArrayList;

import network.Chat;
import sound.audioManagers.AudioManager;
import util.Constants;

/**
 * @author CarlosG
 * 
 * Implementa en el cliente la funcionalidad de juego en red. Se encarga de
 * escuchar al servidor, actualizando el juego segnhn el tipo de mensaje que
 * llegue. Se encarga, ademnhs de ayudar al servidor en la cesinhn del turno.
 * Concretamente espera a que el jugador termine de disparar o termine su
 * movimiento de esacpe y luego notifica al servidor que puede quitarle el
 * turno.
 */
public class NetworkGame implements GameInterface {
	
	private ByteBuffer bufferTCP;
	private ByteBuffer bufferUDP;
    /**
     * Stage en el que se desarrolla la partida en curso.
     */
    private StageInterface stage;

    /**
     * Indica si estoy en mi turno o no.
     */
    private boolean myTurn;

    /**
     * Indica a que sala pertenece la partida en curso, para facilitar al
     * servidor la ubicacinhn del usuario.
     */
    private int roomGame;

    /**
     * Lleva la cuenta de los segundos en el turno, pero snhlo de manera
     * aproximada e informativa. El reloj que se encarga de conceder los turnos
     * estnh en el servidor.
     */
    private SecondsTimer turnTimerAprox;

    private int turnState;

    private boolean turnTransition = false;

    private ActorPlayer myPlayer;

    private ActorPlayer activePlayer;

    private InputManager inputManager;

    private int winner;

    private int loops = 0;

	private Chat chat;
	
	private String userInTurn;

    /**
     * Constructor. Crea los objetos necesarios y los inicializa segnhn los
     * parnhmetros.
     * 
     * @param stage
     *            El stage de la partida en curso
     * @param roomGame
     *            La sala a la que pertenece la partida.
     * @param inputManager
     *            Referencia al control de ususario.
     */
    public NetworkGame(StageInterface stage, InputManager inputManager,
            int roomGame, Chat chat) {
        turnTimerAprox = new SecondsTimer(this);

        init(stage, inputManager, roomGame, chat);
    }

    /**
     * Inicializa los objetos y atributos ya creados, segnhn los parnhmetros.
     * 
     * @param stage
     *            El stage de la partida en curso
     * @param roomGame
     *            La sala a la que pertenece la partida.
     * @param inputManager
     *            Referencia al control de ususario.
     */
    public void init(StageInterface stage, InputManager inputManager,
            int roomGame, Chat chat) {    	
        this.stage = stage;
        this.roomGame = roomGame;
        this.inputManager = inputManager;        
        this.chat=chat;
        chat.setCurrentGame(this);
        bufferTCP=ByteBuffer.allocate(Constants.BYTE_BUFFER_SIZE);
        bufferTCP.clear();
        bufferUDP=ByteBuffer.allocate(Constants.BYTE_BUFFER_SIZE);
        bufferUDP.clear();
        
        this.myTurn = false;
        this.myPlayer = stage.searchActor(chat.getClientPlayer().getName());
        //System.out.println("Asnh que myPlayer es: " + myPlayer);
        //TODO inicializacion a capon de active player
        this.activePlayer = (ActorPlayer) stage.getActorsArray().get(0);
        if(myPlayer.equals(activePlayer)) this.myTurn = true; 
        //System.out.println("La raza de myPlayer es " + myPlayer.getRaceCode());
        this.winner = Constants.NO_WINNER;
        turnTimerAprox.reset(Constants.TURN_TIME);
        setTurnState(Constants.PLAYING);
       // System.out.println("Estamos jugando con " + Constants.PLAYING);
    }

public void updateState() {
        //Se actualiza el juego
        stage.updateState(this);

        //Consulto si estoy muerto
        if (myPlayer.getLogic().getActualState() == LogicActor.STATE_REMOVING) {
            //notificar muerte al servidor, mandando ID y room
            chat.sendDead(roomGame, myPlayer);
            myPlayer = null;
        }
        
        if (winner == Constants.NO_WINNER) {
            if (stage.getActorsArray().size() == 0) {
                winner = Constants.DRAW_MATCH;
            }

            if (stage.getActorsArray().size() == 1) {
                winner = Constants.WINNER;
            }
        }

        //Si es mi turno: me muevo y envio, espero msg de fin de turno,
        // compruebo si tengo que confirmar.
        if (isMyTurn()) {        	
            enableMove();
            
            //me muevo y envio
            loops++;
            if (loops == 4) {
                //Enviar estado del player
                sendLogic();
                loops = 0;
            }

            //Esperamos snhlo mensaje de peticinhn de fin de turno
            listenTCP();
            //processBufferTCP();

            /*
            if(msg == 'd'){
                receiveTurnQuery();
            }
            else{
                //Lo desechamos.En principio no debernha recibir otro tipo de
                // mensajes 
            }
            */ 

            //Compruebo si cambia el estado del turno
            checkTurn();
            
            
            //compruebo si tengo que confirmar
            if (getTurnState() == Constants.END_TURN_TIME
                    && !activePlayer.isShooting()) {
                if (!isAnyPlayerBusy()) {
                    changeTurnDown();
                }
            }

            //No es mi turno: escucho y actualizo logica de juego
        } else {   
        		//System.out.println("sin turno");
           disableMove();
            listenTCP();
            listenUDP();
//        	processBufferTCP();
//        	processBufferUDP();
        }
    }    /**
          * Comprueba si se ha de cambiar el turno
          */
    private void checkTurn() {
        if (getTurnState() == Constants.PLAYING
                && activePlayer.getShootCounter() == 0) {
            this.turnState = Constants.ESCAPING;
            this.turnTimerAprox.reset(Constants.ESCAPE_TIME);

            activePlayer.setEscaping(true);
        }

        //Si el contador<0 y escapando-> END_TURN_TIME
        if (!turnTimerAprox.isRunning() && getTurnState() == Constants.ESCAPING) {
            this.turnState = (Constants.END_TURN_TIME);
        }
    }

    private void listenTCP() {
//    	bufferTCP.clear();
//    	bufferUDP.clear();
     	chat.showMessageNGTCP();
    	//chat.showMessageUDP();
//    	bufferTCP=chat.getBufferReaderTCP();
//    	bufferUDP=chat.getBufferReaderUDP();
        //TODO que hay q poner aca?
    }
    
    private void listenUDP(){ 	
    		chat.showMessageUDP();
    }

    /**
	 *  
	 */
	public void processBufferTCP(ByteBuffer b) {
	    //TODO Meter el winner
		char ident=b.getChar();
		//System.out.println("En el processBuffer "+ident);
        switch(ident){
        case 'a': 
            //Mensaje de lnhgica
        	//receiveLogic(actor);
            break;
        case Constants.PACK_SHOOT:
            //Mensaje de disparo
        	processShoot(bufferTCP);
            //makeShoot();
            break; 
        /*case Constants.PACK_CHANGE_TURN:{
            //Mensaje de cambio de turno
        	changeTurnUp();
            String userID;
			try {
				userID = chat.getDescodificador().decode(b).toString();
				grantTurnTo(userID);
			} catch (CharacterCodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            break; 
        }*/
        case Constants.PACK_WIND:{ 
            float wind=b.getFloat();
            //System.out.println("El de viento es "+wind);
        	//Mensaje de cambio de viento
            changeWind(wind);}
            break; 
        case Constants.PACK_ACTIVE_PLAYER:{ 
        	try {
        		    changeTurnUp();
				String userID = chat.getDescodificador().decode(b).toString();
				//userID.trim();
			//	System.out.println("El nombre del turno es: " + userID + "| Fin del nombre");
				grantTurnTo(userID);
        	} catch (CharacterCodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();}        	
            }
            break; 
        case Constants.PACK_END_TURN:{ 
        	//changeTurnDown();     	
        	setTurnState(Constants.END_TURN_TIME);
            }
            break; 
        case Constants.PACK_TURN_STATE:{ 
        	int state=b.getInt();
        	//System.out.println("Dentro del case "+state);
        	setTurnState(state);        	
            }
            break; 
        }		
	}

	/**
	 * 
	 */
	private void processShoot(ByteBuffer b) {
		b.rewind();
		char ident=b.getChar();
		if(ident==Constants.PACK_SHOOT){
			double f=b.getDouble();
			long l=b.getLong();
			makeShoot(f,l);		
		}	
	}

	/**
	 *  
     */
	public void processBufferUDP(ByteBuffer buffer) {
		char ident=buffer.getChar();    
		if(ident==Constants.PACK_ACTOR_STATE){     		
			reciveLogic(buffer);
		}
	}
	
	public ByteBuffer desmontar(){
    	
		ByteBuffer lectura=ByteBuffer.allocate(Constants.BYTE_BUFFER_SIZE);
    	//Limpiamos el buffer y nos colocamos al principio de el
    	lectura.clear(); 
    	lectura.rewind();
    	lectura.putChar(Constants.PACK_ACTOR_STATE);
    	lectura.putInt(myPlayer.getLogic().getActualState());
    	lectura.putInt(((LogicActorPlayer)myPlayer.getLogic()).getCurrentWeapon());
    	lectura.putDouble(myPlayer.getLogic().getAngle());
    	lectura.putDouble(myPlayer.getLogic().getAngleVelocity());
    	lectura.putDouble(myPlayer.getLogic().getInternalPosition().getX());
    	lectura.putDouble(myPlayer.getLogic().getInternalPosition().getY());
    	lectura.putInt(myPlayer.getLogic().getX());
    	lectura.putInt(myPlayer.getLogic().getY());
    	lectura.putFloat(myPlayer.getLogic().getPower());
    	lectura.putInt(myPlayer.getLogic().getWidth());
    	lectura.putInt(myPlayer.getLogic().getHeight());
    	lectura.putFloat(myPlayer.getLogic().getXVelocity());
    	lectura.putFloat(myPlayer.getLogic().getYVelocity());
    	lectura.putInt(myPlayer.getLogic().getDirection().getDirectionValue());
    	lectura.put((((LogicActorPlayer)myPlayer.getLogic()).getUserID()).getBytes());
  //  	System.out.println("GETBYTES= "+System.getProperty("line.separator").getBytes().toString());
    	//lectura.put(System.getProperty("line.separator").getBytes());
    	//int num =lectura.getInt();
    	//System.out.println("THE WINNER IS:"+num);
  //  	System.out.println(this.getActualState()+","+this.getHeight());    	
    	//String ls=lectura.toString();    	
    	return lectura;
    }
	/**
	 * El jugador activo recibe la orden del servidor de moverse.
	 * 
	 * NOTA: Snhlo se actualiza la lnhgica del jugador activo. El resto de los
	 * jugadores no tiene interaccinhn directa con sus respectivos personajes,
	 * por lo que los posibles movimientos resultantes de explosiones y caidas
	 * sernhn interpretados por los clientes, y no recibidos por red.
	 * 
	 * @param logic
	 *            Logica del jugador, que sustiturnh a la antigua.
	 *  
	 */
	private void reciveLogic(ByteBuffer buffer) {
     	buffer.rewind();
	   	if (buffer.hasRemaining()){
	   		char ch =buffer.getChar();
	   		int a=buffer.getInt();
	   		if((a<LogicActor.STATE_SET)&(a>LogicActor.STATE_MAKING_DAMAGE)){
	   			int weapon=buffer.getInt();
	   			double angle=buffer.getDouble();
	   			double angleVelocity=buffer.getDouble();
	   			double internaX=buffer.getDouble();
	   			double internaY=buffer.getDouble();
	   			int positioX=buffer.getInt();
	   			int positioY=buffer.getInt();
	   			float power=buffer.getFloat();
	   			int sizeX=buffer.getInt();
	   			int sizeY=buffer.getInt();
	   			float velocityX=buffer.getFloat();
	   			float velocityY=buffer.getFloat();
	   			int direX=buffer.getInt();
	   			try {
	   				userInTurn="";
					userInTurn =chat.getDescodificador().decode(buffer).toString();
				} catch (CharacterCodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	   			if(userInTurn.startsWith(((LogicActorPlayer)activePlayer.getLogic()).getUserID())){
	   				activePlayer.getLogic().setActualState(a);
	   				((LogicActorPlayer)activePlayer.getLogic()).setCurrentWeapon(weapon);
	   				activePlayer.getLogic().setAngle(angle);
	   				activePlayer.getLogic().setAngleVelocity(angleVelocity);
	   				activePlayer.getLogic().setInternalPosition(internaX,internaY);
	   				activePlayer.getLogic().setPosition(positioX,positioY);
	   				activePlayer.getLogic().setPower(power);
	   				activePlayer.getLogic().setSize(sizeX,sizeY);
	   				activePlayer.getLogic().setVelocity(velocityX,velocityY);
	   				activePlayer.getLogic().getDirection().setDirection(direX);
	    		}
	   		}
	    	}
	   }

    /**
     * El jugador activo recibe la orden del servidor de disparar
     * 
     * @param angle
     *            Angulo de disparo
     * @param power
     *            Fuerza del disparo
     */
    private void makeShoot(double angle, long power) {
        activePlayer.getTarget().getLogic().setAngle(angle);
        activePlayer.shot(stage, power);
    }

    /**
     * Se recibe la orden desde el servidor de cambiar de turno a favor del
     * jugador con userID. nhste mnhtodo le concede el turno.
     * 
     * @param userID
     *            Identidad del jugador
     */
    private void grantTurnTo(String userID) {
    	//System.out.println("Dentro del grantururu "+userID);
        activePlayer = stage.searchActor(userID);
        //System.out.println("En GrantTurnTo -> Raza: " + activePlayer.getRaceCode());

        if (myPlayer != null) {
        	//System.out.println("Entro en myPlayer != null");
            /*boolean comparation = userID.startsWith(((LogicActorPlayer) myPlayer
                    .getLogic()).getUserID());*/

            //Si al que le toca el turno soy yo
            if (userID.startsWith(((LogicActorPlayer) myPlayer.getLogic()).getUserID())) {
            //	System.out.println("En GrantTurnTo -> Es mi turno");
                setMyTurn(true);
                //myPlayer = activePlayer;
            } else {
            	//System.out.println("NO es mi turno");
                setMyTurn(false);
                //disableMove() No snh si hace falta
            }
        }
        //Si mi player ha muerto
        else {
            setMyTurn(false);
            //disableMove() No snh si hace falta
        }

        turnTimerAprox.reset(Constants.TURN_TIME);
    }

    private void receiveTurnQuery() {
        //Llego aqunh sabiendo q es mi turno
        setTurnState(Constants.END_TURN_TIME);
    }

    private void changeWind(float newWind) {
        this.stage.getEnvironment().setWind(newWind);
    }

    private void enableMove() {
        this.inputManager.setEnabled(true);
        //GameEngine.setMoveEnabled(true);
    }

    private void disableMove() {
        //activePlayer.stop();
        this.inputManager.setEnabled(false);
        //GameEngine.setMoveEnabled(false);
    }

    private void changeTurnDown() {
        setMyTurn(false);

        //nhapa del puto Ibon pa mover la cnhmara. No podnha usar los estados, no.
        // Tennha que meter otro booleano para hacer de nuestras vidas un
        // infierno de desgracias y desdichas.
        setTurnTransition(true);

        //enviar confirm al server
        chat.sendConfirmInterlude(roomGame);

        this.setTurnState(Constants.INTERLUDE);

        this.disableMove();

        AudioManager.getStaticAm().play("quack", false);
    }
    
	private void changeTurnUp() {
        //resetear valores de turno
        activePlayer.resetTurn();
        //turnTimerAprox.reset(Constants.TURN_TIME); Lo hago en grantTurnTo()
        setTurnState(Constants.PLAYING);

        AudioManager.getStaticAm().play("whistleUp", false);
    }

    /**
     *  
     */
    private boolean isAnyPlayerBusy() {
        boolean dying = false;
        int i = 0;
        ArrayList playersArray = stage.getActorsArray();

        while (i < playersArray.size() && !dying) {
            int actualState;

            actualState = ((Actor) playersArray.get(i)).getLogic()
                    .getActualState();
            dying = (actualState >= LogicActor.STATE_JUMP)
                    || (actualState == LogicActor.STATE_DYING);

            i++;
        }
        return dying;
    }
    
    private void sendLogic() {
    	//System.out.println("Send logic");
    	//TODO enviar logica
    	ByteBuffer bb = ByteBuffer.allocate(Constants.BYTE_BUFFER_SIZE);		
    	bb=desmontar();    	
    	bb.flip();
		chat.sendPackageUDP(bb);			
    }
    
    /**
     * @return Returns the myTurn.
     */
    public boolean isMyTurn() {
        return myTurn;
    }

    /**
     * @param myTurn
     *            The myTurn to set.
     */
    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see game.GameInterface#getTurnState()
     */
    public int getTurnState() {
        return turnState;
    }

    /**
     * 
     * @param state
     *            Valor del estado
     */
    public void setTurnState(int state) {
        this.turnState = state;
    }

    /*
     * (non-Javadoc)
     * 
     * @see game.GameInterface#getActivePlayer()
     */
    public ActorPlayer getActivePlayer() {
        return activePlayer;
    }

    /*
     * (non-Javadoc)
     * 
     * @see game.GameInterface#getTurnTime()
     */
    public int getTurnTime() {
        return turnTimerAprox.getTime();
    }

    /*
     * (non-Javadoc)
     * 
     * @see game.GameInterface#isTurnTransition()
     */
    public boolean isTurnTransition() {
        return turnTransition;
    }

    /*
     * (non-Javadoc)
     * 
     * @see game.GameInterface#setTurnTransition(boolean)
     */
    public void setTurnTransition(boolean turnTransition) {
        this.turnTransition = turnTransition;

    }

    /*
     * (non-Javadoc)
     * 
     * @see game.GameInterface#getWinner()
     */
    public int getWinner() {
        return winner;
    }

    /*
     * (non-Javadoc)
     * 
     * @see game.GameInterface#getActorWinner()
     */
    public ActorPlayer getActorWinner() {
        return (ActorPlayer) stage.getActorsArray().get(0);
    }

    public SecondsTimer getTimer() {
        return turnTimerAprox;
    }

	/**
	 * @return Returns the stage.
	 */
	public StageInterface getStage() {
		return stage;
	}
	/**
	 * @return Returns the myPlayer.
	 */
	public ActorPlayer getMyPlayer() {
		return myPlayer;
	}
	/**
	 * @param myPlayer The myPlayer to set.
	 */
	public void setMyPlayer(ActorPlayer myPlayer) {
		this.myPlayer = myPlayer;
	}
}