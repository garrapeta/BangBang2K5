package sound;

import java.io.File;

import sound.audioManagers.AudioManager;

/**
 * AudioEvent implementa un evento de sonido. Son eventos de sonido reproducir
 * un sample, empezar un loop, y parar un loop
 * 
 * @author Sergio Torres
 */
public class AudioEvent {

    // Constantes que definen la accion de un evento de sonido

    public static final int START_LOOP = 0;

    public static final int STOP_LOOP = 1;

    public static final int PLAY = 2;

    //	sample involucrado
    int sampleId;

    //	accion a ejecutar
    int action;


    /**
     * @param am Audio manager donde se asocia el evento
     * @param sample Sample involucardo
     * @param act Accion del evento
     */
    public AudioEvent(AudioManager am, File sample, int act) {
        sampleId = am.loadSample(sample);
        action = act;
    }


    /**
     * @return el Id del sample (en la tabla de samples del audiomanager)
     */
    public int getSampleId() {
        return sampleId;
    }


    /**
     * @return Returns the action.
     */
    public int getAction() {
        return action;
    }


    /**
     * @return Representacion string del evento
     */
    public String toString() {
        return ("<Sample " + sampleId + ", Accion " + action + ">");
    }
}