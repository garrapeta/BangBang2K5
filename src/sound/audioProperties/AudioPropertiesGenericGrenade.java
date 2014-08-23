package sound.audioProperties;

import game.logicEngine.LogicActor;

import java.io.File;

import sound.*;
import sound.audioManagers.AudioManager;

/**
 * @author GaRRaPeTa
 *  
 */
public class AudioPropertiesGenericGrenade extends AudioProperties {

    public AudioPropertiesGenericGrenade(AudioManager am) {
        super();
        initProperties(am);
    }


    // carga propertiesMap
    public void initProperties(AudioManager am) {
        AudioCondition ac;
        AudioEvent ae;

        // COMIENZA DISPARO
        ac = new AudioCondition(LogicActor.STATE_ANY, LogicActor.STATE_MOVING,
                LogicActor.STATE_ANY);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "genericThrow.wav"), AudioEvent.START_LOOP);
        loadEvent(ac, ae);

        ac = new AudioCondition(LogicActor.STATE_ANY, LogicActor.STATE_MOVING,
                LogicActor.STATE_ANY);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "genericThrow2.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //EXPLOTA
        ac = new AudioCondition(LogicActor.STATE_MOVING, LogicActor.STATE_ANY,
                LogicActor.STATE_ANY);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "genericThrow.wav"), AudioEvent.STOP_LOOP);
        loadEvent(ac, ae);

        ac = new AudioCondition(LogicActor.STATE_MOVING, LogicActor.STATE_ANY,
                LogicActor.STATE_ANY);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "genericBang3.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);
 
    }
}