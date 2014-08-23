package sound.audioManagers;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import sound.*;
import sound.audioProperties.AudioProperties;

/**
 * AudioManager es el modulo de sonido que se usa en todo un stage. Contiene la
 * tabla de samples de todos los actores del stage, tiene una pool con las
 * propiedades de sonido de los actores, y el SoundManager
 * 
 * @author Sergio Torres
 *  
 */
public abstract class AudioManager implements AudioManagerInterface {

    // SoundManager que reproduce los samples
    // y gestiona los canales de la tarjeta de sonido
    protected SoundManager sm;

    // pool de las procpiedades de sonido de cada uno de los actores
    protected HashMap soundPropertiesPool;

    //tabla que identifica "samples no actores" con strings
    protected HashMap nonActorSamples;

    //lleva la cuenta de los samples cargados
    protected ArrayList loadedSamples;

    //thread donde se ejecutan los mp3 u ogg
    protected MusicThread musicThread;

    //sonido On
    protected boolean soundOn;
    
    //musica Sonando 
    protected boolean musicOn;

    //volumen musica
    protected float musicVolume;

    //vol sonido
    protected float soundVolume;

    //playlist de la musica
    protected ArrayList playlist = new ArrayList();

    //loop playlist
    protected boolean loopMusic = true;

    //referencia a un AudioManager estatico para poder hacer 
    //sonar samples no asociados  a actores
    protected static AudioManager staticAm;

    
    //METODOS
    
    
    public AudioManager(ArrayList playlist) {
        sm = new SoundManager();
        soundPropertiesPool = new HashMap();
        nonActorSamples = new HashMap();
        loadedSamples = new ArrayList();
        this.playlist = playlist;
        AudioManager.staticAm = this;
    }

    /**
     * Inserta en la pool las propiedades de sonido de todos los actores
     * sonantes que puedan aparecer en el stage. Este metodo debe ser
     * implementado en sus subclases para adaptar un AudioManager en concreto al
     * stage donde va a ser usado
     */
    public abstract void init();

    // carga un sample de manera que no se cargue dos veces
    // asociandole un String como identificador
    public int loadNonActorSample(File sample, String stringId) {
        int intId = loadSample(sample);
        nonActorSamples.put(stringId, new Integer(intId));
        return intId;
    }

    // carga un sample de manera que no se cargue dos veces
    public int loadSample(File sample) {
        if (loadedSamples.contains(sample)) {
            return loadedSamples.indexOf(sample);
        } else {
            System.out.print(".");
            int sampleId = sm.addSound(sample);
            loadedSamples.add(sampleId, sample);
            return sampleId;
        }
    }

    /**
     * Produce un evento de sonido (Reproduce / inicia loop / para loop)
     */
    public void playAudioEvent(AudioEvent event) {
        int action = event.getAction();

        switch (action) {
        case AudioEvent.START_LOOP:
            play(event.getSampleId(), true);
            break;
        case AudioEvent.STOP_LOOP:
            sm.stop(event.getSampleId());
            break;
        case AudioEvent.PLAY:
            play(event.getSampleId(), false);
            break;
        default:
            System.out.println("Accion de evento de sonido no definida");
            break;
        }
    }

    /**
     * Procesa una lista de eventos de sonido
     */
    public void playAudioEvents(ArrayList events) {
        for (int i = 0; i <= events.size() - 1; i++) {
            playAudioEvent((AudioEvent) events.get(i));
        }
    }

    /**
     * Devuelve las propiedades de sonido de un determinado actor (guardadas en
     * la pool)
     */
    public AudioProperties getActorProperties(String actor) {
        if (soundPropertiesPool.containsKey(actor)) {
            return (AudioProperties) soundPropertiesPool.get(actor);
        } else {
            System.out
                    .println("No existen propiedades de sonido para el actor "
                            + actor);
            return null;
        }
    }

    //pone el volumen a la musica
    public void setMusicVolume(float volume) {
        this.musicVolume = volume;
        if (musicThread != null && musicVolume >= 0)
            musicThread.setVolume(musicVolume);
    }

    //pone el volumen al sonido
    public void setSoundVolume(float volume) {
        this.soundVolume = volume;
        if (soundVolume >= 0)
            sm.setSoundVolume(volume);
    }

    
    public void setMusicOn(boolean musicOn) {
        this.musicOn = musicOn;

        if (this.musicOn == true) {
            musicThread = new MusicThread(playlist, loopMusic, musicVolume);
            musicThread.start();
        } else {
            musicThread.setMusicOn(false);
        }
    }

    public boolean isSoundOn() {
        return soundOn;
    }

    public void setSoundOn(boolean soundOn) {
        this.soundOn = soundOn;
    }

    public boolean isMusicOn() {
        return musicOn;
    }

    public float getMusicVolume() {
        return musicVolume;
    }

    public float getSoundVolume() {
        return soundVolume;
    }

    public static AudioManager getStaticAm() {
        return staticAm;
    }

    public static void setStaticAm(AudioManager staticAm) {
        AudioManager.staticAm = staticAm;
    }

    //hace sonar un sample a partir del id String con el que se
    //le ha cargado.
    //Usado para hacer sonar samples NO asociados a actores
    public void play(String sampleString, boolean loop) {
        if (isSoundOn()) {
            Integer sampleId = (Integer) nonActorSamples.get(sampleString);
            play(sampleId.intValue(), loop);            
        }
    }

    //para un sample a partir del id String con el que se
    //le ha cargado.
    //Usado para parar samples NO asociados a actores
    public void stop(String sampleString) {
        Integer sampleId = (Integer) nonActorSamples.get(sampleString);
        sm.stop(sampleId.intValue());
    }

    //escribe info sobre el AM
    public void printInfo() {
        System.out.println("El AudioManager tiene "
                + soundPropertiesPool.size() + " propiedades cargadas y "
                + loadedSamples.size() + " samples cargados");
    }

    //metodo para ejecutar samples a bajo nivel
    protected void play(int sampleId, boolean loop) {
        sm.play(sampleId, loop, sm.AUTO_ASSIGN_CHANNEL);
    }

    //Carga una propiedad de sonido en la pool a partir del
    //nombre de su clase
    protected void loadProperties(String className) {
        System.out.print("\t"+className+" ");
        try {
            //se crea una clase a partir del String de entrada
            Class propClass = Class.forName(className);

            //se crea un array con una clase AudioManager
            Class amClass = Class.forName("sound.audioManagers.AudioManager");
            Class[] params = { amClass };

            //se pide el constructor de la propiedad de sonido que
            //recibe un audiomanager como parametro
            Constructor propClassCons = propClass.getConstructor(params);

            //se crea un array con este AudioManager para pasar como
            //parametro al constructor anterior
            AudioManager[] params2 = { this };

            //se crea una nueva instancia de la AudioPropertie
            AudioProperties prop = (AudioProperties) propClassCons
                    .newInstance(params2);

            //se mete la propiedad en la pool indexada por su nobre de clase
            soundPropertiesPool.put(className, prop);

        } catch (SecurityException e1) {
            e1.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (InstantiationException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
        } catch (ClassNotFoundException e) {
            //System.out.println("Clase " + className + " no encontrda");
            System.out.println("NO EXISTE!!");
        } catch (NoSuchMethodException e1) {
            System.out
                    .println("La clase  "
                            + className
                            + "no tiene un constructor que reciba AudioManager como parametro");
        }
        System.out.println(" ok");
    }
}