package game.actors.ammo.projGrenade;

import game.Animation;
import game.actors.ActorPlayer;
import game.actors.ammo.ActorAmmo;
import game.stages.Stage;

import java.io.File;

import util.Tools;
import weapons.ProjGrenade;

/**
 * Esta clase sirve como plantilla para la municion que tenga comportamiento de
 * proyectil. Se ha de cambiar el png que la representa. Tb podrnha contener los
 * atributos de destruccinhn.
 * 
 * @author CarlosG
 */
public class ActorAmmoProjGrenadeTest extends ActorAmmo {

    public ActorAmmoProjGrenadeTest(Stage stage, ActorPlayer owner) {
        super(owner);
        audioPlayer = new sound.AudioPlayer(stage.getAudioManager(), this
                .getLogic(),
                "sound.audioProperties.AudioPropertiesGenericGrenade");

        Animation anim = new Animation();
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "bullet.png"), 1);
        anim.setStopped(true);
        anim.start();

        graphicsEngine.setActiveAnimation(anim);

        this.behavior = new ProjGrenade(this.getLogic(), stage);
    }
}