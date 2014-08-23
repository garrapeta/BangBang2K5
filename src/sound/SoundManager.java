package sound;

import java.io.*;
import java.util.Vector;

import javax.sound.sampled.*;

/**
 * El SoundManager gestiona los canales de la tarjeta de sonido. Esta clase es
 * la que se ocupa de reproducir sonido a mas bajo nivel en el paquete sound.
 * 
 * Esta clase es del libro de Andrew Mullholand y Glenn Murphy con algunos
 * retoques,
 * 
 * @author Andrew Mullholand y Glenn Murphy, Sergio Torres
 */
public class SoundManager implements LineListener {

    private Clip channelArray[];

    private int channelSoundIdRef[];

    private Vector soundByteData;

    public final int AUTO_ASSIGN_CHANNEL = -1;

    private float soundVolume=1;
    
    public SoundManager() {
        // Initialise the vector to hold the sound data...
        soundByteData = new Vector();
        channelSoundIdRef = new int[32];
        channelArray = new Clip[32];
        for (int i = 0; i < 32; i++) {
            channelSoundIdRef[i] = -1;
        }
    }

    public int addSound(File soundFile) {

        if (!soundFile.isFile()) {
            System.out
                    .println("Sound File '" + soundFile + "' does not exist!");
            System.exit(1);
        }

        byte[] tempArray = new byte[(int) soundFile.length()];

        try {
            DataInputStream inputStream = new DataInputStream(
                    new FileInputStream(soundFile));
            inputStream.read(tempArray);
            inputStream.close();
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("There was a problem reading the sound file: "
                    + soundFile);
            System.exit(1);
        }

        // Add it to the Vector...
        soundByteData.add(tempArray);

        // return its position in the vector...
        return soundByteData.size() - 1;
    }

    public void play(int soundId, boolean loop, int channelId) {
        // Check the channelId is valid...
        if (channelId < -1 || channelId >= 32) {
            System.out.println("Channel ID was out of range");
            return;
        }

        // Check the soundId is valid...
        if (soundId < 0 || soundId >= soundByteData.size()) {
            System.out.println("Sound ID was out of range");
            return;
        }

        int validChannelId = -1;
        if (channelId == AUTO_ASSIGN_CHANNEL) {
            // we need to find a suitable channel...

            // first find a free channel...

            for (int i = 0; i < 32; i++) {
                if (channelArray[i] == null || !channelArray[i].isOpen()) {
                    // this one will do...
                    validChannelId = i;
                    break;
                }
            }

            if (validChannelId == -1) {
                System.out.println("Could not find a suitable channel");
                return;
            }
        } else {
            // we need to ensure the selected channel is stopped...
            stopChannel(channelId);

            // set the valid channel id...
            validChannelId = channelId;
        }

        //System.out.println("Allocating Channel "+validChannelId);

        try {
            AudioInputStream audioInputStream = AudioSystem
                    .getAudioInputStream(new ByteArrayInputStream(
                            (byte[]) soundByteData.get(soundId)));

            // retreive the audio format...
            AudioFormat audioFormat = audioInputStream.getFormat();

            // set the line up
            DataLine.Info dataLineInfo = new DataLine.Info(Clip.class,
                    audioInputStream.getFormat(), ((int) audioInputStream
                            .getFrameLength() * audioFormat.getFrameSize()));

            // assign a clip (channel) for the sample
            channelArray[validChannelId] = (Clip) AudioSystem
                    .getLine(dataLineInfo);

            channelArray[validChannelId].addLineListener(this);

            channelArray[validChannelId].open(audioInputStream);
            channelSoundIdRef[validChannelId] = soundId;
            
            //se asigna el volumen
            FloatControl vol = (FloatControl) channelArray[validChannelId]
                    .getControl(FloatControl.Type.MASTER_GAIN);            
            vol.setValue((float) soundVolume);

            // play the clip (or loop it)
            if (loop == true)
                channelArray[validChannelId].loop(Clip.LOOP_CONTINUOUSLY);
            else
                channelArray[validChannelId].loop(0);

        } catch (Exception e) {

            System.out.println(e);
            if (channelArray[validChannelId] != null) {
                if (channelArray[validChannelId].isOpen()) {
                    channelArray[validChannelId].close();
                }

                channelArray[validChannelId] = null;
                channelSoundIdRef[validChannelId] = -1;
            }
        }
    }

    public void stop(int soundId) {
        // find the first occurrence of the sound and stop it...
        for (int i = 0; i < 32; i++) {
            if (channelSoundIdRef[i] == soundId) {
                // reset the channel...
                //System.out.println("Stopping Channel "+i);
                channelArray[i].stop();
                break;
            }
        }
    }

    public void stopChannel(int channelId) {
        if (isChannelPlaying(channelId)) {
            channelArray[channelId].stop();
            // note the 'update' method closes the channel properly
        }
    }

    public boolean isChannelPlaying(int channelId) {
        return (channelArray[channelId] != null && channelArray[channelId]
                .isOpen());
    }

    public boolean isSoundPlaying(int soundId) {
        // check to see if any occurence of the sound is playing...
        for (int i = 0; i < 32; i++) {
            if (channelSoundIdRef[i] == soundId) {
                return true;
            }
        }
        return false;
    }

    public void update(LineEvent e) {
        // handles samples stopping...
        if (e.getType() == LineEvent.Type.STOP) {
            // find the channel this line relates to...
            for (int i = 0; i < 32; i++) {
                if (channelArray[i] == e.getLine()) {
                    // reset the channel...
                    //System.out.println("Closing Channel "+i);
                    channelArray[i] = null;
                    channelSoundIdRef[i] = -1;
                }
            }

            // close the line...
            e.getLine().close();
        }
    }

    public void stopAllChannels() {
        // stop active channels...
        for (int i = 0; i < 32; i++)
            stopChannel(i);
    }
    
    public float getSoundVolume() {
        return soundVolume;
    }
    
    public void setSoundVolume(float volume) {        
        this.soundVolume = (float) Math.floor((Math.log(volume) / Math.log(10.0) * 20.0));
    }

}