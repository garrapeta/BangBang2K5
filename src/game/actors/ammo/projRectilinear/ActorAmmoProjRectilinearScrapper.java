package game.actors.ammo.projRectilinear;

import game.actors.ActorPlayer;
import game.actors.ammo.projParabolic.ActorAmmoProjParabolic;
import game.stages.Stage;

import java.awt.Point;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

import util.Constants;
import weapons.Explossion;

/*
 * Created on 26-abr-2005
 */

/**
 * @author Carlos Segovia
 */

/**
 * Clase que extiende al proyectil parabnhlico gennhrico y sirve 
 * para dar funcionalidades concretas al proyectil rectilnhneo
 * de los scrappers 
 */
public class ActorAmmoProjRectilinearScrapper extends ActorAmmoProjRectilinear {

    private String route;

    private ArrayList bangAnim;

    /**
     * Constructor de la clase
     * 
     * @param stage
     *            Pantalla
     * @param owner
     *            Actor-personaje que dispara
     */
    public ActorAmmoProjRectilinearScrapper(Stage stage, ActorPlayer owner,
            int power) {
        super(stage, owner);
        int damage=0;	
        int radius=0;

        //en funcion del power se configura de diferente manera
        switch (power) {

        //POWER 1
        case Constants.POW_1:
        	route = "art" + File.separator + "scrapers" + File.separator
            + "proyectiles" + File.separator + "tostada_recto1.png";

            this.audioPlayer = new sound.AudioPlayer(stage.getAudioManager(),
                    this.getLogic(),
            "sound.audioProperties.AudioPropertiesGenericRectilinear");

            damage=100;
            radius=50;
            
            break;

        //POWER 2
        case Constants.POW_2:
        	route = "art" + File.separator + "scrapers" + File.separator
            + "proyectiles" + File.separator + "tostada_recto2.png";

            this.audioPlayer = new sound.AudioPlayer(stage.getAudioManager(),
                    this.getLogic(),
            "sound.audioProperties.AudioPropertiesGenericRectilinear");

            damage=150;
            radius=100;
            
            break;

        //POWER 3
        case Constants.POW_3:
        	route = "art" + File.separator + "scrapers" + File.separator
            + "proyectiles" + File.separator + "tostada_recto3.png";

            this.audioPlayer = new sound.AudioPlayer(stage.getAudioManager(),
                    this.getLogic(),
            "sound.audioProperties.AudioPropertiesGenericRectilinear");

            damage=200;
            radius=100;
            
            break;

        //ARMAS SIN POWER
        default:
            break;
        }

        this.newExplosion(new Point(), Explossion.FRACTAL_POLYGON2, 10, 50 + (radius/10));

        bangAnim = new ArrayList(5);
        bangAnim.add("art" + File.separator + "bang0001.png");
        bangAnim.add("art" + File.separator + "bang0002.png");
        bangAnim.add("art" + File.separator + "bang0003.png");
        bangAnim.add("art" + File.separator + "bang0004.png");
        bangAnim.add("art" + File.separator + "bang0005.png");

        this.initLogic(40, 26, damage, radius);
        this.newFlyingAnim(route);
        this.newBangAnim(bangAnim);
    }

}