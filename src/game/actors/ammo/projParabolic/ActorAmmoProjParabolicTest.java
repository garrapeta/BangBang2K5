package game.actors.ammo.projParabolic;

import game.Animation;
import game.actors.ActorPlayer;
import game.actors.ammo.ActorAmmo;
import game.stages.Stage;

import java.awt.Point;
import java.io.File;

import util.Tools;
import weapons.*;

/**
 * Esta clase sirve como plantilla para la municion que tenga comportamiento de
 * proyectil. Se ha de cambiar el png que la representa. Tb podrnha contener los
 * atributos de destruccinhn.
 * 
 * @author CarlosG
 */
public class ActorAmmoProjParabolicTest extends ActorAmmo {
    public ActorAmmoProjParabolicTest(Stage stage, ActorPlayer owner) {
        super(owner);
        audioPlayer = new sound.AudioPlayer(stage.getAudioManager(), this
                .getLogic(),
                "sound.audioProperties.AudioPropertiesGenericParabolic");
        Animation flyingAnim = new Animation();
        flyingAnim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "tostada1.png"), 1);
        flyingAnim.setStopped(true);
        flyingAnim.start();
        this.logic.setSize(40, 26);
        graphicsEngine.setActiveAnimation(flyingAnim);
        graphicsEngine.setBangAnim(createBangAnim());
        this.behavior = new ProjParabolic(this.getLogic(), stage);
        Point p = new Point();
        //explossion = new Explossion(p, Explossion.FRACTAL_POLYGON,
        //10, 50.0);
        explossion = new Explossion(p, Explossion.FRACTAL_POLYGON2, 10, 50.0);
        //explossion = new Explossion(p, Explossion.FRACTAL_POLYGON3,
        //8, 50.0);
        //explossion = new Explossion(p, Explossion.FRACTAL_POLYGON4,
        //10, 50.0);
        //explossion = new Explossion(p, Explossion.FRACTAL_POLYGON5,
        //10, 50.0);
        //explossion = new Explossion(p, Explossion.FRACTAL_POLYGON6,
        //8, 50.0);
        //explossion = new Explossion(p, Explossion.FRACTAL_POLYGON7,
        //8, 50.0);

    }

    private Animation createBangAnim() {
        Animation bangAnim = new Animation();
        bangAnim.setDefaultFrameDuration(3);
        bangAnim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "bang0001.png"));
        bangAnim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "bang0002.png"));
        bangAnim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "bang0003.png"));
        bangAnim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "bang0004.png"));
        bangAnim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "bang0005.png"));
        //TODO Esto es una nhapa de lo peor. Cogemos el pto medio del png.
        bangAnim.setOffset(-100, -100);
        bangAnim.start();
        bangAnim.setStopped(false);
        return bangAnim;
    }
}