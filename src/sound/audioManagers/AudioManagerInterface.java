package sound.audioManagers;

import java.io.File;
import java.util.ArrayList;

import sound.*;
import sound.audioProperties.AudioProperties;

/**
 * Interfaz del AudioManger. Cada stage tiene una AudioManagerInterface como
 * atributo. Esta interfaz es instanciada en el constructor del stage.
 * 
 * @author Sergio Torres
 */
public interface AudioManagerInterface {

    public void init();

    public int loadSample(File sample);

    public void playAudioEvent(AudioEvent event);

    public void playAudioEvents(ArrayList events);

    public int loadNonActorSample(File sample, String stringId);

    public void stop(String sampleString);

    public void play(String sampleString, boolean loop);

    public AudioProperties getActorProperties(String actor);

    public void setMusicVolume(float volume);

    public void setSoundVolume(float volume);

    public void setMusicOn(boolean musicOn);

    public void setSoundOn(boolean soundOn);

    public boolean isSoundOn();

    public boolean isMusicOn();

    public float getSoundVolume();

    public float getMusicVolume();

    public void printInfo();

}