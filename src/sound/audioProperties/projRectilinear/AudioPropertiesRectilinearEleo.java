package sound.audioProperties.projRectilinear;

import game.logicEngine.LogicActor;

import java.io.File;

import sound.*;
import sound.audioManagers.AudioManager;
import sound.audioProperties.AudioProperties;

/**
 * @author GaRRaPeTa
 *  
 */
public class AudioPropertiesRectilinearEleo extends AudioProperties {

    public AudioPropertiesRectilinearEleo(AudioManager am) {
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
                + "genericElectric.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //ac = new AudioCondition(LogicActor.STATE_ANY, LogicActor.STATE_MOVING,
        //      LogicActor.STATE_ANY);
        //ae = new AudioEvent(am, new File("soundFiles" + File.separator
        //        + "genericBow.wav"), AudioEvent.PLAY);
        //loadEvent(ac, ae);

        //EXPLOTA
        ac = new AudioCondition(LogicActor.STATE_MOVING, LogicActor.STATE_ANY,
                LogicActor.STATE_ANY);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "genericElectric.wav"), AudioEvent.STOP_LOOP);
        loadEvent(ac, ae);

        ac = new AudioCondition(LogicActor.STATE_MOVING, LogicActor.STATE_ANY,
                LogicActor.STATE_ANY);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "eleoRectilinear3.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);
 
    }
}