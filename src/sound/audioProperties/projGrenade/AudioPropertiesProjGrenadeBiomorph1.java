package sound.audioProperties.projGrenade;

import game.logicEngine.LogicActor;

import java.io.File;

import sound.*;
import sound.audioManagers.AudioManager;
import sound.audioProperties.AudioProperties;

/**
 * @author GaRRaPeTa
 *  
 */
public class AudioPropertiesProjGrenadeBiomorph1 extends AudioProperties {

    public AudioPropertiesProjGrenadeBiomorph1(AudioManager am) {
        super();
        initProperties(am);
    }


    // carga propertiesMap
    public void initProperties(AudioManager am) {
        AudioCondition ac;
        AudioEvent ae;

        //COMIENZA DISPARO
        ac = new AudioCondition(LogicActor.STATE_ANY, LogicActor.STATE_MOVING,
                LogicActor.STATE_ANY);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "genericMissileDrop.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);


        //EXPLOTA
        ac = new AudioCondition(LogicActor.STATE_MOVING, LogicActor.STATE_ANY,
                LogicActor.STATE_ANY);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "genericMissileDrop.wav"), AudioEvent.STOP_LOOP);
        loadEvent(ac, ae);

        ac = new AudioCondition(LogicActor.STATE_MOVING, LogicActor.STATE_ANY,
                LogicActor.STATE_ANY);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "biomorphBang1.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

    }
}