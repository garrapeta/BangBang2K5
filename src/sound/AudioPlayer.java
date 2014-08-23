package sound;

import game.actors.ActorInterface;
import game.actors.ammo.ActorAmmo;
import game.logicEngine.LogicActor;
import game.logicEngine.LogicActorInterface;
import game.logicEngine.*;

import java.util.ArrayList;

import sound.audioManagers.AudioManagerInterface;

/**
 * Cada actor que produzca sonidos tiene un AudioPlayer. En este AudioPlayer se
 * almacenan las propiedades de sonido del actor. En cada frame, el AudioPlayer
 * mira si se satisfacen condiciones de eventos de sonidos y si es asi, produce
 * el evento de sonido.
 * 
 * @author Sergio Torres
 */
public class AudioPlayer {

    protected ArrayList properties;

    // los eventos se seleccionan en funcion de la logica
    protected LogicActorInterface logic;

    // AudioManager desde el que se producen los sonidos y donde
    // se guardan los samples
    protected AudioManagerInterface am;

    //inicializacion
    protected int actualState = LogicActor.STATE_SET;

    protected int prevState = LogicActor.STATE_SET;

    /**
     * @param am
     *            AudioManger asociado
     * @param log
     *            Logica del actor
     */
    public AudioPlayer(AudioManagerInterface am, LogicActorInterface log,
            String actorId) {
        properties = new ArrayList();
        this.logic = log;
        this.am = am;
        init(actorId);
    }

    protected void init(String actorId) {
        properties = this.am.getActorProperties(actorId).getProperties();
    }

    /**
     * @return booleando indicando si ha habido cambio de estado
     */
    protected boolean checkStateChange() {
        prevState = actualState;
        actualState = (logic.getActualState());
        return actualState != prevState;

    }

    /**
     * Mira si se cumple una condicion. Una condicion se cumple si el estado de
     * cada uno de los campos de la condicion actual coincide con el estado de
     * cada uno de los campos de la condicion evaluada. El estado STATE ANY
     * coincide con cualquier valor.
     * 
     * @param actual
     *            Condicion actual del actor
     * @param evaluated
     *            Condicion que se comprueba
     * @return booleano que dice si se cumple la condicion
     */
    protected boolean matchsCondition(AudioCondition actual,
            AudioCondition evaluated) {
        boolean match;

        match = ((evaluated.getPrevState() == game.logicEngine.LogicActor.STATE_ANY) || evaluated
                .getPrevState() == actual.getPrevState())
                && ((evaluated.getActualState() == game.logicEngine.LogicActor.STATE_ANY) || evaluated
                        .getActualState() == actual.getActualState())
                && ((evaluated.getCurrentWeapon() == game.logicEngine.LogicActor.STATE_ANY) || evaluated
                        .getCurrentWeapon() == actual.getCurrentWeapon());

        return match;
    }

    /**
     * Devuelve la lista de eventos que hay que procesar en un estado descrito
     * por la condicion que se le pasa como parametro
     * 
     * @param actualCondition
     *            condicion que describe el estado del actor
     * @return lista de eventos que hay que procesar
     */
    protected ArrayList getEvents(AudioCondition actualCondition) {
        ArrayList eventsList = new ArrayList();

        for (int i = 0; i <= properties.size() - 1; i++) {
            Object[] pair = (Object[]) properties.get(i);
            AudioCondition evaluatedCondition = (AudioCondition) pair[0];

            if (matchsCondition(actualCondition, evaluatedCondition)) {
                AudioEvent event = (AudioEvent) pair[1];
                eventsList.add(event);
                //am.playAudioEvent(event);
            }
        }

        return eventsList;
    }

    /**
     * Metodo que dispara todos los eventos de sonido asociados a un actor con
     * la condicion que tenga el actor. Este metodo debe ser llamado en todos
     * los frames por cada uno de los actores.
     */
    public void playSounds() {
        if (am.isSoundOn()) {
            if (checkStateChange()) {
                //System.out.println(prevState + "->" + actualState);

                AudioCondition actualCondition;

                //si es el AudioPlayer de un ActorPlayer se tiene en cuenta el
                // arma
                if (this.logic instanceof LogicActorPlayer)
                    actualCondition = new AudioCondition(prevState,
                            actualState, ((LogicActorPlayer) logic)
                                    .getCurrentWeapon());

                //si no, da igual el arma
                else
                    actualCondition = new AudioCondition(prevState,
                            actualState, LogicActor.STATE_ANY);

                am.playAudioEvents(getEvents(actualCondition));
            }
        }
    }
}