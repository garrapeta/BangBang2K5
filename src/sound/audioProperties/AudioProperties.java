package sound.audioProperties;

import java.util.ArrayList;

import sound.*;
import sound.audioManagers.AudioManager;

/**
 * Las propiedades de audio de un actor son la descripcion de que eventos de
 * sonido tiene que producir, y en que cambios de estado los tiene que producir
 * Esta clase es abstracta. Se debe extender con las propiedades de sonido de
 * cada uno de los actores que produzcan sonido.
 * 
 * En el AuadioManager de cada stage, existe una pool donde al iniciarse el
 * stage se cargan las propiedades de sonido de cada uno de los actores sonantes
 * del stage. Cuando un actor se crea, su AudioPlayer extrae de la pool sus
 * propias propiedades eficientemente (sin cargar todos los samples y crear
 * nuevos objetos)
 * 
 * @author Sergio Torres
 */
public abstract class AudioProperties {

    // asocia condiciones con eventos de sonido
    protected ArrayList properties;


    public AudioProperties() {
        properties = new ArrayList();
    }


    /**
     * @param am AudioManager asociado
     */
    public AudioProperties(AudioManager am) {
        initProperties(am);
    }


    /**
     * Inserta un evento en la propiedad de sonido descrito por su condicion y
     * el evento
     * 
     * @param ac condicion
     * @param ae evento
     */
    protected void loadEvent(AudioCondition ac, AudioEvent ae) {
        Object[] pair = { ac, ae };
        properties.add(pair);
    }


    /**
     * Inicia (crea) la propiedad de sonido. Este metodo debe ser reimplementado
     * en cada una de las clases que extiendan AudioProperties, de modo que se
     * creen unas propiedades de sonido especificas para cada actor.
     * 
     * @param am asociado
     */
    protected abstract void initProperties(AudioManager am);


    /**
     * @return ArrayList con las tuplas condicion-evento de estas propiedades de
     *         sonido
     */
    public ArrayList getProperties() {
        return properties;
    }

}