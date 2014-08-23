package sound.audioProperties;

import game.logicEngine.LogicActor;

import java.io.File;

import sound.*;
import sound.audioManagers.AudioManager;

/**
 * @author GaRRaPeTa
 *  
 */
public class AudioPropertiesGMarine extends AudioProperties {

    public AudioPropertiesGMarine(AudioManager am) {
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
                + "gmarineWalk.wav"), AudioEvent.START_LOOP);
        loadEvent(ac, ae);

        //TERMINA DE CAMINAR
        ac = new AudioCondition(LogicActor.STATE_MOVING, LogicActor.STATE_ANY,
                LogicActor.STATE_ANY);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "gmarineWalk.wav"), AudioEvent.STOP_LOOP);
        loadEvent(ac, ae);

        //PRESALTA
        ac = new AudioCondition(LogicActor.STATE_ANY, LogicActor.STATE_PREJUMP,
                LogicActor.STATE_ANY);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "gmarinePrejump2.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);
        
        //SALTA
        ac = new AudioCondition(LogicActor.STATE_PREJUMP, LogicActor.STATE_JUMP,
                LogicActor.STATE_ANY);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "gmarineJump.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);


        //CAE
        ac = new AudioCondition(LogicActor.STATE_ANY, LogicActor.STATE_FALLING,
                LogicActor.STATE_ANY);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "gmarineFall.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //ATERRIZA
        ac = new AudioCondition(LogicActor.STATE_JUMP, LogicActor.STATE_ANY,
                LogicActor.STATE_ANY);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "genericLand.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        ac = new AudioCondition(LogicActor.STATE_FALLING, LogicActor.STATE_ANY,
                LogicActor.STATE_ANY);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "genericLand.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        ac = new AudioCondition(LogicActor.STATE_HIT_ON_AIR,
                LogicActor.STATE_ANY, LogicActor.STATE_ANY);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "genericLand.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);


        //DAnhO
        ac = new AudioCondition(LogicActor.STATE_ANY,
                LogicActor.STATE_HIT_ON_AIR, LogicActor.STATE_ANY);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "gmarineHit.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        ac = new AudioCondition(LogicActor.STATE_ANY,
                LogicActor.STATE_HIT_ON_GROUND, LogicActor.STATE_ANY);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "gmarineHit.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //MUERE
        ac = new AudioCondition(LogicActor.STATE_ANY, LogicActor.STATE_DYING,
                LogicActor.STATE_ANY);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "gmarineDie.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //DISPAROS

        //disparo MELEE1 (inicio animacion)
        ac = new AudioCondition(LogicActor.STATE_ANY,
                LogicActor.STATE_SHOOTING, 41);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "gmarinePrejump2.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo MELEE1 (fin animacion)
        ac = new AudioCondition(LogicActor.STATE_SHOOTING,
                LogicActor.STATE_ENDSHOOTING, 41);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "gmarineMelee2.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo MELEE2 (inicio animacion)
        ac = new AudioCondition(LogicActor.STATE_ANY,
                LogicActor.STATE_SHOOTING, 42);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "gmarinePrejump2.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo MELEE2 (fin animacion)
        ac = new AudioCondition(LogicActor.STATE_SHOOTING,
                LogicActor.STATE_ENDSHOOTING, 42);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "gmarineMelee2.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo MELEE3 (inicio animacion)
        ac = new AudioCondition(LogicActor.STATE_ANY,
               LogicActor.STATE_SHOOTING, 43);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "gmarinePrejump2.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo MELEE3 (fin animacion)
        ac = new AudioCondition(LogicActor.STATE_SHOOTING,
                LogicActor.STATE_ENDSHOOTING, 43);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "gmarineMelee2.wav"), AudioEvent.PLAY);
         loadEvent(ac, ae);

        //disparo PARAB1 (inicio animacion)
        ac = new AudioCondition(LogicActor.STATE_ANY,
                LogicActor.STATE_SHOOTING, 21);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "gmarineParabolicShoot.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo PARAB1 (fin animacion)
        //ac = new AudioCondition(LogicActor.STATE_SHOOTING,
        //        LogicActor.STATE_ENDSHOOTING, 21);
        //ae = new AudioEvent(am, new File("soundFiles" + File.separator
        //        + "gmarineParabolicShoot.wav"), AudioEvent.PLAY);
        //loadEvent(ac, ae);

        //disparo PARAB2 (inicio animacion)
        ac = new AudioCondition(LogicActor.STATE_ANY,
                LogicActor.STATE_SHOOTING, 22);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "gmarineParabolicShoot.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo PARAB2 (fin animacion)
        //ac = new AudioCondition(LogicActor.STATE_SHOOTING,
        //        LogicActor.STATE_ENDSHOOTING, 22);
        //ae = new AudioEvent(am, new File("soundFiles" + File.separator
        //        + "biomorphParabolicA1.wav"), AudioEvent.PLAY);
        //loadEvent(ac, ae);

        //disparo PARAB3 (inicio animacion)
        ac = new AudioCondition(LogicActor.STATE_ANY,
                LogicActor.STATE_SHOOTING, 23);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "gmarineParabolicShoot.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo PARAB3 (fin animacion)
        //ac = new AudioCondition(LogicActor.STATE_SHOOTING,
        //        LogicActor.STATE_ENDSHOOTING, 23);
        //ae = new AudioEvent(am, new File("soundFiles" + File.separator
        //        + "biomorphParabolicA1.wav"), AudioEvent.PLAY);
        //loadEvent(ac, ae);

        //disparo GRENADE1 (inicio animacion)
        ac = new AudioCondition(LogicActor.STATE_ANY,
                LogicActor.STATE_SHOOTING, 31);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "gmarineGrenadeShoot.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo GRENADE1 (fin animacion)
        //ac = new AudioCondition(LogicActor.STATE_SHOOTING,
        //        LogicActor.STATE_ENDSHOOTING, 31);
        //ae = new AudioEvent(am, new File("soundFiles" + File.separator
        //        + "biomorphParabolicA1.wav"), AudioEvent.PLAY);
        //loadEvent(ac, ae);

        //disparo GRENADE2 (inicio animacion)
        ac = new AudioCondition(LogicActor.STATE_ANY,
                LogicActor.STATE_SHOOTING, 32);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "gmarineGrenadeShoot.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo GRENADE2 (fin animacion)
        //ac = new AudioCondition(LogicActor.STATE_SHOOTING,
        //        LogicActor.STATE_ENDSHOOTING, 32);
        //ae = new AudioEvent(am, new File("soundFiles" + File.separator
        //        + "biomorphParabolicA1.wav"), AudioEvent.PLAY);
        //loadEvent(ac, ae);

        //disparo GRENADE3 (inicio animacion)
        ac = new AudioCondition(LogicActor.STATE_ANY,
                LogicActor.STATE_SHOOTING, 33);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "gmarineGrenadeShoot.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo GRENADE3 (fin animacion)
        //ac = new AudioCondition(LogicActor.STATE_SHOOTING,
        //        LogicActor.STATE_ENDSHOOTING, 33);
        //ae = new AudioEvent(am, new File("soundFiles" + File.separator
        //        + "biomorphParabolicA1.wav"), AudioEvent.PLAY);
        //loadEvent(ac, ae);

        //disparo RECTILINEAR1 (inicio animacion)
        ac = new AudioCondition(LogicActor.STATE_ANY,
                LogicActor.STATE_SHOOTING, 11);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "gmarineRectilinearShoot.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo RECTILINEAR1 (fin animacion)
        //ac = new AudioCondition(LogicActor.STATE_SHOOTING,
        //        LogicActor.STATE_ENDSHOOTING, 11);
        //ae = new AudioEvent(am, new File("soundFiles" + File.separator
        //        + "gmarineRectilinearShoot.wav"), AudioEvent.PLAY);
        //loadEvent(ac, ae);

        //disparo RECTILINEAR2 (inicio animacion)
        ac = new AudioCondition(LogicActor.STATE_ANY,
               LogicActor.STATE_SHOOTING, 12);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "gmarineRectilinearShoot.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo RECTILINEAR2 (fin animacion)
        //ac = new AudioCondition(LogicActor.STATE_SHOOTING,
        //        LogicActor.STATE_ENDSHOOTING, 12);
        //ae = new AudioEvent(am, new File("soundFiles" + File.separator
        //        + "biomorphParabolicA1.wav"), AudioEvent.PLAY);
        //loadEvent(ac, ae);

        //disparo RECTILINEAR3 (inicio animacion)
        ac = new AudioCondition(LogicActor.STATE_ANY,
                LogicActor.STATE_SHOOTING, 13);
        ae = new AudioEvent(am, new File("soundFiles" + File.separator
                + "gmarineRectilinearShoot.wav"), AudioEvent.PLAY);
        loadEvent(ac, ae);

        //disparo RECTILINEAR3 (fin animacion)
        //ac = new AudioCondition(LogicActor.STATE_SHOOTING,
        //        LogicActor.STATE_ENDSHOOTING, 13);
        //ae = new AudioEvent(am, new File("soundFiles" + File.separator
        //        + "biomorphParabolicA1.wav"), AudioEvent.PLAY);
        //loadEvent(ac, ae);

    }
}