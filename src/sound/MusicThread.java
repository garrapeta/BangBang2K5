package sound;

import java.io.*;
import java.util.ArrayList;

import javax.sound.sampled.*;

/**
 * Clase que sirve para reproducir un archivo .ogg o .mp3 en un hilo diferente
 * al del juego. Utiliza varias librerias, incluyendo javazoom SPI y JOrbis
 * 
 * @author Sergio Torres, javazoom
 */
public class MusicThread extends Thread implements Runnable {

    protected ArrayList playlist = new ArrayList();

    protected boolean loop;

    protected SourceDataLine musicLine;

    protected boolean musicOn = true;

    protected float volume;

    public MusicThread(ArrayList pl, boolean loop, float volume) {
        this.playlist = pl;
        this.loop = loop;
     }

    public void run() {
        //se reproduce siempre que loop sea true
        do {
            File musicFile;
            for (int i = 0; i < playlist.size(); i++) {
                try {
                    musicFile = (File) playlist.get(i);
                    // Crea un AudioInputStream para el archivo.
                    AudioInputStream in = AudioSystem
                            .getAudioInputStream(musicFile);
                    AudioInputStream din = null;
                    if (in != null) {
                        AudioFormat baseFormat = in.getFormat();
                        AudioFormat decodedFormat = new AudioFormat(
                                AudioFormat.Encoding.PCM_SIGNED, baseFormat
                                        .getSampleRate(), 16, baseFormat
                                        .getChannels(), baseFormat
                                        .getChannels() * 2, baseFormat
                                        .getSampleRate(), false);
                        // Crea AudioInputStream que es decodificado por
                        // VorbisSPI

                        din = AudioSystem
                                .getAudioInputStream(decodedFormat, in);
                        // Play

                        play(decodedFormat, din);
                        in.close();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } while (loop && musicOn);
    }

    public FloatControl getControl() {
        if (musicLine != null)
            return (FloatControl) (musicLine
                    .getControl(FloatControl.Type.MASTER_GAIN));
        else {
            return null;
        }
    }

    public void setVolume(float volume) {
        FloatControl gainControl = getControl();
        this.volume = volume;
        if (gainControl != null) {
            float db = (float) Math
                    .floor((Math.log(volume) / Math.log(10.0) * 20.0));
            if (db >= gainControl.getMinimum()
                    && db <= gainControl.getMaximum())
                gainControl.setValue(db);
        }
    }

    /**
     * Decodifica un AudioInputStream con el formato targetFormat
     * 
     * @param targetFormat
     * @param din
     * @throws IOException
     * @throws LineUnavailableException
     */
    protected void play(AudioFormat targetFormat, AudioInputStream din)
            throws IOException, LineUnavailableException {

        byte[] data = new byte[4096];
        this.musicLine = getLine(targetFormat);
        this.setVolume(volume);
        if (musicLine != null) {

            // Start

            musicLine.start();

            int nBytesRead = 0, nBytesWritten = 0;

            while (nBytesRead != -1 && musicOn) {
                nBytesRead = din.read(data, 0, data.length);
                if (nBytesRead != -1)
                    nBytesWritten = musicLine.write(data, 0, nBytesRead);

            }

            // Stop
            musicLine.drain();
            musicLine.stop();
            musicLine.close();
            din.close();

        }

    }

    /**
     * @param audioFormat
     * @return SourceDataLine a decodificar
     * @throws LineUnavailableException
     */
    protected SourceDataLine getLine(AudioFormat audioFormat)
            throws LineUnavailableException {
        SourceDataLine res = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class,
                audioFormat);
        res = (SourceDataLine) AudioSystem.getLine(info);
        res.open(audioFormat);
        return res;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public void setMusicOn(boolean musicOn) {
        this.musicOn = musicOn;
    }

    public void setPlaylist(ArrayList playlist) {
        this.playlist = playlist;
    }
}