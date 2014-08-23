package sound.audioProperties;

import game.logicEngine.LogicActor;

import java.io.File;

import sound.*;
import sound.audioManagers.AudioManager;
import util.*;

/**
 * @author GaRRaPeTa
 *  
 */
public class AudioPropertiesScraper extends AudioProperties {

    public AudioPropertiesScraper(AudioManager am) {
        super();
        initProperties(am);
    }

    // carga propertiesMap
    public void initProperties(AudioManager am) {
        AudioCondition ac;
        AudioEvent ae;

        //EMPIEZA A CAMINAR
        ac = new AudioCondition(LogicActor.STATE_ANY, LogicActor.STATE_MOVING,
                LogicActor.STATE_ANY);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "scraperWalk2.wav"), AudioEvent.START_LOOP);
        loadEvent(ac, ae);

        ac = new AudioCondition(LogicActor.STATE_ANY, LogicActor.STATE_MOVING,
                LogicActor.STATE_ANY);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "scraperWalk1.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //TERMINA DE CAMINAR
        ac = new AudioCondition(LogicActor.STATE_MOVING, LogicActor.STATE_ANY,
                LogicActor.STATE_ANY);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "scraperWalk2.wav"), AudioEvent.STOP_LOOP);
        loadEvent(ac, ae);

        ac = new AudioCondition(LogicActor.STATE_MOVING, LogicActor.STATE_ANY,
                LogicActor.STATE_ANY);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "scraperWalk3.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //SALTA
        ac = new AudioCondition(LogicActor.STATE_ANY, LogicActor.STATE_JUMP,
                LogicActor.STATE_ANY);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "scraperJump.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //CAE
        ac = new AudioCondition(LogicActor.STATE_ANY, LogicActor.STATE_FALLING,
                LogicActor.STATE_ANY);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "scraperFall.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //ATERRIZA
        ac = new AudioCondition(LogicActor.STATE_JUMP, LogicActor.STATE_ANY,
                LogicActor.STATE_ANY);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "scraperLand.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        ac = new AudioCondition(LogicActor.STATE_FALLING, LogicActor.STATE_ANY,
                LogicActor.STATE_ANY);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "scraperLand.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        ac = new AudioCondition(LogicActor.STATE_HIT_ON_AIR,
                LogicActor.STATE_ANY, LogicActor.STATE_ANY);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "scraperLand.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //DAnhO
        ac = new AudioCondition(LogicActor.STATE_ANY,
                LogicActor.STATE_HIT_ON_AIR, LogicActor.STATE_ANY);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "scraperHit.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        ac = new AudioCondition(LogicActor.STATE_ANY,
                LogicActor.STATE_HIT_ON_GROUND, LogicActor.STATE_ANY);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "scraperHit.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //MUERTE
        ac = new AudioCondition(LogicActor.STATE_ANY, LogicActor.STATE_DYING,
                LogicActor.STATE_ANY);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "scraperDie.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //DISPAROS

        //disparo MELEE1 (inicio animacion)
        ac = new AudioCondition(LogicActor.STATE_ANY,
                LogicActor.STATE_SHOOTING, 41);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "scraperSpeaks.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo MELEE1 (fin animacion)
        ac = new AudioCondition(LogicActor.STATE_SHOOTING,
                LogicActor.STATE_ENDSHOOTING, 41);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "genericMelee.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo MELEE2 (inicio animacion)
        ac = new AudioCondition(LogicActor.STATE_ANY,
                LogicActor.STATE_SHOOTING, 42);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
               + "scraperSpeaks.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo MELEE2 (fin animacion)
        ac = new AudioCondition(LogicActor.STATE_SHOOTING,
                LogicActor.STATE_ENDSHOOTING, 42);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "scraperBong.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo MELEE3 (inicio animacion)
        ac = new AudioCondition(LogicActor.STATE_ANY,
                LogicActor.STATE_SHOOTING, 43);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "scraperSpeaks.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo MELEE3 (fin animacion)
        ac = new AudioCondition(LogicActor.STATE_SHOOTING,
                LogicActor.STATE_ENDSHOOTING, 43);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "scraperBong.wav"), AudioEvent.PLAY);
         loadEvent(ac, ae);

        //disparo PARAB1 (principio animacion)
        ac = new AudioCondition(LogicActor.STATE_SHOOTING,
                LogicActor.STATE_ENDSHOOTING, 21);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "genericMissileLaunch.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo PARAB1 (fin animacion)
        ac = new AudioCondition(LogicActor.STATE_ANY,
                LogicActor.STATE_SHOOTING, 21);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "scraperParabolicA.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo PARAB2 (principio animacion)
        ac = new AudioCondition(LogicActor.STATE_SHOOTING,
                LogicActor.STATE_ENDSHOOTING, 22);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "genericMissileLaunch.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo PARAB2 (fin animacion)
        ac = new AudioCondition(LogicActor.STATE_ANY,
                LogicActor.STATE_SHOOTING, 22);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "scraperParabolicA.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo PARAB3 (principio animacion)
        ac = new AudioCondition(LogicActor.STATE_SHOOTING,
                LogicActor.STATE_ENDSHOOTING, 23);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "genericMissileLaunch.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo PARAB3 (fin animacion)
        ac = new AudioCondition(LogicActor.STATE_ANY,
                LogicActor.STATE_SHOOTING, 23);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "scraperParabolicA.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo GRENADE1 (fin animacion)
        ac = new AudioCondition(LogicActor.STATE_SHOOTING,
                LogicActor.STATE_ENDSHOOTING, 31);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "genericThrow.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo GRENADE1 (ppio animacion)
        ac = new AudioCondition(LogicActor.STATE_ANY,
                LogicActor.STATE_SHOOTING, 31);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "scraperSpeaks2.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo GRENADE2 (fin animacion)
        ac = new AudioCondition(LogicActor.STATE_SHOOTING,
                LogicActor.STATE_ENDSHOOTING, 32);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "genericThrow.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo GRENADE2 (ppio animacion)
        ac = new AudioCondition(LogicActor.STATE_ANY,
                LogicActor.STATE_SHOOTING, 32);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "scraperSpeaks2.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo GRENADE3 (fin animacion)
        ac = new AudioCondition(LogicActor.STATE_SHOOTING,
                LogicActor.STATE_ENDSHOOTING, 33);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "scraperHead.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo GRENADE3 (ppio animacion)
        ac = new AudioCondition(LogicActor.STATE_ANY,
                LogicActor.STATE_SHOOTING, 33);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "scraperSpeaks2.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo RECTO1 (fin animacion)
        ac = new AudioCondition(LogicActor.STATE_SHOOTING,
                LogicActor.STATE_ENDSHOOTING, 11);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "scraperRectilinear.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo RECTO1 (ppio animacion)
        ac = new AudioCondition(LogicActor.STATE_ANY,
                LogicActor.STATE_SHOOTING, 11);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "scraperRectilinear2.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo RECTO2 (fin animacion)
        ac = new AudioCondition(LogicActor.STATE_SHOOTING,
                LogicActor.STATE_ENDSHOOTING, 12);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "scraperRectilinear.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo RECTO2 (ppio animacion)
        ac = new AudioCondition(LogicActor.STATE_ANY,
                LogicActor.STATE_SHOOTING, 12);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "scraperRectilinear2.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo RECTO3 (fin animacion)
        ac = new AudioCondition(LogicActor.STATE_SHOOTING,
                LogicActor.STATE_ENDSHOOTING, 13);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "scraperRectilinear.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo RECTO3 (ppio animacion)
        ac = new AudioCondition(LogicActor.STATE_ANY,
                LogicActor.STATE_SHOOTING, 13);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "scraperRectilinear2.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

    }
}